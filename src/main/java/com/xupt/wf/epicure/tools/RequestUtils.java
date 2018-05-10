package com.xupt.wf.epicure.tools;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-25
 * Time: 17:05
 */
public class RequestUtils {

    /**
     * 获取请求的 body
     * @param req
     * @return
     * @throws IOException
     */
    public synchronized static String getRequestBody(HttpServletRequest req){
        StringBuffer requestBody = null;
        try {
            BufferedReader reader = req.getReader();    //获取请求体的输入流
            String input = null;
            requestBody = new StringBuffer();
            while ((input = reader.readLine()) != null) {
                requestBody.append(input);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return requestBody.toString();
    }
}
