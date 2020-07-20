package com.sonlcr1.projectrecipe.member;

import java.io.Serializable;
import java.util.ArrayList;

public class VORecipe implements Serializable {
    public String maintitle;
    public String mainimg;
    public ArrayList<Apple> list;

    public VORecipe(String maintitle, ArrayList<Apple> list) {
        this.maintitle = maintitle;
        this.list = list;
    }

    //recyclerview, 레시피화면 img, sub, title
    public class Apple implements Serializable{
        public String video;
        public String uri;
        public String img;
        public String sub;
        public String title;
        public Banana materials;
        public ArrayList<Candy> recipe;

        public Apple(String video, String img, String sub, String title, Banana materials, ArrayList<Candy> recipe) {
            this.video = video;
            this.img = img;
            this.sub = sub;
            this.title = title;
            this.materials = materials;
            this.recipe = recipe;
        }
    }

    //재료 화면
    public class Banana implements Serializable{
        public String essential;
        public String essentialmsg;
        public String choice;
        public String choicemsg;
        public String source;
        public String sourcemsg;

        public Banana(String essential, String essentialmsg, String choice, String choicemsg, String source, String sourcemsg) {
            this.essential = essential;
            this.essentialmsg = essentialmsg;
            this.choice = choice;
            this.choicemsg = choicemsg;
            this.source = source;
            this.sourcemsg = sourcemsg;
        }
    }

    //레시피 화면
    public class Candy implements Serializable{
        public String num;
        public String recipeimg;
        public String recipemsg;

        public Candy(String num, String recipeimg, String recipemsg) {
            this.num = num;
            this.recipeimg = recipeimg;
            this.recipemsg = recipemsg;
        }
    }


}
