# CanDoIT

## Levantar el proyecto

### 1) Cloná el proyecto: git clone https://github.com/AibarHernanEzequiel/CanDoIT.git
### 2) Instalá IntelliJ IDEA Ultimate(Es el IDE utilizado) o Eclipse
### 3) Descargar e Instala apache tomcat 8.0.5. Url: https://tomcat.apache.org/download-80.cgi (descargar versión core).
### 4) Configurá el servior en el caso de intellij:
    ## 4.0) Click derecho en el proyecto, luego clickear en 'Open Module Settings'
    ## 4.1) En "Artefacts" dar click en el + y agregá uno del tipo Web Application: Exploded - FromModules, si es que no se agrega automaticamente
    ## 4.2) Hace click en Add Configuration para instalar Tomcat y seleccioná en el + "Tomcat Server - Local"
    ## 4.3) Hace click en Configure y seleccioná la carpeta donde tengas Tomcat descargado
    ## 4.4) En la parte de deployments - agregá el Artefact que se creo previamente, en el caso de tener el IDE en la Version Community instalar pluggin               'Smart Tomcat'
### 5) Ir a File -> preferences (esto puede ser distinto según el sistema operativo) y seleccioná la version que tengas de jdk (en este caso el 11)
### 6) En services corré el TomCat.
### 7) Configurar MySQL
    ## 7.0) Instalar MySQL server
    ## 7.1) Crea la base de datos db_clima
    ## 7.2) Dentro del proyecto en la carpeta 'src/main/resources/' hay un archivo 'hibernateContext.xml', donde esta la configuracion, solamente deberias               setear tu usuario y contraseña.
    
