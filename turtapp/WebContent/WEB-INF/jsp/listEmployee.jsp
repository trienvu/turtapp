<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<p>
		<fmt:message key="greeting" />
		<c:out value="${employeeModel.appraisalRemarks}"></c:out>
	</p>
</body>
</html>