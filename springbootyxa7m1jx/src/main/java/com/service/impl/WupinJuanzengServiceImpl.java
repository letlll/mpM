package com.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.WupinJuanzengDao;
import com.entity.WupinJuanzengEntity;
import com.service.WupinJuanzengService;
import com.utils.PageUtils;
import com.utils.Query;
import java.util.Map;

@Service("wupinJuanzengService")
public class WupinJuanzengServiceImpl extends ServiceImpl<WupinJuanzengDao, WupinJuanzengEntity> implements WupinJuanzengService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WupinJuanzengEntity> page = this.selectPage(
            new Query<WupinJuanzengEntity>(params).getPage(),
            new EntityWrapper<WupinJuanzengEntity>()
        );
        return new PageUtils(page);
    }
}