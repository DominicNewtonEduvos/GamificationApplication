package com.example.cognify;

public class Milestone {
    // Display properties (for UI listing)
    private String name;
    private String description;
    private int xpReward;
    private boolean completed;
    private String status;
    
    // XP progression properties (for level system)
    private int level;
    private String title;
    private int requiredXP;
    private int maxXP;

    // Constructor for UI-based milestones (existing functionality)
    public Milestone(String name, String description, int xpReward, boolean completed, String status) {
        this.name = name;
        this.description = description;
        this.xpReward = xpReward;
        this.completed = completed;
        this.status = status;
        this.level = 0;
        this.title = name;
        this.requiredXP = 0;
        this.maxXP = 0;
    }

    // Constructor for XP-based milestones (new functionality for progression)
    public Milestone(int level, String title, int requiredXP, int maxXP) {
        this.level = level;
        this.title = title;
        this.requiredXP = requiredXP;
        this.maxXP = maxXP;
        this.name = title;
        this.description = "";
        this.xpReward = 0;
        this.completed = false;
        this.status = "";
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

    public int getXpReward() {
        return xpReward;
    }

    public void setXpReward(int xpReward) {
        this.xpReward = xpReward;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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