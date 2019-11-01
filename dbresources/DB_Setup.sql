--Creacion de esquema de datos

CREATE SCHEMA `empresa_seguridad` ;

--Creacion de tablas que componen el esquema
CREATE TABLE `empresa_seguridad`.`tipo_documento` (
  `id_tipo_documento` INT NOT NULL AUTO_INCREMENT,
  `nombre_tipo_documento` VARCHAR(30) NOT NULL,
  `abreviatura_tipo_documento` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id_tipo_documento`),
  UNIQUE INDEX `abreviatura_tipo_documento_UNIQUE` (`abreviatura_tipo_documento` ASC) VISIBLE)
COMMENT = 'Tabla con los diferentes tipos de documentos autorizados en el pais.';

CREATE TABLE `empresa_seguridad`.`rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
  `nombre_rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_rol`))
COMMENT = 'Tabla en la que se guardan los roles establecidos por la organizacion';

CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipo_documento_persona` int(11) NOT NULL,
  `numero_documento_persona` varchar(15) NOT NULL,
  `nombres_persona` varchar(45) NOT NULL,
  `primer_apellido_persona` varchar(30) NOT NULL,
  `segundo_apellido_persona` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `numero_documento_persona_UNIQUE` (`numero_documento_persona`),
  KEY `tipo_documento_idx` (`id_tipo_documento_persona`),
  CONSTRAINT `tipo_documento` FOREIGN KEY (`id_tipo_documento_persona`) REFERENCES `tipo_documento` (`id_tipo_documento`)
) COMMENT='Tabla con la información personal de todos los empleados de la empresa';

CREATE TABLE `empresa_seguridad`.`empresa` (
  `id_empresa` INT NOT NULL AUTO_INCREMENT,
  `nombre_empresa` VARCHAR(45) NOT NULL,
  `nit_empresa` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_empresa`))
COMMENT = 'Tabla en la que se almacenan las empresas a las cuales se les presta servicio de vigilancia';

CREATE TABLE `empresa_seguridad`.`turno` (
  `id_turno` INT NOT NULL AUTO_INCREMENT,
  `nombre_turno` VARCHAR(7) NOT NULL,
  `hora_inicio_turno` INT NOT NULL COMMENT 'Hora militar en la que inicia el turno, se usa como entero para facilitar su posterior manejo.',
  `hora_fin_turno` INT NOT NULL COMMENT 'Hora militar en la que finaliza el turno, se usa como entero para facilitar su posterior manejo.',
  PRIMARY KEY (`id_turno`))
COMMENT = 'tabla en la que se almacenan los turnos establecidos por la organizacion';

CREATE TABLE `empresa_seguridad`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `pass_usuario` VARCHAR(45) NOT NULL,
  `id_persona_usuario` INT NOT NULL,
  `id_rol_usuario` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `usuario_UNIQUE` (`nombre_usuario` ASC) VISIBLE)
  INDEX `datos_persona_idx` (`id_persona_usuario` ASC) VISIBLE,
  INDEX `rol_usuario_idx` (`id_rol_usuario` ASC) VISIBLE,
  CONSTRAINT `datos_persona`
    FOREIGN KEY (`id_persona_usuario`)
    REFERENCES `empresa_seguridad`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rol_usuario`
    FOREIGN KEY (`id_rol_usuario`)
    REFERENCES `empresa_seguridad`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabla en la que se almacenan los usuarios de la aplicacion (Administrador, secretaria, supervisor y vigilantes)';

CREATE TABLE `turno_usuario_empresa` (
  `id_usuario` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_turno` int(11) NOT NULL,
  `fehca_inicio_turno` date NOT NULL,
  `fecha_fin_turno` date NOT NULL,
  `id_registro_turno` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_registro_turno`)
) COMMENT='Tabla en la que se relacionan los turnos con los usuarios y las empresas';


CREATE TABLE `reporte_turnos` (
  `id_registro` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario_registra` int(11) NOT NULL,
  `id_usuario_reportado` int(11) NOT NULL,
  `comentario_reporte` varchar(1000) NOT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `id_usuario_reporta_idx` (`id_usuario_registra`),
  KEY `id_usuario_reportado_idx` (`id_usuario_reportado`),
  CONSTRAINT `id_usuario_reporta` FOREIGN KEY (`id_usuario_registra`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `id_usuario_reportado` FOREIGN KEY (`id_usuario_reportado`) REFERENCES `usuario` (`id_usuario`)
) COMMENT = 'Tabla en la que se registran los comentarios hechos por el supervisor';


--Parametrizacion de la base de datos

INSERT INTO empresa_seguridad.tipo_documento
(nombre_tipo_documento, abreviatura_tipo_documento)
values
('CEDULA DE CIUDADANIA', 'CC');

INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '11111', 'Luis Arturo', 'Velez', 'Velez');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '22222', 'Secretaria', 'Prueba', 'Uno');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '33333', 'SUPERVISOR', 'PRUEBA', 'UNO');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '44444', 'VIGILANTE', 'PRUEBA', 'DOS');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '55555', 'VIGILANTE', 'PRUEBA', 'TRES');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '66666', 'VIGILANTE', 'PRUEBA', 'CUATRO');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '77777', 'VIGILANTE', 'PRUEBA', 'CINCO');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '88888', 'VIGILANTE', 'PRUEBA', 'SEIS');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '99999', 'VIGILANTE', 'PRUEBA', 'SIETE');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '101010', 'VIGILANTE', 'PRUEBA', 'OCHO');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '121212', 'VIGILANTE', 'PRUEBA', 'NUEVE');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '131313', 'VIGILANTE', 'PRUEBA', 'DIEZ');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '141414', 'VIGILANTE', 'PRUEBA', 'ONCE');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '151515', 'VIGILANTE', 'PRUEBA', 'DOCE');
INSERT INTO `empresa_seguridad`.`persona` (`id_tipo_documento_persona`, `numero_documento_persona`, `nombres_persona`, `primer_apellido_persona`, `segundo_apellido_persona`) VALUES ('1', '161616', 'VIGILANTE', 'PRUEBA', 'TRECE');


INSERT INTO `empresa_seguridad`.`empresa` (`nombre_empresa`, `nit_empresa`) VALUES ('PAQUITA GALLEGO', '9008001');
INSERT INTO `empresa_seguridad`.`empresa` (`nombre_empresa`, `nit_empresa`) VALUES ('MARIA LA DEL BARRIO', '9008002');

INSERT INTO `empresa_seguridad`.`rol` (`nombre_rol`) VALUES ('ADMINISTRADOR');
INSERT INTO `empresa_seguridad`.`rol` (`nombre_rol`) VALUES ('SECRETARIA');
INSERT INTO `empresa_seguridad`.`rol` (`nombre_rol`) VALUES ('VIGILANTE');
INSERT INTO `empresa_seguridad`.`rol` (`nombre_rol`) VALUES ('SUPERVISOR');

INSERT INTO `empresa_seguridad`.`turno` (`nombre_turno`, `hora_inicio_turno`, `hora_fin_turno`) VALUES ('DIA', '0600', '1800');
INSERT INTO `empresa_seguridad`.`turno` (`nombre_turno`, `hora_inicio_turno`, `hora_fin_turno`) VALUES ('NOCHE', '1800', '0600');

INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('luis.velez', 'admin', '1', '1');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('secretaria.prueba', '123456', '2', '2');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('supervisor.prueba', '123456', '3', '4');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.02', 'vig02', '4', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.03', 'vig03', '5', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.04', 'vig04', '6', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.05', 'vig05', '7', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.06', 'vig06', '8', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.06', 'vig07', '9', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.08', 'vig08', '10', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.09', 'vig09', '11', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.10', 'vig10', '12', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.11', 'vig11', '13', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.12', 'vig12', '14', '3');
INSERT INTO `empresa_seguridad`.`usuario` (`nombre_usuario`, `pass_usuario`, `id_persona_usuario`, `id_rol_usuario`) VALUES ('vigilante.13', 'vig13', '15', '3');


INSERT INTO `empresa_seguridad`.`turno_usuario_empresa` (`id_usuario`, `id_empresa`, `id_turno`, `fehca_inicio_turno`, `fecha_fin_turno`) VALUES ('4', '1', '1', '2019-10-21', '2019-11-03');
INSERT INTO `empresa_seguridad`.`turno_usuario_empresa` (`id_usuario`, `id_empresa`, `id_turno`, `fehca_inicio_turno`, `fecha_fin_turno`) VALUES ('5', '1', '2', '2019-10-21', '2019-11-03');
INSERT INTO `empresa_seguridad`.`turno_usuario_empresa` (`id_usuario`, `id_empresa`, `id_turno`, `fehca_inicio_turno`, `fecha_fin_turno`) VALUES ('6', '2', '1', '2019-10-21', '2019-11-03');
INSERT INTO `empresa_seguridad`.`turno_usuario_empresa` (`id_usuario`, `id_empresa`, `id_turno`, `fehca_inicio_turno`, `fecha_fin_turno`) VALUES ('7', '2', '2', '2019-10-21', '2019-11-03');










