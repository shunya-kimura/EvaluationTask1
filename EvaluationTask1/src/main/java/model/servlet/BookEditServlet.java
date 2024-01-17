package model.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.BookBean;
import model.dao.BookDAO;

@WebServlet("/edit")
public class BookEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		String JAN_CD = request.getParameter("JAN_CD");
		BookBean bookBean = new BookBean();
		BookDAO bookDAO = new BookDAO();
		try {
			bookBean = bookDAO.editBook(JAN_CD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("JAN_CD", bookBean.getJAN_CD());
		request.setAttribute("ISBN_CD", bookBean.getISBN_CD());
		request.setAttribute("BOOK_NM", bookBean.getBOOK_NM());
		request.setAttribute("BOOK_KANA", bookBean.getBOOK_KANA());
		request.setAttribute("PRICE", bookBean.getPRICE());
		request.setAttribute("ISSUE_DATE", bookBean.getISSUE_DATE());
		
		String view = "/WEB-INF/views/edit.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException {
		String JAN_CD = request.getParameter("JAN_CD");
		String BOOK_NM = request.getParameter("BOOK_NM");
		String BOOK_KANA = request.getParameter("BOOK_KANA");
		String priceString = request.getParameter("PRICE");
		
		String bookNmError = "";
		String bookKanaError = "";
		String priceError = "";
		String issueDateError = "";
		
		// 書籍名バリデーション
		if (BOOK_NM == null || BOOK_NM.trim().isEmpty()) {
			bookNmError = "書籍名は空白にできません。";
		} else if (BOOK_NM.length() > 500) {
			bookNmError = "書籍名は500文字を超えてはいけません。";
		}
		
		// 書籍名カナバリデーション
		if (BOOK_KANA == null || BOOK_KANA.trim().isEmpty()) {
			bookKanaError = "書籍名カナは空白にできません。";
		} else if (BOOK_KANA.length() > 500) {
			bookKanaError = "書籍名カナは500文字を超えてはいけません。";
		}
		
		// 価格のバリデーション
		int  PRICE = 0;
		if (priceString == null || priceString.trim().isEmpty()) {
			priceError = "価格は空白にできません。";
		} else {
			try {
				PRICE = Integer.parseInt(priceString);
				if (priceString.length() > 11) {
					priceError = "価格は11桁を超えてはいけません。";
				}
			} catch (NumberFormatException e) {
				priceError = "価格は数値である必要があります。";
			}
		}
        
        request.setAttribute("bookNmError", bookNmError);
        request.setAttribute("bookKanaError", bookKanaError);
        request.setAttribute("priceError", priceError);
        
        if (!bookNmError.isEmpty() || !bookKanaError.isEmpty() || !priceError.isEmpty() || !issueDateError.isEmpty()) {
        	doGet(request, response);
        	return;
        }
        
        BookDAO bookDAO = new BookDAO();
        
        try {
			bookDAO.updateBook(JAN_CD, BOOK_NM, BOOK_KANA, PRICE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        response.sendRedirect("book");
	}
}
