package com.example.ProjectNova.Nova.Service;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
;


import javax.annotation.PostConstruct;
import java.net.URI;


@Service
public class AWSInitializer {
    private static DynamoDbClient client;
    private static DynamoDbEnhancedClient enhancedClient;
    private static S3Client s3;

    @PostConstruct
    public void startAws() {
        Region region=Region.US_WEST_2;
//        client = DynamoDbClient.builder()
//                .region(region)
//                .build();
        s3 = S3Client.builder().region(region).build();
        client = DynamoDbClient.builder()
                .region(region)
                .build();

        enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }

    public static DynamoDbClient getClient() {
        return client;
    }
    public static S3Client getS3() {
        return s3;
    }

    public static DynamoDbEnhancedClient getEnhancedClient() {
        return enhancedClient;
    }
}
