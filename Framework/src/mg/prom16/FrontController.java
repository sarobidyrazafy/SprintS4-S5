package mg.prom16;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import Annotations.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {

    // Stocker les classes des contrôleurs
    protected List<Class<?>> list_controller = new ArrayList<>();
    // Pour stocker les mappings URL vers les méthodes
    protected Map<String, Mapping> urlMappings = new HashMap<>();

    protected void getControllerList(String package_name) throws ServletException, ClassNotFoundException {
        // Construire le chemin vers le dossier des classes du package
        String bin_path = "WEB-INF/classes/" + package_name.replace(".", "/");
    
        // Obtenir le chemin réel du contexte de servlet
        bin_path = getServletContext().getRealPath(bin_path);
    
        // Référence au dossier des classes
        File b = new File(bin_path);
    
        // Vider la liste des contrôleurs avant de la remplir
        list_controller.clear();
        
        for (File onefile : b.listFiles()) {
            if (onefile.isFile() && onefile.getName().endsWith(".class")) {
                // Charger dynamiquement la classe
                Class<?> clazz = Class.forName(package_name + "." + onefile.getName().split(".class")[0]);
                // Vérifier si la classe est annotée avec @Controller
                if (clazz.isAnnotationPresent(Controller.class))
                
                list_controller.add(clazz);
    
                for (Method method : clazz.getMethods()) {
                    // Vérifier si la méthode est annotée avec @Get
                    if (method.isAnnotationPresent(Get.class)) {
                        Mapping mapping = new Mapping(clazz.getName(), method);
                        // Récupérer l'URL depuis l'annotation @Get
                        String key = method.getAnnotation(Get.class).value();  
                        if (urlMappings.containsKey(key)) {
                            throw new ServletException("La methode '"+urlMappings.get(key).getMethod().getName()+"' possede deja l'URL '"+key+"' comme annotation, donc elle ne peux pas etre assigner a la methode '"+mapping.getMethod().getName()+"'");
                        }                   
                        else{
                            urlMappings.put(key, mapping);
                        }
                    }
                }
            }
        }
    }

    protected Object invoke_Method(HttpServletRequest request, String className, Method method) throws IOException, NoSuchMethodException {
        Object returnValue = null;
        try {
            // Charger dynamiquement la classe du contrôleur
            Class<?> clazz = Class.forName(className);
            // Permettre d'accéder à la méthode même si elle est privée
            method.setAccessible(true);
    
            Parameter[] methodParams = method.getParameters();
            // Tableau pour stocker les arguments de la méthode
            Object[] args = new Object[methodParams.length];
    
            // Récupérer les noms des paramètres de la requête
            Enumeration<String> params = request.getParameterNames();
            // Map pour stocker les paramètres de la requête
            Map<String, String> paramMap = new HashMap<>();
    
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                paramMap.put(paramName, request.getParameter(paramName));
            }
            for (int i = 0; i < methodParams.length; i++) {
                if (methodParams[i].isAnnotationPresent(Param.class)) {
                    String paramName = methodParams[i].getAnnotation(Param.class).name();
                    String paramValue = paramMap.get(paramName);
                    args[i] = paramValue;
                }
                else{
                    args[i] = null;
                }
            }
            
            // Instancier la classe du contrôleur
            Object instance = clazz.getDeclaredConstructor().newInstance();
            // Invoquer la méthode avec les arguments
            returnValue = method.invoke(instance, args);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public void init() throws ServletException {
        // Initialisation du servlet, appelée lors du démarrage du serveur. Charge la liste des contrôleurs et leurs mappings URL
        super.init();
        try {
            String package_name = "controllerPackage"; 
            // Récupère le nom du package à partir du fichier de configuration du servlet
            String pack = getServletContext().getInitParameter(package_name);
            if (pack == null) {
                throw new ServletException("Le package \""+package_name+"\" n'est pas reconnu.");
            } else {
                getControllerList(pack);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Extrait l'URL relative
        String url = request.getRequestURI().substring(request.getContextPath().length());
        
        // Récupère le mapping correspondant à l'URL
        Mapping mapping = urlMappings.get(url);

        if (mapping != null) {
            
            try {
                // Invoque la méthode du contrôleur
                Object returnValue = invoke_Method(request, mapping.getClassName(), mapping.getMethod());

                if (returnValue instanceof String) {
                    // Écrit la réponse dans la sortie HTML
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<p>Contenue de la methode <strong>"+mapping.method_to_string()+"</strong> : "+(String) returnValue+"</p>");
                    }
                } else if (returnValue instanceof ModelView) {
                    ModelView modelView = (ModelView) returnValue;
                    String viewUrl = modelView.getUrl();
                    HashMap<String, Object> data = modelView.getData();

                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }

                    // Redirige vers une vue (JSP).
                    RequestDispatcher dispatcher = request.getRequestDispatcher(viewUrl);
                    dispatcher.forward(request, response);
                    
                } 
                else if (returnValue == null) {
                    throw new ServletException("La methode \""+mapping.method_to_string()+"\" retourne une valeur NULL");

                }
                else {
                    throw new ServletException("Le type de retour de l'objet \""+returnValue.getClass().getName()+"\" n'est pas pris en charge par le Framework");
                }

            } catch (NoSuchMethodException | IOException e) {
                throw new ServletException("Erreur lors de l'invocation de la methode \""+mapping.method_to_string()+"\"", null);
            }

        } else {
            throw new ServletException("Pas de methode Get associer a l'URL: \"" + url +"\"");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
