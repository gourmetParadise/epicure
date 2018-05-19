package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.Const.MaterilalsFilter;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.UserHistory;
import com.xupt.wf.epicure.mapper.UserHistoryMapper;
import com.xupt.wf.epicure.vo.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
