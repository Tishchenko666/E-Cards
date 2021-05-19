package com.tish.e_cards;

public class Card {
    private String word;
    private String translation;
    private String tag;
    private String description;
    private boolean mark;


    public Card(String word, String translation) {
        this.word = word;
        this.translation = translation;
        this.mark = false;
    }

    public Card(String word, String translation, String tag, String description) {
        this.word = word;
        this.translation = translation;
        this.tag = tag;
        this.description = description;
        this.mark = false;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
