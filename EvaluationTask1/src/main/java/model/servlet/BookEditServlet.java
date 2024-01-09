package model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException {
		String JAN_CD = request.getParameter("JAN_CD");
		String BOOK_NM = request.getParameter("BOOK_NM");
		String BOOK_KANA = request.getParameter("BOOK_KANA");
		int PRICE = Integer.parseInt(request.getParameter("PRICE"));
		String issueDateString = request.getParameter("ISSUE_DATE");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ISSUE_DATE = null;
        if (issueDateString != null && !issueDateString.isEmpty()) {
            try {
				ISSUE_DATE = formatter.parse(issueDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        BookDAO bookDAO = new BookDAO();
        try {
			bookDAO.updateBook(JAN_CD, BOOK_NM, BOOK_KANA, PRICE, ISSUE_DATE);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        response.sendRedirect("book");
	}
}
