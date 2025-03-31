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


import com.dao.ShouzhushenqingDao;
import com.entity.ShouzhushenqingEntity;
import com.service.ShouzhushenqingService;
import com.entity.vo.ShouzhushenqingVO;
import com.entity.view.ShouzhushenqingView;

@Service("shouzhushenqingService")
public class ShouzhushenqingServiceImpl extends ServiceImpl<ShouzhushenqingDao, ShouzhushenqingEntity> implements ShouzhushenqingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShouzhushenqingEntity> page = this.selectPage(
                new Query<ShouzhushenqingEntity>(params).getPage(),
                new EntityWrapper<ShouzhushenqingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShouzhushenqingEntity> wrapper) {
		  Page<ShouzhushenqingView> page =new Query<ShouzhushenqingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShouzhushenqingVO> selectListVO(Wrapper<ShouzhushenqingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShouzhushenqingVO selectVO(Wrapper<ShouzhushenqingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShouzhushenqingView> selectListView(Wrapper<ShouzhushenqingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShouzhushenqingView selectView(Wrapper<ShouzhushenqingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
