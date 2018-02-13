package com.cayuela.ghost.exceptions;

/**
 * GHOST Technical Common Runtime Exception
 * Signals an error on accessing an external resource or an internal application error.
 *
 * @author Daniel Ruiz
 */
public class IllegalPlayException extends IllegalArgumentException {
    private static final long serialVersionUID = 1126590095558370837L;

    public IllegalPlayException(String s) {
        super(s);
    }
}
