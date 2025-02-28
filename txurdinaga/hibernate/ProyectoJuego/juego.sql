-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2017 a las 14:00:42
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `juego`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `Id` int(6) NOT NULL,
  `Nombre` varchar(20) COLLATE ucs2_spanish2_ci NOT NULL,
  `Direccion` varchar(30) COLLATE ucs2_spanish2_ci NOT NULL,
  `Poblacion` varchar(20) COLLATE ucs2_spanish2_ci NOT NULL,
  `Telefono` int(15) NOT NULL,
  `NIF` varchar(30) COLLATE ucs2_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Id`, `Nombre`, `Direccion`, `Poblacion`, `Telefono`, `NIF`) VALUES
(1, 'Gari', 'Ornilla 3', 'Txurdinaga', 680546320, '45698745-X'),
(2, 'Garikoitz', 'Sta Isabel', 'Arrigorriaga', 654789321, '1235897-J'),
(3, 'Garik', 'Olabeaga', 'Basauri', 696321547, '87988587-Y'),
(4, 'Gary', 'Los burros', 'Basauri', 696369632, '5879241-J');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IdNumerico` int(6) NOT NULL,
  `Descripcion` varchar(30) COLLATE ucs2_spanish2_ci DEFAULT NULL,
  `StockActual` int(6) DEFAULT NULL,
  `StockMinimo` int(6) DEFAULT NULL,
  `PVP` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`IdNumerico`, `Descripcion`, `StockActual`, `StockMinimo`, `PVP`) VALUES
(1, 'Producto1', 1250, 10, 8),
(2, 'Producto2', 25, 15, 10),
(3, 'Producto3', 6, 1, 10),
(4, 'Producto4', 30, 12, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IdVenta` int(6) NOT NULL,
  `FechaVenta` date DEFAULT NULL,
  `IdCliente` int(6) DEFAULT NULL,
  `IdProducto` int(6) DEFAULT NULL,
  `Cantidad` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVenta`, `FechaVenta`, `IdCliente`, `IdProducto`, `Cantidad`) VALUES
(1, '2016-12-15', 1, 1, 13),
(2, '2016-12-12', 2, 2, 10),
(3, '2016-12-21', 3, 3, 30),
(4, '2016-12-26', 4, 4, 44);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IdNumerico`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IdVenta`),
  ADD KEY `IdProducto` (`IdProducto`),
  ADD KEY `IdCliente` (`IdCliente`),
  ADD KEY `IdProducto_2` (`IdProducto`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`IdProducto`) REFERENCES `productos` (`IdNumerico`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
