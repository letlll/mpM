package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussjuanzengxiangmuDao;
import com.entity.DiscussjuanzengxiangmuEntity;
import com.service.DiscussjuanzengxiangmuService;
import com.entity.vo.DiscussjuanzengxiangmuVO;
import com.entity.view.DiscussjuanzengxiangmuView;

@Service("discussjuanzengxiangmuService")
public class DiscussjuanzengxiangmuServiceImpl extends ServiceImpl<DiscussjuanzengxiangmuDao, DiscussjuanzengxiangmuEntity> implements DiscussjuanzengxiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussjuanzengxiangmuEntity> page = this.selectPage(
                new Query<DiscussjuanzengxiangmuEntity>(params).getPage(),
                new EntityWrapper<DiscussjuanzengxiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussjuanzengxiangmuEntity> wrapper) {
		  Page<DiscussjuanzengxiangmuView> page =new Query<DiscussjuanzengxiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussjuanzengxiangmuVO> selectListVO(Wrapper<DiscussjuanzengxiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussjuanzengxiangmuVO selectVO(Wrapper<DiscussjuanzengxiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussjuanzengxiangmuView> selectListView(Wrapper<DiscussjuanzengxiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussjuanzengxiangmuView selectView(Wrapper<DiscussjuanzengxiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
