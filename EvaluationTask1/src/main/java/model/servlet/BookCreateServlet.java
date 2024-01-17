package model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BookDAO;

@WebServlet("/create")
public class BookCreateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/views/create.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String JAN_CD = request.getParameter("JAN_CD");
		String ISBN_CD = request.getParameter("ISBN_CD");
		String BOOK_NM = request.getParameter("BOOK_NM");
		String BOOK_KANA = request.getParameter("BOOK_KANA");
		String priceString = request.getParameter("PRICE");
		String issueDateString = request.getParameter("ISSUE_DATE");
		
		String janError = "";
		String isbnError = "";
		String bookNmError = "";
		String bookKanaError = "";
		String priceError = "";
		String issueDateError = "";
		
		if (JAN_CD == null || JAN_CD.trim().isEmpty()) {
			janError = "JANコードは空白にできません。";
		} else if (JAN_CD.length() > 13) {
			janError = "JANコードは13文字を超えてはいけません。";
		}
		
		if (ISBN_CD == null || ISBN_CD.trim().isEmpty()) {
			isbnError = "ISBNコードは空白にできません。";
		} else if (ISBN_CD.length() > 13) {
			isbnError = "ISBNコードは13文字を超えてはいけません。";
		}
		
		if (BOOK_NM == null || BOOK_NM.trim().isEmpty()) {
			bookNmError = "書籍名は空白にできません。";
		} else if (BOOK_NM.length() > 500) {
			bookNmError = "書籍名は500文字を超えてはいけません。";
		}
		
		if (BOOK_KANA == null || BOOK_KANA.trim().isEmpty()) {
			bookKanaError = "書籍名カナは空白にできません。";
		} else if (BOOK_KANA.length() > 500) {
			bookKanaError = "書籍名カナは500文字を超えてはいけません。";
		}
		
		int PRICE = 0;
		try {
			PRICE = Integer.parseInt(priceString);
			if (priceString.length() > 11) {
				priceError = "価格は11桁を超えてはいけません。";
			}
		} catch (NumberFormatException e) {
			priceError = "価格は数値である必要があります。";
		}
		
		Date ISSUE_DATE = null;
		try {
			ISSUE_DATE = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);
		} catch (ParseException e) {
			issueDateError = "無効な日付形式です。";
		}
		
		if (!janError.isEmpty() || !isbnError.isEmpty() || !bookNmError.isEmpty() || 
			!bookKanaError.isEmpty() || !priceError.isEmpty() || !issueDateError.isEmpty()) {
			request.setAttribute("janError", janError);
			request.setAttribute("isbnError", isbnError);
			request.setAttribute("bookNmError", bookNmError);
			request.setAttribute("bookKanaError", bookKanaError);
			request.setAttribute("priceError", priceError);
			request.setAttribute("issueDateError", issueDateError);
			
			doGet(request, response);
			return;
		}
		
		BookDAO bookDAO = new BookDAO();
		try {
			bookDAO.createBook(JAN_CD, ISBN_CD, BOOK_NM, BOOK_KANA, PRICE, ISSUE_DATE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("book");
	}

}
