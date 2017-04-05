/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : sati

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-31 05:32:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminstrators
-- ----------------------------
DROP TABLE IF EXISTS `adminstrators`;
CREATE TABLE `adminstrators` (
  `ADMINISTRATOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PBI_ID` int(11) DEFAULT NULL COMMENT '个人基本信息id',
  `ADMIN_TYPE` int(11) DEFAULT NULL COMMENT '管理员类型',
  PRIMARY KEY (`ADMINISTRATOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminstrators
-- ----------------------------
INSERT INTO `adminstrators` VALUES ('8', '19', '1');
INSERT INTO `adminstrators` VALUES ('9', '23', '2');

-- ----------------------------
-- Table structure for agencies
-- ----------------------------
DROP TABLE IF EXISTS `agencies`;
CREATE TABLE `agencies` (
  `AGENCY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UNIT_NATURE` varchar(64) DEFAULT NULL COMMENT '单位性质',
  `CLASSIC_CASE` varchar(255) DEFAULT NULL COMMENT '经典案例',
  `SOE_ID` int(11) DEFAULT NULL COMMENT '单位从业人员id',
  `BASE_INFO_ID` int(11) DEFAULT NULL COMMENT '基本信息Id',
  `INDUSTRY` varchar(32) DEFAULT NULL,
  `EMP_SITUATION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`AGENCY_ID`),
  KEY `FK_m1hitpg8vmancen2aac9suv35` (`EMP_SITUATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agencies
-- ----------------------------
INSERT INTO `agencies` VALUES ('2', '1', '经典案例', '4', '4', null, null);

-- ----------------------------
-- Table structure for audit_infos
-- ----------------------------
DROP TABLE IF EXISTS `audit_infos`;
CREATE TABLE `audit_infos` (
  `AUDIT_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBMIT_TIME` datetime DEFAULT NULL COMMENT '提交审核时间',
  `AUDIT_TIME` datetime DEFAULT NULL COMMENT '审核时间',
  `AUDIT_STATUS` int(11) DEFAULT '-1' COMMENT '审核状态',
  `AUDIT_RESULT` varchar(255) DEFAULT NULL COMMENT '审核结果',
  `USER_ID` varchar(64) DEFAULT NULL COMMENT '被审核用户',
  `AUDITOR_ID` varchar(64) DEFAULT NULL COMMENT '审核人用户账号',
  `AUDIT_TYPE` int(11) DEFAULT NULL COMMENT '审核的信息类型',
  PRIMARY KEY (`AUDIT_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of audit_infos
-- ----------------------------
INSERT INTO `audit_infos` VALUES ('27', '2017-03-30 17:09:08', '2017-03-30 17:09:08', '1', null, null, null, '1');
INSERT INTO `audit_infos` VALUES ('30', '2017-03-30 23:14:59', '2017-03-30 23:14:59', '2', '审核通过', null, 'admin', '1');
INSERT INTO `audit_infos` VALUES ('31', '2017-03-30 21:52:19', '2017-03-30 21:52:19', '2', '长得丑', '3', 'admin', '1');
INSERT INTO `audit_infos` VALUES ('34', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('35', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('36', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('37', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('68', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('69', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('70', '2017-03-31 03:11:17', '2017-03-31 03:11:17', '1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('71', '2017-03-30 23:43:53', '2017-03-30 23:43:53', '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('72', '2017-03-30 23:43:55', '2017-03-30 23:43:55', '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('73', '2017-03-30 23:43:58', '2017-03-30 23:43:58', '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('79', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('82', null, null, '-1', null, null, null, '4');
INSERT INTO `audit_infos` VALUES ('83', null, null, '-1', null, null, null, '4');
INSERT INTO `audit_infos` VALUES ('84', null, null, '-1', null, null, null, '4');
INSERT INTO `audit_infos` VALUES ('85', '2017-03-31 04:36:19', '2017-03-31 04:36:19', '2', null, null, 'admin', null);
INSERT INTO `audit_infos` VALUES ('86', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('87', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('88', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('89', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('90', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('91', null, null, '-1', null, null, null, null);
INSERT INTO `audit_infos` VALUES ('92', null, null, '-1', null, null, null, null);

-- ----------------------------
-- Table structure for category_infos
-- ----------------------------
DROP TABLE IF EXISTS `category_infos`;
CREATE TABLE `category_infos` (
  `CATEGORY_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_INFO_NAME` varchar(64) DEFAULT NULL COMMENT '类名',
  `FATHER_ID` int(11) DEFAULT NULL COMMENT '父分类id',
  `CATEGORY_INFO_TYPE` int(11) DEFAULT NULL COMMENT '分类类型（区分各个分类）',
  `CATEGORY_INFO_LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_infos
-- ----------------------------

-- ----------------------------
-- Table structure for companies
-- ----------------------------
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `COMPANY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NATURE_ID` int(11) DEFAULT NULL COMMENT '企业性质id',
  `REGISTERED_CAPITAL` float DEFAULT NULL COMMENT '注册资金（万元）',
  `REGISTERED_ADDRESS` varchar(64) DEFAULT NULL COMMENT '注册地址',
  `INDUSTRY_ID` int(11) DEFAULT NULL COMMENT '所属行业id',
  `CIRC_OR_NOT` int(11) DEFAULT NULL COMMENT '是否国家创新驿站',
  `CTT_OR_NOT` int(11) DEFAULT NULL COMMENT '是否国家技术转移',
  `BUSINESS_FIELD` varchar(255) DEFAULT NULL COMMENT '业务领域',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `SOE_ID` int(11) DEFAULT NULL COMMENT '单位从业人员id',
  `BASE_INFO_ID` int(11) DEFAULT NULL COMMENT '基本信息',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companies
-- ----------------------------
INSERT INTO `companies` VALUES ('1', '1', '10000', '广东省梅州市', '6', null, null, null, null, '1', '1');

-- ----------------------------
-- Table structure for contact_infos
-- ----------------------------
DROP TABLE IF EXISTS `contact_infos`;
CREATE TABLE `contact_infos` (
  `CONTACT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTACT_NAME` varchar(32) DEFAULT NULL COMMENT '联系人姓名',
  `CONTACT_BUSINESS` varchar(32) DEFAULT NULL COMMENT '联系人职务',
  `CONTACT_NUMBER` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `CONTACT_ADDRESS` varchar(255) DEFAULT NULL COMMENT '通讯地址',
  `POSTCODE` varchar(32) DEFAULT NULL COMMENT '邮编',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT 'email',
  `CONTACT_URL` varchar(255) DEFAULT NULL COMMENT '联系人的网址或公司网址',
  `FAX_NUMBER` varchar(64) DEFAULT NULL COMMENT '传真号码',
  `QQORMSN_NUMER` varchar(64) DEFAULT NULL COMMENT 'QQ或MSN号码',
  `PHONE_NUMBER` varchar(64) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_infos
-- ----------------------------
INSERT INTO `contact_infos` VALUES ('27', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('30', 'company', '执行负责人', '12345555', '广东省梅州市嘉应学院', '51400', 'company@qq.com', 'www.jiaying.edu.cn', 'www.jiaying.edu.cn', '126556366', '18813973442');
INSERT INTO `contact_infos` VALUES ('31', 'person', '项目负责人', '1234', '梅州市嘉应学院', '51400', 'person@qq.com', 'www.jiaying.edu.cn', 'www.jiaying.edu.cn', '1234567891', '18813973444');
INSERT INTO `contact_infos` VALUES ('34', 'agency', '项目负责人', '18813973445', '梅州市嘉应学院', '51400', 'agency@qq.com', 'www.jiaying.edu.cn', 'www.jiaying.edu.cn', '1253685', '18813973445');
INSERT INTO `contact_infos` VALUES ('35', 'college', '项目负责人', '18813973447', '梅州市嘉应学院', '51400', 'college@qq.com', 'www.jiaying.edu.cn', 'www.jiaying.edu.cn', '1213212313', '18813973447');
INSERT INTO `contact_infos` VALUES ('36', 'scientify', '项目负责人', '18813973448', '梅州市嘉应学院', '51400', 'scientify@qq.com', 'www.jiaying.edu.cn', 'www.jiaying.edu.cn', '1236644', '18813973448');
INSERT INTO `contact_infos` VALUES ('37', null, null, null, null, null, 'platform@qq.com', null, null, null, '18813973449');
INSERT INTO `contact_infos` VALUES ('64', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('65', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('66', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('67', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('68', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('69', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('75', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('78', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('79', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('80', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('81', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('82', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('83', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('84', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('85', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('86', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('87', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');
INSERT INTO `contact_infos` VALUES ('88', null, null, null, null, null, '1095682334@qq.com', null, null, null, '18813973446');

-- ----------------------------
-- Table structure for data_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionaries`;
CREATE TABLE `data_dictionaries` (
  `DATA_DICTIONARY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATA_KEY` varchar(32) DEFAULT NULL,
  `DATA_VALUE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`DATA_DICTIONARY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_dictionaries
-- ----------------------------

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `DOCUMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DOCUMENT_URL` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `DOCUMENT_DESC` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `DOCUMENT_TYPE` int(11) DEFAULT NULL COMMENT '文件类型字段',
  PRIMARY KEY (`DOCUMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------
INSERT INTO `documents` VALUES ('26', 'uploadFiles/personImages/admin3/e6a20960104b11e7abd35d3d523cb045.jpg', '【admin3】的个人照片', '2');
INSERT INTO `documents` VALUES ('27', 'uploadFiles/idcardImages/admin3/0d894bb0104c11e7abd35d3d523cb045.jpg', '【admin3】的身份证照片', '1');
INSERT INTO `documents` VALUES ('28', 'uploadFiles/enclosures/admin3/0f99c3800ffc11e7dcd9fa8bf8c5ae5b.jar', '【admin3】的附件', '3');
INSERT INTO `documents` VALUES ('31', 'uploadFiles/enclosures/person/7b88c0d0130a11e73daf8e9315c1f8ef.jpg', '【person】的附件', '3');
INSERT INTO `documents` VALUES ('32', 'uploadFiles/homePageImages/home/9eb9f1e0104a11e769101e74c1076322.jpg', '主页宣传图片', '5');
INSERT INTO `documents` VALUES ('33', 'uploadFiles/personImages/person/b85d16c0120e11e711037bb88fc6cdfd.jpg', '【person】的个人照片', '2');
INSERT INTO `documents` VALUES ('34', 'uploadFiles/idcardImages/person/be576490120e11e711037bb88fc6cdfd.jpg', '【person】的身份证照片', '1');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FATHER_MENU_ID` int(11) DEFAULT NULL COMMENT '父菜单id',
  `MENU_NAME` varchar(32) DEFAULT NULL COMMENT '菜单名',
  `MENU_LEVEL` int(11) DEFAULT NULL COMMENT '菜单级别',
  `MENU_ORDER` int(11) DEFAULT NULL COMMENT '菜单排序',
  `LINK_URL` varchar(255) DEFAULT NULL COMMENT '关联的url',
  `STATUS` int(11) DEFAULT NULL COMMENT '菜单状态',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`MENU_ID`),
  KEY `FK_djr36jlatprs8xy84dwr0892b` (`FATHER_MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('1', '11', '个人用户管理', '2', '1', 'getManagment', '1', '2017-03-21 14:20:51');
INSERT INTO `menus` VALUES ('2', '11', '超级管理员管理', '2', '7', 'getManagment', '1', '2017-03-21 14:20:53');
INSERT INTO `menus` VALUES ('3', '11', '平台管理员管理', '2', '6', 'getManagment', '1', '2017-03-21 14:20:56');
INSERT INTO `menus` VALUES ('4', '11', '中介单位管理', '2', '3', 'getManagment', '1', '2017-03-21 14:20:58');
INSERT INTO `menus` VALUES ('5', '11', '企业用户管理', '2', '2', 'getManagment', '1', '2017-03-21 14:21:00');
INSERT INTO `menus` VALUES ('6', '11', '高校单位管理', '2', '4', 'getManagment', '1', '2017-03-21 14:21:02');
INSERT INTO `menus` VALUES ('7', '11', '科研单位管理', '2', '5', 'getManagment', '1', '2017-03-21 14:21:04');
INSERT INTO `menus` VALUES ('8', null, '菜单权限管理', '1', '2', 'menu/PermissionManagment', '1', '2017-03-21 14:21:08');
INSERT INTO `menus` VALUES ('9', '8', '菜单管理', '2', '3', 'getMenuManagment', '1', '2017-03-22 21:17:02');
INSERT INTO `menus` VALUES ('10', '8', '权限管理', '2', '2', 'getPermissionManagment', '1', '2017-03-21 14:21:13');
INSERT INTO `menus` VALUES ('11', null, '用户信息管理', '1', '1', 'user/userManagment', '1', '2017-03-22 16:13:19');
INSERT INTO `menus` VALUES ('17', null, '审核信息管理', '1', '4', 'getAuditManagment', '1', '2017-03-22 16:13:40');
INSERT INTO `menus` VALUES ('19', '17', '用户注册审核管理', '2', '1', 'getAuditUserManagment', '1', '2017-03-22 19:59:27');
INSERT INTO `menus` VALUES ('21', '17', '技术供给审核管理', '2', '2', 'getAuditTechSupplyManagment', '1', '2017-03-22 13:38:10');
INSERT INTO `menus` VALUES ('22', '17', '技术需求审核管理', '2', '3', 'getAuditTechRequireManagment', '1', '2017-03-22 13:39:25');
INSERT INTO `menus` VALUES ('23', null, '技术供给管理', '1', '4', 'getTechSupplyMangment', '1', '2017-03-22 15:48:43');
INSERT INTO `menus` VALUES ('24', null, '技术需求管理', '1', '5', 'getTechRequireManagment', '1', '2017-03-22 15:49:34');
INSERT INTO `menus` VALUES ('25', null, '供需对接管理', '1', '6', 'getButtInfoManagment', '1', '2017-03-22 20:56:35');
INSERT INTO `menus` VALUES ('26', null, '政策法规管理', '1', '7', 'getPlicyManagment', '1', '2017-03-22 15:51:26');
INSERT INTO `menus` VALUES ('27', null, '系统公告管理', '1', '8', 'getNoticeManament', '1', '2017-03-22 15:52:10');
INSERT INTO `menus` VALUES ('28', null, '产品成果管理', '1', '9', 'getProduceManagment', '1', '2017-03-22 15:54:48');
INSERT INTO `menus` VALUES ('29', null, '会员信息管理', '1', '10', 'getUserInfoManagment', '1', '2017-03-22 15:56:33');
INSERT INTO `menus` VALUES ('30', '19', '个人用户审核', '3', '1', 'getAuditUserManagment', '1', '2017-03-23 18:23:26');
INSERT INTO `menus` VALUES ('31', '8', '角色管理', '2', '1', 'getRoleManagment', '1', '2017-03-22 21:16:49');
INSERT INTO `menus` VALUES ('32', '19', '企业用户审核', '3', '2', 'getCompanyAuditManagment', '1', '2017-03-22 19:45:47');
INSERT INTO `menus` VALUES ('33', '19', '高校用户审核', '3', '3', 'getAuditCollegeManagment', '1', '2017-03-22 19:36:36');
INSERT INTO `menus` VALUES ('34', '19', '科研单位用户审核', '3', '4', 'getAuditScientifyManament', '1', '2017-03-22 19:41:03');
INSERT INTO `menus` VALUES ('35', '19', '中介单位用户审核', '3', '5', 'getAuditAgencyManagment', '1', '2017-03-22 20:10:45');
INSERT INTO `menus` VALUES ('36', '23', '发布我的技术供给', '2', '1', 'getTechSupplyPublish', '1', '2017-03-22 20:45:56');
INSERT INTO `menus` VALUES ('37', '23', '管理我的技术供给', '2', '2', '1', '1', '2017-03-22 20:46:41');
INSERT INTO `menus` VALUES ('38', '24', '发布我的技术需求', '2', '1', 'getTechSupplyPublish', '1', '2017-03-22 20:48:43');
INSERT INTO `menus` VALUES ('39', '24', '管理我的技术需求', '2', '2', 'getTechRequireManagment', '1', '2017-03-23 23:00:46');
INSERT INTO `menus` VALUES ('40', '25', '供需对接', '2', '1', 'getButtInfo', '1', '2017-03-22 20:56:05');
INSERT INTO `menus` VALUES ('41', '26', '发布政策法规', '2', '1', 'getPolicyPublish', '1', '2017-03-22 22:06:55');
INSERT INTO `menus` VALUES ('42', '26', '管理政策法规', '2', '2', 'getPolicyManagment', '1', '2017-03-22 20:59:08');
INSERT INTO `menus` VALUES ('43', '27', '发布系统公告', '2', '1', 'getNoticePublish', '1', '2017-03-22 21:01:01');
INSERT INTO `menus` VALUES ('44', '27', '管理系统公告', '2', '2', 'getNoticeManagment', '1', '2017-03-22 21:01:54');
INSERT INTO `menus` VALUES ('45', '28', '发布我的产品成果', '2', '1', 'getProducePublish', '1', '2017-03-22 21:02:38');
INSERT INTO `menus` VALUES ('46', '28', '管理我的产品成果', '2', '2', 'getProduceManagment', '1', '2017-03-22 21:03:22');
INSERT INTO `menus` VALUES ('47', '29', '我的个人信息', '2', '1', 'getPersonInfoManagment', '1', '2017-03-22 21:08:39');
INSERT INTO `menus` VALUES ('48', '29', '我的企业信息', '2', '2', 'getCompanyInfoManagment', '1', '2017-03-22 21:08:47');
INSERT INTO `menus` VALUES ('49', '29', '我的高校信息', '2', '3', 'getCollegeManagment', '1', '2017-03-22 21:09:36');
INSERT INTO `menus` VALUES ('50', '29', '我的科研单位信息', '2', '7', 'getScientifyManagment', '1', '2017-03-22 21:13:25');
INSERT INTO `menus` VALUES ('51', '29', '我的中介单位信息', '2', '5', 'getAgencyManagment', '1', '2017-03-22 21:11:01');
INSERT INTO `menus` VALUES ('52', '29', '我的管理员信息', '2', '4', 'getPlatFormInfoManagment', '1', '2017-03-22 21:13:04');
INSERT INTO `menus` VALUES ('53', null, '测试', '1', '11', 'getTest', '-1', '2017-03-26 01:57:13');
INSERT INTO `menus` VALUES ('54', '17', '产品成果审核管理', '2', '4', 'getProduceAuditManagment', '1', '2017-03-28 13:16:28');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FATHER_PERMISSION_ID` int(11) DEFAULT NULL COMMENT '父权限id',
  `PERMISSION_MARK` int(11) DEFAULT NULL COMMENT '权限标记',
  `PERMISSION_LEVEL` int(11) DEFAULT NULL COMMENT '权限级别',
  `PERMISSION_NAME` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `STATUS` int(11) DEFAULT '1' COMMENT '权限状态',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `OPERATE_TIME` datetime DEFAULT NULL COMMENT '上次操作时间',
  PRIMARY KEY (`PERMISSION_ID`),
  KEY `FK_94k5em7snpfoci8plkmgdxxbt` (`FATHER_PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', '11', '2', '2', 'user:read_person_info', '读取个人用户信息权限', '1', '2017-03-22 19:20:25', '2017-03-22 19:20:25');
INSERT INTO `permissions` VALUES ('2', '11', '2', '2', 'user:read_super_admin_info', '读取超级管理员信息权限', '1', '2017-03-22 19:20:20', '2017-03-22 19:20:20');
INSERT INTO `permissions` VALUES ('3', '11', '2', '2', 'user:read_plat_admin_info', '读取平台管理员信息权限', '1', '2017-03-22 19:20:19', '2017-03-22 19:20:19');
INSERT INTO `permissions` VALUES ('4', '11', '2', '2', 'user:read_agency_info', '读取中介机构信息权限', '1', '2017-03-22 19:20:18', '2017-03-22 19:20:18');
INSERT INTO `permissions` VALUES ('5', '11', '2', '2', 'user:read_colleage_info', '读取高校用户信息权限', '1', '2017-03-22 19:20:15', '2017-03-22 20:41:32');
INSERT INTO `permissions` VALUES ('6', '11', '2', '2', 'user:read_scientif_info', '读取科研单位信息权限', '1', '2017-03-22 19:20:14', '2017-03-22 19:20:14');
INSERT INTO `permissions` VALUES ('7', '11', '2', '2', 'user:read_company_info', '读取企业用户信息权限', '1', '2017-03-22 19:20:12', '2017-03-22 20:41:58');
INSERT INTO `permissions` VALUES ('8', null, '1', '1', 'user:permission_menu_manager', '菜单权限管理权限', '1', '2017-03-22 22:02:51', '2017-03-22 22:02:51');
INSERT INTO `permissions` VALUES ('9', '8', '1', '2', 'user:menu_manager', '菜单管理权限', '1', '2017-03-22 22:02:49', '2017-03-22 22:02:49');
INSERT INTO `permissions` VALUES ('10', '8', '1', '2', 'user:permission_manager', '权限管理权限', '1', '2017-03-22 22:02:48', '2017-03-22 22:02:48');
INSERT INTO `permissions` VALUES ('11', null, '1', '1', 'user:user_managment', '用户信息管理权限', '1', '2017-03-22 19:20:08', '2017-03-22 20:40:00');
INSERT INTO `permissions` VALUES ('12', '8', '2', '2', 'menu:read_menu', '读取菜单的权限', '1', '2017-03-22 22:02:46', '2017-03-22 22:02:46');
INSERT INTO `permissions` VALUES ('13', '8', '2', '2', 'menu:update_menu', '更新菜单的权限', '1', '2017-03-22 22:02:38', '2017-03-22 22:02:38');
INSERT INTO `permissions` VALUES ('14', '10', '2', '3', 'menu:add_permission', '添加权限的权限', '1', '2017-03-22 22:02:44', '2017-03-22 22:02:44');
INSERT INTO `permissions` VALUES ('15', '11', '2', '2', 'user:add_user', '添加用户的权限', '1', '2017-03-22 19:18:26', '2017-03-22 19:18:26');
INSERT INTO `permissions` VALUES ('16', '11', '2', '2', 'user:delete_user', '删除用户的权限', '1', '2017-03-22 19:18:25', '2017-03-22 19:18:25');
INSERT INTO `permissions` VALUES ('17', '9', '2', '3', 'menu:add_menu', '添加菜单的权限', '1', '2017-03-22 22:02:42', '2017-03-22 22:02:42');
INSERT INTO `permissions` VALUES ('18', '9', '2', '3', 'menu:delete_menu', '删除菜单的权限', '1', '2017-03-22 22:02:40', '2017-03-22 22:02:40');
INSERT INTO `permissions` VALUES ('19', null, '1', '1', 'audit:audit_managment', '审核信息管理权限', '1', '2017-03-22 19:18:17', '2017-03-22 19:18:17');
INSERT INTO `permissions` VALUES ('20', '19', '2', '2', 'audit:read_audit_info', '读取审核信息权限', '1', '2017-03-22 19:18:15', '2017-03-22 19:18:15');
INSERT INTO `permissions` VALUES ('28', '19', '1', '2', 'audit:audit_user_managment', '用户审核管理权限', '1', '2017-03-22 19:18:12', '2017-03-22 19:18:12');
INSERT INTO `permissions` VALUES ('29', '28', '1', '3', 'audit:audit_conpany_managment', '企业用户审核管理权限', '1', '2017-03-22 19:18:09', '2017-03-22 19:44:06');
INSERT INTO `permissions` VALUES ('30', '28', '1', '3', 'audit:audit_person_managment', '个人用户审核管理权限', '1', '2017-03-22 19:18:07', '2017-03-22 19:18:07');
INSERT INTO `permissions` VALUES ('31', '19', '1', '2', 'audit:tech_supply_managment', '技术供给审核管理权限', '1', '2017-03-22 19:18:05', '2017-03-22 19:18:05');
INSERT INTO `permissions` VALUES ('32', '19', '1', '2', 'audit:tech_require_managment', '技术需求审核管理权限', '1', '2017-03-22 19:18:02', '2017-03-22 19:18:02');
INSERT INTO `permissions` VALUES ('33', '28', '1', '3', 'audit:audit_colleage_managment', '高校用户审核管理权限', '1', '2017-03-22 19:18:00', '2017-03-22 19:18:00');
INSERT INTO `permissions` VALUES ('34', '28', '1', '3', 'audit:audit_scientify_managment', '科研单位用户审核管理权限', '1', '2017-03-22 19:17:29', '2017-03-22 19:17:29');
INSERT INTO `permissions` VALUES ('35', '28', '1', '3', 'audit:audit_agency_managment', '中介单位用户审核管理权限', '1', '2017-03-22 19:17:28', '2017-03-22 19:17:28');
INSERT INTO `permissions` VALUES ('36', '19', '1', '2', 'permission:general_permission_managment', '普通权限管理权限', '1', '2017-03-22 19:17:26', '2017-03-22 19:17:26');
INSERT INTO `permissions` VALUES ('37', null, '1', '1', 'policy:policy_managment', '政策法规管理权限', '1', '2017-03-23 18:34:05', '2017-03-23 18:34:05');
INSERT INTO `permissions` VALUES ('38', null, '1', '1', 'notice:notice_managment', '系统公告管理权限', '1', '2017-03-22 19:17:17', '2017-03-22 20:40:31');
INSERT INTO `permissions` VALUES ('40', null, '1', '1', 'tech:tech_supply_managment', '技术供给管理权限', '1', '2017-03-22 19:17:10', '2017-03-22 19:17:10');
INSERT INTO `permissions` VALUES ('41', null, '1', '1', 'tech:tech_require_managment', '技术需求管理权限', '1', '2017-03-22 19:17:08', '2017-03-22 19:17:08');
INSERT INTO `permissions` VALUES ('42', null, '1', '1', 'user:user_info_managment', '会员信息管理权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('43', null, '1', '1', 'produce:produce_managment', '产品成果管理权限', '1', '2017-03-22 19:17:04', '2017-03-22 19:17:04');
INSERT INTO `permissions` VALUES ('44', '8', '1', '2', 'role:role_pemission_managment', '角色权限管理权限', '1', '2017-03-22 22:02:54', '2017-03-22 22:02:54');
INSERT INTO `permissions` VALUES ('46', '40', '1', '2', 'tech:tech_supply_publish', '发布我的技术供给权限', '1', '2017-03-22 20:17:25', '2017-03-22 20:22:24');
INSERT INTO `permissions` VALUES ('47', '40', '1', '2', 'tech:managment_tech_supply', '管理我的技术供给权限', '1', '2017-03-22 20:18:52', '2017-03-22 20:22:34');
INSERT INTO `permissions` VALUES ('48', '41', '1', '2', 'tech:tech_require_publish', '发布我的技术需求权限', '1', '2017-03-22 20:20:33', '2017-03-22 20:22:04');
INSERT INTO `permissions` VALUES ('49', '41', '1', '2', 'tech:mange_tech_require', '管理我的技术需求权限', '1', '2017-03-22 20:21:49', '2017-03-22 20:21:49');
INSERT INTO `permissions` VALUES ('50', '42', '1', '2', 'user:personal_info_managment', '我的个人信息维护权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('51', '42', '1', '2', 'user:company_info_managment', '我的企业信息维护权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('52', '42', '1', '2', 'user:college_info_managment', '我的高校信息维护权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('53', '42', '1', '2', 'user:scientify_info_managment', '我的科研单位信息维护权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('54', '42', '1', '2', 'user:agency_info_managment', '我的中介单位信息维护权限', '1', '2017-03-22 20:28:40', '2017-03-22 20:28:40');
INSERT INTO `permissions` VALUES ('55', '42', '1', '2', 'user:platform_info_managment', '我的管理员信息维护权限', '1', '2017-03-22 20:29:48', '2017-03-22 20:31:36');
INSERT INTO `permissions` VALUES ('56', '43', '1', '2', 'produce:produce_publish', '发布我的产品成果权限', '1', '2017-03-22 20:31:18', '2017-03-22 20:32:56');
INSERT INTO `permissions` VALUES ('57', '43', '1', '2', 'produce:manage_produce', '管理我的产品成果权限', '1', '2017-03-22 20:32:42', '2017-03-22 20:33:10');
INSERT INTO `permissions` VALUES ('58', '37', '1', '2', 'policy:publish_policy', '发布政策法规权限', '1', '2017-03-23 18:31:21', '2017-03-23 18:31:21');
INSERT INTO `permissions` VALUES ('59', '37', '1', '2', 'policy:manage_policy', '管理政策法规权限', '1', '2017-03-23 18:34:05', '2017-03-23 18:34:05');
INSERT INTO `permissions` VALUES ('60', '38', '1', '2', 'notice:publsh_notice', '发布系统公告权限', '1', '2017-03-22 20:36:46', '2017-03-22 20:38:41');
INSERT INTO `permissions` VALUES ('61', '38', '1', '2', 'notice:manage_notice', '管理系统公告权限', '1', '2017-03-22 20:38:17', '2017-03-22 20:40:58');
INSERT INTO `permissions` VALUES ('62', null, '1', '1', 'butt:butt_managment', '供需对接管理权限', '1', '2017-03-22 22:21:43', '2017-03-22 22:21:43');
INSERT INTO `permissions` VALUES ('63', '62', '1', '2', 'butt:manage_butt', '供需对接权限', '1', '2017-03-22 22:21:43', '2017-03-22 22:21:43');
INSERT INTO `permissions` VALUES ('64', null, '1', '1', '测试', '测试', '1', '2017-03-23 23:11:27', '2017-03-23 23:11:27');
INSERT INTO `permissions` VALUES ('65', '66', '1', '2', 'test2', '子菜单权限1', '1', '2017-03-28 14:55:32', '2017-03-28 14:55:32');
INSERT INTO `permissions` VALUES ('66', null, '1', '1', 'test2', '测试2', '1', '2017-03-23 23:12:44', '2017-03-23 23:12:44');
INSERT INTO `permissions` VALUES ('70', '19', '1', '2', 'audit:produce_audit_managment', '产品成果审核管理权限', '1', '2017-03-28 15:24:00', '2017-03-28 15:24:00');

-- ----------------------------
-- Table structure for permission_menus
-- ----------------------------
DROP TABLE IF EXISTS `permission_menus`;
CREATE TABLE `permission_menus` (
  `PERMISSION_MUNU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION_ID` int(11) DEFAULT NULL,
  `MENU_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`PERMISSION_MUNU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_menus
-- ----------------------------
INSERT INTO `permission_menus` VALUES ('1', '1', '1', '2017-03-13 01:10:35');
INSERT INTO `permission_menus` VALUES ('2', '2', '2', '2017-03-13 01:10:48');
INSERT INTO `permission_menus` VALUES ('3', '3', '3', '2017-03-13 01:10:56');
INSERT INTO `permission_menus` VALUES ('4', '4', '4', '2017-03-13 01:11:06');
INSERT INTO `permission_menus` VALUES ('5', '5', '5', '2017-03-13 01:11:17');
INSERT INTO `permission_menus` VALUES ('6', '6', '6', '2017-03-13 01:11:31');
INSERT INTO `permission_menus` VALUES ('7', '7', '7', '2017-03-13 01:11:40');
INSERT INTO `permission_menus` VALUES ('8', '8', '8', '2017-03-14 08:49:00');
INSERT INTO `permission_menus` VALUES ('9', '9', '9', '2017-03-14 08:49:11');
INSERT INTO `permission_menus` VALUES ('10', '10', '10', '2017-03-14 08:49:23');
INSERT INTO `permission_menus` VALUES ('11', '11', '11', '2017-03-14 09:03:47');
INSERT INTO `permission_menus` VALUES ('12', '19', '17', '2017-03-20 13:27:49');
INSERT INTO `permission_menus` VALUES ('13', '28', '19', '2017-03-22 20:02:46');
INSERT INTO `permission_menus` VALUES ('15', '31', '21', '2017-03-22 14:00:04');
INSERT INTO `permission_menus` VALUES ('16', '32', '22', '2017-03-22 14:00:07');
INSERT INTO `permission_menus` VALUES ('17', '40', '23', '2017-03-22 15:48:43');
INSERT INTO `permission_menus` VALUES ('18', '41', '24', '2017-03-22 15:49:34');
INSERT INTO `permission_menus` VALUES ('19', '62', '25', '2017-03-22 15:50:23');
INSERT INTO `permission_menus` VALUES ('20', '37', '26', '2017-03-22 15:51:26');
INSERT INTO `permission_menus` VALUES ('21', '38', '27', '2017-03-22 15:52:10');
INSERT INTO `permission_menus` VALUES ('22', '43', '28', '2017-03-22 15:54:48');
INSERT INTO `permission_menus` VALUES ('23', '42', '29', '2017-03-22 15:56:33');
INSERT INTO `permission_menus` VALUES ('24', '30', '30', '2017-03-22 18:53:02');
INSERT INTO `permission_menus` VALUES ('25', '44', '31', '2017-03-22 19:07:20');
INSERT INTO `permission_menus` VALUES ('26', '29', '32', '2017-03-22 19:13:08');
INSERT INTO `permission_menus` VALUES ('27', '33', '33', '2017-03-22 19:36:36');
INSERT INTO `permission_menus` VALUES ('28', '34', '34', '2017-03-22 19:41:03');
INSERT INTO `permission_menus` VALUES ('29', '35', '35', '2017-03-22 20:10:45');
INSERT INTO `permission_menus` VALUES ('30', '46', '36', '2017-03-22 20:45:56');
INSERT INTO `permission_menus` VALUES ('31', '47', '37', '2017-03-22 20:46:41');
INSERT INTO `permission_menus` VALUES ('32', '48', '38', '2017-03-22 20:48:43');
INSERT INTO `permission_menus` VALUES ('33', '49', '39', '2017-03-22 20:49:51');
INSERT INTO `permission_menus` VALUES ('34', '63', '40', '2017-03-22 20:56:05');
INSERT INTO `permission_menus` VALUES ('35', '58', '41', '2017-03-22 20:58:20');
INSERT INTO `permission_menus` VALUES ('36', '59', '42', '2017-03-22 20:59:08');
INSERT INTO `permission_menus` VALUES ('37', '60', '43', '2017-03-22 21:01:01');
INSERT INTO `permission_menus` VALUES ('38', '61', '44', '2017-03-22 21:01:54');
INSERT INTO `permission_menus` VALUES ('39', '56', '45', '2017-03-22 21:02:38');
INSERT INTO `permission_menus` VALUES ('40', '57', '46', '2017-03-22 21:03:22');
INSERT INTO `permission_menus` VALUES ('41', '50', '47', '2017-03-22 21:05:33');
INSERT INTO `permission_menus` VALUES ('42', '51', '48', '2017-03-22 21:07:22');
INSERT INTO `permission_menus` VALUES ('43', '52', '49', '2017-03-22 21:09:36');
INSERT INTO `permission_menus` VALUES ('44', '53', '50', '2017-03-22 21:10:17');
INSERT INTO `permission_menus` VALUES ('45', '54', '51', '2017-03-22 21:11:01');
INSERT INTO `permission_menus` VALUES ('46', '55', '52', '2017-03-22 21:12:09');
INSERT INTO `permission_menus` VALUES ('47', '64', '53', '2017-03-23 23:18:19');
INSERT INTO `permission_menus` VALUES ('48', '70', '54', '2017-03-28 13:16:28');

-- ----------------------------
-- Table structure for persons
-- ----------------------------
DROP TABLE IF EXISTS `persons`;
CREATE TABLE `persons` (
  `PERSON_ID` int(11) NOT NULL AUTO_INCREMENT,
  `POLITICAL_LANDSCAPE` int(11) DEFAULT NULL COMMENT '政治面貌',
  `LEARN_MAJOR_ID` int(11) DEFAULT NULL COMMENT '所学专业id',
  `DEGREE_ID` int(11) DEFAULT NULL COMMENT '学位',
  `UNIVERSITY` varchar(64) DEFAULT NULL COMMENT '毕业院校',
  `WORK_MAJOR_ID` int(11) DEFAULT NULL COMMENT '从事专业',
  `INDUSTRY_ID` int(11) DEFAULT NULL COMMENT '研究行业id',
  `RESEARCH_DIRECTION` varchar(64) DEFAULT NULL COMMENT '研究方向',
  `INFO_KEY` varchar(255) DEFAULT NULL COMMENT '关键字',
  `ACHIEVEMENT` varchar(255) DEFAULT NULL COMMENT '个人成绩成果简介',
  `ENCLOSURE_ID` int(11) DEFAULT NULL COMMENT '附件id',
  `BASE_INFO_ID` int(11) DEFAULT NULL COMMENT '个人基本信息id',
  `ACADEMIC_TITLE_ID` int(11) DEFAULT NULL COMMENT '职称',
  `HIGH_TECH_FIELD_ID` int(11) DEFAULT NULL COMMENT '高新技术领域',
  `LOCATION_ID` int(11) DEFAULT NULL COMMENT '所在地',
  PRIMARY KEY (`PERSON_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persons
-- ----------------------------
INSERT INTO `persons` VALUES ('3', '1', '81202', null, '嘉应学院', '81202', '201', '软件开发', '软件', '成果简介               ', '31', '22', '505', '201', '112');

-- ----------------------------
-- Table structure for person_infos
-- ----------------------------
DROP TABLE IF EXISTS `person_infos`;
CREATE TABLE `person_infos` (
  `PBI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERSON_NAME` varchar(32) DEFAULT NULL COMMENT '用户名',
  `IDCARD_NUMBER` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `IDCARD_IMAGE_ID` varchar(11) DEFAULT NULL COMMENT '身份证正面图片id',
  `SEX` int(11) DEFAULT NULL COMMENT '性别',
  `NATION` varchar(32) DEFAULT NULL COMMENT '民族',
  `BIRTH` datetime DEFAULT NULL COMMENT '出生日期',
  `EDUCATION_ID` int(11) DEFAULT NULL COMMENT '学历id',
  `WORKPLACE` varchar(64) DEFAULT NULL COMMENT '工作单位',
  `BUSINESS` varchar(255) DEFAULT NULL COMMENT '职务',
  `PERSON_IMAGE_ID` int(11) DEFAULT NULL COMMENT '个人照片id',
  `PERSONAL_PROFILE` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `CONTACT_ID` int(11) DEFAULT NULL COMMENT '联系人id',
  PRIMARY KEY (`PBI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_infos
-- ----------------------------
INSERT INTO `person_infos` VALUES ('19', null, null, null, null, null, '2017-03-18 01:12:00', null, null, null, null, null, null, '27');
INSERT INTO `person_infos` VALUES ('22', '张三', '1231424555', '34', '1', '汉族', '2017-03-27 00:00:00', '5', '计算机学院', '开发', '33', '个人简介	\r\n	                        ', '备注\r\n	                        ', '31');
INSERT INTO `person_infos` VALUES ('23', null, null, null, null, null, null, null, null, null, null, null, null, '37');

-- ----------------------------
-- Table structure for produces
-- ----------------------------
DROP TABLE IF EXISTS `produces`;
CREATE TABLE `produces` (
  `PRODUCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `PRODUCE_NAME` varchar(255) DEFAULT NULL COMMENT '产品成果名称',
  `PRODUCE_DESC` text COMMENT '产品成果描述',
  `PRODUCE_TYPE_ID` int(11) DEFAULT NULL COMMENT '产品成果类别',
  `PRODUCE_STATUS` int(11) DEFAULT '-1' COMMENT '产品成果状态',
  `PRODUCE_KEY` varchar(255) DEFAULT NULL COMMENT '搜索关键字',
  `PUBLISHER_ID` varchar(64) DEFAULT NULL COMMENT '发布人Id',
  `IS_RECOMMEND` int(11) DEFAULT '-1' COMMENT '是否推荐到首页展示',
  `AUDIT_INFO_ID` int(11) DEFAULT NULL COMMENT '审核信息id',
  `CONTACT_INFO_ID` int(11) DEFAULT NULL COMMENT '联系人信息id',
  `PAGE_VIEW` int(11) DEFAULT '0' COMMENT '浏览次数',
  `PUBLISH_TIME` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`PRODUCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of produces
-- ----------------------------
INSERT INTO `produces` VALUES ('15', 't5', 'fasdfa', '1', '1', 'test', 'admin', '1', '70', '66', '0', '2017-03-31 00:00:00');
INSERT INTO `produces` VALUES ('16', null, null, null, '2', 'yiss', 'admin', '1', '71', '67', '0', '2017-03-30 23:26:02');
INSERT INTO `produces` VALUES ('17', null, null, null, '2', '2343', 'admin', '1', '72', '68', '0', '2017-03-31 02:42:04');
INSERT INTO `produces` VALUES ('18', null, null, null, '1', '343', 'admin', '1', '73', '69', '0', '2017-03-30 23:20:12');
INSERT INTO `produces` VALUES ('26', null, null, null, '1', null, 'admin', '-1', '82', '78', '0', null);
INSERT INTO `produces` VALUES ('27', null, null, null, '1', null, 'admin', '-1', '83', '79', '0', null);
INSERT INTO `produces` VALUES ('28', null, null, null, '1', null, 'admin', '-1', '84', '80', '0', null);

-- ----------------------------
-- Table structure for produce_images
-- ----------------------------
DROP TABLE IF EXISTS `produce_images`;
CREATE TABLE `produce_images` (
  `PRODUCE_IMAGE_ID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `PRODUCE_IMAGE_DESC` varchar(255) DEFAULT NULL COMMENT '产品成果描述',
  `PRODUCE_IMAGE_URL` varchar(255) DEFAULT NULL COMMENT '产品成果图片URL',
  `PRODUCE_IMAGE_TYPE` int(11) DEFAULT NULL COMMENT '产品成果图片',
  `PRODUCE_ID` int(11) DEFAULT NULL COMMENT '绑定产品id',
  PRIMARY KEY (`PRODUCE_IMAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of produce_images
-- ----------------------------
INSERT INTO `produce_images` VALUES ('00000000001', '测试', 'uploadFiles/produceImages/admin/fab50230123611e7054875ae4abc9861.jpg', null, '5');
INSERT INTO `produce_images` VALUES ('00000000002', '测试2', 'uploadFiles/produceImages/admin/27d8f280123711e7054875ae4abc9861.jpg', null, '5');
INSERT INTO `produce_images` VALUES ('00000000003', '测试3', 'uploadFiles/produceImages/admin/473052e0123711e7054875ae4abc9861.jpg', null, '5');
INSERT INTO `produce_images` VALUES ('00000000005', '测试描述信息', 'uploadFiles/produceImages/admin/dc4b7240156d11e7b5139168a0520989.jpg', '-1', '18');
INSERT INTO `produce_images` VALUES ('00000000007', 'cd', 'uploadFiles/produceImages/admin/50d9d6e0157511e7b5139168a0520989.jpg', '-1', '26');
INSERT INTO `produce_images` VALUES ('00000000008', '测试', 'uploadFiles/produceImages/admin/16129410157611e7b5139168a0520989.jpg', '-1', '26');
INSERT INTO `produce_images` VALUES ('00000000009', 'asdf', 'uploadFiles/produceImages/admin/efd5f430157611e7542b3f2e19202d97.jpg', '-1', '27');
INSERT INTO `produce_images` VALUES ('00000000010', 'adf', 'uploadFiles/produceImages/admin/0c7d04d0157b11e7804b4f20cb9a4d66.jpg', '-1', '15');

-- ----------------------------
-- Table structure for produce_visitors
-- ----------------------------
DROP TABLE IF EXISTS `produce_visitors`;
CREATE TABLE `produce_visitors` (
  `VISITOR_IP` varchar(255) NOT NULL COMMENT '访问者ip',
  `VIEW_DATE` datetime DEFAULT NULL COMMENT '访问时间',
  `PRODUCE_ID` int(11) DEFAULT NULL COMMENT '访问的产品id',
  `VIEW_TIMES` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`VISITOR_IP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of produce_visitors
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(32) DEFAULT NULL COMMENT '规则名',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '规则描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `OPERATE_TIME` datetime NOT NULL COMMENT '最近一次操作时间',
  `STATUS` int(11) DEFAULT '-1',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'super_admin', '超级管理员', '2017-03-12 22:33:01', '2017-03-12 22:33:06', '1');
INSERT INTO `roles` VALUES ('2', 'platform_admin', '平台管理员', '2017-03-12 22:33:58', '2017-03-12 22:33:58', '1');
INSERT INTO `roles` VALUES ('3', 'person', '个人用户', '2017-03-12 22:34:16', '2017-03-12 22:34:21', '1');
INSERT INTO `roles` VALUES ('4', 'company', '企业用户', '2017-03-12 22:34:51', '2017-03-12 22:34:55', '1');
INSERT INTO `roles` VALUES ('5', 'agency', '中介机构用户', '2017-03-12 22:35:30', '2017-03-12 22:35:35', '1');
INSERT INTO `roles` VALUES ('6', 'colleage', '高校用户', '2017-03-12 22:36:00', '2017-03-12 22:36:04', '1');
INSERT INTO `roles` VALUES ('7', 'scientific', '科研单位用户', '2017-03-12 22:36:48', '2017-03-12 22:36:53', '1');

-- ----------------------------
-- Table structure for role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `ROLE_PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `PERMISSION_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ROLE_PERMISSION_ID`),
  KEY `FK_sppcfnq6y9jh9wuinjeylelxh` (`PERMISSION_ID`),
  KEY `FK_8o0gs9igpb6w4lqgutb0xnmjx` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permissions
-- ----------------------------
INSERT INTO `role_permissions` VALUES ('80', '1', '1', '2017-03-23 15:02:14');
INSERT INTO `role_permissions` VALUES ('81', '1', '2', '2017-03-23 15:02:55');
INSERT INTO `role_permissions` VALUES ('82', '1', '3', '2017-03-23 15:02:59');
INSERT INTO `role_permissions` VALUES ('83', '1', '4', '2017-03-23 15:03:01');
INSERT INTO `role_permissions` VALUES ('84', '1', '5', '2017-03-23 15:03:04');
INSERT INTO `role_permissions` VALUES ('85', '1', '6', '2017-03-23 15:03:09');
INSERT INTO `role_permissions` VALUES ('86', '1', '7', '2017-03-23 15:03:13');
INSERT INTO `role_permissions` VALUES ('91', '1', '11', '2017-03-23 15:06:20');
INSERT INTO `role_permissions` VALUES ('94', '1', '38', '2017-03-23 15:06:34');
INSERT INTO `role_permissions` VALUES ('95', '1', '40', '2017-03-23 15:06:41');
INSERT INTO `role_permissions` VALUES ('96', '1', '41', '2017-03-23 15:06:45');
INSERT INTO `role_permissions` VALUES ('98', '1', '43', '2017-03-23 15:06:53');
INSERT INTO `role_permissions` VALUES ('99', '1', '62', '2017-03-23 15:06:57');
INSERT INTO `role_permissions` VALUES ('103', '1', '15', '2017-03-23 15:07:18');
INSERT INTO `role_permissions` VALUES ('104', '1', '16', '2017-03-23 15:07:22');
INSERT INTO `role_permissions` VALUES ('110', '1', '46', '2017-03-23 15:07:49');
INSERT INTO `role_permissions` VALUES ('111', '1', '47', '2017-03-23 15:07:53');
INSERT INTO `role_permissions` VALUES ('112', '1', '48', '2017-03-23 15:07:57');
INSERT INTO `role_permissions` VALUES ('113', '1', '49', '2017-03-23 15:08:01');
INSERT INTO `role_permissions` VALUES ('120', '1', '56', '2017-03-23 15:08:42');
INSERT INTO `role_permissions` VALUES ('121', '1', '57', '2017-03-23 15:08:47');
INSERT INTO `role_permissions` VALUES ('124', '1', '60', '2017-03-23 15:09:03');
INSERT INTO `role_permissions` VALUES ('125', '1', '61', '2017-03-23 15:09:09');
INSERT INTO `role_permissions` VALUES ('126', '1', '63', '2017-03-23 15:09:14');
INSERT INTO `role_permissions` VALUES ('135', '3', '57', '2017-03-23 17:25:34');
INSERT INTO `role_permissions` VALUES ('136', '3', '43', '2017-03-23 17:26:10');
INSERT INTO `role_permissions` VALUES ('137', '3', '56', '2017-03-23 17:26:11');
INSERT INTO `role_permissions` VALUES ('139', '3', '48', '2017-03-23 17:42:03');
INSERT INTO `role_permissions` VALUES ('140', '3', '41', '2017-03-23 17:42:23');
INSERT INTO `role_permissions` VALUES ('141', '3', '49', '2017-03-23 17:42:23');
INSERT INTO `role_permissions` VALUES ('142', '3', '40', '2017-03-23 17:42:49');
INSERT INTO `role_permissions` VALUES ('143', '3', '46', '2017-03-23 17:42:49');
INSERT INTO `role_permissions` VALUES ('144', '3', '47', '2017-03-23 17:42:49');
INSERT INTO `role_permissions` VALUES ('174', '1', '37', '2017-03-23 18:32:26');
INSERT INTO `role_permissions` VALUES ('175', '1', '58', '2017-03-23 18:32:26');
INSERT INTO `role_permissions` VALUES ('176', '1', '59', '2017-03-23 18:32:26');
INSERT INTO `role_permissions` VALUES ('188', '2', '37', '2017-03-23 18:37:55');
INSERT INTO `role_permissions` VALUES ('189', '2', '58', '2017-03-23 18:37:55');
INSERT INTO `role_permissions` VALUES ('190', '2', '59', '2017-03-23 18:37:55');
INSERT INTO `role_permissions` VALUES ('191', '2', '38', '2017-03-23 18:38:00');
INSERT INTO `role_permissions` VALUES ('192', '2', '60', '2017-03-23 18:38:00');
INSERT INTO `role_permissions` VALUES ('193', '2', '61', '2017-03-23 18:38:00');
INSERT INTO `role_permissions` VALUES ('194', '2', '55', '2017-03-23 18:38:39');
INSERT INTO `role_permissions` VALUES ('195', '3', '50', '2017-03-23 18:39:02');
INSERT INTO `role_permissions` VALUES ('196', '4', '40', '2017-03-23 18:39:37');
INSERT INTO `role_permissions` VALUES ('197', '4', '46', '2017-03-23 18:39:37');
INSERT INTO `role_permissions` VALUES ('198', '4', '47', '2017-03-23 18:39:37');
INSERT INTO `role_permissions` VALUES ('199', '4', '41', '2017-03-23 18:39:42');
INSERT INTO `role_permissions` VALUES ('200', '4', '48', '2017-03-23 18:39:42');
INSERT INTO `role_permissions` VALUES ('201', '4', '49', '2017-03-23 18:39:42');
INSERT INTO `role_permissions` VALUES ('202', '4', '51', '2017-03-23 18:39:54');
INSERT INTO `role_permissions` VALUES ('203', '4', '43', '2017-03-23 18:40:00');
INSERT INTO `role_permissions` VALUES ('204', '4', '56', '2017-03-23 18:40:00');
INSERT INTO `role_permissions` VALUES ('205', '4', '57', '2017-03-23 18:40:00');
INSERT INTO `role_permissions` VALUES ('206', '5', '40', '2017-03-23 18:41:00');
INSERT INTO `role_permissions` VALUES ('207', '5', '46', '2017-03-23 18:41:00');
INSERT INTO `role_permissions` VALUES ('208', '5', '47', '2017-03-23 18:41:00');
INSERT INTO `role_permissions` VALUES ('209', '5', '41', '2017-03-23 18:41:05');
INSERT INTO `role_permissions` VALUES ('210', '5', '48', '2017-03-23 18:41:05');
INSERT INTO `role_permissions` VALUES ('211', '5', '49', '2017-03-23 18:41:05');
INSERT INTO `role_permissions` VALUES ('212', '5', '54', '2017-03-23 18:41:16');
INSERT INTO `role_permissions` VALUES ('213', '5', '43', '2017-03-23 18:41:24');
INSERT INTO `role_permissions` VALUES ('214', '5', '56', '2017-03-23 18:41:24');
INSERT INTO `role_permissions` VALUES ('215', '5', '57', '2017-03-23 18:41:24');
INSERT INTO `role_permissions` VALUES ('216', '6', '40', '2017-03-23 18:41:42');
INSERT INTO `role_permissions` VALUES ('217', '6', '46', '2017-03-23 18:41:42');
INSERT INTO `role_permissions` VALUES ('218', '6', '47', '2017-03-23 18:41:42');
INSERT INTO `role_permissions` VALUES ('219', '6', '41', '2017-03-23 18:41:48');
INSERT INTO `role_permissions` VALUES ('220', '6', '48', '2017-03-23 18:41:48');
INSERT INTO `role_permissions` VALUES ('221', '6', '49', '2017-03-23 18:41:48');
INSERT INTO `role_permissions` VALUES ('222', '6', '52', '2017-03-23 18:42:22');
INSERT INTO `role_permissions` VALUES ('223', '6', '43', '2017-03-23 18:42:27');
INSERT INTO `role_permissions` VALUES ('224', '6', '56', '2017-03-23 18:42:27');
INSERT INTO `role_permissions` VALUES ('225', '6', '57', '2017-03-23 18:42:27');
INSERT INTO `role_permissions` VALUES ('226', '7', '40', '2017-03-23 18:42:38');
INSERT INTO `role_permissions` VALUES ('227', '7', '46', '2017-03-23 18:42:38');
INSERT INTO `role_permissions` VALUES ('228', '7', '47', '2017-03-23 18:42:38');
INSERT INTO `role_permissions` VALUES ('229', '7', '41', '2017-03-23 18:42:46');
INSERT INTO `role_permissions` VALUES ('230', '7', '48', '2017-03-23 18:42:46');
INSERT INTO `role_permissions` VALUES ('231', '7', '49', '2017-03-23 18:42:46');
INSERT INTO `role_permissions` VALUES ('232', '7', '53', '2017-03-23 18:43:03');
INSERT INTO `role_permissions` VALUES ('233', '7', '43', '2017-03-23 18:43:09');
INSERT INTO `role_permissions` VALUES ('234', '7', '56', '2017-03-23 18:43:09');
INSERT INTO `role_permissions` VALUES ('235', '7', '57', '2017-03-23 18:43:09');
INSERT INTO `role_permissions` VALUES ('236', '2', '42', '2017-03-23 18:46:07');
INSERT INTO `role_permissions` VALUES ('242', '3', '42', '2017-03-23 18:48:20');
INSERT INTO `role_permissions` VALUES ('248', '4', '42', '2017-03-23 18:50:38');
INSERT INTO `role_permissions` VALUES ('254', '5', '42', '2017-03-23 18:52:03');
INSERT INTO `role_permissions` VALUES ('260', '6', '42', '2017-03-23 18:52:49');
INSERT INTO `role_permissions` VALUES ('266', '7', '42', '2017-03-23 18:53:59');
INSERT INTO `role_permissions` VALUES ('272', '2', '11', '2017-03-23 19:33:08');
INSERT INTO `role_permissions` VALUES ('273', '2', '1', '2017-03-23 19:33:08');
INSERT INTO `role_permissions` VALUES ('275', '2', '3', '2017-03-23 19:33:08');
INSERT INTO `role_permissions` VALUES ('276', '2', '4', '2017-03-23 19:33:08');
INSERT INTO `role_permissions` VALUES ('277', '2', '5', '2017-03-23 19:33:09');
INSERT INTO `role_permissions` VALUES ('278', '2', '6', '2017-03-23 19:33:09');
INSERT INTO `role_permissions` VALUES ('279', '2', '7', '2017-03-23 19:33:09');
INSERT INTO `role_permissions` VALUES ('282', '1', '8', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('283', '1', '9', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('284', '1', '17', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('285', '1', '18', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('286', '1', '10', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('287', '1', '14', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('288', '1', '12', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('289', '1', '13', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('290', '1', '44', '2017-03-23 20:17:01');
INSERT INTO `role_permissions` VALUES ('291', '1', '64', '2017-03-23 23:11:28');
INSERT INTO `role_permissions` VALUES ('292', '1', '65', '2017-03-23 23:12:23');
INSERT INTO `role_permissions` VALUES ('293', '1', '66', '2017-03-23 23:12:44');
INSERT INTO `role_permissions` VALUES ('305', '1', '19', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('306', '1', '20', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('307', '1', '28', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('308', '1', '29', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('309', '1', '30', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('310', '1', '33', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('311', '1', '34', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('312', '1', '35', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('313', '1', '31', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('314', '1', '32', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('315', '1', '36', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('316', '1', '70', '2017-03-28 14:47:42');
INSERT INTO `role_permissions` VALUES ('317', '2', '19', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('318', '2', '20', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('319', '2', '28', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('320', '2', '29', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('321', '2', '30', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('322', '2', '33', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('323', '2', '34', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('324', '2', '35', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('325', '2', '31', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('326', '2', '32', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('327', '2', '36', '2017-03-28 14:49:52');
INSERT INTO `role_permissions` VALUES ('328', '2', '70', '2017-03-28 14:49:52');

-- ----------------------------
-- Table structure for soes
-- ----------------------------
DROP TABLE IF EXISTS `soes`;
CREATE TABLE `soes` (
  `SOE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOTAL_NUMBER` int(11) DEFAULT NULL COMMENT '人员总数',
  `DEVELOPER_NUMBER` int(11) DEFAULT NULL COMMENT '研发人员数',
  `JUNIOR_NUMBER` int(11) DEFAULT NULL COMMENT '初级人员数',
  `INTERMEDIATE_NUNBER` int(11) DEFAULT NULL COMMENT '中级人员数',
  `SENIOR_NUMBER` int(11) DEFAULT NULL COMMENT '高级人员数',
  `JUNIOR_COLLEGE_NUMBER` int(11) DEFAULT NULL COMMENT '大专学历人数',
  `UNDERGRADUATE_NUMBER` int(11) DEFAULT NULL COMMENT '本科学历人数',
  `MASTER_NUMBER` int(11) DEFAULT NULL COMMENT '硕士学历人数',
  `DOCTOR_NUMBER` int(11) DEFAULT NULL COMMENT '博士学历人数',
  `OVERSEAS_NUMBER` int(11) DEFAULT NULL COMMENT '海归人数',
  `ACADEMICIAN_NUNBER` int(11) DEFAULT NULL COMMENT '院士人数',
  PRIMARY KEY (`SOE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of soes
-- ----------------------------
INSERT INTO `soes` VALUES ('1', '20000', '2', '50', '25', '2', '1', '50', '25', '15', '10', '5');
INSERT INTO `soes` VALUES ('4', '654', '65', '64', '12', '65', '5', '65', '6', '45', '54', '5');
INSERT INTO `soes` VALUES ('5', '253', '25', '45', '55', '564', '645', '654', '654', '66', '5', '4');
INSERT INTO `soes` VALUES ('6', '5465', '654', '654', '654', '654', '5', '54', '56', '65', '54', '6');

-- ----------------------------
-- Table structure for system_logs
-- ----------------------------
DROP TABLE IF EXISTS `system_logs`;
CREATE TABLE `system_logs` (
  `SYSTEM_LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYSTEM_LOG_TITLE` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `SYSTEM_LOG_CONTENT` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `SUBMIT_TIME` datetime DEFAULT NULL COMMENT '事件发生时间',
  `LOG_TYPE` int(11) DEFAULT NULL COMMENT '系统日志类型',
  PRIMARY KEY (`SYSTEM_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_logs
-- ----------------------------
INSERT INTO `system_logs` VALUES ('1', '审核用户信息日志', '【admin】在2017-03-26 04:05:57【删除】【用户TTTT】', '2017-03-26 04:05:57', '1');
INSERT INTO `system_logs` VALUES ('2', '菜单日志', '【admin】在2017-03-28 13:10:37【添加】新权限【audit:produce_audit_managment】', '2017-03-28 13:10:38', '5');
INSERT INTO `system_logs` VALUES ('3', '菜单日志', '【admin】在2017-03-28 13:11:19【更新】了【权限70】', '2017-03-28 13:11:19', '5');
INSERT INTO `system_logs` VALUES ('4', '菜单日志', '【admin】在2017-03-28 13:16:27【添加】新菜单【产品成果审核管理】', '2017-03-28 13:16:28', '5');
INSERT INTO `system_logs` VALUES ('5', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限50】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('6', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限51】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('7', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限52】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('8', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限53】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('9', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限54】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('10', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限55】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('11', '菜单日志', '【admin】在2017-03-28 14:46:50【绑定】了【权限55】和【角色1】', '2017-03-28 14:46:50', '5');
INSERT INTO `system_logs` VALUES ('12', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限50】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('13', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色50】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('14', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限51】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('15', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色51】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('16', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限52】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('17', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色52】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('18', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限53】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('19', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色53】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('20', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限54】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('21', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色54】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('22', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限55】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('23', '菜单日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色55】的【绑定】', '2017-03-28 14:47:12', '5');
INSERT INTO `system_logs` VALUES ('24', '权限日志', '【admin】在2017-03-28 14:47:12【解除】【权限42】和【角色1】的【绑定】', '2017-03-28 14:47:12', '4');
INSERT INTO `system_logs` VALUES ('25', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限20】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('26', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色20】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('27', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限29】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('28', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色29】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('29', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限30】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('30', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色30】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('31', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限33】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('32', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色33】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('33', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限34】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('34', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色34】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('35', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限35】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('36', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色35】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('37', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限28】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('38', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色28】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('39', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限31】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('40', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色31】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('41', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限32】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('42', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色32】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('43', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限36】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('44', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色36】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('45', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限70】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('46', '菜单日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色70】的【绑定】', '2017-03-28 14:47:29', '5');
INSERT INTO `system_logs` VALUES ('47', '权限日志', '【admin】在2017-03-28 14:47:28【解除】【权限19】和【角色1】的【绑定】', '2017-03-28 14:47:29', '4');
INSERT INTO `system_logs` VALUES ('48', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限20】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('49', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限29】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('50', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限30】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('51', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限33】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('52', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限34】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('53', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限35】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('54', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限35】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('55', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限31】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('56', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限32】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('57', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限36】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('58', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限70】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('59', '菜单日志', '【admin】在2017-03-28 14:47:42【绑定】了【权限70】和【角色1】', '2017-03-28 14:47:42', '5');
INSERT INTO `system_logs` VALUES ('60', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限20】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('61', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色20】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('62', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限29】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('63', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色29】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('64', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限30】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('65', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色30】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('66', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限33】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('67', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色33】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('68', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限34】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('69', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色34】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('70', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限35】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('71', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色35】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('72', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限28】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('73', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色28】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('74', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限31】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('75', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色31】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('76', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限32】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('77', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色32】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('78', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限36】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('79', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色36】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('80', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限70】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('81', '菜单日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色70】的【绑定】', '2017-03-28 14:49:46', '5');
INSERT INTO `system_logs` VALUES ('82', '权限日志', '【admin】在2017-03-28 14:49:46【解除】【权限19】和【角色2】的【绑定】', '2017-03-28 14:49:46', '4');
INSERT INTO `system_logs` VALUES ('83', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限20】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('84', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限29】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('85', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限30】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('86', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限33】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('87', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限34】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('88', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限35】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('89', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限35】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('90', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限31】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('91', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限32】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('92', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限36】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('93', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限70】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('94', '菜单日志', '【admin】在2017-03-28 14:49:51【绑定】了【权限70】和【角色2】', '2017-03-28 14:49:52', '5');
INSERT INTO `system_logs` VALUES ('95', '审核用户信息日志', '【admin】在2017-03-30 10:59:32【审核】了【admin】', '2017-03-30 10:59:32', '1');
INSERT INTO `system_logs` VALUES ('96', '审核用户信息日志', '【admin】在2017-03-30 11:00:12【审核】了【admin】', '2017-03-30 11:00:13', '1');
INSERT INTO `system_logs` VALUES ('97', '审核用户信息日志', '【admin】在2017-03-30 11:00:45【审核】了【admin】', '2017-03-30 11:00:46', '1');
INSERT INTO `system_logs` VALUES ('98', '审核用户信息日志', '【admin】在2017-03-30 11:07:09【审核】了【admin】', '2017-03-30 11:07:10', '1');
INSERT INTO `system_logs` VALUES ('99', '审核用户信息日志', '【admin】在2017-03-30 12:26:46【审核】了【admin】', '2017-03-30 12:26:46', '1');
INSERT INTO `system_logs` VALUES ('100', '审核用户信息日志', '【admin】在2017-03-30 17:14:40【审核】了【admin】', '2017-03-30 17:14:41', '1');

-- ----------------------------
-- Table structure for technology_base_infos
-- ----------------------------
DROP TABLE IF EXISTS `technology_base_infos`;
CREATE TABLE `technology_base_infos` (
  `TBI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INFO_TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `INFO_KEY` varchar(255) DEFAULT NULL COMMENT '关键字',
  `INDUSTRY_ID` int(11) DEFAULT NULL COMMENT '所属行业',
  `HTF_ID` int(11) DEFAULT NULL COMMENT '高新技术领域',
  `LOCATION_ID` int(11) DEFAULT NULL COMMENT '所在地',
  `SCI_ID` int(11) DEFAULT NULL COMMENT '学科分类',
  `SE_INDUSTRY_ID` int(11) DEFAULT NULL COMMENT '战略新兴产业',
  `FIRST_FINISH_UNIT` varchar(255) DEFAULT NULL COMMENT '第一完成单位',
  `TECHNOLOGY_MATURITY_ID` int(11) DEFAULT NULL COMMENT '技术成熟度',
  `SOURCE_TYPE_ID` int(11) DEFAULT NULL COMMENT '来源类型',
  `COOPERATION_MODE_ID` int(11) DEFAULT NULL COMMENT '合作方式',
  `IMAGE_ID` int(11) DEFAULT NULL COMMENT '图片附件',
  `VIDEO_ID` int(11) DEFAULT NULL COMMENT '视频附件',
  `TEXT_ID` int(11) DEFAULT NULL COMMENT '文本附件',
  `CONTACT_INFO_ID` int(11) DEFAULT NULL COMMENT '联系信息',
  `STATUS` int(11) DEFAULT NULL COMMENT '信息状态',
  `AUDIT_INFO_ID` int(11) DEFAULT NULL COMMENT '审核信息',
  `CREATE_TIME` datetime DEFAULT NULL,
  `PUBLISHER_ID` varchar(255) DEFAULT NULL,
  `LIMIT_STATUS` int(11) DEFAULT '1' COMMENT '禁用状态',
  PRIMARY KEY (`TBI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of technology_base_infos
-- ----------------------------
INSERT INTO `technology_base_infos` VALUES ('47', null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '64', null, '68', '2017-03-31 04:31:06', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('48', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '65', null, '69', '2017-03-31 04:31:07', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('49', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '75', null, '79', '2017-03-31 04:31:09', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('50', null, 'tes', null, null, null, null, null, null, null, null, null, null, null, null, '81', null, '85', '2017-03-01 04:55:56', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('51', null, '12', null, null, null, null, null, null, null, null, null, null, null, null, '82', null, '86', '2017-03-04 04:55:58', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('52', 'afsdf', '234', null, null, null, null, null, null, null, null, null, null, null, null, '83', null, '87', '2017-03-07 04:56:27', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('53', '标题', '23ger', null, null, null, null, null, null, null, null, null, null, null, null, '84', null, '88', '2017-03-11 04:56:36', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('54', '标题', '23te', null, null, null, null, null, null, null, null, null, null, null, null, '85', null, '89', '2017-03-17 04:56:41', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('55', null, '2fe', null, null, null, null, null, null, null, null, null, null, null, null, '86', null, '90', '2017-03-22 04:56:08', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('56', null, '343t', null, null, null, null, null, null, null, null, null, null, null, null, '87', null, '91', '2017-03-26 04:56:06', 'admin', '1');
INSERT INTO `technology_base_infos` VALUES ('57', null, '测试', null, null, null, null, null, null, null, null, null, null, null, null, '88', null, '92', '2017-03-27 04:56:19', 'admin', '1');

-- ----------------------------
-- Table structure for technology_requirement_infos
-- ----------------------------
DROP TABLE IF EXISTS `technology_requirement_infos`;
CREATE TABLE `technology_requirement_infos` (
  `TRI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INVESTMENT_FUNDS` float DEFAULT NULL COMMENT '欲投资资金',
  `TIME_LIMIT` int(11) DEFAULT '0' COMMENT '解决时限(天)',
  `PROBLEM_DESCRIPTION` text COMMENT '问题描述',
  `TBI_ID` int(11) DEFAULT NULL COMMENT '技术基本信息id',
  PRIMARY KEY (`TRI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of technology_requirement_infos
-- ----------------------------
INSERT INTO `technology_requirement_infos` VALUES ('1', null, '2147483647', '问题描述', '50');
INSERT INTO `technology_requirement_infos` VALUES ('2', null, '2147483647', '问题描述', '51');
INSERT INTO `technology_requirement_infos` VALUES ('3', null, '2147483647', '问题描述', '52');
INSERT INTO `technology_requirement_infos` VALUES ('4', null, '2147483647', '测试', '53');
INSERT INTO `technology_requirement_infos` VALUES ('5', null, null, null, '54');
INSERT INTO `technology_requirement_infos` VALUES ('6', null, '2147483647', '问题描', '55');
INSERT INTO `technology_requirement_infos` VALUES ('7', null, null, null, '56');
INSERT INTO `technology_requirement_infos` VALUES ('8', null, '2147483647', '测试', '57');

-- ----------------------------
-- Table structure for technology_supply_infos
-- ----------------------------
DROP TABLE IF EXISTS `technology_supply_infos`;
CREATE TABLE `technology_supply_infos` (
  `TSI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INTELLECTUAL_TYPE_ID` int(11) DEFAULT NULL COMMENT '知识产权id',
  `INTELLECTUAL_CODE` varchar(64) DEFAULT NULL COMMENT '知识产权编号',
  `RESULT_FORM_ID` int(11) DEFAULT NULL COMMENT '成果体现形式',
  `RESULT_PROPERTY_ID` int(11) DEFAULT NULL COMMENT '成果属性id',
  `RESULT_STAGE_ID` int(11) DEFAULT NULL COMMENT '成果阶段',
  `RESULT_LEVEL_ID` int(11) DEFAULT NULL COMMENT '成果水平',
  `RESERACH_FORM_ID` int(11) DEFAULT NULL COMMENT '研究水平',
  `TOPIC_SOURCE_ID` int(11) DEFAULT NULL COMMENT '课题来源',
  `FIRST_UNIT` varchar(64) DEFAULT NULL COMMENT '第一完成单位',
  `TECHNOLOGY_MATURITY_ID` int(11) DEFAULT NULL COMMENT '技术成熟度',
  `SOURCE_TYPE_ID` int(11) DEFAULT NULL COMMENT '交易价格',
  `PRICE` float(10,2) DEFAULT NULL COMMENT '交易价格(万元）',
  `RESULT_INTRODUCTION` text COMMENT '成果简介',
  `APPLICATION_RANGE` varchar(255) DEFAULT NULL COMMENT '应用范围',
  `PROSPECT_ANALYSIS` varchar(255) DEFAULT NULL COMMENT '前景分析',
  `TBI_ID` int(11) DEFAULT NULL COMMENT '基本信息',
  PRIMARY KEY (`TSI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of technology_supply_infos
-- ----------------------------
INSERT INTO `technology_supply_infos` VALUES ('26', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '47');
INSERT INTO `technology_supply_infos` VALUES ('27', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '48');
INSERT INTO `technology_supply_infos` VALUES ('28', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '49');

-- ----------------------------
-- Table structure for tech_enclosures
-- ----------------------------
DROP TABLE IF EXISTS `tech_enclosures`;
CREATE TABLE `tech_enclosures` (
  `ENCLOSURE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `ENCLOSURE_URL` varchar(255) DEFAULT NULL COMMENT '附件路径',
  `ENCLOSURE_DESC` varchar(255) DEFAULT NULL COMMENT '附件描述',
  `ENCLOSURE_TYPE` int(11) DEFAULT NULL COMMENT '附件类型',
  `BIND_ID` int(11) DEFAULT NULL COMMENT '绑定的技术供给信息id',
  PRIMARY KEY (`ENCLOSURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tech_enclosures
-- ----------------------------

-- ----------------------------
-- Table structure for tech_require_visitors
-- ----------------------------
DROP TABLE IF EXISTS `tech_require_visitors`;
CREATE TABLE `tech_require_visitors` (
  `VISITOR_IP` varchar(255) NOT NULL,
  `VIEW_DATE` datetime DEFAULT NULL,
  `TECH_REQUIRE_ID` int(11) DEFAULT NULL COMMENT '技术需求id',
  `VIEW_TIMES` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`VISITOR_IP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tech_require_visitors
-- ----------------------------

-- ----------------------------
-- Table structure for tech_supply_visitors
-- ----------------------------
DROP TABLE IF EXISTS `tech_supply_visitors`;
CREATE TABLE `tech_supply_visitors` (
  `VISITOR_IP` varchar(255) NOT NULL COMMENT '访问者ip',
  `VIEW_DATE` datetime DEFAULT NULL COMMENT '浏览时间',
  `TECH_SUPPLY_ID` int(11) DEFAULT NULL COMMENT '浏览技术供给id',
  `VIEW_TIMES` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`VISITOR_IP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tech_supply_visitors
-- ----------------------------

-- ----------------------------
-- Table structure for unit_base_infos
-- ----------------------------
DROP TABLE IF EXISTS `unit_base_infos`;
CREATE TABLE `unit_base_infos` (
  `UNIT_BASEINFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UNIT_NAME` varchar(64) DEFAULT NULL,
  `UNIT_ABBREVIATION` varchar(64) DEFAULT NULL,
  `ESTABLISHMENT_DATE` datetime DEFAULT NULL,
  `LEGAL_REPRESENTATIVE` varchar(32) DEFAULT NULL,
  `UNIT_ADDRESS` varchar(128) DEFAULT NULL,
  `lOCATION_ID` int(11) DEFAULT NULL,
  `INFO_KEY` varchar(128) DEFAULT NULL,
  `UNIT_CODE_TYPE` int(11) DEFAULT NULL,
  `UNIT_CODE` varchar(64) DEFAULT NULL,
  `UNIT_CODE_IMAGE_URL` varchar(255) DEFAULT NULL COMMENT '机构代码图片路径',
  `UNIT_PROFILE` varchar(255) DEFAULT NULL,
  `BUSINESS_FIELD` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CONTACT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UNIT_BASEINFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unit_base_infos
-- ----------------------------
INSERT INTO `unit_base_infos` VALUES ('1', '嘉应学院', '嘉大', '2017-03-28 00:25:36', 'company', null, '112', '教育', '1', '550', 'uploadFiles/unitCodeImages/company/021cc4d0130a11e73daf8e9315c1f8ef.jpg', '公办大学', '教育', '2A院校', '30');
INSERT INTO `unit_base_infos` VALUES ('4', '中介机构', '中介', '2017-03-28 00:00:00', 'agency', '广东省梅州市', '112', '软件', '1', '5555', 'uploadFiles/unitCodeImages/agency/22f98cb0130a11e73daf8e9315c1f8ef.jpg', '机构简介', null, '备注', '34');
INSERT INTO `unit_base_infos` VALUES ('5', '嘉应学院', '嘉大', '2017-03-27 22:31:42', 'college', '广东省梅州市', '112', '教育', '1', '12530', 'uploadFiles/unitCodeImages/college/1e0ecd6012fa11e771d0544cc00aa0d3.jpg', '国家2A院校', '教育', '备注', '35');
INSERT INTO `unit_base_infos` VALUES ('6', '科研单位', '嘉大科研', '2017-03-28 00:27:36', 'scientify', '广东省梅州市', '112', '教育', '1', '2536', 'uploadFiles/unitCodeImages/scientify/49e27cb0130a11e73daf8e9315c1f8ef.jpg', '2A院校', '教育', '备注', '36');

-- ----------------------------
-- Table structure for unit_infos
-- ----------------------------
DROP TABLE IF EXISTS `unit_infos`;
CREATE TABLE `unit_infos` (
  `UNIT_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SOE_ID` int(11) DEFAULT NULL COMMENT '单位从业人员情况Id',
  `BASE_INFO_ID` int(11) DEFAULT NULL COMMENT '基本信息Id',
  `UNIT_TYPE` int(11) DEFAULT NULL COMMENT '单位类型',
  PRIMARY KEY (`UNIT_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unit_infos
-- ----------------------------
INSERT INTO `unit_infos` VALUES ('2', '5', '5', '1');
INSERT INTO `unit_infos` VALUES ('3', '6', '6', '2');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `USER_ID` varchar(255) NOT NULL,
  `USER_NAME` varchar(64) DEFAULT NULL,
  `PASSWORD` varchar(64) DEFAULT NULL,
  `USER_TYPE` int(11) DEFAULT NULL,
  `PERSON_ID` int(11) DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `UNIVERSITY_ID` int(11) DEFAULT NULL,
  `SRU_ID` int(11) DEFAULT NULL,
  `AGENCY_ID` int(11) DEFAULT NULL,
  `ADMINISTRATOR_ID` int(11) DEFAULT NULL,
  `SUPER_ADMINISTRATOR_ID` int(11) DEFAULT NULL,
  `REGISTER_TIME` datetime NOT NULL,
  `PRElOGIN_TIME` datetime DEFAULT NULL,
  `CURRENTLOGIN_TIME` datetime DEFAULT NULL,
  `PREUPDATE_TIME` datetime NOT NULL,
  `AUDIT_ID` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '-1',
  `CONTACT_INFO_ID` int(11) DEFAULT NULL COMMENT '联系人信息ID',
  PRIMARY KEY (`USER_ID`),
  KEY `FK_orut4wh9g3oumhog22ss46epu` (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', 'admin', '8758E71EF4926A2AB873D015CFEEBE38', '7', null, null, null, null, null, null, '8', '2017-03-26 03:51:55', '2017-03-31 11:23:24', '2017-03-31 11:29:20', '2017-03-26 03:51:55', '27', '1', '27');
INSERT INTO `users` VALUES ('agency', 'agency', '7D86E3873D961A958F2DD38A6504AA96', '3', null, null, null, null, '2', null, null, '2017-03-26 03:52:56', '2017-03-30 13:03:47', '2017-03-30 21:00:39', '2017-03-26 03:52:56', '34', '1', '34');
INSERT INTO `users` VALUES ('college', 'college', '6EFC8CD0351FFCE8C5A3EF53A0E4743A', '4', null, null, '2', null, null, null, null, '2017-03-26 03:53:44', '2017-03-30 13:06:52', '2017-03-30 21:03:19', '2017-03-26 03:53:44', '35', '1', '35');
INSERT INTO `users` VALUES ('company', 'company', 'A02951E3DAE9931D6D55AF80B1BF7DEF', '2', null, '1', null, null, null, null, null, '2017-03-26 03:54:41', '2017-03-30 20:52:15', '2017-03-30 20:59:13', '2017-03-26 03:54:41', '30', '1', '30');
INSERT INTO `users` VALUES ('person', 'person', 'AA17C57E4700E68098C58B60E73CC9B8', '1', '3', null, null, null, null, null, null, '2017-03-26 03:55:15', '2017-03-30 20:37:19', '2017-03-30 20:55:08', '2017-03-26 03:55:15', '31', '1', '31');
INSERT INTO `users` VALUES ('platform', 'platform', 'A50CE024DFEFE9FE8F366435BC6AB103', '6', null, null, null, null, null, '9', null, '2017-03-26 03:56:00', '2017-03-26 03:56:00', '2017-03-30 12:53:27', '2017-03-26 03:56:00', '37', '1', '37');
INSERT INTO `users` VALUES ('scientify', 'scientify', '722177373BB154F8A0A6E1BFA6086B8E', '5', null, null, null, '3', null, null, null, '2017-03-26 03:56:12', '2017-03-26 03:56:12', '2017-03-30 21:03:49', '2017-03-26 03:56:12', '36', '1', '36');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `USER_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `USER_ID` varchar(64) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_6oxkx373ix67kf0lg3iyvewqd` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('9', '1', 'admin', '2017-03-17 12:35:29');
INSERT INTO `user_roles` VALUES ('12', '4', 'company', '2017-03-23 18:57:04');
INSERT INTO `user_roles` VALUES ('13', '3', 'person', '2017-03-23 19:05:06');
INSERT INTO `user_roles` VALUES ('16', '5', 'agency', '2017-03-23 19:18:39');
INSERT INTO `user_roles` VALUES ('17', '6', 'college', '2017-03-23 19:21:26');
INSERT INTO `user_roles` VALUES ('18', '7', 'scientify', '2017-03-23 19:22:29');
INSERT INTO `user_roles` VALUES ('19', '2', 'platform', '2017-03-23 19:30:11');

-- ----------------------------
-- Table structure for user_visitors
-- ----------------------------
DROP TABLE IF EXISTS `user_visitors`;
CREATE TABLE `user_visitors` (
  `VISITOR_IP` varchar(255) NOT NULL COMMENT '浏览者id',
  `VIEW_DATE` datetime DEFAULT NULL COMMENT '浏览时间',
  `USER_ID` int(11) DEFAULT NULL COMMENT '被浏览用户id',
  `VIEW_TIMES` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`VISITOR_IP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_visitors
-- ----------------------------
