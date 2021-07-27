<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>New User Form</title>
</head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<body>
<div class="container">
  <h1 class="text-center">Add New User</h1>
  <form:form action="/users/add" method="post" modelAttribute="user">
    <div class="mb-3">
      <form:input path="name" placeholder="Enter name"
                  class="form-control"/>
    </div>

    <div class="mb-3">
      <form:input path="email" placeholder="Enter email"
                  class="form-control"/>
    </div>

    <div class="text-left">
      <form:button
          class="btn btn-primary">Submit</form:button>
    </div>
  </form:form>
</div>
</body>
</html>
