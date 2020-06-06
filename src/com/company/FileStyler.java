package com.company;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * This is the FileStyler class which add styles to the file
 * According to the Assignment Requirements
 *
 */

public class FileStyler {




    public static String REGULAR = "REGULAR";
    public static String CONTENT_BOLD = "CONTENT_BOLD";
    public static String NICK_BOLD_BLUE = "NICK_BOLD_BLUE";
    public static String SMILE_HAPPY = "SMILE_HAPPY";
    public static String SMILE_SAD = "SMILE_SAD";





    /**
     * The addStyle Method - describes how the content of the file should look
     * @param styledDocument
     */





    public void addStyle(StyledDocument styledDocument) {

        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style norm = styledDocument.addStyle(REGULAR,defaultStyle);
        StyleConstants.setFontSize(norm,18);

        Style BOLD = styledDocument.addStyle(CONTENT_BOLD, norm);
        BOLD.addAttribute(StyleConstants.Bold, true);

        Style BOLD_BLUE = styledDocument.addStyle(NICK_BOLD_BLUE, BOLD);
        StyleConstants.setForeground(BOLD_BLUE, Color.blue);

        Style HAPPY = styledDocument.addStyle(SMILE_HAPPY, defaultStyle);
        StyleConstants.setIcon(HAPPY, new ImageIcon("Smiles/smile_happy.gif"));

        Style SAD = styledDocument.addStyle(SMILE_SAD, defaultStyle);
        StyleConstants.setIcon(SAD, new ImageIcon("Smiles/smile_sad.gif"));



    }
}
