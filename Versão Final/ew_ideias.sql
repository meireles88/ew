-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 27-Jun-2014 às 21:30
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
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`) VALUES
(1, 'Cultura', 'Área da cultura', '2014-06-24', NULL),
(2, 'Turismo', 'Área do turismo', '2014-06-24', NULL),
(3, 'Desporto', 'Área do desporto', '2014-06-24', NULL),
(4, 'Mobilidade', 'Rede de transportes da cidade', '2014-06-24', NULL),
(5, 'Educação', 'Ensino e Educação', '2014-06-24', NULL),
(6, 'Ambiente', 'Meio ambiente', '2014-06-26', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(255) DEFAULT NULL,
  `module_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_group_module` (`module_oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `group`
--

INSERT INTO `group` (`oid`, `groupname`, `module_oid`) VALUES
(1, 'URegistado', NULL),
(2, 'Moderador', NULL),
(3, 'Administrador', NULL);

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

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoorcamental`
--

CREATE TABLE IF NOT EXISTS `grupoorcamental` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `grupoorcamental`
--

INSERT INTO `grupoorcamental` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`) VALUES
(1, '<= 50.000€', 'Conjunto dos projetos de valor igual ou inferior a 50.000€', '2014-06-24', '2014-06-24'),
(2, '> 50.000€', 'Conjunto dos projetos de valor superior a 50.000€', '2014-06-24', '2014-06-24');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ideia`
--

CREATE TABLE IF NOT EXISTS `ideia` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datainsercao` date DEFAULT NULL,
  `dataedicao` date DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `quantia` int(11) DEFAULT NULL,
  `aprovada` bit(1) DEFAULT NULL,
  `categoria_oid` int(11) DEFAULT NULL,
  `grupoorcamental_oid` int(11) DEFAULT NULL,
  `user_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_ideia_categoria` (`categoria_oid`),
  KEY `fk_ideia_grupoorcamental` (`grupoorcamental_oid`),
  KEY `fk_ideia_user` (`user_oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `ideia`
--

INSERT INTO `ideia` (`oid`, `titulo`, `descricao`, `datainsercao`, `dataedicao`, `rating`, `quantia`, `aprovada`, `categoria_oid`, `grupoorcamental_oid`, `user_oid`) VALUES
(1, 'Aplicação Móvel para turismo em Braga', 'Desenvolvimento de uma aplicação para dispositivos móveis que funcione como guia turístico.', '2014-06-24', NULL, 6, 10000, b'1', 2, 1, 3),
(2, 'Pavilhão Multiusos', 'Construção de um pavilhão que suporte diversas modalidades de desporto', '2014-06-24', NULL, 1, 100000, b'1', 3, 2, 3),
(3, 'Auditorio Municipal', 'Construção de um auditório municipal para teatro e outros fins.', '2014-06-24', NULL, 0, 60000, b'1', 1, 2, 4),
(4, 'Metropolitano de Braga', 'Criação de um metro em Braga', '2014-06-24', NULL, 2, 100000, b'1', 4, 2, 5),
(5, 'Oferta de Manuais Escolares', 'Oferecer manuais escolares aos alunos do ensino primário', '2014-06-24', NULL, 4, 40000, b'1', 5, 1, 5),
(6, 'Nova ligação de autocarro', 'Ligação de autocarro entre a UM e o centro', '2014-06-25', NULL, 0, 10000, b'1', 4, 1, 3),
(7, 'Criação de um centro de estudos público e gratuito', 'Um centro de estudos daria apoio a crianças e jovens estudantes da cidade', '2014-06-26', NULL, 0, 35000, b'1', 5, 1, 9),
(8, 'Contentores do Lixo', 'A cidade precisa de mais contentores do lixo. Colocar os sacos na rua para recolha degrada as imagem das ruas de Braga', '2014-06-26', NULL, 0, 5000, b'1', 6, 1, 9),
(9, 'Iluminação Campos da Rodovia', 'Os campos da rodovia deveriam ser iluminados até mais tarde.', '2014-06-27', NULL, 0, 1000, b'1', 3, 1, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `moduleid` varchar(255) DEFAULT NULL,
  `modulename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Extraindo dados da tabela `tag`
--

INSERT INTO `tag` (`oid`, `nome`) VALUES
(1, 'app'),
(2, 'guia turistico'),
(3, 'pavilhao desportivo'),
(4, 'modalidades desportivas'),
(5, 'teatro municipal'),
(6, 'auditorio'),
(7, 'metropolitano'),
(8, 'ensino primário'),
(9, 'livros'),
(10, 'manuais escolares'),
(11, 'autocarro'),
(12, 'tub'),
(13, 'centro de estudos'),
(14, 'apoio escolar'),
(15, 'contentores do lixo'),
(16, 'recolha de lixo'),
(17, 'iluminação'),
(18, 'campos da rodovia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag_ideia`
--

CREATE TABLE IF NOT EXISTS `tag_ideia` (
  `tagoid` int(11) NOT NULL,
  `ideiaoid` int(11) NOT NULL,
  PRIMARY KEY (`tagoid`,`ideiaoid`),
  KEY `FKtag_ideia975008` (`tagoid`),
  KEY `FKtag_ideia196265` (`ideiaoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tag_ideia`
--

INSERT INTO `tag_ideia` (`tagoid`, `ideiaoid`) VALUES
(1, 1),
(1, 7),
(2, 1),
(2, 7),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 5),
(9, 5),
(10, 5),
(11, 6),
(12, 6),
(15, 8),
(16, 8),
(17, 9),
(18, 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
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
  `data_ban` date DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_user_group` (`group_oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`oid`, `username`, `password`, `email`, `datanascimento`, `morada`, `foto`, `ip`, `datacriacao`, `dataultimoacesso`, `dataultimaedicao`, `group_oid`, `data_ban`) VALUES
(1, 'admin', 'admin', 'admin@admin.com', '1983-10-20', 'Rua da Junqueira', NULL, '0:0:0:0:0:0:0:1', '2014-06-24', '2014-06-27', '2014-06-24', 3, NULL),
(2, 'mod', 'mod', 'mod@mod.com', '1997-10-24', '', NULL, '0:0:0:0:0:0:0:1', '2014-06-24', '2014-06-27', '2014-06-24', 2, NULL),
(3, 'miguel', 'miguel', 'miguel@miguel.com', '2014-04-16', 'Rua da Liberdade', NULL, '0:0:0:0:0:0:0:1', '2014-06-24', '2014-06-26', NULL, 1, '2014-07-02'),
(4, 'pedro', 'pedro', 'pedro@pedro.com', '2014-06-13', 'Ribeirao', NULL, '0:0:0:0:0:0:0:1', '2014-06-24', '2014-06-26', NULL, 1, NULL),
(5, 'zita', 'zita', 'zita@zita.com', '1985-03-22', 'Aveiro', NULL, '0:0:0:0:0:0:0:1', '2014-06-24', '2014-06-24', NULL, 1, NULL),
(6, 'usrtest', 'usrtest', 'usrtest@gmail.com', '1985-06-04', 'Rua Francisco Garcia', NULL, NULL, '2014-06-25', NULL, NULL, 1, NULL),
(7, 'usrtest2', 'usrtest2', 'usrtest2@gmail.com', '1993-01-09', 'Rua Dr Rodrigues', NULL, NULL, '2014-06-25', NULL, NULL, 1, NULL),
(8, 'mario', 'mario', 'mario@gmail.com', '1988-06-21', 'Alto da Mourisca', NULL, NULL, '2014-06-26', NULL, NULL, 1, NULL),
(9, 'teresa', 'teresa', 'teresa.aa@hotmail.com', '1989-06-13', 'avenida general norton de matos', NULL, '0:0:0:0:0:0:0:1', '2014-06-26', '2014-06-26', '2014-06-26', 1, '2014-06-27'),
(10, 'Albertina', 'albertina', 'albertina@gmail.com', '1987-11-18', 'Rua do Souto, Braga', 'Screenshot 2014-06-27 16.35.11.png', '0:0:0:0:0:0:0:1', '2014-06-27', '2014-06-27', NULL, 1, NULL);

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

-- --------------------------------------------------------

--
-- Estrutura da tabela `voto`
--

CREATE TABLE IF NOT EXISTS `voto` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `ideia_oid` int(11) DEFAULT NULL,
  `user_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_voto_ideia` (`ideia_oid`),
  KEY `fk_voto_user` (`user_oid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Extraindo dados da tabela `voto`
--

INSERT INTO `voto` (`oid`, `ideia_oid`, `user_oid`) VALUES
(1, 1, 5),
(2, 2, 5),
(3, 4, 5),
(4, 5, 5),
(5, 1, 3),
(6, 4, 3),
(7, 5, 3),
(8, 1, 4),
(9, 5, 4),
(10, 1, 2),
(11, 1, 9),
(13, 5, 9),
(14, 1, 10);

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
  ADD CONSTRAINT `fk_group_module_group` FOREIGN KEY (`group_oid`) REFERENCES `group` (`oid`),
  ADD CONSTRAINT `fk_group_module_module` FOREIGN KEY (`module_oid`) REFERENCES `module` (`oid`);

--
-- Limitadores para a tabela `ideia`
--
ALTER TABLE `ideia`
  ADD CONSTRAINT `fk_ideia_categoria` FOREIGN KEY (`categoria_oid`) REFERENCES `categoria` (`oid`),
  ADD CONSTRAINT `fk_ideia_grupoorcamental` FOREIGN KEY (`grupoorcamental_oid`) REFERENCES `grupoorcamental` (`oid`),
  ADD CONSTRAINT `fk_ideia_user` FOREIGN KEY (`user_oid`) REFERENCES `user` (`oid`);

--
-- Limitadores para a tabela `tag_ideia`
--
ALTER TABLE `tag_ideia`
  ADD CONSTRAINT `FKtag_ideia196265` FOREIGN KEY (`ideiaoid`) REFERENCES `ideia` (`oid`),
  ADD CONSTRAINT `FKtag_ideia975008` FOREIGN KEY (`tagoid`) REFERENCES `tag` (`oid`);

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
  ADD CONSTRAINT `fk_voto_ideia` FOREIGN KEY (`ideia_oid`) REFERENCES `ideia` (`oid`),
  ADD CONSTRAINT `fk_voto_user` FOREIGN KEY (`user_oid`) REFERENCES `user` (`oid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
