package com.sonlcr1.projectrecipe.member;

public class Recipe {
    public String firstimg;
    public String firstsub;
    public String firsttitle;
    public String secondessential;
    public String secondessentialmsg;
    public String secondchoice;
    public String secondchoicemsg;
    public String secondsource;
    public String secondsourcemsg;
    public String thirdimg;
    public String thirdmsg;
    public String fourthimg;
    public String fourthmsg;
    public String fifthimg;
    public String fifthmsg;
    public String sixthimg;
    public String sixthmsg;
    public String seventhimg;
    public String seventhmsg;
    public String eighthimg;
    public String eighthmsg;

    public Recipe() {
    }

    public Recipe(String thirdimg, String thirdmsg){
        this.thirdimg = thirdimg;
        this.thirdmsg = thirdmsg;
    }

    public Recipe(String firstimg,
                  String firstsub,
                  String firsttitle,
                  String secondessential,
                  String secondessentialmsg,
                  String secondchoice,
                  String secondchoicemsg,
                  String secondsource,
                  String secondsourcemsg,
                  String thirdimg,
                  String thirdmsg,
                  String fourthimg,
                  String fourthmsg,
                  String fifthimg,
                  String fifthmsg,
                  String sixthimg,
                  String sixthmsg,
                  String seventhimg,
                  String seventhmsg,
                  String eighthimg,
                  String eighthmsg) {


        this.firstimg = firstimg;
        this.firstsub = firstsub;
        this.firsttitle = firsttitle;
        this.secondessential = secondessential;
        this.secondessentialmsg = secondessentialmsg;
        this.secondchoice = secondchoice;
        this.secondchoicemsg = secondchoicemsg;
        this.secondsource = secondsource;
        this.secondsourcemsg = secondsourcemsg;
        this.thirdimg = thirdimg;
        this.thirdmsg = thirdmsg;
        this.fourthimg = fourthimg;
        this.fourthmsg = fourthmsg;
        this.fifthimg = fifthimg;
        this.fifthmsg = fifthmsg;
        this.sixthimg = sixthimg;
        this.sixthmsg = sixthmsg;
        this.seventhimg = seventhimg;
        this.seventhmsg = seventhmsg;
        this.eighthimg = eighthimg;
        this.eighthmsg = eighthmsg;
    }
}
