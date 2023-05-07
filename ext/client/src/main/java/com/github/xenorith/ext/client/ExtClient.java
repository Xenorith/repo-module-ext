package com.github.xenorith.ext.client;

import com.github.xenorith.client.AbstractClient;
import com.github.xenorith.ext.grpc.HelloRequest;
import com.github.xenorith.ext.grpc.HelloResponse;
import com.github.xenorith.ext.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ExtClient extends AbstractClient {

    public ExtClient(String host, int port) {
        super(host, port);
    }

    @Override
    public void PrintName() {
        System.out.println("My name is ExtClient");
    }

    /*
    Note that although this implementation of Hello() is practically identical to BaseClient.Hello(),
    it is invoking the proto classes of the same name but built by xenorith.ext.grpc instead of xenorith.grpc.
     */
    public void Hello(String firstName, String lastName) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(mHost, mPort)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build());

        System.out.println(helloResponse);

        channel.shutdown();
    }

    public void FormalHello(String title, String lastName) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(mHost, mPort)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.formalHello(HelloRequest.newBuilder()
                .setTitle(title)
                .setLastName(lastName)
                .build());

        System.out.println(helloResponse);

        channel.shutdown();
    }
}
