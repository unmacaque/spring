<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

<h2>Login</h2>

<c:if test="${not empty error}">
  <span style="color: red">${error}</span>
</c:if>

<p>Enter your credentials to gain privileged access.</p>

<form action="<c:url value="j_spring_security_check" />" method="POST">

<table>
  <tr>
    <td>User:</td>
    <td><input type="text" name="j_username"></td>
  </tr>
  <tr>
    <td>Password:</td>
    <td><input type="password" name="j_password" /></td>
  </tr>
  <tr>
    <td colspan="2"><input name="submit" type="submit" /></td>
  </tr>
</table>

</form>

</body>
</html>
