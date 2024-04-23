/*
 Navicat Premium Data Transfer

 Source Server         : 本地Mysql
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : bluearchive

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 23/04/2024 16:15:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `blue_article`;
CREATE TABLE `blue_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户ID',
  `sort_id` int NOT NULL COMMENT '分类ID',
  `article_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章名称',
  `article_describe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章描述',
  `status` int NOT NULL COMMENT '审核状态',
  `cover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章封面',
  `video_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频地址',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `del_flag` int NOT NULL COMMENT '逻辑删除',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `blue_article_information`;
CREATE TABLE `blue_article_information`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户ID',
  `article_id` int NOT NULL COMMENT '文章ID',
  `is_browse` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否浏览',
  `is_like` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否点赞',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章信息表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_article_tag`;
CREATE TABLE `blue_article_tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int NOT NULL COMMENT '文章ID',
  `tag_id` int NOT NULL COMMENT '标签ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章标签关联表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for blue_avater
-- ----------------------------
DROP TABLE IF EXISTS `blue_avater`;
CREATE TABLE `blue_avater`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '头像id',
  `avater_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像路径',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户头像列表存储表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_bg`;
CREATE TABLE `blue_bg`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bg_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '背景类型',
  `bg_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '背景路径',
  `bg_position` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '背景位置类型',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网站背景资源信息表' ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `blue_comment`;
CREATE TABLE `blue_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int NOT NULL COMMENT '是否是父节点(0是,否则是其他评论ID)',
  `comment_type` int NOT NULL COMMENT '回复类型(0弹幕,1番剧)',
  `comment_content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复内容',
  `common_id` int NULL DEFAULT NULL COMMENT '通用id',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for blue_erchuang
-- ----------------------------
DROP TABLE IF EXISTS `blue_erchuang`;
CREATE TABLE `blue_erchuang`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ec_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '二创作者',
  `ec_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '二创标题',
  `ec_avater` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '二创图片',
  `ec_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '二创路径',
  `ec_publish` timestamp NOT NULL COMMENT '二创发布时间',
  `ec_synopsis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '二创简介',
  `status` int NOT NULL COMMENT '审核状态',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '二创信息表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_friend`;
CREATE TABLE `blue_friend`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `friend_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请手机号',
  `friend_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站名称',
  `friend_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片URL',
  `friend_introduce` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '介绍',
  `status` int NOT NULL COMMENT '申请状态',
  `please_time` timestamp NOT NULL COMMENT '发布时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友情链接表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_friend_info`;
CREATE TABLE `blue_friend_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `friend_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请友链接口',
  `friend_content` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请友链内容',
  `friend_detail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请友链备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友链申请信息表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_leave_message`;
CREATE TABLE `blue_leave_message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户ID',
  `content` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '弹幕内容',
  `user_avater` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像',
  `barrage_height` int NOT NULL COMMENT '弹幕高度',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '弹幕表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_leave_message_report`;
CREATE TABLE `blue_leave_message_report`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `leave_id` int NOT NULL COMMENT '弹幕ID',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '审核状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '弹幕举报表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_music`;
CREATE TABLE `blue_music`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `music_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '音乐的路径',
  `music_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '音乐名称',
  `sort_id` int NOT NULL COMMENT '音乐分类ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '音乐表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_music_sort`;
CREATE TABLE `blue_music_sort`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sort_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类的名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '音乐分类表' ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `blue_notice`;
CREATE TABLE `blue_notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `notice_title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告的标题',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告的内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_photo`;
CREATE TABLE `blue_photo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户id',
  `sort_id` int NOT NULL COMMENT '分类id',
  `photo_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册图片地址',
  `photo_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL COMMENT '审核状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `del_flag` int NOT NULL COMMENT '逻辑删除',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for blue_photo_sort
-- ----------------------------
DROP TABLE IF EXISTS `blue_photo_sort`;
CREATE TABLE `blue_photo_sort`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sort_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '相册分类名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册分类信息表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_pixiv_episode`;
CREATE TABLE `blue_pixiv_episode`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pixiv_id` int NOT NULL COMMENT '番剧ID',
  `pixiv_chapters` int NOT NULL COMMENT '番剧集',
  `pixiv_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '番剧地址',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '番剧集存储表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_pixiv_leave_message`;
CREATE TABLE `blue_pixiv_leave_message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pixiv_id` int NOT NULL COMMENT '番剧ID',
  `episode_id` int NOT NULL COMMENT '集ID',
  `border` tinyint(1) NOT NULL COMMENT '是否描边 0表示描边,1不描边',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '背景色',
  `text` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '弹幕内容',
  `time` decimal(20, 6) NOT NULL COMMENT '弹幕发送时间',
  `mode` tinyint(1) NOT NULL COMMENT '弹幕模式: 0表示滚动, 1静止',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '番剧弹幕表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_pixiv_tv`;
CREATE TABLE `blue_pixiv_tv`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pixiv_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '番剧名称',
  `pixiv_avater` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '番剧图片',
  `pixiv_publish` timestamp NOT NULL COMMENT '番剧发布时间',
  `pixiv_score` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评分',
  `pixiv_type` int NOT NULL COMMENT '番剧类型',
  `pixiv_voice_actor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '声优',
  `pixiv_synopsis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '番剧简介',
  `pixiv_play` int NOT NULL COMMENT '播放数',
  `status` int NOT NULL COMMENT '番剧审核状态的ID(0未审核,1已审核,2驳回)',
  `is_end` int NOT NULL COMMENT '是否完结(0:未完结,1:已完结)',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '番剧信息表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_pixiv_type`;
CREATE TABLE `blue_pixiv_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型名称',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '番剧类型表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_program_tool`;
CREATE TABLE `blue_program_tool`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sort_id` int NOT NULL COMMENT '工具分类ID',
  `tool_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工具名称',
  `tool_describe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工具简介',
  `tool_avater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工具图片',
  `tool_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工具路径',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '编程工具表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `blue_program_tool_sort`;
CREATE TABLE `blue_program_tool_sort`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sort_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NOT NULL COMMENT '创建人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '编程工具分类表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `blue_sort`;
CREATE TABLE `blue_sort`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `sort_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类的名称',
  `sort_describe` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_sort_tag`;
CREATE TABLE `blue_sort_tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sort_id` int NOT NULL COMMENT '分类id',
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签的名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签表' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `blue_website`;
CREATE TABLE `blue_website`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `website_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站的名称',
  `website_cover` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站的头像',
  `website_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站的地址',
  `website_detail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站的描述',
  `website_remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站的备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网站信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (19, 'blue_bg', '网站背景资源信息表', NULL, NULL, 'BlueBg', 'crud', 'element-ui', 'com.blue.system', 'system', 'bg', '网站背景资源信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17', NULL);
INSERT INTO `gen_table` VALUES (20, 'blue_leave_message', '弹幕表', NULL, NULL, 'BlueLeaveMessage', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'message', '弹幕', 'ruoyi', '0', '/', '{\"parentMenuId\":2002}', 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:11', NULL);
INSERT INTO `gen_table` VALUES (21, 'blue_photo', '相册表', NULL, NULL, 'BluePhoto', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'photo', '相册', 'ruoyi', '0', '/', '{\"parentMenuId\":\"2006\"}', 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31', NULL);
INSERT INTO `gen_table` VALUES (22, 'blue_music', '音乐表', NULL, NULL, 'BlueMusic', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'music', '音乐', 'ruoyi', '0', '/', '{\"parentMenuId\":\"2010\"}', 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20', NULL);
INSERT INTO `gen_table` VALUES (23, 'blue_photo_sort', '相册分类信息表', NULL, NULL, 'BluePhotoSort', 'crud', 'element-ui', 'com.blue.sort', 'sort', 'sort', '相册分类信息', 'ruoyi', '0', '/', '{\"parentMenuId\":2005}', 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30', NULL);
INSERT INTO `gen_table` VALUES (24, 'blue_sort', '分类表', NULL, NULL, 'BlueSort', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'sort', '分类', 'ruoyi', '0', '/', '{\"parentMenuId\":2007}', 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55', NULL);
INSERT INTO `gen_table` VALUES (25, 'blue_sort_tag', '标签表', NULL, NULL, 'BlueSortTag', 'crud', 'element-ui', 'com.blue.sort', 'sort', 'tag', '标签', 'ruoyi', '0', '/', '{\"parentMenuId\":2008}', 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57', NULL);
INSERT INTO `gen_table` VALUES (26, 'blue_music_sort', '音乐分类表', NULL, NULL, 'BlueMusicSort', 'crud', 'element-ui', 'com.blue.sort', 'sort', 'sort', '音乐分类', 'ruoyi', '0', '/', '{\"parentMenuId\":\"2009\"}', 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32', NULL);
INSERT INTO `gen_table` VALUES (27, 'blue_avater', '用户头像列表存储表', NULL, NULL, 'BlueAvater', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'avater', '用户头像列存储', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07', NULL);
INSERT INTO `gen_table` VALUES (28, 'blue_friend', '友情链接表', NULL, NULL, 'BlueFriend', 'crud', 'element-ui', 'com.blue.system', 'system', 'friend', '友情链接', 'ruoyi', '0', '/', '{\"parentMenuId\":\"2013\"}', 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09', NULL);
INSERT INTO `gen_table` VALUES (29, 'blue_website', '网站信息表', NULL, NULL, 'BlueWebsite', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'website', '网站信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44', NULL);
INSERT INTO `gen_table` VALUES (30, 'blue_notice', '公告信息表', NULL, NULL, 'BlueNotice', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'notice', '公告信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28', NULL);
INSERT INTO `gen_table` VALUES (31, 'blue_friend_info', '友链申请信息表', NULL, NULL, 'BlueFriendInfo', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'info', '友链申请信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37', NULL);
INSERT INTO `gen_table` VALUES (32, 'blue_article', '文章表', NULL, NULL, 'BlueArticle', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'article', '文章', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49', NULL);
INSERT INTO `gen_table` VALUES (33, 'blue_article_tag', '文章标签关联表', NULL, NULL, 'BlueArticleTag', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'tag', '文章标签关联', 'ruoyi', '0', '/', '{}', 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37', NULL);
INSERT INTO `gen_table` VALUES (34, 'blue_pixiv_tv', '番剧信息表', NULL, NULL, 'BluePixivTv', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'tv', '番剧信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55', NULL);
INSERT INTO `gen_table` VALUES (35, 'blue_pixiv_type', '番剧类型表', NULL, NULL, 'BluePixivType', 'crud', 'element-ui', 'com.blue.sort', 'sort', 'type', '番剧类型', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56', NULL);
INSERT INTO `gen_table` VALUES (36, 'blue_pixiv_episode', '番剧集存储表', NULL, NULL, 'BluePixivEpisode', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'episode', '番剧集存储', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31', NULL);
INSERT INTO `gen_table` VALUES (37, 'blue_erchuang', '二创信息表', NULL, NULL, 'BlueErchuang', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'erchuang', '二创信息', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41', NULL);
INSERT INTO `gen_table` VALUES (38, 'blue_program_tool_sort', '编程工具分类表', NULL, NULL, 'BlueProgramToolSort', 'crud', 'element-ui', 'com.blue.sort', 'sort', 'sort', '编程工具分类', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30', NULL);
INSERT INTO `gen_table` VALUES (39, 'blue_program_tool', '编程工具表', NULL, NULL, 'BlueProgramTool', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'tool', '编程工具', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06', NULL);
INSERT INTO `gen_table` VALUES (40, 'blue_pixiv_leave_message', '番剧弹幕表', NULL, NULL, 'BluePixivLeaveMessage', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'message', '番剧弹幕', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31', NULL);
INSERT INTO `gen_table` VALUES (41, 'blue_comment', '评论表', NULL, NULL, 'BlueComment', 'crud', 'element-ui', 'com.blue.blog', 'blog', 'comment', '评论', 'ruoyi', '0', '/', '{}', 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 318 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (105, 19, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (106, 19, 'bg_type', '背景类型', 'char(1)', 'String', 'bgType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_bg_type', 2, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (107, 19, 'bg_url', '背景路径', 'varchar(255)', 'String', 'bgUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 3, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (108, 19, 'bg_position', '背景位置类型', 'char(1)', 'String', 'bgPosition', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_bg_use', 4, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (109, 19, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (110, 19, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (111, 19, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (112, 19, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (113, 19, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-19 17:16:11', '', '2024-03-19 17:23:17');
INSERT INTO `gen_table_column` VALUES (114, 20, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:11');
INSERT INTO `gen_table_column` VALUES (115, 20, 'user_id', '用户ID', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:11');
INSERT INTO `gen_table_column` VALUES (116, 20, 'content', '弹幕内容', 'varchar(20)', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (117, 20, 'user_avater', '用户头像', 'varchar(100)', 'String', 'userAvater', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (118, 20, 'barrage_height', '弹幕高度', 'int', 'Long', 'barrageHeight', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (119, 20, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (120, 20, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (121, 20, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (122, 20, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (123, 20, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-19 17:27:50', '', '2024-03-19 17:29:12');
INSERT INTO `gen_table_column` VALUES (124, 21, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (125, 21, 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (126, 21, 'sort_id', '分类id', 'int', 'Long', 'sortId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_photo_sort', 3, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (127, 21, 'photo_url', '相册图片地址', 'varchar(100)', 'String', 'photoUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 4, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (128, 21, 'status', '审核状态', 'int', 'Long', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_shenhe', 6, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (129, 21, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (130, 21, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (131, 21, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (132, 21, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (133, 21, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2024-03-19 17:32:58', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (134, 22, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (135, 22, 'img_url', '图片路径', 'varchar(150)', 'String', 'imgUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'imageUpload', '', 2, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (136, 22, 'music_url', '音乐的路径', 'varchar(150)', 'String', 'musicUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'fileUpload', '', 3, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (137, 22, 'sort_id', '音乐分类ID', 'int', 'Long', 'sortId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_music_type', 5, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (138, 22, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (139, 22, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (140, 22, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (141, 22, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (142, 22, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-19 17:36:26', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (143, 23, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (144, 23, 'sort_name', '相册分类名称', 'varchar(20)', 'String', 'sortName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (145, 23, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (146, 23, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (147, 23, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (148, 23, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (149, 23, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 17:40:02', '', '2024-03-19 17:40:30');
INSERT INTO `gen_table_column` VALUES (150, 24, 'id', '分类id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (151, 24, 'sort_name', '分类的名称', 'varchar(10)', 'String', 'sortName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (152, 24, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (153, 24, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (154, 24, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (155, 24, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (156, 24, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 17:45:13', '', '2024-03-19 17:45:55');
INSERT INTO `gen_table_column` VALUES (157, 25, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (158, 25, 'sort_id', '分类名词', 'int', 'Long', 'sortId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_blog_type', 2, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (159, 25, 'tag_name', '标签的名称', 'varchar(20)', 'String', 'tagName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (160, 25, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (161, 25, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (162, 25, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (163, 25, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (164, 25, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-19 17:49:01', '', '2024-03-19 17:49:57');
INSERT INTO `gen_table_column` VALUES (165, 26, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (166, 26, 'sort_name', '分类名称', 'varchar(100)', 'String', 'sortName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (167, 26, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (168, 26, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (169, 26, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (170, 26, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (171, 26, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 17:53:32', '', '2024-03-19 19:45:32');
INSERT INTO `gen_table_column` VALUES (172, 27, 'id', '头像id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (173, 27, 'avater_url', '头像路径', 'varchar(100)', 'String', 'avaterUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 2, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (174, 27, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (175, 27, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (176, 27, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (177, 27, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (178, 27, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-19 21:20:00', '', '2024-03-19 21:28:07');
INSERT INTO `gen_table_column` VALUES (179, 22, 'music_name', '音乐名称', 'varchar(100)', 'String', 'musicName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, '', '2024-03-22 09:57:29', '', '2024-03-22 10:02:20');
INSERT INTO `gen_table_column` VALUES (180, 21, 'photo_name', '图片名称', 'varchar(100)', 'String', 'photoName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 5, '', '2024-03-22 10:15:03', '', '2024-03-22 10:15:31');
INSERT INTO `gen_table_column` VALUES (181, 28, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (182, 28, 'friend_phone', '申请手机号', 'char(11)', 'String', 'friendPhone', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (183, 28, 'friend_name', '网站名称', 'varchar(20)', 'String', 'friendName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (184, 28, 'friend_url', '网站图片', 'varchar(100)', 'String', 'friendUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 4, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (185, 28, 'friend_introduce', '介绍', 'varchar(50)', 'String', 'friendIntroduce', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (186, 28, 'status', '申请状态', 'char(1)', 'String', 'status', '0', '0', '1', '1', '0', '1', '1', 'EQ', 'select', 'sys_apply_status', 6, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (187, 28, 'please_time', '发布时间', 'timestamp', 'Date', 'pleaseTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (188, 28, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (189, 28, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (190, 28, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (191, 28, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (192, 28, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 12, 'admin', '2024-03-22 19:16:09', '', '2024-03-22 19:21:09');
INSERT INTO `gen_table_column` VALUES (193, 29, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (194, 29, 'website_name', '网站的名称', 'varchar(20)', 'String', 'websiteName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (195, 29, 'website_cover', '网站的头像', 'varchar(150)', 'String', 'websiteCover', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (196, 29, 'website_url', '网站的地址', 'varchar(100)', 'String', 'websiteUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (197, 29, 'website_detail', '网站的描述', 'varchar(50)', 'String', 'websiteDetail', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (198, 29, 'website_remark', '网站的备注', 'varchar(50)', 'String', 'websiteRemark', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (199, 29, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (200, 29, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (201, 29, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (202, 29, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:44');
INSERT INTO `gen_table_column` VALUES (203, 29, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2024-03-22 19:56:14', '', '2024-03-23 06:07:45');
INSERT INTO `gen_table_column` VALUES (204, 30, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (205, 30, 'notice_title', '公告的标题', 'varchar(20)', 'String', 'noticeTitle', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (206, 30, 'notice_content', '公告的内容', 'text', 'String', 'noticeContent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (207, 30, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (208, 30, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (209, 30, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (210, 30, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (211, 30, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-23 06:35:16', '', '2024-03-23 06:37:28');
INSERT INTO `gen_table_column` VALUES (212, 31, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (213, 31, 'friend_url', '申请友链接口', 'varchar(100)', 'String', 'friendUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (214, 31, 'friend_content', '申请友链内容', 'varchar(50)', 'String', 'friendContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (215, 31, 'friend_detail', '申请友链备注', 'varchar(50)', 'String', 'friendDetail', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (216, 31, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (217, 31, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (218, 31, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (219, 31, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (220, 31, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 9, 'admin', '2024-03-23 07:23:21', '', '2024-03-23 07:23:37');
INSERT INTO `gen_table_column` VALUES (221, 32, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (222, 32, 'user_id', '用户名称', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (223, 32, 'sort_id', '分类名称', 'int', 'Long', 'sortId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (224, 32, 'status', '审核状态', 'int', 'Long', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_shenhe', 4, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (225, 32, 'video_url', '文章视频', 'varchar(100)', 'String', 'videoUrl', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'fileUpload', '', 5, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (226, 32, 'content', '文章内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (227, 32, 'cover', '文章图片', 'varchar(100)', 'String', 'cover', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 7, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (228, 32, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (229, 32, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (230, 32, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (231, 32, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (232, 32, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 12, 'admin', '2024-03-23 11:51:10', '', '2024-03-23 12:49:49');
INSERT INTO `gen_table_column` VALUES (233, 33, 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (234, 33, 'article_id', '文章ID', 'int', 'Long', 'articleId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (235, 33, 'tag_id', '标签ID', 'int', 'Long', 'tagId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (236, 33, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (237, 33, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (238, 33, 'create_by', '创建者', 'int', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (239, 33, 'update_by', '修改者', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (240, 33, 'remark', '备注', 'varchar(20)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2024-03-23 15:03:48', '', '2024-03-27 23:30:37');
INSERT INTO `gen_table_column` VALUES (241, 34, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (242, 34, 'pixiv_name', '番剧名称', 'varchar(50)', 'String', 'pixivName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (243, 34, 'pixiv_avater', '番剧图片', 'varchar(100)', 'String', 'pixivAvater', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 3, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (244, 34, 'pixiv_publish', '番剧发布时间', 'timestamp', 'Date', 'pixivPublish', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (245, 34, 'pixiv_score', '评分', 'varchar(10)', 'String', 'pixivScore', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (246, 34, 'pixiv_type', '番剧类型', 'int', 'Long', 'pixivType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (247, 34, 'pixiv_voice_actor', '声优', 'varchar(255)', 'String', 'pixivVoiceActor', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (248, 34, 'pixiv_synopsis', '番剧简介', 'varchar(255)', 'String', 'pixivSynopsis', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (249, 34, 'pixiv_play', '播放数', 'int', 'Long', 'pixivPlay', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (250, 34, 'is_end', '是否完结', 'int', 'Long', 'isEnd', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'system_is_end', 11, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (251, 34, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (252, 34, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (253, 34, 'create_by', '创建人', 'int', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (254, 34, 'update_by', '修改人', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2024-04-01 09:28:32', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (255, 34, 'status', '审核状态', 'int', 'Long', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', 'sys_apply_status', 10, '', '2024-04-01 10:00:28', '', '2024-04-01 10:05:55');
INSERT INTO `gen_table_column` VALUES (256, 35, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (257, 35, 'type_name', '类型名称', 'varchar(50)', 'String', 'typeName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (258, 35, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (259, 35, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (260, 35, 'create_by', '创建人', 'timestamp', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (261, 35, 'update_by', '修改人', 'timestamp', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-04-01 10:30:05', '', '2024-04-01 10:31:56');
INSERT INTO `gen_table_column` VALUES (262, 36, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (263, 36, 'pixiv_id', '番剧ID', 'int', 'Long', 'pixivId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (264, 36, 'pixiv_chapters', '番剧集', 'int', 'Long', 'pixivChapters', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (265, 36, 'pixiv_url', '番剧地址', 'varchar(100)', 'String', 'pixivUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'fileUpload', '', 4, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (266, 36, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (267, 36, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (268, 36, 'create_by', '创建人', 'timestamp', 'Date', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (269, 36, 'update_by', '修改人', 'timestamp', 'Date', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2024-04-01 22:30:56', '', '2024-04-01 22:37:31');
INSERT INTO `gen_table_column` VALUES (270, 37, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (271, 37, 'ec_author', '二创作者', 'varchar(50)', 'String', 'ecAuthor', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (272, 37, 'ec_name', '二创标题', 'varchar(50)', 'String', 'ecName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (273, 37, 'ec_avater', '二创图片', 'varchar(100)', 'String', 'ecAvater', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 4, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (274, 37, 'ec_url', '二创路径', 'varchar(100)', 'String', 'ecUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (275, 37, 'ec_publish', '二创发布时间', 'timestamp', 'Date', 'ecPublish', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (276, 37, 'ec_synopsis', '二创简介', 'varchar(255)', 'String', 'ecSynopsis', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (278, 37, 'status', '审核状态', 'int', 'Long', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sys_shenhe', 8, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (279, 37, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (280, 37, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (281, 37, 'create_by', '创建人', 'int', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (282, 37, 'update_by', '修改人', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2024-04-02 22:38:42', '', '2024-04-02 22:45:41');
INSERT INTO `gen_table_column` VALUES (283, 38, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (284, 38, 'sort_name', '分类名称', 'varchar(20)', 'String', 'sortName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (285, 38, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (286, 38, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (287, 38, 'create_by', '创建人', 'int', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (288, 38, 'update_by', '修改人', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2024-04-03 21:56:18', '', '2024-04-03 21:59:30');
INSERT INTO `gen_table_column` VALUES (289, 39, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (290, 39, 'sort_id', '工具分类ID', 'int', 'Long', 'sortId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (291, 39, 'tool_name', '工具名称', 'varchar(20)', 'String', 'toolName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (292, 39, 'tool_describe', '工具简介', 'varchar(50)', 'String', 'toolDescribe', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (293, 39, 'tool_avater', '工具图片', 'varchar(255)', 'String', 'toolAvater', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 5, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (294, 39, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (295, 39, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2024-04-03 22:28:33', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (296, 39, 'create_by', '创建人', 'int', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-04-03 22:28:34', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (297, 39, 'update_by', '修改人', 'int', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2024-04-03 22:28:34', '', '2024-04-03 22:31:06');
INSERT INTO `gen_table_column` VALUES (298, 40, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (299, 40, 'pixiv_id', '番剧ID', 'int', 'Long', 'pixivId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (300, 40, 'episode_id', '集ID', 'int', 'Long', 'episodeId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (301, 40, 'border', '是否描边 0表示描边,1不描边', 'tinyint(1)', 'Integer', 'border', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (302, 40, 'color', '背景色', 'varchar(20)', 'String', 'color', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (303, 40, 'text', '弹幕内容', 'varchar(30)', 'String', 'text', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (304, 40, 'time', '弹幕发送时间', 'decimal(20,6)', 'BigDecimal', 'time', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (305, 40, 'mode', '弹幕模式: 0表示滚动, 1静止', 'tinyint(1)', 'Integer', 'mode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (306, 40, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (307, 40, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (308, 40, 'create_by', '创建人', 'timestamp', 'Date', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (309, 40, 'update_by', '修改人', 'timestamp', 'Date', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2024-04-05 11:38:40', '', '2024-04-05 11:41:31');
INSERT INTO `gen_table_column` VALUES (310, 41, 'id', 'ID', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (311, 41, 'parent_id', '是否父节点', 'int', 'Long', 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (312, 41, 'comment_type', '回复类型', 'int', 'Long', 'commentType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'system_comment_type', 3, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (313, 41, 'comment_content', '回复内容', 'varchar(100)', 'String', 'commentContent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 4, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (314, 41, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (315, 41, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (316, 41, 'create_by', '创建人', 'timestamp', 'Long', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');
INSERT INTO `gen_table_column` VALUES (317, 41, 'update_by', '修改人', 'timestamp', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2024-04-08 00:00:24', '', '2024-04-08 00:03:42');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-03-15 02:01:58', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-03-15 02:01:58', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-03-15 02:01:58', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'true', 'Y', 'admin', '2024-03-15 02:01:58', 'admin', '2024-03-19 22:03:29', '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-03-15 02:01:58', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '碧蓝档案多人博客', 0, '夏天', '18074589556', '939648675@qq.com', '0', '0', 'admin', '2024-03-15 02:01:47', 'admin', '2024-04-14 22:59:30');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '开发部', 1, '夏天', '18074589556', '939648675@qq.com', '0', '0', 'admin', '2024-03-15 02:01:47', 'admin', '2024-04-14 23:00:24');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '全栈开发', 1, '夏天', '18074589556', '939648675@qq.com', '0', '0', 'admin', '2024-03-15 02:01:47', 'admin', '2024-04-14 23:00:51');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-03-15 02:01:47', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2024-03-15 02:01:58', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '未删除', '0', 'sys_is_delete', NULL, 'success', 'N', '0', 'admin', '2024-03-16 16:08:42', 'admin', '2024-03-16 16:09:00', '未删除');
INSERT INTO `sys_dict_data` VALUES (101, 1, '已删除', '1', 'sys_is_delete', NULL, 'danger', 'N', '0', 'admin', '2024-03-16 16:09:28', 'admin', '2024-03-16 16:09:53', '已删除');
INSERT INTO `sys_dict_data` VALUES (102, 0, '图片', '0', 'sys_bg_type', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:12:11', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 0, '视频', '1', 'sys_bg_type', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:12:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 0, '首页', '0', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:14:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 1, '发现', '1', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:15:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 2, '分类', '2', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:15:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 3, '相册', '3', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:15:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 4, '友链', '4', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:15:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 5, '听歌', '5', 'sys_bg_use', NULL, 'default', 'N', '0', 'admin', '2024-03-16 16:16:01', 'admin', '2024-03-17 17:12:18', NULL);
INSERT INTO `sys_dict_data` VALUES (111, 0, '日常', '1', 'sys_photo_sort', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:53:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 0, '美食', '2', 'sys_photo_sort', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:54:13', 'admin', '2024-03-17 20:54:18', NULL);
INSERT INTO `sys_dict_data` VALUES (113, 0, '旅游', '3', 'sys_photo_sort', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:54:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 0, '风景', '4', 'sys_photo_sort', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:54:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 0, '未审核', '0', 'sys_shenhe', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:56:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (116, 0, '已通过', '1', 'sys_shenhe', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:57:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (117, 0, '未通过', '2', 'sys_shenhe', NULL, 'default', 'N', '0', 'admin', '2024-03-17 20:57:11', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (118, 0, '雅韵生活', '1', 'sys_blog_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 21:57:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (119, 1, '博学雅境', '2', 'sys_blog_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 21:57:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (120, 2, '悠游雅途', '3', 'sys_blog_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 21:57:33', 'admin', '2024-03-17 21:57:38', NULL);
INSERT INTO `sys_dict_data` VALUES (121, 3, '雅趣时光', '4', 'sys_blog_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 21:57:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (122, 0, 'Pop music｜坠入欧美音海深处', '1', 'sys_music_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 22:20:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (123, 1, '治愈系日语✘跨越时光去拥抱你', '2', 'sys_music_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 22:21:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (124, 2, '古风女声:女嗓开腔，唱尽繁华', '3', 'sys_music_type', NULL, 'default', 'N', '0', 'admin', '2024-03-17 22:21:12', 'admin', '2024-03-17 22:21:19', NULL);
INSERT INTO `sys_dict_data` VALUES (125, 0, '申请中', '0', 'sys_apply_status', NULL, 'default', 'N', '0', 'admin', '2024-03-22 19:19:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (126, 0, '申请通过', '1', 'sys_apply_status', NULL, 'default', 'N', '0', 'admin', '2024-03-22 19:19:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (127, 0, '申请驳回', '2', 'sys_apply_status', NULL, 'default', 'N', '0', 'admin', '2024-03-22 19:19:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (218, 0, '未完结', '0', 'system_is_end', NULL, 'default', 'N', '0', 'admin', '2024-04-01 10:02:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (219, 1, '已完结', '1', 'system_is_end', NULL, 'default', 'N', '0', 'admin', '2024-04-01 10:03:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (220, 0, '弹幕', '0', 'system_comment_type', NULL, 'default', 'N', '0', 'admin', '2024-04-08 00:01:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (221, 1, '番剧', '1', 'system_comment_type', NULL, 'default', 'N', '0', 'admin', '2024-04-08 00:01:41', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2024-03-15 02:01:56', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2024-03-15 02:01:57', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '是否删除', 'sys_is_delete', '0', 'admin', '2024-03-16 16:07:32', '', NULL, '是否被删除');
INSERT INTO `sys_dict_type` VALUES (101, '背景类型', 'sys_bg_type', '0', 'admin', '2024-03-16 16:11:54', '', NULL, '背景类型');
INSERT INTO `sys_dict_type` VALUES (102, '背景使用类型', 'sys_bg_use', '0', 'admin', '2024-03-16 16:14:28', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (103, '相册分类', 'sys_photo_sort', '0', 'admin', '2024-03-17 20:52:40', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (104, '审核流程', 'sys_shenhe', '0', 'admin', '2024-03-17 20:56:14', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (105, '文章分类', 'sys_blog_type', '0', 'admin', '2024-03-17 21:56:51', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (106, '音乐类型', 'sys_music_type', '0', 'admin', '2024-03-17 22:20:37', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (107, '申请状态', 'sys_apply_status', '0', 'admin', '2024-03-22 19:19:06', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (109, '是否完结', 'system_is_end', '0', 'admin', '2024-04-01 10:02:13', 'admin', '2024-04-01 10:02:21', NULL);
INSERT INTO `sys_dict_type` VALUES (110, '回复类型', 'system_comment_type', '0', 'admin', '2024-04-08 00:01:18', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2024-03-15 02:01:58', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2024-03-15 02:01:58', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2024-03-15 02:01:59', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '提示信息',
  `access_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`access_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2070 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 3, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2024-03-15 02:01:48', 'admin', '2024-03-22 18:59:26', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 1, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2024-03-15 02:01:48', 'admin', '2024-03-22 18:59:06', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 2, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2024-03-15 02:01:48', 'admin', '2024-03-22 18:59:22', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2024-03-15 02:01:48', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2024-03-15 02:01:48', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2024-03-15 02:01:48', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2024-03-15 02:01:48', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2024-03-15 02:01:48', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2024-03-15 02:01:48', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2024-03-15 02:01:48', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2024-03-15 02:01:48', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2024-03-15 02:01:49', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2024-03-15 02:01:49', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2024-03-15 02:01:49', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', '', '', 0, 0, 'C', '0', '0', 'monitor:sentinel:list', 'sentinel', 'admin', '2024-03-15 02:01:49', '', NULL, '流量控制菜单');
INSERT INTO `sys_menu` VALUES (112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', '', '', 0, 0, 'C', '0', '0', 'monitor:nacos:list', 'nacos', 'admin', '2024-03-15 02:01:49', '', NULL, '服务治理菜单');
INSERT INTO `sys_menu` VALUES (113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', '', '', 0, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2024-03-15 02:01:49', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2024-03-15 02:01:49', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2024-03-15 02:01:49', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'http://localhost:8080/swagger-ui/index.html', '', '', 0, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2024-03-15 02:01:49', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'system/operlog/index', '', 1, 0, 'C', '0', '0', 'system:operlog:list', 'form', 'admin', '2024-03-15 02:01:49', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'system/logininfor/index', '', 1, 0, 'C', '0', '0', 'system:logininfor:list', 'logininfor', 'admin', '2024-03-15 02:01:49', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2024-03-15 02:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2024-03-15 02:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:query', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:remove', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:export', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:export', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:unlock', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2024-03-15 02:01:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2024-03-15 02:01:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2024-03-15 02:01:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '博客管理', 0, 0, 'blog', NULL, NULL, 1, 0, 'M', '0', '0', '', 'list', 'admin', '2024-03-16 14:13:40', 'admin', '2024-03-22 18:59:01', '');
INSERT INTO `sys_menu` VALUES (2001, '网站背景管理', 2000, 1, 'bg', 'blog/bg/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-16 14:17:45', 'admin', '2024-03-23 21:13:13', '');
INSERT INTO `sys_menu` VALUES (2002, '网站弹幕管理', 2000, 2, 'message', 'blog/message/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-16 16:35:42', 'admin', '2024-03-23 21:13:17', '');
INSERT INTO `sys_menu` VALUES (2004, '博客分类管理', 0, 0, 'sort', NULL, NULL, 1, 0, 'M', '0', '0', '', 'list', 'admin', '2024-03-17 19:58:21', 'admin', '2024-03-22 18:59:13', '');
INSERT INTO `sys_menu` VALUES (2005, '相册分类管理', 2004, 1, 'photo', 'sort/photo/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 19:59:18', 'admin', '2024-03-23 21:13:44', '');
INSERT INTO `sys_menu` VALUES (2006, '网站相册管理', 2000, 3, 'photo', 'blog/photo/index', '', 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 21:11:04', '18074589556', '2024-04-19 17:00:50', '');
INSERT INTO `sys_menu` VALUES (2007, '文章分类管理', 2004, 2, 'sort', 'sort/sort/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 21:46:56', 'admin', '2024-03-23 21:13:48', '');
INSERT INTO `sys_menu` VALUES (2008, '文章标签管理', 2004, 3, 'tag', 'sort/tag/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 21:54:48', 'admin', '2024-03-23 21:13:51', '');
INSERT INTO `sys_menu` VALUES (2009, '音乐分类管理', 2004, 4, 'music', 'sort/music/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 22:08:49', 'admin', '2024-03-23 21:13:55', '');
INSERT INTO `sys_menu` VALUES (2010, '网站音乐管理', 2000, 4, 'music', 'blog/music/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-17 22:22:00', 'admin', '2024-03-23 21:13:30', '');
INSERT INTO `sys_menu` VALUES (2012, '友链管理', 0, 0, 'friend', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'peoples', 'admin', '2024-03-22 19:12:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '友链链接管理', 2012, 1, 'friend', 'blog/friend/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-22 19:13:25', 'admin', '2024-03-23 21:14:04', '');
INSERT INTO `sys_menu` VALUES (2014, '审核管理', 0, 0, 'auditing', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'button', 'admin', '2024-03-22 19:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '友链审核', 2014, 1, 'friend', 'auditing/friend/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-22 19:27:34', 'admin', '2024-03-23 20:50:25', '');
INSERT INTO `sys_menu` VALUES (2016, '博客信息管理', 0, 0, 'website', 'website/index', NULL, 1, 1, 'C', '0', '0', '', 'dict', 'admin', '2024-03-22 19:44:39', 'admin', '2024-03-23 21:14:16', '');
INSERT INTO `sys_menu` VALUES (2019, '文章管理', 0, 0, 'article', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'list', 'admin', '2024-03-23 12:51:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '文章列表管理', 2019, 1, 'article', 'blog/article/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-23 12:52:09', '18074589556', '2024-04-19 17:13:13', '');
INSERT INTO `sys_menu` VALUES (2021, '文章审核', 2014, 2, 'article', 'auditing/article/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-23 14:45:51', 'admin', '2024-03-23 20:50:29', '');
INSERT INTO `sys_menu` VALUES (2022, '文章标签关联管理', 2004, 5, 'articleTag', 'sort/sortTag/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-03-23 16:05:32', 'admin', '2024-03-23 21:13:59', '');
INSERT INTO `sys_menu` VALUES (2023, '文章编辑管理', 2019, 2, 'edit', 'blog/edit/index', NULL, 1, 1, 'C', '1', '0', '', 'client', 'admin', '2024-03-24 21:15:31', 'admin', '2024-03-26 09:19:43', '');
INSERT INTO `sys_menu` VALUES (2024, '相册审核', 2014, 3, 'photo', 'auditing/photo/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-03-27 14:15:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '网站番剧管理', 2000, 5, 'pixiv', 'blog/tv/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-04-01 10:06:46', 'admin', '2024-04-01 22:59:26', '');
INSERT INTO `sys_menu` VALUES (2026, '番剧分类管理', 2004, 6, 'pixiv', 'sort/tv/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-04-01 10:29:55', 'admin', '2024-04-01 20:02:40', '');
INSERT INTO `sys_menu` VALUES (2027, '资源管理', 0, 0, 'resource', NULL, NULL, 1, 0, 'M', '0', '0', '', 'list', 'admin', '2024-04-01 22:34:38', 'admin', '2024-04-01 22:34:47', '');
INSERT INTO `sys_menu` VALUES (2028, '番剧资源管理', 2027, 0, 'episode', 'resource/episode/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-01 22:35:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '头像资源管理', 2027, 1, 'avater', 'resource/avater/index', NULL, 1, 0, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-01 22:58:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '番剧审核', 2014, 4, 'tv', 'auditing/tv/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-04-02 22:09:50', 'admin', '2024-04-02 22:10:04', '');
INSERT INTO `sys_menu` VALUES (2031, '网站二创管理', 2000, 6, 'erchuang', 'blog/erchuang/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-04-02 22:38:20', '18074589556', '2024-04-19 17:05:34', '');
INSERT INTO `sys_menu` VALUES (2032, '二创审核', 2014, 5, 'erchuang', 'auditing/erchuang/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-02 22:57:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '编程工具分类', 2004, 7, 'tool', 'sort/tool/index', NULL, 1, 1, 'C', '0', '0', '', 'client', 'admin', '2024-04-03 22:02:44', 'admin', '2024-04-03 22:03:04', '');
INSERT INTO `sys_menu` VALUES (2034, '网站工具管理', 2000, 7, 'tool', 'blog/tool/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-03 22:30:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '网址番剧弹幕管理', 2000, 8, 'tvMessage', 'blog/tvMessage/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-05 11:35:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '文章评论管理', 2000, 9, 'comment', 'blog/comment/index', NULL, 1, 1, 'C', '0', '0', NULL, 'client', 'admin', '2024-04-09 15:56:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '添加背景', 2001, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:bg:add', '#', '18074589556', '2024-04-19 16:55:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2039, '修改背景', 2001, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:bg:edit', '#', '18074589556', '2024-04-19 16:56:11', '18074589556', '2024-04-19 16:57:02', '');
INSERT INTO `sys_menu` VALUES (2040, '删除背景', 2001, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:bg:remove', '#', '18074589556', '2024-04-19 16:56:55', '18074589556', '2024-04-19 16:57:09', '');
INSERT INTO `sys_menu` VALUES (2041, '修改弹幕', 2002, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:message:edit', '#', '18074589556', '2024-04-19 16:58:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '删除弹幕', 2002, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:message:remove', '#', '18074589556', '2024-04-19 16:58:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '新增相册', 2006, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:photo:add', '#', '18074589556', '2024-04-19 17:01:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2044, '修改相册', 2006, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:photo:edit', '#', '18074589556', '2024-04-19 17:01:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2045, '删除相册', 2006, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:photo:remove', '#', '18074589556', '2024-04-19 17:01:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '查询相册', 2006, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:photo:query', '#', '18074589556', '2024-04-19 17:02:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '新增音乐', 2010, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:music:add', '#', '18074589556', '2024-04-19 17:03:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '修改音乐', 2010, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:music:edit', '#', '18074589556', '2024-04-19 17:03:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2049, '删除音乐', 2010, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:music:remove', '#', '18074589556', '2024-04-19 17:04:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '新增番剧', 2025, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tv:add', '#', '18074589556', '2024-04-19 17:04:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '修改番剧', 2025, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tv:edit', '#', '18074589556', '2024-04-19 17:04:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '删除番剧', 2025, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tv:remove', '#', '18074589556', '2024-04-19 17:05:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '查询二创', 2031, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:erchuang:query', '#', '18074589556', '2024-04-19 17:05:43', '18074589556', '2024-04-19 17:28:43', '');
INSERT INTO `sys_menu` VALUES (2054, '新增二创', 2031, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:erchuang:add', '#', '18074589556', '2024-04-19 17:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '修改二创', 2031, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:erchuang:edit', '#', '18074589556', '2024-04-19 17:06:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, '删除二创', 2031, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:erchuang:remove', '#', '18074589556', '2024-04-19 17:06:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2057, '新增工具', 2034, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tool:add', '#', '18074589556', '2024-04-19 17:07:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2058, '修改工具', 2034, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tool:edit', '#', '18074589556', '2024-04-19 17:07:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, '删除工具', 2034, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:tool:remove', '#', '18074589556', '2024-04-19 17:08:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2060, '修改番剧弹幕', 2035, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:message:edit', '#', '18074589556', '2024-04-19 17:08:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2061, '删除番剧弹幕', 2035, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:message:remove', '#', '18074589556', '2024-04-19 17:08:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2062, '新增评论', 2036, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:comment:add', '#', '18074589556', '2024-04-19 17:10:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2063, '修改评论', 2036, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:comment:edit', '#', '18074589556', '2024-04-19 17:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2064, '删除评论', 2036, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:comment:remove', '#', '18074589556', '2024-04-19 17:10:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2065, '新增文章', 2020, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:article:add', '#', '18074589556', '2024-04-19 17:15:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2066, '查询文章', 2020, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:article:query', '#', '18074589556', '2024-04-19 17:15:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '修改文章', 2020, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:article:edit', '#', '18074589556', '2024-04-19 17:15:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, '删除文章', 2020, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:article:remove', '#', '18074589556', '2024-04-19 17:16:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2069, '审核文章', 2020, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'blog:article:auditing', '#', '18074589556', '2024-04-22 17:27:32', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2024-03-15 02:01:59', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2024-03-15 02:01:59', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (2, '操作日志', 3, 'com.blue.system.controller.SysOperlogController.remove()', 'DELETE', 1, '18074589556', NULL, '/operlog/1', '127.0.0.1', '', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-04-23 16:14:43', 9);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '岗位信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'admin', '超级管理员', 1, '0', 'admin', '2024-03-15 02:01:47', 'admin', '2024-04-14 23:11:45', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-03-15 02:01:48', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '2', 'admin', '2024-03-15 02:01:48', '', NULL, '普通角色');
INSERT INTO `sys_role` VALUES (100, '普通用户', 'user', 2, '1', 1, 1, '0', '0', '18074589556', '2024-04-15 21:16:54', '18074589556', '2024-04-19 17:25:45', NULL);
INSERT INTO `sys_role` VALUES (101, '高级用户', 'vip', 3, '1', 1, 1, '0', '0', '18074589556', '2024-04-15 22:08:43', '18074589556', '2024-04-15 22:09:00', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (100, 2000);
INSERT INTO `sys_role_menu` VALUES (100, 2006);
INSERT INTO `sys_role_menu` VALUES (100, 2019);
INSERT INTO `sys_role_menu` VALUES (100, 2020);
INSERT INTO `sys_role_menu` VALUES (100, 2031);
INSERT INTO `sys_role_menu` VALUES (100, 2036);
INSERT INTO `sys_role_menu` VALUES (100, 2043);
INSERT INTO `sys_role_menu` VALUES (100, 2044);
INSERT INTO `sys_role_menu` VALUES (100, 2045);
INSERT INTO `sys_role_menu` VALUES (100, 2046);
INSERT INTO `sys_role_menu` VALUES (100, 2053);
INSERT INTO `sys_role_menu` VALUES (100, 2054);
INSERT INTO `sys_role_menu` VALUES (100, 2055);
INSERT INTO `sys_role_menu` VALUES (100, 2056);
INSERT INTO `sys_role_menu` VALUES (100, 2062);
INSERT INTO `sys_role_menu` VALUES (100, 2063);
INSERT INTO `sys_role_menu` VALUES (100, 2064);
INSERT INTO `sys_role_menu` VALUES (100, 2065);
INSERT INTO `sys_role_menu` VALUES (100, 2066);
INSERT INTO `sys_role_menu` VALUES (100, 2067);
INSERT INTO `sys_role_menu` VALUES (100, 2068);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, '18074589556', '夏天', '00', '939648675@qq.com', '18074589556', '0', 'http://127.0.0.1:9300/statics/2024/03/19/avater45_20240319220142A076.png', '$2a$10$4FEjOW6gO7ZJSCFkhiKse.8Sj0qMdBDytMWj7rk5MmSnRLA8drajO', '0', '0', '127.0.0.1', '2024-03-16 22:58:52', 'admin', '2024-03-15 02:01:47', '', '2024-04-15 19:25:39', '管理员');
-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (104, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (104, 100);
INSERT INTO `sys_user_role` VALUES (107, 100);
INSERT INTO `sys_user_role` VALUES (108, 100);

SET FOREIGN_KEY_CHECKS = 1;
