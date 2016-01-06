-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 05 Jan 2016 pada 19.35
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
-- Struktur dari tabel `jawaban`
--

CREATE TABLE IF NOT EXISTS `jawaban` (
  `ID_kategori` int(1) NOT NULL,
  `ID_soal` int(1) NOT NULL,
  `jawab` text NOT NULL,
  `score` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jawaban`
--

INSERT INTO `jawaban` (`ID_kategori`, `ID_soal`, `jawab`, `score`) VALUES
(1, 1, 'Bung Karno', 0),
(1, 1, 'Ki Hajar Dewantoro', 1),
(1, 1, 'Jusuf Kala', 0),
(1, 1, 'Moh. Hatta', 0),
(1, 2, '2 Mei', 1),
(1, 2, '10 November', 0),
(1, 2, '21 April', 0),
(1, 2, '1 Juni', 0),
(1, 3, 'SMK', 0),
(1, 3, 'MAN', 0),
(1, 3, 'STIE', 1),
(1, 3, 'SMAK', 0),
(1, 4, 'Ing ngarsa sung tuladha', 0),
(1, 4, 'Bhineka tunggal ika', 0),
(1, 4, 'Jer basuki mawa bea', 0),
(1, 4, 'Tut wuri handayani', 1),
(2, 1, 'diare', 0),
(2, 1, 'sakit gigi', 0),
(2, 1, 'rabun mata', 0),
(2, 1, 'sariawan', 1),
(2, 2, 'suntik insulin', 1),
(2, 2, 'cuci darah', 0),
(2, 2, 'cangkok hati', 0),
(2, 2, 'imunisasi', 0),
(2, 3, 'nyamuk chikunguya', 0),
(2, 3, 'nyamuk anopheles betina', 0),
(2, 3, 'nyamuk anopheles jantan', 0),
(2, 3, 'nyamuk aides aygepti', 1),
(2, 4, 'penyakit gondok', 1),
(2, 4, 'rabun mata', 0),
(2, 4, 'sariawan', 0),
(2, 4, 'hepatitis', 0),
(3, 1, 'Thomas Alfa Edison', 0),
(3, 1, 'Al Khawairsm', 0),
(3, 1, 'Albert Einstein', 0),
(3, 1, 'Alexander Graham Bell', 1),
(3, 2, 'rotasi', 1),
(3, 2, 'revolusi', 0),
(3, 2, 'umbra', 0),
(3, 2, 'penumbra', 0),
(3, 3, 'Hindia dan Atlantika', 0),
(3, 3, 'Pasifik dan Atlantika', 0),
(3, 3, 'Hindia dan Pasifik', 1),
(3, 3, 'Asia dan Australia', 0);

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
  `pertanyaan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `soal`
--

INSERT INTO `soal` (`ID_kategori`, `ID_soal`, `pertanyaan`) VALUES
(1, 1, 'Siapakah tokoh pelopor pendidikan Indonesia?'),
(3, 1, 'Penemu telepon pertama kali adalah...'),
(3, 2, 'Perputaran bumi pada porosnya disebut...'),
(3, 3, 'Letak Indonesia adalah diantara dua samudera, yaitu .... dan ...'),
(2, 1, 'Salah satu akibat dari kekurangan vitamin C adalah?'),
(2, 2, 'Penyakit diabetes dapat diatasi dengan cara?'),
(2, 3, 'Penyebab penyakit demam berdarah adalah...'),
(2, 4, 'Kekurangan zat yodium dapat mengakibatkan...'),
(1, 2, 'tanggal berapakah hari pendidikan nasional?'),
(1, 3, 'berikut pendidikan setara SMA, kecuali...'),
(1, 4, 'Semboyan pendidikan nasional adalah...');

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
