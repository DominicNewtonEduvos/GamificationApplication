package com.example.cognify;

public class MaterialAdapter {
    public void notifyDataSetChanged() {
    }

    public static abstract class OnMaterialActionListener {
        public abstract void onViewMaterial(StudyMaterial material);

        public abstract void onApproveMaterial(StudyMaterial material);

        public abstract void onRejectMaterial(StudyMaterial material);

        public abstract void onDeleteMaterial(StudyMaterial material);
    }
}
