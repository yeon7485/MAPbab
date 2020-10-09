package com.kplo.mapbab.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String userID;
    public String userPW;

    public User(){

    }
    public User(String userID, String userPW) {
        this.userID = userID;
        this.userPW = userPW;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userPW='" + userPW + '\'' +
                '}';
    }
}
