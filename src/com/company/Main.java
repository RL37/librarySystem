package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//good

public class Main {

    private static ArrayList<String> booksLibrary = new ArrayList<>();

    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() {
        int menuOption = menuOption();
        switch(menuOption){
            case 1: //shows all book info in the array
                showBooksListAll();
                break;
            case 2: //adds a new book and saves it to the array
                String bookDetails = getBookDetails();
                saveBookDetailsToArray(bookDetails);
                break;
            case 3: //creates/resets file info about the books
                createFile();
                break;
        }
    }

    private static Integer menuOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do\nEnter: \n1 for show library\n2 for adding a book\n3 for creating/reseting a file");
        int menuOption = input.nextInt();
        return menuOption;
    }

    public static String getBookDetails(){
        String bookTitle = getInput("What is the book called\n:");
        String ISBNNumber = getInput("What is the book ISBN number\n:");
        String bookAuthor = getInput("Who is the author");
        String bookGenre = getInput("What is the books genre\n:");
        return (bookTitle + "," + ISBNNumber + "," + bookAuthor + "," + bookGenre);
    }

    public static void saveBookDetailsToFile(){

    }

    private static void saveBookDetailsToArray(String bookDetails) {
        for (int i = 0; i < booksLibrary.size() + 1;) {
            if (booksLibrary.get(i).equals(null)){
                booksLibrary.add(bookDetails);
                i = booksLibrary.size() +2;
            }
            else{
                i++;
            }
        }
    }

    public static void showBooksListAll(){
        for (int i = 0; i < booksLibrary.size(); i++) {
            System.out.println(booksLibrary.get(i).split(","));
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
