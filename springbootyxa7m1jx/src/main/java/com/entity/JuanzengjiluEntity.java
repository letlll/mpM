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
 * 捐赠记录
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@TableName("juanzengjilu")
public class JuanzengjiluEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JuanzengjiluEntity() {
		
	}
	
	public JuanzengjiluEntity(T t) {
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
	 * 项目名称
	 */
					
	private String xiangmumingcheng;
	
	/**
	 * 项目分类
	 */
					
	private String xiangmufenlei;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 发布人
	 */
					
	private String faburen;
	
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
	 * 捐赠账号
	 */
					
	private String juanzengzhanghao;
	
	/**
	 * 捐赠姓名
	 */
					
	private String juanzengxingming;
	
	/**
	 * 捐赠手机
	 */
					
	private String juanzengshouji;
	
	/**
	 * 捐赠金额
	 */
					
	private Double leijizijin;
	
	/**
	 * 捐赠方式
	 */
					
	private String juanzengfangshi;
	
	/**
	 * 捐赠时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date juanzengshijian;
	
	/**
	 * 捐赠备注
	 */
					
	private String juanzengbeizhu;
	
	
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
	 * 设置：项目名称
	 */
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	/**
	 * 获取：项目名称
	 */
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
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
	 * 设置：发布人
	 */
	public void setFaburen(String faburen) {
		this.faburen = faburen;
	}
	/**
	 * 获取：发布人
	 */
	public String getFaburen() {
		return faburen;
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
	 * 设置：捐赠账号
	 */
	public void setJuanzengzhanghao(String juanzengzhanghao) {
		this.juanzengzhanghao = juanzengzhanghao;
	}
	/**
	 * 获取：捐赠账号
	 */
	public String getJuanzengzhanghao() {
		return juanzengzhanghao;
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
	 * 设置：捐赠金额
	 */
	public void setLeijizijin(Double leijizijin) {
		this.leijizijin = leijizijin;
	}
	/**
	 * 获取：捐赠金额
	 */
	public Double getLeijizijin() {
		return leijizijin;
	}
	/**
	 * 设置：捐赠方式
	 */
	public void setJuanzengfangshi(String juanzengfangshi) {
		this.juanzengfangshi = juanzengfangshi;
	}
	/**
	 * 获取：捐赠方式
	 */
	public String getJuanzengfangshi() {
		return juanzengfangshi;
	}
	/**
	 * 设置：捐赠时间
	 */
	public void setJuanzengshijian(Date juanzengshijian) {
		this.juanzengshijian = juanzengshijian;
	}
	/**
	 * 获取：捐赠时间
	 */
	public Date getJuanzengshijian() {
		return juanzengshijian;
	}
	/**
	 * 设置：捐赠备注
	 */
	public void setJuanzengbeizhu(String juanzengbeizhu) {
		this.juanzengbeizhu = juanzengbeizhu;
	}
	/**
	 * 获取：捐赠备注
	 */
	public String getJuanzengbeizhu() {
		return juanzengbeizhu;
	}

}
