package io.github.protocol.prom;

import io.github.protocol.prom.module.VectorResp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Testcontainers
class PromApiVectorTest {

    @Container
    private static final GenericContainer prom = new GenericContainer("prom/prometheus:v2.2.1")
            .waitingFor(new HttpWaitStrategy().forPath("/api/v1/query?query=process_max_fds")
                    .forResponsePredicate(s -> !s.contains("\"result\":[]")))
            .withExposedPorts(9090);

    @Test
    public void testPrometheusGauge() throws IOException {
        PromApiClient promApiClient = PromApiClient.builder().host("localhost").port(prom.getFirstMappedPort()).build();
        VectorResp vectorResp = promApiClient.query("process_max_fds");
        Assertions.assertNotNull(vectorResp);
        System.out.println(vectorResp);
        Assertions.assertEquals(1, vectorResp.getData().getResult().size());
    }

    @Test
    public void testPrometheusQueryRange() throws IOException {
        PromApiClient promApiClient = PromApiClient.builder().host("localhost").port(prom.getFirstMappedPort()).build();
        long now = System.currentTimeMillis();
        VectorResp vectorResp = promApiClient.queryRange("process_max_fds", now - TimeUnit.SECONDS.toMillis(10), now, 100);
        Assertions.assertNotNull(vectorResp);
        System.out.println(vectorResp);
        Assertions.assertEquals(1, vectorResp.getData().getResult().size());
    }

}
