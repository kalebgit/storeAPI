#!/bin/bash
# primera instalacion en el vps de hostinger es el servicio que tengo para poder desplegar
# igual para documentar lo que hice paso a paso
# ejecutar una sola vez despues de clonar el repositorio

echo "configurando el servidor..."

# verificar que docker este instalado
if ! command -v docker &> /dev/null; then
  echo "instalando docker..."
  curl -fsSL https://get.docker.com | sh
fi

# verificar que exista el .env con las variables necesarias
if [ ! -f .env ]; then
  echo ""
  echo "no existe el archivo .env"
  echo "crearlo con: vim .env"
  echo ""
  echo "debe contener las siguientes variables:"
  echo "  DB_USER=postgres"
  echo "  DB_PASSWORD=tu_password"
  echo "  JWT_PRIVATE_KEY=..."
  echo "  JWT_PUBLIC_KEY=..."
  echo ""
  echo "para generar las claves jwt ejecuta: bash scripts/generate-keys.sh"
  echo "cuando termine con esto se vuelve a ejecutar: bash scripts/setup-prod.sh"
  exit 1
fi

echo "iniciando servicios en produccion..."
docker compose -f docker-compose.prod.yml up -d --build

echo ""
echo "deploy completado. gateway disponible en el puerto 8080"
