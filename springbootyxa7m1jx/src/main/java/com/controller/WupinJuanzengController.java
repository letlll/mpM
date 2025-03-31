package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.WupinJuanzengEntity;
import com.service.WupinJuanzengService;
import com.utils.PageUtils;
import com.utils.R;

@RestController
@RequestMapping("/wupinjuanzeng")
public class WupinJuanzengController {
    @Autowired
    private WupinJuanzengService wupinJuanzengService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils page = wupinJuanzengService.queryPage(params);
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        WupinJuanzengEntity wupinJuanzeng = wupinJuanzengService.selectById(id);
        return R.ok().put("data", wupinJuanzeng);
    }

    @RequestMapping("/save")
    public R save(@RequestBody WupinJuanzengEntity wupinJuanzeng) {
        wupinJuanzeng.setAddtime(new Date());
        wupinJuanzeng.setZhuangtai("待审核");
        wupinJuanzengService.insert(wupinJuanzeng);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody WupinJuanzengEntity wupinJuanzeng) {
        wupinJuanzengService.updateById(wupinJuanzeng);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        wupinJuanzengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/shenhe/{id}/{status}")
    public R shenhe(@PathVariable("id") Long id, @PathVariable("status") String status) {
        WupinJuanzengEntity wupinJuanzeng = wupinJuanzengService.selectById(id);
        if (wupinJuanzeng == null) {
            return R.error("物品捐赠记录不存在");
        }
        wupinJuanzeng.setZhuangtai(status);
        wupinJuanzengService.updateById(wupinJuanzeng);
        return R.ok("审核操作成功");
    }
}