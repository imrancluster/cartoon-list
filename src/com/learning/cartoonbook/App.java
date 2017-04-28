package com.learning.cartoonbook;

import com.learning.cartoonbook.model.CartoonBook;

public class App {
    public static void main(String[] args) {
        CartoonBook cartoonBook = new CartoonBook();
        cartoonBook.importFrom("cartoons.txt");
        CartoonPlayList playList = new CartoonPlayList(cartoonBook);
        playList.run();

        System.out.println("Saving cartoon...");
        cartoonBook.exportTo("cartoons.txt");

    }
}
