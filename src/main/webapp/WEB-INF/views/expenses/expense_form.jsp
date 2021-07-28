<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>New Expense Form</title>
</head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<body>
<div class="container">
  <h1 class="text-center">Add New Expense</h1>
  <form:form action="/expenses/add" method="post" modelAttribute="expense">
    <div class="mb-3">
      <form:input path="expenseDate" placeholder="Enter Date"
                  class="form-control"/>
    </div>

    <div class="mb-3">
      <form:input path="description" placeholder="Enter Description"
                  class="form-control"/>
    </div>

    <div class="mb-3">
      <form:input path="amount" placeholder="Enter Amount"
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
