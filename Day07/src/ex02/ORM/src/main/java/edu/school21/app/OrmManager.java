package edu.school21.app;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;

public class OrmManager {
    private String packageName;
    private Connection connection;

    public OrmManager(String packageName, DataSource dataSource) {
        this.packageName = packageName;
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() throws ClassNotFoundException, SQLException {
        String scannedPath = packageName.replace('.', '/');
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) { System.out.println("Not found classes!"); }
        File scannedDir = new File(scannedUrl.getFile());
        PreparedStatement statement;
        for (File file : scannedDir.listFiles()) {
            String className = file.getName().replace(".class", "");
            Class<?> aClass = Class.forName(packageName + className);

            if (aClass.isAnnotationPresent(OrmEntity.class)) {
                OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                String dropQuery = "DROP TABLE " + entity.table() + " IF EXISTS;";
                statement = connection.prepareStatement(dropQuery);
                statement.execute();
                System.out.println(dropQuery);
                String createQuery = "CREATE TABLE " + entity.table() + "(\n";
                for (Field f : aClass.getDeclaredFields()) {
                    if (f.isAnnotationPresent(OrmColumnId.class)) {
                        String typeName = f.getType().getSimpleName();
                        if (typeName.equals("Long")) {
                            createQuery += "\t" + f.getName() + " BIGINT PRIMARY KEY,\n";
                        } else {
                            System.err.println("Wrong type");
                        }
                    } else if (f.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = f.getAnnotation(OrmColumn.class);
                        createQuery += "\t" + column.name() + " " +
                                defineType(f.getType().getSimpleName(), 10, aClass.getName() + "." + f.getName()) + ",\n";
                    }
                }
                createQuery += ");";
                statement = connection.prepareStatement(createQuery);
                statement.execute();
                System.out.println(createQuery);
            }
        }
    }

    private String defineType(String typeName, int length, String classWithField) {
        if (typeName.equals("Integer")) { return "INT"; }
        else if (typeName.equals("Long")) {	return "BIGINT"; }
        else if (typeName.equals("String")) { return "VARCHAR(" + length + ")"; }
        else if (typeName.equals("Double")) { return "DOUBLE"; }
        else if (typeName.equals("Boolean")) { return "BOOLEAN"; }
        else {
            throw new IllegalArgumentException("Not valid type");
        }
    }

    public void save(Object object) throws IllegalAccessException, SQLException {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
            String saveQuery = "INSERT INTO " + entity.table() + "(id, ";
            String fieldsStr = "";
            String valStr = "";
            String id = "1, ";
            for (Field f : aClass.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(OrmColumnId.class)) {
                    id = f.get(object).toString() + ", ";
                } else if (f.isAnnotationPresent(OrmColumn.class)) {
                    OrmColumn column = f.getAnnotation(OrmColumn.class);
                    fieldsStr += column.name() + ", ";
                    if (f.getType().getSimpleName().equals("String")) {
                        valStr += "'" + f.get(object) + "', ";
                    } else {
                        valStr += f.get(object) + ", ";
                    }
                }
            }
            if (fieldsStr.equals("") && valStr.equals("")) {
                System.out.println("Not saved");
                return;
            }
            fieldsStr = fieldsStr.substring(0, fieldsStr.length() - 2);
            valStr = valStr.substring(0, valStr.length() - 2);
            saveQuery += fieldsStr + ") VALUES (" + id + valStr + ");";
            PreparedStatement statement = connection.prepareStatement(saveQuery);
            statement.execute();
            System.out.println(saveQuery);
        }
    }

    public <T> T findById(Long id, Class<T> aClass) throws SQLException, InstantiationException, IllegalAccessException {
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
            String findQuery = "SELECT * FROM " + entity.table() + " WHERE id = " + id + ";";
            PreparedStatement statement = connection.prepareStatement(findQuery);
            statement.execute();
            System.out.println(findQuery);
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                T object = aClass.newInstance();
                for (Field f : aClass.getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(OrmColumnId.class)) {
                        f.set(object, resultSet.getLong("id"));
                    } else if (f.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = f.getAnnotation(OrmColumn.class);
                        f.set(object, resultSet.getObject(column.name()));
                    }
                }
                return object;
            }
            System.out.println("Not found shiiit");
            return null;
        }
        System.out.println("Not in the class");
        return null;
    }

    public void update(Object object) throws SQLException, IllegalAccessException {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
            String updateQuery = "UPDATE " + entity.table() + " SET ";
            String id = "1";
            for (Field f : aClass.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(OrmColumnId.class)) {
                    id = f.get(object).toString();
                } else if (f.isAnnotationPresent(OrmColumn.class)) {
                    OrmColumn column = f.getAnnotation(OrmColumn.class);
                    updateQuery += column.name() + " = ";
                    if (f.getType().getSimpleName().equals("String")) {
                        updateQuery += "'" + f.get(object) + "', ";
                    } else {
                        updateQuery += f.get(object) + ", ";
                    }
                }
            }
            updateQuery = updateQuery.substring(0, updateQuery.length() - 2);
            updateQuery += " WHERE id = " + id + ";";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.execute();
            System.out.println(updateQuery);
        }
    }
}