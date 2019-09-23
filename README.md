
# Aplicación de test contacts para cyxtera

### Inicio aplicación

Para iniciar la aplicación construya el proyecto usando el comando:

`mvn spring-boot:run`

### Cliente de servicios web y documentación

La documentación de los servicios web y clientes usando swagger-ui se encuentra en la URL [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


### Interfaz web con historico de ejecución

Para ver el historico ingrese a [http://localhost:8080/index.html](http://localhost:8080/index.html)

### Notas

1. El histórico se almacena en una base de datos embebida que almacena la información en memoria, una vez que se reinicie la aplicación se pederan
   todos los datos.
1. El código no incluye comentarios javadoc para procurar que todo el código de las clases se puedan leer en pantalla sin scroll.
1. No se incluyen pruebas unitarias para clase de configuración e inicio de la aplicación.