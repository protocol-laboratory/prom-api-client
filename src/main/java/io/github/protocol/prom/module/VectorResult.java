package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class VectorResult {

    private Map<String, String> metric;

    private List<Double> value;

    public VectorResult() {
    }

    @Override
    public String toString() {
        return "VectorResult{"
                + "metric=" + metric
                + ", value=" + value
                + '}';
    }
}
