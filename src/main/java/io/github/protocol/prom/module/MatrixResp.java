package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrixResp extends PromResp {

    private MatrixData data;

    public MatrixResp() {
    }
}
