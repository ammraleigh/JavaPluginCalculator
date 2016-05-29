package calculator.service.rpn.sub;

import java.util.Deque;

import aQute.bnd.annotation.component.*;
import calculator.service.OperatorService;

@Component
public class RpnSub implements OperatorService {

	@Override
	public boolean getResult(Deque<Float> operandArray,
			Deque<String> operatorArray) {

	      String operator = operatorArray.pop();
	      
		  if (operator.equals("-") || operator.equals("sub")) {
			  operandArray.push(operandArray.pop() - operandArray.pop());
		  }
		  
		return true;
	}


}
