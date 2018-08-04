package com.xzy.grpc;

import com.xzy.grpc.proto.GreeterGrpc;
import com.xzy.grpc.proto.HelloReply;
import com.xzy.grpc.proto.HelloRequest;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloWorldAsyncClient {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterStub greeterStub;

    public HelloWorldAsyncClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.greeterStub = GreeterGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void asyncGreet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        StreamObserver<HelloReply> streamObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                log.info("onNext---{}", value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                log.error("error occured,message-->", t.getMessage(), t);
            }

            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }
        };
        greeterStub.sayHello(request, streamObserver);
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldAsyncClient client = new HelloWorldAsyncClient("localhost", 50051);
        for (int i = 0; i < 100000; i++) {
            try {
                String user = "dddd" + i;
                client.asyncGreet(user);
            } catch (Exception e) {
                log.error("exception occured!", e);
            }
        }
        Thread.sleep(10000000L);
    }
}
