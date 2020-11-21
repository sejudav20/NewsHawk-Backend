package com.example.ProjectNova.Nova.Service;

import java.util.Date;
import java.util.UUID;
import java.sql.Timestamp;


public class IdService {
    public static String getId(){
        return UUID.randomUUID().toString();
    };
    public static Date getTimeStamp(){
        return new Date(new Timestamp(new Date().getTime()).getTime());
    }
}
