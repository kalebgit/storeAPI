<div align="center">

# storeAPI

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL_16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

API REST de una tienda de la fac de Ciencias  con arquitectura de microservicios. Permite gestionar productos, clientes, carrito de compras y facturación.

</div>

---

## API en producción

> La API está desplegada en un VPS, hice artesanalmente el despliegue y configuracion del server para alojar mi proeycto de microservicios, usé certbot de python para tener una conexión segura i.e. HTTPS y configuré un dominio personalizado.

| Recurso | URL |
|---|---|
| **Base URL** | `https://storeapi.kaljimenez.com` |
| **Swagger UI** | `https://storeapi.kaljimenez.com/swagger-ui.html` |
| **Spring Boot Admin** | `https://admin-store.kaljimenez.com` |

---

## Cómo usar la API
Como si se estuviera haciendo dede Postman u otra herramienta similar

### 1. Registrarse

```http
POST https://storeapi.kaljimenez.com/auth/register
Content-Type: application/json

{
  "name": "Juan Pérez",
  "email": "juan@ciencias.unam.mx",
  "password": "mi_password"
}
```

### 2. Obtener token

```http
POST https://storeapi.kaljimenez.com/auth/login
Content-Type: application/json

{
  "email": "juan@ciencias.unam.mx",
  "password": "mi_password"
}
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJSUzI1NiJ9..."
}
```

### 3. Autorizar

Todos los demás endpoints requieren el token en el header:

```
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

<details>
<summary><strong>Autorizar en Postman</strong></summary>

1. En la colección, abrir **Edit → Authorization**
2. Seleccionar tipo **Bearer Token**
3. Pega el token
4. Todas las peticiones de la colección lo usarán automáticamente

</details>

<details>
<summary><strong>Autorizar en Swagger UI</strong></summary>

1. Abre `https://storeapi.kaljimenez.com/swagger-ui.html`
2. Selecciona el servicio en el dropdown **"Select a definition"**
3. Haz clic en el botón **Authorize** (arriba a la derecha)
4. Pega únicamente el token, sin el prefijo `Bearer`
5. Haz clic en **Authorize** y luego **Close**
6. Todos los endpoints del servicio seleccionado usarán el token automáticamente

> **Nota:** debes autorizar en cada servicio del dropdown por separado,  de cada servicio, es simplemente que Swagger UI maneja el token por pestaña/definición. Cuando cambias el dropdown a otro servicio, la UI carga una nueva spec y el token que pegaste se resetea.

</details>

> **Tip:** El cliente se identifica automáticamente por su token. No necesitas enviar el `customerId` en el body del carrito — el gateway lo extrae del JWT y lo inyecta internamente.

---

## Endpoints

Los endpoints marcados con <big>*</big> requieren token JWT.

### Auth
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `POST` | `/auth/register` | Registro de usuario | — |
| `POST` | `/auth/login` | Login, devuelve JWT | — |

### Customers
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/customer/{id}` | Obtener cliente por ID | <big>*</big> |
| `GET` | `/customer/me` | Perfil del cliente autenticado | <big>*</big> |

### Products
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/product` | Listar productos | <big>*</big> |
| `GET` | `/product/{id}` | Obtener producto por ID | <big>*</big> |
| `POST` | `/product` | Crear producto | <big>*</big> |
| `PUT` | `/product/{id}` | Actualizar producto | <big>*</big> |
| `PUT` | `/product/{id}/stock` | Actualizar stock | <big>*</big> |
| `PATCH` | `/product/{id}/enable` | Activar producto | <big>*</big> |
| `PATCH` | `/product/{id}/disable` | Desactivar producto | <big>*</big> |

### Categories
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/category` | Listar todas las categorías | <big>*</big> |
| `GET` | `/category/active` | Listar categorías activas | <big>*</big> |
| `POST` | `/category` | Crear categoría | <big>*</big> |
| `PUT` | `/category/{id}` | Actualizar categoría | <big>*</big> |
| `PATCH` | `/category/{id}/enable` | Activar categoría | <big>*</big> |
| `PATCH` | `/category/{id}/disable` | Desactivar categoría | <big>*</big> |

### Carrito de compras
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/cart-item` | Ver carrito del cliente autenticado | <big>*</big> |
| `POST` | `/cart-item` | Agregar producto al carrito | <big>*</big> |
| `DELETE` | `/cart-item/{id}` | Eliminar un artículo del carrito | <big>*</big> |
| `DELETE` | `/cart-item` | Vaciar el carrito completo | <big>*</big> |

### Facturación
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `POST` | `/invoice` | Finalizar compra y generar factura | <big>*</big> |
| `POST` | `/invoice/coupon` | Crear cupón de descuento | <big>*</big> |

> **Tip:** El body del `POST /invoice` es completamente opcional. Puedes incluir dirección de envío, método de pago y/o cupón:
> ```json
> {
>   "street": "Av. Universidad 3000",
>   "city": "Ciudad de México",
>   "state": "CDMX",
>   "zipCode": "04510",
>   "paymentMethod": "CARD",
>   "cardLastFour": "1234",
>   "couponCode": "DESCUENTO10"
> }
> ```

---

## Microservicios

| Servicio | Puerto | Descripción |
|---|---|---|
| `gateway-service` | 8080 | Punto de entrada único. Enruta peticiones y valida JWT |
| `auth-service` | 8085 | Registro, login y emisión de tokens JWT |
| `customer-service` | 8081 | Gestión de clientes |
| `product-service` | 8082 | Gestión de productos e inventario |
| `category-service` | 8084 | Gestión de categorías |
| `invoice-service` | 8083 | Carrito de compras y facturación |
| `config-service` | 8888 | Configuración centralizada (Spring Cloud Config) |
| `registry-service` | 8761 | Registro y descubrimiento de servicios (Eureka) |
| `admin-service` | 9090 | Panel de administración (Spring Boot Admin) |

---

## Correr localmente con Docker

### Requisitos
- Docker y Docker Compose instalados
- Par de claves RSA para firmar JWT

### 1. Generar claves RSA

```bash
# Clave privada
openssl genrsa -out private_key.pem 2048

# Clave pública
openssl rsa -in private_key.pem -pubout -out public_key.pem

# Exportar como variables de entorno
export JWT_PRIVATE_KEY=$(base64 -i private_key.pem)
export JWT_PUBLIC_KEY=$(base64 -i public_key.pem)
```

### 2. Levantar los servicios

```bash
docker compose up --build
```

Los servicios tienen healthchecks y arrancan en el orden correcto automáticamente.  
El tiempo de inicio completo es aproximadamente **2-3 minutos**, aunque diga que ya todos estan healthy, hay que esperar unos segundos mas para que swagger pueda desplegar correctamente toda la documentación.

### 3. Verificar que todo está levantado

| URL | Descripción |
|---|---|
| `http://localhost:8761` | Eureka — todos los servicios deben aparecer como `UP` |
| `http://localhost:9090` | Spring Boot Admin |
| `http://localhost:8080` | Gateway (punto de entrada de la API) |
| `http://localhost:8080/swagger-ui.html` | Swagger UI unificado |

Hecho por: kalebgit o kal
