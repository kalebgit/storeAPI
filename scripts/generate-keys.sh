#!/bin/bash
# genera el par de claves rsa para firmar y verificar jwt

echo "generando claves rsa..."

# genera la clave privada de 2048 bits
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048

# extrae la clave publica desde la privada
openssl rsa -pubout -in private.pem -out public.pem

echo "claves generadas: private.pem y public.pem"
echo ""
echo "se deben agregar estas claves a .env:"
echo ""
echo "JWT_PRIVATE_KEY=\"$(cat private.pem | tr '\n' '|' | sed 's/|/\\n/g')\""
echo ""
echo "JWT_PUBLIC_KEY=\"$(cat public.pem | tr '\n' '|' | sed 's/|/\\n/g')\""
echo ""
echo "se deben eliminar estos archivos pem"
