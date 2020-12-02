package com.example.beenlovememory.Model;

public class TimeLine {
    private int Id;
    private String sDate, sDescription;
    private byte[] img;



    public TimeLine() {
    }

    public TimeLine(int Id, String sDate, String sDescription, byte[] img) {
        this.Id = Id;
        this.sDate = sDate;
        this.sDescription = sDescription;
        this.img = img;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
