package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.WupinJuanzengEntity;
import com.utils.PageUtils;
import java.util.Map;

public interface WupinJuanzengService extends IService<WupinJuanzengEntity> {
    PageUtils queryPage(Map<String, Object> params);
}