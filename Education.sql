-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 28, 2016 at 06:03 PM
-- Server version: 5.5.44-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Education`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sub_cate_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `description`, `sub_cate_id`) VALUES
(3, 'Getting Started With HTML and CSS', 'HTML và CSS là những ngôn ngữ bạn có thể sử dụng để xây dựng các trang web và phong cách. Trong khóa học này, bạn sẽ học được những điều cơ bản về HTML và CSS, xây dựng trang web đầu tiên của bạn, và sau đó xem xét một số HTML5 hiện nay và thực hành tốt nhất CSS3.', 2),
(4, 'Intermediate CSS', 'Simple CSS có thể giúp bạn có được khá xa, nhưng một khi bạn bắt đầu nhận nghiêm trọng về phát triển front-end, bạn cần phải được tiếp xúc với chủ đề nâng cao hơn, chẳng hạn như tính đặc trưng, ​​nổi, hoạt hình và thiết kế đáp ứng. Các khóa học này dạy cho bạn một số thông lệ tốt nhất để làm việc với CSS và xây dựng trang web đáp ứng để người dùng của bạn di chuyển đúng hướng.', 2),
(5, 'CSS Preprocessors', 'Nhiều nhà phát triển front-end thích sử dụng tiền xử lý CSS để tổ chức và tăng tốc công việc của họ. Có nhiều lựa chọn, nhưng chúng tôi thích sự đa dạng phổ biến Sass.', 2),
(6, 'Design', 'Một nhà phát triển front-end với sự nhạy cảm thiết kế tốt có thể mơ đến cách thức điều nên xem xét và làm việc, và sau đó sử dụng sức mạnh của HTML và CSS để thực hiện những mẫu thiết kế.', 2),
(7, 'iOS Development With Swift', 'Swift là một ngôn ngữ mới cho lập trình iOS và OS X ứng dụng lần đầu tiên được giới thiệu vào tháng Sáu năm 2014. Các khóa học này sẽ dạy cho bạn những điều cơ bản của việc sử dụng Swift để làm cho các ứng dụng iOS với khung UIKit của Apple, do đó, bạn sẽ được tạo nhãn, hình ảnh, di chuyển quan điểm, và các bảng dữ liệu trong thời gian không.', 6),
(8, 'iOS Development With Objective-C', 'Trước Swift, hầu hết các ứng dụng iOS được phát triển với một ngôn ngữ lập trình gọi là Objective-C. Nhiều ứng dụng hiện có trong App Store được xây dựng với nó, và nó vẫn còn có thể xây dựng các ứng dụng chỉ với Objective-C. Các khóa học này là một lựa chọn tuyệt vời cho những ai có kinh nghiệm lập trình với nó và muốn tìm hiểu thêm hoặc đã thừa kế một dự án Objective-C tại nơi làm việc và cần phải đào sâu vào ngôn ngữ.', 6),
(9, 'Angularjs Watch', 'Angularjs Watch exmaple', 3);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `link_icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `link_image_bgr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tag_id` varchar(50) NOT NULL,
  `related_course_id` varchar(100) NOT NULL,
  `createDate` date NOT NULL,
  `cate_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `name`, `description`, `link_icon`, `link_image_bgr`, `tag_id`, `related_course_id`, `createDate`, `cate_id`) VALUES
(1, 'Try iOS', 'Learn iOS from the ground up.', 'http://localhost:9200/static/image/icon-3.svg', 'http://localhost:9200/static/image/level-1-on-try-objective-c.png', '1,2,3', '4', '2016-07-20', 8),
(2, 'iOS Operation: Models', 'Tìm hiểu để sử dụng mô hình tổ chức và quản lý dữ liệu ứng dụng iOS.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/239/completed-ios-operation-models-85575233fe0938a46405060a96121d8d.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/31/ios-operation-models-170b04a6e062b2529a690081201bde94.jpg', '1,2,3', '3', '2016-07-20', 8),
(3, 'Exploring Google Maps for iOS', 'Learn to use the Google Maps SDK for iOS.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/385/completed-exploring-google-maps-for-ios-e91f4a65f676703091a312d8bc05393d.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/112/exploring-google-maps-for-ios-a9a1e36ecc01505411a3153c93a3e456.png', '1,2', '6', '2016-07-20', 8),
(4, 'App Evolution With Swift', 'Phát triển kỹ năng của bạn và học hỏi làm thế nào để làm cho ứng dụng iOS đầu tiên của bạn với Swift.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/587/app-evolution-with-swift-b8f38def617f774e6f7cb4e3e845b146.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/153/app-evolution-with-swift-0376e516b25e2329b8f62063d2ca503a.jpg', '1,2,3', '', '2016-07-20', 7),
(5, 'Try Objective-C', 'Learn the language that powers iOS and Mac apps.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/246/completed-try-objective-c-8416ac82bc82b2a7bd8f9bcc4a3e79ef.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/64/try-objective-c-1b1dd51d4eba393fa4be2b84c5e27570.png', '1,2,3', '', '2016-07-20', 8),
(6, 'Try iOS', 'Learn iOS from the ground up.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/162/completed-try-ios-db05f0ded51e402d88cb960af4e152f0.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/24/try-ios-cce043af45a55b00994c48261b9ccac7.jpg', '1,2,3', '4', '2016-07-20', 8),
(7, 'iOS Operation: Models', 'Tìm hiểu để sử dụng mô hình tổ chức và quản lý dữ liệu ứng dụng iOS.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/239/completed-ios-operation-models-85575233fe0938a46405060a96121d8d.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/31/ios-operation-models-170b04a6e062b2529a690081201bde94.jpg', '1,2,3', '3', '2016-07-20', 8),
(8, 'Core iOS 7', 'Nhanh chóng có được tốc độ trên các bản cập nhật cốt lõi của iOS 7.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/306/completed-core-ios-7-1a959e081035f3ca3a592ecc8ab99c6c.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/103/core-ios-7-ef40f00818967514a9777f57c8e00ab0.jpg', '1,2,3', '', '2016-07-20', 8),
(9, 'iOS Operation: MapKit', 'Tìm hiểu làm thế nào để thêm một bản đồ để ứng dụng của bạn.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/259/completed-ios-operation-mapkit-97e67d7e7ac3c8111098274ad072ac23.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/97/ios-operation-mapkit-9658903eae540018b75534b38cf2fe4a.jpg', '1,2', '', '2016-07-20', 8),
(10, 'Exploring Google Maps for iOS', 'Learn to use the Google Maps SDK for iOS.', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/385/completed-exploring-google-maps-for-ios-e91f4a65f676703091a312d8bc05393d.png', 'https://d1ffx7ull4987f.cloudfront.net/images/courses/hero/112/exploring-google-maps-for-ios-a9a1e36ecc01505411a3153c93a3e456.png', '1,2', '6', '2016-07-20', 8),
(11, 'Photoshop CS 6', 'Photoshop CS 6', 'http://localhost:9200/static/image/badge-electives.svg', 'http://localhost:9200/static/image/level-1-on-try-objective-c.png', '3,4', '3,5', '2016-07-28', 3);

-- --------------------------------------------------------

--
-- Table structure for table `course_comment`
--

CREATE TABLE IF NOT EXISTS `course_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course_details`
--

CREATE TABLE IF NOT EXISTS `course_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `level` int(10) unsigned NOT NULL,
  `description` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `link_icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `num_video` int(10) unsigned NOT NULL,
  `num_challenges` int(10) unsigned NOT NULL,
  `free` tinyint(10) NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `course_details`
--

INSERT INTO `course_details` (`id`, `name`, `level`, `description`, `link_icon`, `num_video`, `num_challenges`, `free`, `course_id`) VALUES
(1, 'Grass World', 1, 'Create and log common objects', 'http://localhost:9200/static/image/completed-try-objective-c.png', 1, 14, 100, 2),
(2, 'Ice World', 2, 'Sending messages and getting results', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/248/level-2-on-try-objective-c-eb7fc97ab60c09066184efcc9b59064a.png', 1, 14, 0, 2),
(3, 'Sand World', 3, 'Control the flow', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/249/level-3-on-try-objective-c-eb6eb18f34044444af8be55253a496ed.png', 1, 14, 0, 2),
(4, 'Lava World', 4, 'Create your own classes', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/250/level-4-on-try-objective-c-4f070ee5a338c4fad949e42ea0d21b7b.png', 1, 5, 0, 2),
(5, 'Space World', 5, 'Learning from mistakes', 'https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/251/level-5-on-try-objective-c-f86ad56ea2b33997a08e7782f9d12ffb.png', 1, 6, 1, 2),
(6, 'Angularjs Level 1', 2, 'Angularjs Level 2', 'http://localhost:9200/static/image/badge-ruby.svg', 0, 0, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE IF NOT EXISTS `details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` tinyint(3) unsigned NOT NULL,
  `link` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `course_detail_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`id`, `name`, `type`, `link`, `content`, `course_detail_id`) VALUES
(1, 'Ramp Up', 1, 'https://projector.codeschool.com/videos/254ad8a6.mp4?profile=720p&site=course&sso=T6Q2kRZSQcK8yVbcBAc8OrV7v9o3cfNYX6jGr_TCHU7ZBhkTkRmboNKD2MSQV9cc9J_gQSl5Wjtv0iVJNEEMEiEOH9ufjVCyOLv3B_NeBuMTtzeIChJpVIO7Evrb5eZn', '', 1),
(2, 'Creating a Store Module ', 2, '', 'The Flatlanders need a store to sell their gems and more! They need it really quick, Angular will do the trick!\r\n\r\nThey have figured out how to manipulate time and space, allowing them to create three-dimensional gems. The buying and selling of their gems has become a popular Flatlander practice and they believe the next step is taking their wonderful wares to the fourth dimension (the web).\r\n\r\nCan you help them reach their online peddling goals?', 1),
(3, 'Built-in Directives', 1, 'https://projector.codeschool.com/videos/a4a83fc3.mp4?profile=720p&site=course&sso=T6Q2kRZSQcK8yVbcBAc8OrV7v9o3cfNYX6jGr_TCHU7ZBhkTkRmboNKD2MSQV9ccs9VWQSU0rnXDhByv0X1x9PGI2eaiak4wZp3hPFajepVMRi21gdR9864F6XpS955b', '', 1),
(4, ' Not For Sale 250 PTS', 2, '', '<strong>We''ve added two new properties to our <code>product</code> that we can use on the HTML side. The first of these two is <code>canPurchase</code>, which is a <code>boolean</code> indicating if the product can be purchased. The second is <code>soldOut</code> which, as you can imagine, is a <code>boolean</code> indicating if the product is sold out.</strong>', 1),
(5, 'Built-in Directives', 1, 'https://projector.codeschool.com/videos/a4a83fc3.mp4?profile=720p&site=course&sso=T6Q2kRZSQcK8yVbcBAc8OrV7v9o3cfNYX6jGr_TCHU7ZBhkTkRmboNKD2MSQV9ccs9VWQSU0rnXDhByv0X1x9PGI2eaiak4wZp3hPFajepVMRi21gdR9864F6XpS955b', '', 1),
(6, ' Not For Sale 250 PTS', 2, '', '<strong>We''ve added two new properties to our <code>product</code> that we can use on the HTML side. The first of these two is <code>canPurchase</code>, which is a <code>boolean</code> indicating if the product can be purchased. The second is <code>soldOut</code> which, as you can imagine, is a <code>boolean</code> indicating if the product is sold out.</strong>', 1),
(7, 'video 1', 1, 'http://campus.codeschool.com/courses/staying-sharp-with-angular-js/level/1/section/2/http-methods1', '', 1),
(8, 'video 4', 1, 'http://campus.codeschool.com/courses/staying-sharp-with-angular-js/level/1/section/2/llamas-in-space', '', 1),
(9, 'tutorial 3.1', 2, '', '<header class="header--challenge header ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<h1 class="header-title ng-binding" style="font-size: 20.8px; margin: 0px 0px 10px; font-weight: 300; line-height: 1.2;"><strong class="header-num ng-binding ng-scope" style="border-right-width: 1px; border-right-style: solid; border-right-color: #dddddd; color: #f839ae; margin-right: 5px; padding-right: 10px;">1.8</strong>&nbsp;Llamas in Space&nbsp;<span class="header-label label ng-scope ng-binding" style="margin-left: 5px; position: relative; top: -4px; border: 0px; border-radius: 3px; font-size: 9.36px; font-weight: bold; color: #ffffff; display: inline-block; line-height: 2; padding-left: 20px; padding-right: 20px; text-align: center; text-transform: uppercase; background: #b7b7b7;">250 PTS</span></h1>\n</header>\n<div class="objective-body ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<p class="ng-scope" style="margin-bottom: 20px; margin-top: 0px;">A brand new llama recruit has signed up to go into space and he''s ready to be added to the list on the server. Make a&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">$http()</code>&nbsp;function call to make the proper request to the server at the&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">/llamas.json</code>&nbsp;URL.</p>\n</div>', 1),
(10, 'tutorial 4', 2, '', '<header class="header--challenge header ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<h1 class="header-title ng-binding" style="font-size: 20.8px; margin: 0px 0px 10px; font-weight: 300; line-height: 1.2;"><strong class="header-num ng-binding ng-scope" style="border-right-width: 1px; border-right-style: solid; border-right-color: #dddddd; color: #f839ae; margin-right: 5px; padding-right: 10px;">1.9</strong>&nbsp;Llamas in Space&nbsp;<span class="header-label label ng-scope ng-binding" style="margin-left: 5px; position: relative; top: -4px; border: 0px; border-radius: 3px; font-size: 9.36px; font-weight: bold; color: #ffffff; display: inline-block; line-height: 2; padding-left: 20px; padding-right: 20px; text-align: center; text-transform: uppercase; background: #b7b7b7;">250 PTS</span></h1>\n</header>\n<div class="objective-body ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<p class="ng-scope" style="margin-bottom: 20px; margin-top: 0px;">A brand new llama recruit has signed up to go into space and he''s ready to be added to the list on the server. Make a&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">$http()</code>&nbsp;function call to make the proper request to the server at the&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">/llamas.json</code>&nbsp;URL.</p>\n<header class="header--challenge header ng-scope">\n<h1 class="header-title ng-binding" style="font-size: 20.8px; margin: 0px 0px 10px; font-weight: 300; line-height: 1.2;"><strong class="header-num ng-binding ng-scope" style="border-right-width: 1px; border-right-style: solid; border-right-color: #dddddd; color: #f839ae; margin-right: 5px; padding-right: 10px;">2.0</strong>&nbsp;Llamas in Space&nbsp;<span class="header-label label ng-scope ng-binding" style="margin-left: 5px; position: relative; top: -4px; border: 0px; border-radius: 3px; font-size: 9.36px; font-weight: bold; color: #ffffff; display: inline-block; line-height: 2; padding-left: 20px; padding-right: 20px; text-align: center; text-transform: uppercase; background: #b7b7b7;">250 PTS</span></h1>\n</header>\n<div class="objective-body ng-scope">\n<p class="ng-scope" style="margin-bottom: 20px; margin-top: 0px;">A brand new llama recruit has signed up to go into space and he''s ready to be added to the list on the server. Make a&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">$http()</code>&nbsp;function call to make the proper request to the server at the&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">/llamas.json</code>&nbsp;URL.</p>\n<p>&nbsp;</p>\n</div>\n</div>', 1),
(11, 'tutorial 3.2', 2, '', '<header class="header--challenge header ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<h1 class="header-title ng-binding" style="font-size: 20.8px; margin: 0px 0px 10px; font-weight: 300; line-height: 1.2;"><strong class="header-num ng-binding ng-scope" style="border-right-width: 1px; border-right-style: solid; border-right-color: #dddddd; color: #f839ae; margin-right: 5px; padding-right: 10px;">1.8</strong>&nbsp;Llamas in Space&nbsp;<span class="header-label label ng-scope ng-binding" style="margin-left: 5px; position: relative; top: -4px; border: 0px; border-radius: 3px; font-size: 9.36px; font-weight: bold; color: #ffffff; display: inline-block; line-height: 2; padding-left: 20px; padding-right: 20px; text-align: center; text-transform: uppercase; background: #b7b7b7;">250 PTS</span></h1>\n</header>\n<div class="objective-body ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<p class="ng-scope" style="margin-bottom: 20px; margin-top: 0px;"><strong>A brand new llama recruit has signed up to go into space and he''s ready to be added to the list on the server. Make a&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">$http()</code>&nbsp;function call to make the proper request to the server at the&nbsp;<code style="font-family: Monaco, monospace; font-size: 14.4px; border-radius: 3px; border: 1px solid #dddddd; padding: 0.15em 0.35em; word-break: break-word; background: #ffffff;">/llamas.json</code>&nbsp;URL.</strong></p>\n</div>', 1),
(12, 'video 5', 1, 'http://campus.codeschool.com/courses/staying-sharp-with-angular-js/level/1/section/2/llamas-in-space', '', 1),
(13, 'Video 1', 1, 'http://campus.codeschool.com/courses/staying-sharp-with-angular-js/contents', '', 0),
(14, 'dasda', 0, 'dad', '', 0),
(15, 'Video 1', 1, 'http://campus.codeschool.com/courses/staying-sharp-with-angular-js/contents', '', 6),
(16, 'Tutorial 1', 2, '', '<header class="header--challenge header ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<h1 class="header-title ng-binding" style="font-size: 20.8px; margin: 0px 0px 10px; font-weight: 300; line-height: 1.2;"><strong class="header-num ng-binding ng-scope" style="border-right-width: 1px; border-right-style: solid; border-right-color: #dddddd; color: #f839ae; margin-right: 5px; padding-right: 10px;">1.9</strong>&nbsp;Inside Route Controller&nbsp;<span class="header-label label ng-scope ng-binding" style="margin-left: 5px; position: relative; top: -4px; border: 0px; border-radius: 3px; font-size: 9.36px; font-weight: bold; color: #ffffff; display: inline-block; line-height: 2; padding-left: 20px; padding-right: 20px; text-align: center; text-transform: uppercase; background: #b7b7b7;">250 PTS</span></h1>\n</header>\n<div class="objective-body ng-scope" style="color: #4d4d4d; font-family: ''Open Sans'', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; background-color: #f6f7f7;">\n<p class="ng-scope" style="margin-bottom: 20px; margin-top: 0px;">We want to display notes from the server within our template. Let''s get started by creating a controller in our routes file.</p>\n<div class="md-file-list card card--b card--s directoryList ng-scope" style="border-radius: 3px; padding: 10px; position: relative; margin-bottom: 20px; background-color: #e9ebeb;">\n<ul style="margin: 0px 0px 10px; padding: 0px 0px 0px 8px;">\n<li class="ng-scope directoryList-parent is-expanded" style="margin-bottom: 10px; margin-top: 10px; font-weight: bold; display: block;">app\n<ul style="margin: 0px 0px 10px 8px; padding: 0px 0px 0px 8px; font-weight: 400;">\n<li style="margin-bottom: 10px; margin-top: 10px; display: block;">index.html</li>\n<li class="directoryList-parent" style="margin-bottom: 10px; margin-top: 10px; font-weight: bold; display: block;">js</li>\n<li class="directoryList-parent" style="margin-bottom: 10px; margin-top: 10px; font-weight: bold; display: block;">templates</li>\n</ul>\n</li>\n</ul>\n</div>\n</div>', 6);

-- --------------------------------------------------------

--
-- Table structure for table `parent_category`
--

CREATE TABLE IF NOT EXISTS `parent_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `createDate` date NOT NULL,
  `sub` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `parent_category`
--

INSERT INTO `parent_category` (`id`, `name`, `description`, `createDate`, `sub`) VALUES
(1, 'Công nghệ thông tin', 'Công nghệ thông tin 1', '2016-07-22', 0),
(2, 'Đầu tư tài chính', 'Đầu tư tài chính , chứng khoán , vàng , forex', '2016-07-22', 0),
(3, 'Tiếng Anh', 'Tiếng Anh cho trẻ em , người đi làm ...', '2016-07-22', 0),
(4, 'Chứng khoán', 'Chứng khoán1233', '2016-07-27', 2);

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE IF NOT EXISTS `publisher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `sub_category`
--

CREATE TABLE IF NOT EXISTS `sub_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `parent_cate_id` int(10) unsigned NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `link_icon` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `sub_category`
--

INSERT INTO `sub_category` (`id`, `name`, `parent_cate_id`, `description`, `link_icon`) VALUES
(2, 'HTML/CSS', 1, 'Tìm hiểu các nguyên tắc cơ bản của thiết kế, phát triển front-end, và đúc trải nghiệm người dùng được dễ dàng trên mắt.', 'http://localhost:9200/static/image/badge-html-css.svg'),
(3, 'JavaScript', 1, 'Hãy dành một chút thời gian với ngôn ngữ kịch bản mạnh mẽ này và tìm hiểu để xây dựng các ứng dụng nhẹ với tăng cường giao diện người dùng.', 'http://codeschool.com/assets/paths/badge-javascript-min-61c3e5858a6e6aee7aa5715f8d8824deaf087b6fa689df71451ea864805832f7.svg'),
(4, 'Ruby', 1, 'Nắm vững các kỹ năng của Ruby và tăng cred đường Rails của bạn bằng cách học để xây dựng năng động, các ứng dụng bền vững cho các trang web.', 'http://codeschool.com/assets/paths/badge-ruby-min-d25a54bbab64a6e8b4fe1558748a49ee8c71887d0b65630a0cf0dab4791c57dc.svg'),
(5, 'Python', 1, 'Khám phá những gì nó có nghĩa là để lưu trữ và thao tác dữ liệu, đưa ra quyết định với chương trình của bạn, và tận dụng sức mạnh của Python.', 'http://codeschool.com/assets/paths/badge-python-min-ec07ed3e9d1c5bd23a07ec84975d0cc447000e188cbc34264a22581b35fa2025.svg'),
(6, 'iOS', 1, 'Hãy thử xây dựng các ứng dụng iOS cho các thiết bị di động iPhone và iPad. Tìm hiểu những điều cơ bản của sự phát triển iOS và mang lại những ý tưởng ứng dụng của bạn với cuộc sống.', 'https://www.codeschool.com/assets/paths/badge-ios-min-0f00eefa69cc5a41ff1522e2e3012023cdd2859169504c425d1c557fcd789894.svg'),
(7, 'Git + Svn', 1, 'Xây dựng một nền tảng vững chắc trong Git, sau đó ghép nó với phiên bản nâng cao các kỹ năng kiểm soát. Tìm hiểu làm thế nào để cộng tác trên các dự án có hiệu quả với GitHub.', 'https://www.codeschool.com/assets/paths/badge-git-min-cbf88ad762eee30610641b2373a7e3786123f55c0450ecd42f61e7d7255138d2.svg'),
(8, 'Database', 1, 'Xây dựng câu trúc lưu trữ data với nhiều loại database khác nhau.', 'https://www.codeschool.com/assets/paths/badge-database-min-d3543bd7f75a1797dc1328648e88e0faaa4b963014037a63d40c618641f009cc.svg'),
(9, 'Java', 1, 'Lập trình web , mobile với ngôn ngữ phổ biến nhất giới.', 'https://www.codeschool.com/assets/paths/badge-electives-min-04558aa07be6a8e932309a7575c158c1922d5ed80bb27d510a43087951d0855a.svg');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id`, `name`) VALUES
(1, 'Objective C'),
(2, 'C/C++'),
(3, 'HTML/CSS'),
(4, 'Java');

-- --------------------------------------------------------

--
-- Table structure for table `tags_course`
--

CREATE TABLE IF NOT EXISTS `tags_course` (
  `id_tag` int(10) unsigned NOT NULL,
  `id_course` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_tag`,`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_register`
--

CREATE TABLE IF NOT EXISTS `user_register` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `passw` varchar(150) NOT NULL,
  `displayname` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_user` tinyint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
