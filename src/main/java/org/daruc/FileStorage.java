package org.daruc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileStorage implements Storage {

    private final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        try (var fileInputStream = new FileInputStream(filePath)) {
            byte[] allBytes = fileInputStream.readAllBytes();
            return new String(allBytes, StandardCharsets.UTF_8);
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public void write(String str) {
        try (var fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }
}
