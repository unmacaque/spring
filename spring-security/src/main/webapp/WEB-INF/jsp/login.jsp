<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

<h2>Login</h2>

<c:if test="${not empty error}">
  <span style="color: red">${error}</span>
</c:if>

<c:if test="${not empty logout}">
  <span style="color: green">${logout}</span>
</c:if>

<p>Enter your credentials to gain privileged access.</p>

<form action="<c:url value="login" />" method="POST">

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

<table>
  <tr>
    <td>User:</td>
    <td><input type="text" name="username"></td>
  </tr>
  <tr>
    <td>Password:</td>
    <td><input type="password" name="password" /></td>
  </tr>
  <tr>
    <td colspan="2"><input name="submit" type="submit" /></td>
  </tr>
</table>

</form>

</body>
</html>
