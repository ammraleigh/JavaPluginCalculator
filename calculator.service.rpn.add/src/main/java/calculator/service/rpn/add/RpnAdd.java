package calculator.service.rpn.add;

import java.util.Deque;

import aQute.bnd.annotation.component.*;
import calculator.service.OperatorService;

@Component
public class RpnAdd implements OperatorService {

	@Override
	public boolean getResult(Deque<Float> operandArray,
			Deque<String> operatorArray) {

	      String operator = operatorArray.pop();
	      
		  if (operator.equals("+") || operator.equals("add")) {
			  operandArray.push(operandArray.pop() + operandArray.pop());
				return true;
		  }
		  
		  return false;
	}


}
