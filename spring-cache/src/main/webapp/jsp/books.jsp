<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

<h1>Books</h1>

<table>
  <tr>
    <th>Title</th>
    <th>Author</th>
    <th>Description</th>
  </tr>
  <c:forEach items="${books}" var="book">
  <tr>
    <td>${book.title}</td>
    <td>${book.author}</td>
    <td>${book.description}</td>
  </tr>
  </c:forEach>
</table>

</body>
</html>
