-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema proyecto_final
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `proyecto_final` ;

-- -----------------------------------------------------
-- Schema proyecto_final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto_final` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `proyecto_final` ;

-- -----------------------------------------------------
-- Table `proyecto_final`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `paternal_name` VARCHAR(45) NOT NULL,
  `maternal_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(10) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role_id` INT NOT NULL,
  `register_date` DATETIME NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `proyecto_final`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `author` VARCHAR(250) NOT NULL,
  `editorial` VARCHAR(300) NOT NULL,
  `year` INT NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `image` VARCHAR(100) NOT NULL,
  `pages` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`summary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`summary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `street` VARCHAR(250) NOT NULL,
  `int_number` VARCHAR(10) NOT NULL,
  `ext_number` VARCHAR(10) NOT NULL,
  `neighborhood` VARCHAR(250) NOT NULL,
  `city` VARCHAR(250) NOT NULL,
  `state` VARCHAR(150) NOT NULL,
  `zip_code` INT(5) ZEROFILL NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`requisition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`requisition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total` DECIMAL(12,2) NOT NULL,
  `date` DATETIME NOT NULL,
  `summary_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `office_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_summary1_idx` (`summary_id` ASC),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  INDEX `fk_order_office1_idx` (`office_id` ASC),
  CONSTRAINT `fk_order_summary1`
    FOREIGN KEY (`summary_id`)
    REFERENCES `proyecto_final`.`summary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proyecto_final`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_office1`
    FOREIGN KEY (`office_id`)
    REFERENCES `proyecto_final`.`office` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`book_has_requisition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`book_has_requisition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` INT NOT NULL,
  `requisition_id` INT NOT NULL,
  INDEX `fk_book_has_requisition_requisition1_idx` (`requisition_id` ASC),
  INDEX `fk_book_has_requisition_book1_idx` (`book_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_book_has_requisition_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `proyecto_final`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_requisition_requisition1`
    FOREIGN KEY (`requisition_id`)
    REFERENCES `proyecto_final`.`requisition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`role` (`id`, `type`) VALUES (1, 'Administrador');
INSERT INTO `proyecto_final`.`role` (`id`, `type`) VALUES (2, 'Cliente');

COMMIT; START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (1, 'Admin', 'Admin', 'Admin', '0123456789', 'admin@admin.com', 'admin123', 1, '2015-12-25 12:00:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (2, 'Alejandro', 'Villanueva', 'Molina', '5544663377', 'alex@hotmail.com', 'alex1234', 2, '2015-12-26 14:30:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (3, 'Paola', 'Hernandez', 'Garcia', '5523645512', 'paola@hotmail.com', 'pao54321', 2, '2015-12-26 16:35:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (4, 'Jorge', 'Garcia', 'Corrales', '5532458156', 'jorge@gmail.com', 'jorge0110', 2, '2015-12-26 18:45:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (5, 'Luis', 'Estrada', 'Avila', '5510235845', 'luis_e@yahoo.com.mx', 'luis1992', 2, '2015-12-27 11:00:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (6, 'Sandra', 'Soltero', 'Cortes', '5510457812', 'sandra_sc@hotmail.com', 'sandra95', 2, '2015-12-27 12:10:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (7, 'Ivan', 'Resendis', 'Diaz', '5564983217', 'ivanresendis@gmail.com', 'ivan1212', 2, '2015-12-27 13:24:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (8, 'Jhonatan', 'Guzman', 'Arellano', '5532658145', 'jguzman@yahoo.com.mx', 'jhon4321', 2, '2015-12-27 15:37:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (9, 'Lorena', 'Argumedo', 'Lopez', '5585964572', 'lore_argumedo@gmail.com', 'lorena1234', 2, '2015-12-28 16:48:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (10, 'Alan', 'Hernandez', 'Hernandez', '5513467985', 'alan_hernandez@yahoo.com.mx', 'alan1991', 2, '2015-12-29 22:05:00', 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (11, 'Lizbeth', 'Arellano', 'Castillo', '5528391745', 'lizbeth_ac@gmail.com', 'liz02051994', 2, '2015-12-30 20:30:00', 1);

COMMIT; 
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (1, 'C# - Curso de programaci�n, 2� Edici�n', 'Francisco Javier Ceballos Sierra', 'Alfaomega Grupo Editor', 2009, 'Programaci�n orientada a objetos. Elementos del lenguaje. Sentencias de control. Clases de uso com�n. Matrices, cadenas y colecciones. Clases, espacios de nombres y estructuras. Operadores sobrecargados. Clases derivadas e interfaces. Tipos y m�todos gen�ricos. Excepciones. Ficheros. Estructuras din�micas. Algoritmos. Hilos. Introducci�n a las interfaces gr�ficas y aplicaciones para Internet. Ejercicios resueltos.', 'cchar_1.jpg', 820, 338.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (2, 'A Fondo C#', 'Tom Archer', 'McGraw-Hill', 2001, 'Esta completa obra proporciona una visi�n detallada de la arquitectura interna del nuevo y original lenguaje C#. Sum�rjase en este vanguardista lenguaje orientado a objetos y en sus par�metros de dise�o y construcci�n.', 'cchar_2.jpg', 400, 350.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (3, 'Pro C# 2010 and the .NET 4 Platform', 'Andrew Troelsen', 'Apress', 2010, 'The first edition of this book was released at the 2001 Tech-Ed conference in Atlanta, Georgia. At that time, the .NET platform was still a beta product, and in many ways, so was this book. This is not to say that the early editions of this text did not have merit�after all, the book was a 2002 Jolt Award finalist and it won the 2003 Referenceware Excellence Award. However, over the years that author Andrew Troelsen spent working with the common language runtime (CLR), he gained a much deeper understanding of the .NET platform and the subtleties of the C# programming language, and he feels that this fifth edition of the book is as close to a �final release� as he�s come yet.', 'cchar_3.jpg', 1752, 983.80);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (4, 'Programaci�n Orientada a Objetos con C++', 'Francisco Javier Ceballos Sierra', 'Alfaomega Grupo Editor', 2009, 'La programaci�n orientada a objetos (POO) es una de las t�cnicas m�s modernas de desarrollo que trata de disminuir el costo del software, aumentando la eficiencia y reduciendo el tiempo de espera. Por eso, donde la POO toma verdadera ventaja es en poder compartir y reutilizar el c�digo. Sin embargo, no debe pensarse que esta forma de programaci�n resuelve todos los problemas de una forma sencilla y r�pida. Para conseguir buenos resultados, es preciso dedicar un tiempo mayor al an�lisis y al dise�o; pero no ser� un tiempo perdido, ya que redundar� en el empleado en la realizaci�n de aplicaciones futuras. Existen varios lenguajes que permiten escribir un programa orientado a objetos y entre ellos se encuentra C++. Se trata de un lenguaje de programaci�n basado en el lenguaje C, estandarizado (ISO/IEC 14882:1998), ampliamente difundido, y con una biblioteca est�ndar C++ que lo ha convertido en un lenguaje universal, de prop�sito general, y ampliamente utilizado tanto en el �mbito profesional como en el educativo. Programaci�n orientada a objetos con C++ es un libro: Totalmente actualizado al est�ndar ISO/IEC 14882:1998, relativo al lenguaje C++ est�ndar. Con ejemplos claros y sencillos, f�ciles de entender, que ilustran los fundamentos de la programaci�n C++. Que le permitir� aprender programaci�n orientada a objetos. Que le ense�ar� a trabajar con estructuras din�micas de datos, Archivos, excepciones e hilos. Y con el que aprender� a desarrollar aplicaciones. Incluye un CD-ROM con todos los ejemplos realizados y con el software necesario para que el lector pueda reproducirlos durante el estudio.', 'cmas_1.jpg', 622, 338.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (5, 'C++ Como Programar', 'Harvery M. Deitel', 'Prentice Hall / Pearson', 2005, 'Una introducci�n completa y autorizada del c�digo activo de DEITEL? a C++, la programaci�n orientada a objetos (POO) y el dise�o orientado a objetos (D00) con UMLM 2 C++ es uno de los lenguajes de programaci�n orientada a objetos m�s populares. Esta nueva edici�n del libro de texto sobre C++ m�s utilizado en el mundo presenta una introducci�n a la programaci�n de juegos con las bibliotecas Ogre. Proporciona, adem�s, una cobertura bastante completa de la programaci�n orientada a objetos en C++, incluyendo varios ejemplos pr�cticos integrados: la clase LibroCalificaciones, la clase Tiempo, la clase Empleado y el Sistema ATM opcional de DOO/UMLTM 2. En los sitios Web www.deitel.com y wsvsv.pearsoneducacion.net/deitel encontrar� los ejemplos de c�digo del libro e informaci�n para profesores, estudiantes y profesionales. Asimismo, le recomendamos que d� un vistazo a los Centros de Recursos Deitel sobre C++ en: www.deitel.com/resourcecenters.html El CD incluido en este libro contiene Microsoft Visual C++Express Edition v material adicional en espa�ol.', 'cmas_2.jpg', 1211, 572.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (6, 'C++', 'Jesse Liberty, Rogers Cadenhead', 'Anaya Multimedia', 2011, 'C++, dise�ado con la intenci�n de agregarle al exitoso lenguaje de programaci�n C, mecanismos para la manipulaci�n de objetos, sigue siendo hoy en d�a el lenguaje m�s �til y vers�til utilizado en los desarrollos modernos. \n\nPara aprender C++ no necesita experiencia previa como programador. Este libro utiliza un intuitivo m�todo paso a paso, con proyectos pr�cticos para reforzar el aprendizaje. Un dato interesante es que podr� acceder a detalles de la pr�xima versi�n C++0x.\n\nEncontrar� las herramientas para trabajar en cualquier plataforma del mercado actual: ordenadores personales, servidores Linux y UNIX, pasando por los mainframes y los dispositivos m�viles. Se adjunta un CD-ROM con el c�digo fuente de los ejemplos y un compilador C++ e IDE para Windows, Mac y Linux.', 'cmas_3.jpg', 464, 844.37);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (7, 'Profesional CSS para dise�o WEB', 'Christopher Schmitt', 'Anaya Multimedia', 2008, 'Centrado en el aspecto de las mejores pr�cticas del desarrollo Web, este libro refleja los cambios en los procedimientos del desarrollo con CSS (Hojas de Estilo en Cascada). Incluye ejemplos de sitios Web reales en cada cap�tulo y proporciona trucos CSS f�cilmente asimilables y t�cnicas utilizadas en cada uno de los sitios espec�ficos. Los cap�tulos documentan el proceso de sus dise�adores de principio a fin y proporcionan pistas sobre c�mo superaron cada uno de los desaf�os particulares de cada sitio, as� como las formas en las que pensaron en el desarrollo de una manera diferente. Ofreci�ndonos una �til visi�n de dise�os CSS basados en est�ndares, proyectos a gran escala de nivel profesional, este imprescindible libro nos proporciona soluciones f�ciles de comprender para los problemas comunes y nos muestra una aproximaci�n inteligente al desarrollo efectivo de dise�os Web en CSS a un nivel profesional. Con este libro aprender�, entre otros aspectos, las mejores pr�cticas para el uso de XHTML con CSS, c�mo dise�ar el nuevo aspecto para un blog, los pros y los contras del dise�o de un sitio que cuenta con millones de usuarios, as� como trucos para superar algunos problemas de compatibilidad con navegadores.', 'css_1.jpg', 336, 379.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (8, 'Curso de CSS', 'Christopher Schmitt', 'Anaya Multimedia', 2010, 'Las hojas de estilo en cascada, o CSS, ofrecen a los dise�adores una sintaxis sencilla y estandarizada que garantiza un control exhaustivo sobre la presentaci�n de p�ginas Web. CSS va m�s all� del dise�o Web tradicional para crear y controlar el aspecto de una p�gina Web cuando se imprime. Este libro va dirigido a dise�ador es Web y desarrolladores que tienen que enfrentarse a los problemas del dise�o con CSS. Este manual cubre los fundamentos b�sicos como el tratamiento de im�genes, elementos de p�gina, listas, v�nculos y navegaci�n, formularios y tablas, entre otros, as� como los aspectos propios de CSS que van desde la tipograf�a Web hasta dise�os de p�ginas. Encontrar�, adem�s, numerosos trucos y soluciones que le ser�n de gran utilidad. ', 'css_2.jpg', 800, 1399.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (9, 'Gu�a Completa de CSS3', 'Antonio Navajas Ojeda', 'Autoedici�n', 2012, 'CSS u hojas de estilo en cascada (en ingl�s Cascading Style Sheets) es un lenguaje usado para definir la presentaci�n de un documento estructurado escrito en HTML. El W3C (World Wide Web Consortium) es el encargado de formular la especificaci�n de las hojas de estilo que servir�n de est�ndar para los agentes de usuario o navegadores.\n\nActualmente, pese a que la especificaci�n 2.1 se aprob� recientemente, CSS3 ha venido desarroll�ndose desde 1999. Esta nueva especificaci�n viene con interesantes novedades que permitir�n hacer webs m�s elaboradas y din�micas, con mayor separaci�n entre estilos y contenidos. Dar� soporte a muchas necesidades de las webs actuales, sin tener que recurrir a trucos de dise�adores o lenguajes de programaci�n.\n\nEn esta completa gu�a, el autor nos muestra el uso de las principales caracter�sticas que este lenguaje ofrece. De entre ellas, destacamos los nuevos selectores, los pseudo elementos, las pseudo clases y toda una serie de efectos est�ticos que abarcan desde la tipograf�a a las transformaciones 3D.', 'css_3.jpg', 63, 199.90);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (10, 'Compendium HTML', 'Gunter Born', 'S.A. Marcombo', 2001, 'Esta obra le ofrecer� todos los conocimientos para el uso de HTML hasta la versi�n 4.0. Y no s�lo eso; tambi�n encontrar� temas perif�ricos como la programaci�n de Scripts, gr�ficos, XML, incluyendo las aplicaciones correspondientes como VML y WAP/WML, HTML din�mico y dise�o en la web. Las �reas tem�ticas del libro se han d ividido en seis partes independientes con las que podr� familiarizarse con la materia. La parte de referencia contiene informaciones de r�pido acceso sobre los diferentes comandos HTML y otras funciones.El CD contiene todos los ejemplos del libro, los navegadores en las versiones actuales de Microsfot y Netscape, as� com informaciones adicionales de gran utilidad, especificaciones y herramientas.', 'html_1.jpg', 976, 999.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (11, 'La Biblia de HTML', 'Francisco Charte Ojeda', 'Anaya Multimedia', 2004, 'Internet, y particularmente lo que conocemos como la Web, se han hecho tan omnipresentes que incluso se ha llegado a decir que lo que no aparece en ella no existe. Tener presencia en la Red es relativamente barato y sencillo, de tal manera que cualquier persona con las herramientas de dise�o adecuadas puede crear una web si n muchos problemas.HTML es el lenguaje por excelencia para la creaci�n de sitios web: conocerlo le permitir� no s�lo producir documentos totalmente personales, sino tambi�n ser capaz de mejorar aquellas p�ginas que hubiera generado previamente con un editor HTML.Este manual se convierte en la gu�a de aprendizaje fundamental para todos aquellos que, con independencia de su nivel de conocimientos inicial, quieran adentrarse en la creaci�n de sus propias p�ginas web, utilizando para ello el lenguaje HTML, hojas de estilo CSS, guiones en JavaScript, gr�ficos, p�ginas de servidor y otros elementos usuales. Progresivamente descubrir� otros conceptos clave, como el dise�o de p�ginas compatibles con todos los sistemas operativos y navegadores.Incluye CD-ROM con los ejemplos desarrollados a lo largo del libro, as� como diferentes herramientas que le ser�n de gran utilidad.', 'html_2.jpg', 1038, 1200.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (12, 'USERS: HTML 5: Entienda el cambio, aproveche su potencial', 'Dami�n De Luca', 'Autoedici�n', 2011, 'Adquirir el conocimiento de un nuevo lenguaje, o de una actualizaci�n importante de uno que ya conocemos, nos enfrenta a la necesidad de buscar informaci�n. En el caso de HTML5, buena parte de la informaci�n se encuentra en idioma ingl�s, por tal raz�n, contar con obras que aborden esta tem�tica en castellano son bienvenidas para aquellos que desean aprender.\nHTML 5, Entienda el cambio, aproveche su potencial, es una obra escrita por Dami�n De Luca, que nos introduce en el mundo del HTML5 mostr�ndonos su evoluci�n y recorriendo cada una de sus caracter�sticas. Partiendo de la base te�rica y analizando diferentes ejemplos pr�cticos, el libro nos sumerge en el mundo de HTML5 para que podamos comenzar a ser parte de este cambio que se est� gestando en el mundo del desarrollo Web.\nEntre los temas que se incluyen en este libro se destaca la recorrida por la evoluci�n de los est�ndares web, un repaso por las novedades que incorpora HTML5, sem�ntica, multimedia y formulario. Tambi�n hay un espacio para el trabajo con estilos con CSS 2.1 y con CSS3. Un lugar especial ocupa todo lo relacionado con la interacci�n con las nuevas APIs, el uso de canvas, geolocalizaci�n, WebGL, almacenamiento local y muchas otras caracter�sticas que est�n revolucionando la Web.', 'html_3.jpg', 320, 349.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (13, 'JAVA 6: Las bases del Lenguaje y de la Programaci�n Objeto', 'Thierry Groyssard', 'ENI', 2010, 'Este libro es un extracto del libro \\\" JAVA 6- Los fundamentos del lenguaje Java\\\" editado en la colecci�n Recursos Inform�ticos de Ediciones ENI. Se dirige a un p�blico de desarrolladores principiantes que quieren adquirir conocimientos b�sicos sobre el lenguaje Java, detallando la implementaci�n de variables y estructur as de controles elementales. A continuaci�n, le permitir� iniciarse en la programaci�n de objetos, detallando c�mo poner en marcha las clases de objetos, los atributos, los m�todos.', 'java_1.jpg', 165, 179.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (14, 'Como Programar en JAVA', 'Harvey M. Deitel', 'Prentice Hall / Pearson', 2008, 'Una introducci�n completa y autorizada del c�digo activo de Deitel a la programaci�n orientada a objetos, con la nueva edici�n Java Standard Edition 6, JDBC 4, Java Server Faces y Servicios Web.�Java es el lenguaje de programaci�n orientada a objetos m�s popular, con cinco millones de desarrolladores!Esta nueva edici�n del libro de texto sobre Java m�s utilizado en el mundo emplea un m�todo anticipado para las clases y objetos. Incluye tambi�n una cobertura completa de la programaci�n orientada a objetos en Java, para lo cual presenta varios ejemplos pr�cticos integrados: la clase Tiempo, la clase Empleado, la clase LibroCalificaciones, un ejemplo pr�ctico opcional de DOO/UML 2 con el ATM, el ejemplo pr�ctico opcional de GUI y gr�ficos, un libro de direcciones controlado por base de datos: una libreta de direcciones que utiliza controles JSF habilitados para AJAX para mostrar un nombre y una direcci�n en un Mapa de Google, y un sistema de reservaciones de una aerol�nea que utiliza servicios Web.', 'java_2.jpg', 1280, 800.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (15, 'JAVA 2 Curso de Programacion', 'Francisco Javier Ceballos', 'RA-MA', 2010, 'Hace pocos a�os quiz�s �Java� nos tra�a a la mente una taza de caf�. �Por qu� una taza de caf�? Seguramente por las muchas que se tomaron sus creadores. De hecho la taza de caf� ha pasado a ser su logotipo. Hoy en d�a, cualquiera que haya tenido contacto con una p�gina Web tiene otro concepto; sabe que Java es un lenguaje de programaci�n orientado a objetos introducido por Sun Microsystems (adquirida por Oracle en 2009) cuyas caracter�sticas lo sit�an, junto con Microsoft Visual Studio .Net, en el producto ideal para desarrollar programas para la Web. Pero con Java, no s�lo se pueden escribir programas para la Web, sino que es un lenguaje de programaci�n orientado a objetos que tambi�n permite desarrollar aplicaciones de uso general. Por lo tanto, Java le permitir� crear programas para su uso personal, para su grupo de trabajo, para una empresa, aplicaciones distribuidas a trav�s de Internet, aplicaciones de bases de datos, p�ginas Web y otras muchas cosas. Java 2 - Curso de programaci�n es un libro: - Totalmente actualizado a las nuevas caracter�sticas de Java 2. - Con ejemplos claros y sencillos, f�ciles de entender, que ilustran los fundamentos de la programaci�n Java. - Que le permitir� aprender programaci�n orientada a objetos. - Que le ense�ar� a trabajar con estructuras din�micas de datos, con ficheros, con excepciones y con hilos. - Con el que aprender� a desarrollar aplicaciones. - Y que le introducir� en el dise�o de interfaces gr�ficas, en las tareas de agregar applets a sus p�ginas Web, y en poner aplicaciones denominadas servlets en un servidor a disposici�n de los clientes del mismo. - Todo lo expuesto tiene continuaci�n en mi otro libro Java 2 � Interfaces gr�ficas y aplicaciones para Internet. Podr� descargarse de www.ra-ma.es, en la p�gina Web correspondiente al libro, un CD-ROM con los ejemplos realizados, con los ap�ndices, as� como el software necesario para que el lector pueda reproducirlos durante el estudio. ', 'java_3.jpg', 820, 999.99);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (16, 'JavaScript y JQuery', 'David Sawyer Mcfarland', 'Anaya Multimedia', 2012, 'JavaScript es un lenguaje de programaci�n para interactuar con los navegadores, que proporciona a los sitios Web dinamismo e inmediatez. Todos los navegadores importantes del mercado contienen un int�rprete de JavaScript.Cuando aprenda a utilizar su biblioteca jQuery, obtendr� una programaci�n m�s sencilla y potente que sim plificar� la manera de interactuar con los documento HTML, manejar eventos, desarrollar animaciones y agregar interacci�n a las p�ginas Web.Este libro le ense�ar� las bases de la programaci�n con JavaScript. Hace �nfasis en jQuery lo que le ayudar� a liberarse de la complejidad de JavaScript y la naturaleza cambiante entre navegadores. Con este manual no tardar� en crear p�ginas Web, sofisticadas y divertidas, que funcionar�n como programas de escritorio, sin apenas programaci�n.', 'javascript_1.jpg', 560, 779.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (17, 'Domine JavaScript', 'Jos� L�pez Quijado', 'RA-MA', 2010, 'En sus manos tiene un trabajo muy elaborado y con una larga trayectoria editorial, sobre lo que necesita conocer acerca de JavaScript. La presente obra est�, como todos mis textos did�cticos, est� orientada con un enfoque eminentemente pr�ctico. Se ha evitado, en la medida de lo posible, las disquisiciones acad�micas, que p ueden ser muy interesantes en altos c�rculos universitarios pero que, en la pr�ctica, solo sirven para que los �rboles no nos dejen ver el bosque. Este libro est� orientado al lector que desea aprender a usar JavaScript, y a sacarle partido para crear sus propios documentos web, sabiendo lo qu� hace, c�mo lo hace y por qu� lo hace. Si usted no conoce JavaScript, y desea aprender desde lo m�s b�sico, encontrar� el texto muy c�modo, coloquial y amigable, sin dejar de ser exhaustivo y riguroso. Si ya conoce algo de JavaScript y desea ir m�s all�, podr� echar un vistazo r�pido a los primeros cap�tulos, y en seguida alcanzar� unos niveles de programaci�n propios de un webmaster experimentado.', 'javascript_2.jpg', 706, 779.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (18, 'JavaScript: La gu�a definitiva', 'David Flanagan', 'Anaya Multimedia', 2007, 'JavaScript es el lenguaje interpretado m�s utilizado, principalmente en la construcci�n de p�ginas Web, con una sintaxis muy semejante a Java y a C. Pero, al contrario que Java, no se trata de un lenguaje orientado a objetos propiamente dicho, sino que �ste est� basado en prototipos, ya que las nuevas clases se generan clon ando las clases base (prototipos) y extendiendo su funcionalidad. Este libro es un manual de referencia para el programador, con cap�tulos que explican todo lo que necesita saber para obtener el m�ximo partido de JavaScript, as� como pulir sus conocimientos y profundizar en el lenguaje. El manual le ofrece la posibilidad de analizar el entorno de la escritura de secuencia de comandos proporcionado por los exploradores web. Adem�s, encontrar� una referencia completa para el c�digo JavaScript en la parte del cliente y analiza la herencia de las API, la API DOM de Nivel 2 y los est�ndares emergentes, como el objeto XMLHttpRequest.', 'javascript_3.jpg', 1168, 1799.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (19, 'Python para todos', 'Ra�l Gonz�lez Duque', 'Autoedici�n', 2010, 'Python para todos� es un libro sobre programaci�n en Python escrito por Ra�l Gonz�lez Duque. Se trata de un tutorial de Python adecuado para todos los niveles y que puedes descargar totalmente gratis.\n\nEl tutorial de Python �Python para todos� se distribuye bajo licencia Creative Commons Reconocimiento 2.5 Espa�a, lo que supone que puedes distribuirlo, modificarlo, traducirlo a otros idiomas, � siempre que indiques el autor original, preferiblemente con un enlace a esta web: Tutorial de Python �Python para todos�', 'python_1.jpg', 160, 300.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (20, 'Learning Python', 'Mark Lutz', 'O Reilly & Associates', 2013, 'Get a comprehensive, in-depth introduction to the core Python language with this hands-on book. Based on author Mark Lutz\'s popular training course, this updated fifth edition will help you quickly write efficient, high-quality code with Python. It\'s an ideal way to begin, whether you\'re new to programming or a professional developer versed in other languages. Complete with quizzes, exercises, and helpful illustrations, this easy-to-follow, self-paced tutorial gets you started with both Python 2.7 and 3.3 - the latest releases in the 3.X and 2.X lines - plus all other releases in common use today. You\'ll also learn some advanced language features that recently have become more common in Python code. Explore Python\'s major built-in object types such as numbers, lists, and dictionaries Create and process objects with Python statements, and learn Python\'s general syntax model Use functions to avoid code redundancy and package code for reuse Organize statements, functions, and other tools into larger components with modules Dive into classes: Python\'s object-oriented programming tool for structuring code Write large programs with Python\'s exception-handling model and development tools Learn advanced Python tools, including decorators, descriptors, metaclasses, and Unicode processing.', 'python_2.jpg', 1600, 1299.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (21, 'Black Hat Python: Python Programming for Hackers and Pentesters', 'Justin Seitz', 'No Starch Press', 2014, 'When it comes to creating powerful and effective hacking tools, Python is the language of choice for most security analysts. But just how does the magic happen?\n\nIn Black Hat Python, the latest from Justin Seitz (author of the best-selling Gray Hat Python), you\'ll explore the darker side of Python\'s capabilities�writing network sniffers, manipulating packets, infecting virtual machines, creating stealthy trojans, and more. You\'ll learn how to:\n\nCreate a trojan command-and-control using GitHub\nDetect sandboxing and automate common malware tasks, like keylogging and screenshotting\nEscalate Windows privileges with creative process control\nUse offensive memory forensics tricks to retrieve password hashes and inject shellcode into a virtual machine\nExtend the popular Burp Suite web-hacking tool\nAbuse Windows COM automation to perform a man-in-the-browser attack\nExfiltrate data from a network most sneakily\nInsider techniques and creative challenges throughout show you how to extend the hacks and how to write your own exploits.\nWhen it comes to offensive security, your ability to create powerful tools on the fly is indispensable. Learn how in Black Hat Python.', 'python_3.jpg', 192, 700.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (22, 'Practical Ruby for System Administration', 'Andr� Ben Hamou', 'Springer', 2007, 'Within the pages of Practical Ruby for System Administration, you\'ll learn the Ruby way to construct files, tap into clouds of data, build domain-specific languages, perform network traffic analysis, and more. Coverage places equal emphasis on fundamental Ruby concepts as well as practical how-tos. Based on author Andr� Ben Hamou\'s own experiences working as a system administrator, this book will help you pick up practical tips on Ruby coding style, learn how to analyze and improve script performance, and make use of no-nonsense advice on scripting workflow, including testing, documentation, and version control.', 'ruby_1.jpg', 264, 795.80);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (23, 'Ruby on Rails', 'Bruce A. Tate, Curt Hibbs', 'Anaya Multimedia', 2007, 'Ruby on Rails, es una tecnolog�a framework de aplicaciones web de c�digo abierto escrito en el lenguaje de programaci�n Ruby. Trata de combinar la simplicidad con la posibilidad de desarrollar aplicaciones del mundo real escribiendo menos c�digo que con otros frameworks y con un m�nimo de configuraci�n. El lenguaje de progr amaci�n Ruby permite la metaprogramaci�n, de la cual Rails hace uso, lo que resulta en una sintaxis que muchos de sus usuarios encuentran muy legible. Con el libro que tiene en sus manos, ganar� una imagen global de c�mo se mantienen unidas las aplicaciones Rails. Ver� c�mo se a�ade de forma din�mica utilidades a todos los modelos de base de datos, llamados objetos Active Record, recorreremos el proceso de creaci�n de un proyecto sencillo, le mostraremos las que consideramos fundamentales, las que conforman los elementos m�s importantes y tambi�n estudiaremos con alg�n detalle las migraciones y Ajax. Este libro es para desarrolladores con experiencia pero nuevos en Rails y, posiblemente, en Ruby.', 'ruby_2.jpg', 224, 429.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (24, 'Metaprogramming Ruby: Program Like the Ruby Pros', 'Paolo Perrota', 'Pragmatic Bookshelf', 2012, 'Everyone in the Ruby world seems to be talking about metaprogramming�how you can use it to remove duplication in your code and write elegant, beautiful programs. Now you can get in on the action as well.\n\nThis book describes metaprogramming as an essential component of Ruby. Once you understand the principles of Ruby, including the object model, scopes, and eigenclasses, you�re on your way to applying metaprogramming both in your daily work and in your fun, after-hours projects.\n\nLearning metaprogramming doesn�t have to be difficult or boring. By taking you on a Monday-through-Friday workweek adventure with a pair of programmers, Paolo Perrotta helps make mastering the art of metaprogramming both straightforward and entertaining.', 'ruby_3.jpg', 296, 672.20);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (25, 'VHDL: El Arte de Programar Sistemas Digitales', 'David G. Maxinez', 'Cecsa', 2003, 'Actualmente en nuestro ambiente familiar y de trabajo nos encontramos rodeados de un sinf�n de aparatos electr�nicos. Sin embargo, todos ellos tienen un punto en com�n: sus microcomponentes electr�nicos, una aplicaci�n directa de la microelectr�nica. Por eso cada d�a es mayor la importancia de aprender c�mo programar esos diminutos circuitos integrados. El lenguaje m�s poderoso para programar este tipo de aplicaciones es precisamente VHDL (VHSIC Hardware Description Language). Esta obra brinda al lector un panorama completo sobre este lenguaje de descripci�n de hardware, considerado como la m�xima herramienta de dise�o por las industrias y universidades del mundo. El texto va dirigido principalmente a estudiantes y profesionales del �rea de ingenier�a el�ctrica, electr�nica y computaci�n. En la primera parte se introduce al lector a los dispositivos l�gicos programables y su campo de aplicaci�n. En la segunda, se expone la estructura b�sica de este lenguaje, as� como las diferentes arquitecturas empleadas en la programaci�n. En la tercera y �ltima parte se realiza una s�ntesis de los principales circuitos digitales, proporcionando al lector un panorama completo y general del potencial de VHDL. Esta obra es sumamente atractiva por la actualidad de su informaci�n y por la did�ctica que presenta. Los temas se abordan de manera concisa y clara, adem�s se da gran importancia a la parte de aplicaciones.', 'vhdl_1.jpg', 352, 349.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (26, 'VHDL: Lenguaje para Sintesis y Modelado de Circuitos', 'Fernando Pardo Carpio', 'RA-MA', 2008, 'Los lenguajes de descripci�n hardware son los pilares sobre los que se asienta la fuerte evoluci�n que el dise�o electr�nico digital ha venido sufriendo durante los �ltimos a�os, por lo que el VHDL ha emergido como est�ndar en la industria convirti�ndose en el m�s utilizado hoy en d�a. El objetivo de este libro no es �nica mente el de presentar el lenguaje y su sintaxis, sino tambi�n el de introducir la metodolog�a de trabajo inherente al lenguaje, ya que se trata del flujo de dise�o actual de circuitos digitales. Adem�s, se centra en las dos grandes �reas de aplicaci�n del VHDL: la simulaci�n y la s�ntesis autom�tica de circuitos. - El presente manual se compone de 13 cap�tulos y 3 anexos, donde se incluyen los temas siguientes: - Metodolog�a y posibilidades en la descripci�n del dise�o electr�nico. - Lenguajes de descripci�n hardware. - Introducci�n y sintaxis del lenguaje. - Estilos de descripci�n: estructural, flujo de datos y algor�tmica. - Bibliotecas, Paquetes y Unidades. Conceptos avanzados. - Simulaci�n y modelado. - S�ntesis autom�tica de circuitos. - VHDL en la pr�ctica. Ejemplos y ejercicios resueltos. - VHDL, herramientas de CAD y tutoriales. - Evoluci�n del lenguaje y el nuevo est�ndar VHDL 2008. La estructura y contenido de esta obra est�n basados en varios a�os de experiencia en la ense�anza del VHDL y dise�o digital, por lo que el principal objetivo perseguido por los autores es su car�cter did�ctico y pedag�gico, sin olvidar que tambi�n va dirigido a los ingenieros que actualmente empiezan a incorporar estas t�cnicas de dise�o a su entorno laboral. En esta tercera edici�n se han a�adido nuevos elementos del lenguaje y ejemplos para dar cobertura a los importantes cambios y mejoras introducidos en el est�ndar de VHDL 2008. Tambi�n se han incorporado los �ltimos avances en dispositivos de l�gica programable, pues sigue siendo un �rea de fuerte evoluci�n y cambio. El libro contiene material adicional que podr� descargarse accediendo a la ficha del libro en www.ra-ma.es. Este material incluye los ejemplos desarrollados en la obra, as� como software de simulaci�n VHDL, y enlaces a las herramientas de simulaci�n y s�ntesis descritas en los tutoriales del libro.', 'vhdl_2.jpg', 308, 439.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (27, 'VHDL: Lenguaje para Sintesis y Modelado de Circuitos. 3a Edici�n Actualizada', 'Fernando Pardo Carpio', 'RA-MA', 2011, 'Los lenguajes de descripci�n hardware son los pilares sobre los que se asienta la fuerte evoluci�n que el dise�o electr�nico digital ha venido sufriendo durante los �ltimos a�os, por lo que el VHDL ha emergido como est�ndar en la industria convirti�ndose en el m�s utilizado hoy en d�a. El objetivo de este libro no es �nica mente el de presentar el lenguaje y su sintaxis, sino tambi�n el de introducir la metodolog�a de trabajo inherente al lenguaje, ya que se trata del flujo de dise�o actual de circuitos digitales. Adem�s, se centra en las dos grandes �reas de aplicaci�n del VHDL: la simulaci�n y la s�ntesis autom�tica de circuitos. - El presente manual se compone de 13 cap�tulos y 3 anexos, donde se incluyen los temas siguientes: - Metodolog�a y posibilidades en la descripci�n del dise�o electr�nico. - Lenguajes de descripci�n hardware. - Introducci�n y sintaxis del lenguaje. - Estilos de descripci�n: estructural, flujo de datos y algor�tmica. - Bibliotecas, Paquetes y Unidades. Conceptos avanzados. - Simulaci�n y modelado. - S�ntesis autom�tica de circuitos. - VHDL en la pr�ctica. Ejemplos y ejercicios resueltos. - VHDL, herramientas de CAD y tutoriales. - Evoluci�n del lenguaje y el nuevo est�ndar VHDL 2008. La estructura y contenido de esta obra est�n basados en varios a�os de experiencia en la ense�anza del VHDL y dise�o digital, por lo que el principal objetivo perseguido por los autores es su car�cter did�ctico y pedag�gico, sin olvidar que tambi�n va dirigido a los ingenieros que actualmente empiezan a incorporar estas t�cnicas de dise�o a su entorno laboral. En esta tercera edici�n se han a�adido nuevos elementos del lenguaje y ejemplos para dar cobertura a los importantes cambios y mejoras introducidos en el est�ndar de VHDL 2008. Tambi�n se han incorporado los �ltimos avances en dispositivos de l�gica programable, pues sigue siendo un �rea de fuerte evoluci�n y cambio. El libro contiene material adicional que podr� descargarse accediendo a la ficha del libro en www.ra-ma.es. Este material incluye los ejemplos desarrollados en la obra, as� como software de simulaci�n VHDL, y enlaces a las herramientas de simulaci�n y s�ntesis descritas en los tutoriales del libro.', 'vhdl_3.jpg', 310, 479.00);

COMMIT;


 
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`summary` (`id`, `type`) VALUES (1, 'Pendiente');
INSERT INTO `proyecto_final`.`summary` (`id`, `type`) VALUES (2, 'Entregado');

COMMIT; START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (1, 'Insurgentes Sur', 'Av. de los Insurgentes Sur', '10-A', '319', 'Hip�dromo', 'Cuauht�moc', 'Ciudad de M�xico', 06100);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (2, 'Polanco', 'Av. Ejercito Nacional', '15-B', '843', 'Granada', 'Miguel Hidalgo', 'Ciudad de M�xico', 11520);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (3, 'Ciudad Universitaria', 'Av Universidad', 'S/N', '3000', 'Cd. Universitaria', 'Coyoac�n', 'Ciudad de M�xico', 04510);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (4, 'Parque Tezontle', 'Av Canal de Tezontle', '12-G', '1512', 'Alfonso Ortiz Tirado', 'Iztapalapa', 'Ciudad de M�xico', 09020);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (5, 'Parque Delta', 'Av. Cuauht�moc', '24-L', '462', 'Narvarte Poniente', 'Benito Ju�rez', 'Ciudad de M�xico', 03020);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (6, 'Centro', 'Av. Francisto I. MAdero', '8-D', '5', 'Centro', 'Cuauht�moc', 'Ciudad de M�xico', 06000);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (7, 'Centro Las Am�ricas', 'Av. Central', '22-K', '1', 'Las Am�ricas', 'Ecatepec de Morelos', 'M�xico', 55076);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (8, 'Perisur', 'Anillo Perif�rico Sur', 'S/N', '4690', 'Jardines del Pedregal de San �ngel', 'Coyoac�n', 'Ciudad de M�xico', 04500);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (9, 'Portal Churubusco', 'Av. R�o Churubusco', '6-E', '583', 'Sector Popular', 'Iztapalapa', 'Ciudad de M�xico', 09060);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (10, 'Galer�as Coapa', 'Calzada del Hueso', 'I-51', '519', 'Acoxpa', 'Tlalpan', 'Ciudad de M�xico', 14300);

COMMIT;


 
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (1,1928.00,'2015-12-31 19:36:38',1,2,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (2,1348.99,'2015-12-31 19:37:16',1,2,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (3,618.00,'2015-12-31 19:38:36',1,3,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (4,3598.00,'2015-12-31 19:41:10',1,4,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (5,978.90,'2015-12-31 19:41:25',1,4,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (6,1799.99,'2015-12-31 19:42:02',1,5,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (7,2371.99,'2015-12-31 19:44:57',1,6,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (8,2478.99,'2015-12-31 19:45:36',1,7,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (9,2127.99,'2015-12-31 19:59:01',1,8,1,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (10,771.90,'2015-12-31 19:59:18',1,8,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (11,1548.89,'2015-12-31 19:59:53',1,9,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (12,2735.99,'2015-12-31 20:00:48',1,10,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (13,2362.79,'2015-12-31 20:01:25',1,11,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (14,1578.89,'2015-12-31 20:01:41',1,11,2,1);
INSERT INTO `requisition` (`id`,`total`,`date`,`summary_id`,`user_id`,`office_id`,`status`) VALUES (15,999.99,'2015-12-31 20:02:46',1,2,2,1);
COMMIT;


 
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (1,14,1);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (2,25,1);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (3,17,1);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (4,12,2);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (5,15,2);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (6,13,3);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (7,26,3);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (8,10,4);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (9,8,4);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (10,11,4);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (11,17,5);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (12,9,5);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (13,14,6);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (14,15,6);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (15,15,7);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (16,5,7);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (17,14,7);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (18,15,8);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (19,21,8);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (20,17,8);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (21,15,9);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (22,17,9);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (23,12,9);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (24,9,10);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (25,5,10);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (26,9,11);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (27,15,11);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (28,25,11);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (29,15,12);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (30,23,12);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (31,12,12);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (32,13,12);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (33,17,12);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (34,15,13);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (35,3,13);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (36,7,13);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (37,7,14);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (38,9,14);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (39,15,14);
INSERT INTO `book_has_requisition` (`id`,`book_id`,`requisition_id`) VALUES (40,15,15);
COMMIT;
