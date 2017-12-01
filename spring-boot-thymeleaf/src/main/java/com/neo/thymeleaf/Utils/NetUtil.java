package com.neo.thymeleaf.Utils;

/**
 * Created by jason on 2017/11/29.
 */

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jason on 15-4-20.
 */
public class NetUtil {
    public static String GET(String url){
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod(url);

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                return "Method failed: " + method.getStatusLine();
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            return new String(responseBody);

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
        return null;
    }
	//POST请求
    public static String POST(String url,Map<String,Object> dataForm){
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(url);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        if(dataForm!=null){
            Set<String> keys = dataForm.keySet();
            for(String key:keys){
                NameValuePair nameValuePair = new NameValuePair(key, (String) dataForm.get(key));
                data.add(nameValuePair);
            }
        }
        post.setRequestBody(data.toArray(new NameValuePair[0]));
        try {
            int statusCode = httpClient.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                return "Method failed: " + post.getStatusLine();
            }

            // Read the response body.
            byte[] responseBody = post.getResponseBody();
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            return new String(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            post.releaseConnection();
        }
        return null;
    }

	//DElETE请求
    public static String DELETE(String url,Map<String,Object> dataForm){
        HttpClient httpClient = new HttpClient();
        DeleteMethod deleteMethod = new DeleteMethod(url);

        List<NameValuePair> data = new ArrayList<NameValuePair>();
        if(dataForm!=null){
            Set<String> keys = dataForm.keySet();
            for(String key:keys){
                NameValuePair nameValuePair = new NameValuePair(key, (String) dataForm.get(key));
                data.add(nameValuePair);
            }
        }
        deleteMethod.setQueryString(data.toArray(new NameValuePair[0]));
        try {
            int statusCode = httpClient.executeMethod(deleteMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return "Method failed: " + deleteMethod.getStatusLine();
            }

            // Read the response body.
            byte[] responseBody = deleteMethod.getResponseBody();
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            return new String(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            deleteMethod.releaseConnection();
        }
        return null;
    }
	//PUT请求
    public static String PUT(String put_url, NameValuePair[] params) throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();
        PutMethod httpPut = new PutMethod(put_url);
        httpPut.setQueryString(params);   //以键值对形式传递参数
        httpPut.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        httpClient.executeMethod(httpPut);
        String res = httpPut.getResponseBodyAsString();
        return res;
    }
}

