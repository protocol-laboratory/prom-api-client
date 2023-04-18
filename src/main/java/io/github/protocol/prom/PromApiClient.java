package io.github.protocol.prom;

import io.github.protocol.prom.module.VectorResp;

import java.io.IOException;

public interface PromApiClient {

    static PromApiClientBuilder builder() {
        return new PromApiClientBuilder();
    }

    VectorResp query(String query) throws IOException;
    
    VectorResp queryRange(String query, long startMs, long endMs, long stepMs) throws IOException;

    Double queryPulsarTotalRateIn() throws IOException;

    Double queryPulsarNamespaceRateIn(String namespace) throws IOException;

    Double queryPulsarTopicRateIn(String topic) throws IOException;

    Double queryPulsarPartitionRateIn(String partitionName) throws IOException;

    Double queryPulsarTotalRateOut() throws IOException;

    Double queryPulsarNamespaceRateOut(String namespace) throws IOException;

    Double queryPulsarTopicRateOut(String topic) throws IOException;

    Double queryPulsarPartitionRateOut(String partitionName) throws IOException;

    Double queryPulsarTopicSubscriptionRateOut(String topic, String subscription) throws IOException;

    Double queryPulsarPartitionSubscriptionRateOut(String partitionName, String subscription) throws IOException;

    Double queryPulsarTotalSubscriptionBacklog() throws IOException;

    Double queryPulsarNamespaceSubscriptionBacklog(String namespace) throws IOException;

    Double queryPulsarTopicSubscriptionBacklog(String topic, String subscription) throws IOException;

    Double queryPulsarPartitionSubscriptionBacklog(String partitionName, String subscription) throws IOException;

}
