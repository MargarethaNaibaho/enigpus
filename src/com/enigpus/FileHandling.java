package com.enigpus;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class FileHandling {
    public FileHandling() {
    }

    public Map<String, List<String>> getAllDataBooks(){
        File file = new File("/home/enigma/livecode-enigpus/BookDatabase.txt");
        Map<String, List<String>> mapAllData = new HashMap<>();

        if(file.exists()){
            try(
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ){
                System.out.println("Berhasil buka file");
                String line;
                while((line = br.readLine()) != null){
                    String[] data = line.split(",");
                    String key = "";
                    List<String> singleBookData = new ArrayList<>();
                    for(int i = 0; i < data.length; i++){
                        if(i == 0){
                            key = data[i];
                        } else{
                            singleBookData.add(data[i]);
                        }
                    }

                    mapAllData.put(key, singleBookData);

                }
            } catch (IOException e){
                System.out.println("ini error");
                e.printStackTrace();
            }
        } else{
            System.out.println("Database isn't found!");
            return mapAllData;
        }

        return mapAllData;

    }
    public Map<String, String> getAllIDAndType(){
        File file = new File("/home/enigma/livecode-enigpus/BookDatabase.txt");
        Map<String, String> mapIDAndType = new HashMap<>();

        if(file.exists()){
            try(
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ){
                String line;
                while((line = br.readLine()) != null){
                    String[] data = line.split(",");
                    String key = "";
                    String value = "";
                    for(int i = 0; i < data.length; i++){
                        if(i == 0){
                            key = data[0];
                        } else if(i == 1){
                            value = data[1];
                        } else{
                            continue;
                        }
                    }

                    mapIDAndType.put(key, value);

                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else{
            System.out.println("Database isn't found!");
            return mapIDAndType;
        }

        return mapIDAndType;
    }

    public String getHighestID(String bookType){
        String lastId = "";
        int highestIdInt = 0;
        String currentLine;
//        System.out.println(fileName);
        File file = new File("/home/enigma/livecode-enigpus/BookDatabase.txt");

        if(file.exists()){
            try(
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ){
                while((currentLine = br.readLine()) != null){
                    String[] currentLineArray = currentLine.split(",");
                    if(currentLineArray[1].equalsIgnoreCase(bookType)){
                        String[] id = currentLineArray[0].split("-");
                        int currentLineId = Integer.parseInt(id[2]);
                        if(currentLineId > highestIdInt){
                            lastId = currentLineArray[0];
                            highestIdInt = currentLineId;
                        }
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else{
            System.out.println("File does not exist");
        }
//        System.out.println(lastId);
        return lastId;
    }

    public void addNewBook(List<String> newBookInformation, String bookId, String bookType){
        File file = new File("/home/enigma/livecode-enigpus/BookDatabase.txt");

        String front = bookId + "," + bookType + ",";
        String back = "";

        for(int i = 0; i < newBookInformation.size(); i++){
            if(i == newBookInformation.size() - 1){
                back = back + newBookInformation.get(i);
            } else{
                back = back + newBookInformation.get(i) + ",";
            }
        }

        String willBeAppended = front + back;

        if(file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
                bw.write(willBeAppended);
                bw.newLine();
                System.out.println("New Book Information is Successfully Added in Our Database!");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void deleteABook(Map<String, List<String>> mapAllDataBooks){
        File file = new File("/home/enigma/livecode-enigpus/BookDatabase.txt");
        String willBeAppended;

        if(file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                for(Map.Entry<String, List<String>> book : mapAllDataBooks.entrySet()){
                    if(book.getValue().get(0).equalsIgnoreCase("novel")){
                        willBeAppended = book.getKey() + "," +
                                book.getValue().get(0) + "," +
                                book.getValue().get(1) + "," +
                                book.getValue().get(2) + "," +
                                book.getValue().get(3) + "," +
                                book.getValue().get(4);
                    } else{
                        willBeAppended = book.getKey() + "," +
                                book.getValue().get(0) + "," +
                                book.getValue().get(1) + "," +
                                book.getValue().get(2) + "," +
                                book.getValue().get(3);
                    }

                    bw.write(willBeAppended);
                    bw.newLine();

                }

            } catch (IOException e){
                e.printStackTrace();
            }
        } else{
            System.out.println("Database isn't found!");
        }
    }

}
