package com.example.POS_System.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorFileLogger {
    private final PrintWriter writer;

    public ErrorFileLogger() throws IOException {
        writer = new PrintWriter(new FileWriter("error-log.txt", true));
    }

    public void log(Exception e) {
        writer.println("----- ERROR -----");
        writer.println(LocalDateTime.now());
        writer.println(e.getMessage());
        e.printStackTrace(writer);
        writer.println();
        writer.flush();
    }
}

