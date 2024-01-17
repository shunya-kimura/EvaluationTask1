<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍新規登録</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
   <h1>書籍登録</h1>
      <form action="create" method="POST">
         <div class="form-group">
            <label>JANコード</label>
            <input type="text" name="JAN_CD" class="form-control">
            <% if (request.getAttribute("janError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("janError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <label>ISBNコード</label>
            <input type="text" name="ISBN_CD" class="form-control">
            <% if (request.getAttribute("isbnError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("isbnError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <label>書籍名</label>
            <input type="text" name="BOOK_NM" class="form-control">
            <% if (request.getAttribute("bookNmError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("bookNmError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <label>書籍名カナ</label>
            <input type="text" name="BOOK_KANA" class="form-control">
            <% if (request.getAttribute("bookKanaError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("bookKanaError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <label>価格</label>
            <input type="number" name="PRICE" class="form-control">
            <% if (request.getAttribute("priceError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("priceError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <label>発行日</label>
            <input type="date" name="ISSUE_DATE" class="form-control">
            <% if (request.getAttribute("issueDateError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("issueDateError") %></small>
            <% } %>
         </div>
         
         <div class="form-group">
            <input type="submit" value="新規登録" class="btn btn-primary">
         </div>
         
         <div class="form-group">
            <a href="book" class="btn btn-secondary">一覧に戻る</a>
         </div>
      </form>
</body>
</html>