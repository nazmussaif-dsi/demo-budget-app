<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Details for ${user.name}</title>
</head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<body>
<div class="container">
  <h1 class="text-center">User Details</h1>
  <h3>Name: ${user.name}</h3>
  <h3>Email:
    <a href="mailto:${user.email}">${user.email}</a>
  </h3>
</div>
</body>
</html>
