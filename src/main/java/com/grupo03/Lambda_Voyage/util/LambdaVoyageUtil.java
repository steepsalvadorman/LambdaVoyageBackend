package com.grupo03.Lambda_Voyage.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class LambdaVoyageUtil {

    private static final Random random = new Random();

    public static LocalDateTime getRandomSoon(){
        var randomHours = random.nextInt(5-2)+2;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

    public static LocalDateTime getRandomLater(){
        var randomHours = random.nextInt(12-6)+6;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

    public static void writeNotification(String text, String path) throws IOException {
        var fileWriter = new FileWriter(path, true);
        var bufferWriter = new BufferedWriter(fileWriter);
        try(fileWriter; bufferWriter){
            bufferWriter.write(text);
            bufferWriter.newLine();
        }
    }
}
