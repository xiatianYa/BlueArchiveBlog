package com.blue.common.core.enums;

public enum ExchangeStatus {
    SMS_EXCHANGE_SEND("blueArchive.smsTopic","smsTopic","sms.send");

    private final String exchangeName;
    private final String queue;
    private final String routingKey;

    ExchangeStatus(String exchangeName, String queue, String routingKey) {
        this.exchangeName=exchangeName;
        this.queue=queue;
        this.routingKey=routingKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getQueue() {
        return queue;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
