package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//good

public class Main {

    private static ArrayList<String> booksLibrary = new ArrayList<>();

    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() {
        boolean exit = false;
        booksLibrary.add(0, "bookTitle,ISBN number, author, genre");
        do {
            int menuOptionInput = menuOption();
            switch (menuOptionInput) {
                case 1: //shows all book info in the array
                    //showBooksListAll();
                    readLibrary();
                    break;
                case 2: //adds a new book and saves it to the array
                    String bookDetails = getBookDetails();
                    saveBookDetailsToArray(bookDetails);
                    saveBookDetailsToFile(bookDetails);
                    break;
                case 3: //creates/resets file info about the books
                    createFile();
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

    public static String getBookDetails(){
        String bookTitle = getInput("What is the book called:");
        String bookISBN = getInput("What is the book ISBN number:");
        String bookAuthor = getInput("Who is the author");
        String bookGenre = getInput("What is the books genre:");
        return (bookTitle + "," + bookISBN + "," + bookAuthor + "," + bookGenre);
    }

    public static void saveBookDetailsToFile(String bookDetails){
        File fileHandler = new File("Library.txt");
        try {
            FileWriter myWriter = new FileWriter(fileHandler.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(bookDetails); // Overwrites everything in the file
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readLibrary(){
        File fileHandler = new File("Library.txt");
        try {
            Scanner myReader = new Scanner(fileHandler);
            int i =0;
            while (myReader.hasNextLine()) {
                String currentBookDetails = myReader.nextLine();
                //booksLibrary.remove(i);
                //booksLibrary.add(i, currentBookDetails);
                //System.out.println(booksLibrary.get(i));
                //System.out.println(booksLibrary.get(i).split(","));
                System.out.println(currentBookDetails.split(",")[3]);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void saveBookDetailsToArray(String bookDetails) {
        System.out.println(bookDetails);
        booksLibrary.add(bookDetails);
        /*
        try {
            //for (int i = 0; i < booksLibrary.size();i++) {
                //String currentBookDetails = booksLibrary.get(i);
            //booksLibrary.add(bookDetails);

                if (currentBookDetails.equals(null)) {
                    booksLibrary.add(i, bookDetails);
                    i = booksLibrary.size() + 2;
                } else {
                    i++;
                }

            }
        }catch (IOError e){
            System.out.println("An error occurred" +e);
        }
        */
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

    public static void createFile() {
        File fileHandler = new File("Library.txt");
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
