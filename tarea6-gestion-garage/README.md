# 🚗 Gestión de garaje

> 📚 Práctica 06 · **Programación** · 1.º DAW

Aplicación desarrollada en **Java** para la gestión de vehículos y las operaciones de un garaje. El proyecto se ejecuta por consola y utiliza diferentes conceptos de programación orientada a objetos.

## 📖 Descripción

El programa permite gestionar diferentes tipos de vehículos dentro de un garaje mediante un menú interactivo por consola.

La aplicación está organizada mediante diferentes clases, interfaces y paquetes para separar las responsabilidades relacionadas con los vehículos, la gestión del garaje y la interacción con el usuario.

## ✨ Funcionalidades

- 🚗 Gestión de diferentes tipos de vehículos
- 🅿️ Gestión de vehículos dentro del garaje
- 🔄 Entrada y salida de vehículos
- 📋 Consulta de vehículos
- 🔎 Búsqueda y gestión de información
- 🖥️ Menú interactivo por consola
- ✅ Validación de los datos introducidos

## 🎯 Conceptos trabajados

- ☕ Programación orientada a objetos (POO)
- 🧬 Herencia
- 🔌 Interfaces
- 🔒 Encapsulación
- 📦 Clases y objetos
- 🗂️ Organización mediante paquetes
- 📋 Gestión de colecciones
- 🔄 Estructuras de control
- ⌨️ Entrada de datos por consola
- 🧩 Métodos y modularización

## 🛠️ Tecnologías

- ☕ Java
- 🧑‍💻 Apache NetBeans
- 🌱 Git
- 🐙 GitHub

## 📂 Estructura del proyecto

    tarea6-gestion-garage/
    │
    ├── nbproject/
    │   ├── build-impl.xml
    │   ├── genfiles.properties
    │   ├── project.properties
    │   └── project.xml
    │
    ├── src/
    │   └── garaje/
    │       ├── Main.java
    │       │
    │       ├── gestion/
    │       │   └── Garaje.java
    │       │
    │       ├── interfaces/
    │       │   ├── Arrancable.java
    │       │   └── Movible.java
    │       │
    │       ├── ui/
    │       │   ├── Controlador.java
    │       │   └── Menu.java
    │       │
    │       ├── util/
    │       │   └── Util.java
    │       │
    │       └── vehiculos/
    │           ├── Bicicleta.java
    │           ├── Coche.java
    │           ├── Motocicleta.java
    │           └── Vehiculo.java
    │
    ├── build.xml
    ├── manifest.mf
    └── README.md

## 🚀 Cómo ejecutar el proyecto

El proyecto está preparado para ejecutarse como proyecto de **Apache NetBeans**.

### 1. Clonar el repositorio

    git clone https://github.com/gon-hernando/daw-programacion.git

### 2. Abrir el proyecto

Desde **NetBeans**:

1. Seleccionar **File → Open Project**.
2. Buscar la carpeta `tarea6-gestion-garage`.
3. Abrir el proyecto.

### 3. Ejecutar

Ejecutar el proyecto desde NetBeans.

La clase principal es:

    garaje.Main

Al iniciar la aplicación se mostrará el menú principal por consola.

## 🕹️ Uso

La aplicación funciona mediante un menú interactivo en consola.

El usuario selecciona las diferentes opciones disponibles introduciendo el número correspondiente y sigue las instrucciones mostradas por el programa.

## 📚 Objetivo

Esta práctica forma parte de la asignatura **Programación** del ciclo de **Desarrollo de Aplicaciones Web (DAW)** y permite trabajar conceptos fundamentales de la programación orientada a objetos, especialmente la **herencia** y el uso de **interfaces**, mediante la creación de diferentes tipos de vehículos y su gestión dentro de un garaje.

## 👨‍💻 Autor

**Gonzalo Hernando**

[![GitHub](https://img.shields.io/badge/GitHub-gon--hernando-181717?logo=github)](https://github.com/gon-hernando)

[![Portfolio](https://img.shields.io/badge/Portfolio-DAW-663CB7)](https://gon-hernando.github.io/daw-programacion/)