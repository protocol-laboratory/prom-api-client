package io.github.protocol.prom.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class KeyValueResp extends PromResp {

    List<Map<String, String>> data;

    public KeyValueResp() {
    }
}
