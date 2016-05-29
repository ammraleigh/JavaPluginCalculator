package calculator;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.lang.math.NumberUtils;


//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import calculator.service.OperatorService;
import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;

@Component (name = Calculator.COMPONENT_NAME)
public class Calculator  {

    public static final String COMPONENT_NAME = "CalculatorComponent"; 
    public static final String COMPONENT_LABEL = "OperatorService Component";

    private static OperatorService calc;
    private static Map<String, OperatorInfo> m_operators = new HashMap<String, OperatorInfo>();
    
    public Calculator()  {
    	// No work to do
 	}

	public void RunCalculator() {
		
	      Scanner scan = new Scanner(System.in);
          Deque <Float> operandArray = new LinkedList<Float>();
          Deque <String> operatorArray = new LinkedList<String>();
          
  	      
	      try {
	          for (;;){
	        	  
	              System.out.print("$ ");

	              StringTokenizer tokenizer = new StringTokenizer(scan.nextLine()," ");
	              
        		  operandArray.clear();
        		  operatorArray.clear();            		  

        		  while(tokenizer.hasMoreTokens()){
	                  String nextToken = tokenizer.nextToken();
		              if (NumberUtils.isNumber(nextToken)) {
	                	  operandArray.push((Float.valueOf(nextToken)));                		                  		  
	                  }  
	                  else 
	                  {
	                	  operatorArray.offerLast(nextToken);                		  
	                  }
	              }
	              
	              if (!operatorArray.isEmpty() ){
	            	  
	        	      Boolean validResult = true;

	            	  if (operatorArray.peekFirst().equals("rpn"))	{
	            		  operatorArray.pop();
    	            	  validResult = false;     	

    	            	  calc = getOperator(operatorArray.peekFirst());    

    	            	  while ((calc != null) && (operandArray.size()>=2 && operatorArray.size()>=1)) {
    	            		  calc = getOperator(operatorArray.peekFirst());    
    	            		  validResult = calc.getResult(operandArray, operatorArray);	        	            	  
	            		  }
    	            	  
            			  if (validResult) {
	                          System.out.println("Result = " + operandArray.pop());
            			  }
            			  else {
        	            	  System.out.println("Invalid format");
        	        		  operandArray.clear();
        	        		  operatorArray.clear();            		  
            			  }
	            	  }
	            	  else {
    	            	  System.out.println("Invalid format");	            		  
    	        		  operandArray.clear();
    	        		  operatorArray.clear();            		  
	            	  }
	              } 
	          } 
	      } catch (Exception e) {
			scan.close();
	      } 
	 }
		   	
    @Activate
    public void activate() {
    		// Note: Calculator will not activate until at least one service attaches
            //System.out.println("Activating the " + COMPONENT_LABEL);
        	RunCalculator();
        	     }
 
    /**
     * Called when any of the SCR Components required dependencies become
     * unsatisfied.
     */
    @Deactivate
    public void deactivate() {
    	//System.out.println("Deactivating the " + COMPONENT_LABEL);
    }
 
    @Reference (multiple=true,dynamic=true)
    public void setOperatorService(OperatorService calc) {
    	//System.out.println("Adding " +  calc.getClass().getName());

    }
 
    public void unsetOperatorService( OperatorService calc) {
        //System.out.println("Removing " + calc.getClass().getName());
    }
    
    public OperatorService getOperator(String name)
    {
        OperatorInfo info = m_operators.get(name);
        if (info == null)
        {
            return null;
        }
        else
        {
            return info.m_operator;
        }
    }

    public void addOperator(String name, OperatorService operator)
    {
        m_operators.put(name, new OperatorInfo(name, operator));
    }

    public void removeOperator(String name)
    {
        m_operators.remove(name);

    }

    private static class OperatorInfo
    {
        private final String m_name;
        private final OperatorService m_operator;
        
        public OperatorInfo(String name, OperatorService operator)
        {
            m_name = name;
            m_operator = operator;
        }
    }    
}
