package calculator.service.rpn.avg;

import java.util.Deque;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import aQute.bnd.annotation.component.*;
import calculator.service.OperatorService;

@Component
public class RpnAvg implements OperatorService {

	@Override
	public boolean getResult(Deque<Float> operandArray,
			Deque<String> operatorArray) {

	      String operator = operatorArray.pop();
	      
		  if (operator.equals("avg")) {
			  DescriptiveStatistics stats = new DescriptiveStatistics();
			  while (!operandArray.isEmpty()){
					stats.addValue(operandArray.pop());				  
			  }		  
			  operandArray.push((float) stats.getMean());
				return true;
		  }
		  
    	  System.out.println("Invalid format: rpn opd1 opd2 op3 ... op1 [op1 = avg] ");    	            		  
		  return false;
	}


}
