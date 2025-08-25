--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)

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

SET default_table_access_method = heap;

--
-- Name: action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.action (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.action OWNER TO postgres;

--
-- Name: action_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.action_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.action_id_seq OWNER TO postgres;

--
-- Name: action_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.action_id_seq OWNED BY public.action.id;


--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    id character varying(255) NOT NULL,
    active_code character varying(255),
    email character varying(255),
    facebook_account character varying(255),
    full_name character varying(255),
    is_active boolean,
    password character varying(255) NOT NULL,
    phone_number character varying(255)
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: attribute_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attribute_group (
    id integer NOT NULL,
    creation_time timestamp without time zone,
    description text,
    name character varying(255) NOT NULL,
    order_number integer,
    show_home boolean,
    slug character varying(255),
    update_time timestamp without time zone,
    avatar_id integer,
    parent_id integer
);


ALTER TABLE public.attribute_group OWNER TO postgres;

--
-- Name: attribute_group_image_slider_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attribute_group_image_slider_items (
    attribute_group_id integer NOT NULL,
    image_slider_item integer NOT NULL
);


ALTER TABLE public.attribute_group_image_slider_items OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    creation_time timestamp without time zone,
    description text,
    name character varying(255) NOT NULL,
    order_number integer,
    show_home boolean,
    slug character varying(255),
    update_time timestamp without time zone,
    avatar_id integer,
    parent_id integer
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_image_slider_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_image_slider_items (
    category_id integer NOT NULL,
    image_slider_item integer NOT NULL
);


ALTER TABLE public.category_image_slider_items OWNER TO postgres;

--
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.city OWNER TO postgres;

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_id_seq OWNER TO postgres;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    content text NOT NULL,
    is_purchased boolean,
    rate double precision,
    title character varying(255) NOT NULL,
    parent_id integer,
    product_id integer,
    user_id character varying(255)
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- Name: comment_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment_status (
    id integer NOT NULL,
    "time" timestamp without time zone,
    status_id character varying(255),
    comment_id integer
);


ALTER TABLE public.comment_status OWNER TO postgres;

--
-- Name: common; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.common (
    id integer NOT NULL,
    min_index_warehouse_manager integer
);


ALTER TABLE public.common OWNER TO postgres;

--
-- Name: district; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.district (
    id integer NOT NULL,
    name character varying(255),
    city_id integer
);


ALTER TABLE public.district OWNER TO postgres;

--
-- Name: district_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.district_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.district_id_seq OWNER TO postgres;

--
-- Name: district_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.district_id_seq OWNED BY public.district.id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: image_slider_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image_slider_item (
    id integer NOT NULL,
    caption text,
    link text,
    title character varying(255),
    url text NOT NULL
);


ALTER TABLE public.image_slider_item OWNER TO postgres;

--
-- Name: item_of_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_of_order (
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    applying_number integer,
    deduction double precision,
    error_code integer,
    final_price double precision,
    price_of_item double precision,
    promotion_code character varying(255),
    quantity integer NOT NULL
);


ALTER TABLE public.item_of_order OWNER TO postgres;

--
-- Name: order_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_status (
    id integer NOT NULL,
    "time" timestamp without time zone,
    status_id character varying(255),
    order_id integer
);


ALTER TABLE public.order_status OWNER TO postgres;

--
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    order_id integer NOT NULL,
    purchased boolean,
    "time" timestamp without time zone,
    token character varying(255) NOT NULL
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- Name: permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.permission OWNER TO postgres;

--
-- Name: permission_action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_action (
    action_id integer NOT NULL,
    permission_id integer NOT NULL,
    licensed boolean
);


ALTER TABLE public.permission_action OWNER TO postgres;

--
-- Name: permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permission_id_seq OWNER TO postgres;

--
-- Name: permission_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    content text,
    creation_time timestamp without time zone,
    description text,
    is_hidden boolean,
    name character varying(255) NOT NULL,
    order_number integer,
    slug character varying(255),
    update_time timestamp without time zone,
    average_rate double precision,
    original_price double precision,
    price double precision,
    quantity integer,
    question_and_answer text,
    rate_number integer,
    warranty_period integer,
    weight double precision,
    avatar_id integer,
    promotion_id integer
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_attribute_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_attribute_group (
    attribute_group_id integer NOT NULL,
    product_id integer NOT NULL,
    addition_price real
);


ALTER TABLE public.product_attribute_group OWNER TO postgres;

--
-- Name: product_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_category (
    category_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.product_category OWNER TO postgres;

--
-- Name: product_image_slider_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_image_slider_item (
    product_id integer NOT NULL,
    image_slider_item integer NOT NULL
);


ALTER TABLE public.product_image_slider_item OWNER TO postgres;

--
-- Name: product_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_order (
    id integer NOT NULL,
    current_status_id character varying(255),
    final_price double precision,
    is_available boolean,
    payment_time timestamp without time zone,
    shipping_fee double precision,
    total_price double precision NOT NULL,
    total_weight double precision NOT NULL,
    user_id character varying(255)
);


ALTER TABLE public.product_order OWNER TO postgres;

--
-- Name: product_product_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_product_type (
    product_id integer NOT NULL,
    product_type_id integer NOT NULL
);


ALTER TABLE public.product_product_type OWNER TO postgres;

--
-- Name: product_rate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_rate (
    product_id integer NOT NULL,
    user_id character varying(255) NOT NULL,
    rate double precision
);


ALTER TABLE public.product_rate OWNER TO postgres;

--
-- Name: product_return; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_return (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    order_id integer
);


ALTER TABLE public.product_return OWNER TO postgres;

--
-- Name: product_return_image_slider_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_return_image_slider_item (
    id integer NOT NULL,
    caption text,
    link text,
    title character varying(255),
    url text NOT NULL,
    returned_variant_product_id character varying(255)
);


ALTER TABLE public.product_return_image_slider_item OWNER TO postgres;

--
-- Name: product_return_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_return_status (
    id integer NOT NULL,
    "time" timestamp without time zone,
    status_id character varying(255),
    product_return_id integer
);


ALTER TABLE public.product_return_status OWNER TO postgres;

--
-- Name: product_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_type (
    id integer NOT NULL,
    creation_time timestamp without time zone,
    description text,
    name character varying(255) NOT NULL,
    order_number integer,
    show_home boolean,
    slug character varying(255),
    update_time timestamp without time zone,
    avatar_id integer,
    parent_id integer
);


ALTER TABLE public.product_type OWNER TO postgres;

--
-- Name: product_type_image_slider_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_type_image_slider_items (
    product_type_id integer NOT NULL,
    image_slider_item integer NOT NULL
);


ALTER TABLE public.product_type_image_slider_items OWNER TO postgres;

--
-- Name: promotion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.promotion (
    id integer NOT NULL,
    code character varying(255) NOT NULL,
    discount_money real,
    discount_percent integer,
    name character varying(255) NOT NULL,
    remaining_number integer,
    validity_period timestamp without time zone
);


ALTER TABLE public.promotion OWNER TO postgres;

--
-- Name: returned_product_of_shop; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.returned_product_of_shop (
    id character varying(255) NOT NULL,
    content text NOT NULL,
    quantity integer NOT NULL,
    product_return_id integer,
    product_return_status_id integer
);


ALTER TABLE public.returned_product_of_shop OWNER TO postgres;

--
-- Name: shipping_fee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shipping_fee (
    id integer NOT NULL,
    additional_price real,
    internal_city boolean,
    max_weight real,
    min_weight real,
    price real
);


ALTER TABLE public.shipping_fee OWNER TO postgres;

--
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.status OWNER TO postgres;

--
-- Name: store_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.store_request (
    id integer NOT NULL,
    quantity integer NOT NULL,
    "time" timestamp without time zone NOT NULL,
    product_of_shop_id integer
);


ALTER TABLE public.store_request OWNER TO postgres;

--
-- Name: transfer_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transfer_request (
    id integer NOT NULL,
    quantity integer NOT NULL,
    "time" timestamp without time zone NOT NULL,
    product_of_shop_id integer
);


ALTER TABLE public.transfer_request OWNER TO postgres;

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id character varying(255) NOT NULL,
    active_code character varying(255),
    email character varying(255),
    facebook_account character varying(255),
    full_name character varying(255),
    is_active boolean,
    password character varying(255) NOT NULL,
    phone_number character varying(255),
    birth_date date,
    email_verifier boolean,
    is_female boolean,
    master boolean
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- Name: user_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_address (
    id integer NOT NULL,
    street text NOT NULL,
    ward_id integer,
    user_id character varying(255)
);


ALTER TABLE public.user_address OWNER TO postgres;

--
-- Name: user_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_permission (
    permission_id integer NOT NULL,
    user_id character varying(255) NOT NULL,
    licensed boolean
);


ALTER TABLE public.user_permission OWNER TO postgres;

--
-- Name: user_search_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_search_history (
    id integer NOT NULL,
    keyword character varying(255),
    "time" timestamp without time zone,
    user_id character varying(255)
);


ALTER TABLE public.user_search_history OWNER TO postgres;

--
-- Name: ward; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ward (
    id integer NOT NULL,
    name character varying(255),
    district_id integer
);


ALTER TABLE public.ward OWNER TO postgres;

--
-- Name: ward_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ward_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ward_id_seq OWNER TO postgres;

--
-- Name: ward_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ward_id_seq OWNED BY public.ward.id;


--
-- Name: action id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.action ALTER COLUMN id SET DEFAULT nextval('public.action_id_seq'::regclass);


--
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- Name: district id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.district ALTER COLUMN id SET DEFAULT nextval('public.district_id_seq'::regclass);


--
-- Name: permission id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);


--
-- Name: ward id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ward ALTER COLUMN id SET DEFAULT nextval('public.ward_id_seq'::regclass);


--
-- Data for Name: action; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.action (id, name) FROM stdin;
1	view post
2	create post
3	edit post
4	delete post
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin (id, active_code, email, facebook_account, full_name, is_active, password, phone_number) FROM stdin;
\.


--
-- Data for Name: attribute_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attribute_group (id, creation_time, description, name, order_number, show_home, slug, update_time, avatar_id, parent_id) FROM stdin;
74	2022-10-11 23:08:08.07	\N	Màu sắc	\N	\N	mau-sac	2022-10-11 23:08:08.07	\N	\N
75	2022-10-11 23:08:19.423	\N	Vàng	\N	\N	vang	2022-10-11 23:08:19.423	\N	74
76	2022-10-11 23:08:38.202	\N	Tím	\N	\N	tim	2022-10-11 23:08:38.202	\N	74
80	2022-10-11 23:26:52.731	\N	test attribute 1	\N	\N	test-attribute-	2022-10-11 23:26:52.731	\N	75
\.


--
-- Data for Name: attribute_group_image_slider_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attribute_group_image_slider_items (attribute_group_id, image_slider_item) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, creation_time, description, name, order_number, show_home, slug, update_time, avatar_id, parent_id) FROM stdin;
3	2022-10-10 00:46:04.24	\N	Danh mục 1	\N	t	danh-muc-	2022-10-10 00:46:04.24	\N	\N
5	2022-10-10 00:47:13.405	\N	Danh mục 2	\N	t	danh-muc-_2	2022-10-10 00:47:13.405	\N	\N
4	2022-10-10 00:46:48.922	\N	Danh mục 1.1	\N	t	danh-muc-_1	2022-10-10 00:46:48.922	\N	3
6	2022-10-10 00:47:42.206	\N	Danh mục 2.1	\N	t	danh-muc-2.1	2022-10-10 00:47:42.206	\N	5
22	2022-10-10 02:09:39.239	\N	Danh mục 3	\N	\N	danh-muc-_3	2022-10-10 02:09:39.239	\N	\N
25	2022-10-10 08:51:18	\N	Danh mục 4	\N	\N	danh-muc-_4	2022-10-10 08:51:18	\N	\N
28	\N	\N	Danh mục 5.2	\N	\N	danh-muc-_7	2022-10-10 10:28:24.235	36	26
27	\N	\N	Danh mục 5.1	\N	\N	danh-muc-_6	2022-10-10 13:05:26.194	\N	26
29	\N	\N	Danh mục 5.3	\N	\N	danh-muc-_8	2022-10-10 15:06:19.389	\N	26
30	2022-10-10 09:32:13.399	\N	Danh mục 5.3.1	\N	\N	danh-muc-_9	2022-10-10 16:04:08.682	\N	29
26	\N	\N	Danh mục 5	\N	\N	danh-muc-_5	2022-10-10 16:15:36.221	\N	\N
\.


--
-- Data for Name: category_image_slider_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category_image_slider_items (category_id, image_slider_item) FROM stdin;
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.city (id, name) FROM stdin;
\.


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, content, is_purchased, rate, title, parent_id, product_id, user_id) FROM stdin;
\.


--
-- Data for Name: comment_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment_status (id, "time", status_id, comment_id) FROM stdin;
\.


--
-- Data for Name: common; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.common (id, min_index_warehouse_manager) FROM stdin;
\.


--
-- Data for Name: district; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.district (id, name, city_id) FROM stdin;
\.


--
-- Data for Name: image_slider_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image_slider_item (id, caption, link, title, url) FROM stdin;
2	\N	\N	\N	http://localhost:8081/files/x_purple.png
8	\N	\N	\N	http://localhost:8081/files/x_green.png
23	\N	\N	\N	http://localhost:8081/files/x_purple.png
31	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
32	\N	\N	\N	http://localhost:8081/files/z_bcdefbaadbcccb.jpg
33	\N	\N	\N	http://localhost:8081/files/z_bcdefbaadbcccb.jpg
34	\N	\N	\N	http://localhost:8081/files/banner.jpg
35	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
36	\N	\N	\N	http://localhost:8081/files/logo.jpg
37	\N	\N	\N	http://localhost:8081/files/x_blue.png
38	\N	\N	\N	http://localhost:8081/files/z_bcdefbaadbcccb.jpg
39	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
40	\N	\N	\N	http://localhost:8081/files/logo.jpg
41	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
42	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
43	\N	\N	\N	http://localhost:8081/files/logo.jpg
44	\N	\N	\N	http://localhost:8081/files/x_orange.png
45	\N	\N	\N	http://localhost:8081/files/x_green.png
46	\N	\N	\N	http://localhost:8081/files/x_orange.png
48	\N	\N	\N	http://localhost:8081/files/logo.jpg
49	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
50	\N	\N	\N	http://localhost:8081/files/logo.jpg
51	\N	\N	\N	http://localhost:8081/files/logo.jpg
71	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
72	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
73	\N	\N	\N	http://localhost:8081/files/logo.jpg
78	\N	\N	\N	http://localhost:8081/files/z_bcdefbaadbcccb.jpg
91	\N	\N	\N	http://localhost:8081/files/z_bcdefbaadbcccb.jpg
92	\N	\N	\N	http://localhost:8081/files/screenshot_from_--_--.png
\.


--
-- Data for Name: item_of_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_of_order (order_id, product_id, applying_number, deduction, error_code, final_price, price_of_item, promotion_code, quantity) FROM stdin;
20	24	\N	0	0	400000	100000		4
\.


--
-- Data for Name: order_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_status (id, "time", status_id, order_id) FROM stdin;
21	2022-10-10 01:36:33.11	order_init	20
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (order_id, purchased, "time", token) FROM stdin;
\.


--
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission (id, name) FROM stdin;
1	Full
2	Admin
3	Edit
4	Create
5	View only
\.


--
-- Data for Name: permission_action; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_action (action_id, permission_id, licensed) FROM stdin;
2	5	f
3	4	f
3	5	f
4	3	f
4	4	f
4	5	f
1	1	t
2	1	t
3	1	t
4	1	t
1	2	t
2	2	t
3	2	t
4	2	t
1	3	t
3	3	t
1	4	t
2	4	t
1	5	t
2	3	t
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, content, creation_time, description, is_hidden, name, order_number, slug, update_time, average_rate, original_price, price, quantity, question_and_answer, rate_number, warranty_period, weight, avatar_id, promotion_id) FROM stdin;
9	<p>🙂<span class="fr-emoticon fr-deletable fr-emoticon-img" style="background: url(https://cdnjs.cloudflare.com/ajax/libs/emojione/2.0.1/assets/svg/1f602.svg);">&nbsp;</span>  Xin ch&agrave;o<img src="data:image/jpeg;base64,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" style="width: 300px;" class="fr-fic fr-dib"></p>	2022-10-10 01:10:50.747	\N	\N	Sản phẩm 1	\N	san-pham-1	2022-10-10 01:10:50.747	\N	150000	100000	10	hihi	\N	\N	\N	8	\N
24	<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAJYBAMAAAC+wq27AAAAG1BMVEU2Yc////9PdNXM1/Pl6/masOdoiNuzw+2BnOH1bvIZAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAEPElEQVR4nO3ZTXPbRBgAYKHEjo+IOKTHJOOBa8ylHKMO/Tg2DJnmmHQy0GMCZbjG0DL92Wi1kryUaawx1hhmnudgex3tq3e1692VkmUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/y/5UfvpzcVl9zkbv7/4vUft8cXFn/8qwObMz5oPPxRFMX3ZFEZlVXq6svJPRVJpnQCbMyrO4oeb4umHV+X0qC6My+nl+7fFkxWV94r9y6rS2doBNui6aci4eFa9Tsp46uvpVfX6rsnqk+YH4YD85doBNqhshtZsP75Nw2tensa3swfr7tTZNtYJsEF7+9fxXHfxSuZFuLx7zZU8Pniw8vmXSWGdABu0eBIbkhf3TXKhePwoFkbTh+p2ddYNsEF5eRUb0p2xTqFOJvso1X8Y7aeFNQJs0M5BFhuy246BvZBd2U6id6cPVN5NR9Y6ATbo/LRpyPEXzTeTIlzHo6a0+Lw9ctTVaXNsajZWBhjUuDphTGfRJhVymHQju0tveWn3ugF1fpuEWhlgULuP2ut63g2B8j4Z/LuP2k+z9ru7bkDN76u0fzyJhZUBBnV32zbkrru685fZ3mFb2Ommz2ZlqBbzq/ar6tL/EfYhR70CDKkeAbEh827kVxktz77MqO2SZYfkRbUref7b2+KgX4ABHYekYkOq8bDMYzkekjxilyQdMp6Oip+r96+L214BBlRfxY8bUg32ZR7pUlF3ybJDqv5cPKs/LA77BRhMPEnvhoQuSTqkakizD5mEL7fZkOt6d9S7IaFLkg6p8m8LYWreYkOq7Ul469+QvHycdEh1J9NOVGGx2GJDduLvsJm1ujzCpPOJ3+qsSDclk24fFfLuFWAYzVrcd/oNpSLdOU26fUg4anvTb7sf6rkg1gdN04ESNlXR6HCbC2I7jntuUbLQIa/LpEvGXY+E47e3RVlMv6uV1fttrz1fNWXN0ul42ZDDbW4aF8XSaVzkg5BD3k1OXXpZXNTztEu6iSqMpB4BBpKfRIsnJyfpfVG9THf3RclOvV5D0i7p/hjGT48AA+t7qxsX9bRLupumMH62fKubtQ1Z/eygWdSTLvms7YSwsm/54UPW3bCex6c54zqfUbOLul7OOe0uK+mSUfNrH9d/WhVgcE1DduOpb+IFj88Lx8k46nZZSZfMm6eKh30CDK5pSF6+yMJFjrnNpvfV63nyxPPmqvmQv+i+mxXfZ+F+5LRPgMG1z0JmxeHFL8V+PHNeFr++mhffPFw1nxePqzoHawfYqO6hzrtqQdm/bwqT8F+Bb1fVncyro54frR9gGF99WP7PJsvffHjdo87fjlonAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMB/zV/nvsRSu7oR6QAAAABJRU5ErkJggg=="><br>	2022-10-10 02:30:52.343	\N	\N	Sản phẩm 2	\N	san-pham-	2022-10-10 02:30:52.343	\N	120000	100000	14	\N	\N	\N	\N	23	\N
69	\N	2022-10-11 21:03:30.161	\N	\N	San pham C4	\N	san-pham-c_3	2022-10-11 21:03:30.161	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
70	\N	2022-10-11 21:07:57.901	\N	\N	San pham C5	\N	san-pham-c_4	2022-10-11 21:07:57.901	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
67	\N	\N	\N	\N	San pham C2	\N	san-pham-c_1	2022-10-11 21:13:44.026	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
79	<p>Xin ch&agrave;o c&aacute;c bạn</p>	2022-10-11 23:11:29.265	\N	\N	Mẫu nè	\N	mau-ne	2022-10-11 23:11:29.265	\N	\N	\N	\N	\N	\N	\N	\N	78	\N
81	\N	2022-10-12 00:01:03.781	\N	\N	Xin chào	\N	xin-chao	2022-10-12 00:01:03.781	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
83	\N	2022-10-12 00:11:29.866	\N	\N	abc	\N	abc_2	2022-10-12 00:11:29.866	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
84	\N	2022-10-12 00:48:13.164	\N	\N	gfhgn	\N	gfhgn	2022-10-12 00:48:13.164	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
85	\N	2022-10-12 00:49:21.613	\N	\N	gfhgnh	\N	gfhgnh	2022-10-12 00:49:21.613	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
86	\N	2022-10-12 00:49:33.154	\N	\N	gfhgnhh	\N	gfhgnhh	2022-10-12 00:49:33.154	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
87	\N	2022-10-12 00:50:05.126	\N	\N	gfhgnhh	\N	gfhgnhh_1	2022-10-12 00:50:05.126	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
52	\N	\N	\N	\N	Sản phẩm mẫu 2	\N	san-pham-mau-2	2022-10-11 11:32:22.767	\N	\N	\N	\N	\N	\N	\N	\N	51	\N
53	\N	2022-10-11 11:33:01.205	\N	\N	Sản phẩm mẫu 3	\N	san-pham-mau-	2022-10-11 11:33:01.205	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
54	\N	2022-10-11 11:33:40.998	\N	\N	Sản phẩm mẫu 3	\N	san-pham-mau-_1	2022-10-11 11:33:40.998	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
55	\N	2022-10-11 11:35:45.43	\N	\N	Sản phẩm mẫu 3	\N	san-pham-mau-_2	2022-10-11 11:35:45.43	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
56	\N	2022-10-11 11:36:25.531	\N	\N	abc	\N	abc	2022-10-11 11:36:25.531	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
57	\N	2022-10-11 11:38:15.581	\N	\N	abc	\N	abc_1	2022-10-11 11:38:15.581	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
58	\N	2022-10-11 11:58:28.472	\N	\N	Sản phẩm mẫu 11	\N	san-pham-mau-_3	2022-10-11 11:58:28.472	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
59	\N	2022-10-11 12:44:17.901	\N	\N	abcd	\N	abcd	2022-10-11 12:44:17.901	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
60	\N	2022-10-11 12:46:32.184	\N	\N	ggg	\N	ggg	2022-10-11 12:46:32.184	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
61	\N	2022-10-11 17:48:08.732	\N	\N	Sản phẩm mẫu uu	\N	san-pham-mau-uu	2022-10-11 17:48:08.732	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
62	\N	2022-10-11 17:49:57.153	\N	\N	dfgdfg	\N	dfgdfg	2022-10-11 17:49:57.153	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
63	\N	2022-10-11 17:51:53.538	\N	\N	jkhj	\N	jkhj	2022-10-11 17:51:53.538	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
88	\N	2022-10-12 00:50:23.695	\N	\N	gfhgnhhy	\N	gfhgnhhy	2022-10-12 00:50:23.695	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
89	\N	2022-10-12 00:54:40.219	\N	\N	gfhgnhhy5	\N	gfhgnhhy_1	2022-10-12 00:54:40.219	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
90	\N	2022-10-12 00:55:02.812	\N	\N	gfhgnhhy57	\N	gfhgnhhy_2	2022-10-12 00:55:02.812	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
47	<p><img src="http://localhost:8081/files/logo.jpg" style="width: 300px;" class="fr-fic fr-dib"></p><p><br></p><p><a class="fr-file" href="http://localhost:8081/files/box_shadow.txt" target="_blank">box shadow.txt</a></p>	\N	Test ne	\N	San pham mau	\N	san-pham-mau	2022-10-11 20:42:41.395	\N	200000	150000	10	\N	\N	\N	\N	44	\N
64	\N	2022-10-11 20:54:44.797	\N	\N	San pham A	\N	san-pham-a	2022-10-11 20:54:44.797	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
65	\N	2022-10-11 20:57:15.438	\N	\N	San pham B	\N	san-pham-b	2022-10-11 20:57:15.438	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
66	\N	2022-10-11 20:59:23.257	\N	\N	San pham C	\N	san-pham-c	2022-10-11 20:59:23.257	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
68	\N	2022-10-11 21:02:28.996	\N	\N	San pham C3	\N	san-pham-c_2	2022-10-11 21:02:28.996	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
93	<p>Ahiiii</p>	2022-10-12 02:09:38.787	\N	\N	ghgh	\N	ghgh	2022-10-12 02:11:43.879	\N	\N	\N	\N	\N	\N	\N	\N	92	\N
82	<p>fghfngfg</p>	2022-10-12 00:07:26.633	\N	\N	aaa	\N	aaa	2022-10-12 06:54:56.657	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: product_attribute_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_attribute_group (attribute_group_id, product_id, addition_price) FROM stdin;
76	90	\N
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_category (category_id, product_id) FROM stdin;
5	90
3	90
26	90
27	90
28	90
\.


--
-- Data for Name: product_image_slider_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_image_slider_item (product_id, image_slider_item) FROM stdin;
\.


--
-- Data for Name: product_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_order (id, current_status_id, final_price, is_available, payment_time, shipping_fee, total_price, total_weight, user_id) FROM stdin;
20	order_init	400000	t	\N	0	400000	0	master
\.


--
-- Data for Name: product_product_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_product_type (product_id, product_type_id) FROM stdin;
\.


--
-- Data for Name: product_rate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_rate (product_id, user_id, rate) FROM stdin;
\.


--
-- Data for Name: product_return; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_return (id, email, phone_number, title, order_id) FROM stdin;
\.


--
-- Data for Name: product_return_image_slider_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_return_image_slider_item (id, caption, link, title, url, returned_variant_product_id) FROM stdin;
\.


--
-- Data for Name: product_return_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_return_status (id, "time", status_id, product_return_id) FROM stdin;
\.


--
-- Data for Name: product_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_type (id, creation_time, description, name, order_number, show_home, slug, update_time, avatar_id, parent_id) FROM stdin;
\.


--
-- Data for Name: product_type_image_slider_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_type_image_slider_items (product_type_id, image_slider_item) FROM stdin;
\.


--
-- Data for Name: promotion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.promotion (id, code, discount_money, discount_percent, name, remaining_number, validity_period) FROM stdin;
\.


--
-- Data for Name: returned_product_of_shop; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.returned_product_of_shop (id, content, quantity, product_return_id, product_return_status_id) FROM stdin;
\.


--
-- Data for Name: shipping_fee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shipping_fee (id, additional_price, internal_city, max_weight, min_weight, price) FROM stdin;
\.


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.status (id, name) FROM stdin;
order_init	Khởi tạo
\.


--
-- Data for Name: store_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.store_request (id, quantity, "time", product_of_shop_id) FROM stdin;
\.


--
-- Data for Name: transfer_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transfer_request (id, quantity, "time", product_of_shop_id) FROM stdin;
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_account (id, active_code, email, facebook_account, full_name, is_active, password, phone_number, birth_date, email_verifier, is_female, master) FROM stdin;
dinhvb		dinhvb1994@gmail.com	\N	dinhvb	t	e10adc3949ba59abbe56e057f20f883e	0123456789	2022-10-04	t	\N	f
master		dinhvb1993@gmail.com	\N	master	t	1a2b9906039961d886fc7d342ce47d88	0359030380	1993-02-20	t	\N	t
\.


--
-- Data for Name: user_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_address (id, street, ward_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_permission (permission_id, user_id, licensed) FROM stdin;
1	dinhvb	f
3	dinhvb	f
4	dinhvb	f
5	dinhvb	f
2	master	f
3	master	f
4	master	f
5	master	f
1	master	t
2	dinhvb	t
\.


--
-- Data for Name: user_search_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_search_history (id, keyword, "time", user_id) FROM stdin;
\.


--
-- Data for Name: ward; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ward (id, name, district_id) FROM stdin;
\.


--
-- Name: action_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.action_id_seq', 1, false);


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_id_seq', 1, false);


--
-- Name: district_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.district_id_seq', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 93, true);


--
-- Name: permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permission_id_seq', 1, false);


--
-- Name: ward_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ward_id_seq', 1, false);


--
-- Name: action action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.action
    ADD CONSTRAINT action_pkey PRIMARY KEY (id);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: attribute_group attribute_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_group
    ADD CONSTRAINT attribute_group_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: comment_status comment_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_status
    ADD CONSTRAINT comment_status_pkey PRIMARY KEY (id);


--
-- Name: common common_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.common
    ADD CONSTRAINT common_pkey PRIMARY KEY (id);


--
-- Name: district district_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.district
    ADD CONSTRAINT district_pkey PRIMARY KEY (id);


--
-- Name: image_slider_item image_slider_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_slider_item
    ADD CONSTRAINT image_slider_item_pkey PRIMARY KEY (id);


--
-- Name: item_of_order item_of_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_of_order
    ADD CONSTRAINT item_of_order_pkey PRIMARY KEY (order_id, product_id);


--
-- Name: order_status order_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_pkey PRIMARY KEY (id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (order_id);


--
-- Name: permission_action permission_action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_action
    ADD CONSTRAINT permission_action_pkey PRIMARY KEY (action_id, permission_id);


--
-- Name: permission permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- Name: product_attribute_group product_attribute_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_attribute_group
    ADD CONSTRAINT product_attribute_group_pkey PRIMARY KEY (attribute_group_id, product_id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (category_id, product_id);


--
-- Name: product_order product_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_product_type product_product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_product_type
    ADD CONSTRAINT product_product_type_pkey PRIMARY KEY (product_id, product_type_id);


--
-- Name: product_rate product_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_rate
    ADD CONSTRAINT product_rate_pkey PRIMARY KEY (product_id, user_id);


--
-- Name: product_return_image_slider_item product_return_image_slider_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return_image_slider_item
    ADD CONSTRAINT product_return_image_slider_item_pkey PRIMARY KEY (id);


--
-- Name: product_return product_return_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return
    ADD CONSTRAINT product_return_pkey PRIMARY KEY (id);


--
-- Name: product_return_status product_return_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return_status
    ADD CONSTRAINT product_return_status_pkey PRIMARY KEY (id);


--
-- Name: product_type product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_type
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (id);


--
-- Name: promotion promotion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promotion
    ADD CONSTRAINT promotion_pkey PRIMARY KEY (id);


--
-- Name: returned_product_of_shop returned_product_of_shop_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.returned_product_of_shop
    ADD CONSTRAINT returned_product_of_shop_pkey PRIMARY KEY (id);


--
-- Name: shipping_fee shipping_fee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shipping_fee
    ADD CONSTRAINT shipping_fee_pkey PRIMARY KEY (id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: store_request store_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store_request
    ADD CONSTRAINT store_request_pkey PRIMARY KEY (id);


--
-- Name: transfer_request transfer_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transfer_request
    ADD CONSTRAINT transfer_request_pkey PRIMARY KEY (id);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: user_address user_address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT user_address_pkey PRIMARY KEY (id);


--
-- Name: user_permission user_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT user_permission_pkey PRIMARY KEY (permission_id, user_id);


--
-- Name: user_search_history user_search_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_search_history
    ADD CONSTRAINT user_search_history_pkey PRIMARY KEY (id);


--
-- Name: ward ward_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ward
    ADD CONSTRAINT ward_pkey PRIMARY KEY (id);


--
-- Name: product_category fk2k3smhbruedlcrvu6clued06x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fk2k3smhbruedlcrvu6clued06x FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: category fk2y94svpmqttx80mshyny85wqr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fk2y94svpmqttx80mshyny85wqr FOREIGN KEY (parent_id) REFERENCES public.category(id);


--
-- Name: user_permission fk33gaj9f6hqijq4r2omi7nqvh0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fk33gaj9f6hqijq4r2omi7nqvh0 FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: product_image_slider_item fk38xjj79u7mgptyjspm2ks8bsw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image_slider_item
    ADD CONSTRAINT fk38xjj79u7mgptyjspm2ks8bsw FOREIGN KEY (image_slider_item) REFERENCES public.image_slider_item(id);


--
-- Name: product_product_type fk3n22y1fv816fo5iomi29gcfni; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_product_type
    ADD CONSTRAINT fk3n22y1fv816fo5iomi29gcfni FOREIGN KEY (product_type_id) REFERENCES public.product_type(id);


--
-- Name: comment_status fk3oj9p1r9edqt77sdsx0q2c6c4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_status
    ADD CONSTRAINT fk3oj9p1r9edqt77sdsx0q2c6c4 FOREIGN KEY (comment_id) REFERENCES public.comment(id);


--
-- Name: comment fk3y3uou7na66pfn512byon549s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk3y3uou7na66pfn512byon549s FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: product_type fk4m3sdyuagukt6q2p2f9bf0t5t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_type
    ADD CONSTRAINT fk4m3sdyuagukt6q2p2f9bf0t5t FOREIGN KEY (avatar_id) REFERENCES public.image_slider_item(id);


--
-- Name: product_rate fk4q3c2dwt4o9mpkxof291b44wu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_rate
    ADD CONSTRAINT fk4q3c2dwt4o9mpkxof291b44wu FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: order_status fk557pxsnv0n9xm4pi8hvxll0cs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT fk557pxsnv0n9xm4pi8hvxll0cs FOREIGN KEY (order_id) REFERENCES public.product_order(id);


--
-- Name: item_of_order fk5n496h4qoo0gt4ru7qbhevrcl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_of_order
    ADD CONSTRAINT fk5n496h4qoo0gt4ru7qbhevrcl FOREIGN KEY (order_id) REFERENCES public.product_order(id);


--
-- Name: attribute_group_image_slider_items fk6g9tyn9u25jw7uqyqj3pv25q3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_group_image_slider_items
    ADD CONSTRAINT fk6g9tyn9u25jw7uqyqj3pv25q3 FOREIGN KEY (attribute_group_id) REFERENCES public.attribute_group(id);


--
-- Name: product_return_status fk79he8gieimr6lgiej12ixm3cu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return_status
    ADD CONSTRAINT fk79he8gieimr6lgiej12ixm3cu FOREIGN KEY (product_return_id) REFERENCES public.product_return(id);


--
-- Name: product_return_image_slider_item fk7rfm14plx0akjl3vrbrrtsdmj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return_image_slider_item
    ADD CONSTRAINT fk7rfm14plx0akjl3vrbrrtsdmj FOREIGN KEY (returned_variant_product_id) REFERENCES public.returned_product_of_shop(id);


--
-- Name: product fk934vrx9jdwr1wq8u654ty43pw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk934vrx9jdwr1wq8u654ty43pw FOREIGN KEY (avatar_id) REFERENCES public.image_slider_item(id);


--
-- Name: attribute_group fk9uo77wxklsg9stqa008xthx6s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_group
    ADD CONSTRAINT fk9uo77wxklsg9stqa008xthx6s FOREIGN KEY (parent_id) REFERENCES public.attribute_group(id);


--
-- Name: user_address fk_6vktckonm9vn0m59v0a4x89iu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT fk_6vktckonm9vn0m59v0a4x89iu FOREIGN KEY (ward_id) REFERENCES public.ward(id);


--
-- Name: comment_status fk_7wfusmg0lwt6bnklefysn4duo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_status
    ADD CONSTRAINT fk_7wfusmg0lwt6bnklefysn4duo FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- Name: product_return_status fk_hft06qcguok7fqotgfaggoqud; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return_status
    ADD CONSTRAINT fk_hft06qcguok7fqotgfaggoqud FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- Name: order_status fk_nxug1e5aqt15mt90xexgr4ro3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT fk_nxug1e5aqt15mt90xexgr4ro3 FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- Name: returned_product_of_shop fkanmf9g1rt0xsouf8a6u7xa5od; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.returned_product_of_shop
    ADD CONSTRAINT fkanmf9g1rt0xsouf8a6u7xa5od FOREIGN KEY (product_return_status_id) REFERENCES public.product_return_status(id);


--
-- Name: category fkay81vfppw76jqt93y7si5ng0c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fkay81vfppw76jqt93y7si5ng0c FOREIGN KEY (avatar_id) REFERENCES public.image_slider_item(id);


--
-- Name: product_rate fkb8i99eiumjmer23ln6xw6g8nn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_rate
    ADD CONSTRAINT fkb8i99eiumjmer23ln6xw6g8nn FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: category_image_slider_items fkbkfb74knpi8tthbhcuwj6fouh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_image_slider_items
    ADD CONSTRAINT fkbkfb74knpi8tthbhcuwj6fouh FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: user_permission fkbklmo9kchans5u3e4va0ouo1s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fkbklmo9kchans5u3e4va0ouo1s FOREIGN KEY (permission_id) REFERENCES public.permission(id);


--
-- Name: product_attribute_group fkc9cjpx24f0v0qum99ircrkon; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_attribute_group
    ADD CONSTRAINT fkc9cjpx24f0v0qum99ircrkon FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product fkcli9x921yidy04cx25k6m46fy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkcli9x921yidy04cx25k6m46fy FOREIGN KEY (promotion_id) REFERENCES public.promotion(id);


--
-- Name: comment fkde3rfu96lep00br5ov0mdieyt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkde3rfu96lep00br5ov0mdieyt FOREIGN KEY (parent_id) REFERENCES public.comment(id);


--
-- Name: attribute_group_image_slider_items fkdkc1why5mkkqr6ip8xcq1qonq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_group_image_slider_items
    ADD CONSTRAINT fkdkc1why5mkkqr6ip8xcq1qonq FOREIGN KEY (image_slider_item) REFERENCES public.image_slider_item(id);


--
-- Name: user_search_history fkep8qqt060woutr5h3uch9nxn3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_search_history
    ADD CONSTRAINT fkep8qqt060woutr5h3uch9nxn3 FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: product_type_image_slider_items fkg2ly3on4ma52ioohy5kfkmaj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_type_image_slider_items
    ADD CONSTRAINT fkg2ly3on4ma52ioohy5kfkmaj FOREIGN KEY (image_slider_item) REFERENCES public.image_slider_item(id);


--
-- Name: store_request fkgiam212l8jwpcibjydxmgevoh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store_request
    ADD CONSTRAINT fkgiam212l8jwpcibjydxmgevoh FOREIGN KEY (product_of_shop_id) REFERENCES public.product(id);


--
-- Name: product_order fkhfex6omjonloe8iloine8owwv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order
    ADD CONSTRAINT fkhfex6omjonloe8iloine8owwv FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: permission_action fkimuev4af55s4dwna5tkblo6ct; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_action
    ADD CONSTRAINT fkimuev4af55s4dwna5tkblo6ct FOREIGN KEY (action_id) REFERENCES public.action(id);


--
-- Name: attribute_group fkink0cg4rmkdokje8vve4mek2i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_group
    ADD CONSTRAINT fkink0cg4rmkdokje8vve4mek2i FOREIGN KEY (avatar_id) REFERENCES public.image_slider_item(id);


--
-- Name: product_type fkkbyttfipfsi0uipm2su5e154d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_type
    ADD CONSTRAINT fkkbyttfipfsi0uipm2su5e154d FOREIGN KEY (parent_id) REFERENCES public.product_type(id);


--
-- Name: product_category fkkud35ls1d40wpjb5htpp14q4e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fkkud35ls1d40wpjb5htpp14q4e FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: product_return fklfiotv9tm8tawjxsvcve2t9ge; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_return
    ADD CONSTRAINT fklfiotv9tm8tawjxsvcve2t9ge FOREIGN KEY (order_id) REFERENCES public.product_order(id);


--
-- Name: transfer_request fklv737tkt8ilskxw9g6a4t1il5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transfer_request
    ADD CONSTRAINT fklv737tkt8ilskxw9g6a4t1il5 FOREIGN KEY (product_of_shop_id) REFERENCES public.product(id);


--
-- Name: comment fkm1rmnfcvq5mk26li4lit88pc5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkm1rmnfcvq5mk26li4lit88pc5 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_attribute_group fkn9f3nlugx6515mtypsu6ddagw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_attribute_group
    ADD CONSTRAINT fkn9f3nlugx6515mtypsu6ddagw FOREIGN KEY (attribute_group_id) REFERENCES public.attribute_group(id);


--
-- Name: category_image_slider_items fknhweha0q3j1hxmvniv5k5kb8s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_image_slider_items
    ADD CONSTRAINT fknhweha0q3j1hxmvniv5k5kb8s FOREIGN KEY (image_slider_item) REFERENCES public.image_slider_item(id);


--
-- Name: item_of_order fkokn9f6l891xvqetym6mccqudr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_of_order
    ADD CONSTRAINT fkokn9f6l891xvqetym6mccqudr FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_product_type fkpkt86314r6ds40xsp34ixr1tv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_product_type
    ADD CONSTRAINT fkpkt86314r6ds40xsp34ixr1tv FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: user_address fkpno2tvc0iefc1t42xgcdd3aq7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_address
    ADD CONSTRAINT fkpno2tvc0iefc1t42xgcdd3aq7 FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: returned_product_of_shop fkq19gadrpgskr72ktyf045e2lh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.returned_product_of_shop
    ADD CONSTRAINT fkq19gadrpgskr72ktyf045e2lh FOREIGN KEY (product_return_id) REFERENCES public.product_return(id);


--
-- Name: product_type_image_slider_items fkqftq4kq6k1xrxq4l7xtc56hw8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_type_image_slider_items
    ADD CONSTRAINT fkqftq4kq6k1xrxq4l7xtc56hw8 FOREIGN KEY (product_type_id) REFERENCES public.product_type(id);


--
-- Name: permission_action fkr1gmwoexm7lgco2qprowci9ow; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_action
    ADD CONSTRAINT fkr1gmwoexm7lgco2qprowci9ow FOREIGN KEY (permission_id) REFERENCES public.permission(id);


--
-- Name: district fksgx09prp6sk2f0we38bf2dtal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.district
    ADD CONSTRAINT fksgx09prp6sk2f0we38bf2dtal FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- Name: ward fkslko72wj5nauqvsgefqkvwpsb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ward
    ADD CONSTRAINT fkslko72wj5nauqvsgefqkvwpsb FOREIGN KEY (district_id) REFERENCES public.district(id);


--
-- Name: product_image_slider_item fksy1v06gncr23m2ehn62m0bpnr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image_slider_item
    ADD CONSTRAINT fksy1v06gncr23m2ehn62m0bpnr FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- PostgreSQL database dump complete
--

