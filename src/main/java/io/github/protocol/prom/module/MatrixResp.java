package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatrixResp extends PromResp {

    private MatrixData data;

    public MatrixResp() {
    }
}
