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

import model.VoimalikAlluvus;

/**
 * Servlet implementation class VaataVoimalikeAlluvusi
 */

public class VaataVoimalikeAlluvusi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AdminAlluvus> alluvused = null;
		try {
			 alluvused = vaataAlluvusi();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("alluvused", alluvused);
		request.getRequestDispatcher("VaataVoimalikeAlluvusi.jsp").forward(request, response);
		
	}

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<AdminAlluvus> vaataAlluvusi() throws SQLException {
		
		List<AdminAlluvus> alluvused = new ArrayList<AdminAlluvus>();
		
		Connection conn = DriverManager
				.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select * from ADMIN_ALLUVUS");

			while (rset.next()) {
				AdminAlluvus alluvus = new AdminAlluvus();
				alluvus.setId(rset.getInt(1));
				alluvus.setAvaja(rset.getString(2));
				alluvus.setAvatud(rset.getDate(3));
				alluvus.setMuutja(rset.getString(4));
				alluvus.setMuudetud(rset.getDate(5));
				alluvus.setSulgeja(rset.getString(6));
				alluvus.setSuletud(rset.getDate(7));
				alluvus.setAlates(rset.getDate(8));
				alluvus.setKuni(rset.getDate(9));
				alluvus.setKommentaar(rset.getString(10));
				alluvus.setRiigi_admin_yksuse_id(rset.getInt(11));
				alluvus.setRiigi_admin_yksuse_alluva_id(rset.getInt(12));
				
				alluvused.add(alluvus);
			}

			return alluvused;
			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

}
