package org.lightspeed;

import org.lightspeed.model.Man;
import org.lightspeed.utility.CopyUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> books = new ArrayList<>();
        List<Man> friends = new ArrayList<>();

        books.add("1984");
        books.add("Brave New World");

        Man original = new Man("Ann", 30, books, friends);
        Man copy = CopyUtils.deepCopy(original);

        System.out.println("Original name: " + original.getName());
        System.out.println("Copied name: " + copy.getName());

        copy.setName("Ryan");
        copy.getFavoriteBooks().add("Fahrenheit 451");

        System.out.println("After modification:");
        System.out.println("Original name: " + original.getName());
        System.out.println("Copied name: " + copy.getName());
        System.out.println("Original favorite books: " + original.getFavoriteBooks());
        System.out.println("Copied favorite books: " + copy.getFavoriteBooks());

        System.out.println("----------------------------------------");


        List<String> aliceBooks = new ArrayList<>();
        aliceBooks.add("1984");
        aliceBooks.add("Brave New World");

        List<String> bobBooks = new ArrayList<>();
        bobBooks.add("Fahrenheit 451");

        List<String> steveBooks = new ArrayList<>();
        steveBooks.add("Great Gatsby");

        List<Man> steveFriends = new ArrayList<>();
        List<Man> bobFriends = new ArrayList<>();
        List<Man> aliceFriends = new ArrayList<>();

        Man alice = new Man("Alice", 30, aliceBooks, aliceFriends);
        Man bob = new Man("Bob", 25, bobBooks, bobFriends);
        Man steve = new Man("Steve", 25, steveBooks, steveFriends);

        alice.addFriend(bob);

        Man copiedAlice = CopyUtils.deepCopy(alice);

        System.out.println("Original Alice: " + alice);
        System.out.println("Copied Alice: " + copiedAlice);

        copiedAlice.addFriend(steve);
        System.out.println("Original Alice after friend addition to copied Alice: " + alice);
        System.out.println("Copied Alice after friend addition: " + copiedAlice);
    }

}