package org.hospital.hospitalbookup.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "smartcash_session";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_ROLE = "user_role"; // Added for role

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // Updated login to accept role
    public void login(String email, String role) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.contains(KEY_EMAIL);
    }

    public String getUserEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }

    public String getUserRole() {
        return prefs.getString(KEY_ROLE, null);
    }
}
