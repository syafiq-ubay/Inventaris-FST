-- Buat dulu fungsi yang akan dijalankan oleh trigger
CREATE OR REPLACE FUNCTION tambah_stok_barang()
RETURNS TRIGGER AS $$
BEGIN
  -- Tambahkan stok onderdil sesuai dengan jumlah yang disupply
  UPDATE barang
  SET stok = barang.stok + (
    SELECT jmh_masuk
    FROM detail_barang_masuk
    WHERE detail_barang_masuk.kode_barang = barang.kode_barang
      AND detail_barang_masuk.id_pesanan = NEW.id_pesanan
    LIMIT 1
  )
  WHERE barang.kode_barang IN (
    SELECT kode_barang
    FROM detail_barang_masuk
    WHERE detail_barang_masuk.id_pesanan = NEW.id_pesanan
  );

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Setelah itu, buat trigger yang akan memanggil fungsi di atas
CREATE TRIGGER tr_kurangi_stok_barang
AFTER UPDATE ON barang_masuk
FOR EACH ROW
WHEN (NEW.kode_masuk IS NOT NULL)
EXECUTE FUNCTION tambah_stok_barang();
