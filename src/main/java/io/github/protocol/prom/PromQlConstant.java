package io.github.protocol.prom;

public class PromQlConstant {

    public static final String QUERY_PULSAR_TOTAL_RATE_IN = "sum(pulsar_rate_in)";

    public static final String QUERY_PULSAR_NAMESPACE_RATE_IN = """
            sum(pulsar_rate_in{namespace="%s"})
            """;

    public static final String QUERY_PULSAR_TOPIC_RATE_IN = """
            sum(pulsar_rate_in{topic=~".*%s-partition-.*"})
            """;

    public static final String QUERY_PULSAR_PARTITION_RATE_IN = """
            pulsar_rate_in{topic=~".*%s"}
            """;

    public static final String QUERY_PULSAR_TOTAL_RATE_OUT = "sum(pulsar_rate_out)";

    public static final String QUERY_PULSAR_NAMESPACE_RATE_OUT = """
            sum(pulsar_rate_out{namespace="%s"})
            """;

    public static final String QUERY_PULSAR_TOPIC_RATE_OUT = """
            sum(pulsar_rate_out{topic=~".*%s-partition-.*"})
            """;

    public static final String QUERY_PULSAR_PARTITION_RATE_OUT = """
            pulsar_rate_out{topic=~".*%s"}
            """;

    public static final String QUERY_PULSAR_TOPIC_SUBSCRIPTION_RATE_OUT = """
            sum(pulsar_subscription_msg_rate_out{topic=~".*%s-partition-.*",subscription="%s"})
            """;

    public static final String QUERY_PULSAR_PARTITION_SUBSCRIPTION_RATE_OUT = """
            pulsar_subscription_msg_rate_out{topic=~".*%s",subscription="%s"}
            """;

    public static final String QUERY_PULSAR_TOTAL_SUBSCRIPTION_BACKLOG = "sum(pulsar_subscription_back_log)";

    public static final String QUERY_PULSAR_NAMESPACE_SUBSCRIPTION_BACKLOG = """
            sum(pulsar_subscription_back_log{namespace="%s"})
            """;

    public static final String QUERY_TOPIC_PULSAR_SUBSCRIPTION_BACKLOG = """
            sum(pulsar_subscription_back_log{topic=~".*%s-partition-.*",subscription="%s"})
            """;

    public static final String QUERY_PARTITION_PULSAR_SUBSCRIPTION_BACKLOG = """
            pulsar_subscription_back_log{topic=~".*%s", subscription="%s"})
            """;
}
