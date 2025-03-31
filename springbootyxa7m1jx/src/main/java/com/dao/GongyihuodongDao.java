package com.dao;

import com.entity.GongyihuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GongyihuodongVO;
import com.entity.view.GongyihuodongView;


/**
 * 公益活动
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface GongyihuodongDao extends BaseMapper<GongyihuodongEntity> {
	
	List<GongyihuodongVO> selectListVO(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);
	
	GongyihuodongVO selectVO(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);
	
	List<GongyihuodongView> selectListView(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);

	List<GongyihuodongView> selectListView(Pagination page,@Param("ew") Wrapper<GongyihuodongEntity> wrapper);

	
	GongyihuodongView selectView(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);
	

}
