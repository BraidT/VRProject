package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

import model.RiigiAdminYksuseLiik;

/**
 * Servlet implementation class VaataRiigiAdminYksuseLiike
 */

public class VaataRiigiAdminYksuseLiike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<RiigiAdminYksuseLiik> liigid = null;
		try {
			 liigid = vaataLiike();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("liigid", liigid);
		request.getRequestDispatcher("VaataRiigiAdminYksuseLiike.jsp").forward(request, response);
		
	}

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<RiigiAdminYksuseLiik> vaataLiike() throws SQLException {
		
		List<RiigiAdminYksuseLiik> liigid = new ArrayList<RiigiAdminYksuseLiik>();
		
		Connection conn = DriverManager
				.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select * from RIIGI_ADMIN_YKSUSE_LIIK");

			while (rset.next()) {
				RiigiAdminYksuseLiik liik = new RiigiAdminYksuseLiik();
				liik.setId(rset.getInt(1));
				liik.setAvaja(rset.getString(2));
				liik.setAvatud(rset.getDate(3));
				liik.setMuutja(rset.getString(4));
				liik.setMuudetud(rset.getDate(5));
				liik.setSulgeja(rset.getString(6));
				liik.setSuletud(rset.getDate(7));
				liik.setKood(rset.getString(8));
				liik.setNimetus(rset.getString(9));
				liik.setKommentaar(rset.getString(10));
				
				liigid.add(liik);
			}

			return liigid;
			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

}