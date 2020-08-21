package com.yash.onsite3;

import androidx.annotation.Nullable;

public class pathDetails {

    private String name;
    private String path;
    private int img;
    private boolean isOpen;

    public pathDetails(String name, String path, boolean isOpen, int img) {
        this.name = name;
        this.path = path;
        this.isOpen = isOpen;
        this.img = img;
    }

    public pathDetails(String name, String path, boolean isOpen) {
        this.name = name;
        this.isOpen = isOpen;
        this.path = path;
    }

    public void InvertArrow() {
        if (isOpen)
            img = R.drawable.ic_open;
        else
            img = R.drawable.ic_closed;
    }

    public void InvertStatus() {
        isOpen = !isOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
