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

import com.entity.ShouzhushenqingEntity;
import com.entity.view.ShouzhushenqingView;

import com.service.ShouzhushenqingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 受助申请
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/shouzhushenqing")
public class ShouzhushenqingController {
    @Autowired
    private ShouzhushenqingService shouzhushenqingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShouzhushenqingEntity shouzhushenqing,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shouzhuzhe")) {
			shouzhushenqing.setShouzhuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ShouzhushenqingEntity> ew = new EntityWrapper<ShouzhushenqingEntity>();


        //查询结果
		PageUtils page = shouzhushenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhushenqing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShouzhushenqingEntity shouzhushenqing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShouzhushenqingEntity> ew = new EntityWrapper<ShouzhushenqingEntity>();

        //查询结果
		PageUtils page = shouzhushenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhushenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShouzhushenqingEntity shouzhushenqing){
       	EntityWrapper<ShouzhushenqingEntity> ew = new EntityWrapper<ShouzhushenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shouzhushenqing, "shouzhushenqing")); 
        return R.ok().put("data", shouzhushenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShouzhushenqingEntity shouzhushenqing){
        EntityWrapper< ShouzhushenqingEntity> ew = new EntityWrapper< ShouzhushenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shouzhushenqing, "shouzhushenqing")); 
		ShouzhushenqingView shouzhushenqingView =  shouzhushenqingService.selectView(ew);
		return R.ok("查询受助申请成功").put("data", shouzhushenqingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShouzhushenqingEntity shouzhushenqing = shouzhushenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shouzhushenqing,deSens);
        return R.ok().put("data", shouzhushenqing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShouzhushenqingEntity shouzhushenqing = shouzhushenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shouzhushenqing,deSens);
        return R.ok().put("data", shouzhushenqing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShouzhushenqingEntity shouzhushenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouzhushenqing);
        shouzhushenqingService.insert(shouzhushenqing);
        return R.ok().put("data",shouzhushenqing.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShouzhushenqingEntity shouzhushenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouzhushenqing);
        shouzhushenqingService.insert(shouzhushenqing);
        return R.ok().put("data",shouzhushenqing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShouzhushenqingEntity shouzhushenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouzhushenqing);
        //全部更新
        shouzhushenqingService.updateById(shouzhushenqing);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ShouzhushenqingEntity> list = new ArrayList<ShouzhushenqingEntity>();
        for(Long id : ids) {
            ShouzhushenqingEntity shouzhushenqing = shouzhushenqingService.selectById(id);
            shouzhushenqing.setSfsh(sfsh);
            shouzhushenqing.setShhf(shhf);
            list.add(shouzhushenqing);
        }
        shouzhushenqingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shouzhushenqingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
