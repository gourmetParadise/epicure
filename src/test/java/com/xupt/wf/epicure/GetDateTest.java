package com.xupt.wf.epicure;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.service.CookBookService;
import com.xupt.wf.epicure.vo.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-16
 * Time: 19:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class GetDateTest {

    @Autowired
    private CookBookService cookBookService;

    @Test
    public void testGetData(){
        List<CookBook> list = readCSV("src/data/家常菜 - 副本1.csv");
        System.out.println(list.size());
        cookBookService.loadCookBook(list);
    }

    public static List<CookBook> readCSV(String fileName){
        List<CookBook> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            int i = 0;
            CookBook cookBook;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                int n = item.length;
                Integer count = Integer.parseInt(item[0]);
                String cookbookStep = getStep(item[1]);
                String cookImage = item[3];
                String mertials = getMaterials(item[4]);
                Float score = Float.parseFloat(item[5]);
                String cookbookName = item[6];
                String cookbookDesc;
                if(n == 8) {
                    cookbookDesc = item[7];
                } else {
                    cookbookDesc = "好看又好吃";
                }
                Integer typeId = 1;
                cookBook = new CookBook();
                cookBook.setCookbookName(cookbookName);
                cookBook.setTypeId(typeId);
                cookBook.setMaterials(mertials);
                cookBook.setCookImage(cookImage);
                cookBook.setCookbookSteps(cookbookStep);
                cookBook.setCookbookDesc(cookbookDesc);
                cookBook.setUserName("admin");
                cookBook.setCookNum(count);
                cookBook.setCookNum(count);
                cookBook.setScore(score);
                list.add(cookBook);
//                System.out.println(count + "||" + cookbookStep + "||" + cookImage + "||" + mertials + "||" + score + "||" + cookbookName + "||" + cookbookDesc + "||" + ++i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getStep(String step){
        step = step.replaceAll("'","");
        StringBuilder sb = new StringBuilder(step);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String getMaterials(String metrials){
        metrials = metrials.replaceAll("'","");
        StringBuilder sb = new StringBuilder(metrials);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        String metri[] = metrials.split("；");
        int n = metri.length;
        Ingredient ingre = null;
        List<Ingredient> list = new ArrayList<>();
        for (int i = 0; i < n; i++){
            ingre = new Ingredient();
            ingre.setSource(metri[i]);
            ingre.setAmount("适量");
            list.add(ingre);
        }
        return JSONObject.toJSONString(list);
    }

}
