package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class MatrixResult {

    Map<String, String> metric;

    List<List<Double>> values;

    public MatrixResult() {
    }
}
