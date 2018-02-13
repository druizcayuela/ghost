package com.cayuela.ghost.visitorpattern;


import com.cayuela.ghost.tree.Node;

/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>Interface to use the Visitor PATTERN.</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public interface TreeVisitor {
    /**
     * The Logic to apply with Visitor Pattern
     *
     * @param node
     */
    void visit(Node node);
}