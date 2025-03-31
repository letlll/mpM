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

import com.entity.JuanzengxiangmuEntity;
import com.entity.view.JuanzengxiangmuView;

import com.service.JuanzengxiangmuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 捐赠项目
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/juanzengxiangmu")
public class JuanzengxiangmuController {
    @Autowired
    private JuanzengxiangmuService juanzengxiangmuService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuanzengxiangmuEntity juanzengxiangmu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shouzhuzhe")) {
			juanzengxiangmu.setShouzhuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<JuanzengxiangmuEntity> ew = new EntityWrapper<JuanzengxiangmuEntity>();


        //查询结果
		PageUtils page = juanzengxiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengxiangmu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JuanzengxiangmuEntity juanzengxiangmu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengxiangmuEntity> ew = new EntityWrapper<JuanzengxiangmuEntity>();

        //查询结果
		PageUtils page = juanzengxiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengxiangmu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuanzengxiangmuEntity juanzengxiangmu){
       	EntityWrapper<JuanzengxiangmuEntity> ew = new EntityWrapper<JuanzengxiangmuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( juanzengxiangmu, "juanzengxiangmu")); 
        return R.ok().put("data", juanzengxiangmuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuanzengxiangmuEntity juanzengxiangmu){
        EntityWrapper< JuanzengxiangmuEntity> ew = new EntityWrapper< JuanzengxiangmuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( juanzengxiangmu, "juanzengxiangmu")); 
		JuanzengxiangmuView juanzengxiangmuView =  juanzengxiangmuService.selectView(ew);
		return R.ok("查询捐赠项目成功").put("data", juanzengxiangmuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuanzengxiangmuEntity juanzengxiangmu = juanzengxiangmuService.selectById(id);
        if(null==juanzengxiangmu.getClicknum()){
            juanzengxiangmu.setClicknum(0);
        }
		juanzengxiangmu.setClicknum(juanzengxiangmu.getClicknum()+1);
		juanzengxiangmuService.updateById(juanzengxiangmu);
        juanzengxiangmu = juanzengxiangmuService.selectView(new EntityWrapper<JuanzengxiangmuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengxiangmu,deSens);
        return R.ok().put("data", juanzengxiangmu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuanzengxiangmuEntity juanzengxiangmu = juanzengxiangmuService.selectById(id);
        if(null==juanzengxiangmu.getClicknum()){
            juanzengxiangmu.setClicknum(0);
        }
		juanzengxiangmu.setClicknum(juanzengxiangmu.getClicknum()+1);
		juanzengxiangmuService.updateById(juanzengxiangmu);
        juanzengxiangmu = juanzengxiangmuService.selectView(new EntityWrapper<JuanzengxiangmuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengxiangmu,deSens);
        return R.ok().put("data", juanzengxiangmu);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        JuanzengxiangmuEntity juanzengxiangmu = juanzengxiangmuService.selectById(id);
        if(type.equals("1")) {
        	juanzengxiangmu.setThumbsupnum(juanzengxiangmu.getThumbsupnum()+1);
        } else {
        	juanzengxiangmu.setCrazilynum(juanzengxiangmu.getCrazilynum()+1);
        }
        juanzengxiangmuService.updateById(juanzengxiangmu);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JuanzengxiangmuEntity juanzengxiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengxiangmu);
        juanzengxiangmuService.insert(juanzengxiangmu);
        return R.ok().put("data",juanzengxiangmu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JuanzengxiangmuEntity juanzengxiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengxiangmu);
        juanzengxiangmuService.insert(juanzengxiangmu);
        return R.ok().put("data",juanzengxiangmu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JuanzengxiangmuEntity juanzengxiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengxiangmu);
        //全部更新
        juanzengxiangmuService.updateById(juanzengxiangmu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        juanzengxiangmuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
