package com.entity.view;

import com.entity.JuanzengzheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 捐赠者
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("juanzengzhe")
public class JuanzengzheView  extends JuanzengzheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JuanzengzheView(){
	}
 
 	public JuanzengzheView(JuanzengzheEntity juanzengzheEntity){
 	try {
			BeanUtils.copyProperties(this, juanzengzheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
