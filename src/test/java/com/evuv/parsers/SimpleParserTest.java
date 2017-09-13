package com.evuv.parsers;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.evuv.BaseTest;
import com.evuv.exceptions.EventBindingException;
import com.evuv.exceptions.ParserException;
import com.evuv.expressions.BindedExpression;

public class SimpleParserTest extends BaseTest {

	private SimpleConditionParser parser = new SimpleConditionParser();
	
	
	public String loadEvent(String file)
	{
		if (file == null) {
			return null;
		}

		try {

			URI uri = SimpleParserTest.class.getClassLoader().getResource("events/" + file).toURI();

			String event = new String(Files.readAllBytes(Paths.get(uri)));
			return event;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Test
    public void testSimpleStringEquals() throws IOException, JSONException, ParserException, EventBindingException {
       /**
        * {
			   "filter": {
			      "type": "selector",
			      "dimension": "session_sheker",
			      "value": "sheker"
			   }
			}
        */
		

		
        String event = loadEvent("event1.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "selector");
        inner.put("dimension", "session_sheker");
        inner.put("value", "sheker");
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(), false).bind(eventAsMap);
        System.out.println(query);
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testSimpleStringEqualsFalse() throws IOException, JSONException, ParserException, EventBindingException {

        String event = loadEvent("event1.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "selector");
        inner.put("dimension", "session_sheker");
        inner.put("value", "sheker2");
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(), false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	
	@Test
    public void testSimpleNumberEquals() throws IOException, JSONException, ParserException, EventBindingException {

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", "=");
        inner.put("value", 100);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(), false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberEqualsBigNumber() throws IOException, JSONException, ParserException, EventBindingException {

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_big");
        inner.put("op", "=");
        inner.put("value", 10000000000L);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(), false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberEqualsFalse() throws IOException, JSONException, ParserException, EventBindingException {

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", "=");
        inner.put("value", 50);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberBigger() throws IOException, JSONException, ParserException, EventBindingException {
        

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", ">");
        inner.put("value", 10);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberBiggerFalse() throws IOException, JSONException, ParserException, EventBindingException {
        

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", ">");
        inner.put("value", 200);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberSmaller() throws IOException, JSONException, ParserException, EventBindingException {
        

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", "<");
        inner.put("value", 200);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testSimpleNumberSmallerFalse() throws IOException, JSONException, ParserException, EventBindingException {
        

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "measure");
        inner.put("measure", "session_integer_23");
        inner.put("op", "<");
        inner.put("value", 1);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	@Test
    public void testSimpleAndCondition() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	@Test
    public void testMultipleAndCondition() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         },
			         {
			            "op": "=",
			            "measure": "dumber_12",
			            "type": "measure",
			            "value": 12
			         },
			         {
			            "op": ">",
			            "measure": "dumber_4",
			            "type": "measure",
			            "value": 0
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        
        JSONObject third = new JSONObject();
        third.put("type", "measure");
        third.put("measure", "dumber_12");
        third.put("op", "=");
        third.put("value", 12);
        fields.put(third);
        
        JSONObject forth = new JSONObject();
        forth.put("type", "measure");
        forth.put("measure", "dumber_4");
        forth.put("op", ">");
        forth.put("value", 0);
        fields.put(forth);
        
        System.out.println(query.toString());
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testMultipleAndConditionOneFalse() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         },
			         {
			            "op": "=",
			            "measure": "dumber_12",
			            "type": "measure",
			            "value": 12
			         },
			         {
			            "op": ">",
			            "measure": "dumber_4",
			            "type": "measure",
			            "value": 5
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        
        JSONObject third = new JSONObject();
        third.put("type", "measure");
        third.put("measure", "dumber_12");
        third.put("op", "=");
        third.put("value", 12);
        fields.put(third);
        
        JSONObject forth = new JSONObject();
        forth.put("type", "measure");
        forth.put("measure", "dumber_4");
        forth.put("op", ">");
        forth.put("value", 5);
        fields.put(forth);
        
        System.out.println(query.toString());
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	
	@Test
    public void testSimpleAndConditionWithDimenssions() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "dimension": "session_sheker",
			            "type": "dimension",
			            "value": "sheker"
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "selector");
        left.put("dimension", "session_sheker");
        left.put("value", "sheker");
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testSimpleOrCondition() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "or",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 1
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "or");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 1);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testSimpleOrConditionFalse() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "or",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 1
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 100
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "or");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 1);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 100);
        fields.put(right);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	
	@Test
    public void testSimpleNotCondition() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "not",
			      "field": {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 1
			         
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "not");
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 1);
        inner.put("field", left);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testSimpleNotConditionFalse() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 * {
			   "filter": {
			      "type": "not",
			      "field": {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 1000
			         
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "not");
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 1000);
        inner.put("field", left);
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        
        Assert.assertFalse( expr.getValue());
    }
	
	
	@Test
    public void testAndConditionWithInnerOr() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 *{
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": ">",
			            "measure": "dumber_11",
			            "type": "measure",
			            "value": 10
			         },
			         {
			            "type": "or",
			            "fields": [
			               {
			                  "op": "=",
			                  "measure": "dumber_12",
			                  "type": "measure",
			                  "value": 12
			               },
			               {
			                  "op": ">",
			                  "measure": "dumber_4",
			                  "type": "measure",
			                  "value": 1
			               }
			            ]
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        
        JSONObject third = new JSONObject();
        fields.put(third);
        third.put("type", "or");
        JSONArray orFields = new JSONArray();
        third.put("fields", orFields);
        JSONObject orFirst = new JSONObject();
        orFirst.put("type", "measure");
        orFirst.put("measure", "dumber_12");
        orFirst.put("op", "=");
        orFirst.put("value", 12);
        orFields.put(orFirst);
        
        JSONObject orSecond = new JSONObject();
        orSecond.put("type", "measure");
        orSecond.put("measure", "dumber_4");
        orSecond.put("op", ">");
        orSecond.put("value", 1);
        orFields.put(orSecond);
        
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        List<String> flat = expr.toFlatExression();
       flat.forEach(exp -> {
    	   System.out.println(exp);
       });

        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testAndConditionWithInnerOrLaterBindings() throws IOException, JSONException, ParserException, EventBindingException {
 
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "measure");
        right.put("measure", "dumber_11");
        right.put("op", ">");
        right.put("value", 10);
        fields.put(right);
        
        JSONObject third = new JSONObject();
        fields.put(third);
        third.put("type", "or");
        JSONArray orFields = new JSONArray();
        third.put("fields", orFields);
        JSONObject orFirst = new JSONObject();
        orFirst.put("type", "measure");
        orFirst.put("measure", "dumber_12");
        orFirst.put("op", "=");
        orFirst.put("value", 12);
        orFields.put(orFirst);
        
        JSONObject orSecond = new JSONObject();
        orSecond.put("type", "measure");
        orSecond.put("measure", "dumber_4");
        orSecond.put("op", ">");
        orSecond.put("value", 1);
        orFields.put(orSecond);
        
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        List<String> flat = expr.toFlatExression();
        flat.forEach(exp -> {
    	   System.out.println(exp);
        });

        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testExists() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 *{
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": "exists",
			            "measure": "dumber_11",
			            "type": "dimension"
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "selector");
        right.put("dimension", "dumber_11");
        right.put("op", "exists");
        fields.put(right);
        
    
        
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        List<String> flat = expr.toFlatExression();
       flat.forEach(exp -> {
    	   System.out.println(exp);
       });

        Assert.assertTrue( expr.getValue());
    }
	
	
	@Test
    public void testNotExists() throws IOException, JSONException, ParserException, EventBindingException {
        
		/*
		 *{
			   "filter": {
			      "type": "and",
			      "fields": [
			         {
			            "op": "<",
			            "measure": "session_integer_23",
			            "type": "measure",
			            "value": 200
			         },
			         {
			            "op": "exists",
			            "measure": "dumber_11111",
			            "type": "dimension"
			         }
			      ]
			   }
			}
		 */
		
		 

        String event = loadEvent("event2.txt");

        Map<String, Object> eventAsMap = convertJsonToMap(event);

        JSONObject query = new JSONObject();
        JSONObject inner = new JSONObject();
        query.put("filter", inner);
        inner.put("type", "and");
        JSONArray fields = new JSONArray();
        inner.put("fields", fields);
        JSONObject left = new JSONObject();
        left.put("type", "measure");
        left.put("measure", "session_integer_23");
        left.put("op", "<");
        left.put("value", 200);
        fields.put(left);
        JSONObject right = new JSONObject();
        right.put("type", "selector");
        right.put("dimension", "dumber_111111111");
        right.put("op", "exists");
        fields.put(right);
        
    
        
        
        BindedExpression<Boolean> expr = parser.parseCondition(query.toString(),  false).bind(eventAsMap);
        List<String> flat = expr.toFlatExression();
       flat.forEach(exp -> {
    	   System.out.println(exp);
       });

        Assert.assertTrue(! expr.getValue());
    }
	
	
	
	
	
	
	
}
