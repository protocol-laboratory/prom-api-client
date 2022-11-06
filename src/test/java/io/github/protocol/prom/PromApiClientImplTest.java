package io.github.protocol.prom;

import io.github.protocol.prom.module.VectorResp;
import io.github.protocol.prom.util.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PromApiClientImplTest {

    private PromApiClient promApiClient = Mockito.spy(new PromApiClientImpl());

    @Test
    public void queryPulsarTotalRateInTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667722837.195,
                                    "100.0"
                                ]
                            }
                        ]
                    }
                }
                """;

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(PromQlConstant.QUERY_PULSAR_TOTAL_RATE_IN);
        Double result = promApiClient.queryPulsarTotalRateIn();
        Assertions.assertEquals(100.0, result);
    }


    @Test
    public void queryPulsarTotalNamespaceRateInTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667728283.263,
                                    "100.0"
                                ]
                            }
                        ]
                    }
                }
                """;
        String namespace = "public/default";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_RATE_IN, namespace));
        Double result = promApiClient.queryPulsarNamespaceRateIn(namespace);
        Assertions.assertEquals(100.0, result);
    }


    @Test
    public void queryPulsarTotalTopicRateInTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667728972.628,
                                    "100.1"
                                ]
                            }
                        ]
                    }
                }
                """;
        String topic = "public/default/test";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_TOPIC_RATE_IN, topic));
        Double result = promApiClient.queryPulsarTopicRateIn(topic);
        Assertions.assertEquals(100.1, result);
    }


    @Test
    public void queryPulsarPartitionRateInTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{
                                    "__name__":"pulsar_rate_in",
                                    "cluster":"standalone",
                                    "instance":"192.168.31.33:8080",
                                    "job":"pulsar",
                                    "namespace":"public/default",
                                    "topic":"persistent://public/default/test-partition-0"
                                },
                                "value":[
                                    1667729254.325,
                                    "5.25"
                                ]
                            }
                        ]
                    }
                }
                """;
        String partition = "public/default/test-partition-0";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_PARTITION_RATE_IN, partition));
        Double result = promApiClient.queryPulsarPartitionRateIn(partition);
        Assertions.assertEquals(5.25, result);
    }

    @Test
    public void queryPulsarTotalRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667729538.124,
                                    "100.098"
                                ]
                            }
                        ]
                    }
                }
                """;

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(PromQlConstant.QUERY_PULSAR_TOTAL_RATE_OUT);
        Double result = promApiClient.queryPulsarTotalRateOut();
        Assertions.assertEquals(100.098, result);
    }

    @Test
    public void queryPulsarTotalNamespaceRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667729538.124,
                                    "50.098"
                                ]
                            }
                        ]
                    }
                }
                """;
        String namespace = "public/default";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_RATE_OUT, namespace));
        Double result = promApiClient.queryPulsarNamespaceRateOut(namespace);
        Assertions.assertEquals(50.098, result);
    }

    @Test
    public void queryPulsarTotalTopicRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667729538.124,
                                    "10.098"
                                ]
                            }
                        ]
                    }
                }
                """;
        String topic = "public/default/test";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_TOPIC_RATE_OUT, topic));
        Double result = promApiClient.queryPulsarTopicRateOut(topic);
        Assertions.assertEquals(10.098, result);
    }

    @Test
    public void queryPulsarTotalPartitionRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{
                                    "__name__":"pulsar_rate_out",
                                    "cluster":"standalone",
                                    "instance":"192.168.31.33:8080",
                                    "job":"pulsar",
                                    "namespace":"public/default",
                                    "topic":"persistent://public/default/test-partition-0"
                                },
                                "value":[
                                    1667730133.437,
                                    "1.183"
                                ]
                            }
                        ]
                    }
                }
                """;
        String partition = "persistent://public/default/test-partition-0";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_PARTITION_RATE_OUT, partition));
        Double result = promApiClient.queryPulsarPartitionRateOut(partition);
        Assertions.assertEquals(1.183, result);
    }


    @Test
    public void queryPulsarTopicSubscriptionRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667730765.836,
                                    "1.099"
                                ]
                            }
                        ]
                    }
                }
                """;
        String topic = "public/default/test";
        String subscription = "sub-test";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_TOPIC_SUBSCRIPTION_RATE_OUT, topic, subscription));
        Double result = promApiClient.queryPulsarTopicSubscriptionRateOut(topic, subscription);
        Assertions.assertEquals(1.099, result);
    }

    @Test
    public void queryPulsarPartitionSubscriptionRateOutTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{
                                    "__name__":"pulsar_subscription_msg_rate_out",
                                    "cluster":"standalone",
                                    "instance":"192.168.31.33:8080",
                                    "job":"pulsar",
                                    "namespace":"public/default",
                                    "subscription":"sub-test",
                                    "topic":"persistent://public/default/test-partition-0"
                                },
                                "value":[
                                    1667731044.082,
                                    "0.233"
                                ]
                            }
                        ]
                    }
                }
                """;
        String partitionName = "public/default/test";
        String subscription = "sub-test";

        String promQL = String.format(PromQlConstant.QUERY_PULSAR_PARTITION_SUBSCRIPTION_RATE_OUT, partitionName,
                subscription);
        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(promQL);
        Double result = promApiClient.queryPulsarPartitionSubscriptionRateOut(partitionName, subscription);
        Assertions.assertEquals(0.233, result);
    }

    @Test
    public void queryPulsarTotalSubscriptionBacklogTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667731992.904,
                                    "0"
                                ]
                            }
                        ]
                    }
                }
                """;

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(PromQlConstant.QUERY_PULSAR_TOTAL_SUBSCRIPTION_BACKLOG);
        Double result = promApiClient.queryPulsarTotalSubscriptionBacklog();
        Assertions.assertEquals(0, result);
    }

    @Test
    public void queryPulsarTotalNamespaceSubscriptionBacklogTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667731992.904,
                                    "0"
                                ]
                            }
                        ]
                    }
                }
                """;

        String namespace = "public/default";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PULSAR_NAMESPACE_SUBSCRIPTION_BACKLOG, namespace));
        Double result = promApiClient.queryPulsarNamespaceSubscriptionBacklog(namespace);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void queryPulsarTotalTopicSubscriptionBacklogTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{},
                                "value":[
                                    1667732586.703,
                                    "0"
                                ]
                            }
                        ]
                    }
                }
                """;

        String topic = "public/default/test";
        String subscription = "sub-test";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_TOPIC_PULSAR_SUBSCRIPTION_BACKLOG, topic, subscription));
        Double result = promApiClient.queryPulsarTopicSubscriptionBacklog(topic, subscription);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void queryPulsarPartitionSubscriptionBacklogTest() throws IOException {
        String promRep = """
                {
                    "status":"success",
                    "data":{
                        "resultType":"vector",
                        "result":[
                            {
                                "metric":{
                                    "__name__":"pulsar_subscription_back_log",
                                    "cluster":"standalone",
                                    "instance":"192.168.31.33:8080",
                                    "job":"pulsar",
                                    "namespace":"public/default",
                                    "subscription":"sub-test",
                                    "topic":"persistent://public/default/test-partition-0"
                                },
                                "value":[
                                    1667732767.92,
                                    "0"
                                ]
                            }
                        ]
                    }
                }
                """;

        String partitionName = "public/default/test";
        String subscription = "sub-test";

        VectorResp vectorResp = JacksonUtil.toObject(promRep, VectorResp.class);
        Mockito.doReturn(vectorResp).when(promApiClient).query(
                String.format(PromQlConstant.QUERY_PARTITION_PULSAR_SUBSCRIPTION_BACKLOG, partitionName, subscription));
        Double result = promApiClient.queryPulsarPartitionSubscriptionBacklog(partitionName, subscription);
        Assertions.assertEquals(0, result);
    }
}
