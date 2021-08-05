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
          <select class="custom-select" id="category" form="expenseForm">
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
        <button type="button" id="saveExpense" class="btn btn-primary" onclick="saveExpenseBtn()">
          Submit
        </button>
        <button type="button" id="updateExpense" class="btn btn-primary" onclick="updateExpenseBtn()" >
          Update
        </button>
      </div>
    </div>
  </form>
</div>