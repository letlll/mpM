package com.dao;

import com.entity.DiscussjuanzengxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussjuanzengxiangmuVO;
import com.entity.view.DiscussjuanzengxiangmuView;


/**
 * 捐赠项目评论表
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:21
 */
public interface DiscussjuanzengxiangmuDao extends BaseMapper<DiscussjuanzengxiangmuEntity> {
	
	List<DiscussjuanzengxiangmuVO> selectListVO(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
	
	DiscussjuanzengxiangmuVO selectVO(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
	
	List<DiscussjuanzengxiangmuView> selectListView(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);

	List<DiscussjuanzengxiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);

	
	DiscussjuanzengxiangmuView selectView(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
	

}
