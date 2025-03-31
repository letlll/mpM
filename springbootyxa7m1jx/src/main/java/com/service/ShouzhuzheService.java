package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShouzhuzheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShouzhuzheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShouzhuzheView;


/**
 * 受助者
 *
 * @author 
 * @email 
 * @date 2025-03-15 10:58:20
 */
public interface ShouzhuzheService extends IService<ShouzhuzheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShouzhuzheVO> selectListVO(Wrapper<ShouzhuzheEntity> wrapper);
   	
   	ShouzhuzheVO selectVO(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);
   	
   	List<ShouzhuzheView> selectListView(Wrapper<ShouzhuzheEntity> wrapper);
   	
   	ShouzhuzheView selectView(@Param("ew") Wrapper<ShouzhuzheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShouzhuzheEntity> wrapper);

   	

}

