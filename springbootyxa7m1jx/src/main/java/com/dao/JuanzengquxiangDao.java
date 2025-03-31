package com.dao;

import com.entity.JuanzengquxiangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JuanzengquxiangVO;
import com.entity.view.JuanzengquxiangView;


/**
 * 捐赠去向
 * 
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengquxiangDao extends BaseMapper<JuanzengquxiangEntity> {
	
	List<JuanzengquxiangVO> selectListVO(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);
	
	JuanzengquxiangVO selectVO(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);
	
	List<JuanzengquxiangView> selectListView(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);

	List<JuanzengquxiangView> selectListView(Pagination page,@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);

	
	JuanzengquxiangView selectView(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);
	

}
