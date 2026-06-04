#!/bin/bash
# actualiza el proyecto en el vps tras un git push

echo "actualizando proyecto..."

# descarga los cambios del repositorio
git pull

# reconstruye y reinicia los servicios que cambiaron
docker compose -f docker-compose.prod.yml up -d --build

echo "deploy completado."
