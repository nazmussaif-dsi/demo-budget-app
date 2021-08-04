<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <%@include file="../base.jsp" %>

  <script>
    $(document).ready(function () {
      formReset();
      getAllRecordAndSetToTable();

      $('#saveExpense').click(function () {
        $.ajax({
          type: "POST",
          url: "expenses/add",
          data: {
            expenseDate: $("#expenseDate").val(),
            description: $("#description").val(),
            amount: $("#amount").val(),
            userId: 1,
            categoryId: $("#category").val()
          },
          success: function (result) {
            getAllRecordAndSetToTable();
            $('#expenseForm')[0].reset();
            $("#expenseDate").val(new Date().toISOString().split('T')[0]);
          },
          error: function (err) {
            alert("error: " + err);
          }
        });
      });
    });

    function formReset(){
      $('#saveExpense').show();
      $('#updateExpense').hide();
      $('#idField').hide();

      $('#expenseForm')[0].reset();
      $("#expenseDate").val(new Date().toISOString().split('T')[0]);
    }

    function getAllCategoriesAndSetToForm() {
      $.ajax({
        type: "GET",
        url: "api/categories/all",
        success: function (response) {
          $("#category > option").remove();
          for (let i = 0; i < response.length; i++) {
            $("#category").append(new Option(response[i].name, response[i].id));
          }
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }

    function getCategoryAndSetToForm(id){
      $.ajax({
        type: "GET",
        url: "api/categories/" + id,
        success: function (response) {
          $("#category > option").remove();
          $("#category").append(new Option(response.name, response.id), true, true);
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }

    function getAllRecordAndSetToTable() {
      $.ajax({
        type: "GET",
        url: "expenses/all",
        success: function (response) {
          $('.expenseTable_tr').remove();
          for (let i = 0; i < response.length; i++) {
            $("#expenseTable")
                .append(
                    '<tr class="expenseTable_tr">' +
                    '<td>' + response[i].id + '</td>' +
                    '<td>' + response[i].expenseDate + '</td>' +
                    '<td>' + response[i].description + '</td>' +
                    '<td>' + response[i].amount + '</td>' +
                    '<td>' + response[i].categoryId + '</td>' +
                    '<td>' +
                    '<input type="button" class="btn btn-warning" ' +
                    'onclick="getExpenseAndSetToForm(' + response[i].id + ')" ' +
                    'value="Edit">' +
                    '</td> ' +
                    '<td>' +
                    '<input type="button" class="btn btn-danger" ' +
                    'onclick="deleteExpense(' + response[i].id + ');" ' +
                    'value="Delete">' +
                    '</td> ' +
                    '</tr>'
                );
          }
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }

    function getExpenseAndSetToForm(id) {
      $.ajax({
        type: "GET",
        url: "expenses/" + id,
        success: function (response) {
          $("#expenseId").val(response.id);
          $("#expenseDate").val(new Date(response.expenseDate).toISOString().split('T')[0]);

          getCategoryAndSetToForm(response.categoryId);

          $("#description").val(response.description);
          $("#amount").val(response.amount);

          $('#saveExpense').hide();
          $('#updateExpense').show();
          $('#idField').show();
        },
        error: function (err) {
          alert("error from getExpense: " + err);
        }
      });
    }

    function updateExpenseBtn() {
      $.ajax({
        type: "POST",
        url: "/expenses/update",
        data: {
          id: $("#expenseId").val(),
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val(),
          userId: 1,
          categoryId: $("#category").val()
        },
        success: function (result) {
          getAllRecordAndSetToTable();
          formReset();
        },
        error: function (err) {
          alert("error from updateExpenseBtn: " + err);
        }
      });
    }

    function deleteExpense(id) {
      $.ajax({
        type: "DELETE",
        url: "/expenses/delete/" + id,
        success: function (response) {
          getAllRecordAndSetToTable();
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }
  </script>
</head>

<body>
<jsp:include page="../navber.jsp"/>
<div class="container">
  <h1 class="text-center">Expense List</h1>
  <div class="container mt-3">
    <div class="row">
      <div class="col-6">
        <h3>Add New Expense</h3>
        <form id="expenseForm" name="expenseForm">
          <div class="row" id="idField">
            <div class="col">
              <div class="form-group">
                <label for="expenseId">ID</label>
                <input type="text" readonly="readonly" class="form-control" id="expenseId" name="expenseId">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="expenseDate">Date</label>
                <input type="date" class="form-control" id="expenseDate" name="expenseDate">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="category">Category</label>
                <select class="custom-select" id="category" form="expenseForm" onfocus="getAllCategoriesAndSetToForm()">
                  <option value="1" selected>General</option>
                </select>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="description">Description</label>
                <input type="textaria" class="form-control" id="description" name="description"
                       placeholder="Enter Description">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="amount">Amount</label>
                <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter Amount">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <button type="button" id="saveExpense" class="btn btn-primary">Submit</button>
              <button type="button" id="updateExpense" onclick="updateExpenseBtn()" class="btn btn-primary">
                Update
              </button>
            </div>
          </div>
        </form>
      </div>

      <div class="col-6">
        <h3>Expense Record</h3>
        <br>
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Date</th>
            <th scope="col">Description</th>
            <th scope="col">Amount</th>
            <th scope="col">Category</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
          </tr>
          </thead>
          <tbody id="expenseTable">
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>