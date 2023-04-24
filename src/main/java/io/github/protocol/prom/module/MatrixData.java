package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class MatrixData extends PromData {

    List<MatrixResult> result;

    public MatrixData() {
    }
}
