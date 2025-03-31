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

import com.entity.GongyihuodongEntity;
import com.entity.view.GongyihuodongView;

import com.service.GongyihuodongService;
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
 * 公益活动
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
@RestController
@RequestMapping("/gongyihuodong")
public class GongyihuodongController {
    @Autowired
    private GongyihuodongService gongyihuodongService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GongyihuodongEntity gongyihuodong,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GongyihuodongEntity> ew = new EntityWrapper<GongyihuodongEntity>();


        //查询结果
		PageUtils page = gongyihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongyihuodong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,GongyihuodongEntity gongyihuodong, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GongyihuodongEntity> ew = new EntityWrapper<GongyihuodongEntity>();

        //查询结果
		PageUtils page = gongyihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongyihuodong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GongyihuodongEntity gongyihuodong){
       	EntityWrapper<GongyihuodongEntity> ew = new EntityWrapper<GongyihuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gongyihuodong, "gongyihuodong")); 
        return R.ok().put("data", gongyihuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GongyihuodongEntity gongyihuodong){
        EntityWrapper< GongyihuodongEntity> ew = new EntityWrapper< GongyihuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gongyihuodong, "gongyihuodong")); 
		GongyihuodongView gongyihuodongView =  gongyihuodongService.selectView(ew);
		return R.ok("查询公益活动成功").put("data", gongyihuodongView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GongyihuodongEntity gongyihuodong = gongyihuodongService.selectById(id);
		gongyihuodong.setClicktime(new Date());
		gongyihuodongService.updateById(gongyihuodong);
        gongyihuodong = gongyihuodongService.selectView(new EntityWrapper<GongyihuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongyihuodong,deSens);
        return R.ok().put("data", gongyihuodong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GongyihuodongEntity gongyihuodong = gongyihuodongService.selectById(id);
		gongyihuodong.setClicktime(new Date());
		gongyihuodongService.updateById(gongyihuodong);
        gongyihuodong = gongyihuodongService.selectView(new EntityWrapper<GongyihuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongyihuodong,deSens);
        return R.ok().put("data", gongyihuodong);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GongyihuodongEntity gongyihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongyihuodong);
        gongyihuodongService.insert(gongyihuodong);
        return R.ok().put("data",gongyihuodong.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody GongyihuodongEntity gongyihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongyihuodong);
        gongyihuodongService.insert(gongyihuodong);
        return R.ok().put("data",gongyihuodong.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GongyihuodongEntity gongyihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongyihuodong);
        //全部更新
        gongyihuodongService.updateById(gongyihuodong);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        gongyihuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,GongyihuodongEntity gongyihuodong, HttpServletRequest request,String pre){
        EntityWrapper<GongyihuodongEntity> ew = new EntityWrapper<GongyihuodongEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = gongyihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongyihuodong), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（按收藏推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,GongyihuodongEntity gongyihuodong, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "huodongleixing";
        // 查询收藏集合
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "gongyihuodong").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<GongyihuodongEntity> gongyihuodongList = new ArrayList<GongyihuodongEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            List<String> typeList = new ArrayList<String>();
            for(StoreupEntity s : storeups) {
                if(typeList.contains(s.getInteltype())) continue;
                typeList.add(s.getInteltype());
                gongyihuodongList.addAll(gongyihuodongService.selectList(new EntityWrapper<GongyihuodongEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<GongyihuodongEntity> ew = new EntityWrapper<GongyihuodongEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        // 根据协同结果查询结果并返回
        PageUtils page = gongyihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongyihuodong), params), params));
        List<GongyihuodongEntity> pageList = (List<GongyihuodongEntity>)page.getList();
        if(gongyihuodongList.size()<limit) {
            int toAddNum = (limit-gongyihuodongList.size())<=pageList.size()?(limit-gongyihuodongList.size()):pageList.size();
            for(GongyihuodongEntity o1 : pageList) {
                boolean addFlag = true;
                for(GongyihuodongEntity o2 : gongyihuodongList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    gongyihuodongList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(gongyihuodongList.size()>limit) {
            gongyihuodongList = gongyihuodongList.subList(0, limit);
        }
        page.setList(gongyihuodongList);
        return R.ok().put("data", page);
    }






}
