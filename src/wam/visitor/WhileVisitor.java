/**
 * WhileVisitor.java
 *
 * A visitor for the AST created by the parsing process. This class is intended
 * as a base class for other visitors. This visitor merely visites each node in
 * the AST.
 */

package wam.visitor;

import wam.ast.*;
import wam.exception.*;

public abstract class WhileVisitor<T> {
    /**
     * Visit a node and perform node specific actions.
     *
     * @param node The node to operate on.
     */
    public T visit(final Node node) throws WamException {
        throw new NotImplementedException(String.format("No accept method for class %s in %s.",
                    node.getClass().getName(), getClass().getName()));
    }

    public T visit(final Program p) throws WamException {
        return p.getEntry().accept(this);
    }

    public T visit(final CompoundStatement cs) throws WamException {
        T t = null;
        for (Statement s : cs.getStatements())
            t = s.accept(this);
        return t;
    }

    public T visit(final While w) throws WamException {
        w.getCondition().accept(this);
        return w.getStatement().accept(this);
    }

    public T visit(final IfElse ie) throws WamException {
        ie.getCondition().accept(this);
        ie.getIfStatement().accept(this);
        return ie.getElseStatement().accept(this);
    }

    public T visit(final Assignment a) throws WamException {
        a.getIdentifier().accept(this);
        return a.getValue().accept(this);
    }

    public T visit(final Skip s) throws WamException {
        return null;
    }

    public T visit(final TryStatement t) throws WamException {
        T tmp = t.getTryStatement().accept(this);
        t.getCatchStatement().accept(this);
        return tmp;
    }

    public T visit(final BinaryOperation bo) throws WamException {
        bo.getLeftHand().accept(this);
        return bo.getRightHand().accept(this);
    }

    public T visit(final UnaryOperation uo) throws WamException {
        return uo.getExpression().accept(this);
    }

    public T visit(final IntegerValue v) throws WamException {
        return null;
    }

    public T visit(final BooleanValue v) throws WamException {
        return null;
    }

    public T visit(final Identifier id) throws WamException {
        return null;
    }
}
