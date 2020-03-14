package com.kaixin8848.home.utility.sms;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author tianyh
 * @Description:HTTP 请求
 */
public class ChuangLanSmsUtil {
    /**
     *
     * @author tianyh @Description @param path @param postContent @return
     *         String @throws
     */
    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);// 连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);// 读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            // PrintWriter printWriter = new
            // PrintWriter(httpURLConnection.getOutputStream());
            // printWriter.write(postContent);
            // printWriter.flush();

            httpURLConnection.connect();
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] doImgPost(String url, Map<String, Object> map) {
        byte[] result = null;

        // 使用HTTPPost方法访问获取二维码链接url
        HttpPost httpPost = new HttpPost(url);
        // 创建http连接客户端
        CloseableHttpClient client = HttpClients.createDefault();
        // 设置头响应类型
        httpPost.addHeader("Content-Type", "application/json");

        try {
            // 设置请求的参数
            JSONObject postData = new JSONObject();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                postData.put(entry.getKey(), entry.getValue());
            }
            httpPost.setEntity(new StringEntity(postData.toString(), "UTF-8"));
            // 返回的post请求结果
            HttpResponse response = client.execute(httpPost);
            org.apache.http.HttpEntity entity = response.getEntity();
            result = EntityUtils.toByteArray(entity);
        } catch (Exception ex) {
            
        }
        // 最后转成2进制图片流
        return result;
    }
}
