package com.example.ProjectNova.Nova.Service;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


import javax.annotation.PostConstruct;
import java.net.URI;


@Service
public class AWSInitializer {
    private static DynamoDbClient client;

    @PostConstruct
    public void startAws(){
//       Region region=Region.US_WEST_2;
//        client = DynamoDbClient.builder()
//                .region(region)
//                .build();

   client = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
   
    }
    public static DynamoDbClient getClient(){
        return client;
    }
}
