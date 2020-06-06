package com.company;

public class Main {

    /**
     * This is the Main class.
     *  Runs the application
     * @param args
     */

    public static void main(String[] args) {

        /**
         * Main runs the parser and styler and shows the file in the UserInterface
         */

        FileStyler fileStyler = new FileStyler();

        Parser chatParser = new Parser();

        UserInterface userInterface = new UserInterface(chatParser, fileStyler);


    }
}
