package com.github.xenorith.ext.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class MyServer {
    public static void main(String[] args) throws Exception  {
        ExtServer extServer = new ExtServer(ServerBuilder.forPort(8000)
                .addService(new HelloServiceImpl())
                .build());
        extServer.PrintName();
        extServer.PrintFormalName();
        extServer.StartAndWait();
    }
}
