package calculator.service.rpn.add;


import java.util.Dictionary;
import java.util.Hashtable;
import calculator.service.OperatorService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

	    private BundleContext m_context = null;

	    public void start(BundleContext context)
	    {
	        m_context = context;
	        Dictionary<String, Object> dict = new Hashtable<String, Object>();
	        dict.put(OperatorService.NAME_PROPERTY, "add");
	        m_context.registerService(
	        		OperatorService.class.getName(), new RpnAdd(), dict);
	        dict.put(OperatorService.NAME_PROPERTY, "+");
	        m_context.registerService(
	        		OperatorService.class.getName(), new RpnAdd(), dict);
	    }

	    public void stop(BundleContext context)
	    {
	    }

	}