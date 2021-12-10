package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> booksLibrary = new ArrayList<>();

    public static void main(String[] args) {
        String userType = loginMenu();
        if (userType.equals("user")){
            runMainMenu();
        }
        if (userType.equals("admin")){
            runAdminMenu();
        }
    }

    public static String loginMenu(){
        final String fileToAccess = "usersDetails.txt";
        String userType = "notLoggedIn";
        boolean isLoggedIn = false;
        do {
            int loginOrRegister = Integer.parseInt(getInput("Do you want to\n1:Login\n2:Register\n3:Create/Reset all user details info"));
            switch (loginOrRegister) {
                case 1:
                    String isUser = loginUser(fileToAccess);
                    if (isUser.equals("isUser")) {
                        isLoggedIn = true;
                        userType = "user";
                    }
                    if (isUser.equals("isAdmin")){
                        runAdminMenu();
                        isLoggedIn = true;
                        userType = "admin";
                    }
                    break;
                case 2:
                    String userLoginDetails = registerAccount();
                    saveDetailsToFile(userLoginDetails, fileToAccess);
                    break;
                case 3:
                    createFile(fileToAccess);
                    break;
            }
        }while(isLoggedIn == false);
        return userType;
    }

    private static void runMainMenu() {
        final String fileToAccess = "library.txt";
        boolean exit = false;
        //booksLibrary.add(0, "bookTitle,ISBN number, author, genre");
        do {
            int menuOptionInput = Integer.parseInt(getInput("What do you want to do\nEnter: \n1 for show library\n2 to exit"));
            switch (menuOptionInput) {
                case 1: //shows all book info in the array
                    //showBooksListAll();
                    readLibrary(fileToAccess);
                    break;
                case 2:
                    exit = true;
            }
        }while(exit == false);
    }

    public static void runAdminMenu(){
        final String fileToAccess = "library.txt";
        boolean exit = false;
        do{
            int menuOptionInput = Integer.parseInt(getInput("What do you want to do\nEnter: \n1 for show library\n2 for adding a book\n3 for creating/resetting a file\n4 to exit"));
            switch (menuOptionInput) {
                case 1: //shows all book info in the array
                    //showBooksListAll();
                    readLibrary(fileToAccess);
                    break;
                case 2: //adds a new book and saves it to the array
                    String bookDetails = getBookDetails();
                    saveBookDetailsToArray(bookDetails);
                    saveDetailsToFile(bookDetails,fileToAccess);
                    break;
                case 3: //creates/resets file info about the books
                    createFile(fileToAccess);
                    break;
                case 4:
                    exit = true;
            }
        }while(exit == false);
    }

    private static Integer menuOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do\nEnter: \n1 for show library\n2 for adding a book\n3 for creating/resetting a file\n4 to exit");
        int menuOptionInput = input.nextInt();
        return menuOptionInput;
    }

    public static String registerAccount(){
        String email = getInput("What is your email");
        String password = getInput("What do you want your password to be");
        return email+","+password+",user";
    }

    public static String loginUser(String fileToAccess){
        File fileHandler = new File(fileToAccess);
        String isUser = "notLoggedIn";
        String userDetails = getInput("What is your email") +","+ getInput("What is your password");
        //String password = getInput("What is your password");
        try {
            Scanner myReader = new Scanner(fileHandler);
            int i =0;
            while (myReader.hasNextLine()) {
                /*
                if (userDetails.equals(myReader.nextLine())) {
                    isUser = true;
                }
                 */
                if (myReader.nextLine().equals(userDetails.split(",")[0]) && myReader.nextLine().equals(userDetails.split(",")[1])) {
                    isUser = "isUser";
                    userDetails = myReader.nextLine();
                    if (myReader.nextLine().equals(userDetails.split(",")[2])){
                        isUser = "isAdmin";
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return isUser;
    }

    public static String getBookDetails(){
        String bookTitle = getInput("What is the book called:");
        String bookISBN = getInput("What is the book ISBN number:");
        String bookAuthor = getInput("Who is the author");
        String bookGenre = getInput("What is the books genre:");
        return (bookTitle + "," + bookISBN + "," + bookAuthor + "," + bookGenre);
    }

    public static void saveDetailsToFile(String Details,String fileToAccess){
        File fileHandler = new File(fileToAccess);
        try {
            FileWriter myWriter = new FileWriter(fileHandler.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(Details +"\n"); // Overwrites everything in the file
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readLibrary(String fileToAccess){
        File fileHandler = new File(fileToAccess);
        try {
            Scanner myReader = new Scanner(fileHandler);
            while (myReader.hasNextLine()) {
                String currentBookDetails = myReader.nextLine();
                String[] bookDetailsToShow = currentBookDetails.split(",");
                System.out.println("");
                for (int j = 0; j < 4; j++) {
                    System.out.print(bookDetailsToShow[j]+" ");
                }
            }
            System.out.println("");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void saveBookDetailsToArray(String bookDetails) {
        System.out.println(bookDetails);
        booksLibrary.add(bookDetails);
    }

    public static void showBooksListAll(){
        for (int i = 0; i < booksLibrary.size(); i++) {
            System.out.println(booksLibrary.get(i));
            //System.out.println(booksLibrary.get(i).split(","));
        }
    }

    public static String getInput(String caption){
        System.out.println(caption);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void createFile(String fileToAccess) {
        File fileHandler = new File(fileToAccess);
        try {
            if (fileHandler.createNewFile()) {
                System.out.println("File created: " + fileHandler.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
