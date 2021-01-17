package com.htn.legitrack;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserInfo {
    public String state; // user's stored state
    public String interests; // user's stored interests

    public UserInfo() {
        state = "";
        interests = "";
    }

    public UserInfo(UserInfo myObj) {
        state = myObj.state;
        interests = myObj.interests;
    }

    public UserInfo(String State, String Interests) {
        state = State;
        interests = Interests;
    }

    //public UserInfo(Bill myBill) {id = myBill.id; }

    public void setInterests(String INTERESTS) {
        this.interests = INTERESTS;
    }

    public String getInterests() {
        return this.interests;
    }

    public void setState(String State) {
        this.state = State;
    }

    public String getState() {
        return this.state;
    }
}
