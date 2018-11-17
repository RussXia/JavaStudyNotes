package com.xzy.grpc;

import com.xzy.grpc.proto.GreeterGrpc;
import com.xzy.grpc.proto.HelloReply;
import com.xzy.grpc.proto.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloWorldBlockClient {

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    private HelloWorldBlockClient(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public HelloWorldBlockClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        log.info("send greet", name);
        HelloRequest helloRequest = HelloRequest.newBuilder().setName(name).build();

        HelloReply reply = blockingStub.sayHello(helloRequest);

        log.info("reply message : {}", reply);
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldBlockClient client = new HelloWorldBlockClient("localhost", 50051);
        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 100000; i++) {
                try {
                    String user = "qqq" + i;
                    client.greet(user);
                } catch (Exception e) {
                    log.error("exception occured!", e);
                }
            }
        } finally {
            client.shutdown();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
