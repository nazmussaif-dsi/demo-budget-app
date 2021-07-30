<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>New Expense Form</title>
</head>

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
