package com.cayuela.ghost.dto;

/**
 * {@code Update} requests.
 * <p>
 * Technical names: {@code Ghost > GET/POST request}
 * <ul>
 * <li>Model to handle the response in Ghost Game</li>
 * <li>Model to handle SPRING based requests</li>
 * <li>No validation</li>
 * </ul>
 *
 * @author t-systems
 */
public class GameResponse {

    private String string = "";
    private String message = "";
    private Character play = null;

    public GameResponse(String string, String message) {
        this.string = string;
        this.message = message;
    }

    /**
     * Gets the string.
     *
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the string.
     *
     * @param string the new string
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the play.
     *
     * @return the play
     */
    public Character getPlay() {
        return play;
    }
}
