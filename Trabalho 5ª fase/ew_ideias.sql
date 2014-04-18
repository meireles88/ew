-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 18-Abr-2014 às 22:01
-- Versão do servidor: 5.6.14
-- versão do PHP: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `ew_ideias`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `oid` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`) VALUES
(1, 'Transportes', 'tudo sobre transportes', NULL, NULL),
(2, 'Natureza', 'Tudo sobre espaços verdes', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `oid` int(11) NOT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  `module_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_group_module` (`module_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `group`
--

INSERT INTO `group` (`oid`, `groupname`, `module_oid`) VALUES
(1, 'URegistados', 1),
(2, 'Moderadores', 6),
(3, 'Administradores', 8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `group_module`
--

CREATE TABLE IF NOT EXISTS `group_module` (
  `group_oid` int(11) NOT NULL,
  `module_oid` int(11) NOT NULL,
  PRIMARY KEY (`group_oid`,`module_oid`),
  KEY `fk_group_module_group` (`group_oid`),
  KEY `fk_group_module_module` (`module_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `group_module`
--

INSERT INTO `group_module` (`group_oid`, `module_oid`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 19),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 18),
(2, 19),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(3, 19);

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoorcamental`
--

CREATE TABLE IF NOT EXISTS `grupoorcamental` (
  `oid` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `grupoorcamental`
--

INSERT INTO `grupoorcamental` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`) VALUES
(1, 'até 10.000€', '', NULL, NULL),
(2, 'até 20.000€', '', NULL, NULL),
(3, 'até 50.000€', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ideia`
--

CREATE TABLE IF NOT EXISTS `ideia` (
  `oid` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `quantia` int(11) DEFAULT NULL,
  `aprovada` bit(1) DEFAULT NULL,
  `categoria_oid` int(11) DEFAULT NULL,
  `tag_oid` int(11) DEFAULT NULL,
  `grupoorcamental_oid` int(11) DEFAULT NULL,
  `user_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_ideia_categoria` (`categoria_oid`),
  KEY `fk_ideia_tag` (`tag_oid`),
  KEY `fk_ideia_grupoorcamental` (`grupoorcamental_oid`),
  KEY `fk_ideia_user` (`user_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ideia`
--

INSERT INTO `ideia` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`, `rating`, `quantia`, `aprovada`, `categoria_oid`, `tag_oid`, `grupoorcamental_oid`, `user_oid`) VALUES
(1, 'ideia 1', '', NULL, NULL, 0, NULL, b'0', NULL, NULL, NULL, 1),
(2, 'ideia 2', '', NULL, NULL, 0, NULL, b'0', NULL, NULL, NULL, 1),
(3, 'Plantar árvores', 'A rua X precisa de vegetação', NULL, NULL, 0, 6000, b'1', 2, NULL, 1, 4),
(4, 'Nova ligação TUB', 'Nova ligação entre a UM e Rodovia', NULL, NULL, 0, 11000, b'1', 1, NULL, 2, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `oid` int(11) NOT NULL,
  `moduleid` varchar(255) DEFAULT NULL,
  `modulename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `module`
--

INSERT INTO `module` (`oid`, `moduleid`, `modulename`) VALUES
(1, 'page7', 'Perfil'),
(2, 'page3', 'Utilizadores'),
(3, 'page9', 'Adicionar Ideia'),
(4, 'cru6', 'Votar'),
(5, 'page20', 'Ver autor'),
(6, 'page6', 'Moderação de Ideias'),
(7, 'page8', 'Editar Ideia'),
(8, 'page2', 'Gestão de Ideias'),
(9, 'page10', 'Editar Ideia'),
(10, 'page16', 'Editar Grupo Orçamental'),
(11, 'page15', 'Criar Grupo Orçamental'),
(12, 'page12', 'Gestão de Categorias e Grupos Orçamentais'),
(13, 'page13', 'Criar Categoria'),
(14, 'page14', 'Editar Categoria'),
(15, 'page17', 'Gestão de Utilizadores'),
(16, 'page18', 'Editar Utilizador'),
(17, 'area2', 'Área de Administração'),
(18, 'area4', 'Área de Moderação'),
(19, 'area3', 'Área Pessoal');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `oid` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `oid` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `morada` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `datacriacao` date DEFAULT NULL,
  `dataultimoacesso` date DEFAULT NULL,
  `dataultimaedicao` date DEFAULT NULL,
  `group_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_user_group` (`group_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`oid`, `username`, `password`, `email`, `datanascimento`, `morada`, `foto`, `ip`, `datacriacao`, `dataultimoacesso`, `dataultimaedicao`, `group_oid`) VALUES
(1, 'userteste', '123', 'userteste@uminho.pt', '1989-04-16', 'rua da universidade', 'upload/User/1/ideia.png', NULL, NULL, NULL, NULL, 1),
(2, 'modteste', '123', 'moderador@uminho.pt', '2014-04-14', 'rua sta cruz', NULL, NULL, NULL, NULL, NULL, 2),
(3, 'adminteste', '123', 'admin@uminho.pt', NULL, 'avenida da liberdade', NULL, NULL, NULL, NULL, NULL, 3),
(4, 'wolvz', '123', '', NULL, '', NULL, NULL, NULL, NULL, NULL, 1),
(5, 'rox', '123', '', NULL, '', NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_group`
--

CREATE TABLE IF NOT EXISTS `user_group` (
  `user_oid` int(11) NOT NULL,
  `group_oid` int(11) NOT NULL,
  PRIMARY KEY (`user_oid`,`group_oid`),
  KEY `fk_user_group_user` (`user_oid`),
  KEY `fk_user_group_group` (`group_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user_group`
--

INSERT INTO `user_group` (`user_oid`, `group_oid`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `voto`
--

CREATE TABLE IF NOT EXISTS `voto` (
  `oid` int(11) NOT NULL,
  `ideia_oid` int(11) DEFAULT NULL,
  `user_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_voto_ideia` (`ideia_oid`),
  KEY `fk_voto_user` (`user_oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `group`
--
ALTER TABLE `group`
  ADD CONSTRAINT `fk_group_module` FOREIGN KEY (`module_oid`) REFERENCES `module` (`oid`);

--
-- Limitadores para a tabela `group_module`
--
ALTER TABLE `group_module`
  ADD CONSTRAINT `fk_group_module_module` FOREIGN KEY (`module_oid`) REFERENCES `module` (`oid`),
  ADD CONSTRAINT `fk_group_module_group` FOREIGN KEY (`group_oid`) REFERENCES `group` (`oid`);

--
-- Limitadores para a tabela `ideia`
--
ALTER TABLE `ideia`
  ADD CONSTRAINT `fk_ideia_user` FOREIGN KEY (`user_oid`) REFERENCES `user` (`oid`),
  ADD CONSTRAINT `fk_ideia_categoria` FOREIGN KEY (`categoria_oid`) REFERENCES `categoria` (`oid`),
  ADD CONSTRAINT `fk_ideia_grupoorcamental` FOREIGN KEY (`grupoorcamental_oid`) REFERENCES `grupoorcamental` (`oid`),
  ADD CONSTRAINT `fk_ideia_tag` FOREIGN KEY (`tag_oid`) REFERENCES `tag` (`oid`);

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_group` FOREIGN KEY (`group_oid`) REFERENCES `group` (`oid`);

--
-- Limitadores para a tabela `user_group`
--
ALTER TABLE `user_group`
  ADD CONSTRAINT `fk_user_group_group` FOREIGN KEY (`group_oid`) REFERENCES `group` (`oid`),
  ADD CONSTRAINT `fk_user_group_user` FOREIGN KEY (`user_oid`) REFERENCES `user` (`oid`);

--
-- Limitadores para a tabela `voto`
--
ALTER TABLE `voto`
  ADD CONSTRAINT `fk_voto_user` FOREIGN KEY (`user_oid`) REFERENCES `user` (`oid`),
  ADD CONSTRAINT `fk_voto_ideia` FOREIGN KEY (`ideia_oid`) REFERENCES `ideia` (`oid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
