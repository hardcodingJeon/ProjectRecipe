package com.sonlcr1.projectrecipe.member;

public class Board {
    public String userimg;
    public String userid;
    public String date;
    public String msg;
    public String imgmain;
    public int favorstate;
    public String favornum;
    public String chatnum;

    public Board() {
    }

    public Board(String userimg, String userid, String date, String msg, String imgmain, int favorstate, String favornum, String chatnum) {
        this.userimg = userimg;
        this.userid = userid;
        this.date = date;
        this.msg = msg;
        this.imgmain = imgmain;
        this.favorstate = favorstate;
        this.favornum = favornum;
        this.chatnum = chatnum;
    }
}
