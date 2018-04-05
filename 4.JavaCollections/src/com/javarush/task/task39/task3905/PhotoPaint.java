package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int r, int c, Color desiredColor) {
        if (c >= image.length || r >= image[c].length) return false;
        if (image[c][r].equals(desiredColor)) return false;
        image[c][r] = desiredColor;
        return true;
    }
}
