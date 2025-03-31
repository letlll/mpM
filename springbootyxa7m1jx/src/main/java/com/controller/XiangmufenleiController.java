package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XiangmufenleiEntity;
import com.entity.view.XiangmufenleiView;

import com.service.XiangmufenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 项目分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/xiangmufenlei")
public class XiangmufenleiController {
    @Autowired
    private XiangmufenleiService xiangmufenleiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiangmufenleiEntity xiangmufenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();


        //查询结果
		PageUtils page = xiangmufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmufenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiangmufenleiEntity xiangmufenlei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();

        //查询结果
		PageUtils page = xiangmufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmufenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiangmufenleiEntity xiangmufenlei){
       	EntityWrapper<XiangmufenleiEntity> ew = new EntityWrapper<XiangmufenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiangmufenlei, "xiangmufenlei")); 
        return R.ok().put("data", xiangmufenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiangmufenleiEntity xiangmufenlei){
        EntityWrapper< XiangmufenleiEntity> ew = new EntityWrapper< XiangmufenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiangmufenlei, "xiangmufenlei")); 
		XiangmufenleiView xiangmufenleiView =  xiangmufenleiService.selectView(ew);
		return R.ok("查询项目分类成功").put("data", xiangmufenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiangmufenleiEntity xiangmufenlei = xiangmufenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmufenlei,deSens);
        return R.ok().put("data", xiangmufenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiangmufenleiEntity xiangmufenlei = xiangmufenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmufenlei,deSens);
        return R.ok().put("data", xiangmufenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xiangmufenleiService.selectCount(new EntityWrapper<XiangmufenleiEntity>().eq("xiangmufenlei", xiangmufenlei.getXiangmufenlei()))>0) {
            return R.error("项目分类已存在");
        }
        //ValidatorUtils.validateEntity(xiangmufenlei);
        xiangmufenleiService.insert(xiangmufenlei);
        return R.ok().put("data",xiangmufenlei.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xiangmufenleiService.selectCount(new EntityWrapper<XiangmufenleiEntity>().eq("xiangmufenlei", xiangmufenlei.getXiangmufenlei()))>0) {
            return R.error("项目分类已存在");
        }
        //ValidatorUtils.validateEntity(xiangmufenlei);
        xiangmufenleiService.insert(xiangmufenlei);
        return R.ok().put("data",xiangmufenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiangmufenleiEntity xiangmufenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmufenlei);
        //验证字段唯一性，否则返回错误信息
        if(xiangmufenleiService.selectCount(new EntityWrapper<XiangmufenleiEntity>().ne("id", xiangmufenlei.getId()).eq("xiangmufenlei", xiangmufenlei.getXiangmufenlei()))>0) {
            return R.error("项目分类已存在");
        }
        //全部更新
        xiangmufenleiService.updateById(xiangmufenlei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiangmufenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
