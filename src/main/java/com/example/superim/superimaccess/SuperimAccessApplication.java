package com.example.superim.superimaccess;

import com.example.superim.superimaccess.bootstrap.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperimAccessApplication implements CommandLineRunner {

    @Autowired
    NettyServer nettyServer;

    @Value("${netty.port}")
    private int nettyPort;

    public static void main(String[] args) {
        SpringApplication.run(SuperimAccessApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start(nettyPort);
    }

}
