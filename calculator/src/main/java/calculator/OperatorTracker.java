package calculator;

import calculator.service.OperatorService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class OperatorTracker extends ServiceTracker<OperatorService, OperatorService> {

	    private final BundleContext m_context;
	    private final Calculator m_frame;

	    public OperatorTracker(BundleContext context, Calculator frame)
	    {
	        super(context, OperatorService.class.getName(), null);
	        m_context = context;
	        m_frame = frame;
	    }

	    public OperatorService addingService(ServiceReference<OperatorService> ref)
	    {
	        OperatorService shape = new DefaultOperator(m_context, ref);
	        processOperator(OperatorEvent.ADDED, ref, shape);
	        return shape;
	    }

	    public void modifiedService(ServiceReference<OperatorService> ref, OperatorService svc)
	    {
	    	processOperator(OperatorEvent.MODIFIED, ref, svc);
	    }


	    public void removedService(ServiceReference<OperatorService> ref, OperatorService svc)
	    {
	    	processOperator(OperatorEvent.REMOVED, ref, svc);
	    }

	    private void processOperator(OperatorEvent event, ServiceReference<OperatorService> ref, OperatorService shape)
	    {
	        String name = (String) ref.getProperty(OperatorService.NAME_PROPERTY);

	        switch (event)
	        {
	            case MODIFIED:
	                m_frame.removeOperator(name);
	                // Purposely let this fall through to the 'add' case to
	                // reload the service.

	            case ADDED:
	                m_frame.addOperator(name, shape);
	                break;

	            case REMOVED:
	                m_frame.removeOperator(name);
	                break;
	        }
	    }

	    private static enum OperatorEvent
	    {
	        ADDED,
	        MODIFIED,
	        REMOVED
	    }
	}