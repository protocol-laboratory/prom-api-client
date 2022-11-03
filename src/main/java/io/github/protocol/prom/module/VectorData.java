package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class VectorData extends PromData {

    List<VectorResult> result;

    public VectorData() {
    }
}
