<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@page session="true"%>
<html>
<body>

<h2><spring:message code="hello.name" arguments="${username}" /></h2>

<p><spring:message code="hello.greeting"/></p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="/admin"><spring:message code="hello.admin"/></a>
</sec:authorize>

<p><a href="<c:url value="logout" />">Logout</a></p>

</body>
</html>
