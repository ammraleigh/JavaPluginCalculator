package calculator;

import java.util.Deque;

import calculator.service.*;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DefaultOperator implements OperatorService{

		private OperatorService m_operator;
	    private BundleContext m_context;
	    private ServiceReference<OperatorService> m_ref;

	    public DefaultOperator()
	    {
	    }

	    public DefaultOperator(BundleContext context, ServiceReference<OperatorService> ref)
	    {
	        m_context = context;
	        m_ref = ref;
	    }

	    public void dispose()
	    {
	        if (m_operator != null)
	        {
	            m_context.ungetService(m_ref);
	            m_context = null;
	            m_ref = null;
	            m_operator = null;
	        }
	    }

		@Override
		public boolean getResult(Deque<Float> operandArray, Deque<String> operatorArray) {
			
	        if (m_context == null) {
	        	return false;
	        }
	        
	        try {
	            if (m_operator == null) {
	                m_operator = m_context.getService(m_ref);
	                if (m_operator != null) {
		                return(m_operator.getResult(operandArray, operatorArray));
	                }
	            }
	            else {
	                return(m_operator.getResult(operandArray, operatorArray));	            	
	            }
				
			} catch (Exception e) {
				System.out.println("Invalid format");
			}
	        return false;
		}
		
	}