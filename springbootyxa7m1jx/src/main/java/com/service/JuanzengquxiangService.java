package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JuanzengquxiangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JuanzengquxiangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JuanzengquxiangView;


/**
 * 捐赠去向
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengquxiangService extends IService<JuanzengquxiangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JuanzengquxiangVO> selectListVO(Wrapper<JuanzengquxiangEntity> wrapper);
   	
   	JuanzengquxiangVO selectVO(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);
   	
   	List<JuanzengquxiangView> selectListView(Wrapper<JuanzengquxiangEntity> wrapper);
   	
   	JuanzengquxiangView selectView(@Param("ew") Wrapper<JuanzengquxiangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JuanzengquxiangEntity> wrapper);

   	

}

