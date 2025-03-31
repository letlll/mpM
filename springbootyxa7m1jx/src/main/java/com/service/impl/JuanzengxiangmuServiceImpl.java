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


import com.dao.JuanzengxiangmuDao;
import com.entity.JuanzengxiangmuEntity;
import com.service.JuanzengxiangmuService;
import com.entity.vo.JuanzengxiangmuVO;
import com.entity.view.JuanzengxiangmuView;

@Service("juanzengxiangmuService")
public class JuanzengxiangmuServiceImpl extends ServiceImpl<JuanzengxiangmuDao, JuanzengxiangmuEntity> implements JuanzengxiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JuanzengxiangmuEntity> page = this.selectPage(
                new Query<JuanzengxiangmuEntity>(params).getPage(),
                new EntityWrapper<JuanzengxiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JuanzengxiangmuEntity> wrapper) {
		  Page<JuanzengxiangmuView> page =new Query<JuanzengxiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JuanzengxiangmuVO> selectListVO(Wrapper<JuanzengxiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JuanzengxiangmuVO selectVO(Wrapper<JuanzengxiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JuanzengxiangmuView> selectListView(Wrapper<JuanzengxiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JuanzengxiangmuView selectView(Wrapper<JuanzengxiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
