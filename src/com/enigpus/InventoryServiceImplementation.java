package com.enigpus;

import java.util.*;

public class InventoryServiceImplementation implements InventoryService {
    FileHandling fh = new FileHandling();

    private Map<String, List<String>> mapAllDataBooks = fh.getAllDataBooks();
    private Map<String, String> mapAllIDAndType = fh.getAllIDAndType();

    public InventoryServiceImplementation(FileHandling fh, Map<String, List<String>> mapAllDataBooks, Map<String, String> mapAllIDAndType) {
        this.fh = fh;
        this.mapAllDataBooks = mapAllDataBooks;
        this.mapAllIDAndType = mapAllIDAndType;
    }

    public InventoryServiceImplementation(){}

    public Map<String, String> getMapAllIDAndType() {
        return mapAllIDAndType;
    }

    public void setMapAllIDAndType(Map<String, String> mapAllIDAndType) {
        this.mapAllIDAndType = mapAllIDAndType;
    }

    public void updateDatabase(){};

    public void addBook(List<String> newBookInformation){
        List<String> informationForMap = new ArrayList<>();

        for(int i = 2; i < newBookInformation.size(); i++){
            informationForMap.add(newBookInformation.get(i));
        }
        System.out.println(informationForMap.getClass().getName());
        System.out.println(newBookInformation.get(0).getClass().getName());

        this.mapAllDataBooks.put(newBookInformation.get(0), informationForMap);
        this.mapAllIDAndType.put(newBookInformation.get(0), newBookInformation.get(1));

        fh.addNewBook(informationForMap, newBookInformation.get(0), newBookInformation.get(1));
    };

    public Map<String, List<String>> searchBookByTitle(String title){
        Map<String, List<String>> dataFounded = new HashMap<>();

        for(Map.Entry<String, List<String>> book : mapAllDataBooks.entrySet()){
            if(book.getValue().get(1).toLowerCase().contains(title.toLowerCase())){
                dataFounded.put(book.getKey(), book.getValue());
            }

        }

        return dataFounded;
    };

    public Map<String, List<String>> searchBookById(String id){
        Map<String, List<String>> dataFounded = new HashMap<>();

        for(Map.Entry<String, List<String>> book : mapAllDataBooks.entrySet()){
            if(book.getKey().equalsIgnoreCase(id)){
                dataFounded.put(book.getKey(), book.getValue());
            }

        }

        return dataFounded;

    };
    public void deleteBookById(String id){
        this.mapAllDataBooks.remove(id.toUpperCase());
        this.mapAllIDAndType.remove(id.toUpperCase());

        fh.deleteABook(mapAllDataBooks);
    }

    public Map<String, List<String>> getAllBook() {
//        System.out.println("getAllBook inventory implementation jalan");
        return this.mapAllDataBooks;
    }

    public void getAllBook(Map<String, List<String>> mapAllDataBooks) {
        this.mapAllDataBooks = mapAllDataBooks;
    }




}
