USE db_eduini;

INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('LING�STICO- VERBAL');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('L�GICA MATEM�TICA');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('ESPACIAL');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('CORPORAL KINEST�SICA');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('MUSICAL');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('INTERPERSONAL');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('INTRAPERSONAL');
INSERT INTO `db_eduini`.`estilo_aprendizaje` (`nombre`) VALUES ('NATURALISTA');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Cuaderno de Dibujo', '1', '3');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Reproductor de sonido', '1', '5');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Telefono', '1', '1');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Pelota', '1', '4');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Puzzle', '1', '2');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Flores', '1', '8');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Libro', '1', '7');
INSERT INTO `db_eduini`.`recurso_didactico` (`nombre`, `peso`, `id_estilo`) VALUES ('Castillo', '1', '6');

--considerar tabla de actividades
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('1');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('2');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('3');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('4');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('5');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('6');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('7');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('8');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('9');
INSERT INTO `db_eduini`.`alumno` (`id`) VALUES ('10');
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno1@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno1@upc.edu.pe', 'alumno1', '44444444', true, 'alumno1','1111','ROLE_USER', 1);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno2@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno2@upc.edu.pe', 'alumno2', '44444444', true, 'alumno2','1111','ROLE_USER', 2);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno3@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno3@upc.edu.pe', 'alumno3', '44444444', true, 'alumno3','1111','ROLE_USER', 3);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno4@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno4@upc.edu.pe', 'alumno4', '44444444', true, 'alumno4','1111','ROLE_USER', 4);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno5@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno5@upc.edu.pe', 'alumno5', '44444444', true, 'alumno5','1111','ROLE_USER', 5);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno6@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno6@upc.edu.pe', 'alumno6', '44444444', true, 'alumno6','1111','ROLE_USER', 6);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno7@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno7@upc.edu.pe', 'alumno7', '44444444', true, 'alumno7','1111','ROLE_USER', 7);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno8@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno8@upc.edu.pe', 'alumno8', '44444444', true, 'alumno8','1111','ROLE_USER', 8);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno9@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno9@upc.edu.pe', 'alumno9', '44444444', true, 'alumno9','1111','ROLE_USER', 9);
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_alumno`) VALUES ('alumno10@upc.edu.pe', 'urb.ramon castilla k34', '1111111', 'alumno10@upc.edu.pe', 'alumno10', '44444444', true, 'alumno10','1111','ROLE_USER', 10);
INSERT INTO `db_eduini`.`profesor` (`id`) VALUES ('1');
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_profesor`) VALUES ('oscarquerevalu@gmail.com', 'urb.ramon castilla k34', '1111111', 'oscarquerevalu@gmail.com', 'profesor1', '44444444', true, 'profesor1','$2a$10$InNhEXWztd5qf2WAa/iTQOblQb3oW.VhOGZOApOWCy4//k/JUhMBa','ROLE_USER', 1);
INSERT INTO `db_eduini`.`clase` (`id`,`fecha_clase`,`id_profesor`) VALUES (1,sysdate(),1);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,1);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,2);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,3);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,4);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,5);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,6);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,7);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,8);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,9);
INSERT INTO `db_eduini`.`alumno_clase` (`id_clase`,`id_alumno`) VALUES (1,10);
INSERT INTO `db_eduini`.`apoderado` (`id`) VALUES ('1');
INSERT INTO `db_eduini`.`persona` (`confirm_email`, `direccion`, `documento`, `email`, `name`, `telefono`, `terms`, `username`, `password`, `role`, `id_apoderado`) VALUES ('jcastillo@gmail.com', 'metro de av. Tomas Valle', '1111111', 'jcastillo@gmail.com', 'apoderado1', '44444444', true, 'apoderado1','$2a$10$InNhEXWztd5qf2WAa/iTQOblQb3oW.VhOGZOApOWCy4//k/JUhMBa','ROLE_APODE', 1);


select * from persona
