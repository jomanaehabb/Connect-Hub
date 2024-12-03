package com.mycompany.lab9;

import java.awt.Image;

public class InternalContent {
    private String text;
    private Image image;

    public InternalContent(String text, Image image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
