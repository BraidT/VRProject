package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

import model.V2;
import model.AdminAlluvus;

/**
 * Servlet implementation class VaataRiigiAdminYksusi
 */

public class V2 extends HttpServlet {
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
		//request.getRequestDispatcher("V2.jsp").forward(request, response);
		
		List<AdminAlluvus> alluvused = null;
		try {
			 alluvused = vaataAlluvusi(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("alluvused", alluvused);
		request.getRequestDispatcher("V2.jsp").forward(request, response);
		
		
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
		//int liik_id = 0;
		
		Connection conn = DriverManager
				.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

		PreparedStatement ps = null;
		ResultSet rset = null;
		try {
			ps = conn.prepareStatement("select "
					+ "kood, nimetus, kommentaar, riigi_admin_yksuse_liik_id, "
					+ "(select nimetus from riigi_admin_yksuse_liik "
					+ "where RIIGI_ADMIN_YKSUSE_liik.riigi_admin_yksuse_liik_id = "
					+ "RIIGI_ADMIN_YKSUS.riigi_admin_yksuse_liik_id), "
					+ "(select nimetus from riigi_admin_yksus "
					+ "where riigi_admin_yksus_ID = "
					+ "(select riigi_admin_yksuse_id from "
					+ "ADMIN_ALLUVUS where " 
					+ "ADMIN_ALLUVUS.riigi_admin_yksuse_alluva_id = ?"
					+ "AND suletud is null)) "
					+ "from RIIGI_ADMIN_YKSUS where RIIGI_ADMIN_YKSUS_ID = ?");
			ps.setInt(1, ID);
			ps.setInt(2, ID);
			rset = ps.executeQuery();
			RiigiAdminYksus yksus = new RiigiAdminYksus();
			
			while (rset.next()) {
				yksus.setId(ID);
				yksus.setKood(rset.getString(1));
				yksus.setNimetus(rset.getString(2));
				yksus.setKommentaar(rset.getString(3));
				yksus.setRiigi_admin_yksuse_liik_id(rset.getInt(4));
				yksus.setRiigi_admin_yksuse_liik(rset.getString(5));
				yksus.setRiigi_admin_yksuse_liik_nimetus(rset.getString(6));
								
				yksused.add(yksus);
			}

			return yksused;

			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
	
private List<AdminAlluvus> vaataAlluvusi(int ID) throws SQLException {
		
		List<AdminAlluvus> alluvused = new ArrayList<AdminAlluvus>();
		
		Connection conn = DriverManager
				.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");

		PreparedStatement ps = null;
		ResultSet rset = null;
		try {
			ps = conn.prepareStatement("select riigi_admin_yksuse_alluva_id, "
					+ "(select nimetus from RIIGI_ADMIN_YKSUS "
					+ "where RIIGI_ADMIN_YKSUS.riigi_admin_yksus_id = "
					+ "ADMIN_ALLUVUS.riigi_admin_yksuse_alluva_id) "
					+ "from ADMIN_ALLUVUS "
					+ "where riigi_admin_yksuse_id = ?"
					+ "AND suletud is null");

			ps.setInt(1, ID);
			rset = ps.executeQuery();
			
			while (rset.next()) {
				AdminAlluvus alluvus = new AdminAlluvus();
				alluvus.setId(rset.getInt(1));
				alluvus.setRiigi_admin_yksuse_alluva_nimetus(rset.getString(2));
				
				alluvused.add(alluvus);
			}

			return alluvused;
			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("alluv") != null) {
			int alluv = Integer.parseInt(request.getParameter("alluv"));
			out.println("alluv " + alluv);
			int ylev = Integer.parseInt(request.getParameter("ylev"));
			out.println("ylev " + ylev);
			
			try {
				conn = DriverManager
						.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");
									
			  ps = conn.prepareStatement("update ADMIN_ALLUVUS "
					 + "set suletud = TODAY, "
					 + "sulgeja = 'admin' "
					 + "where riigi_admin_yksuse_id = ? "
					 + "AND riigi_admin_yksuse_alluva_id = ?");
			 ps.setInt(1, ylev);
			 ps.setInt(2, alluv);
			 
			 int rowCount = ps.executeUpdate();
			 System.out.println(rowCount + " rows updated!");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
			 DbUtils.closeQuietly(ps);
			 DbUtils.closeQuietly(conn);
			 }
			String redirectURL = "V2?ID=" + ylev;
		    response.sendRedirect(redirectURL);
		}
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String kood = request.getParameter("kood");
			String nimetus = request.getParameter("nimetus");
			String kommentaar = request.getParameter("kommentaar");
			int allub = Integer.parseInt(request.getParameter("allub"));
	
			
			out.println(id + "<br>" + kood + "<br>"
					+ nimetus + "<br>" + kommentaar + "<br>"	+ allub);
	
			try {
				conn = DriverManager
						.getConnection("jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true");
									
			  ps = conn.prepareStatement("update RIIGI_ADMIN_YKSUS "
					 + "set kood = ?, "
					 + "nimetus = ?, "
					 + "kommentaar = ?, "
					 + "muutja = ?, "
					 + "muudetud = TODAY "
					 + "where riigi_admin_yksus_ID = ?");
			 ps.setString(1, kood);
			 ps.setString(2, nimetus);
			 ps.setString(3, kommentaar);
			 ps.setString(4, "admin");
			 ps.setInt(5, id);
			 
			 int rowCount = ps.executeUpdate();
			 System.out.println(rowCount + " rows updated!");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
			 DbUtils.closeQuietly(ps);
			 DbUtils.closeQuietly(conn);
			 }
			
			String redirectURL = "V2?ID=" + id;
		    response.sendRedirect(redirectURL);
		}
	}
}