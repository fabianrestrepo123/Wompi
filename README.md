#  Automatización de Pruebas API – Wompi

##  Descripción General

Este proyecto implementa pruebas automatizadas de **integración vía API** para la plataforma **Wompi**, validando el flujo de creación y consulta de transacciones bancarias.

La solución está diseñada bajo el enfoque **BDD (Behavior Driven Development)** utilizando **Serenity BDD** y el **patrón Screenplay**, priorizando legibilidad, mantenibilidad y escalabilidad.

---

##  Arquitectura

La arquitectura del proyecto se basa en una **separación clara de responsabilidades**, alineada al patrón Screenplay. Cada capa cumple un rol específico dentro del flujo de automatización:

* **Features**: Definen el comportamiento esperado en lenguaje de negocio (Gherkin).
* **Step Definitions**: Orquestan la ejecución entre Gherkin y Screenplay.
* **Tasks**: Representan las acciones que realiza el actor sobre el sistema.
* **Questions**: Validan el estado y las respuestas del sistema.
* **Models**: Encapsulan las estructuras de datos.
* **Utils / Config**: Manejan configuración, llaves, firmas y utilidades técnicas.

Esta arquitectura permite extender fácilmente nuevos flujos o métodos de pago sin afectar la base existente.

---

##  Diagrama Conceptual

```
Feature (Gherkin)
   ↓
Step Definitions
   ↓
Actor
   ↓
Tasks (Acciones)
   ↓
API Wompi
   ↓
Questions (Validaciones)
```

---

##  Patrón de Diseño

### Screenplay Pattern

Se eligió el **patrón Screenplay** por las siguientes razones:

* Modela las pruebas desde la perspectiva del usuario/actor
* Evita pruebas monolíticas y frágiles
* Facilita la reutilización de acciones y validaciones
* Mejora la legibilidad y el mantenimiento
* Escalable para múltiples flujos y escenarios

En este patrón:

* **Actor**: Representa quién interactúa con el sistema
* **Tasks**: Qué hace el actor
* **Questions**: Qué valida el actor

---

##  Estructura del Proyecto

```
api-wompi
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.wompi.api
│   │           ├── abilities
│   │           ├── constants
│   │           ├── interactions
│   │           ├── models
│   │           ├── questions
│   │           ├── task
│   │           └── utils
│   │
│   └── test
│       ├── java
│       │   └── com.wompi.api
│       │       ├── runners
│       │       ├── setups
│       │       └── stepdefinitions
│       └── resources
│           └── features
│               └── transaccion.feature
│
├── serenity.conf
├── build.gradle
└── README.md
```

---

##  Tecnologías / Herramientas

* **Java** (LTS)
* **Gradle**
* **Serenity BDD**
* **Serenity Screenplay**
* **Serenity Rest / RestAssured**
* **Cucumber (Gherkin)**
* **JUnit**
* **IntelliJ IDEA**
* **Postman** (validación manual previa)

---

##  Pre-requisitos

* Java JDK 11 o superior
* Gradle instalado o wrapper del proyecto
* Acceso a credenciales de Wompi (sandbox)
* Conexión a internet

---

##  Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/fabianrestrepo123/Wompi.git
```

2. Acceder al proyecto:

```bash
cd api-wompi
```

3. Configurar variables sensibles en `serenity.conf` o como variables de entorno:

* `wompi.publicKey`
* `wompi.privateKey`
* `wompi.integrityKey`

---

##  Ejecución

Ejecutar las pruebas con Gradle:

```bash
gradlew clean test
```

O ejecutar directamente el runner desde el IDE.

---

##  Informe

Serenity genera automáticamente un **reporte HTML detallado** al finalizar la ejecución.

Ruta del informe:

```
/target/site/serenity/index.html
```

El reporte incluye:

* Escenarios ejecutados
* Evidencias de requests y responses
* Resultados de validaciones
* Trazabilidad por actor

---

##  Ambiente

* **Ambiente**: Sandbox
* **Base URL**:

```
https://api-sandbox.co.uat.wompi.dev
```

El ambiente es configurable desde `serenity.conf`.

---

##  Autores

* **Fabian Restrepo**
  QA Automation Engineer
  Especializado en pruebas funcionales, API y BDD


