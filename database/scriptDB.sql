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
  `date` DATETIME NOT NULL,
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

-- -----------------------------------------------------
-- Data for table `proyecto_final`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`role` (`id`, `type`) VALUES (1, 'Administrador');
INSERT INTO `proyecto_final`.`role` (`id`, `type`) VALUES (2, 'Cliente');

COMMIT;


-- -----------------------------------------------------
-- Data for table `proyecto_final`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (1, 'Admin', 'Admin', 'Admin', '0123456789', 'admin@admin.com', 'admin', 1, DEFAULT, 1);
INSERT INTO `proyecto_final`.`user` (`id`, `name`, `paternal_name`, `maternal_name`, `phone_number`, `email`, `password`, `role_id`, `register_date`, `status`) VALUES (2, 'Alejandro', 'Villanueva', 'Molina', '5544663377', 'ale@ale.ale', 'ale', 2, DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `proyecto_final`.`book`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (1, 'C# - Curso de programación, 2ª Edición', 'Francisco Javier Ceballos Sierra', 'Alfaomega Grupo Editor', 2009, 'Programación orientada a objetos. Elementos del lenguaje. Sentencias de control. Clases de uso común. Matrices, cadenas y colecciones. Clases, espacios de nombres y estructuras. Operadores sobrecargados. Clases derivadas e interfaces. Tipos y métodos genéricos. Excepciones. Ficheros. Estructuras dinámicas. Algoritmos. Hilos. Introducción a las interfaces gráficas y aplicaciones para Internet. Ejercicios resueltos.', 'cchar_1.jpg', 820, 338.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (2, 'A Fondo C#', 'Tom Archer', 'McGraw-Hill', 2001, 'Esta completa obra proporciona una visión detallada de la arquitectura interna del nuevo y original lenguaje C#. Sumérjase en este vanguardista lenguaje orientado a objetos y en sus parámetros de diseño y construcción.', 'cchar_2.jpg', 400, 350.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (3, 'Pro C# 2010 and the .NET 4 Platform', 'Andrew Troelsen', 'Apress', 2010, 'The first edition of this book was released at the 2001 Tech-Ed conference in Atlanta, Georgia. At that time, the .NET platform was still a beta product, and in many ways, so was this book. This is not to say that the early editions of this text did not have merit—after all, the book was a 2002 Jolt Award finalist and it won the 2003 Referenceware Excellence Award. However, over the years that author Andrew Troelsen spent working with the common language runtime (CLR), he gained a much deeper understanding of the .NET platform and the subtleties of the C# programming language, and he feels that this fifth edition of the book is as close to a “final release” as he’s come yet.', 'cchar_3.jpg', 1752, 983.80);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (4, 'Programación Orientada a Objetos con C++', 'Francisco Javier Ceballos Sierra', 'Alfaomega Grupo Editor', 2009, 'La programación orientada a objetos (POO) es una de las técnicas más modernas de desarrollo que trata de disminuir el costo del software, aumentando la eficiencia y reduciendo el tiempo de espera. Por eso, donde la POO toma verdadera ventaja es en poder compartir y reutilizar el código. Sin embargo, no debe pensarse que esta forma de programación resuelve todos los problemas de una forma sencilla y rápida. Para conseguir buenos resultados, es preciso dedicar un tiempo mayor al análisis y al diseño; pero no será un tiempo perdido, ya que redundará en el empleado en la realización de aplicaciones futuras. Existen varios lenguajes que permiten escribir un programa orientado a objetos y entre ellos se encuentra C++. Se trata de un lenguaje de programación basado en el lenguaje C, estandarizado (ISO/IEC 14882:1998), ampliamente difundido, y con una biblioteca estándar C++ que lo ha convertido en un lenguaje universal, de propósito general, y ampliamente utilizado tanto en el ámbito profesional como en el educativo. Programación orientada a objetos con C++ es un libro: Totalmente actualizado al estándar ISO/IEC 14882:1998, relativo al lenguaje C++ estándar. Con ejemplos claros y sencillos, fáciles de entender, que ilustran los fundamentos de la programación C++. Que le permitirá aprender programación orientada a objetos. Que le enseñará a trabajar con estructuras dinámicas de datos, Archivos, excepciones e hilos. Y con el que aprenderá a desarrollar aplicaciones. Incluye un CD-ROM con todos los ejemplos realizados y con el software necesario para que el lector pueda reproducirlos durante el estudio.', 'cmas_1.jpg', 622, 338.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (5, 'C++ Como Programar', 'Harvery M. Deitel', 'Prentice Hall / Pearson', 2005, 'Una introducción completa y autorizada del código activo de DEITEL? a C++, la programación orientada a objetos (POO) y el diseño orientado a objetos (D00) con UMLM 2 C++ es uno de los lenguajes de programación orientada a objetos más populares. Esta nueva edición del libro de texto sobre C++ más utilizado en el mundo presenta una introducción a la programación de juegos con las bibliotecas Ogre. Proporciona, además, una cobertura bastante completa de la programación orientada a objetos en C++, incluyendo varios ejemplos prácticos integrados: la clase LibroCalificaciones, la clase Tiempo, la clase Empleado y el Sistema ATM opcional de DOO/UMLTM 2. En los sitios Web www.deitel.com y wsvsv.pearsoneducacion.net/deitel encontrará los ejemplos de código del libro e información para profesores, estudiantes y profesionales. Asimismo, le recomendamos que dé un vistazo a los Centros de Recursos Deitel sobre C++ en: www.deitel.com/resourcecenters.html El CD incluido en este libro contiene Microsoft Visual C++Express Edition v material adicional en español.', 'cmas_2.jpg', 1211, 572.00);
INSERT INTO `proyecto_final`.`book` (`id`, `title`, `author`, `editorial`, `year`, `description`, `image`, `pages`, `price`) VALUES (6, 'C++', 'Jesse Liberty, Rogers Cadenhead', 'Anaya Multimedia', 2011, 'C++, diseñado con la intención de agregarle al exitoso lenguaje de programación C, mecanismos para la manipulación de objetos, sigue siendo hoy en día el lenguaje más útil y versátil utilizado en los desarrollos modernos. \n\nPara aprender C++ no necesita experiencia previa como programador. Este libro utiliza un intuitivo método paso a paso, con proyectos prácticos para reforzar el aprendizaje. Un dato interesante es que podrá acceder a detalles de la próxima versión C++0x.\n\nEncontrará las herramientas para trabajar en cualquier plataforma del mercado actual: ordenadores personales, servidores Linux y UNIX, pasando por los mainframes y los dispositivos móviles. Se adjunta un CD-ROM con el código fuente de los ejemplos y un compilador C++ e IDE para Windows, Mac y Linux.', 'cmas_3.jpg', 464, 844.37);

COMMIT;


-- -----------------------------------------------------
-- Data for table `proyecto_final`.`summary`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`summary` (`id`, `type`) VALUES (1, 'Pendiente');
INSERT INTO `proyecto_final`.`summary` (`id`, `type`) VALUES (2, 'Entregado');

COMMIT;


-- -----------------------------------------------------
-- Data for table `proyecto_final`.`office`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto_final`;
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (1, 'Insurgentes Sur', 'Av. de los Insurgentes Sur', '10-A', '319', 'Hipódromo', 'Cuauhtémoc', 'Ciudad de México', 06100);
INSERT INTO `proyecto_final`.`office` (`id`, `name`, `street`, `int_number`, `ext_number`, `neighborhood`, `city`, `state`, `zip_code`) VALUES (DEFAULT, 'Polanco', 'Av. Ejercito Nacional', '15-B', '843', 'Granada', 'Miguel Hidalgo', 'Ciudad de México', 11520);

COMMIT;

