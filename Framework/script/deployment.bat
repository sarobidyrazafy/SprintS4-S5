@echo off
setlocal

rem D\u00E9claration des variables
set projet=SprintRehetra
set temp=.\..\temp
set conf=.\..\..\test\conf
set config=.\..\config.properties
set lib=.\..\lib
set src=.\..\src
set bin=.\..\bin
set package=mg\itu
set web=.\..\..\test\views
set destination=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps

rem V\u00E9rifier si le dossier temp existe
if exist "%temp%\" (
    rd /S /Q "%temp%"
)

rem Cr\u00E9ation d'un dossier temp avec les contenu de base si le dossier temp n'existe pas
mkdir "%temp%"
mkdir "%temp%\views"
mkdir "%temp%\WEB-INF"
mkdir "%temp%\WEB-INF\lib"
mkdir "%temp%\WEB-INF\classes"  

rem Copie des \u00E9lements indispensables pour tomcat vers temp
copy /Y  "%config%" "%temp%\WEB-INF\"
xcopy /E /I /Y "%web%\" "%temp%\views"
xcopy /E /I /Y "%conf%\" "%temp%\WEB-INF\"

rem Compilation des codes java vers le dossier bin
call compilateur.bat

rem cr\u00E9ation de fichier .jar
jar cvf "%lib%\%projet%.jar" -C "%bin%" . 

rem Copie des \u00E9lements n\u00E9cessaires vers classes de tomcat
xcopy /E /I /Y "%lib%\" "%temp%\WEB-INF\lib"
xcopy /E /I /Y "%bin%\" "%temp%\WEB-INF\classes"

rem D\u00E9placement du r\u00E9pertoire actuel vers temp
cd /D "%temp%"

rem Compresser dans un fichier war
jar -cvf "..\%projet%".war *

rem D\u00E9placement du r\u00E9pertoire actuel vers le projet
cd /D ..\

rem Copie des \u00E9lements indispensables pour tomcat vers temp
copy /Y  ".\%projet%.war" "%destination%"

endlocal
