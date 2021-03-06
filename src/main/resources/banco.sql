PGDMP     !                    z            postgres    14.3    14.3     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    13754    postgres    DATABASE     g   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE postgres;
                postgres    false                       0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3330                        2615    16394    banco    SCHEMA        CREATE SCHEMA banco;
    DROP SCHEMA banco;
                postgres    false            ?            1259    16423    cliente    TABLE     ?   CREATE TABLE banco.cliente (
    id bigint NOT NULL,
    direccion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE banco.cliente;
       banco         heap    postgres    false    5            ?            1259    16430    cuenta    TABLE     ?   CREATE TABLE banco.cuenta (
    id bigint NOT NULL,
    numero numeric(19,2),
    saldo numeric(19,2),
    id_cliente bigint NOT NULL
);
    DROP TABLE banco.cuenta;
       banco         heap    postgres    false    5            ?            1259    16412    hibernate_sequence    SEQUENCE     z   CREATE SEQUENCE banco.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE banco.hibernate_sequence;
       banco          postgres    false    5            ?            1259    16435 
   movimiento    TABLE     ?   CREATE TABLE banco.movimiento (
    id bigint NOT NULL,
    fecha date,
    tipo character varying(255),
    valor numeric(19,2),
    id_cuenta bigint NOT NULL
);
    DROP TABLE banco.movimiento;
       banco         heap    postgres    false    5            ?          0    16423    cliente 
   TABLE DATA           A   COPY banco.cliente (id, direccion, nombre, telefono) FROM stdin;
    banco          postgres    false    212   w       ?          0    16430    cuenta 
   TABLE DATA           >   COPY banco.cuenta (id, numero, saldo, id_cliente) FROM stdin;
    banco          postgres    false    213   ?       ?          0    16435 
   movimiento 
   TABLE DATA           F   COPY banco.movimiento (id, fecha, tipo, valor, id_cuenta) FROM stdin;
    banco          postgres    false    214                     0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('banco.hibernate_sequence', 74, true);
          banco          postgres    false    211            g           2606    16429    cliente cliente_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY banco.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY banco.cliente DROP CONSTRAINT cliente_pkey;
       banco            postgres    false    212            i           2606    16434    cuenta cuenta_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY banco.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id);
 ;   ALTER TABLE ONLY banco.cuenta DROP CONSTRAINT cuenta_pkey;
       banco            postgres    false    213            k           2606    16439    movimiento movimiento_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY banco.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY banco.movimiento DROP CONSTRAINT movimiento_pkey;
       banco            postgres    false    214            m           2606    16445 &   movimiento fk8veysyanipny5mpudj13t8873    FK CONSTRAINT     ?   ALTER TABLE ONLY banco.movimiento
    ADD CONSTRAINT fk8veysyanipny5mpudj13t8873 FOREIGN KEY (id_cuenta) REFERENCES banco.cuenta(id);
 O   ALTER TABLE ONLY banco.movimiento DROP CONSTRAINT fk8veysyanipny5mpudj13t8873;
       banco          postgres    false    214    3177    213            l           2606    16440 "   cuenta fkmkmi3xf6wrp0y1mdn8nm4weim    FK CONSTRAINT     ?   ALTER TABLE ONLY banco.cuenta
    ADD CONSTRAINT fkmkmi3xf6wrp0y1mdn8nm4weim FOREIGN KEY (id_cliente) REFERENCES banco.cliente(id);
 K   ALTER TABLE ONLY banco.cuenta DROP CONSTRAINT fkmkmi3xf6wrp0y1mdn8nm4weim;
       banco          postgres    false    3175    213    212            ?   V   x?32?tN??IU04RH.JT04?H-HUH-J??4624153515?22*,NT02P?i???/NU ??Ɔ?f&?&f??\1z\\\ ??      ?   .   x?3?ര472 =N0?2?22??D??ML?r?\1z\\\ K?
Q      ?   /   x?37?4202?50?56?tru???44500?30?42?????? ??"     