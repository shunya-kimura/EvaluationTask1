package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.DBConnection;
import model.bean.BookBean;

public class BookDAO {
	
	// 一覧表示
	public List<BookBean> listBook() throws ClassNotFoundException, SQLException {
		List<BookBean> books = new ArrayList<>();
		String sql = "SELECT * FROM BOOK";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				BookBean book = new BookBean();
				book.setJAN_CD(res.getString("JAN_CD"));
				book.setISBN_CD(res.getString("ISBN_CD"));
				book.setBOOK_NM(res.getString("BOOK_NM"));
				book.setBOOK_KANA(res.getString("BOOK_KANA"));
				book.setPRICE(res.getInt("PRICE"));
				book.setISSUE_DATE(res.getDate("ISSUE_DATE"));
				book.setCREATE_DATETIME(res.getTimestamp("CREATE_DATETIME"));
	            book.setUPDATE_DATETIME(res.getTimestamp("UPDATE_DATETIME"));
				
				books.add(book);
			}
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return books;
	}
	
	// 編集画面表示
	public BookBean editBook(String JAN_CD) 
			throws SQLException, ClassNotFoundException {
		BookBean book = new BookBean();
		String sql = "SELECT * FROM BOOK WHERE JAN_CD = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, JAN_CD);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				book.setJAN_CD(res.getString("JAN_CD"));
				book.setISBN_CD(res.getString("ISBN_CD"));
				book.setBOOK_NM(res.getString("BOOK_NM"));
				book.setBOOK_KANA(res.getString("BOOK_KANA"));
				book.setPRICE(res.getInt("PRICE"));
				book.setISSUE_DATE(res.getDate("ISSUE_DATE"));
			}
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return book;
	}
	
	// 更新
	public void updateBook(String JAN_CD, String BOOK_NM, String BOOK_KANA, int PRICE) 
			throws ClassNotFoundException, SQLException {
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(new java.util.Date().getTime());
		String sql = "UPDATE BOOK SET BOOK_NM = ?, BOOK_KANA = ?, PRICE = ?, UPDATE_DATETIME = ? WHERE JAN_CD = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, BOOK_NM);
			pstmt.setString(2, BOOK_KANA);
			pstmt.setInt(3, PRICE);
			pstmt.setTimestamp(4, currentTimestamp);
	        pstmt.setString(5, JAN_CD);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
	
	// 削除
	public void destroyBook(String JAN_CD) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM BOOK WHERE JAN_CD = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, JAN_CD);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
	
	// 新規作成
	public void createBook(String JAN_CD, String ISBN_CD, String BOOK_NM, String BOOK_KANA, int PRICE, java.util.Date ISSUE_DATE) 
	        throws ClassNotFoundException, SQLException {
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(new java.util.Date().getTime());
	    String sql = "INSERT INTO BOOK (JAN_CD, ISBN_CD, BOOK_NM, BOOK_KANA, PRICE, ISSUE_DATE, CREATE_DATETIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, JAN_CD);
	        pstmt.setString(2, ISBN_CD);
	        pstmt.setString(3, BOOK_NM);
	        pstmt.setString(4, BOOK_KANA);
	        pstmt.setInt(5, PRICE);
	        pstmt.setDate(6, new java.sql.Date(ISSUE_DATE.getTime()));
	        pstmt.setTimestamp(7, currentTimestamp);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
}
