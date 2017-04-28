package com.learning.cartoonbook;

import com.learning.cartoonbook.model.CartoonBook;

public class App {
    public static void main(String[] args) {

        /**
         * Main application class to run the program
         *
         * First cartoons will be imported from cartoons.txt file
         * Second application will run and ask to add/choose/play cartoons
         *
         * If user will add any new cartoon and "quit" the application
         * then system will save the new cartoon into the cartoons.txt file
         */
        CartoonBook cartoonBook = new CartoonBook();
        cartoonBook.importFrom("cartoons.txt");
        CartoonPlayList playList = new CartoonPlayList(cartoonBook);
        playList.run();

        System.out.println("Saving cartoon...");
        cartoonBook.exportTo("cartoons.txt");

    }
}
