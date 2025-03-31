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


import com.dao.GongyihuodongDao;
import com.entity.GongyihuodongEntity;
import com.service.GongyihuodongService;
import com.entity.vo.GongyihuodongVO;
import com.entity.view.GongyihuodongView;

@Service("gongyihuodongService")
public class GongyihuodongServiceImpl extends ServiceImpl<GongyihuodongDao, GongyihuodongEntity> implements GongyihuodongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GongyihuodongEntity> page = this.selectPage(
                new Query<GongyihuodongEntity>(params).getPage(),
                new EntityWrapper<GongyihuodongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GongyihuodongEntity> wrapper) {
		  Page<GongyihuodongView> page =new Query<GongyihuodongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<GongyihuodongVO> selectListVO(Wrapper<GongyihuodongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GongyihuodongVO selectVO(Wrapper<GongyihuodongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GongyihuodongView> selectListView(Wrapper<GongyihuodongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GongyihuodongView selectView(Wrapper<GongyihuodongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
