package com.evuv.expressions;

import com.evuv.exceptions.EventBindingException;

import java.util.Collection;
import java.util.Map;

public class CollectionValueExpression <T extends Collection<U>, U> implements Expression<T>, BindedExpression<T> {
    private T value;

    public CollectionValueExpression(T val) {
        this.value = val;
    }
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Expression getLeft() {
        return null;
    }

    @Override
    public Expression getRight() {
        return null;
    }

    @Override
    public void setLeft(Expression left) {
        
    }

    @Override
    public void setRight(Expression right) {

    }

    @Override
    public BindedExpression<T> bind(Map<String, Object> event) throws EventBindingException {
        return new CollectionValueExpression<>(value);
    }
}
