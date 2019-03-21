package org.its.bus;

@FunctionalInterface
public interface MessageConsumer {
    void handle(BusMessage messageType) throws Exception;
}
