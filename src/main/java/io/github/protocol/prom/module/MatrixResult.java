package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class MatrixResult {

    Map<String, String> metric;

    List<List<Double>> values;

    public MatrixResult() {
    }
}
