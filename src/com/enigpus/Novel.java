package com.enigpus;

public class Novel {
    private FileHandling fh = new FileHandling();
    private String stringBookType;
    private String title;
    private String author;
    private String publisher;
    private String yearPublished;
    private String id;
    private String highestIdString = fh.getHighestID("Novel");
    private int highestId;

    public Novel(String stringBookType, String title, String author, String publisher, String yearPublished) {
        this.stringBookType = stringBookType;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;

//        System.out.println(highestIdString);
        String[] idArray = this.highestIdString.split("-");
        this.highestId = Integer.parseInt(idArray[2]);
        String fixedNumberForID = String.format("%04d", ++this.highestId);
        this.id = yearPublished + "-A-" + fixedNumberForID;
    }

    public Novel(){}

    public Novel(String stringBookType, String title, String author, String publisher, String yearPublished, String id) {
        this.stringBookType = stringBookType;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public int getHighestId() {
        return highestId;
    }

    public void setHighestId(int highestId) {
        this.highestId = highestId;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "stringBookType='" + stringBookType + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearPublished='" + yearPublished + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
