package com.company;

/**
 * Class of file elements, returns the elements
 */

public class TextElements {


    /**
     * The private Values given by the requirements
     */
    private String TIME_STAMP;
    private String NICK_NAME;
    private String CONTENT;



    /**
     * Constructor for TextElements
     * @param TIME_STAMP
     * @param NICK_NAME
     * @param CONTENT
     */
    public TextElements(String TIME_STAMP, String NICK_NAME, String CONTENT) {
        this.TIME_STAMP = TIME_STAMP;
        this.NICK_NAME = NICK_NAME;
        this.CONTENT = CONTENT;
    }

    /**
     * Methods to return the elements
     */

    public String getTIME_STAMP() {

        return TIME_STAMP;
    }

    public String getNICK_NAME() {

        return NICK_NAME;
    }

    public String getCONTENT() {
        return CONTENT;
    }
}


