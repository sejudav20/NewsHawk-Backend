package com.example.ProjectNova.Nova.Model;

import com.example.ProjectNova.Nova.Service.AWSInitializer;
import com.example.ProjectNova.Nova.Service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.*;

public class MyUserDetails implements UserDetails {
    private String username;
    //Holds password,roles, and usernames for auth
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles= new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return roles;

    }
    public MyUserDetails(String username){
        this.username = username;
    }

    @Override
    public String getPassword() {
        DynamoDbClient ddc = AWSInitializer.getClient();
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("name", AttributeValue.builder()
                .s(username)
                .build());
        GetItemRequest gir = GetItemRequest.builder()
                .projectionExpression("password")
                .key(keyMap)
                .tableName("Users")
                .build();
        String password = "";
        try {
            Map<String, AttributeValue> results = ddc.getItem(gir).item();
            if (!results.isEmpty()) {

                System.out.println(results);

                password = results.get("password").s();
            }
        } catch (DynamoDbException e) {
            throw new UsernameNotFoundException("Username not found");
        }
        return password;

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
