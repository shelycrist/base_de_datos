DROP TABLE IF EXISTS bitacora CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS producto_factura CASCADE;
DROP TABLE IF EXISTS factura CASCADE;
DROP TABLE IF EXISTS producto CASCADE;
DROP TABLE IF EXISTS tema CASCADE;

CREATE TABLE tema (
id int NOT NULL AUTO_INCREMENT,
nombre_tema varchar (50) NOT NULL,
descripcion varchar(50) NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
status enum('Activo','Inactivo'),
primary key (id)
);

CREATE TABLE producto (
id int NOT NULL AUTO_INCREMENT,
nombre_producto varchar (50) NOT NULL,
codigo varchar(50) NOT NULL,
precio float NOT NULL,
descripcion varchar(50) NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
status enum('Disponible','Agotado'),
primary key (id)
);

CREATE TABLE factura (
id int NOT NULL AUTO_INCREMENT,
nombre_cliente varchar (50) NOT NULL,
cedula varchar(50) NOT NULL,
rif varchar(50) NOT NULL,
direccion varchar(50) NOT NULL,
monto float NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
status enum('Activa','Inactiva'),
primary key (id)
);

CREATE TABLE producto_factura (
id int NOT NULL AUTO_INCREMENT,
id_producto int NOT NULL,
id_factura int NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
primary key (id),
foreign key (id_producto) references producto (id),
foreign key (id_factura) references factura (id)
);

CREATE TABLE ticket (
id int NOT NULL AUTO_INCREMENT,
nombre_contacto varchar (50) NOT NULL,
email varchar(50) NOT NULL,
telefono varchar(50) NOT NULL,
tema_id int NOT NULL,
factura_id int NOT NULL,
detalle_falla varchar(350),
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
status enum('Activo','Inactivo'),
primary key (id),
foreign key (tema_id) references tema (id),
foreign key (factura_id) references factura (id)
);

create TABLE usuario (
id int NOT NULL AUTO_INCREMENT,
nombre varchar(50) NOT NULL,
clave varchar(50) NOT NULL,
correo varchar(50) NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
status enum('Activo','Inactivo'),
primary key (id)
);

create TABLE bitacora (
id int NOT NULL AUTO_INCREMENT,
usuario_id int NOT NULL,
accion varchar(50) NOT NULL,
fecha_creacion DATE NOT NULL,
fecha_actualizacion DATE NOT NULL,
primary key (id),
foreign key (usuario_id) references usuario (id)
);

INSERT INTO tema (nombre_tema, descripcion, fecha_creacion, fecha_actualizacion, status)
VALUES ('Soporte','Si requiere alguna orientacion adicional', '05/05/22', '07/05/22', 'Activo'),
('Falla','Si algun producto tiene fallas', '06/05/22', '08/05/22', 'Activo'),
('Inconformidad','Si algun producto no cumple sus expectativas', '05/05/22', '07/05/22', 'Activo'),
('Pago','Si se le facturo algun producto que no corresponde', '05/05/22', '07/05/22', 'Activo');

INSERT INTO producto (nombre_producto, codigo, precio, descripcion, fecha_creacion, fecha_actualizacion, status)
VALUES ('Licuadora', 'LIC' , '40', 'Licuadora/Mixer', '02/04/22', '02/04/22', 'Disponible'),
('Batidora', 'BAT' , '20', 'Batidora multiuso', '03/04/22', '03/04/22', 'Disponible'),
('Cafetera Oster', 'CAF' , '90', 'Cafetera multifuncion', '04/04/22', '06/04/22', 'Disponible');

INSERT INTO factura (nombre_cliente, cedula, rif, direccion,  monto, fecha_creacion, fecha_actualizacion, status)
VALUES ('Juan Perez', '25.141.826', '25-141826-002', 'Patarata',  '40', '08/05/22', '08/05/22', 'Activa'),
('Shely', '25.865.358', '25-856358-002', 'Nueva Segovia',  '90', '10/05/22', '12/05/22', 'Activa'),
('Maria Lopez', '25.898.989', '25-898989-002', 'Santa Elena',  '20', '09/05/22', '09/05/22', 'Activa');

INSERT INTO producto_factura (id_producto, id_factura, fecha_creacion, fecha_actualizacion)
VALUES ('3', '2', '11/05/22', '12/05/22'),
('2', '3', '12/05/22', '13/05/22'),
('1', '1', '13/05/22', '14/05/22');

INSERT INTO ticket (nombre_contacto, email, telefono, tema_id, factura_id, detalle_falla, fecha_creacion, fecha_actualizacion, status)
VALUES ('Mary Yepez', 'mary@mary.mary', '123456789','2', '2', 'No funciona el producto', '15/05/22', '15/05/22', 'Activo');

INSERT INTO usuario (nombre, clave, correo, fecha_creacion, fecha_actualizacion, status)
VALUES ('admin', 'admin', 'admin@admin.admin', '10/05/22', '10/05/22', 'Activo')
