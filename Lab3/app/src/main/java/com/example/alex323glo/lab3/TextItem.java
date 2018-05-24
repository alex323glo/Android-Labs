package com.example.alex323glo.lab3;

public class TextItem {

    private float size;
    private String value;

    public TextItem() {
    }

    public TextItem(float size, String value) {
        this.size = size;
        this.value = value;
    }

    public float getSize() {
        return size;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '\"' + value + "\" (size: " + size + ")";
    }
}
