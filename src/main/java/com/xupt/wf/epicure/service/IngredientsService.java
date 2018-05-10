package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.entity.Ingredients;
import com.xupt.wf.epicure.mapper.IngredientsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-07
 * Time: 18:44
 */
@Service
public class IngredientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientsService.class);

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addMaterials(List<Ingredients> ingreList){
        LOGGER.info("================ingreList= " + ingreList.toString());
        boolean result = false;
        try{
            ingredientsMapper.addList(ingreList);
            result = true;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }
}
