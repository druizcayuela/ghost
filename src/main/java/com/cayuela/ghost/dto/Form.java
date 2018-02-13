package com.cayuela.ghost.dto;

/**
 * {@code Update} requests.
 * <p>
 * Technical names: {@code Ghost > GET/POST request}
 * <ul>
 * <li>Model to handle the Post request in Ghost Game</li>
 * <li>Model to handle SPRING based requests</li>
 * <li>Do specific data validation for the letter</li>
 * </ul>
 *
 * @author t-systems
 */
public class Form {

    private Character letter;

    /**
     * Gets the letter.
     *
     * @return the letter
     */
    public Character getLetter() {
        return letter;
    }

    /**
     * Sets the letter.
     *
     * @param letter the new letter
     */
    public void setLetter(Character letter) {
        this.letter = letter;
    }
}
