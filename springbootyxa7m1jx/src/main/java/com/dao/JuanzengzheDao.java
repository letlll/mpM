package com.dao;

import com.entity.JuanzengzheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JuanzengzheVO;
import com.entity.view.JuanzengzheView;


/**
 * 捐赠者
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengzheDao extends BaseMapper<JuanzengzheEntity> {
	
	List<JuanzengzheVO> selectListVO(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);
	
	JuanzengzheVO selectVO(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);
	
	List<JuanzengzheView> selectListView(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);

	List<JuanzengzheView> selectListView(Pagination page,@Param("ew") Wrapper<JuanzengzheEntity> wrapper);

	
	JuanzengzheView selectView(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);
	

}
