package com.mythawk.yixing.com;

public class ComBody {

    private int share_id;
    private String com_num;
    private String com_text;
    private int com_floor;


    public String getCom_num() {
        return com_num;
    }

    public void setCom_num(String com_num) {
        this.com_num = com_num;
    }

    public String getCom_text() {
        return com_text;
    }

    public void setCom_text(String com_text) {
        this.com_text = com_text;
    }

    public int getShare_id() {
        return share_id;
    }

    public void setShare_id(int share_id) {
        this.share_id = share_id;
    }

    public int getCom_floor() {
        return com_floor;
    }

    public void setCom_floor(int com_floor) {
        this.com_floor = com_floor;
    }
}
