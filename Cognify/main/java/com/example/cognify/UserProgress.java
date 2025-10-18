package com.example.cognify;

public class UserProgress {
    private static int currentXP = 350;

    public static int getCurrentXP() {
        return currentXP;
    }

    public static void setCurrentXP(int xp) {
        if (xp < 0) {
            currentXP = 0;
        } else {
            currentXP = xp;
        }
    }

    public static void addXP(int amount) {
        if (amount <= 0) return;
        currentXP += amount;
    }
}