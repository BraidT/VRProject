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
 * Servlet implementation class V1
 */
public class V1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String connectionString = "jdbc:hsqldb:file:${user.home}/i377/Team09d/db;shutdown=true";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public V1() {
		super();
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
		List<RiigiAdminYksuseLiik> riigiAdminYksuseLiigid = null;
		List<RiigiAdminYksuseLiik> alluvad = null;
		List<RiigiAdminYksuseLiik> ylemused = null;

		int ID = 1;
		if (request.getParameter("ID") != null) {
			ID = Integer.parseInt(request.getParameter("ID"));
		}
		try {
			riigiAdminYksuseLiigid = leiaYksusteLiigid(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("yksusteLiigid", riigiAdminYksuseLiigid);

		try {
			alluvad = leiaAlluvad(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("alluvad", alluvad);
		try {
			ylemused = leiaYlemused(ID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("ylemused", ylemused);
		request.getRequestDispatcher("V1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("alluv") != null) {
			int alluv = Integer.parseInt(request.getParameter("alluv"));
			int ylemus = Integer.parseInt(request.getParameter("ylemus"));

			try {
				conn = DriverManager.getConnection(connectionString);

				ps = conn.prepareStatement("update VOIMALIK_ALLUVUS "
						+ "set suletud = TODAY, " + "sulgeja = 'admin' "
						+ "where riigi_admin_yksuse_liik_id = ? "
						+ "AND riigi_admin_yksuse_alluva_liik_id = ?");
				ps.setInt(1, ylemus);
				ps.setInt(2, alluv);
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(ps);
				DbUtils.closeQuietly(conn);
			}
			String redirectURL = "V1?ID=" + ylemus;
			response.sendRedirect(redirectURL);
		} else {
			System.out.println(" alluv = null");
			int id = Integer.parseInt(request.getParameter("id"));
			String kood = request.getParameter("kood");
			String nimetus = request.getParameter("nimetus");
			String kommentaar = request.getParameter("kommentaar");
			try {
				conn = DriverManager.getConnection(connectionString);

				ps = conn.prepareStatement("update RIIGI_ADMIN_YKSUSE_LIIK "
						+ "set kood = ?, " + "nimetus = ?, "
						+ "kommentaar = ?, " + "muutja = ?, "
						+ "muudetud = TODAY "
						+ "where riigi_admin_yksuse_liik_ID = ?");
				ps.setString(1, kood);
				ps.setString(2, nimetus);
				ps.setString(3, kommentaar);
				ps.setString(4, "admin");
				ps.setInt(5, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				DbUtils.closeQuietly(ps);
				DbUtils.closeQuietly(conn);
			}
			String redirectURL = "V1?ID=" + id;
			response.sendRedirect(redirectURL);
		}
	}

	private List<RiigiAdminYksuseLiik> leiaYksusteLiigid(int ID)
			throws SQLException {

		List<RiigiAdminYksuseLiik> riigiAdminYksuseLiigid = new ArrayList<RiigiAdminYksuseLiik>();
		Connection conn = DriverManager.getConnection(connectionString);

		ResultSet rset = null;
		PreparedStatement ps = null;
		try {
			ps = conn
					.prepareStatement("select riigi_admin_yksuse_liik_id, kood, nimetus, kommentaar "
							+ "from RIIGI_ADMIN_YKSUSE_LIIK where riigi_admin_yksuse_liik_id = ?");
			ps.setInt(1, ID);
			rset = ps.executeQuery();
			RiigiAdminYksuseLiik riigiAdminYksuseLiik = new RiigiAdminYksuseLiik();
			while (rset.next()) {
				riigiAdminYksuseLiik.setId(rset.getInt(1));
				riigiAdminYksuseLiik.setKood(rset.getString(2));
				riigiAdminYksuseLiik.setNimetus(rset.getString(3));
				riigiAdminYksuseLiik.setKommentaar(rset.getString(4));
				riigiAdminYksuseLiigid.add(riigiAdminYksuseLiik);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(conn);
		}
		return riigiAdminYksuseLiigid;
	}

	private List<RiigiAdminYksuseLiik> leiaAlluvad(int ID) throws SQLException {

		List<RiigiAdminYksuseLiik> alluvad = new ArrayList<RiigiAdminYksuseLiik>();
		Connection conn = DriverManager.getConnection(connectionString);

		ResultSet rset = null;
		PreparedStatement ps = null;
		ResultSet rsetAlluvad = null;
		PreparedStatement psAlluvad = null;
		try {
			ps = conn
					.prepareStatement("select riigi_admin_yksuse_alluva_liik_id from VOIMALIK_ALLUVUS where riigi_admin_yksuse_liik_id=? and suletud IS NULL");

			ps.setInt(1, ID);
			rset = ps.executeQuery();
			
			while (rset.next()) {
				RiigiAdminYksuseLiik riigiAdminYksuseLiik = new RiigiAdminYksuseLiik();
				psAlluvad = conn
						.prepareStatement("select riigi_admin_yksuse_liik_id, nimetus from RIIGI_ADMIN_YKSUSE_LIIK where riigi_admin_yksuse_liik_id = ?");
				psAlluvad.setInt(1, rset.getInt(1));
				rsetAlluvad = psAlluvad.executeQuery();
				while (rsetAlluvad.next()) {
					riigiAdminYksuseLiik.setId(rsetAlluvad.getInt(1));
					riigiAdminYksuseLiik.setNimetus(rsetAlluvad.getString(2));
					alluvad.add(riigiAdminYksuseLiik);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(rsetAlluvad);
			DbUtils.closeQuietly(psAlluvad);
			DbUtils.closeQuietly(conn);
		}
		return alluvad;
	}

	private List<RiigiAdminYksuseLiik> leiaYlemused(int ID) throws SQLException {
		List<RiigiAdminYksuseLiik> ylemused = new ArrayList<RiigiAdminYksuseLiik>();
		Connection conn = DriverManager.getConnection(connectionString);

		ResultSet rset = null;
		PreparedStatement ps = null;
		try {
			ResultSet rsetAlluvad = null;
			PreparedStatement psAlluvad = null;
			ps = conn
					.prepareStatement("select riigi_admin_yksuse_liik_id from VOIMALIK_ALLUVUS where riigi_admin_yksuse_alluva_liik_id=?");

			ps.setInt(1, ID);
			rset = ps.executeQuery();
			RiigiAdminYksuseLiik riigiAdminYksuseLiik = new RiigiAdminYksuseLiik();
			while (rset.next()) {
				psAlluvad = conn
						.prepareStatement("select riigi_admin_yksuse_liik_id, nimetus from RIIGI_ADMIN_YKSUSE_LIIK where riigi_admin_yksuse_liik_id = ?");
				psAlluvad.setInt(1, rset.getInt(1));
				rsetAlluvad = psAlluvad.executeQuery();
				while (rsetAlluvad.next()) {
					riigiAdminYksuseLiik.setId(rsetAlluvad.getInt(1));
					riigiAdminYksuseLiik.setNimetus(rsetAlluvad.getString(2));
					ylemused.add(riigiAdminYksuseLiik);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(conn);
		}
		return ylemused;
	}
}
