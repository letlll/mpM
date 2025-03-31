package com.dao;

import com.entity.JuanzengxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JuanzengxiangmuVO;
import com.entity.view.JuanzengxiangmuView;


/**
 * 捐赠项目
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengxiangmuDao extends BaseMapper<JuanzengxiangmuEntity> {
	
	List<JuanzengxiangmuVO> selectListVO(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);
	
	JuanzengxiangmuVO selectVO(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);
	
	List<JuanzengxiangmuView> selectListView(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);

	List<JuanzengxiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);

	
	JuanzengxiangmuView selectView(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);
	

}
