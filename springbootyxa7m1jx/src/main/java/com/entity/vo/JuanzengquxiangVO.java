package com.entity.vo;

import com.entity.JuanzengquxiangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 捐赠去向
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public class JuanzengquxiangVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 项目分类
	 */
	
	private String xiangmufenlei;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 受助账号
	 */
	
	private String shouzhuzhanghao;
		
	/**
	 * 受助人
	 */
	
	private String shouzhuren;
		
	/**
	 * 联系方式
	 */
	
	private String lianxifangshi;
		
	/**
	 * 去向说明
	 */
	
	private String quxiangshuoming;
		
	/**
	 * 更新时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date gengxinshijian;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
				
	
	/**
	 * 设置：项目分类
	 */
	 
	public void setXiangmufenlei(String xiangmufenlei) {
		this.xiangmufenlei = xiangmufenlei;
	}
	
	/**
	 * 获取：项目分类
	 */
	public String getXiangmufenlei() {
		return xiangmufenlei;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：受助账号
	 */
	 
	public void setShouzhuzhanghao(String shouzhuzhanghao) {
		this.shouzhuzhanghao = shouzhuzhanghao;
	}
	
	/**
	 * 获取：受助账号
	 */
	public String getShouzhuzhanghao() {
		return shouzhuzhanghao;
	}
				
	
	/**
	 * 设置：受助人
	 */
	 
	public void setShouzhuren(String shouzhuren) {
		this.shouzhuren = shouzhuren;
	}
	
	/**
	 * 获取：受助人
	 */
	public String getShouzhuren() {
		return shouzhuren;
	}
				
	
	/**
	 * 设置：联系方式
	 */
	 
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	
	/**
	 * 获取：联系方式
	 */
	public String getLianxifangshi() {
		return lianxifangshi;
	}
				
	
	/**
	 * 设置：去向说明
	 */
	 
	public void setQuxiangshuoming(String quxiangshuoming) {
		this.quxiangshuoming = quxiangshuoming;
	}
	
	/**
	 * 获取：去向说明
	 */
	public String getQuxiangshuoming() {
		return quxiangshuoming;
	}
				
	
	/**
	 * 设置：更新时间
	 */
	 
	public void setGengxinshijian(Date gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}
	
	/**
	 * 获取：更新时间
	 */
	public Date getGengxinshijian() {
		return gengxinshijian;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
			
}
