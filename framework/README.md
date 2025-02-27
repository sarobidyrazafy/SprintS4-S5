## Les classes Java

Contenus dans le dossier src, voici les classes et leur fonctionnalit\u00E9s:
- `FrontController`: cette classe modifier les fonctions suivantes:
    -`getClassesInSpecificPackage`: prend les classes dans le chemin specifier par init param du fichier xml
    -`scanner`: appel la fonction pr\u00E9c\u00E9dente et change la liste des controller ainsi que le boolean de v\u00E9rification
- `AnnotationController`: l'anotation des controllers
- `TestController`: une classe qui \u00E0 l'annotation des controllers

## Les scripts

Voici les scripts(.bat):

-`compilateur`: ceci compile tout les fichiers java dans src et les met dans le repertoire bin
-`deployement`: ceci deploi le projet framework vers le r\u00E9pertoire webapps de tomcat 