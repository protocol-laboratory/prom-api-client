package io.github.protocol.prom;

import io.github.protocol.prom.module.MatrixResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;

@Slf4j
public class PromApiMatrixTest {

    @Container
    private static final GenericContainer prom = new GenericContainer("prom/prometheus:v2.2.1")
            .waitingFor(new HttpWaitStrategy().forPath("/api/v1/query?query=process_max_fds")
                    .forResponsePredicate(s -> !s.contains("\"result\":[]")))
            .withExposedPorts(9090);

    @BeforeAll
    public static void init() {
        prom.start();
    }

    @Test
    public void testPrometheusQueryRange() throws IOException {
        PromApiClient promApiClient = PromApiClient.builder().host("localhost").port(prom.getFirstMappedPort()).build();
        MatrixResp processMaxFds = promApiClient.queryRange(
                "process_max_fds", System.currentTimeMillis() - 12000, System.currentTimeMillis()
                        + 60000, 1);
        Assertions.assertNotNull(processMaxFds);
        log.info("MatrixRangeResp: {}", processMaxFds);
        Assertions.assertTrue(processMaxFds.getData().getResult().size() > 0);
    }

}
