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

import com.entity.JuanzengjiluEntity;
import com.entity.view.JuanzengjiluView;

import com.service.JuanzengjiluService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 捐赠记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/juanzengjilu")
public class JuanzengjiluController {
    @Autowired
    private JuanzengjiluService juanzengjiluService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuanzengjiluEntity juanzengjilu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shouzhuzhe")) {
			juanzengjilu.setShouzhuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("juanzengzhe")) {
			juanzengjilu.setJuanzengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();


        //查询结果
		PageUtils page = juanzengjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengjilu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JuanzengjiluEntity juanzengjilu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();

        //查询结果
		PageUtils page = juanzengjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengjilu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuanzengjiluEntity juanzengjilu){
       	EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( juanzengjilu, "juanzengjilu")); 
        return R.ok().put("data", juanzengjiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuanzengjiluEntity juanzengjilu){
        EntityWrapper< JuanzengjiluEntity> ew = new EntityWrapper< JuanzengjiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( juanzengjilu, "juanzengjilu")); 
		JuanzengjiluView juanzengjiluView =  juanzengjiluService.selectView(ew);
		return R.ok("查询捐赠记录成功").put("data", juanzengjiluView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuanzengjiluEntity juanzengjilu = juanzengjiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengjilu,deSens);
        return R.ok().put("data", juanzengjilu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuanzengjiluEntity juanzengjilu = juanzengjiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengjilu,deSens);
        return R.ok().put("data", juanzengjilu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        juanzengjiluService.insert(juanzengjilu);
        return R.ok().put("data",juanzengjilu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        juanzengjiluService.insert(juanzengjilu);
        return R.ok().put("data",juanzengjilu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        //全部更新
        juanzengjiluService.updateById(juanzengjilu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        juanzengjiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
