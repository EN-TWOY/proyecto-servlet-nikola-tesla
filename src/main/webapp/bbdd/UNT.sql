DROP DATABASE IF EXISTS universidad_nikola_tesla;
CREATE DATABASE universidad_nikola_tesla;
USE universidad_nikola_tesla;

-- TABLAS

CREATE TABLE carrera(
	ide_car int auto_increment,
    des_car varchar(100) not null,
    constraint pk_car primary key (ide_car)
);

CREATE TABLE modalidad(
	ide_mod int auto_increment,
	des_mod varchar(50) not null,
    constraint pk_modalidad primary key (ide_mod) 
);

CREATE TABLE materia(
	ide_mat int auto_increment,
    des_mat varchar(100) not null,
    ide_mod int,
    constraint pk_materia primary key (ide_mat),
    constraint fk_modalidad foreign key (ide_mod) references modalidad (ide_mod)
);

CREATE TABLE profesor(
	ide_pro int auto_increment,
    nom_pro varchar(50) not null,
    ape_pro varchar(50) not null,
    dni_pro char(8) not null,
    gen_pro char(1) not null,
    fec_pro date not null,
    ema_pro varchar(50) not null,
    tel_pro char(15) not null,
    dir_pro varchar(200) not null,
    constraint pk_profesor primary key (ide_pro)
);

CREATE TABLE turno(
	ide_tur int auto_increment,
    des_tur varchar(50) not null,
    constraint pk_turno primary key (ide_tur)
);

CREATE TABLE sede(
	ide_sed int auto_increment,
    des_sed varchar(50) not null,
    constraint pk_sede primary key (ide_sed)
);

CREATE TABLE administrador(
	ide_adm int auto_increment,
    nom_adm varchar(50) not null,
    ape_adm varchar(50) not null,
    dni_adm char(8) not null,
    gen_adm char(1) not null,
    fec_adm date not null,
    ema_adm varchar(50) not null,
    tel_adm char(15) not null,
    dir_adm varchar(200) not null,
    usuario varchar(10) not null,
    contra varchar(15) not null,
    constraint pk_administrador primary key (ide_adm)
);

CREATE TABLE estudiante(
	ide_est int auto_increment,
    nom_est varchar(50) not null,
    ape_est varchar(50) not null,
    dni_est char(8) not null,
    gen_est varchar(20) not null,
    fec_est date not null,
    ema_est varchar(50) not null,
    tel_est char(15) not null,
	dir_est varchar(200) not null,
	constraint pk_estudiante primary key (ide_est)
);

CREATE TABLE matricula(
	num_mat int auto_increment,
    fec_mat date not null,
    ide_adm int,
    ide_est int,
	ide_car int,
    ide_tur int,
    ide_sed int,
    constraint pk_matricula primary key (num_mat),
    constraint fk_administrador foreign key (ide_adm) references administrador (ide_adm),
    constraint fk_estudiante foreign key (ide_est) references estudiante (ide_est),
    constraint fk_carrera foreign key (ide_car) references carrera (ide_car),
    constraint fk_turno foreign key (ide_tur) references turno (ide_tur),
    constraint fk_sede foreign key (ide_sed) references sede (ide_sed)
);

CREATE TABLE contrato(
	num_con int auto_increment,
    fec_con date not null,
	ide_adm int,
    ide_pro int,
    sue_con float not null,
    constraint pk_contrato primary key (num_con),
    constraint fk_administrador_2 foreign key (ide_adm) references administrador (ide_adm),
    constraint fk_profesor_2 foreign key (ide_pro) references profesor (ide_pro)
);

CREATE TABLE materia_profesor(
	ide_mat int,
    ide_pro int,
	constraint fk_mat_pro_1 foreign key (ide_mat) references materia (ide_mat),
	constraint fk_mat_pro_2 foreign key (ide_pro) references profesor (ide_pro)
);

CREATE TABLE materia_estudiante(
	ide_mat int,
    ide_est int,
    constraint fk_mat_est_1 foreign key (ide_mat) references materia (ide_mat),
    constraint fk_mat_est_2 foreign key (ide_est) references estudiante (ide_est)
);

CREATE TABLE estudiante_profesor(
	ide_est int,
    ide_pro int,
    constraint fk_est_pro_1 foreign key (ide_est) references estudiante (ide_est),
    constraint fk_est_pro_2 foreign key (ide_pro) references profesor (ide_pro) 
);

-- INSERCIONES

INSERT INTO carrera VALUES 
(1, "ADMINISTRACIÓN Y AFINES"),
(2, "AGRONOMÍA"),
(3, "ALTA COSTURA"),
(4, "ANTROPOLOGÍA"),
(5, "ARQUEOLOGÍA"),
(6, "ARQUITECTURA Y AFINES"),
(7, "ARTES ESCÉNICAS Y AFINES"),
(8, "ARTES LIBERALES"),
(9, "ARTES PLÁSTICAS Y AFINES"),
(10, "BACTERIOLOGÍA"),
(11, "BIBLIOTECOLOGÍA"),
(12, "BIOINGENIERÍA"),
(13, "BIOLOGÍA Y AFINES"),
(14, "CARTOGRAFÍA"),
(15, "CIENCIAS POLÍTICAS"),
(16, "CIENCIAS SOCIALES"),
(17, "CINE, TELEVISIÓN Y AFINES"),
(18, "COMERCIO EXTERIOR, NEGOCIOS INTERNACIONALES"),
(19, "COMUNICACIÓN E INFORMACIÓN"),
(20, "CONSERVACIÓN Y RESTAURACIÓN DE BIENES"),
(21, "CONTABILIDAD Y AFINES"),
(22, "CRIMINALÍSTICA, INVESTIGACIÓN JUDICIAL"),
(23, "CULTURA FISÍCA Y DEPORTE"),
(24, "DERECHO"),
(25, "DESARROLLO DE JOYAS, BOLSOS Y CALZADO"),
(26, "DISEÑO DE INTERIORES"),
(27, "DISEÑO DE MEDIOS INTERACTIVOS"),
(28, "DISEÑO DE MODAS"),
(29, "DISEÑO GRÁFICO"),
(30, "DISEÑO INDUSTRIAL"),
(31, "ECOLOGÍA Y AFINES"),
(32, "ECONOMÍA Y AFINES"),
(33, "ECONOMÍA Y NEGOCIOS INTERNACIONALES"),
(34, "EDUCACIÓN BÁSICA"),
(35, "EDUCACIÓN MEDIA"),
(36, "EDUCACIÓN PARA OTRAS MODALIDADES"),
(37, "EDUCACIÓN PREESCOLAR"),
(38, "ELECTRICIDAD INDUSTRIAL"),
(39, "ENFERMERÍA"),
(40, "FILOSOFÍA"),
(41, "FINANZAS Y NEGOCIOS INTERNACIONALES"),
(42, "FÍSICA"),
(43, "FORMACIÓN DE EMPRESARIOS"),
(44, "FOTOGRAFÍA"),
(45, "GASTRONOMÍA"),
(46, "GEOCIENCIAS"),
(47, "GEOGRAFÍA"),
(48, "GEOLOGÍA"),
(49, "GESTIÓN EMPRESARIAL"),
(50, "GESTIÓN FINANCIERA"),
(51, "GESTIÓN PORTUARIA"),
(52, "GESTIÓN Y DESARROLLO URBANO"),
(53, "HISTORIA"),
(54, "INGENIERÍA ADMINISTRATIVA"),
(55, "INGENIERÍA AERONÁUTICA"),
(56, "INGENIERÍA AGRÍCOLA"),
(57, "INGENIERÍA AGROINDUSTRIAL"),
(58, "INGENIERÍA AMBIENTAL"),
(59, "INGENIERÍA BIOMÉDICA"),
(60, "INGENIERÍA CIVIL"),
(61, "INGENIERÍA COMERCIAL"),
(62, "INGENIERÍA DE ALIMENTOS Y AFINES"),
(63, "INGENIERÍA DE DISEÑO DE PRODUCTO"),
(64, "INGENIERÍA DE MINAS"),
(65, "INGENIERÍA DE PETRÓLEOS"),
(66, "INGENIERÍA DE PROCESOS"),
(67, "INGENIERÍA DE PRODUCCIÓN"),
(68, "INGENIERÍA DE SISTEMAS Y AFINES"),
(69, "INGENIERÍA DE TELECOMUNICACIONES"),
(70, "INGENIERÍA ELÉCTRICA"),
(71, "INGENIERÍA ELECTROMECÁNICA"),
(72, "INGENIERÍA ELECTRÓNICA"),
(73, "INGENIERÍA FINANCIERA"),
(74, "INGENIERÍA FÍSICA"),
(75, "INGENIERÍA FORESTAL"),
(76, "INGENIERÍA GEOLÓGICA"),
(77, "INGENIERÍA INDUSTRIAL"),
(78, "INGENIERÍA INFORMÁTICA"),
(79, "INGENIERÍA MATERIALES"),
(80, "INGENIERÍA MECÁNICA"),
(81, "INGENIERÍA MECATRÓNICA"),
(82, "INGENIERÍA METALÚRGICA"),
(83, "INGENIERÍA MULTIMEDIA"),
(84, "INGENIERÍA PESQUERA"),
(85, "INGENIERÍA QUÍMICA"),
(86, "INGENIERÍA TEXTIL"),
(87, "INSTRUMENTACIÓN QUIRÚRGICA"),
(88, "JURISPRUDENCIA"),
(89, "LENGUAS MODERNAS Y AFINES"),
(90, "LICENCIATURA EN PEDAGOGÍA INFANTIL"),
(91, "LITERATURA, LINGÜÍSTICA Y AFINES"),
(92, "MATEMÁTICAS, ESTADÍSTICA Y AFINES"),
(93, "MEDICINA"),
(94, "MEDICINA VETERINARIA"),
(95, "MERCADEO"),
(96, "MERCADEO INTERNACIONAL"),
(97, "MICROBIOLOGÍA"),
(98, "MUSEOLOGÍA"),
(99, "MÚSICA Y AFINES"),
(100, "NUTRICIÓN Y DIETÉTICA"),
(101, "OBSTETRICIA"),
(102, "ODONTOLOGÍA"),
(103, "OPERACIÓN LOGÍSTICA"),
(104, "OPTOMETRÍA"),
(105, "PEDAGOGÍA REEDUCATIVA"),
(106, "PROCESOS INDUSTRIALES"),
(107, "PROGRAMACIÓN DE COMPUTADORES"),
(108, "PSICOLOGÍA"),
(109, "PUBLICIDAD Y AFINES"),
(110, "QUÍMICA FARMACEÚTICA"),
(111, "QUÍMICA Y AFINES"),
(112, "SALUD OCUPACIONAL"),
(113, "SISTEMAS DE INFORMACIÓN"),
(114, "SOCIOLOGÍA"),
(115, "TALENTO HUMANO"),
(116, "TEOLOGÍA Y AFINES"),
(117, "TERAPIA FÍSICA, FISIOTERAPIA Y AFINES"),
(118, "TERAPIA OCUPACIONAL"),
(119, "TERAPIA RESPIRATORIA"),
(120, "TRABAJO SOCIAL"),
(121, "TURISMO, HOTELERÍA Y AFINES"),
(122, "ZOOTECNIA");

INSERT INTO modalidad VALUES
(1, "PRESENCIAL"),
(2, "VIRTUAL"),
(3, "A DISTANCIA"),
(4, "SEMIPRESENCIAL");

INSERT INTO materia VALUES
(1, "ADMINISTRACIÓN Y NEGOCIOS", 1),
(2, "AGRICULTURA Y AGRONOMÍA", 2),
(3, "ALIMENTACIÓN Y NUTRICIÓN", 3),
(4, "ADMINISTRACIÓN Y NEGOCIOS", 4),
(5, "ALTA GERENCIA Y LIDERAZGO", 2),
(6, "ARQUITECTURA Y URBANISMO", 3),
(7, "ARTE, CINE Y TELEVISIÓN", 1),
(8, "AUDITORÍA Y CONTROL INTERNO", 4),
(9, "BANCA Y FINANZAS", 3),
(10, "CALIDAD Y SERVICIO AL CLIENTE", 2),
(11, "CIENCIAS BÁSICAS Y EXACTAS", 4),
(12, "CIENCIAS BIOLÓGICAS", 1),
(13, "CIENCIAS DE LA SALUD", 1),
(14, "CIENCIAS SOCIALES", 2),
(15, "COMERCIAL Y VENTAS", 1),
(16, "COMERCIO EXTERIOR Y RELACIONES INTERNACIONALES", 2),
(17, "COMUNICACIÓN Y PERIODISMO", 1),
(18, "CONTADURÍA, TESORERÍA Y CONTABILIDAD", 2),
(19, "DEPORTES Y EDUCACIÓN FÍSICA", 4),
(20, "DERECHO Y CIENCIAS POLÍTICAS", 2),
(21, "DISEÑO", 1),
(22, "ECOLOGÍA Y MEDIO AMBIENTE", 4),
(23, "ECONOMÍA", 3),
(24, "ECONOMÍA SOLIDARIA", 2),
(25, "EDUCACIÓN Y PEDAGOGÍA", 1),
(26, "GASTRONOMÍA", 3),
(27, "GESTIÓN HUMANA Y PSICOLOGÍA", 1),
(28, "HOTELERÍA Y TURISMO", 1),
(29, "HUMANIDADES, FILOSOFÍA Y TEOLOGÍA", 2),
(30, "IDIOMAS", 3),
(31, "INGENIERÍA", 1),
(32, "LOGÍSTICA E INVENTARIOS", 2),
(33, "MANUALIDADES Y DECORACIÓN", 1),
(34, "MERCADEO Y PUBLICIDAD", 1),
(35, "NEGOCIACIÓN", 2),
(36, "PRODUCCIÓN Y OPERACIÓN", 2),
(37, "SEGURIDAD Y PROTECCIÓN", 2),
(38, "SISTEMAS E INFORMÁTICA", 1),
(39, "TELECOMUNICACIONES", 1),
(40, "VETERINARIA Y ZOOTECNIA", 4);

INSERT INTO profesor VALUES
(1, "Ascensión", "Fabregat Ródenas", "68478144", "F", "1985-12-25", "i201871809@tesla.com", "911873993", "Av. Hipólito Vallejo # 4"),
(2, "Matías Álvaro", "Egea Morillo", "56285418", "M", "1987-05-15", "i201960958@tesla.com", "992297065", "Av. Pedro Zaragoza # 335"),
(3, "Natividad", "Segovia Feijoo", "25398261", "F", "1986-07-26", "i202135079@tesla.com", "957401359", "Urb. Lorenzo Nieves # 14"),
(4, "Albina", "Arregui Tejera", "61238438", "F", "1988-11-21", "i202210946@tesla.com", "962116124", "Cl. Alejandra Tafoya # 26"),
(5, "Sandalio", "Cerezo Cuenca", "73703469", "M", "1985-02-23", "i202129448@tesla.com", "974325791", "Av. Alexander Briseño # 02"),
(6, "José María", "Farré Castrillo", "90480069", "M", "1982-05-28", "i202005724@tesla.com", "993847635", "Av. Delfina Arredondo # 390"),
(7, "Teófila", "Rubio Atienza", "76778511", "F", "1979-09-15", "i202284442@tesla.com", "991128503", "Cl. Valentino Varela # 81"),
(8, "Marc", "Ruíz Salazar", "28353699", "M", "1975-01-16", "i201964716@tesla.com", "940303971", "Jr. Abril Valdez # 4"),
(9, "Jose Chuy", "Cases Vaquero", "36788070", "M", "1981-09-12", "i201836439@tesla.com", "925235859", "Urb. Máximo Olvera # 32570"),
(10, "Odalys", "Izaguirre García", "43934856", "F", "1986-10-17", "i201681523@tesla.com", "948828637", "Jr. Josefa De La Rosa # 29"),
(11, "Gracia", "Marcos Ricart", "16362659", "F", "1985-06-15", "i201834085@tesla.com", "957627567", "Av. Lautaro Castellanos # 4"),
(12, "Georgina", "Mateo Bermúdez", "62033342", "F", "1991-04-18", "i201820458@tesla.com", "984672681", "Jr. Alejandro Saavedra # 18"),
(13, "Cristian", "Palma Infante", "54104048", "M", "1987-10-30", "i201562687@tesla.com", "996731418", "Urb. Ashley Cabán # 4"),
(14, "Maura", "Gordillo Fernadez", "42438359", "F", "1975-12-19", "i201967049@tesla.com", "985193955", "Av. Violeta Menéndez # 66"),
(15, "Fátima", "del Jove Aroni", "43749060", "F", "1979-10-21", "i202066944@tesla.com", "963155204", "Av. Mario Escalante # 4660"),
(16, "Juan Luis", "Borja Barrio", "86772059", "M", "1981-08-22", "i201756168@tesla.com", "936949950", "Jr. Paula Ferrer # 02"),
(17, "Armando", "del Pons Lebrón", "78051930", "M", "1983-05-10", "i201646685@tesla.com", "937362099", "Jr. Regina Robledo # 46"),
(18, "Elena", "del Sierra Oviedo", "49240636", "F", "1986-03-19", "i201958946@tesla.com", "968627659", "Av. Josué Nazario # 90409"),
(19, "Esperanza", "Asensio Monreal", "44100383", "F", "1980-11-17", "i202048657@tesla.com", "906914554", "Jr. Emily Casares # 843"),
(20, "Herberto", "Calvo Llano", "89853999", "M", "1990-10-18", "i201781221@tesla.com", "959741589", "Cl. Julieta Araña # 39");

INSERT INTO turno VALUES
(1, "MAÑANA"),
(2, "TARDE"),
(3, "NOCHE");

INSERT INTO sede VALUES
(1, "AMAZONAS"),
(2, "ANCASH"),
(3, "APURIMAC"),
(4, "AREQUIPA"),
(5, "AYACUCHO"),
(6, "CAJAMARCA"),
(7, "CALLAO"),
(8, "CUSCO"),
(9, "HUANCAVELICA"),
(10, "HUANUCO"),
(11, "ICA"),
(12, "JUNÍN"),
(13, "LA LIBERTAD"),
(14, "LAMBAYEQUE"),
(15, "LIMA"),
(16, "LORETO"),
(17, "MADRE DE DIOS"),
(18, "MOQUEGUA"),
(19, "PASCO"),
(20, "PIURA"),
(21, "PUNO"),
(22, "SAN MARTÍN"),
(23, "TACNA"),
(24, "TUMBES"),
(25, "UCAYALI");

INSERT INTO administrador VALUES
(1, "Andrés", "Fuster Arregui", "18899492", "M", "1985-10-03", "i866474621@tesla.com", "935452995","Jr. Isabel Olivárez # 7", "i202115024", "tc6IX71"),
(2, "Reyna Fátima", "Verdú Nicolás", "90929968", "F", "1990-11-10", "i889447885@tesla.com", "960767737","Urb. Luciano Tello # 5785", "i20211815", "O4zPd0u"),
(3, "Amílcar", "Ávila Escalona", "85937814", "M", "1989-05-16", "i933535925@tesla.com", "912701160","Jr. Luana Villegas # 994", "i202110405", "B8y95pZ");

INSERT INTO estudiante VALUES
(1, "Eloy", "Recio Amorós", "70529054", "M", "2002-12-05", "i836256880@tesla.com", "929937142", "Jr. Mario Sanabria # 46"),
(2, "Emma", "Lamas Losada", "88014149", "F", "2003-05-12", "i263184428@tesla.com", "945126407", "Cl. Malena Sepúlveda # 50930"),
(3, "Gervasio", "Serra Almagro", "44741869", "M", "2001-10-18", "i394299625@tesla.com", "918078858", "Av. Montserrat Barreto # 113 Piso 80"),
(4, "Abigaíl Roxana", "Fuertes Sevilla", "33424293", "F", "2000-12-15", "i272508102@tesla.com", "910639666", "Cl. Elías Zamudio # 1 Piso 90"),
(5, "Ángeles", "Gallardo Blanca", "58810625", "F", "2003-08-16", "i156135399@tesla.com", "976942400", "Cl. Juan Sebastián Acosta # 8 Piso 4"),
(6, "Pía", "Carrera Salcedo", "69578866", "F", "2002-04-19", "i651369891@tesla.com", "996349944", "Jr. Camila Guzmán # 3"),
(7, "Donato", "Llorente Abad", "43378634", "M", "2001-10-17", "i969790791@tesla.com", "974190508", "Calle 13, Sector #24"),
(8, "Danilo", "Osuna Borrell", "12685607", "M", "2003-12-13", "i749685960@tesla.com", "996063151", "Urb. Carla Córdova # 86569 Piso 18"),
(9, "Florencio", "Lasa Ramis", "42499999", "M", "2000-11-24", "i502501469@tesla.com", "919774652", "Urb. Matthew Mesa # 8448"),
(10, "Abraham", "Blanch Juan", "18774159", "M", "2001-09-05", "i536068712@tesla.com", "941163110", "Jr. Sofía Salinas # 525 Piso 98");

INSERT INTO matricula VALUES
(1, "2022-11-05", 2, 1, 29, 1, 4),
(2, "2022-11-04", 1, 2, 119, 2, 4),
(3, "2022-11-03", 2, 3, 58, 3, 15),
(4, "2022-11-04", 3, 4, 47, 1, 15),
(5, "2022-11-05", 1, 5, 36, 3, 4),
(6, "2022-11-03", 2, 6, 25, 1, 15),
(7, "2022-11-02", 1, 7, 52 , 1, 8),
(8, "2022-11-02", 2, 8, 65, 2, 8),
(9, "2022-11-01", 3, 9, 74, 2, 4),
(10, "2022-11-05", 1, 10, 83, 3, 15);

INSERT INTO contrato VALUES
(1, "2022-11-25", 1, 1, 3499.99),
(2, "2021-10-29", 2, 2, 4449.99),
(3, "2021-01-18", 3, 3, 4499.99),
(4, "2021-02-19", 1, 4, 3799.99),
(5, "2020-09-17", 2, 5, 3999.99),
(6, "2022-05-18", 2, 6, 2499.99),
(7, "2021-09-15", 1, 7, 1499.99),
(8, "2022-09-14", 3, 8, 1899.99),
(9, "2021-04-18", 3, 9, 1999.99),
(10, "2020-05-19", 3, 10, 3899.99),
(11, "2021-09-18", 2, 11, 1899.99),
(12, "2019-08-17", 2, 12, 2599.99),
(13, "2018-07-19", 1, 13, 1799.99),
(14, "2017-09-16", 1, 14, 2799.99),
(15, "2020-11-15", 2, 15, 2899.99),
(16, "2020-12-15", 2, 16, 3599.99),
(17, "2019-01-16", 3, 17, 3399.99),
(18, "2018-03-17", 3, 18, 3799.99),
(19, "2017-03-18", 2, 19, 3099.99),
(20, "2017-03-20", 1, 20, 3499.99);

/*INSERT INTO materia_profesor VALUES
(1, 1),
(4, 1),
(9, 1),
(13, 2),
(21, 3),
(30, 4),
(37, 4),
(38, 4),
(26, 5),
(28, 6),
(29, 7),
(15, 8),
(16, 8),
(25, 9),
(3, 10);

INSERT INTO materia_estudiante VALUES
(1, 1),
(4, 1),
(9, 2),
(13, 2),
(21, 3),
(30, 3),
(37, 3),
(38, 4),
(26, 5),
(28, 6),
(29, 7),
(15, 7),
(16, 8),
(25, 9),
(3, 10);

INSERT INTO estudiante_profesor VALUES
(1, 1),
(1, 1),
(2, 1),
(2, 2),
(3, 3),
(3, 4),
(3, 4),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(7, 8),
(8, 8),
(9, 9),
(10, 10);


CONSULTAS 

SELECT * FROM carrera;
SELECT * FROM modalidad;
SELECT * FROM materia;
SELECT * FROM profesor;
SELECT * FROM turno;
SELECT * FROM sede;
SELECT * FROM administrador;
SELECT * FROM estudiante;
SELECT * FROM materia_profesor;
SELECT * FROM materia_estudiante;
SELECT * FROM estudiante_profesor;
SELECT * FROM matricula;
SELECT * FROM contrato;

*/
/*
select m.num_mat, m.fec_mat, concat(a.nom_adm,space(1),a.ape_adm), concat(e.nom_est,space(1),e.ape_est), c.des_car, t.des_tur, s.des_sed from matricula m 
	inner join administrador a on m.ide_adm = a.ide_adm
    inner join estudiante e on m.ide_est = e.ide_est
    inner join carrera c on m.ide_car = c.ide_car 
    inner join turno t on m.ide_tur = t.ide_tur
    inner join sede s on m.ide_sed = s.ide_sed
;

select c.num_con, c.fec_con, concat(a.nom_adm, space(1), a.ape_adm), concat(p.nom_pro, space(1), p.ape_pro), c.sue_con from contrato c
	inner join administrador a on c.ide_adm = a.ide_adm
    inner join profesor p on c.ide_pro = p.ide_pro
;
*/

-- select m.ide_mat,m.des_mat,md.des_mod from materia m inner join modalidad md on m.ide_mod=md.ide_mod;
-- select * from modalidad;

-- update materia set des_mat="FARMACOS",ide_mod=2 where ide_mat = 1;

SELECT * FROM administrador;