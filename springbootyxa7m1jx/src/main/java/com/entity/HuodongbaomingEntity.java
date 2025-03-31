package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 活动报名
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("huodongbaoming")
public class HuodongbaomingEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public HuodongbaomingEntity() {
		
	}
	
	public HuodongbaomingEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 活动标题
	 */
					
	private String huodongbiaoti;
	
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
	 * 签到状态
	 */
	private String qiandaozhuangtai;

	/**
	 * 结束时间
	 */
	private String jieshushijian;

	/**
	 * 工时记录
	 */
	private Integer gongshijilu;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：活动标题
	 */
	public void setHuodongbiaoti(String huodongbiaoti) {
		this.huodongbiaoti = huodongbiaoti;
	}
	/**
	 * 获取：活动标题
	 */
	public String getHuodongbiaoti() {
		return huodongbiaoti;
	}
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

	/**
	 * 设置：签到状态
	 */
	public void setQiandaozhuangtai(String qiandaozhuangtai) {
		this.qiandaozhuangtai = qiandaozhuangtai;
	}

	/**
	 * 获取：签到状态
	 */
	public String getQiandaozhuangtai() {
		return qiandaozhuangtai;
	}

	/**
	 * 设置：结束时间
	 */
	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	/**
	 * 获取：结束时间
	 */
	public String getJieshushijian() {
		return jieshushijian;
	}

	/**
	 * 设置：工时记录
	 */
	public void setGongshijilu(Integer gongshijilu) {
		this.gongshijilu = gongshijilu;
	}

	/**
	 * 获取：工时记录
	 */
	public Integer getGongshijilu() {
		return gongshijilu;
	}
}
