package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.Const.MaterilalsFilter;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.UserHistory;
import com.xupt.wf.epicure.mapper.UserHistoryMapper;
import com.xupt.wf.epicure.vo.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-15
 * Time: 22:37
 */

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryMapper userHistoryMapper;

    public boolean addHistory(CookBook cookBook, String userName){
        boolean result = false;
        UserHistory userHistory = new UserHistory();
        userHistory.setCookbookId(cookBook.getCookbookId());
        userHistory.setCookbookName(cookBook.getCookbookName());
        List<Ingredient> list = cookBook.getIngreList();
        if(null != list) {
            userHistory.setMaterials(listMaterilas(list));
        }
        userHistory.setUserName(userName);
        userHistory.setTypeId(cookBook.getTypeId());
        int count = userHistoryMapper.addUserHistory(userHistory);
        if(count == 1) {
            result = true;
        }
        return result;
    }

    public List<UserHistory> queryHistory(String userName){
        return userHistoryMapper.queryListByName(userName);
    }

    //给某个用户做推荐
    public List<CookBook> recommend(String userName){

        //获取用户历史记录
        List<UserHistory> historyList = queryHistory(userName);

        Set<Integer> typeSet = new HashSet<>();
        Set<Integer> idSet = new HashSet<>();
        Set<String> nameSet = new HashSet<>();
        Set<String> materialsSet = new HashSet<>();
        Map<Integer, String> idName = new HashMap<>();

        System.out.println("=============" + historyList.toString());

        for (UserHistory userHistory: historyList) {
            int typeId = userHistory.getTypeId();
            int cookbookId = userHistory.getCookbookId();
            String cookbookName = userHistory.getCookbookName();
            typeSet.add(typeId);
            idSet.add(cookbookId);
            idName.put(cookbookId, cookbookName);
            nameSet.add(cookbookName);
            String[] materials = userHistory.getMaterials().split(",");
            int n = materials.length;
            for (int i = 0; i < n; i++){
                materialsSet.add(materials[i]);
            }
        }
        System.out.println("UserHistoryService.recommend = " + typeSet.size());
        System.out.println("===========" + materialsSet.toString());

        //TODO 1、根据类别推荐
        List<CookBook> list = recommendByType(typeSet, idSet);
        if(null != list){
            return list;
        } else {
            //再用户为访问过的菜谱中，随机推荐
            return null;
        }
    }

    // 1、根据类别推荐
    public List<CookBook> recommendByType(Set<Integer> typeSet, Set<Integer> idSet){
        List<Integer> typeList = new ArrayList<>(typeSet);
        System.out.println("=================" + typeList.size());
        System.out.println("=================" + typeList.toString());
        List<CookBook> list = userHistoryMapper.recommendByType(typeList);
        System.out.println("===============" + list.size());
        System.out.println("===============" + list.toString());
        Iterator<CookBook> it =  list.iterator();
        while (it.hasNext()) {
            CookBook cookBook = it.next();
            if(idSet.contains(cookBook.getCookbookId())){
                it.remove();
            }
        }
        return list;
    }

    public String listMaterilas(List<Ingredient> materList){
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingre : materList) {
            String source =  ingre.getSource();
            if(isContain(source)){
                continue;
            }
            sb.append(source).append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static boolean isContain(String source){
        boolean result = false;
        String mFilter[] = MaterilalsFilter.filter;
        int n = mFilter.length;
        for(int i = 0; i < n; i++){
            if(source.indexOf(mFilter[i]) != -1){
                result = true;
                break;
            }
        }
        return result;
    }
}
