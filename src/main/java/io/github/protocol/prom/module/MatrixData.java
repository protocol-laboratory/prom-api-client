package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MatrixData extends PromData {

    List<MatrixResult> result;

    public MatrixData() {
    }
}
