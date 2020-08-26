package com.example.hr_project1;

import android.os.Parcel;
import android.os.Parcelable;

public class Login_Response implements Parcelable {
    private int status;
    private String message;
    private User user;

    protected Login_Response(Parcel in) {
        status = in.readInt();
        message = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(message);
        dest.writeParcelable(user, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Login_Response> CREATOR = new Creator<Login_Response>() {
        @Override
        public Login_Response createFromParcel(Parcel in) {
            return new Login_Response(in);
        }

        @Override
        public Login_Response[] newArray(int size) {
            return new Login_Response[size];
        }
    };
}
