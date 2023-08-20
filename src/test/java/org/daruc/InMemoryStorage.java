package org.daruc;

public class InMemoryStorage implements Storage {

    private String memory;

    @Override
    public String read() {
        return memory;
    }

    @Override
    public void write(String str) {
        memory = str;
    }
}