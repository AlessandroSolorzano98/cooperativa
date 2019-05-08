create database cooperativa;
use cooperativa;

create table persona(
	id_persona int not null auto_increment primary key,
    nombres varchar(60),
    apellidos varchar(60),
    numeroDocumento varchar(70)
);

create table cliente(
	id_cliente int not null auto_increment primary key,
    id_persona int,
    esMiembro char(1),
    foreign key fk_clientepersona(id_persona)
    references persona(id_persona)
);

create table referencia(
	id_referencia int not null auto_increment primary key,
    id_cliente int,
    id_persona int,
    tiempoconocerse int,
    foreign key fk_refcliente(id_cliente)
    references cliente(id_cliente),
    foreign key fk_refpersona(id_persona)
    references persona(id_persona)
);

create table beneficiario(
	id_beneficiario int not null auto_increment primary key,
    id_cliente int,
    id_persona int,
    edad int,
    parentesco varchar(50),
    porcentaje float(2.2),
    foreign key fk_bencliente(id_cliente)
    references cliente(id_cliente),
    foreign key fk_benpersona(id_persona)
    references persona(id_persona)
);

create table direccion(
	id_direccion int not null auto_increment primary key,
    id_persona int,
    direccion varchar(150),
    foreign key fk_dirpersona(id_persona)
    references persona(id_persona)
);

create table telefono(
	id_telefono int not null auto_increment primary key,
    id_persona int,
    telefono varchar(20),
    foreign key fk_telpersona(id_persona)
    references persona(id_persona)
);

create table cuentatipo(
	id_cuentatipo int not null auto_increment primary key,
    nombre varchar(150)
);


create table cuenta(
	id_cuenta int not null auto_increment primary key,
	numerocuenta varchar(50) unique,
    id_cliente int,
    id_cuentatipo int,
    totalCargos float(10,5),
    totalAbonos float(10,5),
    saldo float(10,5),
    foreign key fk_cuentatipo(id_cuentatipo)
    references cuentatipo(id_cuentatipo),
    foreign key fk_cuentacliente(id_cliente)
    references cliente(id_cliente)
);

create table transaccion(
	id_transaccion int not null auto_increment primary key,
	id_cuenta int,
    montocargo float(10,5),
    montoabono float(10,5),
    fecha date,
    foreign key fk_cuenta(id_cuenta)
    references cuenta(id_cuenta)
);
