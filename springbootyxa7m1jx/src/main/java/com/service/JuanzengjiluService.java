package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JuanzengjiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JuanzengjiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JuanzengjiluView;


/**
 * 捐赠记录
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface JuanzengjiluService extends IService<JuanzengjiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JuanzengjiluVO> selectListVO(Wrapper<JuanzengjiluEntity> wrapper);
   	
   	JuanzengjiluVO selectVO(@Param("ew") Wrapper<JuanzengjiluEntity> wrapper);
   	
   	List<JuanzengjiluView> selectListView(Wrapper<JuanzengjiluEntity> wrapper);
   	
   	JuanzengjiluView selectView(@Param("ew") Wrapper<JuanzengjiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JuanzengjiluEntity> wrapper);

   	

}

