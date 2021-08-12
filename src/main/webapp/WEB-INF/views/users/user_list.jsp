<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>User List</title>

  <script>
    function deleteUserBtn(id) {
      $.ajax({
        type: "DELETE",
        url: "api/users/" + id,
        success: function (response) {
          location.reload();
        },
        error: function (err) {
          console.log(err);
          alert("error from deleteUserBtn: " + err);
        }
      });
    }
  </script>
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
      <th scope="col">User ID</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user" varStatus="counter">
      <tr>
        <th scope="row">${counter.count}</th>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>
          <button class="btn btn-danger" onclick="deleteUserBtn(${user.id})" value="Delete">Delete</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
