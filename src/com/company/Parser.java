package com.company;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;





/**
 * Parser Class.
 * Streams the Chosen File, checks for the specifications and throws exceptions if
 * Wrong file format, wrong file content format, file not found or corrupted.
 * Adds the content of the file
 * Parses the file According to the Styling requirements of the Assignment
 */

public class Parser {


    /**
     * Array of Text elements
     */


    public ArrayList<TextElements> elements;



        /**
         * Constructor Parser
         */


    public Parser() {

        this.elements = new ArrayList<>();
    }






        /**
         *  Method parse, getting the file and parsing it

         * @param destination
         * @throws WrongFormatException
         * @throws IOException
         * @throws WrongFileContentFormatException
         */
    public void parse(String destination) throws WrongFormatException, IOException, WrongFileContentFormatException {


        FileInputStream fstream = new FileInputStream(new File(destination));

        /**
         * Getting the destination of the file, checking if its .msg
         * If not - throws Exceptions
         */

        int destinationIndex = destination.lastIndexOf('.');
        String fileFormatCheck = destination.substring(destinationIndex + 1);
        if (!fileFormatCheck.equals("msg"))
            throw new WrongFormatException();


        ArrayList<String> allFileContent = new ArrayList<>();
        String TIME_STAMP = "";
        String NICK_NAME = "";
        String CONTENT = "";
        String fileContent = "";
        boolean space = false;


        while (true) {
            int a = fstream.read();
            if (a == -1) {
                return;
            }

            fileContent += (char) a;
            if ((char) a == '\n') {

                if (space) {
                    space = false;
                    fileContent = "";
                    continue;
                }

                allFileContent.add(fileContent);

                if (allFileContent.size() == 3) {
                    if (allFileContent.get(0).startsWith("TIME:")) {
                        TIME_STAMP = allFileContent.get(0).substring(5, allFileContent.get(0).length() - 2);
                    } else {
                        throw new WrongFileContentFormatException();
                    }

                    if (allFileContent.get(1).startsWith("USER:")) {
                        NICK_NAME = allFileContent.get(1).substring(5, allFileContent.get(1).length() - 2);
                    } else {
                        throw new WrongFileContentFormatException();
                    }

                    if (allFileContent.get(2).startsWith("MESSAGE:")) {
                        CONTENT = allFileContent.get(2).substring(8, allFileContent.get(2).length() - 2);

                    } else {
                        throw new WrongFileContentFormatException();

                    }

                    space = true;
                    allFileContent.clear();
                    elements.add(new TextElements(TIME_STAMP, NICK_NAME, CONTENT));


                }
                fileContent = "";

            }
        }


    }




        /**
         * Method - styling.
         * Styles the parsed document
         * Displays the file content
         * @param styledDocument
         * @throws BadLocationException
         */

    public void styling(StyledDocument styledDocument) throws BadLocationException {


        String a = "";
        for (TextElements element : elements)
            try {
                styledDocument.insertString(styledDocument.getLength(), "[" + element.getTIME_STAMP() + "]", styledDocument.getStyle(FileStyler.REGULAR));

                if (element.getNICK_NAME().equals(a))
                    styledDocument.insertString(styledDocument.getLength(), "...", styledDocument.getStyle(FileStyler.NICK_BOLD_BLUE));
                else
                    styledDocument.insertString(styledDocument.getLength(), element.getNICK_NAME() + ":", styledDocument.getStyle(FileStyler.NICK_BOLD_BLUE));
                a = element.getNICK_NAME();


                int characters = 0;
                for (int i = 0; i < element.getCONTENT().length() - 1; i++) {
                    if (element.getCONTENT().charAt(i) == ':') {
                        if (element.getCONTENT().charAt(i + 1) == ')') {
                            styledDocument.insertString(styledDocument.getLength(), element.getCONTENT().substring(characters, i), styledDocument.getStyle(FileStyler.CONTENT_BOLD));
                            styledDocument.insertString(styledDocument.getLength(), "x", styledDocument.getStyle(FileStyler.SMILE_HAPPY));
                            characters = i + 2;
                        }
                    }

                    if (element.getCONTENT().charAt(i) == ':') {
                        if (element.getCONTENT().charAt(i + 1) == '(') {
                            styledDocument.insertString(styledDocument.getLength(), element.getCONTENT().substring(characters, i), styledDocument.getStyle(FileStyler.CONTENT_BOLD));
                            styledDocument.insertString(styledDocument.getLength(), "x", styledDocument.getStyle(FileStyler.SMILE_SAD));
                            characters = i + 2;
                        }
                    }
                }
                styledDocument.insertString(styledDocument.getLength(), element.getCONTENT().substring(characters, element.getCONTENT().length()), styledDocument.getStyle(FileStyler.CONTENT_BOLD));
                styledDocument.insertString(styledDocument.getLength(), "\n", styledDocument.getStyle(FileStyler.REGULAR));

            } catch (BadLocationException e) {
                e.printStackTrace();
            }



    }


        /**
         * Method deleteAll
         * Deletes all content in the frame
         * (Before opening a new file)
         */

    public void deleteAll() {

        elements.clear();
    }


}
