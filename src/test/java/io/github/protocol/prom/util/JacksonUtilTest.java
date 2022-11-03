package io.github.protocol.prom.util;

import io.github.protocol.prom.module.VectorResp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JacksonUtilTest {

    @Test
    public void testDecodeVectorResp() {
        String src = """
                {
                "status":"success",
                "data":{
                "resultType":"vector",
                "result":
                [
                {
                "metric":{"__name__":"process_max_fds","instance":"localhost:9090","job":"prometheus"},
                "value":[1667490236.205,"1048576"]
                }
                ]
                }
                }""";
        VectorResp vectorResp = JacksonUtil.toObject(src, VectorResp.class);
        Assertions.assertNotNull(vectorResp);
        Assertions.assertEquals(1, vectorResp.getData().getResult().size());
    }

}
