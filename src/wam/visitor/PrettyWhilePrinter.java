/**
 * PrettyWhilePrinter.java
 *
 * A visitor for printing a While program.
 */

package wam.visitor;

import wam.ast.*;
import wam.exception.*;

public class PrettyWhilePrinter extends WhileVisitor<String> {
    @Override
    public String visit(final CompoundStatement cs) throws WamException {
        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        int numStatements = cs.getStatements().size();

        if (numStatements > 1)
            buffer.append("(");

        for (Statement s : cs.getStatements()) {
            if (first)
                first = false;
            else
                buffer.append(" ; ");
            buffer.append(s.accept(this));
        }

        if (numStatements > 1)
            buffer.append(")");

        return buffer.toString();
    }

    @Override
    public String visit(final While w) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("while ");
        buffer.append(w.getCondition().accept(this));
        buffer.append(" do ");
        buffer.append(w.getStatement().accept(this));
        return buffer.toString();
    }

    @Override
    public String visit(final IfElse ie) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("if ");
        buffer.append(ie.getCondition().accept(this));
        buffer.append(" then ");
        buffer.append(ie.getIfStatement().accept(this));
        buffer.append(" else ");
        buffer.append(ie.getElseStatement().accept(this));
        return buffer.toString();
    }

    @Override
    public String visit(final Assignment a) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(a.getIdentifier().accept(this));
        buffer.append(" := ");
        buffer.append(a.getValue().accept(this));
        return buffer.toString();
    }

    @Override
    public String visit(final Skip s) throws WamException {
        return "skip";
    }

    @Override
    public String visit(final TryStatement s) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("try ");
        buffer.append(s.getTryStatement().accept(this));
        buffer.append(" catch ");
        buffer.append(s.getCatchStatement().accept(this));
        return buffer.toString();
    }

    @Override
    public String visit(final BinaryOperation bo) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        buffer.append(bo.getLeftHand().accept(this));
        buffer.append(" " + bo.getOperator().toString() + " ");
        buffer.append(bo.getRightHand().accept(this));
        buffer.append(")");
        return buffer.toString();
    }

    @Override
    public String visit(final UnaryOperation uo) throws WamException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(uo.getOperator().toString());
        buffer.append(uo.getExpression().accept(this));
        return buffer.toString();
    }

    @Override
    public String visit(final IntegerValue v) throws WamException {
        return "" + v.getValue();
    }

    @Override
    public String visit(final BooleanValue v) throws WamException {
        return "" + v.getValue();
    }

    @Override
    public String visit(final Identifier id) throws WamException {
        return id.getName();
    }
}
