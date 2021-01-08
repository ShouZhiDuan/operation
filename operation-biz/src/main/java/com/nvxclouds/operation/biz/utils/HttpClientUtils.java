package com.nvxclouds.operation.biz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import okhttp3.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/4 14:49
 * @Description: HttpServer
 */
@Slf4j
@Component
public class HttpClientUtils {
    /**
     * 构建Ok_Http_Client
     */
    private OkHttpClient getAsyncOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType){ }
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) { }
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new java.security.cert.X509Certificate[]{}; }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * <p style="font-size:30px;color:red">发起https调用、没有请求头Header</p>
     */
    public  String sendHttpsSyncedNoHeader(String url, String jsonBody) throws IOException {
        OkHttpClient client = getAsyncOkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(new HashMap<>()))
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String msg;
        if(response.code() == HttpsURLConnection.HTTP_OK){
            ResponseBody content=response.body();
            msg = content==null?null:content.string();
        } else {
            System.out.println("Response: " + response.code() + ":" + response.message());
            msg = "Connection Failed!";
        }
        response.close();
        return msg;
    }











}
