<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style type="text/css">
.error { color: red; }
</style>
</head>
<body>

<h1>Validation Example</h1>

<c:if test="${not empty formData}">
  <strong>Server received your data: ${formData}</strong>
</c:if>

<p>Enter all fields and click on submit.</p>

<form:form action="/form" modelAttribute="data">
<table>
  <tr>
    <td><form:label path="shortName">Short name</form:label></td>
    <td><form:input path="shortName" /></td>
    <td><form:errors path="shortName" cssClass="error" /></td>
  </tr>
  <tr>
    <td><form:label path="shortInteger">Short Integer</form:label></td>
    <td><form:input path="shortInteger" /></td>
    <td><form:errors path="shortInteger" cssClass="error" /></td>
  </tr>
  <tr>
    <td><form:label path="comment">Comment</form:label></td>
    <td><form:textarea path="comment" id="comment"></form:textarea></td>
    <td><form:errors path="comment" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" /></td>
  </tr>
</table>

</form:form>

</body>
</html>
