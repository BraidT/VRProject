package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

import model.V1;

/**
 * Servlet implementation class VaataRiigiAdminYksusi
 */

public class V1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ID = Integer.parseInt(request.getParameter("ID"));
				
		List<RiigiAdminYksus> yksused = null;
		try {
			 yksused = vaataYksuseid(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("yksused", yksused);
		request.getRequestDispatcher("V1.jsp").forward(request, response);
		
	}

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<RiigiAdminYksus> vaataYksuseid(int ID) throws SQLException {
		
		List<RiigiAdminYksus> yksused = new ArrayList<RiigiAdminYksus>();
		
		Connection conn = DriverManager
				.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

		PreparedStatement ps = null;
		ResultSet rset = null;
		try {
			ps = conn.prepareStatement("select * from RIIGI_ADMIN_YKSUS where RIIGI_ADMIN_YKSUS_ID = ?");
			ps.setInt(1, ID);
			rset = ps.executeQuery();

			while (rset.next()) {
				RiigiAdminYksus yksus = new RiigiAdminYksus();
				yksus.setId(rset.getInt(1));
				yksus.setAvaja(rset.getString(2));
				yksus.setAvatud(rset.getDate(3));
				yksus.setMuutja(rset.getString(4));
				yksus.setMuudetud(rset.getDate(5));
				yksus.setSulgeja(rset.getString(6));
				yksus.setSuletud(rset.getDate(7));
				yksus.setKood(rset.getString(8));
				yksus.setNimetus(rset.getString(9));
				yksus.setKommentaar(rset.getString(10));
				yksus.setAlates(rset.getDate(11));
				yksus.setKuni(rset.getDate(12));
				yksus.setRiigi_admin_yksuse_liik_id(rset.getInt(13));
				
				yksused.add(yksus);
			}

			return yksused;
			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
}