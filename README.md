<div align="center">

# storeAPI

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL_16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

API REST de e-commerce con arquitectura de microservicios. Permite gestionar productos, clientes, carrito de compras y facturación.

</div>

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

## Endpoints

Todas las peticiones pasan por el **gateway** (`localhost:8080` local / VPS en producción).  
Los endpoints marcados con 🔒 requieren token JWT en el header `Authorization: Bearer <token>`.

### Auth
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `POST` | `/auth/register` | Registro de usuario | — |
| `POST` | `/auth/login` | Login, devuelve JWT | — |

### Customers
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/customer` | Listar clientes | 🔒 |
| `GET` | `/customer/{id}` | Obtener cliente por ID | 🔒 |
| `POST` | `/customer` | Crear cliente | 🔒 |
| `PUT` | `/customer/{id}` | Actualizar cliente | 🔒 |
| `DELETE` | `/customer/{id}` | Eliminar cliente | 🔒 |

### Products
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/product` | Listar productos | 🔒 |
| `GET` | `/product/{id}` | Obtener producto por ID | 🔒 |
| `POST` | `/product` | Crear producto | 🔒 |
| `PUT` | `/product/{id}` | Actualizar producto | 🔒 |
| `PUT` | `/product/{id}/stock` | Actualizar stock | 🔒 |
| `DELETE` | `/product/{id}` | Eliminar producto | 🔒 |

### Categories
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/category` | Listar categorías | 🔒 |
| `POST` | `/category` | Crear categoría | 🔒 |
| `DELETE` | `/category/{id}` | Eliminar categoría | 🔒 |

### Carrito de compras
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `GET` | `/cart-item` | Ver carrito del cliente autenticado | 🔒 |
| `POST` | `/cart-item` | Agregar producto al carrito | 🔒 |
| `DELETE` | `/cart-item/{id}` | Eliminar un artículo del carrito | 🔒 |
| `DELETE` | `/cart-item` | Vaciar el carrito completo | 🔒 |

### Facturación
| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `POST` | `/invoice` | Finalizar compra y generar factura | 🔒 |

---

## Probar la API en producción (VPS)

> La API está desplegada y disponible, el despligue lo hice todo artesanalmente, y se implementaron algunas cosas como que sea una conexión segura es decir HTTPS, agregué un DNS para poder poner la url sin la ip específica y sea más legible. Solo sustituye `localhost:8080` por la URL del VPS.

### 1. Obtener token

```http
POST /auth/login
Content-Type: application/json

{
  "username": "tu_usuario",
  "password": "tu_password"
}
```

La respuesta incluye el campo `token`.

### 2. Autorizar en Postman

1. En la pestaña **Authorization** de la colección o la petición, selecciona tipo **Bearer Token**.
2. Pega el token recibido.
3. Todas las peticiones de esa colección usarán el token automáticamente.

O de forma manual en el header:

```
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

> **Tip:** El cliente se identifica automáticamente por su token. No necesitas enviar el `customerId` en el body del carrito — el gateway lo extrae del JWT y lo inyecta internamente.

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
El tiempo de inicio completo es aproximadamente **2-3 minutos**.

### 3. Verificar que todo está levantado

| URL | Descripción |
|---|---|
| `http://localhost:8761` | Eureka — todos los servicios deben aparecer como `UP` |
| `http://localhost:9090` | Spring Boot Admin |
| `http://localhost:8080` | Gateway (punto de entrada de la API) |

---

## Documentación Swagger

Solo el `product-service` tiene Swagger habilitado. Al correr localmente:

```
http://localhost:8082/swagger-ui/index.html
```

> Para habilitar Swagger en los demás servicios hay que agregar la dependencia `springdoc-openapi-starter-webmvc-ui` en cada `pom.xml`.

