package com.example.cognifyjavafiles;

public class Badge {
    private String badgeName;
    private String description;
    private int requiredXP;
    private boolean isEarned;

    public Badge(String badgeName, String description, int requiredXP, boolean isEarned) {
        this.badgeName = badgeName;
        this.description = description;
        this.requiredXP = requiredXP;
        this.isEarned = isEarned;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredXP() {
        return requiredXP;
    }

    public void setRequiredXP(int requiredXP) {
        this.requiredXP = requiredXP;
    }

    public boolean isEarned() {
        return isEarned;
    }

    public void setEarned(boolean earned) {
        isEarned = earned;
    }
}


