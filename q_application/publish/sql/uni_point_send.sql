CREATE TABLE uni_point_send (
  send_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  plat_code varchar(20) binary NOT NULL,
  customer_no varchar(64) binary NOT NULL COMMENT '积分发放/扣减的用户ID',
  card_type varchar(16) binary DEFAULT NULL COMMENT '卡类型',
  point_type int(10) DEFAULT NULL COMMENT '积分类型 1、积分 2、微点   考虑与忠诚度产品的保持一致',
  type varchar(30) binary DEFAULT NULL COMMENT '类型1、手动调整 2、积分兑换 3、活动发放 4、订单消费送积分  5、订单退单退积分 6、开卡赠送',
  busi_id varchar(60) binary DEFAULT NULL COMMENT '流水号',
  point int(11) NOT NULL COMMENT '正的为加积分,负的为减积分',
  remark varchar(200) binary DEFAULT NULL COMMENT '备注',
  schedule_time timestamp NULL DEFAULT NULL COMMENT '积分计划发放时间 如果为空则取插入时间',
  status int(4) NOT NULL DEFAULT 0 COMMENT '0初始化  1发放中  2 发放完成 9发放失败',
  send_time timestamp NULL DEFAULT NULL COMMENT '发放时间',
  error_msg text binary DEFAULT NULL COMMENT '发放失败的信息描述',
  batch_id int(10) DEFAULT NULL COMMENT '批次号',
  insert_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (send_id),
  INDEX IDX_uni_point_send_busi_id (busi_id, status),
  INDEX IDX_uni_point_send_status (status)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_bin
COMMENT = '积分发放/扣减的中间表';