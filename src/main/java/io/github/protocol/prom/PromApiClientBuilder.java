package io.github.protocol.prom;

public class PromApiClientBuilder {

    private String host;

    private int port;

    public PromApiClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    public PromApiClientBuilder port(int port) {
        this.port = port;
        return this;
    }

    public PromApiClient build() {
        if (this.host == null || this.port == 0) {
            throw new IllegalArgumentException("host or port is invalid");
        }
        return new PromApiClientImpl(host, port);
    }
}
