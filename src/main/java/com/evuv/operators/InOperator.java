package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

import java.util.Collection;

public class InOperator<T> {
    public boolean op(BindedExpression<T> left, BindedExpression<Collection<T>> right) {
        if (left.getValue() != null && right.getValue() != null) {
            return right.getValue().contains(left.getValue());
        }
        return false;
    }
}
