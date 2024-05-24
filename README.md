## Configuration

- Déclarez le package des contrôlleurs dans le web.xml
- Déclarez la classe FrontControlleur dans le web.xml

## Utilisation

- Créez des classes contrôlleurs avec des annotations AnnotationController et GET
- Et ajoutez votre logique de l'utilisation de l'URL
- Déployez l'application avec deployment.bat et compilateur.bat

## Contenu du dossier src: les classes Java 

- `FrontController`: elle modifie les fonctions suivantes
    -`getClassesInSpecificPackage`: prend les classes dans le chemin specifier par init param du fichier xml
    -`scanner`: appel la fonction précédente et change la liste des controller ainsi que le boolean de vérification
    - `processRequest`: traite les requêtes HTTP, trouve le Mapping associé à une URL et invoque la méthode correspondante
- `AnnotationCcontroller`: pour marquer les classes contrôlleurs
- `TestController`: une classe annotée comme contrôleur, incluant une méthode pour gérer l'URL `/test`

## Les Scripts

Voici les scripts(.bat):
    - `compilateur` : compile tous les fichiers Java dans src et les met dans le répertoire bin.
    - ``deployement`` : déploie le projet framework vers le répertoire webapps de Tomcat.
