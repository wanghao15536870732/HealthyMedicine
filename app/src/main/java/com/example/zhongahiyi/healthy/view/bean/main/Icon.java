package com.example.zhongahiyi.healthy.view.bean.main;

public class Icon {
    private int icolor;
    private int iId;
    private String iName;

    public Icon() {
    }

    public Icon(int icolor,int iId, String iName) {
        this.icolor = icolor;
        this.iId = iId;
        this.iName = iName;
    }

    public int getIcolor() {
        return icolor;
    }

    public void setIcolor(int icolor) {
        this.icolor = icolor;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
