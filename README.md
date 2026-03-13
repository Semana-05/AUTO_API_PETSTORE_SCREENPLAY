# AUTO_API_PETSTORE_SCREENPLAY

Proyecto de automatización de API con **Serenity BDD + Cucumber + Screenplay + RestAssured**, ejecutado con **Gradle**.

## 1) Prerrequisitos

- Java 17 instalado (`java -version`)
- Acceso a internet (la suite consume Petstore público)
- Linux/macOS/Windows con permisos para ejecutar `gradlew`

> URL base configurada por defecto:
>
> `https://petstore.swagger.io/v2`
>
> Definida en:
> - `src/test/java/com/petstore/users/utils/ApiConstants.java`
> - `src/test/resources/serenity.conf`

## 2) Ejecutar pruebas (Gradle)

Desde la carpeta del proyecto:

```bash
cd AUTO_API_PETSTORE_SCREENPLAY
chmod +x gradlew
./gradlew clean test aggregate
```

El proyecto está configurado para generar reportes automáticamente al final de `test`.


## 3) Endpoints cubiertos y body de cada operación

### 3.1 Crear usuario

- **Método:** `POST`
- **Endpoint:** `/user`
- **URL final:** `https://petstore.swagger.io/v2/user`
- **Body (JSON):**

```json
{
  "id": 0,
  "username": "matias_api_create_01",
  "firstName": "Matias",
  "lastName": "Nunez",
  "email": "matias.api.create01@mail.com",
  "password": "pass1234",
  "phone": "3001112233",
  "userStatus": 1
}
```

### 3.2 Consultar usuario

- **Método:** `GET`
- **Endpoint:** `/user/{username}`
- **URL ejemplo:** `https://petstore.swagger.io/v2/user/matias_api_get_01`
- **Body:** No aplica.

### 3.3 Actualizar usuario

- **Método:** `PUT`
- **Endpoint:** `/user/{username}`
- **URL ejemplo:** `https://petstore.swagger.io/v2/user/matias_api_update_01`
- **Body (JSON):**

```json
{
  "id": 12345,
  "username": "matias_api_update_01",
  "firstName": "MatiasQA",
  "lastName": "NunezQA",
  "email": "matias.api.update01.qa@mail.com",
  "password": "pass5678",
  "phone": "3009998877",
  "userStatus": 1
}
```

> Nota: En la automatización, el `id` se obtiene primero consultando el usuario (`GET /user/{username}`) y luego se reutiliza en el `PUT`.

### 3.4 Eliminar usuario

- **Método:** `DELETE`
- **Endpoint:** `/user/{username}`
- **URL ejemplo:** `https://petstore.swagger.io/v2/user/matias_api_delete_01`
- **Body:** No aplica.

## 4) Escenarios automatizados

Feature: `src/test/resources/features/user_crud.feature`

- `@CreateUser`: crear usuario
- `@GetUser`: consultar usuario por `username`
- `@UpdateUser`: actualizar datos del usuario
- `@DeleteUser`: eliminar usuario

## 5) Ver reportes

- Reporte principal Serenity:
  - `target/site/serenity/index.html`
- Resumen single-page:
  - `target/site/serenity/serenity-summary.html`
- Reporte de pruebas Gradle:
  - `build/reports/tests/test/index.html`
  prod {
    webdriver.base.url = "https://duckduckgo.com/prod"
  }