-- Buat dulu fungsi yang akan dijalankan oleh trigger
CREATE OR REPLACE FUNCTION kurangi_stok_barang()
RETURNS TRIGGER AS $$
BEGIN
  -- Kurangi stok onderdil sesuai dengan jumlah yang digunakan dalam detail service
  UPDATE barang
  SET stok = stok - NEW.jmh_keluar
  WHERE kode_barang = NEW.kode_barang;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Setelah itu, buat trigger yang akan memanggil fungsi di atas
CREATE TRIGGER tr_kurangi_stok_barang
AFTER INSERT ON detail_barang_keluar
FOR EACH ROW
WHEN (NEW.kode_keluar IS NOT NULL)
EXECUTE FUNCTION kurangi_stok_barang();
