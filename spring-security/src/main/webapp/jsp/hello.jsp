<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page session="true"%>
<html>
<body>

<h2><spring:message code="hello.name" arguments="${username}" /></h2>

<p><spring:message code="hello.greeting"/></p>

<p><a href="<c:url value="j_spring_security_logout" />">Logout</a></p>

</body>
</html>
