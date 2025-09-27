-- Tablas Usuarios

CREATE TABLE users (
  id              BIGSERIAL PRIMARY KEY,
  email           CITEXT UNIQUE NOT NULL,
  password        TEXT NOT NULL,              
  full_name       TEXT NOT NULL,
  is_active       BOOLEAN NOT NULL DEFAULT true,
  created_at      TIMESTAMPTZ DEFAULT now(),
  updated_at      TIMESTAMPTZ DEFAULT now()
);

-- Manejo de Roles

CREATE TABLE roles (
  id        BIGSERIAL PRIMARY KEY,
  scope     TEXT NOT NULL CHECK (scope IN ('PLATFORM','COMPANY')),
  code      TEXT NOT NULL,
  name      TEXT NOT NULL,
  UNIQUE (scope, code)
);

-- Tabla Empresas

CREATE TABLE companies (
  id BIGSERIAL PRIMARY KEY,
  trade_name TEXT NOT NULL,
  legal_name TEXT NOT NULL,
  tax_id VARCHAR(13) UNIQUE,
  country TEXT NOT NULL,
  province TEXT NOT NULL,
  city TEXT NOT NULL,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Tabla Clientes

CREATE TABLE customers (
  id BIGSERIAL PRIMARY KEY,
  company_id BIGINT NOT NULL REFERENCES companies(id) ON DELETE CASCADE,
  identification VARCHAR(20) NOT NULL,
  identification_Type TEXT NOT NULL, -- CEDULA, RUC, PASAPORTE
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  email TEXT,
  phone TEXT,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT uk_customer_ident UNIQUE (company_id, identification)
);

-- Tabla direcciones de Clientes

CREATE TABLE customer_addresses (
  id BIGSERIAL PRIMARY KEY,
  customer_id BIGINT NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
  alias TEXT,
  street TEXT NOT NULL,
  city TEXT,
  state TEXT,
  country TEXT,
  zip TEXT,
  is_headquarters BOOLEAN NOT NULL DEFAULT false,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Controlar que sea solo Cedula, RUC, Pasaporte

ALTER TABLE customers
  ADD CONSTRAINT chk_ident_type
  CHECK (identification_type IN ('CEDULA','RUC','PASAPORTE'));


-- Mantener una sola Matriz por Cliente
CREATE UNIQUE INDEX one_hq_per_customer
  ON customer_addresses (customer_id)
  WHERE is_headquarters = true;

-- Búsquedas Rápidas
CREATE INDEX idx_customers_company_ident ON customers(company_id, identification);
CREATE INDEX idx_customers_company_name   ON customers(company_id, name);

-- Extenciones 

CREATE EXTENSION IF NOT EXISTS citext;


-- =======================
-- Datos de ejemplo
-- =======================

INSERT INTO users (email, password_hash, full_name)
VALUES
('admin@minegocio.com', 'fsdasfdsdfw', 'Admin'),

INSERT INTO roles (scope, code, name)
VALUES
('PLATFORM', 'PLATFORM_ADMIN', 'Administrador de Plataforma'),
('PLATFORM', 'SUPPORT_AGENT', 'Agente de Soporte'),
('COMPANY',  'COMPANY_ADMIN', 'Administrador de Empresa'),
('COMPANY',  'BILLING_CLERK', 'Cajero/Faturador');

INSERT INTO companies (trade_name, legal_name, tax_id, country, province, city)
VALUES
('Tuti', 'Tuti S.A.', '1790012345001', 'Ecuador', 'Pichincha', 'Quito'),
('Farmacias Cruz Azul', 'Farmacia Cruz Azul Cía. Ltda.', '1790098765001', 'Ecuador', 'Tungurahua', 'Ambato');

INSERT INTO customers (company_id, identification, identification_type, name, lastname, email, phone)
VALUES
(1, '1712345678', 'CEDULA', 'Carlos', 'Andrade', 'carlos.andrade@gmail.com', '0991111111'),
(1, '1799999999001', 'RUC', 'Nintendo', '', 'ventas@nintendo.com', '022222222'),
(1, 'AB1234567', 'PASAPORTE', 'Mario', 'Andrade', 'mario.andrade15@yahoo.com', '0933333333'),
(2, '1809876543', 'CEDULA', 'Ana', 'Mendoza', 'ana.mendoza@hotmail.com', '0954444444'),
(2, '1798888888001', 'RUC', 'Panificadora Ambato', '', 'contacto@panificadoraambato.com', '032222333');

INSERT INTO customer_addresses (customer_id, alias, street, city, state, country, zip, is_headquarters)
VALUES
(2, 'Matriz', 'Av. Amazonas y Colón', 'Quito', 'Pichincha', 'Ecuador', '170101', true),
(2, 'Sucursal Ambato', 'Parque Industrial', 'Ambato', 'Tungurahua', 'Ecuador', '180150', false),
(2, 'Sucursal Guayaquil', 'Vía Perimetral', 'Guayaquil', 'Guayas', 'Ecuador', '090150', false),
(4, 'Matriz', 'Av. Cevallos 123', 'Ambato', 'Tungurahua', 'Ecuador', '180101', true);
