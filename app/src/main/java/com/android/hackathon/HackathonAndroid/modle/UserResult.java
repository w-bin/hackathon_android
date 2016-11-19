package com.android.hackathon.HackathonAndroid.modle;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class UserResult {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public UserResult(String userId, boolean result, String err) {

        this.userId = userId;
        this.result = result;
        this.err = err;
    }

    private String userId;
    private boolean result;
    private String err;

}
