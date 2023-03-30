package edu.school21.app;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Program {
    private static Class aClass;
    private static Object object;
    private static final List<Class> listOfClasses = new ArrayList<>();
    private static List<Field> fields;
    private static List<Method> methods = new ArrayList<>();

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            ClassNotFoundException, URISyntaxException {
        listOfClasses.addAll(Utils.getAllClasses("/edu/school21/classes"));
        System.out.println("Classes:");
        listOfClasses.forEach(cl -> System.out.println(Utils.getClassName(cl)));

        System.out.println("------------------");
        System.out.println("Enter class name: ");

        List<Class> listName = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String strInp = scanner.nextLine();
        for (Class cl : listOfClasses) {
            if (Utils.getClassName(cl).equals(strInp)) {
                listName.add(cl);
            }
        }
        if (listName.isEmpty()) {
            System.out.println("Wrong input");
            System.exit(-1);
        } else {
            aClass = listName.get(0);
        }

        fields = Arrays.asList(aClass.getDeclaredFields());
        List<Method> listOfMethods = Arrays.asList(aClass.getMethods());
        for (Method method : listOfMethods) {
            if (method.toString().contains(Utils.getPackageName(aClass))) {
                if (!method.toString().contains("toString()")) {
                    methods.add(method);
                }
            }
        }

        System.out.println("------------------");
        System.out.println("fields: ");
        fields.forEach(field -> System.out.println("\t" + Utils.getFieldName(field)));
        System.out.println("methods: ");
        methods.forEach(method -> System.out.println("\t" + Utils.getMethodsName(method)));

        System.out.println("------------------");
        System.out.println("Let's create an object.");
        List<Object> fieldsOfObject = new ArrayList<>();
        Constructor constructor = Arrays.stream(aClass.getConstructors()).
                filter(cons -> (cons.getParameterCount() > 0)).collect(Collectors.toList()).get(0);

        for (Field field : fields) {
            newParameters(field.getName() + ":", field.getType(), fieldsOfObject);
        }
        object = constructor.newInstance(fieldsOfObject.toArray());
        System.out.println("Object created: " + object);
        System.out.println("------------------");

        System.out.println("Enter name of the field for changing:");
        System.out.print("-> ");
        setFieldsOfClass();
        System.out.println("------------------");

        System.out.println("Enter name of the method for call:");
        System.out.print("-> ");
        methodsCall();
        System.out.println("------------------");
    }

    private static void newParameters(String name, Class param, List<Object> listOfParameters) {
        while (true) {
            System.out.println(name);
            Scanner scanner = new Scanner(System.in);
            System.out.print("-> ");
            String inputStr = scanner.nextLine().trim();

            try {
                if (param.getName().contains("String")) {
                    listOfParameters.add(inputStr);
                } else if (param.getName().contains("int")) {
                    listOfParameters.add(Integer.parseInt(inputStr));
                } else if (param.getName().contains("double")) {
                    listOfParameters.add(Double.parseDouble(inputStr));
                } else if (param.getName().contains("boolean")) {
                    listOfParameters.add(Boolean.parseBoolean(inputStr));
                } else if (param.getName().contains("long")) {
                    listOfParameters.add(Long.parseLong(inputStr));
                } else {
                    System.out.println("Fuck U");
                    System.exit(-1);
                }
                break;
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void setFieldsOfClass() throws IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String inputStr = scanner.nextLine().trim();
            List<Field> fieldList = fields.stream().filter(field -> inputStr.equalsIgnoreCase(field.getName()))
                    .collect(Collectors.toList());

            if (!fieldList.isEmpty()) {
                Field field = fieldList.get(0);
                field.setAccessible(true);
                System.out.println("Enter " + Utils.getFieldName(field) + " value");
                String val = scanner.nextLine().trim();
                String str = field.getType().getName();


                if (str.contains("String")) {
                    field.set(object, val);
                } else if (str.contains("int")) {
                    field.set(object, Integer.parseInt(val));
                } else if (str.contains("double")) {
                    field.set(object, Double.parseDouble(val));
                } else if (str.contains("boolean")) {
                    field.set(object, Boolean.parseBoolean(val));
                } else if (str.contains("long")) {
                    field.set(object, Long.parseLong(val));
                } else {
                    System.out.println("hren");
                }
                System.out.println("Object updated: " + object);
                break;
            } else {
                System.out.println("Fuck off");
            }
        }
    }

    private static void methodsCall() {
        while (true) {
            List<Object> objectList = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String inputStr = scanner.nextLine().trim();
            List<Method> methodList = (List<Method>) methods.stream().filter(method -> inputStr
                    .equalsIgnoreCase(Utils.getMethodName(method))).collect(Collectors.toList());

            if (!objectList.isEmpty()) {
                Method method = methodList.get(0);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                for (Class pr : method.getParameterTypes()) {
                    newParameters("Enter " + Utils.getVal(pr.getName()) + " value", pr, objectList);
                }
                if (!method.getReturnType().getName().equalsIgnoreCase("void")) {
                    System.out.println("Method returned: ");
                    System.out.println(object);
                }
            }
            break;
        }
    }
}
