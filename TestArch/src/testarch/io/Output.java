package testarch.io;

import testarch.cases.Cases;
import testarch.exception.GlobalException;

import java.io.*;

public class Output {
    private static BufferedWriter writer;

    public static BufferedWriter getWriter(String path) throws FileNotFoundException {
        if (writer != null) return writer;
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
    }

    public static void close() throws IOException {
        writer.close();
    }
}
