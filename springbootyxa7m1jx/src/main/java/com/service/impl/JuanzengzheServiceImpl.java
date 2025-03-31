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


import com.dao.JuanzengzheDao;
import com.entity.JuanzengzheEntity;
import com.service.JuanzengzheService;
import com.entity.vo.JuanzengzheVO;
import com.entity.view.JuanzengzheView;

@Service("juanzengzheService")
public class JuanzengzheServiceImpl extends ServiceImpl<JuanzengzheDao, JuanzengzheEntity> implements JuanzengzheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JuanzengzheEntity> page = this.selectPage(
                new Query<JuanzengzheEntity>(params).getPage(),
                new EntityWrapper<JuanzengzheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JuanzengzheEntity> wrapper) {
		  Page<JuanzengzheView> page =new Query<JuanzengzheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JuanzengzheVO> selectListVO(Wrapper<JuanzengzheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JuanzengzheVO selectVO(Wrapper<JuanzengzheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JuanzengzheView> selectListView(Wrapper<JuanzengzheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JuanzengzheView selectView(Wrapper<JuanzengzheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
