-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29-Abr-2024 às 03:30
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `sistemasdistribuidos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `candidato`
--

CREATE TABLE `candidato` (
  `idCandidato` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `candidato`
--

INSERT INTO `candidato` (`idCandidato`, `nome`, `email`, `senha`) VALUES
(3, 'Pedrooo', 'pedro@gmail.com', 1234);

-- --------------------------------------------------------

--
-- Estrutura da tabela `candidatocompetencia`
--

CREATE TABLE `candidatocompetencia` (
  `idCandidatoCompetencia` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `idCompetencia` int(11) NOT NULL,
  `tempo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `candidatovaga`
--

CREATE TABLE `candidatovaga` (
  `idCandidatoVaga` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `idVaga` int(11) NOT NULL,
  `visualizou` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `competencia`
--

CREATE TABLE `competencia` (
  `idCompetencia` int(11) NOT NULL,
  `competencia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `empresa`
--

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL,
  `razaoSocial` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `senha` int(11) NOT NULL,
  `cnpj` int(11) NOT NULL,
  `ramo` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `logincandidato`
--

CREATE TABLE `logincandidato` (
  `idLoginCandidato` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `token` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `vaga`
--

CREATE TABLE `vaga` (
  `idVaga` int(11) NOT NULL,
  `idEmpresa` int(11) NOT NULL,
  `faixaSalarial` double DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `vagacompetencia`
--

CREATE TABLE `vagacompetencia` (
  `idVagaCompetencia` int(11) NOT NULL,
  `idVaga` int(11) NOT NULL,
  `idCompetencia` int(11) NOT NULL,
  `tempo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `candidato`
--
ALTER TABLE `candidato`
  ADD PRIMARY KEY (`idCandidato`);

--
-- Índices para tabela `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  ADD PRIMARY KEY (`idCandidatoCompetencia`),
  ADD KEY `idCandidato` (`idCandidato`),
  ADD KEY `idCompetencia` (`idCompetencia`);

--
-- Índices para tabela `candidatovaga`
--
ALTER TABLE `candidatovaga`
  ADD PRIMARY KEY (`idCandidatoVaga`),
  ADD KEY `idCandidato` (`idCandidato`),
  ADD KEY `idVaga` (`idVaga`);

--
-- Índices para tabela `competencia`
--
ALTER TABLE `competencia`
  ADD PRIMARY KEY (`idCompetencia`);

--
-- Índices para tabela `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmpresa`);

--
-- Índices para tabela `logincandidato`
--
ALTER TABLE `logincandidato`
  ADD PRIMARY KEY (`idLoginCandidato`),
  ADD KEY `idCandidato` (`idCandidato`);

--
-- Índices para tabela `vaga`
--
ALTER TABLE `vaga`
  ADD PRIMARY KEY (`idVaga`),
  ADD KEY `idEmpresa` (`idEmpresa`);

--
-- Índices para tabela `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  ADD PRIMARY KEY (`idVagaCompetencia`),
  ADD KEY `idVaga` (`idVaga`),
  ADD KEY `idCompetencia` (`idCompetencia`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `candidato`
--
ALTER TABLE `candidato`
  MODIFY `idCandidato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  MODIFY `idCandidatoCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `candidatovaga`
--
ALTER TABLE `candidatovaga`
  MODIFY `idCandidatoVaga` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `competencia`
--
ALTER TABLE `competencia`
  MODIFY `idCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `logincandidato`
--
ALTER TABLE `logincandidato`
  MODIFY `idLoginCandidato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `vaga`
--
ALTER TABLE `vaga`
  MODIFY `idVaga` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  MODIFY `idVagaCompetencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `candidatocompetencia`
--
ALTER TABLE `candidatocompetencia`
  ADD CONSTRAINT `candidatocompetencia_ibfk_1` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`idCandidato`),
  ADD CONSTRAINT `candidatocompetencia_ibfk_2` FOREIGN KEY (`idCompetencia`) REFERENCES `competencia` (`idCompetencia`);

--
-- Limitadores para a tabela `candidatovaga`
--
ALTER TABLE `candidatovaga`
  ADD CONSTRAINT `candidatovaga_ibfk_1` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`idCandidato`),
  ADD CONSTRAINT `candidatovaga_ibfk_2` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`);

--
-- Limitadores para a tabela `logincandidato`
--
ALTER TABLE `logincandidato`
  ADD CONSTRAINT `idCandidato` FOREIGN KEY (`idCandidato`) REFERENCES `candidato` (`idCandidato`);

--
-- Limitadores para a tabela `vaga`
--
ALTER TABLE `vaga`
  ADD CONSTRAINT `vaga_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`);

--
-- Limitadores para a tabela `vagacompetencia`
--
ALTER TABLE `vagacompetencia`
  ADD CONSTRAINT `vagacompetencia_ibfk_1` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`),
  ADD CONSTRAINT `vagacompetencia_ibfk_2` FOREIGN KEY (`idCompetencia`) REFERENCES `competencia` (`idCompetencia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
