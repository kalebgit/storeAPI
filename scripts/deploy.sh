#!/bin/bash
echo "actualizando proyecto..."
git pull
docker compose -f docker-compose.prod.yml up -d --build
echo "deploy completado."
