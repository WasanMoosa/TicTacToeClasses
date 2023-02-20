package org.tictactoeclass;

import java.util.ArrayList;

/**
 * Menu class represents a menu of Tic Tac Toe. It has a title, and an
 * ArrayList of MenuItems.
 *
 * @author Wasan
 */
public class Menu {
    // title of the menu
    private String title;
    // list of menu items for this menu
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    /**
     * Constructor for creating a menu with a title and a single menu item
     *
     * @param title    the title of the menu
     * @param menuItem the first menu item to be added to the menu
     */
    public Menu(String title, MenuItem menuItem) {
        this.title = title;
        menuItems.add(menuItem);
    }

    /**
     * Adds a menu item to the menu
     *
     * @param menuItem the menu item to be added
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    /**
     * get the title
     *
     * @return title of the menu
     */

    public String getTitle() {
        return title;

    }

    /**
     * show the Top-level menu
     */
    public void showMenu() {

        for (MenuItem items : menuItems) {
            System.out.println(items.getId() + ": " + items.getTitle());
        }

    }
}
