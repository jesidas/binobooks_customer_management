-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2021-04-01 00:19:16
-- 服务器版本： 5.7.32-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `binobook_Repository`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL COMMENT '主键',
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `true_name`, `email`, `phone`, `create_date`, `update_date`, `user_password`, `name`, `is_valid`) VALUES
(10, 'AndyL', '222@222.com', '13322456780', NULL, '2021-03-27 13:08:24', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', 1),
(13, NULL, NULL, NULL, NULL, NULL, '123456', 'yueyang', 0),
(14, NULL, NULL, NULL, NULL, NULL, '123456', 'root', 1),
(42, 'ccc', '333@123.com', '13456789010', NULL, '2021-03-25 01:38:46', 'gnzLDuqKcGxMNKFokfhOew==', 'scott', 1),
(45, 'ZHangsan', 'peter@queensu.xa', '18876471234', '2021-03-24 23:11:13', '2021-03-24 23:11:13', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin01', 0),
(47, NULL, '123@126.com', '13345678901', '2021-03-24 23:42:59', '2021-03-24 23:42:59', '4QrcOUm6Wau+VuBX8g+IPg==', 'Andy01', 1),
(98, 'ZHangsan', 'peter@queensu.xa', '18876471234', '2021-03-31 19:29:56', '2021-03-31 19:29:56', '4QrcOUm6Wau+VuBX8g+IPg==', 'IT', NULL),
(99, 'string15', 'peter@queensu.xa', '13322456789', '2021-03-31 19:31:55', '2021-03-31 19:31:55', '4QrcOUm6Wau+VuBX8g+IPg==', 'Sales', NULL),
(100, 'Andy', 'string@s.com', '13345678901', '2021-03-31 19:32:27', '2021-03-31 19:32:27', '4QrcOUm6Wau+VuBX8g+IPg==', 'HR', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `book_category` int(11) NOT NULL,
  `book_des` varchar(200) DEFAULT NULL,
  `book_price` double NOT NULL,
  `sale` tinyint(1) NOT NULL,
  `url` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `book`
--

INSERT INTO `book` (`book_id`, `book_name`, `book_category`, `book_des`, `book_price`, `sale`, `url`) VALUES
(1, 'story of my life', 3, 'This birthday, give a child a creative gift that no one else in the whole world will have. Just add a child’s name and choose their age, and shazam! ', 11.23, 1, 'https://images.prismic.io/wonderbly/42f25920-8952-4562-a7ef-220f733edc9b_LMN_Mobile_First_Mobile_First_Carousel_01.jpg?auto=format%2Ccompress&dpr=&w=1100&h=&fit=&crop=&q=35&gif-q=90'),
(2, 'Game of Thrones', 2, 'mbled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s w', 19.99, 1, 'https://images.prismic.io/wonderbly/5143a854-0b5b-47c3-aab9-52bedfc55658_WAY_1_Carousel_06.jpg?auto=format%2Ccompress&dpr=&w=1100&h=&fit=&crop=&q=35&gif-q=90'),
(3, 'Little Women', 3, 'mbled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s w', 22.99, 1, 'https://images.prismic.io/wonderbly/fe7a4c71-a736-4bbb-a5d4-ff4896887074_LMN_Mobile_First_Mobile_First_Carousel_02.jpg?auto=format%2Ccompress&dpr=&w=1100&h=&fit=&crop=&q=35&gif-q=90'),
(4, 'Gone', 1, 'mbled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s w', 10.99, 1, 'https://images.prismic.io/wonderbly/c8aa5a43-4de1-474d-9293-4e650ebe0c4b_LMN_Mobile_First_Mobile_First_Carousel_03.jpg?auto=format%2Ccompress&dpr=&w=1100&h=&fit=&crop=&q=35&gif-q=90'),
(5, 'Steven Jobs', 1, 'mbled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s w', 25.99, 1, 'https://images.prismic.io/wonderbly/a4af8287-dbb8-45f2-86c3-b0eeca3b48f5_LMN_Mobile_First_Mobile_First_Carousel_04.jpg?auto=format%2Ccompress&dpr=&w=1100&h=&fit=&crop=&q=35&gif-q=90'),
(16, 'add a new book', 2, 'test description', 20.99, 1, 'http://159.75.20.131:9000/add-a-new-book/bookcover/4eb52515-0daa-4b44-afd7-ad19cf208d65-2.jpg'),
(19, 'try a new book', 1, 'test a new book', 22, 1, 'http://159.75.20.131:9000/try-a-new-book/bookcover/9fa7a221-9daa-4c98-9db7-decff39bb146-samplepage.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `bookSlideShow`
--

CREATE TABLE `bookSlideShow` (
  `img_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `cartItems`
--

CREATE TABLE `cartItems` (
  `cart_id` int(10) NOT NULL,
  `book_id` int(10) NOT NULL,
  `price` double(100,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(1, '0-3'),
(2, '3-6'),
(3, '6+');

-- --------------------------------------------------------

--
-- 表的结构 `characters`
--

CREATE TABLE `characters` (
  `characters_id` int(11) NOT NULL,
  `basic_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `characters`
--

INSERT INTO `characters` (`characters_id`, `basic_id`, `book_id`, `description`, `url`) VALUES
(1, 1, 1, 'brown hair', 'http://159.75.20.131:9000/image/8f5adae8-007e-4f6a-82f0-b5768e48acd8-brownshort.png'),
(4, 1, 1, 'golden hair', 'http://159.75.20.131:9000/image/aeddf68a-4c74-4d9c-942c-57a59da8a080-blondeshort.png'),
(5, 2, 1, 'white skin', 'http://159.75.20.131:9000/image/54991b79-dfdd-442d-9e5d-fc91d2551df3-skintone1.png'),
(10, 1, 2, 'brown hair', 'http://159.75.20.131:9000/game-of-thrones/characters/f395d51d-cfb7-4eb4-9c10-ef1b6dede52b-brownshort.png'),
(11, 2, 1, 'dark skin', 'http://159.75.20.131:9000/story-of-my-life/characters/4e196ab1-c8f5-48d2-a7d2-509378380f91-skintone3.png'),
(12, 2, 1, 'yellow skin', 'http://159.75.20.131:9000/story-of-my-life/characters/82d1d138-f78d-4453-8fbf-7393ecf55663-skintone2.png'),
(13, 1, 16, 'brown hair', 'http://159.75.20.131:9000/add-a-new-book/characters/f2f76545-45c5-4f50-8b40-28a993068428-brownshort.png'),
(14, 1, 16, 'blond hair', 'http://159.75.20.131:9000/add-a-new-book/characters/64c0bd05-d8ff-42b0-b276-82d3c43f101f-blondeshort.png'),
(15, 2, 16, 'white', 'http://159.75.20.131:9000/add-a-new-book/characters/571ea823-a16d-442a-8f96-de072e1f46f5-skintone1.png');

-- --------------------------------------------------------

--
-- 表的结构 `imagebasictype`
--

CREATE TABLE `imagebasictype` (
  `type_id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `imagebasictype`
--

INSERT INTO `imagebasictype` (`type_id`, `description`) VALUES
(1, 'hair'),
(2, 'skintone');

-- --------------------------------------------------------

--
-- 表的结构 `images`
--

CREATE TABLE `images` (
  `img_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `image_type` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL,
  `position_x` float NOT NULL,
  `position_y` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `images`
--

INSERT INTO `images` (`img_id`, `page_id`, `image_type`, `description`, `url`, `position_x`, `position_y`) VALUES
(1, 1, 1, 'brown hair ', 'http://159.75.20.131:9000/image/74137cfa-7b4d-4ac9-9f66-f59f152a06d8-brownshort.png', 950, 590),
(2, 1, 5, '', 'http://159.75.20.131:9000/image/aac2e232-bc97-48c3-a188-22b86085245f-skintone1.png', 950, 600),
(18, 1, 12, '', 'http://159.75.20.131:9000/story-of-my-life/page1/elements/2b3a95a5-36a3-4b26-83fc-34ab11c789ad-skintone2.png', 950, 600),
(19, 1, 11, '', 'http://159.75.20.131:9000/story-of-my-life/page1/elements/4226ed0f-72cf-41b1-b196-776612572d0d-skintone3.png', 950, 600),
(20, 1, 4, '', 'http://159.75.20.131:9000/story-of-my-life/page1/elements/68118fe3-38c2-400b-9749-170727639b7a-blondeshort.png', 950, 590),
(21, 32, 4, '', 'http://159.75.20.131:9000/story-of-my-life/page32/elements/52da7572-b8c8-4c17-904b-2555767d148f-blondeshort.png', 250, 250),
(22, 32, 11, '', 'http://159.75.20.131:9000/story-of-my-life/page32/elements/fa6b2d9c-80f0-4a4f-8e19-b719f588aadf-skintone3.png', 250, 255),
(23, 32, 1, '', 'http://159.75.20.131:9000/story-of-my-life/page32/elements/0604ca38-9303-4318-89b3-495471a9b1e7-brownshort.png', 250, 250),
(24, 32, 5, '', 'http://159.75.20.131:9000/story-of-my-life/page32/elements/0b1ff39d-9b72-4b42-97d2-ad53598510df-skintone1.png', 250, 255),
(25, 33, 13, '', 'http://159.75.20.131:9000/add-a-new-book/page33/elements/a950384e-6907-4992-800e-358def1ae04d-brownshort.png', 250, 250),
(26, 33, 14, '', 'http://159.75.20.131:9000/add-a-new-book/page33/elements/c59a4079-b36f-4d7e-be88-7fd33bc372fd-blondeshort.png', 250, 250),
(27, 33, 15, '', 'http://159.75.20.131:9000/add-a-new-book/page33/elements/c39d2be1-b59f-479d-aa81-45b054212089-skintone1.png', 250, 255);

-- --------------------------------------------------------

--
-- 表的结构 `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `module_name` varchar(255) DEFAULT NULL COMMENT 'Module Name',
  `module_style` varchar(255) DEFAULT NULL COMMENT 'Module Form',
  `url` varchar(255) DEFAULT NULL COMMENT 'Address',
  `parent_id` int(11) DEFAULT NULL,
  `parent_opt_value` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL COMMENT 'Grade',
  `opt_value` varchar(255) DEFAULT NULL COMMENT 'Option Value',
  `orders` int(11) DEFAULT NULL,
  `is_valid` tinyint(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `module`
--

INSERT INTO `module` (`id`, `module_name`, `module_style`, `url`, `parent_id`, `parent_opt_value`, `grade`, `opt_value`, `orders`, `is_valid`, `create_date`, `update_date`) VALUES
(8, 'Customer management20', '', 'customer/index', -1, NULL, 0, '20', 3, 1, '2017-07-01 00:00:00', '2017-07-01 00:00:00'),
(9, 'Customer Info 2010', '', 'customer/index', 8, NULL, 1, '2010', 1, 1, '2017-09-06 00:00:00', '2017-09-06 00:00:00'),
(10, 'Customer Info Create 201001', '', '#', 9, NULL, 2, '201001', 1, 1, '2017-07-01 00:00:00', '2017-07-01 00:00:00'),
(11, 'Customer Info Update201002', '', '#', 9, NULL, 2, '201002', 2, 1, '2017-07-01 00:00:00', '2017-07-01 00:00:00'),
(12, 'Customer Order 2020', '', 'customer_loss/index', 8, NULL, 1, '2020', 2, 1, '2017-08-17 00:00:00', '2017-08-17 00:00:00'),
(13, 'Customer Order Read 202001', '', 'openCustomerReprieve', 12, NULL, 2, '202001', 1, 1, '2017-09-23 00:00:00', '2017-09-23 00:00:00'),
(18, 'System Management 60', '', '#', -1, NULL, 0, '60', 6, 1, '2017-08-18 00:00:00', '2017-08-18 00:00:00'),
(19, 'Customer Info Deleting 201003', '', '#', 9, NULL, 2, '201003', 3, 1, '2017-08-18 00:00:00', '2021-03-30 21:41:22'),
(26, 'HR Management 6010', '', 'user/index', 18, NULL, 1, '6010', NULL, 1, '2017-10-24 16:54:12', '2017-10-24 16:54:12'),
(27, 'Role Management 6020', '', 'role/index', 18, NULL, 1, '6020', NULL, 1, '2018-01-13 11:29:17', '2018-01-13 11:29:19'),
(28, 'Module Management 6030', '', 'module/index/1', 18, NULL, 1, '6030', NULL, 1, '2018-01-13 11:29:40', '2018-01-13 11:29:42'),
(130, 'HR Create 601001', '', NULL, 26, NULL, 2, '601001', NULL, 1, '2020-02-17 15:55:45', '2020-02-17 15:55:45'),
(131, 'HR Read 601002', '', NULL, 26, NULL, 2, '601002', NULL, 1, '2020-02-17 15:56:04', '2020-02-17 15:56:04'),
(132, 'HR Update 601003', '', NULL, 26, NULL, 2, '601003', NULL, 1, '2020-02-17 15:56:20', '2020-02-17 15:56:20'),
(133, 'HR Delete 601004', '', NULL, 26, NULL, 2, '601004', NULL, 1, '2020-02-17 15:56:36', '2020-02-17 15:56:36'),
(134, 'Role Create 602001', '', NULL, 27, NULL, 2, '602001', NULL, 1, '2020-02-17 15:56:53', '2020-02-17 15:56:53'),
(135, 'Role Query 602002', '', NULL, 27, NULL, 2, '602002', NULL, 1, '2020-02-17 15:57:08', '2020-02-17 15:57:08'),
(136, 'Role Update 602003', '', NULL, 27, NULL, 2, '602003', NULL, 1, '2020-02-17 15:57:23', '2020-02-17 15:57:23'),
(137, 'Role Delete 602003', '', NULL, 27, NULL, 2, '602004', NULL, 1, '2020-02-17 15:57:37', '2020-02-17 15:57:37'),
(138, 'Module Create 603001', '', NULL, 28, NULL, 2, '603001', NULL, 1, '2020-02-17 15:57:57', '2020-02-17 15:57:57'),
(139, 'Module Read 603002', '', NULL, 28, NULL, 2, '603002', NULL, 1, '2020-02-17 15:58:18', '2020-02-17 15:58:18'),
(140, 'Module Update 603003', '', NULL, 28, NULL, 2, '603003', NULL, 1, '2020-02-17 15:58:31', '2020-02-17 15:58:31'),
(141, 'Module Delete 603004', '', NULL, 28, NULL, 2, '603004', NULL, 1, '2020-02-17 15:58:45', '2020-02-17 15:58:45'),
(142, 'IT Support Management 0640', '', 'data_dic/index', 18, NULL, 2, '6040', 1, 1, '2020-02-20 21:30:11', '2021-03-31 11:32:25'),
(143, 'IT Support Create 604001', '', NULL, 142, NULL, 2, '604001', NULL, 1, '2020-02-20 21:31:12', '2020-02-20 21:31:12'),
(144, 'IT Support Read 604002', '', NULL, 142, NULL, 2, '604002', NULL, 1, '2020-02-20 21:31:31', '2020-02-20 21:31:31'),
(145, 'IT Support Update 064003', '', NULL, 142, NULL, 2, '604003', NULL, 1, '2020-02-20 21:31:47', '2020-02-20 21:31:47'),
(146, 'IT Support Delete 604004', '', NULL, 142, NULL, 2, '604004', NULL, 1, '2020-02-20 21:32:03', '2020-02-20 21:32:03'),
(158, 'Test Management 100', '', NULL, -1, NULL, 0, '100', NULL, 1, '2020-11-13 03:32:21', '2020-11-13 04:26:26'),
(159, 'Test Manual 90', '', NULL, -1, NULL, 0, '90', NULL, 0, '2020-11-13 03:33:38', '2020-11-13 04:53:37'),
(160, 'Test Management 10010', '', 'test001/index', 158, NULL, 1, '10010', NULL, 1, '2020-11-13 03:34:14', '2020-11-13 04:26:49'),
(161, 'Test Management 1001001', '', NULL, 160, NULL, 2, '1001001', NULL, 1, '2020-11-13 03:34:34', '2020-11-13 04:27:18'),
(162, 'Test Manual 801003', '', NULL, 160, NULL, 2, '801003', NULL, 1, '2020-11-13 03:34:47', '2020-11-13 04:05:44'),
(163, 'IT Management 50', '', NULL, -1, NULL, 0, '50', 1, 1, '2021-03-31 11:45:58', '2021-03-31 11:45:58');

-- --------------------------------------------------------

--
-- 表的结构 `orderItems`
--

CREATE TABLE `orderItems` (
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `number_ordered` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `sub_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `sub_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `order_date`, `sub_total`) VALUES
(1, 10, '2021-03-16 21:19:47', 18.2),
(2, 10, '2021-03-16 21:19:47', 17.6),
(3, 9, '2021-03-29 00:00:00', 6.99),
(4, 9, '2021-03-29 00:00:00', 9.99),
(5, 9, '2021-03-29 00:00:00', 8.99),
(6, 9, '2021-03-29 00:00:00', 16.8),
(7, 9, '2021-03-30 00:00:00', 18.3),
(8, 9, '2021-03-29 00:00:00', 17.8),
(9, 9, '2021-03-30 00:00:00', 18);

-- --------------------------------------------------------

--
-- 表的结构 `pages`
--

CREATE TABLE `pages` (
  `page_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `pages`
--

INSERT INTO `pages` (`page_id`, `book_id`, `url`) VALUES
(1, 1, 'http://159.75.20.131:9000/image/a527c1d8-ba49-4e54-a33c-b37aa70f00ab-backgroundnotext.jpg'),
(32, 1, 'http://159.75.20.131:9000/story-of-my-life/pagecover/9fb818c3-7c3b-4d86-8de9-2cec57dc7344-backgroundnotext.jpg'),
(33, 16, 'http://159.75.20.131:9000/add-a-new-book/pagecover/12974809-04e8-46ae-a499-79d0d01fe405-backgroundnotext.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT 'Role ID',
  `module_id` int(11) DEFAULT NULL COMMENT 'Module ID',
  `acl_value` varchar(255) DEFAULT NULL COMMENT 'Permission Value',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `permission`
--

INSERT INTO `permission` (`id`, `role_id`, `module_id`, `acl_value`, `create_date`, `update_date`) VALUES
(8120, 19, 162, '801003', '2021-03-30 17:28:40', '2021-03-30 17:28:40'),
(8121, 19, 159, '90', '2021-03-30 17:28:40', '2021-03-30 17:28:40'),
(8462, 17, 158, '100', '2021-03-31 10:56:30', '2021-03-31 10:56:30'),
(8463, 17, 160, '10010', '2021-03-31 10:56:30', '2021-03-31 10:56:30'),
(8464, 17, 161, '1001001', '2021-03-31 10:56:30', '2021-03-31 10:56:30'),
(8465, 17, 162, '801003', '2021-03-31 10:56:30', '2021-03-31 10:56:30'),
(8466, 17, 159, '90', '2021-03-31 10:56:30', '2021-03-31 10:56:30'),
(8475, 18, 8, '20', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8476, 18, 9, '2010', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8477, 18, 10, '201001', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8478, 18, 11, '201002', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8479, 18, 19, '201003', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8480, 18, 12, '2020', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8481, 18, 13, '202001', '2021-03-31 10:57:09', '2021-03-31 10:57:09'),
(8500, 1, 8, '20', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8501, 1, 9, '2010', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8502, 1, 10, '201001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8503, 1, 11, '201002', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8504, 1, 12, '2020', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8505, 1, 13, '202001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8506, 1, 18, '60', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8507, 1, 26, '6010', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8508, 1, 130, '601001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8509, 1, 131, '601002', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8510, 1, 132, '601003', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8511, 1, 133, '601004', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8512, 1, 27, '6020', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8513, 1, 134, '602001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8514, 1, 135, '602002', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8515, 1, 136, '602003', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8516, 1, 137, '602004', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8517, 1, 28, '6030', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8518, 1, 138, '603001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8519, 1, 139, '603002', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8520, 1, 140, '603003', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8521, 1, 141, '603004', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8522, 1, 142, '6040', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8523, 1, 143, '604001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8524, 1, 144, '604002', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8525, 1, 145, '604003', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8526, 1, 146, '604004', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8527, 1, 158, '100', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8528, 1, 160, '10010', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8529, 1, 161, '1001001', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8530, 1, 162, '801003', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8531, 1, 159, '90', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8532, 1, 163, '50', '2021-03-31 11:53:10', '2021-03-31 11:53:10'),
(8550, 3, 18, '60', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8551, 3, 142, '6040', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8552, 3, 143, '604001', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8553, 3, 144, '604002', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8554, 3, 145, '604003', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8555, 3, 146, '604004', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8556, 3, 163, '50', '2021-03-31 11:58:20', '2021-03-31 11:58:20'),
(8610, 31, 18, '60', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8611, 31, 26, '6010', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8612, 31, 130, '601001', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8613, 31, 131, '601002', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8614, 31, 132, '601003', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8615, 31, 133, '601004', '2021-03-31 12:09:01', '2021-03-31 12:09:01'),
(8616, 2, 8, '20', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8617, 2, 9, '2010', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8618, 2, 10, '201001', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8619, 2, 11, '201002', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8620, 2, 19, '201003', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8621, 2, 12, '2020', '2021-03-31 12:14:22', '2021-03-31 12:14:22'),
(8622, 2, 13, '202001', '2021-03-31 12:14:22', '2021-03-31 12:14:22');

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_remark` varchar(255) DEFAULT NULL COMMENT 'Remark',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `role_name`, `role_remark`, `create_date`, `update_date`, `is_valid`) VALUES
(1, 'Admin', 'Admin', '2021-03-01 00:00:00', '2021-03-01 16:08:17', 1),
(2, 'Sales', 'Sales', '2021-03-01 00:00:00', '2021-03-01 16:08:18', 1),
(3, 'IT', 'IT', '2021-03-01 00:00:00', '2021-03-01 16:09:20', 1),
(17, 'Test Engineers', 'Admi', '2021-03-20 17:09:40', '2021-03-30 14:06:32', 1),
(19, 'Front Desk', 'Admi', '2021-03-30 17:28:28', '2021-03-30 17:28:28', 1),
(20, 'Front Table', 'Customer', '2021-03-30 18:56:50', '2021-03-30 18:56:50', 1),
(31, 'HR', 'H', '2021-03-31 11:12:48', '2021-03-31 11:12:48', 1);

-- --------------------------------------------------------

--
-- 表的结构 `shoppingcarts`
--

CREATE TABLE `shoppingcarts` (
  `cart_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `num_items` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `text`
--

CREATE TABLE `text` (
  `text_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `position_x` float NOT NULL,
  `position_y` float NOT NULL,
  `text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `text`
--

INSERT INTO `text` (`text_id`, `page_id`, `position_x`, `position_y`, `text`) VALUES
(5, 1, 200, 270, 'realPlaceholder'),
(6, 33, 450, 450, 'Hello, realPlaceholder '),
(7, 1, 180, 360, 'colours'),
(8, 1, 180, 450, 'with red.'),
(9, 1, 1650, 730, 'realPlaceholder'),
(10, 1, 1600, 820, 'colours'),
(11, 1, 1600, 910, 'wiht blue.');

-- --------------------------------------------------------

--
-- 表的结构 `Users`
--

CREATE TABLE `Users` (
  `name` varchar(50) DEFAULT NULL,
  `id` int(10) NOT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `perms` varchar(100) DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `Users`
--

INSERT INTO `Users` (`name`, `id`, `user_password`, `perms`, `true_name`, `email`, `phone`, `create_date`, `update_date`) VALUES
('yueyang', 1, '123456', 'user:add', '', '', '', NULL, NULL),
('root', 2, '123456', 'user:update', '', '', '', NULL, NULL),
('sam', 4, 'gnzLDuqKcGxMNKFokfhOew==', NULL, NULL, '333@123.com', NULL, NULL, NULL),
('andy', 6, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'string@qu.ca', '613-123-5678', NULL, NULL),
('Mary', 7, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, 'string', 'string', '13287693937', '2021-03-22 19:26:35', '2021-03-22 19:26:35'),
('admin00', 8, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, 'ZHangsan', 'peter@queensu.xa', '18876471234', '2021-03-31 19:18:50', '2021-03-31 19:18:50'),
('Customer', 9, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, 'ccc', 'peter@queensu.xa', '18876471234', '2021-03-31 19:33:31', '2021-03-31 19:33:31'),
('admin', 10, 'wgrU12/pd1mqJ6DJm/9nEA==', NULL, 'bino', NULL, '613-5678900', NULL, '2021-03-24 11:02:13'),
('string', 156, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:24', '2021-03-31 21:02:24'),
('string', 157, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:28', '2021-03-31 21:02:28'),
('string', 158, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:28', '2021-03-31 21:02:28'),
('string', 159, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:29', '2021-03-31 21:02:29'),
('string', 160, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:29', '2021-03-31 21:02:29'),
('string', 161, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '18876471234', '2021-03-31 21:02:29', '2021-03-31 21:02:29'),
('string15', 162, '4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, 'peter@queensu.xa', '13322456789', '2021-03-31 21:47:52', '2021-03-31 21:47:52');

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_date`, `update_date`) VALUES
(213, 42, 1, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(214, 42, 2, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(215, 42, 3, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(216, 42, 14, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(217, 42, 17, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(218, 42, 18, '2021-03-25 01:38:46', '2021-03-25 01:38:46'),
(1043, 179, 1, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1044, 179, 2, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1045, 179, 3, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1046, 179, 17, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1047, 179, 18, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1048, 179, 19, '2021-03-30 18:56:37', '2021-03-30 18:56:37'),
(1081, 188, 3, '2021-03-31 11:11:33', '2021-03-31 11:11:33'),
(1084, 191, 2, '2021-03-31 11:19:12', '2021-03-31 11:19:12'),
(1085, 192, 31, '2021-03-31 11:19:43', '2021-03-31 11:19:43'),
(1086, 10, 1, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1087, 10, 2, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1088, 10, 3, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1089, 10, 17, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1090, 10, 18, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1091, 10, 19, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1092, 10, 20, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1093, 10, 31, '2021-03-31 12:12:11', '2021-03-31 12:12:11'),
(1098, 178, 3, '2021-03-31 12:13:51', '2021-03-31 12:13:51'),
(1113, 98, 3, '2021-03-31 19:29:56', '2021-03-31 19:29:56'),
(1114, 99, 2, '2021-03-31 19:31:56', '2021-03-31 19:31:56'),
(1115, 100, 31, '2021-03-31 19:32:28', '2021-03-31 19:32:28');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `book_category` (`book_category`);

--
-- Indexes for table `bookSlideShow`
--
ALTER TABLE `bookSlideShow`
  ADD PRIMARY KEY (`img_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `cartItems`
--
ALTER TABLE `cartItems`
  ADD PRIMARY KEY (`cart_id`,`book_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `characters`
--
ALTER TABLE `characters`
  ADD PRIMARY KEY (`characters_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `basic_id` (`basic_id`);

--
-- Indexes for table `imagebasictype`
--
ALTER TABLE `imagebasictype`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`img_id`),
  ADD KEY `page_id` (`page_id`),
  ADD KEY `image_type` (`image_type`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `orderItems`
--
ALTER TABLE `orderItems`
  ADD PRIMARY KEY (`order_id`,`book_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `pages`
--
ALTER TABLE `pages`
  ADD PRIMARY KEY (`page_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shoppingcarts`
--
ALTER TABLE `shoppingcarts`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `text`
--
ALTER TABLE `text`
  ADD PRIMARY KEY (`text_id`),
  ADD KEY `page_id` (`page_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=114;
--
-- 使用表AUTO_INCREMENT `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- 使用表AUTO_INCREMENT `bookSlideShow`
--
ALTER TABLE `bookSlideShow`
  MODIFY `img_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- 使用表AUTO_INCREMENT `characters`
--
ALTER TABLE `characters`
  MODIFY `characters_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- 使用表AUTO_INCREMENT `imagebasictype`
--
ALTER TABLE `imagebasictype`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- 使用表AUTO_INCREMENT `images`
--
ALTER TABLE `images`
  MODIFY `img_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- 使用表AUTO_INCREMENT `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;
--
-- 使用表AUTO_INCREMENT `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- 使用表AUTO_INCREMENT `pages`
--
ALTER TABLE `pages`
  MODIFY `page_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- 使用表AUTO_INCREMENT `permission`
--
ALTER TABLE `permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8623;
--
-- 使用表AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- 使用表AUTO_INCREMENT `text`
--
ALTER TABLE `text`
  MODIFY `text_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- 使用表AUTO_INCREMENT `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=165;
--
-- 使用表AUTO_INCREMENT `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1116;
--
-- 限制导出的表
--

--
-- 限制表 `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_category`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `bookSlideShow`
--
ALTER TABLE `bookSlideShow`
  ADD CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `cartItems`
--
ALTER TABLE `cartItems`
  ADD CONSTRAINT `cartItems_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `shoppingcarts` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cartItems_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `characters`
--
ALTER TABLE `characters`
  ADD CONSTRAINT `characters_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `characters_ibfk_2` FOREIGN KEY (`basic_id`) REFERENCES `imagebasictype` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`page_id`) REFERENCES `pages` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `images_ibfk_2` FOREIGN KEY (`image_type`) REFERENCES `characters` (`characters_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `orderItems`
--
ALTER TABLE `orderItems`
  ADD CONSTRAINT `orderItems_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orderItems_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `pages`
--
ALTER TABLE `pages`
  ADD CONSTRAINT `pages_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `shoppingcarts`
--
ALTER TABLE `shoppingcarts`
  ADD CONSTRAINT `shoppingcarts_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `text`
--
ALTER TABLE `text`
  ADD CONSTRAINT `text_ibfk_1` FOREIGN KEY (`page_id`) REFERENCES `pages` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
