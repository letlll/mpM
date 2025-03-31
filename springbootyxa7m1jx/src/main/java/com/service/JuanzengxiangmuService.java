package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JuanzengxiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JuanzengxiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JuanzengxiangmuView;


/**
 * 捐赠项目
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengxiangmuService extends IService<JuanzengxiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JuanzengxiangmuVO> selectListVO(Wrapper<JuanzengxiangmuEntity> wrapper);
   	
   	JuanzengxiangmuVO selectVO(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);
   	
   	List<JuanzengxiangmuView> selectListView(Wrapper<JuanzengxiangmuEntity> wrapper);
   	
   	JuanzengxiangmuView selectView(@Param("ew") Wrapper<JuanzengxiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JuanzengxiangmuEntity> wrapper);

   	

}

