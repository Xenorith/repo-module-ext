package com.github.xenorith.ext.server;

import com.github.xenorith.server.AbstractServer;
import io.grpc.Server;

public class ExtServer extends AbstractServer {

    public ExtServer(Server server) {
        super(server);
    }

    @Override
    public void PrintName() {
        System.out.println("My name is ExtServer");
    }

    public void PrintFormalName() {
        System.out.println("My formal name is com.github.xenorith.ext.server.ExtServer");
    }
}
