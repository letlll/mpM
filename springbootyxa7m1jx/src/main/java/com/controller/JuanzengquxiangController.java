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

import com.entity.JuanzengquxiangEntity;
import com.entity.view.JuanzengquxiangView;

import com.service.JuanzengquxiangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 捐赠去向
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/juanzengquxiang")
public class JuanzengquxiangController {
    @Autowired
    private JuanzengquxiangService juanzengquxiangService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuanzengquxiangEntity juanzengquxiang,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shouzhuzhe")) {
			juanzengquxiang.setShouzhuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<JuanzengquxiangEntity> ew = new EntityWrapper<JuanzengquxiangEntity>();


        //查询结果
		PageUtils page = juanzengquxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengquxiang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JuanzengquxiangEntity juanzengquxiang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengquxiangEntity> ew = new EntityWrapper<JuanzengquxiangEntity>();

        //查询结果
		PageUtils page = juanzengquxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengquxiang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuanzengquxiangEntity juanzengquxiang){
       	EntityWrapper<JuanzengquxiangEntity> ew = new EntityWrapper<JuanzengquxiangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( juanzengquxiang, "juanzengquxiang")); 
        return R.ok().put("data", juanzengquxiangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuanzengquxiangEntity juanzengquxiang){
        EntityWrapper< JuanzengquxiangEntity> ew = new EntityWrapper< JuanzengquxiangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( juanzengquxiang, "juanzengquxiang")); 
		JuanzengquxiangView juanzengquxiangView =  juanzengquxiangService.selectView(ew);
		return R.ok("查询捐赠去向成功").put("data", juanzengquxiangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuanzengquxiangEntity juanzengquxiang = juanzengquxiangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengquxiang,deSens);
        return R.ok().put("data", juanzengquxiang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuanzengquxiangEntity juanzengquxiang = juanzengquxiangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengquxiang,deSens);
        return R.ok().put("data", juanzengquxiang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JuanzengquxiangEntity juanzengquxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengquxiang);
        juanzengquxiangService.insert(juanzengquxiang);
        return R.ok().put("data",juanzengquxiang.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JuanzengquxiangEntity juanzengquxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengquxiang);
        juanzengquxiangService.insert(juanzengquxiang);
        return R.ok().put("data",juanzengquxiang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JuanzengquxiangEntity juanzengquxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengquxiang);
        //全部更新
        juanzengquxiangService.updateById(juanzengquxiang);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        juanzengquxiangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
