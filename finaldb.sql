--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2019-10-11 20:01:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE finaldb;
--
-- TOC entry 2852 (class 1262 OID 16609)
-- Name: finaldb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE finaldb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE finaldb OWNER TO postgres;

\connect finaldb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16610)
-- Name: alocari; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alocari (
    id_alocare bigint NOT NULL,
    id_rezervare bigint NOT NULL,
    id_masina character varying(10) NOT NULL
);


ALTER TABLE public.alocari OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16613)
-- Name: categ_acces; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categ_acces (
    id_acces smallint NOT NULL,
    tip_acces character varying(35) NOT NULL
);


ALTER TABLE public.categ_acces OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16616)
-- Name: masini; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.masini (
    nr_masina character varying(10) NOT NULL,
    nr_locuri smallint NOT NULL
);


ALTER TABLE public.masini OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16619)
-- Name: rezervare; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rezervare (
    id_rezervare bigint NOT NULL,
    nume_cl character varying(35) NOT NULL,
    prenume_cl character varying(35) NOT NULL,
    email character varying(120) NOT NULL,
    nr_telefon character varying(10) NOT NULL,
    id_user character varying(30) NOT NULL,
    id_ruta bigint NOT NULL,
    data_rezervare date NOT NULL
);


ALTER TABLE public.rezervare OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16622)
-- Name: rute; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rute (
    id_ruta bigint NOT NULL,
    nume_ruta character varying(40) NOT NULL
);


ALTER TABLE public.rute OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16625)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(30) NOT NULL,
    password character varying(128) NOT NULL,
    id_acces smallint NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 2841 (class 0 OID 16610)
-- Dependencies: 196
-- Data for Name: alocari; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (1, 2, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (2, 1, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (3, 4, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (4, 5, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (5, 6, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (6, 3, 'TM-03-ABC');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (7, 8, 'TM-03-ABE');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (8, 7, 'B-134-ABD');
INSERT INTO public.alocari (id_alocare, id_rezervare, id_masina) VALUES (9, 9, 'B-134-ABD');


--
-- TOC entry 2842 (class 0 OID 16613)
-- Dependencies: 197
-- Data for Name: categ_acces; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categ_acces (id_acces, tip_acces) VALUES (1, 'admin');
INSERT INTO public.categ_acces (id_acces, tip_acces) VALUES (2, 'user');


--
-- TOC entry 2843 (class 0 OID 16616)
-- Dependencies: 198
-- Data for Name: masini; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-03-ABC', 12);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-03-ABD', 5);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-03-ABE', 16);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-03-ABF', 5);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-03-ABG', 5);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-05-ABC', 16);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-05-ABD', 16);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-05-ABE', 5);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('TM-45-AAA', 5);
INSERT INTO public.masini (nr_masina, nr_locuri) VALUES ('B-134-ABD', 1);


--
-- TOC entry 2844 (class 0 OID 16619)
-- Dependencies: 199
-- Data for Name: rezervare; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (2, 'ALBERT', 'SERBAN', 'SERBAN.ABY15@YAHOO.COM', '0764347740', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (1, 'MIHAI', 'POPESCU', 'MIHAI.POPESCU@GMAIL.COM', '0712345678', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (3, 'POPOVICI', 'RADU', 'RADU.POPOVICI@YAHOO.COM', '0712345123', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (4, 'RADUT', 'MIHAI', 'MIHAI.RADUT@GMAIL.COM', '0712312312', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (6, 'POP', 'PAUL', 'PAULPAUL22@YAHOO.COM', '0758932214', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (5, 'ZILAU', 'RAUL', 'ZILAU.RAUL123@YAHOO.COM', '0723123241', 'admin', 1, '2019-10-10');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (7, 'SERBAN', 'VASILE', 'ALBERT.SERBAN@GMAIL.COM', '0712345688', 'admin', 2, '2019-10-11');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (8, 'POP', 'RADU', 'RADU.POP@YAHOO.COM', '0764343344', 'admin', 1, '2019-10-12');
INSERT INTO public.rezervare (id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, id_ruta, data_rezervare) VALUES (9, 'FILIP', 'FIAT', 'FILIP.FIAT@GMAIL.COM', '0723445566', 'admin', 3, '2019-10-10');


--
-- TOC entry 2845 (class 0 OID 16622)
-- Dependencies: 200
-- Data for Name: rute; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rute (id_ruta, nume_ruta) VALUES (1, 'TIMISOARA-BUDAPESTA');
INSERT INTO public.rute (id_ruta, nume_ruta) VALUES (2, 'TIMISOARA-BELGRAD');
INSERT INTO public.rute (id_ruta, nume_ruta) VALUES (3, 'BELGRAD-TIMISOARA');
INSERT INTO public.rute (id_ruta, nume_ruta) VALUES (4, 'BUDAPESTA-TIMISOARA');
INSERT INTO public.rute (id_ruta, nume_ruta) VALUES (5, 'VIENA-TIMISOARA');


--
-- TOC entry 2846 (class 0 OID 16625)
-- Dependencies: 201
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (username, password, id_acces) VALUES ('user', 'siit1234', 2);
INSERT INTO public.users (username, password, id_acces) VALUES ('admin', '$2a$10$GOuis1pHQqQM6KOeXXXys.8tzkTvaWDj4am.oLkQ4P4qq3zxru6vi', 1);


--
-- TOC entry 2704 (class 2606 OID 16629)
-- Name: alocari alocari_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alocari
    ADD CONSTRAINT alocari_pkey PRIMARY KEY (id_alocare);


--
-- TOC entry 2706 (class 2606 OID 16631)
-- Name: categ_acces categ_acces_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categ_acces
    ADD CONSTRAINT categ_acces_pkey PRIMARY KEY (id_acces);


--
-- TOC entry 2708 (class 2606 OID 16633)
-- Name: masini masini_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.masini
    ADD CONSTRAINT masini_pkey PRIMARY KEY (nr_masina);


--
-- TOC entry 2710 (class 2606 OID 16635)
-- Name: rezervare rezervare_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezervare
    ADD CONSTRAINT rezervare_pkey PRIMARY KEY (id_rezervare);


--
-- TOC entry 2712 (class 2606 OID 16637)
-- Name: rute rute_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rute
    ADD CONSTRAINT rute_pkey PRIMARY KEY (id_ruta);


--
-- TOC entry 2714 (class 2606 OID 16639)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 2719 (class 2606 OID 16640)
-- Name: users fk_id_acces; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_id_acces FOREIGN KEY (id_acces) REFERENCES public.categ_acces(id_acces);


--
-- TOC entry 2715 (class 2606 OID 16645)
-- Name: alocari fk_id_masina; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alocari
    ADD CONSTRAINT fk_id_masina FOREIGN KEY (id_masina) REFERENCES public.masini(nr_masina);


--
-- TOC entry 2716 (class 2606 OID 16650)
-- Name: alocari fk_id_rez; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alocari
    ADD CONSTRAINT fk_id_rez FOREIGN KEY (id_rezervare) REFERENCES public.rezervare(id_rezervare);


--
-- TOC entry 2717 (class 2606 OID 16655)
-- Name: rezervare fk_id_ruta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezervare
    ADD CONSTRAINT fk_id_ruta FOREIGN KEY (id_ruta) REFERENCES public.rute(id_ruta);


--
-- TOC entry 2718 (class 2606 OID 16660)
-- Name: rezervare fk_id_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezervare
    ADD CONSTRAINT fk_id_user FOREIGN KEY (id_user) REFERENCES public.users(username);


-- Completed on 2019-10-11 20:01:17

--
-- PostgreSQL database dump complete
--

