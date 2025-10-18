package com.example.cognify;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GetUsernameTemp {

    private static final String TAG = "Getuid";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();

    private static String usernme;

    public static boolean haveuid = false;

    /**
     * A callback interface to handle the asynchronous result of fetching the uid.
     */
    public interface FirestoreuidCallback {
        /**
         * Called when the uid is successfully retrieved.
         * @param uid The uid fetched from the database.
         */
        void onCallback(String uid);

        /**
         * Called when an error occurs.
         * @param e The exception that occurred.
         */
        void onError(Exception e);
    }

    /**
     * Loads the uid for the currently authenticated user from the "users" collection.
     * @param callback The callback to be invoked with the result.
     */
    public static void loaduid(@NonNull final FirestoreuidCallback callback) {
        FirebaseUser currentUser = auth.getCurrentUser();

        // 1. Check if a user is actually logged in.
        if (currentUser == null) {
            String errorMessage = "No authenticated user found.";
            Log.e(TAG, errorMessage);
            callback.onError(new Exception(errorMessage));
            return;
        }

        String userUid = currentUser.getUid();

        // 2. Create a query to find the document in the "users" collection
        //    where the 'uid' field matches the current user's UID.
        db.collection("users")
                .whereEqualTo("uid", userUid)
                .limit(1) // We only expect one document per user, so we limit the result to 1.
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();

                        // 3. Check if the query returned any documents.
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            // Since we limited to 1, we can just get the first document.
                            QueryDocumentSnapshot document = (QueryDocumentSnapshot) querySnapshot.getDocuments().get(0);
                            String uid = document.getString("uid");

                            if (uid != null && !uid.isEmpty()) {
                                Log.i(TAG, "uid found: " + uid);
                                usernme = uid;
                                // 4. Success! Use the callback to return the uid.
                                callback.onCallback(uid);
                            } else {
                                String errorMessage = "uid field is null or empty in the document.";
                                Log.e(TAG, errorMessage);
                                callback.onError(new Exception(errorMessage));
                            }
                        } else {
                            // This means no document was found for the current user's UID.
                            String errorMessage = "User document not found in Firestore for UID: " + userUid;
                            Log.w(TAG, errorMessage);
                            callback.onError(new Exception(errorMessage));
                        }
                    } else {
                        // This handles failures in the task itself (e.g., network issues, permissions).
                        Log.e(TAG, "Error querying for uid.", task.getException());
                        callback.onError(task.getException());
                    }
                });
    }

    public static String getuid(){
        return usernme;
    }

}


