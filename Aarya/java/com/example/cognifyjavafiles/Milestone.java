package com.example.cognifyjavafiles;

public class Milestone {
    private String name;
    private String description;
    private int xpReward;
    private boolean completed;
    private String status;

    public Milestone(String name, String description, int xpReward, boolean completed, String status) {
        this.name = name;
        this.description = description;
        this.xpReward = xpReward;
        this.completed = completed;
        this.status = status;
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
}
