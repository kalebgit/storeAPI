#!/bin/bash
# levanta todos los servicios en modo desarrollo

echo "levantando servicios en modo dev..."
docker compose up -d --build

echo ""
echo "servicios disponibles:"
echo "  gateway:   http://localhost:8080"
echo "  eureka:    http://localhost:8761"
echo "  admin:     http://localhost:9090"
echo "  config:    http://localhost:8888"
