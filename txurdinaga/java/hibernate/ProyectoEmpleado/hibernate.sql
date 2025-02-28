-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2017 a las 13:02:24
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hibernate`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamentos`
--

CREATE TABLE `departamentos` (
  `dept_no` int(4) NOT NULL,
  `dnombre` varchar(15) COLLATE ucs2_spanish2_ci DEFAULT NULL,
  `loc` varchar(15) COLLATE ucs2_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `departamentos`
--

INSERT INTO `departamentos` (`dept_no`, `dnombre`, `loc`) VALUES
(1, 'departamento', 'txurdinaga'),
(2, 'MEDICINA', 'LEIOA'),
(3, 'MULTIPLATAFORMA', 'RETUERTO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `emp_no` int(4) NOT NULL,
  `apellido` varchar(15) COLLATE ucs2_spanish2_ci DEFAULT NULL,
  `oficio` varchar(10) COLLATE ucs2_spanish2_ci DEFAULT NULL,
  `dir` int(6) DEFAULT NULL,
  `fecha_alt` date DEFAULT NULL,
  `salario` int(6) DEFAULT NULL,
  `comision` int(6) DEFAULT NULL,
  `dept_no` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`emp_no`, `apellido`, `oficio`, `dir`, `fecha_alt`, `salario`, `comision`, `dept_no`) VALUES
(1, 'Diz', 'Alumno', 48620, '2016-12-13', 9999, 666, 1),
(2, 'Izarra', 'Alumno', 50444, '2016-12-09', 1000, 500, 2),
(4, 'Malmasin', 'Director', 85201, '2017-01-25', 3500, 1500, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`dept_no`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`emp_no`),
  ADD KEY `dept_no` (`dept_no`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`dept_no`) REFERENCES `departamentos` (`dept_no`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
