package com.tish.e_cards;

public class Folder {

    private String folderName;
    private int cardAmount;
    private int learnedCardAmount;

    public Folder(String folderName) {
        this.folderName = folderName;
        this.cardAmount = 0;
        this.learnedCardAmount = 0;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public int getLearnedCardAmount() {
        return learnedCardAmount;
    }

    public void setLearnedCardAmount(int learnedCardAmount) {
        this.learnedCardAmount = learnedCardAmount;
    }


}
