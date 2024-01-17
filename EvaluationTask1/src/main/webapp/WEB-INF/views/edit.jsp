<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>書籍編集</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .modal-dialog {
            display: flex;
            align-items: center; /* 垂直中央に配置 */
            min-height: calc(100vh - 60px); /* ビューポートの高さから60pxを引いた高さ */
        }
    </style>
</head>
<body>
    <div class="container">
        <h3 class="mt-4">書籍編集</h3>
        <form action="edit" method="post">
            <input type="hidden" name="JAN_CD" value="${JAN_CD}" />
            
            <div class="form-group">
                <label>JANコード</label>
                <input type="text" name="JAN_CD" value="${JAN_CD}" class="form-control" readonly />
            </div>
            <div class="form-group">
                <label>ISBNコード</label>
                <input type="text" name="ISBN_CD" value="${ISBN_CD}" class="form-control" readonly />
            </div>
            <div class="form-group">
                <label>書籍名</label>
                <input type="text" name="BOOK_NM" value="${BOOK_NM}" class="form-control" />
                <% if (request.getAttribute("bookNmError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("bookNmError") %></small>
                <% } %>
            </div>
            <div class="form-group">
                <label>書籍名カナ</label>
                <input type="text" name="BOOK_KANA" value="${BOOK_KANA}" class="form-control" />
                <% if (request.getAttribute("bookKanaError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("bookKanaError") %></small>
                <% } %>
            </div>
            <div class="form-group">
                <label>価格</label>
                <input type="number" name="PRICE" value="${PRICE}" class="form-control" />
                <% if (request.getAttribute("priceError") != null) { %>
                <small class="form-text text-danger"><%= request.getAttribute("priceError") %></small>
                <% } %>
            </div>
            <div class="form-group">
                <label>発行日</label>
                <input type="date" name="ISSUE_DATE" value="${ISSUE_DATE}" class="form-control" readonly />
            </div>
            <div class="form-group">
                <input type="submit" value="更新" class="btn btn-primary" />
            </div>
            <div class="form-group">
                <a href="book" class="btn btn-secondary">一覧に戻る</a>
            </div>
        </form>
        
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
               <div class="modal-content">
                  <div class="modal-body text-center">本当に削除しますか？</div>
                  <div class="modal-footer justify-content-center">
                     <button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
                     <button type="button" class="btn btn-danger" id="deleteConfirmButton">削除</button>
                  </div>
               </div>
            </div>
          </div>
          
          <form action="destroy" method="POST" style="display:none;" id="deleteForm">
             <input type="hidden" name="JAN_CD" value="${JAN_CD}">
          </form>         
          <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteConfirmModal">削除</button>
    </div>
    
    <!-- jQueryとBootstrapのJavaScript -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('deleteConfirmButton').addEventListener('click', function() {
            document.getElementById('deleteForm').submit();
        });
    </script>
</body>
</html>
