package com.entity.view;

import com.entity.JuanzengjiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 捐赠记录
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("juanzengjilu")
public class JuanzengjiluView  extends JuanzengjiluEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JuanzengjiluView(){
	}
 
 	public JuanzengjiluView(JuanzengjiluEntity juanzengjiluEntity){
 	try {
			BeanUtils.copyProperties(this, juanzengjiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
