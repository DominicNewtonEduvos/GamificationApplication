package com.example.cognify;

public class Badge_UI {
    private String name;
    private String description;
    private int iconResource;

    private int requiredXP;
    private boolean earned;
    public Badge_UI(String name, String description, int iconResource, boolean earned) {
        this.name = name;
        this.description = description;
        this.iconResource = iconResource;
        this.earned = earned;
        this.requiredXP = 0;
    }

    // Getters and Setters
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

    public int getRequiredXP() {
        return requiredXP;
    }

    public void setRequiredXP(int requiredXP) {
        this.requiredXP = requiredXP;
    }

    public boolean isEarned() {
        return earned;
    }

    public void setEarned(boolean earned) {
        this.earned = earned;
    }
}
