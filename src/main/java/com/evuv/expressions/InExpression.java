package com.evuv.expressions;


import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.InOperator;

import java.util.Collection;
import java.util.Map;

public class InExpression<T> implements Expression<Boolean>, BindedExpression<Boolean> {
    protected InOperator<T> inOperator;

    Expression<T> left;
    Expression<Collection<T>> right;

    public InExpression(Expression<T> left, Expression<Collection<T>> right) {
        this.left = left;
        this.right = right;
        inOperator = new InOperator<T>();
    }

    @Override
    public Boolean getValue() {
        return inOperator.op((BindedExpression<T>)left, (BindedExpression<Collection<T>>)right);
    }

    @Override
    public Expression getLeft() {
        return this.left;
    }

    @Override
    public Expression getRight() {
        return this.right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setLeft(Expression left) {
        this.left = left;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InExpression<T> bind(Map<String, Object> event) throws EventBindingException {
        BindedExpression<T> bindedLeft = left.bind(event);
        BindedExpression<Collection<T>> bindedRight =  right.bind(event);
        return new InExpression<T>((Expression<T>)bindedLeft, (Expression<Collection<T>>)bindedRight);
    }
}
