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


import com.dao.JuanzengjiluDao;
import com.entity.JuanzengjiluEntity;
import com.service.JuanzengjiluService;
import com.entity.vo.JuanzengjiluVO;
import com.entity.view.JuanzengjiluView;

@Service("juanzengjiluService")
public class JuanzengjiluServiceImpl extends ServiceImpl<JuanzengjiluDao, JuanzengjiluEntity> implements JuanzengjiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JuanzengjiluEntity> page = this.selectPage(
                new Query<JuanzengjiluEntity>(params).getPage(),
                new EntityWrapper<JuanzengjiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JuanzengjiluEntity> wrapper) {
		  Page<JuanzengjiluView> page =new Query<JuanzengjiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JuanzengjiluVO> selectListVO(Wrapper<JuanzengjiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JuanzengjiluVO selectVO(Wrapper<JuanzengjiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JuanzengjiluView> selectListView(Wrapper<JuanzengjiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JuanzengjiluView selectView(Wrapper<JuanzengjiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
