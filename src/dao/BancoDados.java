-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27-Maio-2024 às 00:37
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
  `senha` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `candidato`
--

INSERT INTO `candidato` (`idCandidato`, `nome`, `email`, `senha`) VALUES
(5, 'UsuarioAtualizado', 'pedro@gmail.com', '12345'),
(9, 'Testeeeee', 'testeeee@gmail.com', 'carloss'),
(11, 'asdasdasdasdas', 'Eduardoooooo@gmail.com', 'carloss'),
(12, 'Carlos', 'testeee@gmail.com', 'carloss'),
(13, 'Teste Swal', 'testeSwal@gmail.com', 'carloss'),
(14, 'testeSwal', 'testeSwal2@gmail.com', 'carloss');

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
  `senha` varchar(11) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `ramo` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `empresa`
--

INSERT INTO `empresa` (`idEmpresa`, `razaoSocial`, `email`, `senha`, `cnpj`, `ramo`, `descricao`) VALUES
(2, 'asdasdasdasdasdasd', 'testeEmpresa@gmail.com', 'carloss', 'aaaaaaaaaaaaaa', 'aa', 'asdasdasd'),
(3, 'asdasdasdasdasd', 'asdasdasd@gmail.com', 'carloss', '11111111111111', 'aa', 'asdasdasd'),
(4, 'asdasdasdasd', 'testeCNPJ@gmail.com', 'carloss', '00000000000000', 'aa', 'asdasdasd'),
(5, 'aaaaaaaaaaaaa', 'aaaaaaa@gmail.com', 'carloss', '88888888888888', 'aa', 'assssssss');

-- --------------------------------------------------------

--
-- Estrutura da tabela `logincandidato`
--

CREATE TABLE `logincandidato` (
  `idLoginCandidato` int(11) NOT NULL,
  `idCandidato` int(11) NOT NULL,
  `token` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `logincandidato`
--

INSERT INTO `logincandidato` (`idLoginCandidato`, `idCandidato`, `token`) VALUES
(2, 5, '56df0385-3681-4572-b337-32a4ed84096c'),
(3, 5, 'c27f81ed-af46-4bc0-8090-6f1d320e74af'),
(4, 5, 'fbd34d47-c41d-4a0c-a76d-f95944baf1f6'),
(5, 5, '81c78510-9129-4bae-b862-341b36b60594'),
(6, 5, '404d6de6-2eb8-4dd7-bbaf-56210028a525'),
(7, 5, '3fd392dc-25e3-4fb9-8eae-7175890f2e5a'),
(8, 5, '9064cfae-b0c7-42cf-8df2-12e1bb365c3a'),
(9, 5, '41db86f2-8545-47af-982a-830cc2e9d16e'),
(10, 5, '642f4771-8092-4f92-a61c-dbcc97747acd'),
(11, 5, '0fd4e95f-e6f8-4f21-a388-ba945e1fe6f4'),
(12, 5, '159615db-38b5-4a99-bdac-158c519a7016');

-- --------------------------------------------------------

--
-- Estrutura da tabela `loginempresa`
--

CREATE TABLE `loginempresa` (
  `idLoginEmpresa` int(11) NOT NULL,
  `idEmpresa` int(11) NOT NULL,
  `token` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `loginempresa`
--

INSERT INTO `loginempresa` (`idLoginEmpresa`, `idEmpresa`, `token`) VALUES
(1, 1, '6c6ee77f-168c-4992-a8b9-64101ee5991b'),
(2, 1, '810e941a-c7a3-4c24-8020-a1e78de7e47f'),
(3, 1, 'e91721d4-0da1-4638-8f97-0cb4372be6a4'),
(4, 1, '59a25a6b-50f2-4d7a-8c1d-a2c6537a5cf3'),
(5, 1, '6dcc3ce6-3eed-4abd-ba79-d33a8a065901'),
(6, 1, '529f0982-ef61-49c4-b314-57832dbbaa50'),
(7, 1, 'ae6329aa-5c53-425f-8c72-dd307f3ebdc8'),
(8, 1, '9a6551fa-2d29-4d2a-902b-e31ff967fed1'),
(9, 1, '4c3b096c-4380-4cf6-b14e-55faf3659052'),
(10, 1, '45517e49-6083-46b5-ba24-256afb42c50e'),
(11, 1, '03faaf08-e5b3-4aed-b5af-a3a6683484c6'),
(14, 1, 'fba2e83b-4c2c-40ad-afc9-3535e63dad6a'),
(15, 1, '1a5a730a-8d6e-4b18-b5ed-79dddf50162b');

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
-- Índices para tabela `loginempresa`
--
ALTER TABLE `loginempresa`
  ADD PRIMARY KEY (`idLoginEmpresa`);

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
  MODIFY `idCandidato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `logincandidato`
--
ALTER TABLE `logincandidato`
  MODIFY `idLoginCandidato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de tabela `loginempresa`
--
ALTER TABLE `loginempresa`
  MODIFY `idLoginEmpresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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
