package ua.hudyma;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadSafeReader {
    private final BufferedReader reader;
    protected ThreadSafeReader(BufferedReader reader) {
        this.reader = reader;
    }
    protected synchronized String readLine() throws IOException {
        return reader.readLine();
    }
}