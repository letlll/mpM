package com.dao;

import com.entity.ShouzhushenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShouzhushenqingVO;
import com.entity.view.ShouzhushenqingView;


/**
 * 受助申请
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface ShouzhushenqingDao extends BaseMapper<ShouzhushenqingEntity> {
	
	List<ShouzhushenqingVO> selectListVO(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);
	
	ShouzhushenqingVO selectVO(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);
	
	List<ShouzhushenqingView> selectListView(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);

	List<ShouzhushenqingView> selectListView(Pagination page,@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);

	
	ShouzhushenqingView selectView(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);
	

}
