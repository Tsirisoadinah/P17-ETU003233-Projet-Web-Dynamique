@echo off
REM Définition des variables
SET APP_NAME=ETU003233
SET SRC_DIR=src\main\java
SET WEB_DIR=src\main\webapp
SET BUILD_DIR=build
SET LIB_DIR=lib
SET TOMCAT_WEBAPPS="C:\Program Files\Apache Software Foundation\apache-tomcat-10.1.28\webapps"
SET SERVLET_API_JAR=%LIB_DIR%\servlet-api.jar

REM Nettoyage et création du répertoire temporaire
rmdir /S /Q %BUILD_DIR% 2>nul
mkdir "%BUILD_DIR%\WEB-INF\classes" 2>nul

REM Compilation des fichiers Java
echo === Compilation des fichiers Java ===
del sources.txt 2>nul

REM Nouvelle méthode robuste pour lister les fichiers Java
dir /s /b "%SRC_DIR%\*.java" > sources.txt

REM Vérification qu'on a bien trouvé des fichiers
if %ERRORLEVEL% neq 0 (
    echo ❌ Aucun fichier Java trouvé dans %SRC_DIR%
    exit /B 1
)

REM Compilation avec gestion des chemins complexes
javac -cp "%SERVLET_API_JAR%" -d "%BUILD_DIR%\WEB-INF\classes" @sources.txt
if ERRORLEVEL 1 (
    echo ❌ ERREUR : Compilation échouée !
    type sources.txt
    del sources.txt 2>nul
    exit /B 1
)
del sources.txt 2>nul
echo ✅ Compilation réussie !

REM Copier les fichiers web
echo === Copie des fichiers web ===
xcopy "%WEB_DIR%\*" "%BUILD_DIR%\" /E /I /Y

REM Création du fichier .war
echo === Création du fichier .war ===
pushd "%BUILD_DIR%"
jar -cvf "%APP_NAME%.war" *
popd

REM Déploiement dans Tomcat
echo === Déploiement dans Tomcat ===
copy "%BUILD_DIR%\%APP_NAME%.war" %TOMCAT_WEBAPPS%

echo ✅ Déploiement terminé avec succès !