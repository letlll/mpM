CREATE TABLE `wupin_juanzeng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wupinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物品名称',
  `wupinleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物品类型',
  `shuliang` int(11) DEFAULT NULL COMMENT '物品数量',
  `juanzengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '捐赠账号',
  `juanzengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '捐赠姓名',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `zhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '状态',
  `juanzengshijian` datetime DEFAULT NULL COMMENT '捐赠时间',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物品捐赠';