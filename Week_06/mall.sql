
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) DEFAULT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `type` tinyint(1) DEFAULT NULL COMMENT '账号类型 0-管理账号 1-商家账号',
  `bussiness_id` bigint(20) DEFAULT NULL COMMENT '关联企业id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, '2020-11-24 22:08:12');
INSERT INTO `admin` VALUES (2, 'merchant', 'e10adc3949ba59abbe56e057f20f883e', 1, 1, '2020-11-25 22:09:06');
COMMIT;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业信息';

-- ----------------------------
-- Records of business
-- ----------------------------
BEGIN;
INSERT INTO `business` VALUES (1, '仌仌电商');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_sn` varchar(64) NOT NULL COMMENT '订单号',
  `pay_type` tinyint(1) DEFAULT NULL COMMENT '支付平台 0-微信 1-支付宝',
  `payment_order_sn` varchar(255) DEFAULT NULL COMMENT '支付订单号',
  `payment_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(255) DEFAULT NULL COMMENT '订单状态 0-未支付 1-已支付 2-已发货 3-已签收 4-已完成 5-失效',
  `remark` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '物流订单',
  `receiver_name` varchar(64) DEFAULT NULL COMMENT '收货人名称',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(20) DEFAULT NULL COMMENT '地区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `confirm_status` tinyint(1) DEFAULT NULL COMMENT '确认收货状态 0-未确认 1-已确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (1, 1, '202000991', 1, '2222333', '2020-11-25 22:23:05', '2020-11-25 22:23:07', 1, NULL, NULL, '诺', '133333333', '北京', '北京', '北京', '北京路', 0);
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_price` bigint(10) DEFAULT NULL COMMENT '商品价格',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `product_pic` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `sotre_name` varchar(64) DEFAULT NULL COMMENT '店铺名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单商品信息';

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (1, 1, 1, 100, 1, '牛仔上衣', NULL, 1, '仌仌服装');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '产品类别id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `name` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `sn` varchar(64) DEFAULT NULL COMMENT '产品编码',
  `verify_status` tinyint(1) DEFAULT NULL COMMENT '审核状态 0-未审核 1-审核通过 2-审核不通过',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `util` varchar(10) DEFAULT NULL COMMENT '单位',
  `title` varchar(64) DEFAULT NULL COMMENT '产品标题',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `html` text COMMENT '富文本信息',
  `publish_status` tinyint(1) DEFAULT NULL COMMENT '上架状态 0-下架 1-上架',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, 1, NULL, '牛仔上衣', 'sn33', 1, 100.00, '件', '牛仔上衣', NULL, NULL, 1, '2020-11-25 22:20:25', NULL);
COMMIT;

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute`;
CREATE TABLE `product_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '属性参数 逗号分割',
  `product_attribute_category_id` int(11) DEFAULT NULL COMMENT '属性分类id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_attribute
-- ----------------------------
BEGIN;
INSERT INTO `product_attribute` VALUES (1, '码数', 's,m,l,xl', 1, 1);
INSERT INTO `product_attribute` VALUES (2, '颜色', '黑,白', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for product_attribute_category
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_category`;
CREATE TABLE `product_attribute_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sotre_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='产品属性分类';

-- ----------------------------
-- Records of product_attribute_category
-- ----------------------------
BEGIN;
INSERT INTO `product_attribute_category` VALUES (1, 1, '衣服');
COMMIT;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级分类id ,0表示一级',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `level` tinyint(1) DEFAULT NULL COMMENT '分类级别',
  `keywords` varchar(64) DEFAULT NULL COMMENT '关键字',
  `described` varchar(64) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类';

-- ----------------------------
-- Records of product_category
-- ----------------------------
BEGIN;
INSERT INTO `product_category` VALUES (1, 0, '服饰', 1, '服饰', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sku_stock
-- ----------------------------
DROP TABLE IF EXISTS `sku_stock`;
CREATE TABLE `sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `sp_date` varchar(200) DEFAULT NULL COMMENT '商品属性 ,json格式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sku_stock
-- ----------------------------
BEGIN;
INSERT INTO `sku_stock` VALUES (1, 1, 100, '{\"码数\":\"l\",\"颜色\":\"白\"}');
COMMIT;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '店铺名称',
  `described` varchar(64) DEFAULT NULL COMMENT '店铺介绍',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `business_id` bigint(20) DEFAULT NULL COMMENT '关联企业id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

-- ----------------------------
-- Records of store
-- ----------------------------
BEGIN;
INSERT INTO `store` VALUES (1, '仌仌服装', '只卖贵的,不卖好的', '2020-11-25 22:16:45', NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(1) DEFAULT NULL COMMENT '0-保密 1-男 2-女',
  `register_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '账号状态 0-正常 1-冻结',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'acj', 'e10adc3949ba59abbe56e057f20f883e', '124', '1333333333', 0, '2020-11-25 22:09:42', 0, '2020-11-25 22:09:45');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
