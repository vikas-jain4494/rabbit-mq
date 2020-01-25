package com.vikas.free;

import java.io.IOException;
import java.nio.channels.Channel;
import java.sql.Connection;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class Publisher {

    private final static String QUEUE_NAME = "products_queue";

    public static void main(String[]args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "product details";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
