package com.example.grpcgradle;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GrpcClientTest {

    @Test
    public void testClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("0.0.0.0", 9000)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Poor")
                .setLastName("Jerry")
                .build());

        System.out.println(helloResponse.getGreeting());
        channel.shutdown();
    }

}
