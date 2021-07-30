<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>New Category</title>
</head>

<body>
<div class="container">
  <h1 class="text-center">Add New Category</h1>
  <form:form action="/categories/add" method="post" modelAttribute="category">
    <div class="mb-3">
      <form:input path="name" placeholder="Enter Category"
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
