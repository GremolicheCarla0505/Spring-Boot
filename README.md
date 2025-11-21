#  Gestor de Tareas – Spring Boot (UTN)

## 📝 Descripción del proyecto
Este proyecto es un **gestor de tareas simple**, desarrollado como trabajo práctico para la materia de **Fundamentos de Spring Boot**.  
Implementa conceptos clave del framework:

- Inyección de dependencias
- Capas (model, repository, service)
- Perfiles (`dev` y `prod`)
- Beans condicionados por perfil (`@Profile`)
- Ejecución con `CommandLineRunner`
- Manejo de propiedades externas (`application.properties`)

Al ejecutarse, la aplicación muestra mensajes personalizados según el perfil activo, gestiona tareas en memoria y presenta estadísticas del sistema.

---

## ⚙️ Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Lombok**
- **Java Streams**
- **Profiles (`dev` / `prod`)**
- **CommandLineRunner**

---

## 🚀 Cómo clonar y ejecutar el proyecto

### 1. Clonar el repositorio
```bash
git clone <URL-del-repositorio>
cd tareas-springboot
### 2. Compilar y ejecutar
mvn spring-boot:run


o desde IntelliJ → botón Run.

La aplicación leerá automáticamente el perfil definido en:

src/main/resources/application.properties
##🔧 Cómo cambiar entre profiles (dev / prod)
###✔ Opción 1 (recomendada): Desde application.properties

Editar:

spring.profiles.active=dev


o:

spring.profiles.active=prod

###✔ Opción 2: Desde consola
mvn spring-boot:run -Dspring-boot.run.profiles=dev

###✔ Opción 3: Desde IntelliJ

Run → Edit Configurations… → Program arguments:

--spring.profiles.active=prod
##📸 Capturas de pantalla
▶️ Ejecución con perfil dev
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/ee47d19d-ed5b-4be7-8b5b-867faeb86524" />

▶️ Ejecución con perfil prod
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/f8906dc8-9701-4be4-88f9-b79e332ab83e" />

##Conclusiones personales

Durante este trabajo práctico aprendí a utilizar conceptos fundamentales de Spring Boot, tales como:

configuraciones externas mediante application.properties;

creación de servicios con distintos perfiles (@Profile);

inyección de dependencias por constructor;

uso de CommandLineRunner para ejecutar lógica al inicio;

separación clara por capas (modelo, repositorio, servicios).

Comprendí también cómo Spring gestiona los beans y cómo cambia el comportamiento según el entorno (dev o prod), algo fundamental en aplicaciones reales.

##Autor

Carla Fernández Gremoliche 
Legajo: 50894
