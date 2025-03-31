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

import com.entity.ZhiyuanzheEntity;
import com.entity.view.ZhiyuanzheView;

import com.service.ZhiyuanzheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 志愿者
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:19
 */
@RestController
@RequestMapping("/zhiyuanzhe")
public class ZhiyuanzheController {
    @Autowired
    private ZhiyuanzheService zhiyuanzheService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        ZhiyuanzheEntity u = zhiyuanzheService.selectOne(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", username));
        // 当用户不存在或des方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.desEncrypt(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"zhiyuanzhe",  "志愿者" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ZhiyuanzheEntity zhiyuanzhe){
    	//ValidatorUtils.validateEntity(zhiyuanzhe);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	ZhiyuanzheEntity u = zhiyuanzheService.selectOne(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同志愿账号，否则返回错误信息
        if(zhiyuanzheService.selectCount(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()))>0) {
            return R.error("志愿账号已存在");
        }
		Long uId = new Date().getTime();
		zhiyuanzhe.setId(uId);
        //设置登录密码des方式加密
        zhiyuanzhe.setMima(EncryptUtil.desEncrypt(zhiyuanzhe.getMima()));
        //保存用户
        zhiyuanzheService.insert(zhiyuanzhe);
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
        ZhiyuanzheEntity u = zhiyuanzheService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        ZhiyuanzheEntity u = zhiyuanzheService.selectOne(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用des方式加密
        u.setMima(EncryptUtil.desEncrypt("123456"));
        zhiyuanzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhiyuanzheEntity zhiyuanzhe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhiyuanzheEntity> ew = new EntityWrapper<ZhiyuanzheEntity>();


        //查询结果
		PageUtils page = zhiyuanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhiyuanzhe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZhiyuanzheEntity zhiyuanzhe, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhiyuanzheEntity> ew = new EntityWrapper<ZhiyuanzheEntity>();

        //查询结果
		PageUtils page = zhiyuanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhiyuanzhe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhiyuanzheEntity zhiyuanzhe){
       	EntityWrapper<ZhiyuanzheEntity> ew = new EntityWrapper<ZhiyuanzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhiyuanzhe, "zhiyuanzhe")); 
        return R.ok().put("data", zhiyuanzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhiyuanzheEntity zhiyuanzhe){
        EntityWrapper< ZhiyuanzheEntity> ew = new EntityWrapper< ZhiyuanzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhiyuanzhe, "zhiyuanzhe")); 
		ZhiyuanzheView zhiyuanzheView =  zhiyuanzheService.selectView(ew);
		return R.ok("查询志愿者成功").put("data", zhiyuanzheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhiyuanzheEntity zhiyuanzhe = zhiyuanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhiyuanzhe,deSens);
        return R.ok().put("data", zhiyuanzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhiyuanzheEntity zhiyuanzhe = zhiyuanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhiyuanzhe,deSens);
        return R.ok().put("data", zhiyuanzhe);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhiyuanzheEntity zhiyuanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zhiyuanzheService.selectCount(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()))>0) {
            return R.error("志愿账号已存在");
        }
        //ValidatorUtils.validateEntity(zhiyuanzhe);
        //验证账号唯一性，否则返回错误信息
        ZhiyuanzheEntity u = zhiyuanzheService.selectOne(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	zhiyuanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		zhiyuanzhe.setId(new Date().getTime());
        //密码使用des方式加密
        zhiyuanzhe.setMima(EncryptUtil.desEncrypt(zhiyuanzhe.getMima()));
        zhiyuanzheService.insert(zhiyuanzhe);
        return R.ok().put("data",zhiyuanzhe.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhiyuanzheEntity zhiyuanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zhiyuanzheService.selectCount(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()))>0) {
            return R.error("志愿账号已存在");
        }
        //ValidatorUtils.validateEntity(zhiyuanzhe);
        //验证账号唯一性，否则返回错误信息
        ZhiyuanzheEntity u = zhiyuanzheService.selectOne(new EntityWrapper<ZhiyuanzheEntity>().eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	zhiyuanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		zhiyuanzhe.setId(new Date().getTime());
        //密码使用des方式加密
        zhiyuanzhe.setMima(EncryptUtil.desEncrypt(zhiyuanzhe.getMima()));
        zhiyuanzheService.insert(zhiyuanzhe);
        return R.ok().put("data",zhiyuanzhe.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhiyuanzheEntity zhiyuanzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhiyuanzhe);
        //验证字段唯一性，否则返回错误信息
        if(zhiyuanzheService.selectCount(new EntityWrapper<ZhiyuanzheEntity>().ne("id", zhiyuanzhe.getId()).eq("zhiyuanzhanghao", zhiyuanzhe.getZhiyuanzhanghao()))>0) {
            return R.error("志愿账号已存在");
        }
	    ZhiyuanzheEntity zhiyuanzheEntity = zhiyuanzheService.selectById(zhiyuanzhe.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(zhiyuanzhe.getMima()) && !zhiyuanzhe.getMima().equals(zhiyuanzheEntity.getMima())) {
            //密码使用des方式加密
            zhiyuanzhe.setMima(EncryptUtil.desEncrypt(zhiyuanzhe.getMima()));
        }
        //全部更新
        zhiyuanzheService.updateById(zhiyuanzhe);
        if(null!=zhiyuanzhe.getZhiyuanzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(zhiyuanzhe.getZhiyuanzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", zhiyuanzhe.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhiyuanzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
