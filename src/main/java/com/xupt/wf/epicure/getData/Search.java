package com.xupt.wf.epicure.getData;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-13
 * Time: 8:51
 */


import java.net.URLEncoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class Search {

    public static final String APPKEY = "39de8346518bf354";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/recipe/search";
    public static final String keyword = "白菜";// utf-8
    public static final int num = 10;
    public static final RestTemplate restTemplate;

    static {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(3000);
        httpRequestFactory.setConnectTimeout(3000);
        httpRequestFactory.setReadTimeout(3000);
        restTemplate = new RestTemplate(httpRequestFactory);
    }


    public static void Get() throws Exception {
        String result = null;
        String url = URL + "?keyword=" + URLEncoder.encode(keyword, "utf-8") + "&num=" + num + "&appkey=" + APPKEY;

        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = JSONObject.fromObject(response);
            if (json.getInt("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = json.optJSONObject("result");
                String num = resultarr.getString("num");
                System.out.println(num);
                if (resultarr.opt("list") != null) {
                    JSONArray list = resultarr.optJSONArray("list");
                    for (int j = 0; j < list.size(); j++) {
                        JSONObject list_ = (JSONObject) list.opt(j);
                        String id = list_.getString("id");
                        String classid = list_.getString("classid");
                        String name = list_.getString("name");
                        String peoplenum = list_.getString("peoplenum");
                        String preparetime = list_.getString("preparetime");
                        String cookingtime = list_.getString("cookingtime");
                        String content = list_.getString("content");
                        String pic = list_.getString("pic");
                        String tag = list_.getString("tag");
                        System.out.println(id + " " + classid + " " + name + " " + peoplenum + " " + preparetime + " "
                                + cookingtime + " " + content + " " + pic + " " + tag);
                        if (list_.opt("material") != null) {
                            JSONArray material = list_.optJSONArray("material");
                            for (int i = 0; i < material.size(); i++) {
                                JSONObject obj = (JSONObject) material.opt(i);
                                String mname = obj.getString("mname");
                                String type = obj.getString("type");
                                String amount = obj.getString("amount");
                                System.out.println(mname + " " + type + " " + amount);
                            }
                        }
                        if (list_.opt("process") != null) {
                            JSONArray process = list_.optJSONArray("process");
                            for (int i = 0; i < process.size(); i++) {
                                JSONObject obj = (JSONObject) process.opt(i);
                                String pcontent = obj.getString("pcontent");
                                String pic_ = obj.getString("pic");
                                System.out.println(pcontent + " " + pic_);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
