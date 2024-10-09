package mg.itu.prom16;

import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import mg.itu.annotation.AnnotationController;
import mg.itu.annotation.FormParametre;
import mg.itu.annotation.Get;
import mg.itu.annotation.Parametre;
import mg.itu.annotation.RequestBody;
import mg.itu.annotation.RestApi;
import mg.itu.model.CustomSession;

import com.google.gson.Gson;

public class FrontController extends HttpServlet {
    Map<String, Mapping> urlMappings = new HashMap<>();
    boolean checked = false;
    ArrayList<Class<?>> list;

    public void init() throws ServletException {
        try {
            scanner();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to load controllers", e);
        }
    }

    private void scanner() throws ServletException {
        if (!checked) {
            String pack = getServletConfig().getInitParameter("ControllerPackage");
            if (pack == null) {
                throw new ServletException("The parameter name ControllerPackage doesn't exist");
            } else {
                try {
                    list = getClassesInSpecificPackage(pack);
                    getListController(pack);
                    checked = true;
                } catch (Exception e) {
                    list = new ArrayList<>();
                    throw new ServletException(e.getMessage());
                }
            }
        }
    }

    private void getListController(String package_name) throws Exception {
        String path = "WEB-INF/classes/" + package_name.replace(".", "/");
        path = getServletContext().getRealPath(path);
        File file = new File(path);

        list.clear();

        if (file.exists()) {
            if (file.isDirectory()) {
                for (File uniquefile : file.listFiles()) {
                    if (uniquefile.isFile() && uniquefile.getName().endsWith(".class")) {
                        String className = package_name + "." + uniquefile.getName().replace(".class", "");
                        Class<?> clazz = Class.forName(className);
                        if (clazz.isAnnotationPresent(AnnotationController.class)) {
                            list.add(clazz);

                            for (Method method : clazz.getMethods()) {
                                if (method.isAnnotationPresent(Get.class)) {
                                    Mapping mapping = new Mapping(clazz.getName(), method);
                                    String key = method.getAnnotation(Get.class).value();
                                    if (urlMappings.containsKey(key)) {
                                        throw new Exception("The method " + urlMappings.get(key).getMethod()
                                                + " have already the url " + key
                                                + ", so you can't affect this url with the method "
                                                + mapping.getMethod());
                                    }
                                    urlMappings.put(key, mapping);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            throw new Exception("The package " + package_name + " doesn't exist");
        }
    }

    private ArrayList<Class<?>> getClassesInSpecificPackage(String packageName)
            throws IOException, ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("file")) {
                File directory = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
                if (directory.exists() && directory.isDirectory()) {
                    File[] files = directory.listFiles();
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".class")) {
                            String className = packageName + '.'
                                    + file.getName().substring(0, file.getName().length() - 6);
                            Class<?> clazz = Class.forName(className);
                            if (clazz.isAnnotationPresent(AnnotationController.class)) {
                                classes.add(clazz);
                            }
                        }
                    }
                }
            }
        }
        return classes;
    }

    private Object miCast(Class<?> targetType, String value) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType == LocalDate.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(value, formatter);
        } else if (targetType == Date.class) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return dateFormat.parse(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Format de date ivalide", e);
            }
        } else {
            throw new IllegalArgumentException("Type de paramètre non supporté: " + targetType);
        }
    }

    // private Object getValueInMethod(HttpServletRequest request, Mapping map)
    // throws Exception{
    // Object returnValue = null;
    // try {
    // Class<?> clazz = Class.forName(map.getClassName());
    // map.getMethod().setAccessible(true);

    // Parameter[] methodParams = map.getMethod().getParameters();
    // Object[] args = new Object[methodParams.length];

    // Enumeration<String> params = request.getParameterNames();
    // Map<String, String> paramMap = new HashMap<>();

    // while (params.hasMoreElements()) {
    // String paramName = params.nextElement();
    // paramMap.put(paramName, request.getParameter(paramName));
    // }
    // for (int i = 0; i < methodParams.length; i++) {
    // // if (methodParams[i].isAnnotationPresent(RequestBody.class)) {
    // // Class<?> paramType = methodParams[i].getType();
    // // Object paramObject = paramType.getDeclaredConstructor().newInstance();
    // // for (Field field : paramType.getDeclaredFields()) {
    // // String paramName = field.isAnnotationPresent(FormParametre.class) ?
    // field.getAnnotation(FormParametre.class).value() : field.getName();
    // // if (paramMap.containsKey(paramName)) {
    // // field.setAccessible(true);
    // // field.set(paramObject, paramMap.get(paramName));
    // // }
    // // }
    // // args[i] = paramObject;
    // // }
    // // //sprint6
    // // else if (methodParams[i].isAnnotationPresent(Parametre.class)) {
    // // String paramName = methodParams[i].getAnnotation(Parametre.class).name();
    // // String paramValue = paramMap.get(paramName);
    // // args[i] = paramValue;
    // // } else {
    // // if (paramMap.containsKey(methodParams[i].getName())) {
    // // args[i] = paramMap.get(methodParams[i].getName());
    // // } else {
    // // args[i] = null;
    // // }
    // // }
    // if (methodParams[i].isAnnotationPresent(RequestBody.class)) {
    // Class<?> paramType = methodParams[i].getType();
    // Object paramObject = paramType.getDeclaredConstructor().newInstance();
    // for (Field field : paramType.getDeclaredFields()) {
    // String paramName = field.isAnnotationPresent(FormParametre.class) ?
    // field.getAnnotation(FormParametre.class).value() : field.getName();
    // if (paramMap.containsKey(paramName)) {
    // field.setAccessible(true);
    // field.set(paramObject, miCast(field.getType(), paramMap.get(paramName)));
    // }
    // }
    // args[i] = paramObject;
    // } else if (methodParams[i].isAnnotationPresent(Parametre.class)) {
    // String paramName = methodParams[i].getAnnotation(Parametre.class).name();
    // String paramValue = paramMap.get(paramName);
    // args[i] = miCast(methodParams[i].getType(), paramValue);
    // } else {
    // if (paramMap.containsKey(methodParams[i].getName())) {
    // args[i] = miCast(methodParams[i].getType(),
    // paramMap.get(methodParams[i].getName()));
    // } else {
    // args[i] = null;
    // }
    // }
    // }

    // Object instance = clazz.getDeclaredConstructor().newInstance();
    // returnValue = map.getMethod().invoke(instance, args);
    // } catch (Exception e) {
    // throw e;
    // }
    // return returnValue;
    // }

    private Object getValueInMethod(HttpServletRequest request, Mapping map) throws Exception {
        Object returnValue = null;
        try {
            Class<?> clazz = Class.forName(map.getClassName());
            map.getMethod().setAccessible(true);

            Parameter[] methodParams = map.getMethod().getParameters();

            if (methodParams.length == 0) {
                throw new IllegalArgumentException(
                        "ETU002616: methode " + map.getMethod().getName() + " sans parametres.");
            }

            Object[] args = new Object[methodParams.length];

            Enumeration<String> params = request.getParameterNames();
            Map<String, String> paramMap = new HashMap<>();

            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                paramMap.put(paramName, request.getParameter(paramName));
            }

            for (int i = 0; i < methodParams.length; i++) {
                if (methodParams[i].getType().equals(CustomSession.class)) {
                    CustomSession session = new CustomSession();
                    session.setSession(request.getSession());
                    args[i] = session;
                } else if (methodParams[i].isAnnotationPresent(RequestBody.class)) {
                    Class<?> paramType = methodParams[i].getType();
                    Object paramObject = paramType.getDeclaredConstructor().newInstance();
                    for (Field field : paramType.getDeclaredFields()) {
                        String paramName = field.isAnnotationPresent(FormParametre.class)
                                ? field.getAnnotation(FormParametre.class).value()
                                : field.getName();
                        if (paramMap.containsKey(paramName)) {
                            field.setAccessible(true);
                            field.set(paramObject, miCast(field.getType(), paramMap.get(paramName)));
                        }
                    }
                    args[i] = paramObject;
                } else if (methodParams[i].isAnnotationPresent(Parametre.class)) {
                    String paramName = methodParams[i].getAnnotation(Parametre.class).name();
                    String paramValue = paramMap.get(paramName);
                    args[i] = miCast(methodParams[i].getType(), paramValue);
                } else {
                    if (paramMap.containsKey(methodParams[i].getName())) {
                        args[i] = miCast(methodParams[i].getType(), paramMap.get(methodParams[i].getName()));
                    } else {
                        args[i] = null;
                    }
                }
            }

            Object instance = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(CustomSession.class)) {
                    field.setAccessible(true);
                    CustomSession session = new CustomSession();
                    session.setSession(request.getSession());
                    field.set(instance, session);
                    break;
                }
            }
            returnValue = map.getMethod().invoke(instance, args);
        } catch (Exception e) {
            throw e;
        }
        return returnValue;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Gson gson = new Gson();

        String url = request.getRequestURI().substring(request.getContextPath().length());
        try {
            PrintWriter out = response.getWriter();
            Mapping mapping = urlMappings.get(url);
            if (mapping != null) {

                // Récupérer la valeur de retour de la méthode
                Object ob = getValueInMethod(request, mapping);

                // Vérifier si la classe ou méthode est annotée @RestApi
                boolean isRestApi = mapping.getMethod().isAnnotationPresent(RestApi.class);

                if (ob != null) {
                    if (ob instanceof String) {
                        // out.println("Method <b>" + mapping.getMethod().getName() + "</b> Value: <b>"
                        // + ob+"<b>");
                        // Renvoyer directement l'object si c'est un String
                        out.println(ob);
                    } else if (ob instanceof ModelAndView mw) {
                        // ModelAndView mw = (ModelAndView) ob;
                        if (isRestApi) {

                            String jsonResponse = gson.toJson(mw.data); // convertir les données en json
                            response.setContentType("application/json"); // indiquer que la reponse est json
                            out.println(jsonResponse); // envoyer la reponse json

                        } else {

                            // Comportement mahazatra ModelAndView
                            for (String cle : mw.getData().keySet()) {
                                request.setAttribute(cle, mw.getData().get(cle));
                            }
                            // RequestDispatcher dispacther = request.getRequestDispatcher(mw.getUrl());
                            // dispacther.forward(request, response);
                            response.setContentType("text/html;charset=UTF-8");
                            out.println("Page HTML à afficher.");
                        }
                    } else {
                        ModelAndView mw = (ModelAndView) ob;
                        if (isRestApi) {
                            String jsonResponse = gson.toJson(mw.data); // convertir les données en json
                            response.setContentType("application/json"); // indiquer que la reponse est json
                            out.println(jsonResponse); // envoyer la reponse json

                        } else {

                            throw new ServletException("Failed to return the value",
                                    new Exception("The value returned by the method <b>" + mapping.getMethod()
                                            + "</b> is not in the framework"));
                        }

                    }
                } else {
                    throw new ServletException("No value returned");
                }

            } else {
                throw new ServletException("Failed to get the method",
                        new Exception("No Get method associated with the URL: <b>" + url + "</b>"));
            }
        } catch (Exception e) {
            try (PrintWriter out = response.getWriter()) {
                out.println(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Erreur: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Erreur: " + ex.getMessage());
        }
    }
}
