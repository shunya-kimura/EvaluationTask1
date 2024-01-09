<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.BookBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍一覧</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<style>
    .hover-row:hover {
        background-color: #f5f5f5; /* ホバー時の背景色 */
        cursor: pointer; /* ホバー時のカーソルをポインターに変更 */
    }
</style>
<script>
    function redirectToEdit(JAN_CD) {
        window.location.href = 'edit?JAN_CD=' + JAN_CD;
    }
</script>
</head>
<body>
   <h1>書籍一覧</h1>
   <table class="table table-striped">
       <thead>
           <tr>
              <th>JANコード</th>
              <th>ISBNコード</th>
              <th>書籍名</th>
              <th>書籍名カナ</th>
              <th>価格</th>
              <th>発行日</th>
              <th>登録日時</th>
              <th>更新日時</th>
           </tr>
       </thead>
       <tbody>
       <% 
         List<BookBean> books = (List<BookBean>) request.getAttribute("books");
         if (books != null && !books.isEmpty()) {
        	 for (BookBean book : books) {
       %>
           <tr class="hover-row" onclick="redirectToEdit('<%= book.getJAN_CD() %>')">
             <td><%= book.getJAN_CD() %></td>
             <td><%= book.getISBN_CD() %></td>
             <td><%= book.getBOOK_NM() %></td>
             <td><%= book.getBOOK_KANA() %></td>
             <td><%= book.getPRICE() %></td>
             <td><%= book.getISSUE_DATE() %></td>
             <td><%= book.getCREATE_DATETIME() %></td>
             <td><%= book.getUPDATE_DATETIME() != null ? book.getUPDATE_DATETIME().toString() : "未更新" %></td>
           </tr>
       <%
        	 }
         }
       %>
       </tbody>
   </table>
</body>
</html>
