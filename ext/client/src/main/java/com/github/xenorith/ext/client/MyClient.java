package com.github.xenorith.ext.client;

public final class MyClient {
    public static void main(String[] args) {
        ExtClient extClient = new ExtClient("localhost", 8000);
        extClient.PrintName();
        if (args.length == 1 && args[0].equals("formal")) {
            extClient.FormalHello("Ms", "bar2");
        } else {
            extClient.Hello("foo", "bar2");
        }
    }
}
