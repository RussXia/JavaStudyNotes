package com.xzy.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.xzy.grpc.proto.GreeterGrpc;
import com.xzy.grpc.proto.HelloReply;
import com.xzy.grpc.proto.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloWorldFutureClient {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterFutureStub futureStub;


    public HelloWorldFutureClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        futureStub = GreeterGrpc.newFutureStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greetFuture(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        ListenableFuture<HelloReply> listenableFuture = futureStub.sayHello(request);
        listenableFuture.addListener(() -> {
            try {
                log.info("future--message:{}", listenableFuture.get().getMessage());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, Executors.newFixedThreadPool(60));
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldFutureClient client = new HelloWorldFutureClient("localhost", 50051);
        for (int i = 0; i < 10000; i++) {
            client.greetFuture("www" + i);
        }
        Thread.sleep(10000000L);

    }
}
