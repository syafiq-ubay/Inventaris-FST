PGDMP         )                {            inventaris FST    15.4    15.4 B    U           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            V           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            W           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            X           1262    19393    inventaris FST    DATABASE     �   CREATE DATABASE "inventaris FST" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
     DROP DATABASE "inventaris FST";
                postgres    false            �            1255    36061    kurangi_stok_barang()    FUNCTION     (  CREATE FUNCTION public.kurangi_stok_barang() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  -- Kurangi stok onderdil sesuai dengan jumlah yang digunakan dalam detail service
  UPDATE barang
  SET stok = stok - NEW.jmh_keluar
  WHERE kode_barang = NEW.kode_barang;

  RETURN NEW;
END;
$$;
 ,   DROP FUNCTION public.kurangi_stok_barang();
       public          postgres    false            �            1255    36065    tambah_stok_barang()    FUNCTION     4  CREATE FUNCTION public.tambah_stok_barang() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
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
$$;
 +   DROP FUNCTION public.tambah_stok_barang();
       public          postgres    false            �            1259    19394    admin    TABLE       CREATE TABLE public.admin (
    id_admin character(10) NOT NULL,
    nama_admin character varying(100),
    telp_admin character varying(150),
    email_admin character varying(150),
    username character varying(150),
    password character varying(150)
);
    DROP TABLE public.admin;
       public         heap    postgres    false            �            1259    19402    barang    TABLE     �   CREATE TABLE public.barang (
    kode_barang character(10) NOT NULL,
    kode_jenis character(10),
    nama_barang character varying(150),
    stok integer
);
    DROP TABLE public.barang;
       public         heap    postgres    false            �            1259    19409    barang_keluar    TABLE     �   CREATE TABLE public.barang_keluar (
    kode_keluar character(10) NOT NULL,
    id_pemesan character(10),
    tgl_keluar date,
    id_admin character(10)
);
 !   DROP TABLE public.barang_keluar;
       public         heap    postgres    false            �            1259    19417    barang_masuk    TABLE     �   CREATE TABLE public.barang_masuk (
    kode_masuk character(10) NOT NULL,
    id_pesanan character(10),
    id_admin character(10),
    tgl_masuk date
);
     DROP TABLE public.barang_masuk;
       public         heap    postgres    false            �            1259    19425    detail_barang_keluar    TABLE     �   CREATE TABLE public.detail_barang_keluar (
    kode_barang character(10) NOT NULL,
    kode_keluar character(10) NOT NULL,
    jmh_keluar integer
);
 (   DROP TABLE public.detail_barang_keluar;
       public         heap    postgres    false            �            1259    19433    detail_barang_masuk    TABLE     �   CREATE TABLE public.detail_barang_masuk (
    kode_barang character(10) NOT NULL,
    jmh_masuk integer,
    id_pesanan character varying(50)
);
 '   DROP TABLE public.detail_barang_masuk;
       public         heap    postgres    false            �            1259    19441    jenisbarang    TABLE     r   CREATE TABLE public.jenisbarang (
    kode_jenis character(10) NOT NULL,
    nama_jenis character varying(200)
);
    DROP TABLE public.jenisbarang;
       public         heap    postgres    false            �            1259    19447    pemesan_barang    TABLE     �   CREATE TABLE public.pemesan_barang (
    id_pemesan character(10) NOT NULL,
    nama_pemesan character varying(150),
    unit_pemesan character varying(150)
);
 "   DROP TABLE public.pemesan_barang;
       public         heap    postgres    false            �            1259    19453    pesanan    TABLE     �   CREATE TABLE public.pesanan (
    id_pesanan character(10) NOT NULL,
    id_supplier character(10),
    id_admin character(10),
    tgl_pesanan date,
    status character varying(50)
);
    DROP TABLE public.pesanan;
       public         heap    postgres    false            �            1259    19461    supplier    TABLE     �   CREATE TABLE public.supplier (
    id_supplier character(10) NOT NULL,
    nama_supplier character varying(150),
    telp_supplier character varying(13),
    email_supplier character varying(200)
);
    DROP TABLE public.supplier;
       public         heap    postgres    false            I          0    19394    admin 
   TABLE DATA           b   COPY public.admin (id_admin, nama_admin, telp_admin, email_admin, username, password) FROM stdin;
    public          postgres    false    214   �S       J          0    19402    barang 
   TABLE DATA           L   COPY public.barang (kode_barang, kode_jenis, nama_barang, stok) FROM stdin;
    public          postgres    false    215   ET       K          0    19409    barang_keluar 
   TABLE DATA           V   COPY public.barang_keluar (kode_keluar, id_pemesan, tgl_keluar, id_admin) FROM stdin;
    public          postgres    false    216   �T       L          0    19417    barang_masuk 
   TABLE DATA           S   COPY public.barang_masuk (kode_masuk, id_pesanan, id_admin, tgl_masuk) FROM stdin;
    public          postgres    false    217   �T       M          0    19425    detail_barang_keluar 
   TABLE DATA           T   COPY public.detail_barang_keluar (kode_barang, kode_keluar, jmh_keluar) FROM stdin;
    public          postgres    false    218   U       N          0    19433    detail_barang_masuk 
   TABLE DATA           Q   COPY public.detail_barang_masuk (kode_barang, jmh_masuk, id_pesanan) FROM stdin;
    public          postgres    false    219   AU       O          0    19441    jenisbarang 
   TABLE DATA           =   COPY public.jenisbarang (kode_jenis, nama_jenis) FROM stdin;
    public          postgres    false    220   wU       P          0    19447    pemesan_barang 
   TABLE DATA           P   COPY public.pemesan_barang (id_pemesan, nama_pemesan, unit_pemesan) FROM stdin;
    public          postgres    false    221   �U       Q          0    19453    pesanan 
   TABLE DATA           Y   COPY public.pesanan (id_pesanan, id_supplier, id_admin, tgl_pesanan, status) FROM stdin;
    public          postgres    false    222   �U       R          0    19461    supplier 
   TABLE DATA           ]   COPY public.supplier (id_supplier, nama_supplier, telp_supplier, email_supplier) FROM stdin;
    public          postgres    false    223   <V       �           2606    19400    admin pk_admin 
   CONSTRAINT     R   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT pk_admin PRIMARY KEY (id_admin);
 8   ALTER TABLE ONLY public.admin DROP CONSTRAINT pk_admin;
       public            postgres    false    214            �           2606    19406    barang pk_barang 
   CONSTRAINT     W   ALTER TABLE ONLY public.barang
    ADD CONSTRAINT pk_barang PRIMARY KEY (kode_barang);
 :   ALTER TABLE ONLY public.barang DROP CONSTRAINT pk_barang;
       public            postgres    false    215            �           2606    19413    barang_keluar pk_barang_keluar 
   CONSTRAINT     e   ALTER TABLE ONLY public.barang_keluar
    ADD CONSTRAINT pk_barang_keluar PRIMARY KEY (kode_keluar);
 H   ALTER TABLE ONLY public.barang_keluar DROP CONSTRAINT pk_barang_keluar;
       public            postgres    false    216            �           2606    19421    barang_masuk pk_barang_masuk 
   CONSTRAINT     b   ALTER TABLE ONLY public.barang_masuk
    ADD CONSTRAINT pk_barang_masuk PRIMARY KEY (kode_masuk);
 F   ALTER TABLE ONLY public.barang_masuk DROP CONSTRAINT pk_barang_masuk;
       public            postgres    false    217            �           2606    19429 ,   detail_barang_keluar pk_detail_barang_keluar 
   CONSTRAINT     �   ALTER TABLE ONLY public.detail_barang_keluar
    ADD CONSTRAINT pk_detail_barang_keluar PRIMARY KEY (kode_barang, kode_keluar);
 V   ALTER TABLE ONLY public.detail_barang_keluar DROP CONSTRAINT pk_detail_barang_keluar;
       public            postgres    false    218    218            �           2606    19445    jenisbarang pk_jenisbarang 
   CONSTRAINT     `   ALTER TABLE ONLY public.jenisbarang
    ADD CONSTRAINT pk_jenisbarang PRIMARY KEY (kode_jenis);
 D   ALTER TABLE ONLY public.jenisbarang DROP CONSTRAINT pk_jenisbarang;
       public            postgres    false    220            �           2606    19451     pemesan_barang pk_pemesan_barang 
   CONSTRAINT     f   ALTER TABLE ONLY public.pemesan_barang
    ADD CONSTRAINT pk_pemesan_barang PRIMARY KEY (id_pemesan);
 J   ALTER TABLE ONLY public.pemesan_barang DROP CONSTRAINT pk_pemesan_barang;
       public            postgres    false    221            �           2606    19457    pesanan pk_pesanan 
   CONSTRAINT     X   ALTER TABLE ONLY public.pesanan
    ADD CONSTRAINT pk_pesanan PRIMARY KEY (id_pesanan);
 <   ALTER TABLE ONLY public.pesanan DROP CONSTRAINT pk_pesanan;
       public            postgres    false    222            �           2606    19465    supplier pk_supplier 
   CONSTRAINT     [   ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT pk_supplier PRIMARY KEY (id_supplier);
 >   ALTER TABLE ONLY public.supplier DROP CONSTRAINT pk_supplier;
       public            postgres    false    223            �           1259    19401    admin_pk    INDEX     E   CREATE UNIQUE INDEX admin_pk ON public.admin USING btree (id_admin);
    DROP INDEX public.admin_pk;
       public            postgres    false    214            �           1259    19414    barang_keluar_pk    INDEX     X   CREATE UNIQUE INDEX barang_keluar_pk ON public.barang_keluar USING btree (kode_keluar);
 $   DROP INDEX public.barang_keluar_pk;
       public            postgres    false    216            �           1259    19422    barang_masuk_pk    INDEX     U   CREATE UNIQUE INDEX barang_masuk_pk ON public.barang_masuk USING btree (kode_masuk);
 #   DROP INDEX public.barang_masuk_pk;
       public            postgres    false    217            �           1259    19407 	   barang_pk    INDEX     J   CREATE UNIQUE INDEX barang_pk ON public.barang USING btree (kode_barang);
    DROP INDEX public.barang_pk;
       public            postgres    false    215            �           1259    19431    detail_barang_keluar2_fk    INDEX     `   CREATE INDEX detail_barang_keluar2_fk ON public.detail_barang_keluar USING btree (kode_keluar);
 ,   DROP INDEX public.detail_barang_keluar2_fk;
       public            postgres    false    218            �           1259    19432    detail_barang_keluar_fk    INDEX     _   CREATE INDEX detail_barang_keluar_fk ON public.detail_barang_keluar USING btree (kode_barang);
 +   DROP INDEX public.detail_barang_keluar_fk;
       public            postgres    false    218            �           1259    19430    detail_barang_keluar_pk    INDEX     s   CREATE UNIQUE INDEX detail_barang_keluar_pk ON public.detail_barang_keluar USING btree (kode_barang, kode_keluar);
 +   DROP INDEX public.detail_barang_keluar_pk;
       public            postgres    false    218    218            �           1259    19439    detail_barang_masuk2_fk    INDEX     ^   CREATE INDEX detail_barang_masuk2_fk ON public.detail_barang_masuk USING btree (kode_barang);
 +   DROP INDEX public.detail_barang_masuk2_fk;
       public            postgres    false    219            �           1259    19446    jenisbarang_pk    INDEX     S   CREATE UNIQUE INDEX jenisbarang_pk ON public.jenisbarang USING btree (kode_jenis);
 "   DROP INDEX public.jenisbarang_pk;
       public            postgres    false    220            �           1259    19452    pemesan_barang_pk    INDEX     Y   CREATE UNIQUE INDEX pemesan_barang_pk ON public.pemesan_barang USING btree (id_pemesan);
 %   DROP INDEX public.pemesan_barang_pk;
       public            postgres    false    221            �           1259    19458 
   pesanan_pk    INDEX     K   CREATE UNIQUE INDEX pesanan_pk ON public.pesanan USING btree (id_pesanan);
    DROP INDEX public.pesanan_pk;
       public            postgres    false    222            �           1259    19408    relationship_1_fk    INDEX     J   CREATE INDEX relationship_1_fk ON public.barang USING btree (kode_jenis);
 %   DROP INDEX public.relationship_1_fk;
       public            postgres    false    215            �           1259    19459    relationship_2_fk    INDEX     L   CREATE INDEX relationship_2_fk ON public.pesanan USING btree (id_supplier);
 %   DROP INDEX public.relationship_2_fk;
       public            postgres    false    222            �           1259    19460    relationship_3_fk    INDEX     I   CREATE INDEX relationship_3_fk ON public.pesanan USING btree (id_admin);
 %   DROP INDEX public.relationship_3_fk;
       public            postgres    false    222            �           1259    19415    relationship_6_fk    INDEX     Q   CREATE INDEX relationship_6_fk ON public.barang_keluar USING btree (id_pemesan);
 %   DROP INDEX public.relationship_6_fk;
       public            postgres    false    216            �           1259    19423    relationship_8_fk    INDEX     P   CREATE INDEX relationship_8_fk ON public.barang_masuk USING btree (id_pesanan);
 %   DROP INDEX public.relationship_8_fk;
       public            postgres    false    217            �           1259    19424    relationship_9_fk    INDEX     N   CREATE INDEX relationship_9_fk ON public.barang_masuk USING btree (id_admin);
 %   DROP INDEX public.relationship_9_fk;
       public            postgres    false    217            �           1259    19466    supplier_pk    INDEX     N   CREATE UNIQUE INDEX supplier_pk ON public.supplier USING btree (id_supplier);
    DROP INDEX public.supplier_pk;
       public            postgres    false    223            �           2620    36062 +   detail_barang_keluar tr_kurangi_stok_barang    TRIGGER     �   CREATE TRIGGER tr_kurangi_stok_barang AFTER INSERT ON public.detail_barang_keluar FOR EACH ROW WHEN ((new.kode_barang IS NOT NULL)) EXECUTE FUNCTION public.kurangi_stok_barang();
 D   DROP TRIGGER tr_kurangi_stok_barang ON public.detail_barang_keluar;
       public          postgres    false    224    218    218            �           2620    36066    pesanan tr_kurangi_stok_barang    TRIGGER     �   CREATE TRIGGER tr_kurangi_stok_barang AFTER UPDATE ON public.pesanan FOR EACH ROW WHEN ((new.id_pesanan IS NOT NULL)) EXECUTE FUNCTION public.tambah_stok_barang();
 7   DROP TRIGGER tr_kurangi_stok_barang ON public.pesanan;
       public          postgres    false    225    222    222            �           2606    36043 7   detail_barang_masuk detail_barang_masuk_id_pesanan_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.detail_barang_masuk
    ADD CONSTRAINT detail_barang_masuk_id_pesanan_fkey FOREIGN KEY (id_pesanan) REFERENCES public.pesanan(id_pesanan) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 a   ALTER TABLE ONLY public.detail_barang_masuk DROP CONSTRAINT detail_barang_masuk_id_pesanan_fkey;
       public          postgres    false    222    3240    219            �           2606    36033    barang_keluar fk_admin    FK CONSTRAINT     �   ALTER TABLE ONLY public.barang_keluar
    ADD CONSTRAINT fk_admin FOREIGN KEY (id_admin) REFERENCES public.admin(id_admin) NOT VALID;
 @   ALTER TABLE ONLY public.barang_keluar DROP CONSTRAINT fk_admin;
       public          postgres    false    3212    214    216            �           2606    19477 ,   barang_keluar fk_barang_k_relations_pemesan_    FK CONSTRAINT     �   ALTER TABLE ONLY public.barang_keluar
    ADD CONSTRAINT fk_barang_k_relations_pemesan_ FOREIGN KEY (id_pemesan) REFERENCES public.pemesan_barang(id_pemesan) ON UPDATE RESTRICT ON DELETE RESTRICT;
 V   ALTER TABLE ONLY public.barang_keluar DROP CONSTRAINT fk_barang_k_relations_pemesan_;
       public          postgres    false    3237    216    221            �           2606    19487 (   barang_masuk fk_barang_m_relations_admin    FK CONSTRAINT     �   ALTER TABLE ONLY public.barang_masuk
    ADD CONSTRAINT fk_barang_m_relations_admin FOREIGN KEY (id_admin) REFERENCES public.admin(id_admin) ON UPDATE RESTRICT ON DELETE RESTRICT;
 R   ALTER TABLE ONLY public.barang_masuk DROP CONSTRAINT fk_barang_m_relations_admin;
       public          postgres    false    217    214    3212            �           2606    19482 *   barang_masuk fk_barang_m_relations_pesanan    FK CONSTRAINT     �   ALTER TABLE ONLY public.barang_masuk
    ADD CONSTRAINT fk_barang_m_relations_pesanan FOREIGN KEY (id_pesanan) REFERENCES public.pesanan(id_pesanan) ON UPDATE RESTRICT ON DELETE RESTRICT;
 T   ALTER TABLE ONLY public.barang_masuk DROP CONSTRAINT fk_barang_m_relations_pesanan;
       public          postgres    false    222    3240    217            �           2606    19467 #   barang fk_barang_relations_jenisbar    FK CONSTRAINT     �   ALTER TABLE ONLY public.barang
    ADD CONSTRAINT fk_barang_relations_jenisbar FOREIGN KEY (kode_jenis) REFERENCES public.jenisbarang(kode_jenis) ON UPDATE RESTRICT ON DELETE RESTRICT;
 M   ALTER TABLE ONLY public.barang DROP CONSTRAINT fk_barang_relations_jenisbar;
       public          postgres    false    215    3234    220            �           2606    19492 1   detail_barang_keluar fk_detail_b_detail_ba_barang    FK CONSTRAINT     �   ALTER TABLE ONLY public.detail_barang_keluar
    ADD CONSTRAINT fk_detail_b_detail_ba_barang FOREIGN KEY (kode_barang) REFERENCES public.barang(kode_barang) ON UPDATE RESTRICT ON DELETE RESTRICT;
 [   ALTER TABLE ONLY public.detail_barang_keluar DROP CONSTRAINT fk_detail_b_detail_ba_barang;
       public          postgres    false    215    3215    218            �           2606    36048 0   detail_barang_masuk fk_detail_b_detail_ba_barang    FK CONSTRAINT     �   ALTER TABLE ONLY public.detail_barang_masuk
    ADD CONSTRAINT fk_detail_b_detail_ba_barang FOREIGN KEY (kode_barang) REFERENCES public.barang(kode_barang) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 Z   ALTER TABLE ONLY public.detail_barang_masuk DROP CONSTRAINT fk_detail_b_detail_ba_barang;
       public          postgres    false    215    3215    219            �           2606    36038 3   detail_barang_keluar fk_detail_b_detail_ba_barang_k    FK CONSTRAINT     �   ALTER TABLE ONLY public.detail_barang_keluar
    ADD CONSTRAINT fk_detail_b_detail_ba_barang_k FOREIGN KEY (kode_keluar) REFERENCES public.barang_keluar(kode_keluar) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 ]   ALTER TABLE ONLY public.detail_barang_keluar DROP CONSTRAINT fk_detail_b_detail_ba_barang_k;
       public          postgres    false    216    218    3219            �           2606    19517 "   pesanan fk_pesanan_relations_admin    FK CONSTRAINT     �   ALTER TABLE ONLY public.pesanan
    ADD CONSTRAINT fk_pesanan_relations_admin FOREIGN KEY (id_admin) REFERENCES public.admin(id_admin) ON UPDATE RESTRICT ON DELETE RESTRICT;
 L   ALTER TABLE ONLY public.pesanan DROP CONSTRAINT fk_pesanan_relations_admin;
       public          postgres    false    214    222    3212            �           2606    19512 %   pesanan fk_pesanan_relations_supplier    FK CONSTRAINT     �   ALTER TABLE ONLY public.pesanan
    ADD CONSTRAINT fk_pesanan_relations_supplier FOREIGN KEY (id_supplier) REFERENCES public.supplier(id_supplier) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.pesanan DROP CONSTRAINT fk_pesanan_relations_supplier;
       public          postgres    false    222    223    3244            I   j   x�st14200TPP��LL�,TMJ�L���I��4�064453�411�,K�e�s3s���s���f�)�����)��F�ɩ��&��)�I��ff\1z\\\ �V)      J   7   x�s24200R N/?��ф�؀�	�7D��9R�r��R�J8�b���� ��      K   -   x�s�64200TPP����9���u�t�9]`
�b���� �	�      L   8   x�s�5424600T�p�3]�@N##c]C#]Cc.'�Z#�Z#\jc���� +#�      M       x�s24200R N'o��42������ Pl�      N   &   x�s24200T N� 'C#Cc ��	��W� �l�      O   +   x���34200TPP�,.�L������D�S�J��b���� Ў	�      P   .   x��14200TP��,JK�+�Q(�)��,(�O�T(������ �'
�      Q   <   x�p2424600T�0418]�L##c]C#]CcN�Ҽ�b� �#�u��qqq >��      R   B   x�04200TPP�tJL�L�S�-��4�044615���L�'�8��&f��%��r��qqq ��     