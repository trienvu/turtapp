<%@include file="/WEB-INF/jsp/include.jsp"%>

<%-- Redirected because we can't set the welcome page to a virtual URL. --%>
<c:redirect url="/hello.htm"/>

<% 
	int a =3; int b =2;
%>

<%! 
	int a =3;
%>
<%! 
	int y =3;
%>