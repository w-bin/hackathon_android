package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class ProductKind {
    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public int  getKindId() {

        return kindId;
    }

    public void setKindId(int  kindId) {
        this.kindId = kindId;
    }

    public ProductKind(int  kindId, String kindName) {
        this.kindId = kindId;
        this.kindName = kindName;
    }

    private int  kindId;
    private String kindName;


}
