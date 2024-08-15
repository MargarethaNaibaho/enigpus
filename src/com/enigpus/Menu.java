package com.enigpus;

import java.util.*;

public class Menu {
    private Scanner scanner;
    private InventoryServiceImplementation inventoryServiceImplementation = new InventoryServiceImplementation();
    private String choice;
    private boolean isClose = false;

    public Menu(Scanner scanner, String choice, boolean isClose) {
        this.scanner = scanner;
        this.choice = choice;
        this.isClose = isClose;
    }

    public Menu(){
        System.out.println("=".repeat(100));
        System.out.println("Welcome to Enigpus Library");
        System.out.println("=".repeat(100));
        System.out.println("We have to types of books. They are Novel and Magazine");
        System.out.println("=".repeat(100));
        scanner = new Scanner(System.in);
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public void menuList(){
        System.out.println("=".repeat(100));
        System.out.println("Menu Enigpus");
        System.out.println("=".repeat(100));

        System.out.println("1. Add New Book (Novel or Magazine)");
        System.out.println("2. Search Book By Title");
        System.out.println("3. Search Book By ID");
        System.out.println("4. Get All Books");
        System.out.println("5. Delete A Book By ID");
        System.out.println("6. Exit");
        System.out.println("=".repeat(100));
    }

    public void closing(){
        System.out.println("=".repeat(100));
        System.out.println("Thank you for using this application! See you and have a good day!");
        System.out.println("=".repeat(100));
    }

    public void start(){
        do{
            menuList();
            System.out.print("Enter your choice [1 / 2 / 3 / 4 / 5 / 6]: ");

            this.choice = scanner.next();
            scanner.nextLine();

            switch(this.choice){
                case "1":
                    addNewBook();
                    closeApp();
                    break;
                case "2":
                    searchByTitle();
                    closeApp();
                    break;
                case "3":
                    searchByID();
                    closeApp();
                    break;
                case "4":
                    getAllBooks();
                    closeApp();
                    break;
                case "5":
                    deleteABookByID();
                    closeApp();
                    break;
                case "6":
                    closing();
                    this.isClose = true;
                    break;
                default:
                    System.out.println("=".repeat(50));
                    System.out.println("Invalid Choice! Input 1 / 2 / 3 / 4 / 5 / 6!");
                    System.out.println("=".repeat(50));
                    System.out.flush();
                    break;
            }

        }while(!this.isClose);

    }

    public void displayBooks(Map<String, List<String>> bookWillBeDisplayed){
        boolean isNovelExist = false;
        boolean isMagazineExist = false;

        for(Map.Entry<String, List<String>> book : bookWillBeDisplayed.entrySet()){
            if(isNovelExist && isMagazineExist){
                break;
            }
            if(book.getValue().get(0).equals("Magazine")){
                isMagazineExist = true;
            }
            if(book.getValue().get(0).equals("Novel")){
                isNovelExist = true;
            }
        }

        if(isNovelExist){
            System.out.println();
            System.out.println("Novels");
            System.out.println("+" + "-".repeat(110) + "+");
            System.out.println("| ID          | Title                         | Publisher           | Year Published      | Author             |");
            System.out.println("+" + "-".repeat(110) + "+");

            for(Map.Entry<String, List<String>> book : bookWillBeDisplayed.entrySet()){
                if(book.getValue().get(0).equals("Novel")){
                    System.out.println(String.format("| %-8s | %-29s | %-20s | %-18s | %-18s |",
                            book.getKey(),
                            book.getValue().get(1),
                            book.getValue().get(2),
                            book.getValue().get(3),
                            book.getValue().get(4)
                    ));
                }

            }
            System.out.println("+" + "-".repeat(110) + "+");
        }

        if(isMagazineExist){
            System.out.println();
            System.out.println("Magazine");
            System.out.println("+" + "-".repeat(113) + "+");
            System.out.println("| ID          | Title                                                 | Publication Period    | Year Published    |");
            System.out.println("+" + "-".repeat(113) + "+");

            for(Map.Entry<String, List<String>> book : bookWillBeDisplayed.entrySet()){
                if(book.getValue().get(0).equals("Magazine")){
                    System.out.println(String.format("| %-8s | %-53s | %-22s | %-16s |",
                            book.getKey(),
                            book.getValue().get(1),
                            book.getValue().get(2),
                            book.getValue().get(3)
                    ));
                }

            }
            System.out.println("+" + "-".repeat(113) + "+");
        }
    }

    public String inputValidationID(){
        String id;

        while(true){
            System.out.println("=".repeat(100));
            System.out.println("Input Book ID with this format YYYY-A-XXXX");
            System.out.println("For example: 2024-A-0001");
            System.out.print("Your Input: ");

            id = scanner.nextLine();
            String[] idArray = id.split("-");
            if(idArray.length == 3){
                if(idArray[1].equalsIgnoreCase("A") || idArray[1].equalsIgnoreCase("B")){
                    try{
                        int y = Integer.parseInt(idArray[0]);
                        try{
                            int x = Integer.parseInt(idArray[2]);
                            break;

                        } catch (NumberFormatException e){
                            System.out.println("=".repeat(100));
                            System.out.println("XXXX must be integer");
                            System.out.println("=".repeat(100));
                        }

                    } catch(NumberFormatException e){
                        System.out.println("=".repeat(100));
                        System.out.println("YYYY must be integer");
                        System.out.println("=".repeat(100));
                    }
                } else{
                    System.out.println("=".repeat(100));
                    System.out.println("Book type is invalid!");
                    System.out.println("=".repeat(100));
                }
            } else{
                System.out.println("=".repeat(100));
                System.out.println("You input is totally invalid!");
                System.out.println("=".repeat(100));
            }
        }

        return id;
    }

    public boolean isBookExisted(String id){


        return false;
    }

    //menu 1. add new book
    public void addNewBook(){
        String id, bookType;
        Map<String, List<String>> dataFounded = new HashMap<>();

        System.out.println("=".repeat(100));
        System.out.println("Add New Book");
        System.out.println("=".repeat(100));

//        while(true){
//            id = inputValidationID();
//
//            try{
//                int idInt = Integer.parseInt(id);
//                dataFounded = inventoryServiceImplementation.searchBookById(id);
//                if(dataFounded.size() < 1) {
//                    break;
//                } else{
//                    System.out.println("The book is already in our database!");
//                    displayBooks(dataFounded);
//                    System.out.println();
//                }
//
//            } catch (NumberFormatException e){
//                System.out.println("The number is invalid!");
//            }
//
////        System.out.println(id);
//        }

        while(true){
            System.out.println("=".repeat(100));
            System.out.println("Book Type That You Wanna Add:");
            System.out.println("1. Novel");
            System.out.println("2. Magazine");
            System.out.println("=".repeat(100));
            System.out.print("Input book type [1 / 2]: ");
            bookType = scanner.nextLine();

            if(bookType.equals("1") || bookType.equals("2")){
                break;
            } else{
                System.out.println("Book Type Entered Is Invalid!");
            }

            System.out.println("=".repeat(100));
        }

        if (bookType.equals("1")){
            String stringBookType = "Novel";
            String title, author, publisher, yearPublished;

            System.out.println("=".repeat(100));
            System.out.print("Input the title: ");
            title = scanner.nextLine();
            System.out.print("Input the author: ");
            author = scanner.nextLine();
            System.out.print("Input the publisher: ");
            publisher = scanner.nextLine();

            while(true){
                System.out.print("Input year published: ");
                yearPublished = scanner.nextLine();
                try{
                    int year = Integer.parseInt(yearPublished);

                    if(year >= 1000 && year <= 9999){
                        if(year >= 1500){
                            break;
                        } else{
                            System.out.println("=".repeat(100));
                            System.out.println("We only accept books with publication year >= 1500");
                            System.out.println("=".repeat(100));
                        }
                    } else{
                        System.out.println("=".repeat(100));
                        System.out.println("Year entered is not 4-digit integer!");
                        System.out.println("=".repeat(100));
                    }
                } catch(NumberFormatException e){
                    System.out.println("=".repeat(100));
                    System.out.println("Year format is invalid!");
                    System.out.println("=".repeat(100));
                }
            }

            Novel novel = new Novel(stringBookType, title, author, publisher, yearPublished);

            System.out.println("Novel Information that will be added: ");
            System.out.println("ID: " + novel.getId());
            System.out.println("Title: " + novel.getTitle());
            System.out.println("Author: " + novel.getAuthor());
            System.out.println("Publisher: " + novel.getPublisher());
            System.out.println("Year Published: " + novel.getYearPublished());

            while(true){
                System.out.println("=".repeat(100));
                System.out.print("Are you sure to add this new book? [Y / N]: ");

                String addConfirmation = scanner.nextLine();
                if(addConfirmation.equalsIgnoreCase("y")){
                    List<String> dataNewBook = new ArrayList<>();
                    dataNewBook.add(novel.getId());
                    dataNewBook.add("Novel");
                    dataNewBook.add(novel.getTitle());
                    dataNewBook.add(novel.getPublisher());
                    dataNewBook.add(novel.getYearPublished());
                    dataNewBook.add(novel.getAuthor());

                    inventoryServiceImplementation.addBook(dataNewBook);
                    break;
                } else if (addConfirmation.equalsIgnoreCase("n")) {
                    System.out.println("Book is Not Added.");
                    break;
                } else{
                    System.out.println("Invalid Input!");
                }

                System.out.println("=".repeat(100));
            }
        } else if (bookType.equals("2")) {
            String stringBookType = "Magazine";
            String title, magazineType, yearPublished;

            System.out.println("=".repeat(100));
            System.out.print("Input the title: ");
            title = scanner.nextLine();

            while (true) {
                System.out.print("Input Magazine Type [Monthly / Weekly]: ");
                magazineType = scanner.nextLine();

                if (magazineType.equalsIgnoreCase("monthly") || magazineType.equalsIgnoreCase("weekly")) {
                    break;
                } else {
                    System.out.println("=".repeat(100));
                    System.out.println("Magazine type is invalid! Input correct type!");
                    System.out.println("=".repeat(100));
                }
            }

            while (true) {
                System.out.print("Input year published: ");
                yearPublished = scanner.nextLine();
                try {
                    int year = Integer.parseInt(yearPublished);

                    if (year >= 1000 && year <= 9999) {
                        if (year >= 1500) {
                            break;
                        } else {
                            System.out.println("=".repeat(100));
                            System.out.println("We only accept books with publication year >= 1500");
                            System.out.println("=".repeat(100));
                        }
                    } else {
                        System.out.println("=".repeat(100));
                        System.out.println("Year entered is not 4-digit integer!");
                        System.out.println("=".repeat(100));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("=".repeat(100));
                    System.out.println("Book type is invalid!");
                    System.out.println("=".repeat(100));
                }
            }

            Majalah magazine = new Majalah(stringBookType, title, magazineType, yearPublished);

            System.out.println("Magazine Information that will be added: ");
            System.out.println("ID: " + magazine.getId());
            System.out.println("Title: " + magazine.getTitle());
            System.out.println("Magazine Type: " + magazine.getMagazineType());
            System.out.println("Year Published: " + magazine.getYearPublished());

            while (true) {
                System.out.println("=".repeat(100));
                System.out.print("Are you sure to add this new book? [Y / N]: ");

                String addConfirmation = scanner.nextLine();
                if (addConfirmation.equalsIgnoreCase("y")) {
                    List<String> dataNewBook = new ArrayList<>();
                    dataNewBook.add(magazine.getId());
                    dataNewBook.add("Magazine");
                    dataNewBook.add(magazine.getTitle());
                    dataNewBook.add(magazine.getMagazineType());
                    dataNewBook.add(magazine.getYearPublished());

                    inventoryServiceImplementation.addBook(dataNewBook);
                    break;
                } else if (addConfirmation.equalsIgnoreCase("n")) {
                    System.out.println("Book is Not Added.");
                    break;
                } else {
                    System.out.println("Invalid Input!");
                }

                System.out.println("=".repeat(100));
            }

        }
    }

    //menu 2. search book by title
    public void searchByTitle(){
        String keyword;
        Map<String, List<String>> dataFounded = new HashMap<>();

        System.out.println("=".repeat(100));
        System.out.println("Search Book by Title");
        System.out.println("=".repeat(100));

        System.out.print("Input the title that you want to search: ");
        keyword = scanner.nextLine();

        dataFounded = inventoryServiceImplementation.searchBookByTitle(keyword);
        if(dataFounded.size() < 1) {
            System.out.println("Data isn't founded!");
        } else{
            displayBooks(dataFounded);
            System.out.println();
        }
    }

    //menu 3. search book by id
    public void searchByID(){
        Map<String, List<String>> dataFounded = new HashMap<>();

        System.out.println("=".repeat(100));
        System.out.println("Search Book by ID");
        System.out.println("=".repeat(100));

        String id = inputValidationID();
//        System.out.println(id);
        dataFounded = inventoryServiceImplementation.searchBookById(id);
        if(dataFounded.size() < 1) {
            System.out.println("Data isn't founded!");
        } else{
            displayBooks(dataFounded);
            System.out.println();
        }
    }

    //menu 4. get all books
    public void getAllBooks(){
//        System.out.println(inventoryServiceImplementation);
        Map<String, List<String>> allDataBooks = inventoryServiceImplementation.getAllBook();
        Map<String, String> allIDAndType = inventoryServiceImplementation.getMapAllIDAndType();

        System.out.println("=".repeat(100));
        System.out.println("Get All Book Data");
        System.out.println("=".repeat(100));

        displayBooks(allDataBooks);

        System.out.println();

    }

    //menu 5. delete a book by id
    public void deleteABookByID(){
        String id, bookType;
        Map<String, List<String>> dataFounded = new HashMap<>();

        System.out.println("=".repeat(100));
        System.out.println("Delete A Book By ID");
        System.out.println("=".repeat(100));

        while (true){
            id = inputValidationID();
//        System.out.println(id);
            dataFounded = inventoryServiceImplementation.searchBookById(id);
            if(dataFounded.size() < 1) {
                System.out.println("Data isn't founded!");
            } else{
                displayBooks(dataFounded);
                System.out.println();
                break;
            }
        }


        while (true){
            System.out.println("=".repeat(100));
            System.out.print("Are you sure to delete this book? [Y / N] : ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("y")) {
                inventoryServiceImplementation.deleteBookById(id);
                System.out.println("A Book is successfully deleted from our database.");
                break;
            } else if (userInput.equalsIgnoreCase("n")) {
                System.out.println("Book is Not Deleted.");
                break;
            } else {
                System.out.println("Invalid Input!");
            }

        }

    }

    public boolean closeApp(){
//        scanner.nextLine();
        while(true){
            System.out.println("=".repeat(100));
            System.out.print("Do you still want to use this application? [Y / N]: ");

            this.choice = scanner.nextLine();
            if(this.choice.equalsIgnoreCase("y")){
                this.isClose = false;
                return isClose;
            } else if(this.choice.equalsIgnoreCase("n")){
                closing();
                this.isClose = true;
                return isClose;
            } else {
                System.out.println("=".repeat(100));
                System.out.println("Invalid input!");
                System.out.println("=".repeat(100));
            }

        }

    }

    @Override
    public String toString() {
        return "Menu{" +
                "scanner=" + scanner +
                ", choice='" + choice + '\'' +
                ", isClose=" + isClose +
                '}';
    }
}
