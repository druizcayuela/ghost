package com.cayuela.ghost.game;

/**
 * {@code ghost}
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>Enum which handle the users</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public enum Player {

    HUMAN, COMPUTER;

    public static Player getNextPlayer(Player player) {
        if (player == null) {
            return Player.HUMAN;
        }

        return (player == Player.HUMAN) ? Player.COMPUTER : Player.HUMAN;
    }
}