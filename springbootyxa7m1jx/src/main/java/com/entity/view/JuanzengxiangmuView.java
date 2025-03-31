package com.entity.view;

import com.entity.JuanzengxiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 捐赠项目
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("juanzengxiangmu")
public class JuanzengxiangmuView  extends JuanzengxiangmuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JuanzengxiangmuView(){
	}
 
 	public JuanzengxiangmuView(JuanzengxiangmuEntity juanzengxiangmuEntity){
 	try {
			BeanUtils.copyProperties(this, juanzengxiangmuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
