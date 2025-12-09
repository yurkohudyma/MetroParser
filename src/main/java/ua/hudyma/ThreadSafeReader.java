package ua.hudyma;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadSafeReader {
    private final BufferedReader reader;
    public ThreadSafeReader(BufferedReader reader) {
        this.reader = reader;
    }
    public synchronized String readLine() throws IOException {
        return reader.readLine();
    }
}