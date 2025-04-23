<%-----------------------------------------------------------------------------
	Copyright (c) 2004 Actuate Corporation and others.
	All rights reserved. This program and the accompanying materials 
	are made available under the terms of the Eclipse Public License v2.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-2.0.html
	
	Contributors:
		Actuate Corporation - Initial implementation.
-----------------------------------------------------------------------------%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="false" buffer="none" %>
<%@ page import="org.eclipse.birt.report.utility.ParameterAccessor,
				 org.eclipse.birt.report.context.BaseAttributeBean,
				 org.eclipse.birt.report.context.ScalarParameterBean" %>

<%-----------------------------------------------------------------------------
	Expected java beans
-----------------------------------------------------------------------------%>
<jsp:useBean id="attributeBean" type="org.eclipse.birt.report.context.BaseAttributeBean" scope="request" />

<%-----------------------------------------------------------------------------
	Hidden parameter control
-----------------------------------------------------------------------------%>
<%
	ScalarParameterBean parameterBean = ( ScalarParameterBean ) attributeBean.getParameterBean( );
	String encodedParameterName = ParameterAccessor.htmlEncode( parameterBean.getName( ) );
	<%-- BEGIN CURAM-BIRT-CODE-CHANGE - added ParameterAccessor.htmlEncode--%>
	String value = ParameterAccessor.htmlEncode( parameterBean.getValue( ));
	<%-- END CURAM-BIRT-CODE-CHANGE --%>
	if( value != null )
	{
%>

<TR>
	<TD NOWRAP></TD>
	<TD NOWRAP WIDTH="100%">
		<INPUT TYPE="HIDDEN" ID="control_type" VALUE="hidden">
		<INPUT TYPE="HIDDEN"
			NAME="<%= encodedParameterName %>"
          <%-- BEGIN CURAM-BIRT-CODE-CHANGE - Removed ParameterAccessor.htmlEncode--%>
			VALUE="<%= value %>"/>
			<%-- END CURAM-BIRT-CODE-CHANGE --%>
		<INPUT TYPE="HIDDEN"
			ID="<%= encodedParameterName + "_displayText" %>"
			VALUE="<%= ParameterAccessor.htmlEncode( ( parameterBean.getDisplayText( ) == null )? "" : parameterBean.getDisplayText( ) ) %>" />				
	</TD>
</TR>

<%
	}
%>