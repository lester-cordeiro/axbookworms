package com.astrex.bookworms;

import java.util.ArrayList;

public class Alphabet {

    private String name;
    private String text;

    public Alphabet(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName(){
        return name;
    }
    public String getText(){
        return text;
    }

    public static ArrayList<Alphabet> getUsers() {
        ArrayList<Alphabet> users = new ArrayList<Alphabet>();
        users.add(new Alphabet("a", "A for Abbot; Our Abbot is very wise"));
        users.add(new Alphabet("b", "B for Buddha; Buddha is unrivalled"));
        users.add(new Alphabet("c", "C for Charity; Some monks and nuns run Charities for the welfare of the poor and orphans "));
        users.add(new Alphabet("d", "D for Donor; We send metta to donors who provide us shelter, robes, food and medicine"));
        users.add(new Alphabet("e", "E for Enlightenment; As soon as you are enlightened, you are completely free from greed, anger and ignorance"));
        users.add(new Alphabet("f", "F for Freedom; Enlightenment gives the highest freedom"));
        users.add(new Alphabet("g", "G for Generosity; Generosity can warm your heart and bring you friends"));
        users.add(new Alphabet("h", "H for Happiness; Mindful people achieve true happiness"));
        users.add(new Alphabet("i", "I for Idea; We get good ideas from Dhamma"));
        users.add(new Alphabet("j", "J for Justice; We can find justice in kamma"));
        users.add(new Alphabet("k", "K for Kindness; Kindness is the key to a happy life"));
        users.add(new Alphabet("l", "L for Lay-devotee; Lay-devotees offer us food, robes, medicine and shelter"));
        users.add(new Alphabet("m", "M for Monastery; We live in a monastery"));
        users.add(new Alphabet("n", "N for Novice; I am a novice"));
        users.add(new Alphabet("o", "O for Order; We are members of the Order of Samgha"));
        users.add(new Alphabet("p", "P for Peace; Buddha taught us to live in Peace"));
        users.add(new Alphabet("q", "Q for Quiet; Every Buddha enjoyed quite places"));
        users.add(new Alphabet("r", "R for Robe; We wear maroon robes"));
        users.add(new Alphabet("s", "S for Salvation; We are responsible for our own salvation"));
        users.add(new Alphabet("t", "T for Tolerate; We should tolerate all difficulties and strive for enlightenment"));
        users.add(new Alphabet("u", "U for Universality; Universality is a Buddhist quality"));
        users.add(new Alphabet("v", "V for Virtue; Virtue is the father of good character"));
        users.add(new Alphabet("w", "W for Wisdom; Insight-meditation can give us the highest wisdom"));
        users.add(new Alphabet("x", "X for Extremes; Buddhas taught us to avoid two extremes, and to choose the middle way"));
        users.add(new Alphabet("y", "Y for Yogi; Yogis meditate at meditation centres"));
        users.add(new Alphabet("z", "Z for Zeal; Novices' zeal for learning can lead to success"));
        return users;
    }
}