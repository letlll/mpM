-- 更新huodongbaoming表，添加签到状态字段
USE springbootyxa7m1jx;
ALTER TABLE huodongbaoming ADD COLUMN IF NOT EXISTS qiandaozhuangtai VARCHAR(50) DEFAULT '未签到' COMMENT '签到状态';