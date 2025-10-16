package com.example.cognifyjavafiles;

public class Badge {
    private String name;
    private String description;
    private int iconResource;
    private boolean earned;

    public Badge(String name, String description, int iconResource, boolean earned) {
        this.name = name;
        this.description = description;
        this.iconResource = iconResource;
        this.earned = earned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public boolean isEarned() {
        return earned;
    }

    public void setEarned(boolean earned) {
        this.earned = earned;
    }
}
