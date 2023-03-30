package edu.school21.app;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Utils {
    public static Set<Class> getAllClasses(String packageName) throws URISyntaxException {

        Set<Class> classes = new HashSet<>();
        try {
            File folder = new File(Utils.class.getResource(packageName).toURI());
            File[] classList = folder.listFiles();
            for (File f : classList) {
                if (f.getName().endsWith(".class")) {
                    String name = packageName.replace('/', '.').substring(1) + "." +
                            f.getName().substring(0, f.getName().length() - 6);
                    classes.add(Class.forName(name));
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(classes).orElse(new HashSet<>());
    }

    public static String getClassName(Class aClass) {
        return getVal(aClass.getName());
    }

    public static String getVal(String str) {
        if (str.contains(".")) {
            str = str.substring(str.lastIndexOf('.') + 1);
        }
        return str;
    }

    public static String getPackageName(Class aClass) {
        String name = aClass.getName();
        name = name.substring(0, name.lastIndexOf('.'));
        return name;
    }

    public static String getFieldName(Field field) {
        String str = field.getType().getName();
        return getVal(str) + " " + field.getName();
    }

    public static String getMethodsName(Method method) {
        String name = method.getReturnType().getName();
        return getVal(name) + " " + getMethodName(method);
    }

    public static String getMethodName(Method method) {
        String name = method.toString().substring(0, method.toString().indexOf('(') + 1);
        name = getVal(name);

        Class[] params = method.getParameterTypes();
        for (int i = 0; i < params.length; i++) {
            name += getVal(params[i].getName());

            if (i + 1 < params.length) {
                name += ", ";
            }
        }
        name += ")";
        return name;
    }
}
