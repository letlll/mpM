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


import com.dao.JuanzengquxiangDao;
import com.entity.JuanzengquxiangEntity;
import com.service.JuanzengquxiangService;
import com.entity.vo.JuanzengquxiangVO;
import com.entity.view.JuanzengquxiangView;

@Service("juanzengquxiangService")
public class JuanzengquxiangServiceImpl extends ServiceImpl<JuanzengquxiangDao, JuanzengquxiangEntity> implements JuanzengquxiangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JuanzengquxiangEntity> page = this.selectPage(
                new Query<JuanzengquxiangEntity>(params).getPage(),
                new EntityWrapper<JuanzengquxiangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JuanzengquxiangEntity> wrapper) {
		  Page<JuanzengquxiangView> page =new Query<JuanzengquxiangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JuanzengquxiangVO> selectListVO(Wrapper<JuanzengquxiangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JuanzengquxiangVO selectVO(Wrapper<JuanzengquxiangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JuanzengquxiangView> selectListView(Wrapper<JuanzengquxiangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JuanzengquxiangView selectView(Wrapper<JuanzengquxiangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
