package io.github.protocol.prom;

public class Constant {

    public final static String QUERY_TOTAL_PULSAR_RATE_IN = "sum(pulsar_rate_in)";

    public final static String QUERY_NAMESPACE_PULSAR_RATE_IN = """
            sum(pulsar_rate_in{namespace="%s"})
            """;

    public final static String QUERY_TOPIC_PULSAR_RATE_IN = """
            sum(pulsar_rate_in{topic=~".*%s-partition-.*"})
            """;

    public final static String QUERY_PARTITION_PULSAR_RATE_IN = """
            pulsar_rate_in{topic=~".*%s"}
            """;

    public final static String QUERY_TOTAL_PULSAR_RATE_OUT = "sum(pulsar_rate_out)";

    public final static String QUERY_NAMESPACE_PULSAR_RATE_OUT = """
            sum(pulsar_rate_out{namespace="%s"})
            """;

    public final static String QUERY_TOPIC_PULSAR_RATE_OUT = """
            sum(pulsar_rate_out{topic=~".*%s-partition-.*"})
            """;

    public final static String QUERY_PARTITION_PULSAR_RATE_OUT = """
            pulsar_rate_out{topic=~".*%s"}
            """;

    public final static String QUERY_TOPIC_PULSAR_SUBSCRIPTION_RATE_OUT = """
            sum(pulsar_subscription_msg_rate_out{topic=~".*%s-partition-.*",subscription="%s"})
            """;

    public final static String QUERY_PARTITION_PULSAR_SUBSCRIPTION_RATE_OUT = """
            pulsar_subscription_msg_rate_out{topic=~".*%s",subscription="%s"}
            """;

    public final static String QUERY_TOTAL_PULSAR_SUBSCRIPTION_BACKLOG = "sum(pulsar_subscription_back_log)";

    public final static String QUERY_NAMESPACE_PULSAR_SUBSCRIPTION_BACKLOG = """
            sum(pulsar_subscription_back_log{namespace="%s"})
            """;

    public final static String QUERY_TOPIC_PULSAR_SUBSCRIPTION_BACKLOG = """
            sum(pulsar_subscription_back_log{topic=~".*%s-partition-.*",subscription="%s"})
            """;

    public final static String QUERY_PARTITION_PULSAR_SUBSCRIPTION_BACKLOG = """
            pulsar_subscription_back_log{topic=~".*%s", subscription="%s"})
            """;
}
