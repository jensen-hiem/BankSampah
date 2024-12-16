DROP TABLE IF EXISTS Harga;
DROP TABLE IF EXISTS TransaksiSampah;
DROP TABLE IF EXISTS Sampah;
DROP TABLE IF EXISTS SUK;
DROP TABLE IF EXISTS JenisSampah;
DROP TABLE IF EXISTS Transaksi;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS IbuBS;
DROP TABLE IF EXISTS Pengguna;
DROP TABLE IF EXISTS BSPusat;
DROP TABLE IF EXISTS Kelurahan;
DROP TABLE IF EXISTS Kecamatan;



CREATE TABLE Kecamatan (
    idKec SERIAL PRIMARY KEY,
    namaKec VARCHAR(20) NOT NULL
);

CREATE TABLE Kelurahan (
    idKel SERIAL PRIMARY KEY,
    namaKel VARCHAR(50) NOT NULL,
    idKec INT NOT NULL REFERENCES Kecamatan(idKec)
);

CREATE TABLE Pengguna (
    idPengguna INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nama VARCHAR(30),
    username VARCHAR(30)
);

CREATE TABLE IbuBS (
    idPengguna INT PRIMARY KEY REFERENCES Pengguna(idPengguna),
    password VARCHAR(10) NOT NULL
);

CREATE TABLE Member (
    idPengguna INT PRIMARY KEY REFERENCES Pengguna(idPengguna),
    noHP VARCHAR(20),
    alamat VARCHAR(50),
    email VARCHAR(30),
    idKel INT REFERENCES Kelurahan(idKel)
);

CREATE TABLE BSPusat (
    idBSPusat SERIAL PRIMARY KEY,
    noTelp CHAR(12) NOT NULL,
    alamat VARCHAR(50) NOT NULL,
    idKel INT NOT NULL REFERENCES Kelurahan(idKel)
);

CREATE TABLE Transaksi (
    idTransaksi SERIAL PRIMARY KEY,
    tanggal DATE NOT NULL,
    tipeTransaksi INT NOT NULL,
    idPengguna INT NOT NULL REFERENCES Pengguna(idPengguna),
    idBSPusat INT NOT NULL REFERENCES BSPusat(idBSPusat)
);

CREATE TABLE JenisSampah (
    idJenisSampah SERIAL PRIMARY KEY,
    namaJenis VARCHAR(20) NOT NULL
);

CREATE TABLE SUK (
    idSUK SERIAL PRIMARY KEY,
    namaSUK VARCHAR(10) NOT NULL
);

CREATE TABLE Sampah (
    idSampah INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    namaSampah VARCHAR(40) NOT NULL,
    idJenisSampah INT NOT NULL REFERENCES JenisSampah(idJenisSampah),
    idSUK INT NOT NULL REFERENCES SUK(idSUK)
);

CREATE TABLE TransaksiSampah (
    idTransaksi INT NOT NULL REFERENCES Transaksi(idTransaksi),
    idSampah INT NOT NULL REFERENCES Sampah(idSampah),
    jumlahSampah INT NOT NULL,
    hargaTotal INT NOT NULL,
    PRIMARY KEY (idTransaksi, idSampah)
);

CREATE TABLE Harga (
    idSampah INT NOT NULL REFERENCES Sampah(idSampah),
    tanggalUbah DATE NOT NULL,
    hargaSampah INT NOT NULL,
    PRIMARY KEY (idSampah, tanggalUbah)
);

ALTER TABLE pengguna
ADD COLUMN password character varying(255) DEFAULT 'pass';


INSERT INTO Kecamatan (idKec, namaKec)
VALUES
	(1, 'Andir'),
	(2, 'Astana Anyar'),
	(3, 'Antapani'),
	(4, 'Arcamanik'),
	(5, 'Babakan Ciparay'),
	(6, 'Bandung Kidul'),
	(7, 'Bandung Kulon'),
	(8, 'Bandung Wetan'),
	(9, 'Batununggal'),
	(10, 'Bojongloa Kaler'),
	(11, 'Bojongloa Kidul'),
	(12, 'Buahbatu'),
	(13, 'Cibeunying Kaler'),
	(14, 'Cibeunying Kidul'),
	(15, 'Cibiru'),
	(16, 'Cicendo'),
	(17, 'Cidadap'),
	(18, 'Cinambo'),
	(19, 'Coblong'),
	(20, 'Gedebage'),
	(21, 'Kiaracondong'),
	(22, 'Lengkong'),
	(23, 'Mandalajati'),
	(24, 'Panyileukan'),
	(25, 'Rancasari'),
	(26, 'Regol'),
	(27, 'Sukajadi'),
	(28, 'Sukasari'),
	(29, 'Sumur Bandung'),
	(30, 'Ujungberung');

INSERT INTO Kelurahan (idKel, namaKel, idKec)
VALUES
	(1, 'Campaka', 1),
	(2, 'Ciroyom', 1),
	(3, 'Dunguscariang', 1),
	(4, 'Garuda', 1),
	(5, 'Kebonjeruk', 1),
	(6, 'Maleber', 1),
	(7, 'Cibadak', 2),
	(8, 'Karanganyar', 2),
	(9, 'Karasak', 2),
	(10, 'Nyengseret', 2),
	(11, 'Panjunan', 2),
	(12, 'Pelindunghewan', 2),
	(13, 'Antapani Kidul', 3),
	(14, 'Antapani Kulon', 3),
	(15, 'Antapani Tengah', 3),
	(16, 'Antapani Wetan', 3),
	(17, 'Cisaranten Bina Harapan', 4),
	(18, 'Cisaranten Endah', 4),
	(19, 'Cisaranten Kulon', 4),
	(20, 'Sukamiskin', 4),
	(21, 'Babakan', 5),
	(22, 'Babakanciparay', 5),
	(23, 'Cirangrang', 5),
	(24, 'Margahayu Utara', 5),
	(25, 'Margasuka', 5),
	(26, 'Sukahaji', 5),
	(27, 'Batununggal', 6),
	(28, 'Kujangsari', 6),
	(29, 'Mengger', 6),
	(30, 'Wates', 6),
	(31, 'Caringin', 7),
	(32, 'Cibuntu', 7),
	(33, 'Cigondewah Kaler', 7),
	(34, 'Cigondewah Kidul', 7),
	(35, 'Cigondewah Rahayu', 7),
	(36, 'Cijerah', 7),
	(37, 'Gempolsari', 7),
	(38, 'Warungmuncang', 7),
	(39, 'Cihapit', 8),
	(40, 'Citarum', 8),
	(41, 'Tamansari', 8),
	(42, 'Binong', 9),
	(43, 'Cibangkong', 9),
	(44, 'Gumuruh', 9),
	(45, 'Kacapiring', 9),
	(46, 'Kebongedang', 9),
	(47, 'Kebonwaru', 9),
	(48, 'Maleer', 9),
	(49, 'Samoja', 9),
	(50, 'Babakan Asih', 10),
	(51, 'Babakan Tarogong', 10),
	(52, 'Jamika', 10),
	(53, 'Kopo', 10),
	(54, 'Suka Asih', 10),
	(55, 'Cibaduyut', 11),
	(56, 'Cibaduyut Kidul', 11),
	(57, 'Cibaduyut Wetan', 11),
	(58, 'Kebon Lega', 11),
	(59, 'Mekarwangi', 11),
	(60, 'Situsaeur', 11),
	(61, 'Cijawura', 12),
	(62, 'Jatisari', 12),
	(63, 'Margasari', 12),
	(64, 'Sekejati', 12),
	(65, 'Cigadung', 13),
	(66, 'Cihaurgeulis', 13),
	(67, 'Neglasari', 13),
	(68, 'Sukaluyu', 13),
	(69, 'Cicadas', 14),
	(70, 'Cikutra', 14),
	(71, 'Padasuka', 14),
	(72, 'Pasirlayung', 14),
	(73, 'Sukamaju', 14),
	(74, 'Sukapada', 14),
	(75, 'Cipadung', 15),
	(76, 'Cisurupan', 15),
	(77, 'Palasari', 15),
	(78, 'Pasirbiru', 15),
	(79, 'Arjuna', 16),
	(80, 'Husen Sastranegara', 16),
	(81, 'Pajajaran', 16),
	(82, 'Pamoyanan', 16),
	(83, 'Pasirkaliki', 16),
	(84, 'Sukaraja', 16),
	(85, 'Ciumbuleuit', 17),
	(86, 'Hegarmanah', 17),
	(87, 'Ledeng', 17),
	(88, 'Babakan Penghulu', 18),
	(89, 'Cisaranten Wetan', 18),
	(90, 'Pakemitan', 18),
	(91, 'Sukamulya', 18),
	(92, 'Cipaganti', 19),
	(93, 'Dago', 19),
	(94, 'Lebakgede', 19),
	(95, 'Lebaksiliwangi', 19),
	(96, 'Sadangserang', 19),
	(97, 'Sekeloa', 19),
	(98, 'Cimincrang', 20),
	(99, 'Cisaranten Kidul', 20),
	(100, 'Rancabolang', 20),
	(101, 'Rancanumpang', 20),
	(102, 'Babakansari', 21),
	(103, 'Babakansurabaya', 21),
	(104, 'Cicaheum', 21),
	(105, 'Kebonkangkung', 21),
	(106, 'Kebunjayanti', 21),
	(107, 'Sukapura', 21),
	(108, 'Burangrang', 22),
	(109, 'Cijagra', 22),
	(110, 'Cikawao', 22),
	(111, 'Lingkar Selatan', 22),
	(112, 'Malabar', 22),
	(113, 'Paledang', 22),
	(114, 'Turangga', 22),
	(115, 'Jatihandap', 23),
	(116, 'Karangpamulang', 23),
	(117, 'Pasir Impun', 23),
	(118, 'Sindangjaya', 23),
	(119, 'Cipadung Kidul', 24),
	(120, 'Cipadung Kulon', 24),
	(121, 'Cipadung Wetan', 24),
	(122, 'Mekarmulya', 24),
	(123, 'Cipamokolan', 25),
	(124, 'Darwati', 25),
	(125, 'Manjahlega', 25),
	(126, 'Mekar Jaya', 25),
	(127, 'Ancol', 26),
	(128, 'Balonggede', 26),
	(129, 'Ciateul', 26),
	(130, 'Cigereleng', 26),
	(131, 'Ciseureuh', 26),
	(132, 'Pasirluyu', 26),
	(133, 'Pungkur', 26),
	(134, 'Karangmekar', 27),
	(135, 'Pasteur', 27),
	(136, 'Sukabungah', 27),
	(137, 'Sukagalih', 27),
	(138, 'Sukawarna', 27),
	(139, 'Cijerah', 27),
	(140, 'Cibogo', 28),
	(141, 'Cipedes', 28),
	(142, 'Gegerkalong', 28),
	(143, 'Isola', 28),
	(144, 'Sarasasih', 28),
	(145, 'Sukawening', 28),
	(146, 'Braga', 29),
	(147, 'Kebon Pisang', 29),
	(148, 'Merdeka', 29),
	(149, 'Babakan Jati', 30),
	(150, 'Pasirendah', 30),
	(151, 'Pasirwangi', 30);

INSERT INTO JenisSampah (idJenisSampah, namaJenis)
VALUES
	(1, 'Plastik'),
	(2, 'Kaca'),
	(3, 'Kertas'),
	(4, 'Logam');

INSERT INTO SUK (idSUK, namaSUK)
VALUES
	(1, 'kg'),
	(2, 'buah'),
	(3, 'gram');
--buat fitur member sama sampah ini harus diinsert dulu (sampai suk)

-- Insert data into Pengguna
INSERT INTO Pengguna (nama, username, password) VALUES
('Dina', 'dina123', 'password123'),
('Rudi', 'rudi456', 'password456'),
('Siti', 'siti789', 'password789'),
('Ahmad', 'ahmad321', 'password321');

-- Insert data into IbuBS
INSERT INTO IbuBS (idPengguna, password) VALUES
(1, 'ibubs123'),
(3, 'ibubs789');

-- Insert data into Member
INSERT INTO Member (idPengguna, noHP, alamat, email, idKel) VALUES
(2, '081234567890', 'Jl. Mawar No. 1', 'rudi@gmail.com', 1),
(4, '082345678901', 'Jl. Melati No. 2', 'ahmad@yahoo.com', 2);

-- Insert data into BSPusat
INSERT INTO BSPusat (idBSPusat, noTelp, alamat, idKel) VALUES
(1, '02112345678', 'Jl. Raya No. 10', 1),
(2, '02187654321', 'Jl. Kebon Jeruk No. 5', 2);

-- Insert data into Transaksi
INSERT INTO Transaksi (idTransaksi, tanggal, tipeTransaksi, idPengguna, idBSPusat) VALUES
(1, '2024-01-10', 1, 2, 1),
(2, '2024-01-11', 2, 4, 2);

-- Insert data into Sampah
INSERT INTO Sampah (namaSampah, idJenisSampah, idSUK) VALUES
('Botol Plastik', 1, 1),
('Kardus', 2, 2);

-- Insert data into TransaksiSampah
INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal) VALUES
(1, 1, 10, 50000),
(2, 2, 5, 30000);

-- Insert data into Harga
INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES
(1, '2024-01-01', 5000),
(2, '2024-01-01', 6000);


-- SELECT * FROM Harga;
-- SELECT * FROM TransaksiSampah;
-- SELECT * FROM Sampah;
-- SELECT * FROM SUK;
-- SELECT * FROM JenisSampah;
-- SELECT * FROM Transaksi;
-- SELECT * FROM Member;
-- SELECT * FROM IbuBS;
-- SELECT * FROM Pengguna;
-- SELECT * FROM BSPusat;
-- SELECT * FROM Kelurahan;
-- SELECT * FROM Kecamatan;

-- SELECT s.idSampah, s.namaSampah, j.idJenisSampah, j.namaJenis AS jenisSampah,suk.idSUK, suk.namaSUK AS satuan,
-- h.hargaSampah AS hargaBeli, h.tanggalUbah AS tanggal
-- FROM Sampah s
-- JOIN JenisSampah j ON s.idJenisSampah = j.idJenisSampah
-- JOIN SUK suk ON s.idSUK = suk.idSUK
-- LEFT JOIN Harga h ON s.idSampah = h.idSampah
-- WHERE h.tanggalUbah = (SELECT MAX(tanggalUbah) 
-- FROM Harga WHERE idSampah = s.idSampah);
	
-- SELECT * FROM Member JOIN Pengguna ON Pengguna.idPengguna = Member.idPengguna 
-- SELECT Pengguna.idpengguna, username, Pengguna.password FROM Pengguna JOIN IbuBS ON  IbuBS.idpengguna = Pengguna.idPengguna

-- SELECT * FROM (
-- SELECT Pengguna.idPengguna, username, Pengguna.password, nama, 'Member' AS roles
-- FROM Member 
-- JOIN Pengguna ON Pengguna.idPengguna = Member.idPengguna
-- UNION ALL
-- SELECT Pengguna.idPengguna, username, Pengguna.password, nama, 'IbuBS' AS roles
-- FROM Pengguna 
-- JOIN IbuBS ON IbuBS.idPengguna = Pengguna.idPengguna
-- ORDER BY idPengguna
-- ) WHERE username = 'rudi456'

-- SELECT * FROM Pengguna WHERE userId = ''

-- INSERT INTO Pengguna (nama, username, password) VALUES


-- ('Dode', 'dodo1232', 'password123e')

-- --QUERY LAMA
-- INSERT INTO Sampah (idSampah, namaSampah, idJenisSampah, idSUK)
-- VALUES
-- 	(1, 'Botol Plastik 600ml', 1, 2),
-- 	(2, 'Botol Sirup', 2, 2),
-- 	(3, 'Kaleng Minuman Ringan', 4, 1),
-- 	(4, 'Tas Belanja Kertas', 3, 2),
-- 	(5, 'Dokumen Bekas', 3, 1),
-- 	(6, 'Piring Beling', 2, 1),
-- 	(7, 'Alat Makan Bekas', 4, 1),
-- 	(8, 'Karton Bekas', 3, 1),
-- 	(9, 'Botol Plastik 300ml', 1, 2),
-- 	(10, 'Buku Bekas', 3, 1),
-- 	(11, 'Botol Parfum', 2, 2),
-- 	(12, 'Kantong Kresek Bekas', 1, 1),
-- 	(13, 'Paku Bekas', 4, 1);
	
-- INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah)
-- VALUES 
-- 	(1, '20240512', 2000),
-- 	(1, '20240221', 1500),
-- 	(2, '20240117', 3000),
-- 	(3, '20231227', 2000),
-- 	(3, '20231110', 2500),
-- 	(4, '20240322', 1500),
-- 	(5, '20240101', 1000),
-- 	(6, '20230911', 2000),
-- 	(7, '20240214', 3000),
-- 	(8, '20240122', 1000),
-- 	(8, '20240116', 1500),
-- 	(9, '20240322', 1500),
-- 	(10, '20240214', 1200),
-- 	(11, '20240221', 1000),
-- 	(11, '20240318', 1100),
-- 	(12, '20240101', 2000),
-- 	(13, '20240322', 3500);

-- INSERT INTO Pengguna (idPengguna, nama, username)
-- VALUES
-- 	(1, 'Asep', 'Asep'),
-- 	(2, 'Budi', 'Budi'),
-- 	(3, 'Cecep', 'Cecep'),
-- 	(4, 'Dodi', 'Dodi'),
-- 	(5, 'Eko', 'Eko'),
-- 	(6, 'Farzan', 'Farzan'),
-- 	(7, 'Gibran', 'Gibran'),
-- 	(8, 'Hani',  'Hani'),
-- 	(9, 'Ina', 'Ina'),
-- 	(10, 'Joko','Joko');

-- INSERT INTO Member (idPengguna, noHP, alamat, email, idKel)
-- VALUES
-- 	(1, '081230334512', 'Jl. Kembang Jati no.33', 'asep21@gmail.com', 23),
-- 	(2, '081237788902', 'Anggrek Residen Cluster 3 no.22A', 'bubudidi@gmail.com', 62),
-- 	(3, '081918723823', 'Jl. Nagasari no.12', 'c3c3p@gmail.com', 67),
-- 	(4, '081128343829', 'Jl. Mercon no.42', 'dodiForever@gmail.com', 29),
-- 	(5, '081872817388', 'Jl. Camilla no.2B', 'xxEKOxx@gmail.com', 135),
-- 	(6, '081123456791', 'Jl. Rawon no.19', 'farzan123@gmail.com', 20),
-- 	(7, '081557783922', 'Jl. Putik Manis no.40', 'gibran1990@gmail.com', 40),
-- 	(10, '081233419929', 'Jl. Putik Manis no.40', 'JokoWee@gmail.com', 40);

-- INSERT INTO IbuBS (idPengguna, password)
-- VALUES
-- 	(8, 'hani123'),
-- 	(9, 'qwertyuiop');

-- INSERT INTO Transaksi (idTransaksi, tanggal, tipeTransaksi, idPengguna, idBSPusat)
-- VALUES
-- 	(1, '20240321', 1, 7, null),
-- 	(2, '20240602', 1, 10, null),
-- 	(3, '20240302', 2, null, 2),
-- 	(4, '20240211', 2, null, 2);

-- INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal)
-- VALUES
--     (1, 1, 4, 4 * (
--         SELECT hargaSampah
--         FROM Sampah
--         INNER JOIN Harga ON Sampah.idSampah = Harga.idSampah
--         WHERE Sampah.idSampah = 1
--           AND Harga.tanggalUbah <= (
--               SELECT tanggal
--               FROM Transaksi
--               WHERE idTransaksi = 1
--           )
--         ORDER BY Harga.tanggalUbah DESC
--         LIMIT 1
--     )),
--     (2, 1, 4, 4 * (
--         SELECT hargaSampah
--         FROM Sampah
--         INNER JOIN Harga ON Sampah.idSampah = Harga.idSampah
--         WHERE Sampah.idSampah = 1
--           AND Harga.tanggalUbah <= (
--               SELECT tanggal
--               FROM Transaksi
--               WHERE idTransaksi = 2
--           )
--         ORDER BY Harga.tanggalUbah DESC
--         LIMIT 1
--     )),
--     (2, 5, 5, 5 * (
--         SELECT hargaSampah
--         FROM Sampah
--         INNER JOIN Harga ON Sampah.idSampah = Harga.idSampah
--         WHERE Sampah.idSampah = 5
--           AND Harga.tanggalUbah <= (
--               SELECT tanggal
--               FROM Transaksi
--               WHERE idTransaksi = 2
--           )
--         ORDER BY Harga.tanggalUbah DESC
--         LIMIT 1
--     )),
--     (3, 1, 2, 2 * (
--         SELECT hargaSampah
--         FROM Sampah
--         INNER JOIN Harga ON Sampah.idSampah = Harga.idSampah
--         WHERE Sampah.idSampah = 1
--           AND Harga.tanggalUbah <= (
--               SELECT tanggal
--               FROM Transaksi
--               WHERE idTransaksi = 3
--           )
--         ORDER BY Harga.tanggalUbah DESC
--         LIMIT 1
--     )),
--     (4, 3, 2, 2 * (
--         SELECT hargaSampah
--         FROM Sampah
--         INNER JOIN Harga ON Sampah.idSampah = Harga.idSampah
--         WHERE Sampah.idSampah = 3
--           AND Harga.tanggalUbah <= (
--               SELECT tanggal
--               FROM Transaksi
--               WHERE idTransaksi = 4
--           )
--         ORDER BY Harga.tanggalUbah DESC
--         LIMIT 1
--     ));