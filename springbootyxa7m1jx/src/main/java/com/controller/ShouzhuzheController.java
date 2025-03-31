package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.ShouzhuzheEntity;
import com.entity.view.ShouzhuzheView;

import com.service.ShouzhuzheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 受助者
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/shouzhuzhe")
public class ShouzhuzheController {
    @Autowired
    private ShouzhuzheService shouzhuzheService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        ShouzhuzheEntity u = shouzhuzheService.selectOne(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", username));
        // 当用户不存在或des方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.desEncrypt(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"shouzhuzhe",  "受助者" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ShouzhuzheEntity shouzhuzhe){
    	//ValidatorUtils.validateEntity(shouzhuzhe);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	ShouzhuzheEntity u = shouzhuzheService.selectOne(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同受助账号，否则返回错误信息
        if(shouzhuzheService.selectCount(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()))>0) {
            return R.error("受助账号已存在");
        }
		Long uId = new Date().getTime();
		shouzhuzhe.setId(uId);
        //设置登录密码des方式加密
        shouzhuzhe.setMima(EncryptUtil.desEncrypt(shouzhuzhe.getMima()));
        //保存用户
        shouzhuzheService.insert(shouzhuzhe);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ShouzhuzheEntity u = shouzhuzheService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        ShouzhuzheEntity u = shouzhuzheService.selectOne(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用des方式加密
        u.setMima(EncryptUtil.desEncrypt("123456"));
        shouzhuzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShouzhuzheEntity shouzhuzhe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShouzhuzheEntity> ew = new EntityWrapper<ShouzhuzheEntity>();


        //查询结果
		PageUtils page = shouzhuzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhuzhe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShouzhuzheEntity shouzhuzhe, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShouzhuzheEntity> ew = new EntityWrapper<ShouzhuzheEntity>();

        //查询结果
		PageUtils page = shouzhuzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhuzhe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShouzhuzheEntity shouzhuzhe){
       	EntityWrapper<ShouzhuzheEntity> ew = new EntityWrapper<ShouzhuzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shouzhuzhe, "shouzhuzhe")); 
        return R.ok().put("data", shouzhuzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShouzhuzheEntity shouzhuzhe){
        EntityWrapper< ShouzhuzheEntity> ew = new EntityWrapper< ShouzhuzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shouzhuzhe, "shouzhuzhe")); 
		ShouzhuzheView shouzhuzheView =  shouzhuzheService.selectView(ew);
		return R.ok("查询受助者成功").put("data", shouzhuzheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShouzhuzheEntity shouzhuzhe = shouzhuzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shouzhuzhe,deSens);
        return R.ok().put("data", shouzhuzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShouzhuzheEntity shouzhuzhe = shouzhuzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shouzhuzhe,deSens);
        return R.ok().put("data", shouzhuzhe);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShouzhuzheEntity shouzhuzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shouzhuzheService.selectCount(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()))>0) {
            return R.error("受助账号已存在");
        }
        //ValidatorUtils.validateEntity(shouzhuzhe);
        //验证账号唯一性，否则返回错误信息
        ShouzhuzheEntity u = shouzhuzheService.selectOne(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	shouzhuzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		shouzhuzhe.setId(new Date().getTime());
        //密码使用des方式加密
        shouzhuzhe.setMima(EncryptUtil.desEncrypt(shouzhuzhe.getMima()));
        shouzhuzheService.insert(shouzhuzhe);
        return R.ok().put("data",shouzhuzhe.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShouzhuzheEntity shouzhuzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shouzhuzheService.selectCount(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()))>0) {
            return R.error("受助账号已存在");
        }
        //ValidatorUtils.validateEntity(shouzhuzhe);
        //验证账号唯一性，否则返回错误信息
        ShouzhuzheEntity u = shouzhuzheService.selectOne(new EntityWrapper<ShouzhuzheEntity>().eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	shouzhuzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		shouzhuzhe.setId(new Date().getTime());
        //密码使用des方式加密
        shouzhuzhe.setMima(EncryptUtil.desEncrypt(shouzhuzhe.getMima()));
        shouzhuzheService.insert(shouzhuzhe);
        return R.ok().put("data",shouzhuzhe.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShouzhuzheEntity shouzhuzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouzhuzhe);
        //验证字段唯一性，否则返回错误信息
        if(shouzhuzheService.selectCount(new EntityWrapper<ShouzhuzheEntity>().ne("id", shouzhuzhe.getId()).eq("shouzhuzhanghao", shouzhuzhe.getShouzhuzhanghao()))>0) {
            return R.error("受助账号已存在");
        }
	    ShouzhuzheEntity shouzhuzheEntity = shouzhuzheService.selectById(shouzhuzhe.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(shouzhuzhe.getMima()) && !shouzhuzhe.getMima().equals(shouzhuzheEntity.getMima())) {
            //密码使用des方式加密
            shouzhuzhe.setMima(EncryptUtil.desEncrypt(shouzhuzhe.getMima()));
        }
        //全部更新
        shouzhuzheService.updateById(shouzhuzhe);
        if(null!=shouzhuzhe.getShouzhuzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(shouzhuzhe.getShouzhuzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", shouzhuzhe.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shouzhuzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    







    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ShouzhuzheEntity shouzhuzhe, HttpServletRequest request){
        EntityWrapper<ShouzhuzheEntity> ew = new EntityWrapper<ShouzhuzheEntity>();
        int count = shouzhuzheService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhuzhe), params), params));
        return R.ok().put("data", count);
    }

}
