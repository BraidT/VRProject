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

/**
 * Servlet implementation class LisaVoimalikAlluv
 */
public class LisaVoimalikAlluv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String connectionString = "jdbc:hsqldb:file:C://VRP//Project;shutdown=true";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LisaVoimalikAlluv() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("lisa alluv @" + id);

		List<RiigiAdminYksuseLiik> yksuseLiigid = null;
		try {
			System.out.println("leian liigid");
			yksuseLiigid = vaataYksuseLiike(id);
			if(yksuseLiigid != null){
				System.out.println("leitud vähemalt üks liik");
			}
			else{
				System.out.println("liike pole");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("yksuseLiigid", yksuseLiigid);
		request.getRequestDispatcher("LisaVoimalikAlluv.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private List<RiigiAdminYksuseLiik> vaataYksuseLiike(int id) throws SQLException {

		List<RiigiAdminYksuseLiik> yksuseLiigid = new ArrayList<RiigiAdminYksuseLiik>();

		Connection conn = DriverManager.getConnection(connectionString);

		PreparedStatement ps = null;
		ResultSet rset = null;
		try {
			ps = conn
					.prepareStatement("select * from RIIGI_ADMIN_YKSUSE_LIIK "
							+ "where riigi_admin_yksuse_liik_id != ?"
							+ " and riigi_admin_yksuse_liik_id not in "
							+ "(select riigi_admin_yksuse_alluva_liik_id from VOIMALIK_ALLUVUS)"
							+ "and riigi_admin_yksuse_liik_id not in "
							+ "(select riigi_admin_yksuse_liik_id from VOIMALIK_ALLUVUS)");

			ps.setInt(1, id);

			rset = ps.executeQuery();
			RiigiAdminYksuseLiik yksuseLiik = new RiigiAdminYksuseLiik();

			while (rset.next()) {
				System.out.println("test");
				yksuseLiik.setId(rset.getInt(1));
				yksuseLiik.setAvaja(rset.getString(2));
				yksuseLiik.setAvatud(rset.getDate(3));
				yksuseLiik.setMuutja(rset.getString(4));
				yksuseLiik.setMuudetud(rset.getDate(5));
				yksuseLiik.setSulgeja(rset.getString(6));
				yksuseLiik.setSuletud(rset.getDate(7));
				yksuseLiik.setKood(rset.getString(8));
				yksuseLiik.setNimetus(rset.getString(9));
				yksuseLiik.setKommentaar(rset.getString(10));
				yksuseLiik.setAlates(rset.getDate(11));
				yksuseLiik.setKuni(rset.getDate(12));
				System.out.println("ID = "+rset.getInt(1));

				yksuseLiigid.add(yksuseLiik);
			}

			return yksuseLiigid;

		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}

}
