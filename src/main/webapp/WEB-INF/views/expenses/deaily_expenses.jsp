<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <%@include file="../base.jsp" %>
  <title>Expenses</title>

  <script>
    $(document).ready(function () {
      resetTable();
      resetForm();
    });

    function getCategories(){
      $.ajax({
        type: "GET",
        url: "api/categories",
        success: function (response) {
          $("#category > option").remove();
          for (let i = 0; i < response.length; i++) {
            $("#category").append(new Option(response[i].name, response[i].id));
          }
        },
        error: function (err) {
          console.log(err);
          alert("error form getCategories");
        }
      });
    }

    function resetForm() {
      $('#addExpenseTitle').show();
      $('#updateExpenseTitle').hide();
      $('#saveExpenseBtn').show();
      $('#updateExpenseBtn').hide();
      $('#idField').hide();

      $('#expenseForm')[0].reset();
      $("#expenseDate").val(new Date().toISOString().split('T')[0]);

      getCategories();
    }

    function resetTable() {
      $.ajax({
        type: "GET",
        url: "api/expenses/user/" + ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id},
        success: function (response) {
          $('.expenseTable_tr').remove();
          for (let i = 0; i < response.length; i++) {
            $("#expenseTable")
                .append(
                    '<tr class="expenseTable_tr">' +
                    '<td>' + (i+1) + '</td>' +
                    '<td>' + response[i].expenseDate + '</td>' +
                    '<td>' + response[i].description + '</td>' +
                    '<td>' + response[i].amount + '</td>' +
                    '<td>' + response[i].category.name + '</td>' +
                    '<td>' +
                    '<input type="button" class="btn btn-warning" ' +
                    'onclick="editExpenseBtn(' + response[i].id + ')" ' +
                    'value="Edit">' +
                    '</td> ' +
                    '<td>' +
                    '<input type="button" class="btn btn-danger" ' +
                    'onclick="deleteExpenseBtn(' + response[i].id + ');" ' +
                    'value="Delete">' +
                    '</td> ' +
                    '</tr>'
                );
          }
        },
        error: function (err) {
          console.log(err);
          alert("error from getAllRecordAndSetToTable");
        }
      });
    }

    function saveExpense() {
      $.ajax({
        type: "POST",
        url: "api/expenses/add",
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val(),
          userId: ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id},
          category: {
            id: $("#category").val(),
            name: $("#category option:selected").text()
          }
        }),
        success: function (result) {
          resetTable();
          resetForm();
        },
        error: function (err) {
          console.log(err);
          alert("error from saveExpense()");
        }
      });
    }

    function updateExpense() {
      $.ajax({
        type: "POST",
        url: "api/expenses/update",
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
          id: $("#expenseId").val(),
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val(),
          userId: ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id},
          category: {
            id: $("#category").val(),
            name: $("#category option:selected").text()
          }
        }),
        success: function (result) {
          resetTable();
          resetForm();
        },
        error: function (err) {
          console.log(err);
          alert("error from updateExpense()");
        }
      });
    }

    function editExpenseBtn(id) {
      $.ajax({
        type: "GET",
        url: "expenses/" + id,
        success: function (response) {
          $("#expenseId").val(response.id);
          $("#expenseDate").val(new Date(response.expenseDate).toISOString().split('T')[0]);
          $("#category").val(response.category.id);
          $("#description").val(response.description);
          $("#amount").val(response.amount);

          $('#saveExpenseBtn').hide();
          $('#updateExpenseBtn').show();

          $('#addExpenseTitle').hide();
          $('#updateExpenseTitle').show();

          $('#idField').show();
        },
        error: function (err) {
          console.log(err);
          alert("error from editExpenseBtn");
        }
      });
    }

    function deleteExpenseBtn(id) {
      $.ajax({
        type: "DELETE",
        url: "/expenses/delete/" + id,
        success: function (response) {
          resetTable();
        },
        error: function (err) {
          console.log(err);
          alert("error form deleteExpenseBtn");
        }
      });
    }
  </script>
</head>

<body>
<%@include file="../navber.jsp" %>
<div class="container">
  <h1 class="text-center">Expense List</h1>
  <div class="container mt-3">
    <div class="row">
      <%@include file="expense_form.jsp" %>
      <%@include file="expense_list.jsp" %>
    </div>
  </div>
</div>
</body>
</html>