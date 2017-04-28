package com.learning.cartoonbook;

import com.learning.cartoonbook.model.Cartoon;
import com.learning.cartoonbook.model.CartoonBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CartoonPlayList {
    private CartoonBook cartoonBook;
    private BufferedReader reader;
    private Map<String, String> menu;
    private Queue<Cartoon> cartoonQueue;

    public CartoonPlayList(CartoonBook cartoonBook) {
        this.cartoonBook = cartoonBook;
        reader = new BufferedReader(new InputStreamReader(System.in));

        menu = new HashMap<String, String>();

        menu.put("add", "Add a new cartoon to the cartoon book");
        menu.put("play", "Play next cartoon in the queue");
        menu.put("choose", "Choose a cartoon to play!");
        menu.put("quit", "Give up. Exit the program");

        cartoonQueue = new ArrayDeque<Cartoon>();
    }

    public String promptAction() throws IOException{
        System.out.printf("There are %s cartoons available and %d in the queue.%n%n", cartoonBook.getCartoonCounts(), cartoonQueue.size());

        System.out.println("[MENU]");
        for (Map.Entry<String, String> option : menu.entrySet()) {
            System.out.printf("%s - %s%n", option.getKey(), option.getValue());
        }

        System.out.print("What do you want:  ");
        String choice = reader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "add":
                        Cartoon cartoon = promptNewCartoon();
                        cartoonBook.addCartoon(cartoon);
                        System.out.printf("%s added! %n%n", cartoon);
                        break;
                    case "choose":
                            String provider = promptProviders();
                            Cartoon providerCartoon = promptCartoonForProvider(provider);
                            cartoonQueue.add(providerCartoon);
                            System.out.printf("You choose: %s %n", provider);
                        break;
                    case "play":
                        playNext();
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        break;
                    default:
                        System.out.printf("Unkown choice %s. Try again. %n%n%n",choice);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        } while(!choice.equals("quit"));
    }

    public Cartoon promptNewCartoon() throws IOException {
        System.out.print("Enter cartoon name:  ");
        String name = reader.readLine();

        System.out.print("Enter your carootn privder:  ");
        String provider = reader.readLine();

        System.out.print("Enter your carootn videoUrl:  ");
        String videoUrl = reader.readLine();

        return new Cartoon(name, provider, videoUrl);
    }

    public String promptProviders() throws IOException {
        System.out.printf("%n");
        System.out.println("Available artists: ");
        List<String> providers = new ArrayList<>(cartoonBook.getProviders());
        int index = promptForIndex(providers);
        return providers.get(index);
    }

    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;
        for (String option : options) {
            System.out.printf("%d.) %s %n", counter, option);
            counter++;
        }

        System.out.print("Your choice:  ");
        String optionAsString = reader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());

        return choice -1;
    }

    private Cartoon promptCartoonForProvider(String provider) throws IOException {
        List<Cartoon> cartoons = cartoonBook.getCartoonsForProvider(provider);

        List<String> cartoonNames = new ArrayList<>();
        for (Cartoon cartoon : cartoons) {
            cartoonNames.add(cartoon.getName());
        }

        System.out.printf("%nAvailable cartoons for %s: %n", provider);
        int index = promptForIndex(cartoonNames);
        return cartoons.get(index);
    }

    public void playNext() {
        Cartoon cartoon = cartoonQueue.poll();
        if (cartoon == null) {
            System.out.println("Sorry there are no cartoon in the queue. Use choose from the menu to add some");
        } else {
            System.out.printf("%n%n%n open %s to watch %s by %s %n%n%n", cartoon.getVideoUrl(), cartoon.getName(), cartoon.getProvider());
        }
    }
}
