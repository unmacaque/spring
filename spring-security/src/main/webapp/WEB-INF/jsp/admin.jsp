<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@page session="true"%>
<html>
<body>

<h2>Hello ${username}!</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
Enjoy your administrative privileges.
</sec:authorize>

<p><a href="<c:url value="logout" />">Logout</a></p>

</body>
</html>
