-- springbootdb.cursos definition

CREATE TABLE `cursos` (
  `id_cursos` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `horas_semanales` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cursos`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



-- springbootdb.alumnos definition

CREATE TABLE `alumnos` (
  `id_alumnos` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `libreta` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_alumnos`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- springbootdb.alumnos_curso definition

CREATE TABLE `alumnos_curso` (
  `id_cursos` bigint(20) NOT NULL,
  `id_alumnos` bigint(20) NOT NULL,
  KEY `FKabpuxp7p833nsyur5cpafja7n` (`id_alumnos`),
  KEY `FKckst8qjyinflndkv6vp70gsn8` (`id_cursos`),
  CONSTRAINT `FKabpuxp7p833nsyur5cpafja7n` FOREIGN KEY (`id_alumnos`) REFERENCES `alumnos` (`id_alumnos`),
  CONSTRAINT `FKckst8qjyinflndkv6vp70gsn8` FOREIGN KEY (`id_cursos`) REFERENCES `cursos` (`id_cursos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




INSERT INTO springbootdb.alumnos(apellido, fecha_nacimiento, libreta, nombre)VALUES('Romano', '1995-03-02', 123, 'Gaston');
INSERT INTO springbootdb.alumnos(apellido, fecha_nacimiento, libreta, nombre)VALUES('Chantiri', '1995-03-02', 124, 'Marina');
INSERT INTO springbootdb.alumnos(apellido, fecha_nacimiento, libreta, nombre)VALUES('Maste', '1985-01-02', 125, 'Lucia');
INSERT INTO springbootdb.alumnos(apellido, fecha_nacimiento, libreta, nombre)VALUES('Roman', '2000-05-05', 126, 'Ramon');




INSERT INTO springbootdb.cursos(fecha_fin, fecha_inicio, horas_semanales, nombre) values ('2020-06-01','2020-04-01',4,'Historia');
INSERT INTO springbootdb.cursos(fecha_fin, fecha_inicio, horas_semanales, nombre) values ('2020-11-01','2020-08-01',16,'Lengua');
INSERT INTO springbootdb.cursos(fecha_fin, fecha_inicio, horas_semanales, nombre) values ('2020-06-01','2020-04-02',20,'Matematica');
INSERT INTO springbootdb.cursos(fecha_fin, fecha_inicio, horas_semanales, nombre) values ('2020-02-01','2020-01-03',6,'Programacion');
