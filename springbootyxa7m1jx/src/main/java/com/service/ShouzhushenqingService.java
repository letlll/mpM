package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShouzhushenqingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShouzhushenqingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShouzhushenqingView;


/**
 * 受助申请
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface ShouzhushenqingService extends IService<ShouzhushenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShouzhushenqingVO> selectListVO(Wrapper<ShouzhushenqingEntity> wrapper);
   	
   	ShouzhushenqingVO selectVO(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);
   	
   	List<ShouzhushenqingView> selectListView(Wrapper<ShouzhushenqingEntity> wrapper);
   	
   	ShouzhushenqingView selectView(@Param("ew") Wrapper<ShouzhushenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShouzhushenqingEntity> wrapper);

   	

}

