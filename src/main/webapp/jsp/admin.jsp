<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@page session="true"%>
<html>
<body>

<h2>Hello ${username}!</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
Enjoy your administrative privileges.
</sec:authorize>

<p><a href="<c:url value="j_spring_security_logout" />">Logout</a></p>

</body>
</html>
