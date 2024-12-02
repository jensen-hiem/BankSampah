-- Member 

-- login
SELECT
    Pengguna.username
FROM
    Pengguna
INNER JOIN Member ON Pengguna.idPengguna = Member.idPengguna
WHERE
    Pengguna.username = 'Joko';



-- Melihat daftar harga beli sampah
SELECT
    Sampah.namaSampah,
    Harga.hargaSampah
FROM
    Sampah
INNER JOIN (
    SELECT
        idSampah,
        MAX(tanggalUbah) AS updateTerbaru
    FROM
        Harga
    GROUP BY
        idSampah
) AS Latest ON Sampah.idSampah = Latest.idSampah
INNER JOIN Harga ON Harga.idSampah = Sampah.idSampah AND Harga.tanggalUbah = Latest.updateTerbaru;


--DONE
-- Histori setoran sampah
SELECT
    Sampah.namaSampah,
	JenisSampah.namajenis,
	SUK.namaSuk,
    TransaksiSampah.jumlahSampah,
    TransaksiSampah.hargaTotal,
	Transaksi.tanggal
FROM
    Member
INNER JOIN Transaksi ON Member.idPengguna = Transaksi.idPengguna
INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
INNER JOIN Sampah ON TransaksiSampah.idSampah = Sampah.idSampah
INNER JOIN JenisSampah ON Sampah.idjenissampah = JenisSampah.idjenissampah
INNER JOIN SUK ON Sampah.idsuk =  SUK.idsuk
WHERE
    Member.idPengguna = (
        SELECT idPengguna
        FROM Pengguna
        WHERE username = 'Joko'
    )
    AND tipeTransaksi = 1
ORDER BY
    Transaksi.tanggal;


-- penghasilan setoran sampah dalam rentang waktu tertentu (semua)
SELECT
    Transaksi.tanggal,
    SUM(TransaksiSampah.hargaTotal) AS total_pendapatan
FROM
    Transaksi
INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
WHERE
    Transaksi.idPengguna = (
        SELECT idPengguna
        FROM Pengguna
        WHERE username = 'Joko'
    )
    AND Transaksi.tanggal BETWEEN '2024-03-01' AND '2024-06-30'
    AND tipeTransaksi = 1
GROUP BY
    Transaksi.tanggal;


-- penghasilan setoran sampah dalam rentang waktu tertentu (tahun)

SELECT
    EXTRACT(YEAR FROM Transaksi.tanggal) AS tahun,
    SUM(TransaksiSampah.hargaTotal) AS total_pendapatan
FROM
    Transaksi
INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
WHERE
    Transaksi.idPengguna = (
        SELECT idPengguna
        FROM Pengguna
        WHERE username = 'Joko'
    )
    AND tipeTransaksi = 1
GROUP BY
    EXTRACT(YEAR FROM Transaksi.tanggal);


-- penghasilan setoran sampah dalam rentang waktu tertentu (bulan)

SELECT
    EXTRACT(MONTH FROM Transaksi.tanggal) AS bulan,
    SUM(TransaksiSampah.hargaTotal) AS total_pendapatan
FROM
    Transaksi
INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
WHERE
    Transaksi.idPengguna = (
        SELECT idPengguna
        FROM Pengguna
        WHERE username = 'Joko'
    )
    AND tipeTransaksi = 1
GROUP BY
    EXTRACT(MONTH FROM Transaksi.tanggal);

-- Ibu BS

-- Log in
SELECT
    IbuBS.password
FROM
    Pengguna
INNER JOIN IbuBS ON Pengguna.idPengguna = IbuBS.idPengguna
WHERE
    Pengguna.username = 'Hani';


-- input sampah
-- sampah memiliki 2 Fk

-- lihat list jenis sampah
SELECT
	*
FROM
	JenisSampah;

-- lihat list SUK sampah
SELECT
	*
FROM
	SUK;

-- lihat id terakhir
SELECT
    idSampah
FROM
    Sampah
ORDER BY
    idSampah DESC
LIMIT 1;

-- insert sampah 
INSERT INTO Sampah (idSampah, namaSampah, idJenisSampah, idSUK)
VALUES (
    (SELECT COALESCE(MAX(idSampah), 0) + 1 FROM Sampah),
    'Botol Plastik 1500ml', -- Nama Sampah
    1,                     -- ID Jenis
    2                      -- ID SUK
);

-- merubah harga

--DEBUG
-- INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah)
-- VALUES (1, '2024-11-26', 15000);

\d Harga

INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah)
VALUES (
    (SELECT idSampah
    FROM Sampah
    ORDER BY idSampah DESC
    LIMIT 1),
    '2024-06-05',
    2200
);

-- output list sampah
SELECT
	idSampah,
	namaSampah
FROM	
	Sampah;

-- update harga
INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah)
VALUES	
	(14, '20240606', 2300);

-- input transaksi masuk
-- input member, transaksi, transaksi sampah

INSERT INTO Transaksi (idTransaksi, tanggal, tipeTransaksi, idPengguna, idBSPusat)
VALUES
	(1 + 
	(SELECT idTransaksi 
		FROM Transaksi 
		ORDER BY idTransaksi DESC 
		LIMIT 1),
	'2024-06-07', 1, 
	(SELECT idPengguna 
		FROM Pengguna 
		WHERE username = 'Joko'),
	null);


-- tunjukkan daftar sampah
SELECT
	idSampah, namaSampah, namaSUK
FROM
	Sampah 
	INNER JOIN SUK ON Sampah.idSUK = SUK.idSUK;

-- loop sebanyak jenis sampah
INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal)
VALUES 
	((SELECT idTransaksi 
	  FROM Transaksi 
	  ORDER BY idTransaksi DESC 
	  LIMIT 1), 
	1, 4, 4 * 
		(SELECT hargaSampah
		 FROM Harga
		 WHERE idSampah = 1
		   AND tanggalUbah <= 
			 (SELECT tanggal
			  FROM Transaksi
			  ORDER BY idTransaksi DESC
			  LIMIT 1)
		 ORDER BY tanggalUbah DESC
		 LIMIT 1));



-- input transaksi keluar
-- tunjukkin list BS Pusat berdasarkan kelurahannya
SELECT
	idBSPusat, namaKel
FROM
	BSPusat 
	INNER JOIN Kelurahan ON BSPusat.idKel = Kelurahan.idKel;

INSERT INTO Transaksi (idTransaksi, tanggal, tipeTransaksi, idPengguna, idBSPusat)
VALUES
	(1 + 
	(SELECT idTransaksi 
	 FROM Transaksi 
	 ORDER BY idTransaksi DESC 
	 LIMIT 1),
	'2024-06-07', 2, 
	null, 
	1);


-- loop tergantung jumlah jenis sampah
INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal)
VALUES 
	((SELECT idTransaksi 
	  FROM Transaksi 
	  ORDER BY idTransaksi DESC 
	  LIMIT 1), 
	1, 4, 4 * 
		(SELECT hargaSampah
		 FROM Harga
		 WHERE idSampah = 1
		   AND tanggalUbah <= 
			 (SELECT tanggal
			  FROM Transaksi
			  ORDER BY idTransaksi DESC
			  LIMIT 1)
		 ORDER BY tanggalUbah DESC
		 LIMIT 1));


-- transaksi masuk (member)
SELECT
	tanggal, nama, namaSampah, hargaTotal
FROM
	Transaksi INNER JOIN TransaksiSampah
	ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
	INNER JOIN Member
	ON Transaksi.idPengguna = Member.idPengguna
	INNER JOIN Pengguna
	ON Pengguna.idPengguna = Member.idPengguna
	INNER JOIN Sampah
	ON TransaksiSampah.idSampah = Sampah.idSampah;

-- transaksi keluar (BS pusat)
SELECT
	Transaksi.tanggal, 
	Pengguna.nama AS namaMember, 
	Sampah.namaSampah, 
	TransaksiSampah.hargaTotal
FROM
	Transaksi
	INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
	INNER JOIN Member ON Transaksi.idPengguna = Member.idPengguna
	INNER JOIN Pengguna ON Pengguna.idPengguna = Member.idPengguna
	INNER JOIN Sampah ON TransaksiSampah.idSampah = Sampah.idSampah;

