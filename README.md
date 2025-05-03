# Proyecto de Acortador de URLs

Este es un proyecto practico desarrollado en **Java** utilizando **Spring Boot** y **Maven**. El objetivo del proyecto es crear un servicio para acortar URLs, con funcionalidades para crear, consultar, actualizar, eliminar y redirigir URLs acortadas.
Basado en: https://roadmap.sh/projects/url-shortening-service

## Características

- Crear URLs acortadas.
- Consultar la URL original a partir de un código corto.
- Actualizar URLs acortadas.
- Eliminar URLs acortadas.
- Redirigir automáticamente a la URL original.
- Consultar estadísticas de las URLs acortadas.
- Endpoint de salud para verificar el estado del servicio.

## Endpoints principales

### URL Controller (`/v1/shorten`)
- **POST** `/v1/shorten`: Crear una URL acortada.
- **GET** `/v1/shorten/{shortCode}`: Obtener la URL original.
- **PUT** `/v1/shorten/{shortCode}`: Actualizar una URL acortada.
- **DELETE** `/v1/shorten/{shortCode}`: Eliminar una URL acortada.
- **GET** `/v1/shorten/{shortCode}/stats`: Consultar estadísticas de una URL acortada.
- **GET** `/v1/shorten/redirect/{shortCode}`: Redirigir a la URL original.

### Health Controller(Opcional)
- **GET** `/health`: Verificar el estado del servicio.

## Configuración de CORS(Opcional)

El archivo `CorsConfig.java` permite configurar los orígenes permitidos para las solicitudes. Actualmente, está configurado para aceptar solicitudes de cualquier origen (`*`).

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **MongoDB**
- **Maven**
- **Docker**

## Requisitos previos

- Tener instalado **Java 17** o superior.
- Tener instalado **Maven**.
- Tener instalado **Docker** (opcional, para ejecutar el contenedor y desplegar en Render).

## Configuración y ejecución

### Ejecución local

1. Clona este repositorio:
   ```bash
   git clone https://github.com/Daniel7759/UrlShortener.git
   cd UrlShortener
   mvn spring-boot:run
   ```