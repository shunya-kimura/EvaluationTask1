package model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.BookBean;
import model.dao.BookDAO;

@WebServlet("/book")
public class BookListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		try {
			List<BookBean> books = bookDAO.listBook();
			request.setAttribute("books", books);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String view = "/WEB-INF/views/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
}
