<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>書籍編集</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">書籍編集</h1>
        <form action="edit" method="post">
            <input type="hidden" name="JAN_CD" value="${JAN_CD}" />
            
            <div class="form-group">
                <label>JANコード:</label>
                <input type="text" name="JAN_CD" value="${JAN_CD}" class="form-control" readonly />
            </div>
            <div class="form-group">
                <label>ISBNコード:</label>
                <input type="text" name="ISBN_CD" value="${ISBN_CD}" class="form-control" readonly />
            </div>
            <div class="form-group">
                <label>書籍名:</label>
                <input type="text" name="BOOK_NM" value="${BOOK_NM}" class="form-control" />
            </div>
            <div class="form-group">
                <label>書籍名カナ:</label>
                <input type="text" name="BOOK_KANA" value="${BOOK_KANA}" class="form-control" />
            </div>
            <div class="form-group">
                <label>価格:</label>
                <input type="number" name="PRICE" value="${PRICE}" class="form-control" />
            </div>
            <div class="form-group">
                <label>発行日:</label>
                <input type="date" name="ISSUE_DATE" value="${ISSUE_DATE}" class="form-control" />
            </div>
            <div class="form-group">
                <input type="submit" value="更新" class="btn btn-primary" />
            </div>
        </form>
    </div>
</body>
</html>
