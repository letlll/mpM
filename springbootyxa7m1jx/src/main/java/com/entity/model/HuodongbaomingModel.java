package com.entity.model;

import com.entity.HuodongbaomingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 活动报名
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public class HuodongbaomingModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 活动类型
	 */
	
	private String huodongleixing;
		
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 开始时间
	 */
	
	private String kaishishijian;
		
	/**
	 * 活动地址
	 */
	
	private String huodongdizhi;
		
	/**
	 * 举办方
	 */
	
	private String jubanfang;
		
	/**
	 * 志愿账号
	 */
	
	private String zhiyuanzhanghao;
		
	/**
	 * 志愿者
	 */
	
	private String zhiyuanzhe;
		
	/**
	 * 志愿者手机
	 */
	
	private String zhiyuanzheshouji;
				
	
	/**
	 * 设置：活动类型
	 */
	 
	public void setHuodongleixing(String huodongleixing) {
		this.huodongleixing = huodongleixing;
	}
	
	/**
	 * 获取：活动类型
	 */
	public String getHuodongleixing() {
		return huodongleixing;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：开始时间
	 */
	 
	public void setKaishishijian(String kaishishijian) {
		this.kaishishijian = kaishishijian;
	}
	
	/**
	 * 获取：开始时间
	 */
	public String getKaishishijian() {
		return kaishishijian;
	}
				
	
	/**
	 * 设置：活动地址
	 */
	 
	public void setHuodongdizhi(String huodongdizhi) {
		this.huodongdizhi = huodongdizhi;
	}
	
	/**
	 * 获取：活动地址
	 */
	public String getHuodongdizhi() {
		return huodongdizhi;
	}
				
	
	/**
	 * 设置：举办方
	 */
	 
	public void setJubanfang(String jubanfang) {
		this.jubanfang = jubanfang;
	}
	
	/**
	 * 获取：举办方
	 */
	public String getJubanfang() {
		return jubanfang;
	}
				
	
	/**
	 * 设置：志愿账号
	 */
	 
	public void setZhiyuanzhanghao(String zhiyuanzhanghao) {
		this.zhiyuanzhanghao = zhiyuanzhanghao;
	}
	
	/**
	 * 获取：志愿账号
	 */
	public String getZhiyuanzhanghao() {
		return zhiyuanzhanghao;
	}
				
	
	/**
	 * 设置：志愿者
	 */
	 
	public void setZhiyuanzhe(String zhiyuanzhe) {
		this.zhiyuanzhe = zhiyuanzhe;
	}
	
	/**
	 * 获取：志愿者
	 */
	public String getZhiyuanzhe() {
		return zhiyuanzhe;
	}
				
	
	/**
	 * 设置：志愿者手机
	 */
	 
	public void setZhiyuanzheshouji(String zhiyuanzheshouji) {
		this.zhiyuanzheshouji = zhiyuanzheshouji;
	}
	
	/**
	 * 获取：志愿者手机
	 */
	public String getZhiyuanzheshouji() {
		return zhiyuanzheshouji;
	}
			
}
