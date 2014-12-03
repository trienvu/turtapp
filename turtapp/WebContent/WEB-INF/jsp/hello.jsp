<%@include file="/WEB-INF/jsp/include.jsp/"%>
<html>
<head>
<title><fmt:message key="title"></fmt:message></title>
</head>
<body>
	<h1>

		<fmt:message key="heading" />
	</h1>

	<p>
		<fmt:message key="greeting" />
		<c:out value="${model.now}"></c:out>
	</p>
	
	<h3>Products</h3>
	<c:forEach items="${model.products}" var="prod">
		 <c:out value="${prod.description}"></c:out>
		 <i><c:out value="${prod.price}"></c:out>  </i><br/><br/>
	</c:forEach>
	 <br>
    <a href="<c:url value="priceincrease.htm"/>">Increase Prices</a>
    <br>
</body>

</html>