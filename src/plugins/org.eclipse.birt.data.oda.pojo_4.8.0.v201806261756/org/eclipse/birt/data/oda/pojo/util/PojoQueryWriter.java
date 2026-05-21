/*******************************************************************************
 * Copyright (c) 2013 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.data.oda.pojo.util;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
/*BEGIN CURAM-BIRT-CODE-CHANGE*/
import org.eclipse.core.internal.runtime.XmlProcessorFactory;
//import javax.xml.parsers.DocumentBuilderFactory;
/*END CURAM-BIRT-CODE-CHANGE*/
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.birt.data.oda.pojo.api.Constants;
import org.eclipse.birt.data.oda.pojo.querymodel.IColumnsMapping;
import org.eclipse.birt.data.oda.pojo.querymodel.PojoQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Write a PojoQuery instance into a XML
 */
/* BEGIN CURAM-BIRT-CODE-CHANGE */
@SuppressWarnings({ "java:S1118", "java:S2147" }) // For not patched code.
/* END CURAM-BIRT-CODE-CHANGE */
public class PojoQueryWriter
{
	public static String write( PojoQuery query ) throws OdaException
	{
		if ( query == null )
		{
			return null;
		}
		/*BEGIN CURAM-BIRT-CODE-CHANGE*/
		//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
		/*END CURAM-BIRT-CODE-CHANGE*/
		try
		{
			/*BEGIN CURAM-BIRT-CODE-CHANGE*/
			//DocumentBuilder builder = factory.newDocumentBuilder( );
			DocumentBuilder builder = XmlProcessorFactory.createDocumentBuilderWithErrorOnDOCTYPE();
			/*END CURAM-BIRT-CODE-CHANGE*/
			Document doc = builder.newDocument( );
			Element root = doc.createElement( Constants.ELEMENT_ROOT );
			doc.appendChild( root );
			if ( query.getVersion( ) != null )
			{
				root.setAttribute( Constants.ATTR_POJOQUERY_VERSION, query.getVersion( ) );
			}
			if ( query.getAppContextKey( ) != null )
			{
				root.setAttribute( Constants.ATTR_POJOQUERY_APPCONTEXTKEY, query.getAppContextKey( ) );
			}
			if ( query.getDataSetClass( ) != null )
			{
				root.setAttribute( Constants.ATTR_POJOQUERY_DATASETCLASS, query.getDataSetClass( ) );
			}
			for ( IColumnsMapping cm : query.getColumnsMappings( ) )
			{
				root.appendChild( cm.createElement( doc ) );
			}
			
			/*BEGIN CURAM-BIRT-CODE-CHANGE*/
			//TransformerFactory tff = TransformerFactory.newInstance( );
			TransformerFactory tff = XmlProcessorFactory.createTransformerFactoryWithErrorOnDOCTYPE();
			/*END CURAM-BIRT-CODE-CHANGE*/
			tff.setAttribute("indent-number", 4); //$NON-NLS-1$
			Transformer tf = tff.newTransformer( );
			tf.setOutputProperty( OutputKeys.INDENT, "yes" ); //$NON-NLS-1$
			
			StringWriter sw = new StringWriter( );
			StreamResult sr = new StreamResult( sw );
			DOMSource source = new DOMSource( doc );
			tf.transform( source, sr );
			return sw.toString( );
		}
		catch ( ParserConfigurationException e )
		{
			throw new OdaException( e );
		}
		catch ( TransformerConfigurationException e )
		{
			throw new OdaException( e );
		}
		catch ( TransformerException e )
		{
			throw new OdaException( e );
		}
	}
}
