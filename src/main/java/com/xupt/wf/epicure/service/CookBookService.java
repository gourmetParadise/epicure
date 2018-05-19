package com.xupt.wf.epicure.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.Ingredients;
import com.xupt.wf.epicure.mapper.CookBookMapper;
import com.xupt.wf.epicure.mapper.IngredientsMapper;
import com.xupt.wf.epicure.vo.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-05
 * Time: 14:47
 */
@Service
public class CookBookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookBookService.class);

    @Autowired
    private CookBookMapper cookBookMapper;

    @Autowired
    private IngredientsService ingreService;

    @Autowired
    private IngredientsMapper ingreMapper;


    @Transactional
    public Map<String, Object> addCookImage(CookBook cookBook){
        Map<String, Object> resultMap = new HashMap<>();
        cookBookMapper.addCookBook(cookBook);
        int cookbookId = cookBook.getCookbookId();
        if(cookbookId > 0){
            resultMap.put("cookbookId", cookbookId);
            String cookImage = cookBookMapper.getImagePath(cookbookId);
            resultMap.put("cookImage", cookImage);
        }
        return resultMap;
    }

    @Transactional
    public boolean addCookBook(CookBook cookBook){
        boolean result = false;
        int effect = cookBookMapper.updateCookBook(cookBook);
        if(effect == 1){
            //用户上传菜谱数加一
            cookBookMapper.increCookCount(cookBook.getUserName());
            List<Ingredients> ingreList = listMaterials(cookBook);
            int count = ingreList.size();
            if(count == ingreMapper.addList(ingreList)){
                result = true;
            }
        }
        return result;
    }

    @Transactional
    public boolean loadCookBook(List<CookBook> list){
        boolean result = false;
        for (CookBook cookBook : list) {
            int effect = cookBookMapper.loadCookBook(cookBook);
            if(effect == 1){
                //用户上传菜谱数加一
                cookBookMapper.increCookCount(cookBook.getUserName());
                List<Ingredients> ingreList = listMaterials(cookBook);
                int count = ingreList.size();
                if(count == ingreMapper.addList(ingreList)){
                    result = true;
                }
            }
        }
        return result;
    }


    @Transactional
    public int updateImage(Integer cookbookId, String imagePath){
        return cookBookMapper.updateImage(cookbookId, imagePath);
    }

    @Transactional
    public boolean updateCookBook(CookBook cookBook){
        boolean result = false;
        int effect = cookBookMapper.updateCookBook(cookBook);
        if(effect == 1){
            List<Ingredients> ingreList = listMaterials(cookBook);
            int count = ingreList.size();
            if(count == ingreMapper.updateIngre(cookBook.getCookbookId())){
                if(count == ingreMapper.addList(ingreList)){
                    result = true;
                }
            }
        }
        return result;
    }

    public CookBook queryInfoById(Integer cookbookId){
        CookBook cookBook = cookBookMapper.queryById(cookbookId);
        String materials = cookBook.getMaterials();
        cookBook.setIngreList(listMaterials(materials));
        return cookBook;
    }

    public int increReadCount(Integer cookbookId){
        return cookBookMapper.increReadCount(cookbookId);
    }

    public List<CookBook> queryListByName(String userName){
        return cookBookMapper.queryListByName(userName);
    }

    public List<CookBook> queryList(){
        return cookBookMapper.queryList();
    }

    @Transactional
    public boolean deleteCookBook(Integer cookbookId, String userName){
        boolean result = false;
        int count = cookBookMapper.deleteCookBook(cookbookId, userName);
        if(count == 1 ){
            //用户上传菜谱数加一
            cookBookMapper.decreCookCount(userName);
            ingreMapper.deleteIngre(cookbookId);
            result = true;
        }
        return result;
    }

    public List<Ingredient> listMaterials(String materials){
        LOGGER.info("====================materials= " + materials);
        JSONArray jsonArray = JSONArray.parseArray(materials);
        Iterator it = jsonArray.iterator();
        List<Ingredient> ingreList = new ArrayList<>();
        while(it.hasNext()){
            JSONObject material = (JSONObject) it.next();
            Ingredient ingre = new Ingredient();
            ingre.setSource(material.getString("source"));
            ingre.setAmount(material.getString("amount"));
            ingreList.add(ingre);
        }
        return ingreList;
    }

    public List<Ingredients> listMaterials(CookBook cookBook){
        String materials = cookBook.getMaterials();
        JSONArray jsonArray = JSONArray.parseArray(materials);
        Iterator it = jsonArray.iterator();
        List<Ingredients> ingreList = new ArrayList<>();
        while(it.hasNext()){
            Ingredients ingre = new Ingredients();
            JSONObject material = (JSONObject) it.next();
            ingre.setCookbookId(cookBook.getCookbookId());
            ingre.setCookbookName(cookBook.getCookbookName());
            ingre.setSource(material.getString("source"));
            ingre.setAmount(material.getString("amount"));
            ingreList.add(ingre);
        }
        return ingreList;
    }
}
