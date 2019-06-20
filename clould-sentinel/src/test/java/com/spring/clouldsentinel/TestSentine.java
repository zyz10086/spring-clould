package com.spring.clouldsentinel;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSentine {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        while(true){
            executorService.execute(new Send());
        }
    }

}
class Send implements Runnable{

    @Override
    public void run() {
        try{
            HttpClient httpClient= HttpClients.createDefault();
            HttpGet post = new HttpGet("http://127.0.0.1:8081//sentinel/hello");

            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                String str=EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(str);
            }else{
                String str=EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println("响应码:"+response.getStatusLine().getStatusCode()+str);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
