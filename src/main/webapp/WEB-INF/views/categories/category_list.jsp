<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>Categories</title>
</head>

<body>
<jsp:include page="../navber.jsp"/>
<div class="container">
  <h1 class="text-center">Category List</h1>
  <a href="/categories/add" class="btn btn-info" role="button">Add new Category</a>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Category</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="categories">
      <tr>
        <th scope="row">${categories.id}</th>
        <td>${categories.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
