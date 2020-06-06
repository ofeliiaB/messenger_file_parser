package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class of the user interface
 * Opens the Interface Window
 * Gets file, parses the file with the public methods from Parser.java
 * Styles the files through the FileStyler.java
 */
public class UserInterface {


    /**
     * Swift Tools - the parts of the GUI of the app
     */

    private JPanel mainPanel;
    private JLabel label;
    private JTextPane textPane;
    private JButton fileChooser;
    private JFrame frame;

    /**
     * Connection to the chatParser and fileStyler classes
     */

    public Parser chatParser;
    public FileStyler fileStyler;




    /**
         * Constructor of the UserInterface

         */

    public UserInterface(Parser chatParser, FileStyler fileStyler)  {

        this.chatParser = chatParser;
        this.fileStyler = fileStyler;


        frame = new JFrame("Chat Viewer");
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        label.setText("");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);

        fileStyler.addStyle(textPane.getStyledDocument());







        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showOpenDialog(frame);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        getFile(file.getPath());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }


                }
            }
        });
    }







    /**
     * display Method
     * displays the styled file
     * @throws BadLocationException
     */

    public void display() throws BadLocationException {


        textPane.setText("");

        chatParser.styling(textPane.getStyledDocument());

        chatParser.deleteAll();

    }

    /**
     * Private Method getFile - Encapsulation Technique
     * Gets and opens the file
     * @param pathtofile
     * @throws BadLocationException
     */


    private void getFile(String pathtofile) throws BadLocationException {


        try {
            chatParser.parse(pathtofile);
            display();
            label.setText(pathtofile);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, " The file is not found. ");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, " The file is not readable or it is corrupted. ");

        }

        /*
         * My Custom Exceptions
         */
        catch (WrongFormatException e) {
            JOptionPane.showMessageDialog(frame, " The file has suffix different to \"msg\". ");

        }
        /**
         * Any other exceptions thrown by the method getFile
         * It is in this specific case WrongFileContentFormatException
         * Polymorphism Technique
         */

        catch (Throwable e) {
            JOptionPane.showMessageDialog(frame, " The file does not follow the specification. ");

        }


    }






}
