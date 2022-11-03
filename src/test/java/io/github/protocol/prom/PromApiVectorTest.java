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
            .waitingFor(new HttpWaitStrategy().forPath("/metrics"))
            .withExposedPorts(9090);

    @Test
    public void testPrometheusGauge() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(16);
        PromApiClient promApiClient = new PromApiClient("localhost", prom.getFirstMappedPort());
        VectorResp vectorResp = promApiClient.query("process_max_fds");
        Assertions.assertNotNull(vectorResp);
        Assertions.assertEquals(1, vectorResp.getData().getResult().size());
    }

}
