package com.example.cognifyjavafiles;

public class Milestone {
    private int level;
    private String title;
    private int requiredXP;
    private int maxXP;

    public Milestone(int level, String title, int requiredXP, int maxXP) {
        this.level = level;
        this.title = title;
        this.requiredXP = requiredXP;
        this.maxXP = maxXP;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public int getRequiredXP() {
        return requiredXP;
    }

    public int getMaxXP() {
        return maxXP;
    }
}


