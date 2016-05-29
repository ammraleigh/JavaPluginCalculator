package calculator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	    private Calculator m_calculator = null;
	    private OperatorTracker m_operatortracker = null;

	    public void start(final BundleContext context)
	    {

            m_calculator = new Calculator();
            m_operatortracker = new OperatorTracker(context, m_calculator);
            
            m_operatortracker.open();

	    }

	    public void stop(BundleContext context)
	    {

	         m_operatortracker.close();

	    }
	}