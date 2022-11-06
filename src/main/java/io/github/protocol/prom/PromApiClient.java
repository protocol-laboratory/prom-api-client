package io.github.protocol.prom;

import io.github.protocol.prom.module.VectorResp;

import java.io.IOException;

public interface PromApiClient {

    static PromApiClientBuilder builder() {
        return new PromApiClientBuilder();
    }

    VectorResp query(String query) throws IOException;

    Double queryTotalPulsarRateIn() throws IOException;

    Double queryNamespacePulsarRateIn(String namespace) throws IOException;

    Double queryTopicPulsarRateIn(String topic) throws IOException;

    Double queryPartitionPulsarRateIn(String partitionName) throws IOException;

    Double queryTotalPulsarRateOut() throws IOException;

    Double queryNamespacePulsarRateOut(String namespace) throws IOException;

    Double queryTopicPulsarRateOut(String topic) throws IOException;

    Double queryPartitionPulsarRateOut(String partitionName) throws IOException;

    Double queryTopicPulsarSubscriptionRateOut(String topic, String subscription) throws IOException;

    Double queryPartitionPulsarSubscriptionRateOut(String partitionName, String subscription) throws IOException;

    Double queryTotalPulsarSubscriptionBacklog() throws IOException;

    Double queryNamespacePulsarSubscriptionBacklog(String namespace) throws IOException;

    Double queryTopicPulsarSubscriptionBacklog(String topic, String subscription) throws IOException;

    Double queryPartitionPulsarSubscriptionBacklog(String partitionName, String subscription) throws IOException;

}
