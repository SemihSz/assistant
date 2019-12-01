package com.spring.assistant.assistant.todo.shared.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
@Component
@NoArgsConstructor
public class GenerateNumberUtil {

    private  final Random random = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGHT = 256;


    public String generateUserId(int lenght){
        return generateRandomString(lenght);
    }
    private String generateRandomString(int lenght){
        StringBuilder retrun= new StringBuilder(lenght);
        for(int i = 0; i<lenght; i++){
            retrun.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return new String(retrun);
    }
    public String generateSubTaskId(int lenght){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("subtask=?");
        stringBuilder.append(generateRandomString(lenght));
        return String.valueOf(stringBuilder);
    }

    public String generateCommentId() {
        return generateRandomString(12);
    }
}
