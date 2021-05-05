/**
 * BirtSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.eclipse.birt.report.soapengine.endpoint;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.eclipse.birt.report.context.BirtContext;
import org.eclipse.birt.report.context.IContext;
import org.eclipse.birt.report.resource.BirtResources;
import org.eclipse.birt.report.resource.ResourceConstants;
import org.eclipse.birt.report.soapengine.api.GetUpdatedObjects;
import org.eclipse.birt.report.soapengine.api.GetUpdatedObjectsResponse;
import org.eclipse.birt.report.soapengine.api.Operation;
import org.eclipse.birt.report.soapengine.processor.BaseProcessorFactory;
import org.eclipse.birt.report.soapengine.processor.IComponentProcessor;
import org.eclipse.birt.report.soapengine.processor.IProcessorFactory;

public class BirtSoapBindingImpl implements BirtSoapPort
{

	public GetUpdatedObjectsResponse getUpdatedObjects(
			GetUpdatedObjects request ) throws java.rmi.RemoteException
	{
		IProcessorFactory processorFactory = BaseProcessorFactory.getInstance( );

		GetUpdatedObjectsResponse response = new GetUpdatedObjectsResponse( );
		Operation[] ops = request.getOperation( );

		IContext context = BirtContext.getInstance( );
		if ( context.getBean( ).getException( ) != null )
		{
			AxisFault fault = new AxisFault( );
			fault.setFaultCode( new QName(
					"BirtSoapBindingImpl.getUpdatedObjects( )" ) ); //$NON-NLS-1$
			fault.setFaultString( context.getBean( ).getException( )
					.getLocalizedMessage( ) );
			throw fault;
		}

		for ( int i = 0; i < ops.length; i++ )
		{
			Operation op = ops[i];
			IComponentProcessor processor = processorFactory.createProcessor(
					context.getBean( ).getCategory( ), op.getTarget( )
							.getType( ) );

			if ( processor == null )
			{
				AxisFault fault = new AxisFault( );
				fault.setFaultCode( new QName(
						"BirtSoapBindingImpl.getUpdatedObjects( )" ) ); //$NON-NLS-1$
				fault
						.setFaultString( BirtResources
								.getMessage(
										ResourceConstants.SOAP_BINDING_EXCEPTION_NO_HANDLER_FOR_TARGET,
										new Object[]{op.getTarget( )} ) );
				throw fault;
			}

			//BEGIN IBM-SPM-BIRT-CODE-CHANGE
			// dont allow the exception to be re-thrown as it goes to the UI, write to stdio
			try {
			//END IBM-SPM-BIRT-CODE-CHANGE				
			
				processor.process(context, op, response);
				
			//BEGIN IBM-SPM-BIRT-CODE-CHANGE				
			} catch (Exception e) {
				System.out
						.println("Ensuring the exception is not displayed on the users browser");
				System.out.print(e.getLocalizedMessage());
			}
			//END IBM-SPM-BIRT-CODE-CHANGE
		}

		return response;
	}
}