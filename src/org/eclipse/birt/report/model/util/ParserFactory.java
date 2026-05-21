/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.model.util;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
/*BEGIN CURAM-BIRT-CODE-CHANGE*/
import org.eclipse.core.internal.runtime.XmlProcessorFactory;
/*END CURAM-BIRT-CODE-CHANGE*/
import org.xml.sax.SAXException;

/**
 * The XML parser factory. Internally, this factory use the xml parser pool to
 * improve the performance.
 */

/* BEGIN CURAM-BIRT-CODE-CHANGE */
@SuppressWarnings({ "java:S3010" }) // For not patched code.
/* END CURAM-BIRT-CODE-CHANGE */
public class ParserFactory
{
	/**
	 * Single instance.
	 */
	private static ParserFactory factory;

	/**
	 * Singleton pool instance
	 */

	private static XMLParserPool pool;

	private ParserFactory( )
	{
		pool = new XMLParserPoolImpl( );
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the factory instance
	 */

	public static ParserFactory getInstance( ) /*BEGIN CURAM-BIRT-CODE-CHANGE*/throws ParserConfigurationException, SAXException/*END CURAM-BIRT-CODE-CHANGE*/
	{
		if ( factory == null )
		{
			/*BEGIN CURAM-BIRT-CODE-CHANGE*/
			//factory = new ParserFactory( );
			XmlProcessorFactory.createSAXParserNoExternal();
			/*END CURAM-BIRT-CODE-CHANGE*/
			
		}

		return factory;
	}

	/**
	 * Retrieves a parser from the pool given specified properties and features.
	 * If parser can't be created using specified properties or features, an
	 * exception can be thrown.
	 * 
	 * @param properties
	 *            a map of a parser properties and their values.
	 * @return A parser instance with given properties. *
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */

	public SAXParser getParser( Map<String, ?> properties )
			throws SAXException, ParserConfigurationException
	{
		return pool.get( properties );
	}

	/**
	 * Retrieves a parser from the pool given specified properties and features.
	 * If parser can't be created using specified properties or features, an
	 * exception can be thrown.
	 * 
	 * @param parser
	 *            a parser instance with given properties.
	 * @param properties
	 *            a map of a parser properties and their values.
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	/* BEGIN CURAM-BIRT-CODE-CHANGE */
	@SuppressWarnings({ "all" }) // For not patched code.
	/* END CURAM-BIRT-CODE-CHANGE */
	public void releaseParser( SAXParser parser, Map<String, ?> properties )
			throws SAXException, ParserConfigurationException
	{
		pool.release( parser, properties );
	}
}
