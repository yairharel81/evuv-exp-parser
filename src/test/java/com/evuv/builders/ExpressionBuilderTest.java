package com.evuv.builders;

import com.evuv.BaseTest;
import com.evuv.GenericNumber;
import com.evuv.exceptions.EventBindingException;
import com.evuv.expressions.BindedExpression;
import com.evuv.expressions.ComparableExpression;
import com.evuv.expressions.Expression;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExpressionBuilderTest  extends BaseTest {
	

	@Test
	public void testSimple1() throws EventBindingException {
		ComparableExpression<String> left = ExpressionBuilder.prop("A", String.class);
		ComparableExpression<String> right = ExpressionBuilder.value("V1");
		Expression<Boolean> eq = ExpressionBuilder.equals(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", "V1");
		BindedExpression<Boolean> bindedExp = eq.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());
		
	}
	
	@Test
	public void testSimple2() throws EventBindingException {
		ComparableExpression<String> left = ExpressionBuilder.prop("A", String.class);
		ComparableExpression<String> right = ExpressionBuilder.value("V1");
		Expression<Boolean> eq = ExpressionBuilder.equals(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", "V2");
		BindedExpression<Boolean> bindedExp = eq.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	@Test
	public void testBigger() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eq = ExpressionBuilder.bigger(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000.0);
		BindedExpression<Boolean> bindedExp = eq.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());	
	}
	
	@Test
	public void testBiggerFalse() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eq = ExpressionBuilder.bigger(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 0.0);
		BindedExpression<Boolean> bindedExp = eq.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	@Test
	public void testSmaller() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> sm = ExpressionBuilder.smaller(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 0);
		BindedExpression<Boolean> bindedExp = sm.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());	
	}
	
	@Test
	public void testSmallerFalse() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> sm = ExpressionBuilder.smaller(left, right);
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000.0);
		BindedExpression<Boolean> bindedExp = sm.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	
	@Test
	public void testAnd() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		ComparableExpression<GenericNumber> left2 = ExpressionBuilder.prop("B", GenericNumber.class);
		ComparableExpression<GenericNumber> right2 = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eRight = ExpressionBuilder.equals(left2, right2);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 0);
		bindings.put("B", 10.0);
		
		Expression<Boolean> and = ExpressionBuilder.and(smLeft, eRight);
		BindedExpression<Boolean> bindedExp = and.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());	
	}
	
	
	@Test
	public void testAndFalse() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		ComparableExpression<GenericNumber> left2 = ExpressionBuilder.prop("B", GenericNumber.class);
		ComparableExpression<GenericNumber> right2 = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eRight = ExpressionBuilder.equals(left2, right2);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000);
		bindings.put("B", 10.0);
		
		Expression<Boolean> and = ExpressionBuilder.and(smLeft, eRight);
		BindedExpression<Boolean> bindedExp = and.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	
	@Test
	public void testOr() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		ComparableExpression<GenericNumber> left2 = ExpressionBuilder.prop("B", GenericNumber.class);
		ComparableExpression<GenericNumber> right2 = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eRight = ExpressionBuilder.equals(left2, right2);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000);
		bindings.put("B", 10.0);
		
		Expression<Boolean> or = ExpressionBuilder.or(smLeft, eRight);
		BindedExpression<Boolean> bindedExp = or.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());	
	}

	
	@Test
	public void testOrFalse() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		ComparableExpression<GenericNumber> left2 = ExpressionBuilder.prop("B", GenericNumber.class);
		ComparableExpression<GenericNumber> right2 = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> eRight = ExpressionBuilder.equals(left2, right2);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000);
		bindings.put("B", 0.0);
		
		Expression<Boolean> or = ExpressionBuilder.or(smLeft, eRight);
		BindedExpression<Boolean> bindedExp = or.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	
	@Test
	public void testNot() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		Expression<Boolean> enot = ExpressionBuilder.not(smLeft);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 1000);
		
		BindedExpression<Boolean> bindedExp = enot.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());	
	}
	
	@Test
	public void testNotFalse() throws EventBindingException {
		ComparableExpression<GenericNumber> left = ExpressionBuilder.prop("A", GenericNumber.class);
		ComparableExpression<GenericNumber> right = ExpressionBuilder.value(new GenericNumber(10.0));
		Expression<Boolean> smLeft = ExpressionBuilder.smaller(left, right);
		
		Expression<Boolean> enot = ExpressionBuilder.not(smLeft);
		
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", 0);
		
		BindedExpression<Boolean> bindedExp = enot.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());	
	}
	
	
	@Test
	public void testInExpression() throws EventBindingException {
		Expression<String> left = ExpressionBuilder.prop("A", String.class);
		Collection<String> values = new HashSet<>();
		values.add("test1");
		values.add("test2");
		values.add("test4");
		Expression<Collection<String>> right = ExpressionBuilder.collection(values);

		Expression<Boolean> inExpression = ExpressionBuilder.in(left, right);

		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", "test1");

		BindedExpression<Boolean> bindedExp = inExpression.bind(bindings);
		Assert.assertTrue(bindedExp.getValue());
	}

	@Test
	public void testInExpressionNegative() throws EventBindingException {
		Expression<String> left = ExpressionBuilder.prop("A", String.class);
		Collection<String> values = new HashSet<>();
		values.add("test1");
		values.add("test2");
		values.add("test4");
		Expression<Collection<String>> right = ExpressionBuilder.collection(values);

		Expression<Boolean> inExpression = ExpressionBuilder.in(left, right);

		Map<String, Object> bindings = new HashMap<>();
		bindings.put("A", "test3");

		BindedExpression<Boolean> bindedExp = inExpression.bind(bindings);
		Assert.assertFalse(bindedExp.getValue());
	}
	
	
	
	
}
