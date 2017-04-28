package com.learning.cartoonbook.model;

import java.io.*;
import java.util.*;

/**
 * CartoonBook class to manage cartoons like
 * Cartoons will be imported/exported
 * It provides all providers
 * It provides all cartoons according to providers
 */
public class CartoonBook {
    private List<Cartoon> cartoons;

    public CartoonBook() {
        cartoons = new ArrayList<Cartoon>();
    }

    /**
     * It will save cartoons into the fileName
     * @param fileName
     */
    public void exportTo(String fileName) {
        try(
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter writer = new PrintWriter(fos);
        ) {
            for(Cartoon cartoon : cartoons) {
                writer.printf("%s|%s|%s%n", cartoon.getName(), cartoon.getProvider(), cartoon.getVideoUrl());
            }
        } catch (IOException ioe) {
            System.out.println("Problem saving file.");
            ioe.printStackTrace();
        }
    }

    /**
     * Cartoons will be imported from the file
     * It will add cartoon in the cartoons list for next processing
     * @param fileName
     */
    public void importFrom(String fileName) {
        try(
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        ){
            String line;
            while((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                addCartoon(new Cartoon(args[0], args[1], args[2]));
            }
        } catch (IOException ioe) {
            System.out.printf("Problem loading %s %n", fileName);
            ioe.printStackTrace();

        }
    }

    /**
     * Add a cartoon object in the cartoons list
     * @param cartoon
     */
    public void addCartoon(Cartoon cartoon) {
        cartoons.add(cartoon);
    }

    public int getCartoonCounts() {
        return cartoons.size();
    }

    /**
     * A Helper function
     */
    private Map<String, List<Cartoon>> byProvider() {
        Map<String, List<Cartoon>> byProvider = new TreeMap<>();

        for (Cartoon cartoon : cartoons) {
            List<Cartoon> providerCartoons = byProvider.get(cartoon.getProvider());
            if (providerCartoons == null) {
                providerCartoons = new ArrayList<>();
                byProvider.put(cartoon.getProvider(), providerCartoons);
            }
            providerCartoons.add(cartoon);
        }

        return byProvider;
    }

    public Set<String> getProviders() {
        return byProvider().keySet();
    }

    /**
     * Created a sorted cartoons list from provider
     * @param providerName
     * @return
     */
    public List<Cartoon> getCartoonsForProvider(String providerName) {
        List<Cartoon> cartoons = byProvider().get(providerName);

        cartoons.sort(new Comparator<Cartoon>() {
            @Override
            public int compare(Cartoon cartoon1, Cartoon cartoon2) {
                if (cartoon1.equals(cartoon2)) {
                    return 0;
                }
                return cartoon1.getName().compareTo(cartoon2.getName());
            }
        });

        return cartoons;
    }


}

