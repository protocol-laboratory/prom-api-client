package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromResp {

    private String status;

    public PromResp() {
    }

    @Override
    public String toString() {
        return status;
    }
}
