@echo off
setlocal EnableDelayedExpansion

rem Chemin au répertoire des sources et destination des fichiers compilés
set "sourceDirectory=.\..\src"
set "destinationDirectory=.\..\bin"

rem Chemin vers le répertoire contenant les bibliothèques nécessaires
set "libDirectory=.\..\lib"  

rem Initialisation des fichiers Java à compiler
set "javaFiles="

rem Récupération de tous les fichiers Java dans les sous-dossiers de %sourceDirectory%
for /r "%sourceDirectory%" %%G in (*.java) do (
    rem Extraire la structure des packages à partir du chemin complet du fichier source
    set "javaFile=%%~fG"
    set "packagePath=!javaFile:%sourceDirectory%=!"
    set "packagePath=!packagePath:~0,-\%%~nG%%~xG!"

    rem répertoires de sortie 
    if not exist "%destinationDirectory%!packagePath!" (
        mkdir "%destinationDirectory%!packagePath!" >nul
    )

    rem Ajout du fichier Java à la liste des fichiers à compiler
    set "javaFiles=!javaFiles! "%%G""
)
rem Construction du chemin de classe pour toutes les bibliothèques dans le dossier "lib"
set "classpath="
for %%I in ("%libDirectory%\*.jar") do (
    set "classpath=!classpath!;"%%I""
)

rem Compilation de tous les fichiers Java en une seule commande avec les bibliothèques nécessaires
javac -cp "%classpath%" -d "%destinationDirectory%" !javaFiles!

endlocal
