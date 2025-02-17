@echo off
setlocal

rem Déclaration des variables
set projet=sprint_suite
set temp=.\..\temp
set conf=.\..\..\test\conf
set lib=.\..\lib
set src=.\..\src
set bin=.\..\bin
set package=mg\itu
set web=.\..\..\test\views
set destination=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps

rem Vérifier si le dossier temp existe
if exist "%temp%\" (
    rd /S /Q "%temp%"
)

rem Création d'un dossier temp avec les contenu de base si le dossier temp n'existe pas
mkdir "%temp%"
mkdir "%temp%\views"
mkdir "%temp%\WEB-INF"
mkdir "%temp%\WEB-INF\lib"
mkdir "%temp%\WEB-INF\classes"  

rem Copie des élements indispensables pour tomcat vers temp
rem copy /Y  ".\index.jsp" "%temp%"
xcopy /E /I /Y "%web%\" "%temp%\views"
xcopy /E /I /Y "%conf%\" "%temp%\WEB-INF\"

rem Compilation des codes java vers le dossier bin
call compilateur.bat

rem création de fichier .jar
jar cvf "%lib%\%projet%.jar" -C "%bin%" . 

rem Copie des élements nécessaires vers classes de tomcat
xcopy /E /I /Y "%lib%\" "%temp%\WEB-INF\lib"
xcopy /E /I /Y "%bin%\" "%temp%\WEB-INF\classes"

rem Déplacement du répertoire actuel vers temp
cd /D "%temp%"

rem Compresser dans un fichier war
jar -cvf "..\%projet%".war *

rem Déplacement du répertoire actuel vers le projet
cd /D ..\

rem Copie des élements indispensables pour tomcat vers temp
copy /Y  ".\%projet%.war" "%destination%"

endlocal
