# Practica Web Frontend+Backend

Aplicación web para administrar categorías y productos mediante operaciones CRUD. El proyecto está dividido en un frontend desarrollado con Angular y un backend construido con Spring Boot, conectado a una base de datos PostgreSQL.

El sistema permite registrar, consultar, actualizar y eliminar categorías y productos. Cada producto se encuentra relacionado con una categoría registrada.

## Enlaces del proyecto

- **Frontend desplegado en Netlify:**  
  https://mini-inventario-frontend.netlify.app/

- **Backend desplegado en Render:**  
  https://mini-inventario-backend-rf8b.onrender.com

- **Documentación Swagger:**  
  https://mini-inventario-backend-rf8b.onrender.com/documentacion/swagger-ui.html

- **Repositorio de GitHub:**  
  https://github.com/gio1313130/Practica_Web_HZIG_Front-Back

> **Nota:** El backend utiliza el plan gratuito de Render, por lo que puede entrar en reposo después de un periodo de inactividad. La primera consulta puede tardar mientras el servicio vuelve a iniciar.

## Funcionalidades

### Categorías

- Registrar una categoría.
- Consultar todas las categorías.
- Consultar una categoría por su identificador.
- Actualizar una categoría.
- Eliminar una categoría.

### Productos

- Registrar un producto.
- Consultar todos los productos.
- Consultar un producto por su identificador.
- Actualizar un producto.
- Eliminar un producto.
- Asociar cada producto con una categoría.
- Mostrar el precio, la existencia y la categoría relacionada.

## Tecnologías utilizadas

### Backend

- Java 25
- Spring Boot 4
- Spring Data JPA
- Hibernate
- PostgreSQL
- Swagger / OpenAPI
- Maven
- Docker

### Frontend

- Angular 21
- TypeScript
- Bootstrap
- HTML
- CSS
- Angular HttpClient

### Servicios de despliegue

- Render para el backend.
- Netlify para el frontend.
- PostgreSQL como base de datos.
- GitHub para el control de versiones.

## Estructura del proyecto

```text
PracticaWeb(Front+Back)
│
├── Backend_SpringBoot
│   ├── src
│   │   └── main
│   │       ├── java
│   │       └── resources
│   ├── Dockerfile
│   └── pom.xml
│
├── Frontend_Angular
│   ├── src
│   │   └── app
│   ├── public
│   ├── angular.json
│   └── package.json
│
├── .gitignore
└── README.md
```

## Arquitectura general

El frontend desarrollado con Angular realiza peticiones HTTP al backend mediante una API REST. El backend procesa las operaciones y guarda la información en PostgreSQL.

```text
Angular
   ↓
API REST
   ↓
Spring Boot
   ↓
PostgreSQL
```

## Endpoints principales

### Categorías

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/v1/categorias/categoria` | Consultar todas las categorías |
| GET | `/v1/categorias/categoria/{id}` | Consultar una categoría |
| POST | `/v1/categorias/categoria` | Registrar una categoría |
| PUT | `/v1/categorias/categoria/{id}` | Actualizar una categoría |
| DELETE | `/v1/categorias/categoria/{id}` | Eliminar una categoría |

### Productos

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/v1/productos` | Consultar todos los productos |
| GET | `/v1/productos/{id}` | Consultar un producto |
| POST | `/v1/productos` | Registrar un producto |
| PUT | `/v1/productos/{id}` | Actualizar un producto |
| DELETE | `/v1/productos/{id}` | Eliminar un producto |

## Requisitos para la ejecución local

Antes de iniciar el proyecto es necesario tener instalado:

- Java 25.
- Maven.
- Node.js.
- npm.
- PostgreSQL.
- Git.

## Variables de entorno del backend

El backend utiliza variables de entorno para evitar colocar las credenciales de la base de datos directamente dentro del código.

```text
DATABASE_USERNAME
DATABASE_PASSWORD
DATABASE_URL
CORS_LIGA
```

Ejemplo de configuración:

```properties
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.url=${DATABASE_URL}

app.cors.allowed-origins=${CORS_LIGA:http://localhost:4200}
```

La URL de PostgreSQL debe utilizar el formato JDBC:

```text
jdbc:postgresql://servidor:puerto/base_de_datos
```

Las contraseñas y credenciales reales no deben subirse al repositorio.

## Ejecución del backend

Ingresar a la carpeta del backend:

```powershell
cd Backend_SpringBoot
```

Ejecutar con Maven Wrapper en Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

También puede ejecutarse con Maven instalado:

```powershell
mvn spring-boot:run
```

El backend se inicia localmente en:

```text
http://localhost:8085
```

Swagger estará disponible en:

```text
http://localhost:8085/documentacion/swagger-ui.html
```

## Ejecución del frontend

Ingresar a la carpeta del frontend:

```powershell
cd Frontend_Angular
```

Instalar las dependencias:

```powershell
npm install
```

Iniciar Angular:

```powershell
npm start
```

También puede utilizarse:

```powershell
ng serve
```

El frontend se inicia normalmente en:

```text
http://localhost:4200
```

## Configuración de la URL del backend

Para el entorno local se utiliza:

```typescript
export const API_URL = 'http://localhost:8085';
```

Para producción se utiliza:

```typescript
export const API_URL =
  'https://mini-inventario-backend-rf8b.onrender.com';
```

Angular reemplaza automáticamente la configuración local por la configuración de producción durante la compilación.

## Compilación del frontend

Para generar la versión de producción:

```powershell
npm run build
```

El resultado se genera dentro de:

```text
dist/Frontend_Angular/browser
```

Para el despliegue estático en Netlify se crea el archivo `index.html` a partir de la salida CSR:

```bash
npm run build && cp dist/Frontend_Angular/browser/index.csr.html dist/Frontend_Angular/browser/index.html
```

## Configuración de rutas en Netlify

Dentro de la carpeta `public` se utiliza el archivo `_redirects`:

```text
/* /index.html 200
```

Esta configuración permite actualizar o abrir directamente rutas como:

```text
/categorias
/productos
/productos/nuevo
```

sin recibir un error 404.

## Configuración de CORS

El backend permite peticiones desde el frontend local y desde la aplicación desplegada en Netlify.

Ejemplo:

```text
http://localhost:4200,https://mini-inventario-frontend.netlify.app
```

La configuración se obtiene mediante la variable de entorno:

```text
CORS_LIGA
```

## Despliegue del backend

El backend se despliega en Render utilizando el archivo `Dockerfile` incluido dentro del proyecto.

Configuración principal:

```text
Root Directory: Backend_SpringBoot
Dockerfile Path: Dockerfile
Docker Build Context: .
```

El contenedor compila el proyecto mediante Maven y ejecuta el archivo JAR generado.

## Despliegue del frontend

El frontend se encuentra desplegado en Netlify.

Configuración utilizada:

```text
Base directory:
Frontend_Angular
```

```text
Build command:
npm run build && cp dist/Frontend_Angular/browser/index.csr.html dist/Frontend_Angular/browser/index.html
```

```text
Publish directory:
dist/Frontend_Angular/browser
```

## Prueba rápida del sistema

Para comprobar el funcionamiento del proyecto desplegado:

1. Abrir un endpoint del backend para iniciar el servicio de Render:

   ```text
   https://mini-inventario-backend-rf8b.onrender.com/v1/categorias/categoria
   ```

2. Esperar a que aparezca la respuesta en formato JSON.

3. Abrir el frontend:

   ```text
   https://mini-inventario-frontend.netlify.app/
   ```

4. Probar las operaciones de categorías y productos.

5. Verificar que los cambios aparezcan en las tablas correspondientes.

## Consideraciones

- Las fechas de creación se asignan automáticamente desde el backend.
- Los productos deben estar asociados con una categoría existente.
- Las credenciales de la base de datos no se almacenan dentro del repositorio.
- El backend puede tardar en responder después de un periodo de inactividad.
- La base de datos conserva la información aunque el backend se reinicie.
- El frontend y el backend se encuentran dentro del mismo repositorio, pero funcionan como proyectos independientes.
