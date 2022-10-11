drop database if EXISTS restau;
CREATE database restau;
use restau;


-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 13 oct. 2020 à 13:30
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ppe`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `articles` (
  `idarticle` int(3) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prix` float,
  PRIMARY KEY (`idarticle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'hamburger', '4.50');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'cheeseburger', '4.70');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'doublecheese', '6.00');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'noushiburger', '5.30');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'doublenoushi', '5.30');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'noushifish', '3.50');
INSERT INTO `articles` (`idarticle`, `nom`, `prix`) VALUES (NULL, 'noushichicken', '3.50');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idclient` int(3) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `date_naissance` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idclient`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `client` (`idclient`, `nom`, `prenom`, `email`, `date_naissance`, `tel`, `adresse`) VALUES (NULL, 'martin', 'melvin', 'm@gmail.com', '1999-03-12', '0102030405', '09 rue de paris');


-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idcommande` int(3) NOT NULL AUTO_INCREMENT,
  `date_commande` varchar(20) DEFAULT NULL,
  `idclient` int(3) NOT NULL,
  PRIMARY KEY (`idcommande`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
--
DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `idlignecommande` int(3) NOT NULL AUTO_INCREMENT,
  `idcommande` int(3) NOT NULL,
  `idarticle` int(3) NOT NULL,
  `qtecommande` int(3) NOT NULL,

  PRIMARY KEY (`idlignecommande`),
  KEY `idcommande` (`idcommande`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `role` varchar (10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tel` int(10) NOT NULL,
  `adresse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `users` (`iduser`, `role`, `username`, `email`, `password`, `tel`, `adresse`) VALUES (NULL, 'admin', 'Melvin', 'm@gmail.com', '123', '0102030405', '09 rue de paris');
INSERT INTO `users` (`iduser`, `role`, `username`, `email`, `password`, `tel`, `adresse`) VALUES (NULL, 'admin', 'franck', 'f@gmail.com', '123', '0102030405', '09 rue de treich');

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `email`, `mdp`, `role`) VALUES
(1, 'admin', 'admin', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
