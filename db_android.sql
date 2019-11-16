-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16 Nov 2019 pada 05.40
-- Versi Server: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_android`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_data`
--

CREATE TABLE `tb_data` (
  `id` int(11) NOT NULL,
  `objekwisata` varchar(100) NOT NULL,
  `posisi` varchar(100) NOT NULL,
  `gajih` varchar(100) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `jarak` varchar(100) NOT NULL,
  `keramaian` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `nama_fhoto` varchar(100) NOT NULL,
  `fhoto` varchar(999) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_data`
--

INSERT INTO `tb_data` (`id`, `objekwisata`, `posisi`, `gajih`, `jenis`, `jarak`, `keramaian`, `alamat`, `latitude`, `longitude`, `nama_fhoto`, `fhoto`) VALUES
(2, 'Danau Biru', 'Rp.5000', 'wc, pendopo, tempat makan', 'Wisata Alam', '20km', 'ramai', 'jl.amuntai-kalua', '-2.3427011', '115.3149498', 'maulahyu', 'http://192.168.1.4/Android/pegawai/images/rn3t3v3s6srrdbhibcc5.png'),
(3, 'Mesjid Raya At-taqwa', 'Rp.0', 'wc', 'Wisata Religi', '5km', 'ramai', 'jl. a yani', '-2.4204404', '115.2524869', 'reza', ''),
(12, 'Candi Agung', 'Rp.5000', 'WC', 'WIsata Sejarak', 'jnjk', 'njknkj', 'nkjnkj', '-2.4136993', '115.246121', 'u778', 'http://192.168.1.4/Android/pegawai/images/90uzkbw7p6is5a3d2qgs.png'),
(60, 'Water Boom Melati', 'Rp.30000', 'wc, tempat makan, pendopo', 'Wisata Keluarga', '5km', 'Ramai', 'Jl. Abdul Ghani Majedi', '-2.4192105', '115.2504173', 'kolm', 'http://192.168.1.4//Android/pegawai/images/804f87fch3w65yfrn9r4.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_komentar`
--

CREATE TABLE `tb_komentar` (
  `id` int(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `komen` varchar(999) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_komentar`
--

INSERT INTO `tb_komentar` (`id`, `nama`, `komen`) VALUES
(1, 'Reza', 'Perbaiki Tampilan'),
(2, 'Karimi', 'Kok biaya untuk objek wisata candi agung gak sesuai harga yang tertera diaplikasi Rp.5k tapi kenyataan nya 10k, tolong di update data nya ... terimakasih'),
(4, 'Gaben', 'Dota yuk!!! SKIP'),
(5, 'Yusuf', 'GGWP'),
(6, 'nida', 'ya'),
(7, 'najih', 'hmmm'),
(8, 'tes', 'tes'),
(12, 'ufi', 'gambar gmbar'),
(13, 'adi', 'alamstah'),
(14, 'mau', 'lah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `uploadimagetoserver`
--

CREATE TABLE `uploadimagetoserver` (
  `id` varchar(100) NOT NULL,
  `image_patch` varchar(100) NOT NULL,
  `iamge_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_data`
--
ALTER TABLE `tb_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_komentar`
--
ALTER TABLE `tb_komentar`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_data`
--
ALTER TABLE `tb_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT for table `tb_komentar`
--
ALTER TABLE `tb_komentar`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
