<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>User List</title>
</head>

<body>
<jsp:include page="../navber.jsp"/>
<div class="container">
  <h1 class="text-center">User List</h1>
  <a href="/users/add" class="btn btn-info" role="button">Add new User</a>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
      <tr>
        <th scope="row">${user.id}</th>
        <td>${user.name}</td>
        <td>${user.email}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
