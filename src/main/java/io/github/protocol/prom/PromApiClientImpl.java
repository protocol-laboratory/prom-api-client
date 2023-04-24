package io.github.protocol.prom;

import io.github.protocol.prom.module.MatrixResp;
import io.github.protocol.prom.module.VectorResp;
import io.github.protocol.prom.module.VectorResult;
import io.github.protocol.prom.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Slf4j
public class PromApiClientImpl implements PromApiClient {

    private final String promHttpPrefix;

    private final OkHttpClient client;

    PromApiClientImpl(String host, int port) {
        this.promHttpPrefix = "http://" + host + ":" + port;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Duration.ofMillis(30_000));
        this.client = builder.build();
    }

    // only for testing
    PromApiClientImpl() {
        this.promHttpPrefix = null;
        this.client = null;
    }


    @Override
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
        try (Response response = this.client.newCall(request).execute()) {
            ResponseBody body = response.body();
            String content = body == null ? "" : body.string();
            if (!response.isSuccessful()) {
                log.error("query prometheus failed, response code is {} msg {}", response.code(), content);
                throw new IOException(String.format("query prometheus failed, error: %s", content));
            }
            return JacksonUtil.toObject(content, VectorResp.class);
        }
    }

    @Override
    public MatrixResp queryRange(String query, long startMs, long endMs, int stepSec) throws IOException {
        HttpUrl parse = HttpUrl.parse(promHttpPrefix + "/api/v1/query_range");
        if (parse == null) {
            throw new IllegalArgumentException("parse url error");
        }
        String startTime = new StringBuilder(String.valueOf(startMs)).insert(10, ".").toString();
        String endTime = new StringBuilder(String.valueOf(endMs)).insert(10, ".").toString();
        HttpUrl requestUrl = parse.newBuilder()
                .addQueryParameter("query", query)
                .addQueryParameter("start", startTime)
                .addQueryParameter("end", endTime)
                .addQueryParameter("step", String.valueOf(stepSec))
                .build();
        log.info(String.valueOf(requestUrl));
        Request request = new Request.Builder()
                .url(requestUrl)
                .build();
        try (Response response = this.client.newCall(request).execute()) {
            final ResponseBody body = response.body();
            String content = body == null ? "" : body.string();
            if (!response.isSuccessful()) {
                log.error("query prometheus failed, response code is {} msg {}", response.code(), content);
                throw new IOException(String.format("query prometheus failed, err: %s", content));
            }
            return JacksonUtil.toObject(content, MatrixResp.class);
        }
    }

    @Override
    public Double queryPulsarTotalRateIn() throws IOException {
        VectorResp resp = query(PromQlConstant.QUERY_PULSAR_TOTAL_RATE_IN);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new RuntimeException("internal error");
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarNamespaceRateIn(String namespace) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_RATE_IN, namespace);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("namespace %s invalid", namespace));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTopicRateIn(String topic) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_TOPIC_RATE_IN, topic);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("topic %s invalid", topic));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarPartitionRateIn(String partitionName) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_PARTITION_RATE_IN, partitionName);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("partition %s invalid", partitionName));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTotalRateOut() throws IOException {
        VectorResp resp = query(PromQlConstant.QUERY_PULSAR_TOTAL_RATE_OUT);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new RuntimeException("internal error");
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarNamespaceRateOut(String namespace) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_RATE_OUT, namespace);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("namespace %s invalid", namespace));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTopicRateOut(String topic) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_TOPIC_RATE_OUT, topic);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("topic %s invalid", topic));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarPartitionRateOut(String partitionName) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_PARTITION_RATE_OUT, partitionName);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("partition %s invalid", partitionName));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTopicSubscriptionRateOut(String topic, String subscription) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_TOPIC_SUBSCRIPTION_RATE_OUT, topic, subscription);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("topic %s or subscription %s invalid",
                    topic, subscription));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarPartitionSubscriptionRateOut(String partitionName, String subscription)
            throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_PARTITION_SUBSCRIPTION_RATE_OUT,
                partitionName, subscription);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("partitionName %s or subscription %s invalid",
                    partitionName, subscription));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTotalSubscriptionBacklog() throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_TOTAL_SUBSCRIPTION_BACKLOG);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new RuntimeException("internal error");
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarNamespaceSubscriptionBacklog(String namespace) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_SUBSCRIPTION_BACKLOG, namespace);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("namespace %s invalid", namespace));
        }
        return result.get(0).getValue().get(1);
    }

    @Override
    public Double queryPulsarTopicSubscriptionBacklog(String topic, String subscription) throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_TOPIC_PULSAR_SUBSCRIPTION_BACKLOG, topic, subscription);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("topic %s or subscription %s invalid",
                    topic, subscription));
        }
        return result.get(0).getValue().get(1);
    }


    @Override
    public Double queryPulsarPartitionSubscriptionBacklog(String partitionName, String subscription)
            throws IOException {
        String promQl = String.format(PromQlConstant.QUERY_PARTITION_PULSAR_SUBSCRIPTION_BACKLOG,
                partitionName, subscription);
        VectorResp resp = query(promQl);
        List<VectorResult> result = resp.getData().getResult();
        if (result.size() < 1) {
            throw new IllegalArgumentException(String.format("partitionName %s or subscription %s invalid",
                    partitionName, subscription));
        }
        return result.get(0).getValue().get(1);
    }
}
