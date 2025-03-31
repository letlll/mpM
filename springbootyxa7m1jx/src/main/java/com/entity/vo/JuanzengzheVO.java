package com.entity.vo;

import com.entity.JuanzengzheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 捐赠者
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public class JuanzengzheVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 捐赠姓名
	 */
	
	private String juanzengxingming;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 年龄
	 */
	
	private Integer nianling;
		
	/**
	 * 捐赠手机
	 */
	
	private String juanzengshouji;
		
	/**
	 * 头像
	 */
	
	private String touxiang;
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：捐赠姓名
	 */
	 
	public void setJuanzengxingming(String juanzengxingming) {
		this.juanzengxingming = juanzengxingming;
	}
	
	/**
	 * 获取：捐赠姓名
	 */
	public String getJuanzengxingming() {
		return juanzengxingming;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：年龄
	 */
	 
	public void setNianling(Integer nianling) {
		this.nianling = nianling;
	}
	
	/**
	 * 获取：年龄
	 */
	public Integer getNianling() {
		return nianling;
	}
				
	
	/**
	 * 设置：捐赠手机
	 */
	 
	public void setJuanzengshouji(String juanzengshouji) {
		this.juanzengshouji = juanzengshouji;
	}
	
	/**
	 * 获取：捐赠手机
	 */
	public String getJuanzengshouji() {
		return juanzengshouji;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
			
}
