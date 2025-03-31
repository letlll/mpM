package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("wupin_juanzeng")
public class WupinJuanzengEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String wupinmingcheng;
    
    private String wupinleixing;
    
    private Integer shuliang;
    
    private String juanzengzhanghao;
    
    private String juanzengxingming;
    
    private String tupian;
    
    private String zhuangtai;
    
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date juanzengshijian;
    
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWupinmingcheng() {
        return wupinmingcheng;
    }

    public void setWupinmingcheng(String wupinmingcheng) {
        this.wupinmingcheng = wupinmingcheng;
    }

    public String getWupinleixing() {
        return wupinleixing;
    }

    public void setWupinleixing(String wupinleixing) {
        this.wupinleixing = wupinleixing;
    }

    public Integer getShuliang() {
        return shuliang;
    }

    public void setShuliang(Integer shuliang) {
        this.shuliang = shuliang;
    }

    public String getJuanzengzhanghao() {
        return juanzengzhanghao;
    }

    public void setJuanzengzhanghao(String juanzengzhanghao) {
        this.juanzengzhanghao = juanzengzhanghao;
    }

    public String getJuanzengxingming() {
        return juanzengxingming;
    }

    public void setJuanzengxingming(String juanzengxingming) {
        this.juanzengxingming = juanzengxingming;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }
    
    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }
    
    public Date getJuanzengshijian() {
        return juanzengshijian;
    }

    public void setJuanzengshijian(Date juanzengshijian) {
        this.juanzengshijian = juanzengshijian;
    }
    
    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}