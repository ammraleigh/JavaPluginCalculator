package calculator.service.rpn.add;

import java.text.DecimalFormat;
import java.util.Deque;
import java.util.LinkedList;

import junit.framework.TestCase;

public class TestRpnAddService extends TestCase {

    Deque <Float> operandArray = new LinkedList<Float>();
    Deque <String> operatorArray = new LinkedList<String>();
    Boolean validResult = true;    

    public void testBasicAdd() throws Exception {
    	
    	RpnAdd calc = new RpnAdd();
    	
    	operandArray.add((float) 2.5);
    	operandArray.add((float) 3.5);

    	operatorArray.add("add");
    	
    	validResult = calc.getResult(operandArray, operatorArray);
    	
    	assertEquals(true, (boolean)validResult);
    	assertEquals(6.0, operandArray.pop(), 0);
    	
    	operandArray.add((float) 3.5);
    	operandArray.add((float) 4.6);

    	operatorArray.add("+");
    	
    	validResult = calc.getResult(operandArray, operatorArray);
    	
    	assertEquals(true, (boolean)validResult);
    	assertEquals((float)8.1, (operandArray.pop()), 0);
    }
    
}
