package com.dao;

import com.entity.ShouzhuzheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShouzhuzheVO;
import com.entity.view.ShouzhuzheView;


/**
 * 受助者
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface ShouzhuzheDao extends BaseMapper<ShouzhuzheEntity> {
	
	List<ShouzhuzheVO> selectListVO(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);
	
	ShouzhuzheVO selectVO(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);
	
	List<ShouzhuzheView> selectListView(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);

	List<ShouzhuzheView> selectListView(Pagination page,@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);

	
	ShouzhuzheView selectView(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);
	

}
