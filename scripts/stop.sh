#!/bin/bash
# detiene todos los servicios sin borrar los volumenes

echo "deteniendo servicios..."
docker compose down

echo "servicios detenidos. los datos de la base de datos se conservan."
echo "para borrar tambien los datos usa: docker compose down -v"
