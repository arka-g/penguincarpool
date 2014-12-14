-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 30, 2014 at 07:32 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `se3a04db`
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_11_07_204957_UserInfo', 1),
('2014_11_08_234147_create_user_table', 2),
('2014_11_08_234835_create_user_table', 3),
('2014_11_08_235717_add_users', 4),
('2014_11_23_221409_CreateUsersTable', 5),
('2014_11_27_202524_create_taxis_table', 6),
('2014_11_27_204039_create_order_table', 7);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `taxi_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_destination` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`taxi_id`, `user_id`, `user_location`, `user_destination`) VALUES
(1, 1, '65.32', '42.65'),
(1, 1, '65.32', '42.65'),
(1, 1, '43.23', '24.42'),
(1, 31, '43.23', '24.42'),
(1, 41, '43.23', '24.42'),
(1, 31, '32 broadway', 'wtf'),
(1, 0, 'mcmaster', 'waterloo'),
(1, 0, 'mac', 'waterloo'),
(1, 0, 'macfdslkf', 'waterloosdfsd'),
(1, 0, 'mcmaster', 'jdsnfjnjds'),
(1, 44, 'mcam', 'dsfff'),
(1, 44, 'sddfsff', 'fsdffsdfdfdfs'),
(1, 44, 'thode library', 'mills library'),
(1, 44, '', ''),
(1, 44, '21 broadway', '20 hul'),
(1, 44, '1280 main street', 'fuck'),
(1, 0, '', ''),
(1, 44, 'kenneth taylor hall', 'mdcl'),
(1, 1, 'thode library', 'snooty fox'),
(1, 44, 'fdsf', 'fsdf'),
(1, 44, 'fdsf', 'fsdfsd'),
(1, 44, 'fds', 'fsdf'),
(1, 44, 'rewe', 'fsddfs'),
(1, 44, 'fsd', 'fsdfd'),
(1, 44, 'fsdf', 'fsdfd'),
(1, 44, 'fdsff', 'fsdfdf'),
(1, 44, 'fsds', 'fsdffsdf'),
(1, 44, 'fds', 'fdsfsf'),
(1, 39, 'test loc', 'test loc 2'),
(1, 39, 'trwdsf', 'fsfdfd'),
(1, 39, 'gsds', 'fsdfdfsd'),
(1, 39, 'fsd', 'fss'),
(1, 39, 'dasds', 'ffsdf'),
(1, 39, 'dfsd', 'fsdfs'),
(1, 39, 'gsgf', 'fsdf'),
(1, 39, 'test', 'int'),
(1, 39, 'join TAXI', 'arkas house');

-- --------------------------------------------------------

--
-- Table structure for table `taxis`
--

CREATE TABLE IF NOT EXISTS `taxis` (
`id` int(10) unsigned NOT NULL,
  `taxi_location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `active_state` tinyint(1) NOT NULL,
  `carpool` tinyint(1) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `taxis`
--

INSERT INTO `taxis` (`id`, `taxi_location`, `active_state`, `carpool`, `user_id`) VALUES
(1, '56.72', 1, 0, 0),
(2, '32 broadway avenue', 0, 0, 0),
(3, '160 kingknoll drive', 1, 0, 0),
(4, '43 emerson street', 1, 0, 0),
(5, '1280 main street', 1, 0, 0),
(6, '200 marblewood', 0, 0, 0),
(7, '1290 main st', 1, 1, 0),
(8, '432 hollywood', 1, 1, 0),
(9, '53 wtf st', 0, 0, 0),
(10, '1233 i dont care', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `UserInfoTable`
--

CREATE TABLE IF NOT EXISTS `UserInfoTable` (
`id` int(10) unsigned NOT NULL,
  `firstname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=25 ;

--
-- Dumping data for table `UserInfoTable`
--

INSERT INTO `UserInfoTable` (`id`, `firstname`, `lastname`, `password`, `username`, `email`) VALUES
(1, 'Arka', 'Ganguli', 'pass', 'gangua', 'gangua@mcmaster.ca'),
(2, 'kevin', 'dang', 'password', 'kdang', 'kdang@gmail.com'),
(3, 'arka', 'asfjsf', 'fsdfdsfd', 'fsdfs', 'fsfs@gmail.com'),
(4, 'tigran', 'martikian', 'dsad', 'tigranisabitch', 'markit@mcmaster.ca'),
(5, 'test_user', 'test_user_lastname', '123456789', 'kirisanth', 'test_user@gmail.com'),
(8, 'arka', 'ganguli', 'man', 'yo', 'arka@gmail.com'),
(9, 'Saim ', 'Zahid', 'dsds', 'asdass', 'saimzahid@me.com'),
(10, 'Iris', 'Ng', 'irisng', 'irisng', 'ngil@mcmaster.ca'),
(19, 'db', 'test', 'yoyo', 'gdsgsds', 'test@gemail.com'),
(20, 'frank', 'sinatra', 'password', 'whatever', 'email@email.com'),
(21, 'Kevin', 'DAng', 'password', 'ijdsnffsd', 'kdang@hgithub.com'),
(22, 'sid', 'gaypatti', 'whatsup', 'sidandmario', 'galapti@mcmaster.ca'),
(24, 'Tigran', 'Martikit', 'yoyoyoyo', 'tubsters', 'markit2@mcmaster.ca');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(10) unsigned NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=47 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Emily', 'Thorn', 'emily.thorn@gmail.com', '$2y$10$mBgYERXl5x9Xkv6x1aEjaeCzl7W7vAeupxoVyLNH8qQZtFhy/JEjW', '7ayKfwDOLEYd9ZSY7MsxvawzRSnqbanXZhMR9kpPjrqBSnALNw557QgHrWVD', '2014-11-24 06:23:16', '2014-11-28 02:00:12'),
(2, 'Fred', 'Jackson', 'fred.jackson@gmail.com', '$2y$10$BO1icfMMTdDllE0PvzWXoe.wNs6lRrwvwISHu2fRhzZ1uHQ4XPL2y', NULL, '2014-11-24 06:23:16', '2014-11-24 06:23:16'),
(3, 'Arka', 'Ganguli', 'ganguli.arka@gmail.com', 'wtflaravel', NULL, '2014-11-24 06:23:16', '2014-11-24 06:23:16'),
(4, 'saim', 'zahid', 'saim@zahid.com', 'saimspass', NULL, '2014-11-24 07:48:30', '2014-11-24 07:48:30'),
(5, 'saim', 'zahid', 'saim@zahid.com', 'saimspass', 'HIvm38yctVUfixWD6OT9UyW7ZSOHEeQEk0KSOvEmtZKnq22BjBO7xHOO94no', '2014-11-24 07:48:51', '2014-11-24 07:49:00'),
(6, 'wtfman', 'wtfisthisshit', 'arka@sucks.com', 'wtfwtsdfds', NULL, '2014-11-24 07:53:04', '2014-11-24 07:53:04'),
(7, 'fuck', 'you', 'gangua@mcmaster.ca', '$2y$10$KKmIEoLLhgKJewIHhghJBudYH/rDgAYVYGT8z5Y6KQeh/6Lzb5EgO', NULL, '2014-11-24 08:05:36', '2014-11-24 08:05:36'),
(8, 'yoyoy', 'yoyoyooy', 'yowtf@gmail.com', '$2y$10$13CAYaOMB9sryCiQ96Xca.QTmNbdyTj/BsbYWpzr.YCHDmNir6hni', 'sAEPkUex1NkLal5IbmuxPUCalfN4cExqOC7la0r7DvFeK8drI70ilapcEzSG', '2014-11-24 08:06:42', '2014-11-24 08:07:48'),
(10, 'this', 'is', 'wtfsdaaafff@gmail.com', 'test', NULL, '2014-11-24 08:08:31', '2014-11-24 08:08:31'),
(11, 'this', 'is', 'wtfsdaaafff@gmail.com', 'test', NULL, '2014-11-24 08:09:55', '2014-11-24 08:09:55'),
(12, 'this', 'is', 'wtfsdaaafff@gmail.com', 'test', NULL, '2014-11-24 08:09:57', '2014-11-24 08:09:57'),
(13, 'this', 'is', 'wtfsdaaafff@gmail.com', 'test', NULL, '2014-11-24 08:09:57', '2014-11-24 08:09:57'),
(14, 'thisfff', 'isfff', 'wtfsdafffaafff@gmail.com', 'testff', NULL, '2014-11-24 08:10:39', '2014-11-24 08:10:39'),
(15, 'thisfff', 'isfff', 'wtfsdafffaafff@gmail.com', 'testff', NULL, '2014-11-24 08:10:41', '2014-11-24 08:10:41'),
(16, 'thisfff', 'isfff', 'wtfsdafffaafff@gmail.com', 'testff', NULL, '2014-11-24 08:10:41', '2014-11-24 08:10:41'),
(17, 'thisfff', 'isfff', 'wtfsdafffaafff@gmail.com', 'testff', NULL, '2014-11-24 08:10:42', '2014-11-24 08:10:42'),
(18, 'thisfff', 'isfff', 'wtfsdafffaafff@gmail.com', 'testff', NULL, '2014-11-24 08:12:57', '2014-11-24 08:12:57'),
(19, '', '', '', '', NULL, '2014-11-26 01:03:56', '2014-11-26 01:03:56'),
(20, '', '', '', '', NULL, '2014-11-26 01:04:12', '2014-11-26 01:04:12'),
(21, 'arka', 'ganguli', 'gangua@mcmaster.com', 'yoy', NULL, '2014-11-26 01:04:35', '2014-11-26 01:04:35'),
(22, 'arka', 'gangulikrisisat', 'gangua@mcmaster.com', 'yoy', NULL, '2014-11-26 01:27:54', '2014-11-26 01:27:54'),
(23, 'kirisant', 'subrar', 'yoaxsg@gmail.com', 'yoyoyo', NULL, '2014-11-26 01:30:04', '2014-11-26 01:30:04'),
(24, 'wtf', 'islaravel', 'kirisant@email.com', '$2y$10$sesmOo.iN.IrvZRdX2fsT.dMqQoETJdIKWJco/kfb/82iPmmGQAhG', NULL, '2014-11-26 01:34:19', '2014-11-26 01:34:19'),
(25, 'kirisant', 'subrarghchg', 'yoaxsg@gmail.com', 'yoyoyo', NULL, '2014-11-26 01:36:52', '2014-11-26 01:36:52'),
(26, 'kirisant', 'subrarghcfffhg', 'yoaxsg@gmail.com', '$2y$10$rn4KYbx7J1HrN50tki.xbO/YcX9ojA2lEvLOviBO7JvhUyiwUF7hS', NULL, '2014-11-26 01:41:25', '2014-11-26 01:41:25'),
(27, 'kirisant', 'subrarghcfffhg', 'yoaxsg@gmail.com', '$2y$10$akTtSHIDi1q8F5IKaQ/teONTOS3hNrERR6xDSDY1mnkpG2RluXmrW', NULL, '2014-11-26 01:41:26', '2014-11-26 01:41:26'),
(28, 'kirisanthh', 'subrarghcfffhg', 'yoaxsg@gmail.com', '$2y$10$bKakrKvtt.vkTWxybU3sjeZo39Z6xE.9w0RAwe3z6U/iXyh6AUg.6', NULL, '2014-11-26 01:41:50', '2014-11-26 01:41:50'),
(29, 'kirisanthh', 'subrarghcfffhg', 'yoaxdsdasg@gmail.com', '$2y$10$RrO9ZJYQNh5gZJjPH7GZeuYGT23F5bSt5JCRmPv4onnspNHSf2YUm', NULL, '2014-11-26 01:42:00', '2014-11-26 01:42:00'),
(30, 'kirisanthh', 'subrarghcfffhg', 'yoaxdsdasg@gmail.com', '$2y$10$lOivWZ2m5ohs6xwjQC8BBO.geE.TWZfl52Zfjjfrc0dpM00.3Dx9y', NULL, '2014-11-26 01:42:35', '2014-11-26 01:42:35'),
(31, 'arka', 'gangag', 'gangua@mac.ca', '$2y$10$AbRdsiJwp0j88CBOOvRImuQvonA8y0wz9QjEZ.bPJ4Lg2Xxx3Qk7a', 'KfWgYpQWpd9KYNu9PJw2N1s5JjU3DHqSv6WjT8doUTweUH93Ye7nxIa1uwlV', '2014-11-26 01:47:42', '2014-11-27 23:52:12'),
(32, '', '', '', '$2y$10$JGVVKLTv5Won/wIsBY7SEesLWtaT5Xd3BWA/bOhd/pBcLlpgP5ebe', NULL, '2014-11-27 22:30:47', '2014-11-27 22:30:47'),
(33, '', '', '', '$2y$10$P9CfnFZrQ5Y2sR7H3Ovlb..NV9/zf9pAz.r6rKqv/dUWaPZVGl9ku', NULL, '2014-11-27 22:57:55', '2014-11-27 22:57:55'),
(34, '', '', '', '$2y$10$oT24lm3V.gWBAJ4FchxONuC2gIYl2J11zpDJ8Q50btih8Lvt4Itea', NULL, '2014-11-27 23:00:24', '2014-11-27 23:00:24'),
(35, '', '', '', '$2y$10$8gR6XkwdZlAkka2rZG0Ynu1.e2jPRG4vk2N/dtw5GodhrvLME2y1m', NULL, '2014-11-27 23:00:40', '2014-11-27 23:00:40'),
(36, '', '', '', '$2y$10$qf.nyDYQvAQi570m0rXWbug6KPY6q7FXU6BIGEQU3eDAgaEHqYb/S', NULL, '2014-11-27 23:03:35', '2014-11-27 23:03:35'),
(37, '', '', '', '$2y$10$sjo7HuR6AOV/x/ErD1UrPOLSMvpgxpaN4ImVEnN.e0tsVTG1z/c.W', NULL, '2014-11-27 23:03:36', '2014-11-27 23:03:36'),
(38, 'arka', 'test', 'idc@email.com', '$2y$10$5iHDH52EeXnY72MaivnUjOqzSUNCH73ZP72TU7/VgCom4pSXKgOL.', NULL, '2014-11-28 23:24:16', '2014-11-28 23:24:16'),
(39, 'test', 'testarka', 'test@test.ca', '$2y$10$8s9eQKbc5Vs2nGFJt3/B2ureiK9fbxPQAFxh.TYNWodUYH12r4bbi', NULL, '2014-11-28 23:30:52', '2014-11-28 23:30:52'),
(40, 'tigran', 'martikian', 'hsdjshajh@arka.com', '$2y$10$v14Sgb3WIEuM4gXjmoeVtucWy8rWauLwkDlGIEdRQoSDcq.jxSSne', NULL, '2014-11-29 00:27:19', '2014-11-29 00:27:19'),
(41, 'tigran', 'martikian', 'tigran@arka.com', '$2y$10$NUcBAq2BkmUM05x4Y86mneV/QjGohiU72hOW4S6FHg.hDCT4NksLm', NULL, '2014-11-29 00:27:41', '2014-11-29 00:27:41'),
(42, 'Sushant ', 'Anantharam', 'sumsush1711@gmail.com', '$2y$10$SjfVikY1YgsTFqPU3GfHzOv5ZTjj5GAksK5YXBu2RFKhFvLRW8jcK', NULL, '2014-11-29 00:40:28', '2014-11-29 00:40:28'),
(43, 'sushant', 'try2', 'sums@gmail.com', '$2y$10$Q3ZLlm8ECD6uSQ98w54Ot./DIbEuTctJDC8Jr20huc9/uT7dLRae2', NULL, '2014-11-29 00:43:27', '2014-11-29 00:43:27'),
(44, 'Nicolas', 'Lel', 'myemail@email.com', '$2y$10$6fsfv/ASgfQ1DCEPRIZLuOTOxzBc/7TQHLnqUdLq6jWiPtbbWmwqm', NULL, '2014-11-29 21:17:43', '2014-11-29 21:17:43'),
(45, 'pav', 'last', 'register@email.com', '$2y$10$7D9wyHbBQDRf8fEGt.2CUO/a5..Uy7NKyhJ4iq2ruq2SqWLhJx3hm', NULL, '2014-11-30 01:59:03', '2014-11-30 01:59:03'),
(46, 'test', 'user', 'test@test.ca', '$2y$10$WnjLyOjZ5tV85eMym3euuebSC7wxzS9R7CmfoNK9JSSlhn/IjNWAq', NULL, '2014-11-30 11:35:56', '2014-11-30 11:35:56');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `taxis`
--
ALTER TABLE `taxis`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `UserInfoTable`
--
ALTER TABLE `UserInfoTable`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `userinfotable_email_unique` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `taxis`
--
ALTER TABLE `taxis`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `UserInfoTable`
--
ALTER TABLE `UserInfoTable`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
