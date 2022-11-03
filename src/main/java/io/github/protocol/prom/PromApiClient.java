package io.github.protocol.prom;

import io.github.protocol.prom.module.VectorResp;
import io.github.protocol.prom.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.Duration;

@Slf4j
public class PromApiClient {

    private final String promHttpPrefix;

    private final OkHttpClient client;

    public PromApiClient(String host, int port) {
        this.promHttpPrefix = "http://" + host + ":" + port;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Duration.ofMillis(30_000));
        this.client = builder.build();
    }

    public VectorResp query(String query) throws IOException {
        HttpUrl parse = HttpUrl.parse(promHttpPrefix + "/api/v1/query");
        if (parse == null) {
            throw new IllegalArgumentException("parse url error");
        }
        HttpUrl.Builder urlBuilder = parse.newBuilder();
        urlBuilder.addQueryParameter("query", query);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();
        Response response = this.client.newCall(request).execute();
        ResponseBody body = response.body();
        String content = body == null ? "" : body.string();
        if (!response.isSuccessful()) {
            log.error("query prometheus failed, response code is {} msg {}", response.code(), content);
        }
        return JacksonUtil.toObject(content, VectorResp.class);
    }

}
