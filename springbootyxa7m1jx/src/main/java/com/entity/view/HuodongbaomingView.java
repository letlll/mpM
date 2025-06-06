package com.entity.view;

import com.entity.HuodongbaomingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 活动报名
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("huodongbaoming")
public class HuodongbaomingView  extends HuodongbaomingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public HuodongbaomingView(){
	}
 
 	public HuodongbaomingView(HuodongbaomingEntity huodongbaomingEntity){
 	try {
			BeanUtils.copyProperties(this, huodongbaomingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
