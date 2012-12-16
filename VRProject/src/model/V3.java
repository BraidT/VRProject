package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class V3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("liik") !=null) {
			int liik = Integer.parseInt(request.getParameter("liik"));
		
			List<RiigiAdminYksus> yksused = null;
			try {
				 
				yksused = vaataYksuseid(liik);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			request.setAttribute("yksused", yksused);
			
			List<RiigiAdminYksuseLiik> liigid = null;
			try {
				 liigid = vaataLiike();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			request.setAttribute("liigid", liigid);
			request.getRequestDispatcher("V3.jsp").forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ID = 1;
		if(request.getParameter("ID") !=null) {
			ID = Integer.parseInt(request.getParameter("ID"));
		}
		
		List<RiigiAdminYksuseLiik> liigid = null;
		try {
			 liigid = vaataLiike();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("liigid", liigid);
		
		List<RiigiAdminYksus> yksused = null;
		try {
			 
			yksused = vaataYksuseid(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("yksused", yksused);
		request.getRequestDispatcher("V3.jsp").forward(request, response);
		
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
			rset = stmt.executeQuery("select * from RIIGI_ADMIN_YKSUSE_LIIK "
					+ "where suletud is null");

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
		private List<RiigiAdminYksus> vaataYksuseid(int id) throws SQLException {
			
			List<RiigiAdminYksus> yksused = new ArrayList<RiigiAdminYksus>();
			
			Connection conn = DriverManager
					.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

			PreparedStatement ps = null;
			ResultSet rset = null;
			try {
				ps = conn.prepareStatement("select * from RIIGI_ADMIN_YKSUS "
						+ "where suletud is null AND "
						+ "riigi_admin_yksuse_liik_id = ?");
				ps.setInt(1, id);
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