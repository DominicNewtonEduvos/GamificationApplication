package com.example.cognify;

import java.util.ArrayList;

public class MilestoneManager {

    public ArrayList<Milestone> getAllMilestones() {
        ArrayList<Milestone> milestones = new ArrayList<>();
        milestones.add(new Milestone(1, "Beginner", 0, 200));
        milestones.add(new Milestone(2, "Learner", 200, 500));
        milestones.add(new Milestone(3, "Advanced Learner", 500, 1000));
        milestones.add(new Milestone(4, "Expert", 1000, 2000));
        milestones.add(new Milestone(5, "Master", 2000, Integer.MAX_VALUE));
        return milestones;
    }

    public Milestone getCurrentMilestone(int currentXP) {
        for (Milestone milestone : getAllMilestones()) {
            if (currentXP >= milestone.getRequiredXP() && currentXP < milestone.getMaxXP()) {
                return milestone;
            }
        }
        return getAllMilestones().get(0);
    }

    public int calculateProgress(int currentXP, Milestone milestone) {
        int min = milestone.getRequiredXP();
        int maxExclusive = milestone.getMaxXP();
        int range = Math.max(1, maxExclusive - min);
        int progress = (int) (((double) (currentXP - min) / range) * 100.0);
        if (progress < 0) return 0;
        if (progress > 100) return 100;
        return progress;
    }
}