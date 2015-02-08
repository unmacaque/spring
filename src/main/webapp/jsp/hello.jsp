<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<body>

<h2>Hello ${username}!</h2>

<p>It's good to see you here!</p>

<p><a href="<c:url value="j_spring_security_logout" />">Logout</a></p>

</body>
</html>
