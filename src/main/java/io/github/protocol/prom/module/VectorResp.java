package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VectorResp extends PromResp {

    private VectorData data;

    public VectorResp() {
    }
}
