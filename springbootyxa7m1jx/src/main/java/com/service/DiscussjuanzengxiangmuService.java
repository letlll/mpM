package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussjuanzengxiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussjuanzengxiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussjuanzengxiangmuView;


/**
 * 捐赠项目评论表
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:21
 */
public interface DiscussjuanzengxiangmuService extends IService<DiscussjuanzengxiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjuanzengxiangmuVO> selectListVO(Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
   	
   	DiscussjuanzengxiangmuVO selectVO(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
   	
   	List<DiscussjuanzengxiangmuView> selectListView(Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
   	
   	DiscussjuanzengxiangmuView selectView(@Param("ew") Wrapper<DiscussjuanzengxiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjuanzengxiangmuEntity> wrapper);

   	

}

