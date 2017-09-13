package com.evuv.builders;

import com.evuv.EventProperty;
import com.evuv.expressions.AndExpression;
import com.evuv.expressions.BiggerThanExpression;
import com.evuv.expressions.ComparableExpression;
import com.evuv.expressions.ContainsExpression;
import com.evuv.expressions.EqualityExpression;
import com.evuv.expressions.ExistsExpression;
import com.evuv.expressions.Expression;
import com.evuv.expressions.NotExpression;
import com.evuv.expressions.OrExpression;
import com.evuv.expressions.SimplePropertyExpression;
import com.evuv.expressions.SimpleValueExpression;
import com.evuv.expressions.SmallerThanExpression;

public class ExpressionBuilder {
	
	@SuppressWarnings("rawtypes")
	public static Expression<Boolean> and(Expression left, Expression right) {
		AndExpression and = new AndExpression();
		and.setLeft(left);
		and.setRight(right);
		return and;
	}
	
	@SuppressWarnings("rawtypes")
	public static Expression<Boolean> or(Expression left, Expression right) {
		OrExpression or = new OrExpression();
		or.setLeft(left);
		or.setRight(right);
		return or;
	}
	
	@SuppressWarnings("rawtypes")
	public static Expression<Boolean> not(Expression node) {
		NotExpression not = new NotExpression();
		not.setLeft(node);
		return not;
	}
	
	public static <T> Expression<Boolean> bigger(ComparableExpression<T> left, ComparableExpression<T> right) {
		return new BiggerThanExpression<>(left, right);
	}
	
	public static <T> Expression<Boolean> smaller(ComparableExpression<T> left, ComparableExpression<T> right) {
		return new SmallerThanExpression<>(left, right);
	}
	
	public static <T> Expression<Boolean> equals(ComparableExpression<T> left, ComparableExpression<T> right) {
		return new EqualityExpression<>(left, right);
	}
	
	public static Expression<Boolean> contains(ComparableExpression<String> left, ComparableExpression<String> right) {
		return new ContainsExpression<>(left, right);
	}
	
	public static <T extends Comparable<T>> Expression<Boolean> exists(SimplePropertyExpression<T> left) {
		return new ExistsExpression<>(left);
	}
	
	public static <T extends Comparable<T>> SimpleValueExpression<T> value(T t) {
		return  new SimpleValueExpression<T>(t);
	}
	
	public static <T extends Comparable<T>> SimplePropertyExpression<T> prop(String name, Class<T> clazz) {
		EventProperty<T> ev = new EventProperty<T>();
		ev.setPropertyName(name);
		return new SimplePropertyExpression<T>(ev);
	}
}
