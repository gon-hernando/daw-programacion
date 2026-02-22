# Aplicación de Gestión de Cuenta Bancaria (Java)

Aplicación de consola desarrollada en Java que permite gestionar una cuenta bancaria.  
El programa trabaja con una única cuenta creada al inicio y aplica principios de Programación Orientada a Objetos (POO), encapsulación, validación de datos y control de excepciones.

## 📌 Objetivo

Desarrollar una aplicación que:

- Solicite los datos de una cuenta bancaria al iniciar el programa.
- Valide correctamente los datos introducidos.
- Aplique buenas prácticas de diseño orientado a objetos.
- Controle posibles errores mediante manejo de excepciones.

---

## 🧾 Requisitos Funcionales

Al iniciar el programa se solicitarán los siguientes datos:

### 1️⃣ NIF o NIE del titular
- Debe ser válido.
- Si no lo es, se volverá a solicitar hasta que sea correcto.
- Se valida:
  - Formato
  - Letra de control

### 2️⃣ Nombre del titular
- Máximo 36 caracteres.
- No puede estar vacío.

### 3️⃣ Código Cuenta Cliente (CCC)
- Debe contener 20 dígitos:
  - 4 dígitos → entidad
  - 4 dígitos → oficina
  - 2 dígitos → dígitos de control
  - 10 dígitos → número de cuenta
- Se valida:
  - Formato mediante expresión regular
  - Corrección de los dígitos de control

---

## 🧠 Conceptos aplicados

- Programación Orientada a Objetos
- Encapsulación
- Validación de datos
- Uso de expresiones regulares
- Control de excepciones
- Separación de responsabilidades

---
