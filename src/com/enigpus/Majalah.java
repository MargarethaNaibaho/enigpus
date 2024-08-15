package com.enigpus;

public class Majalah {
    private FileHandling fh = new FileHandling();
    private String stringBookType;
    private String title;
    private String magazineType;
    private String yearPublished;
    private String id;
    private String highestIDString = fh.getHighestID("Magazine");
    private int highestID;

    public Majalah(String stringBookType, String title, String magazineType, String yearPublished) {
        this.stringBookType = stringBookType;
        this.title = title;
        this.magazineType = magazineType;
        this.yearPublished = yearPublished;

        String[] idArray = this.highestIDString.split("-");
        this.highestID = Integer.parseInt(idArray[2]);
        String fixedNumberForID = String.format("%04d", ++this.highestID);
        this.id = yearPublished + "-B-" + fixedNumberForID;
    }

    public Majalah(){}

    public Majalah(String stringBookType, String title, String magazineType, String yearPublished, String id) {
        this.stringBookType = stringBookType;
        this.title = title;
        this.magazineType = magazineType;
        this.yearPublished = yearPublished;
        this.id = id;
    }

    public String getStringBookType() {
        return stringBookType;
    }

    public void setStringBookType(String stringBookType) {
        this.stringBookType = stringBookType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMagazineType() {
        return magazineType;
    }

    public void setMagazineType(String magazineType) {
        this.magazineType = magazineType;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Majalah{" +
                "stringBookType='" + stringBookType + '\'' +
                ", title='" + title + '\'' +
                ", magazineType='" + magazineType + '\'' +
                ", yearPublished='" + yearPublished + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
