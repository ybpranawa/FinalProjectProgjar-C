-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 04 Jan 2016 pada 18.54
-- Versi Server: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adupintar`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `friendships`
--

CREATE TABLE IF NOT EXISTS `friendships` (
  `me` varchar(100) NOT NULL,
  `friend` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `friendships`
--

INSERT INTO `friendships` (`me`, `friend`) VALUES
('inyas', 'nela'),
('inyas', 'yuna'),
('nela', 'yuna'),
('yuna', 'nela');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategorisoal`
--

CREATE TABLE IF NOT EXISTS `kategorisoal` (
  `ID_kategori` int(1) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategorisoal`
--

INSERT INTO `kategorisoal` (`ID_kategori`, `nama_kategori`) VALUES
(1, 'Pendidikan'),
(2, 'Kesehatan'),
(3, 'Sains');

-- --------------------------------------------------------

--
-- Struktur dari tabel `soal`
--

CREATE TABLE IF NOT EXISTS `soal` (
  `ID_kategori` int(1) NOT NULL,
  `ID_soal` int(5) NOT NULL,
  `pertanyaan` text NOT NULL,
  `jawaban_benar` text NOT NULL,
  `jawaban_salah1` text NOT NULL,
  `jawaban_salah2` text NOT NULL,
  `jawaban_salah3` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `soal`
--

INSERT INTO `soal` (`ID_kategori`, `ID_soal`, `pertanyaan`, `jawaban_benar`, `jawaban_salah1`, `jawaban_salah2`, `jawaban_salah3`) VALUES
(1, 1, 'Siapakah tokoh pelopor pendidikan Indonesia?', 'Ki Hajar Dewantara', 'RA Kartini', 'Jusuf Kala', 'Tri Rismaharini');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`username`, `password`, `name`) VALUES
('fendy', 'fendy', 'fendy'),
('inyas', 'inyas', 'inyas'),
('nela', 'nela', 'nela'),
('yuna', 'yuna', 'yuna');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friendships`
--
ALTER TABLE `friendships`
 ADD PRIMARY KEY (`me`,`friend`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
