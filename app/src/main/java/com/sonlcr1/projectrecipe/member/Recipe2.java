package com.sonlcr1.projectrecipe.member;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe2 implements Serializable {
    public Apple list;

    public Recipe2(Apple list) {
        this.list = list;
    }

    public class Apple implements Serializable{
        public String img;
        public String sub;
        public String title;
        public Banana materials;
        public ArrayList<Candy> recipe;

        public Apple(String img, String sub, String title, Banana materials, ArrayList<Candy> recipe) {
            this.img = img;
            this.sub = sub;
            this.title = title;
            this.materials = materials;
            this.recipe = recipe;
        }
    }

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
