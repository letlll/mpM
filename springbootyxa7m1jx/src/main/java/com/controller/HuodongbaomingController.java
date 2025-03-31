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

import com.entity.HuodongbaomingEntity;
import com.entity.view.HuodongbaomingView;

import com.service.HuodongbaomingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 活动报名
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/huodongbaoming")
public class HuodongbaomingController {
    @Autowired
    private HuodongbaomingService huodongbaomingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuodongbaomingEntity huodongbaoming,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zhiyuanzhe")) {
			huodongbaoming.setZhiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<HuodongbaomingEntity>();


        //查询结果
		PageUtils page = huodongbaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongbaoming), params), params));
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
    public R list(@RequestParam Map<String, Object> params,HuodongbaomingEntity huodongbaoming, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<HuodongbaomingEntity>();

        //查询结果
		PageUtils page = huodongbaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongbaoming), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuodongbaomingEntity huodongbaoming){
       	EntityWrapper<HuodongbaomingEntity> ew = new EntityWrapper<HuodongbaomingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huodongbaoming, "huodongbaoming")); 
        return R.ok().put("data", huodongbaomingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuodongbaomingEntity huodongbaoming){
        EntityWrapper< HuodongbaomingEntity> ew = new EntityWrapper< HuodongbaomingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huodongbaoming, "huodongbaoming")); 
		HuodongbaomingView huodongbaomingView =  huodongbaomingService.selectView(ew);
		return R.ok("查询活动报名成功").put("data", huodongbaomingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuodongbaomingEntity huodongbaoming = huodongbaomingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huodongbaoming,deSens);
        return R.ok().put("data", huodongbaoming);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuodongbaomingEntity huodongbaoming = huodongbaomingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huodongbaoming,deSens);
        return R.ok().put("data", huodongbaoming);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongbaoming);
        huodongbaomingService.insert(huodongbaoming);
        return R.ok().put("data",huodongbaoming.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongbaoming);
        huodongbaomingService.insert(huodongbaoming);
        return R.ok().put("data",huodongbaoming.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody HuodongbaomingEntity huodongbaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongbaoming);
        HuodongbaomingEntity oldEntity = huodongbaomingService.selectById(huodongbaoming.getId());
        if (oldEntity == null) {
            return R.error("活动报名记录不存在");
        }
        
        // 如果是签到操作
        if (huodongbaoming.getQiandaozhuangtai() != null) {
            oldEntity.setQiandaozhuangtai(huodongbaoming.getQiandaozhuangtai());
            // 计算工时
            if ("已签到".equals(huodongbaoming.getQiandaozhuangtai()) && 
                oldEntity.getKaishishijian() != null && oldEntity.getJieshushijian() != null) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startTime = sdf.parse(oldEntity.getKaishishijian());
                    Date endTime = sdf.parse(oldEntity.getJieshushijian());
                    long diff = endTime.getTime() - startTime.getTime();
                    int hours = (int) (diff / (1000 * 60 * 60));
                    oldEntity.setGongshijilu(hours);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // 其他字段更新
            oldEntity.setHuodongbiaoti(huodongbaoming.getHuodongbiaoti());
            oldEntity.setHuodongleixing(huodongbaoming.getHuodongleixing());
            oldEntity.setFengmian(huodongbaoming.getFengmian());
            oldEntity.setKaishishijian(huodongbaoming.getKaishishijian());
            oldEntity.setJieshushijian(huodongbaoming.getJieshushijian());
            oldEntity.setHuodongdizhi(huodongbaoming.getHuodongdizhi());
            oldEntity.setJubanfang(huodongbaoming.getJubanfang());
        }
        
        huodongbaomingService.updateById(oldEntity);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        huodongbaomingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
