SELECT 'CREATE DATABASE auth_db'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'auth_db')\gexec

SELECT 'CREATE DATABASE customer_db'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'customer_db')\gexec

SELECT 'CREATE DATABASE product_db'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'product_db')\gexec

SELECT 'CREATE DATABASE category_db'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'category_db')\gexec

SELECT 'CREATE DATABASE invoice_db'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'invoice_db')\gexec
