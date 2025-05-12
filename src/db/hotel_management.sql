-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2025 at 07:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE `tbl_log` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `u_type` varchar(255) NOT NULL,
  `log_status` varchar(255) NOT NULL,
  `u_username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`log_id`, `u_id`, `login_time`, `u_type`, `log_status`, `u_username`) VALUES
(1, 3, '2025-05-02 03:07:02', 'Success - User Action', 'Active', 'adolf321'),
(2, 4, '2025-05-02 03:08:47', 'Success - User Action', 'Active', 'gwapo'),
(3, 3, '2025-05-02 03:11:28', 'Success - User Action', 'Active', 'adolf321'),
(4, 3, '2025-05-02 03:38:42', 'Success - Admin Login', 'Active', 'adolf321'),
(5, 4, '2025-05-02 03:39:29', 'Success - User Login', 'Active', 'gwapo'),
(6, 4, '2025-05-02 03:41:19', 'Success - User Login', 'Active', 'gwapo'),
(7, 4, '2025-05-02 03:43:22', 'Success - User Login', 'Active', 'gwapo'),
(8, 4, '2025-05-02 03:43:49', 'User', 'Active', 'gwapo1'),
(9, 5, '2025-05-08 13:34:59', 'Success - User Action', 'Active', 'anton1234'),
(10, 5, '2025-05-08 13:35:23', 'Success - User Login', 'Active', 'anton1234'),
(11, 5, '2025-05-08 13:39:51', 'Success - User Login', 'Active', 'anton1234'),
(12, 5, '2025-05-08 13:41:21', 'Success - User Login', 'Active', 'anton1234'),
(13, 5, '2025-05-08 13:41:54', 'Success - User Login', 'Active', 'anton1234'),
(14, 5, '2025-05-08 13:46:48', 'Success - User Login', 'Active', 'anton1234'),
(15, 5, '2025-05-08 13:52:18', 'Success - User Login', 'Active', 'anton1234'),
(16, 6, '2025-05-08 13:56:05', 'Success - User Action', 'Active', 'nash1234'),
(19, 5, '2025-05-09 15:21:42', 'Success - User Login', 'Active', 'anton1234'),
(20, 7, '2025-05-12 05:42:23', 'Success - User Action', 'Active', 'anton321'),
(21, 7, '2025-05-12 05:42:36', 'Failed - Pending Account', 'Active', 'anton321'),
(22, 7, '2025-05-12 05:42:57', 'Success - User Login', 'Active', 'anton321');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_room`
--

CREATE TABLE `tbl_room` (
  `r_id` int(11) NOT NULL,
  `r_class` int(255) NOT NULL,
  `r_price` int(255) NOT NULL,
  `r_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `u_id` int(11) NOT NULL,
  `u_fname` varchar(55) NOT NULL,
  `u_lname` varchar(55) NOT NULL,
  `u_email` varchar(55) NOT NULL,
  `u_username` varchar(55) NOT NULL,
  `u_password` varchar(55) NOT NULL,
  `u_type` varchar(55) NOT NULL,
  `u_status` varchar(55) NOT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_username`, `u_password`, `u_type`, `u_status`, `security_question`, `security_answer`, `image`) VALUES
(1, 'Alcaya', 'Marlo', 'alcayamarlo12@gmail.com', 'alcayamarlo', '5ZF3MhtQxF4/I0C2MTl61qIptwIsp/26ONaVn65RKaU=', 'User', 'Pending', 'Why are you Gay?', 'WGrLPGusSJMIwJOPdi2nAlc6cU3986cp3LQHWLTDY64=', 'NULL'),
(2, 'Marlo', 'Alcaya', 'marlo12@gmail.com', 'marlo123', 't2KI2ga5PH7olRxcEL4bEeO5LHCHaHB93KYqSOtl6CQ=', 'User', 'Pending', 'What is your Favorite Color?', 'pnpByLx51dqRe1BR8fDT9a60tjuiRrNUapYe96PH2TE=', 'NULL'),
(3, 'Adolf', 'Borja', 'adolf@gmail.com', 'adolf321', '1BOxDuOgCIwUl31VksMtvoUG+C486Ji+D07VYSDZnGw=', 'Admin', 'Active', 'What is your Favorite Color?', 'wAbH46sU1ob2NSQTbx7HxeVT2Dm8AchR5Nyd4r2/xYk=', 'NULL'),
(4, 'jasdih', 'jahdias', 'gwapo1@gmail.com', 'gwapo1', 'k/DAzzVABVzB4YkX/yeZdvdHSzUBhwVG+5/DfK8SLs0=', 'User', 'Active', 'What is your Mothers Tongue?', 'tiNNLqDWAivmPbgNe4DiIQl/5KRp3ET+vNKpJB7/3ro=', 'src/images/ambulance.png'),
(5, 'ajshdi', 'akjsdniausd', 'anton@gmail.com', 'anton1234', '3e3i4+6KiEhz7p014l7iTT4T8G/nUZwJThZ1AmLSzck=', 'User', 'Pending', 'What is your Favourite Sports?', 'iLtCXtyKFfT90sUA+RkGovM/Q5JfjqET2Mito5pLzNQ=', 'NULL'),
(6, 'jashfn', 'kjnasjkd', 'ajshd@gmail.com', 'nash1234', 'hudFWYPVJMBUnzbYvv8H6sRkrO2XfH0kuVI/+7MzG/Y=', 'User', 'Active', 'Why are you Gay?', 'qH8kfJdxLMShb2TarjcNX7ALr01+MZC58YET5i5nRZY=', 'NULL'),
(7, 'Anton', 'aksnd', 'aksnad@gmail.com', 'anton321', 'g2Psf0Tx48+IqoD5/JeA3hdRI5FCm/NE85TEi+R1GZM=', 'User', 'Active', 'What is your Favourite Sports?', '8CyWNXSBlyRDouttKGHIA/p3kPt9AiWy9Utr66lk9K0=', 'NULL');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_users` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
