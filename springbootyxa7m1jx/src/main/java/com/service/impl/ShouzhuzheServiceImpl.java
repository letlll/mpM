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


import com.dao.ShouzhuzheDao;
import com.entity.ShouzhuzheEntity;
import com.service.ShouzhuzheService;
import com.entity.vo.ShouzhuzheVO;
import com.entity.view.ShouzhuzheView;

@Service("shouzhuzheService")
public class ShouzhuzheServiceImpl extends ServiceImpl<ShouzhuzheDao, ShouzhuzheEntity> implements ShouzhuzheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShouzhuzheEntity> page = this.selectPage(
                new Query<ShouzhuzheEntity>(params).getPage(),
                new EntityWrapper<ShouzhuzheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShouzhuzheEntity> wrapper) {
		  Page<ShouzhuzheView> page =new Query<ShouzhuzheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShouzhuzheVO> selectListVO(Wrapper<ShouzhuzheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShouzhuzheVO selectVO(Wrapper<ShouzhuzheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShouzhuzheView> selectListView(Wrapper<ShouzhuzheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShouzhuzheView selectView(Wrapper<ShouzhuzheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
