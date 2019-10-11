package com.siit.JourneyPlanApp.Model;

public class AppUser {

    private String userName;
    private String encrytedPassword;

    public AppUser() {

    }

    public AppUser(String userName, String encrytedPassword) {
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    @Override
    public String toString() {
        return this.userName + "/" + this.encrytedPassword;
    }

}
