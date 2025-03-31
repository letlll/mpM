package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GongyihuodongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GongyihuodongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GongyihuodongView;


/**
 * 公益活动
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface GongyihuodongService extends IService<GongyihuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GongyihuodongVO> selectListVO(Wrapper<GongyihuodongEntity> wrapper);
   	
   	GongyihuodongVO selectVO(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);
   	
   	List<GongyihuodongView> selectListView(Wrapper<GongyihuodongEntity> wrapper);
   	
   	GongyihuodongView selectView(@Param("ew") Wrapper<GongyihuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GongyihuodongEntity> wrapper);

   	

}

