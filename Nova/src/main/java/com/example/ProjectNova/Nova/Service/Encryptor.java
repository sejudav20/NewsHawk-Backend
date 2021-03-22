package com.example.ProjectNova.Nova.Service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Encryptor {

    public static String Encrypt(String s) {
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }
}
