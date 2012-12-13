﻿package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.hsqldb.server.Servlet;

public class DatabaseDAO extends Servlet {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	private Statement statement;

	// final String connectionString = "jdbc:hsqldb:file:${user.home}/i377/Team09d/db;shutdown=true";
	final String connectionString = "jdbc:hsqldb:file:x://ITK//Java//Veebirakendused//DB//Projekt;shutdown=true";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			connection = DriverManager.getConnection(connectionString);
			statement = connection.createStatement();
			createTables();
			insertTestData();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(statement);
			DbUtils.closeQuietly(connection);
		}
		response.getWriter().println("great success!");
	}

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void createTables() throws SQLException {
		createTableRiigiAdminYksuseLiik();
		createTableRiigiAdminYksus();
		createTableAdminAlluvus();
		createTableVoimalikAlluvus();
	}

	public void insertTestData() throws SQLException {		
		insertTestDataRiigiAdminYksuseLiik();
		insertTestDataRiigiAdminYksus();
		insertTestDataAdminAlluvus();
		insertTestDataVoimalikAlluvus();		
	}

	

	private void createTableRiigiAdminYksuseLiik() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUSE_LIIK ("
				+ "riigi_admin_yksuse_liik_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,"
				+ "avaja                VARCHAR(32) NOT NULL,"
				+ "avatud               DATE NOT NULL,"
				+ "muutja               VARCHAR(32) NOT NULL,"
				+ "muudetud             DATE NOT NULL,"
				+ "sulgeja              VARCHAR(32),"
				+ "suletud              DATE,"
				+ "kood                 VARCHAR(10) NOT NULL,"
				+ "nimetus              VARCHAR(100) NOT NULL,"
				+ "kommentaar           LONGVARCHAR,"
				+ "alates               DATE NOT NULL,"
				+ "kuni                 DATE,"
				+ "PRIMARY KEY (riigi_admin_yksuse_liik_id));");
	}

	private void insertTestDataRiigiAdminYksuseLiik() throws SQLException {
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'M', 'Maakond', 'Suurim haldusüksus riigis', TODAY, NULL);");
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'V', 'Vald', NULL, TODAY, NULL);");
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'L', 'Linn', NULL, TODAY, NULL);");
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'K', 'Küla', NULL, TODAY, NULL);");
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'T', 'Talu', NULL, TODAY, NULL);");
	}

	private void createTableVoimalikAlluvus() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS VOIMALIK_ALLUVUS ("
				+ "voimalik_alluvus_id  INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,"
				+ "avaja                VARCHAR(32) NOT NULL,"
				+ "avatud               DATE NOT NULL,"
				+ "muutja               VARCHAR(32) NOT NULL,"
				+ "muudetud             DATE NOT NULL,"
				+ "sulgeja              VARCHAR(32),"
				+ "suletud              DATE,"
				+ "riigi_admin_yksuse_liik_id INTEGER NOT NULL,"
				+ "riigi_admin_yksuse_alluva_liik_id INTEGER NOT NULL,"
				+ "kommentaar           LONGVARCHAR,"
				+ "alates               DATE NOT NULL,"
				+ "kuni                 DATE,"
				+ "PRIMARY KEY (voimalik_alluvus_id), "
				+ "FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT,"
				+ "FOREIGN KEY (riigi_admin_yksuse_alluva_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT);");
	}
	
	private void insertTestDataVoimalikAlluvus() throws SQLException {
		statement.executeUpdate("INSERT INTO VOIMALIK_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, riigi_admin_yksuse_liik_id, riigi_admin_yksuse_alluva_liik_id, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 1, 2, 'Vald allub maakonnale', TODAY, NULL);");
		statement.executeUpdate("INSERT INTO VOIMALIK_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, riigi_admin_yksuse_liik_id, riigi_admin_yksuse_alluva_liik_id, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 1, 3, 'Linn allub maakonnale', TODAY, NULL);");
		statement.executeUpdate("INSERT INTO VOIMALIK_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, riigi_admin_yksuse_liik_id, riigi_admin_yksuse_alluva_liik_id, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 2, 4, 'Küla allub vallale', TODAY, NULL);");
		statement.executeUpdate("INSERT INTO VOIMALIK_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, riigi_admin_yksuse_liik_id, riigi_admin_yksuse_alluva_liik_id, kommentaar, alates, kuni) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 4, 5, 'Talu allub külale', TODAY, NULL);");
	}
	
	private void createTableRiigiAdminYksus() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUS ("
				+ "riigi_admin_yksus_ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,"
				+ "avaja                VARCHAR(32) NOT NULL,"
				+ "avatud               DATE NOT NULL,"
				+ "muutja               VARCHAR(32) NOT NULL,"
				+ "muudetud             DATE NOT NULL,"
				+ "sulgeja              VARCHAR(32),"
				+ "suletud              DATE,"
				+ "kood					VARCHAR(20),"
				+ "nimetus              VARCHAR(100),"
				+ "kommentaar           LONGVARCHAR,"
				+ "alates               DATE NOT NULL,"
				+ "kuni                 DATE,"
				+ "riigi_admin_yksuse_liik_id INTEGER NOT NULL,"
				+ "PRIMARY KEY (riigi_admin_yksus_ID), "
				+ "FOREIGN KEY (riigi_admin_yksuse_liik_id) REFERENCES RIIGI_ADMIN_YKSUSE_LIIK ON DELETE RESTRICT);");
	}
		
	private void insertTestDataRiigiAdminYksus() throws SQLException {
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni, riigi_admin_yksuse_liik_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'M1', 'Viljandi', 'Viljandi maakond', TODAY, NULL, 1); ");
		
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni, riigi_admin_yksuse_liik_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'V1', 'Viiratsi', 'Viiratsi vald', TODAY, NULL, 2); ");
		
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni, riigi_admin_yksuse_liik_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'K1', 'Verilaske', 'Verilaske küla', TODAY, NULL, 4); ");
		
		statement.executeUpdate("INSERT INTO RIIGI_ADMIN_YKSUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, kood, nimetus, kommentaar, alates, kuni, riigi_admin_yksuse_liik_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, 'T1', 'Ärma', 'Ärma talu', TODAY, NULL, 5); ");
	}
	
	private void createTableAdminAlluvus() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS ADMIN_ALLUVUS  ("
				+ "admin_alluvus_id     INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,"
				+ "avaja                VARCHAR(32) NOT NULL,"
				+ "avatud               DATE NOT NULL,"
				+ "muutja               VARCHAR(32) NOT NULL,"
				+ "muudetud             DATE NOT NULL,"
				+ "sulgeja              VARCHAR(32),"
				+ "suletud              DATE,"
				+ "alates               DATE NOT NULL,"
				+ "kuni                 DATE,"
				+ "kommentaar           LONGVARCHAR,"
				+ "riigi_admin_yksuse_id	INTEGER NOT NULL,"
				+ "riigi_admin_yksuse_alluva_id	INTEGER NOT NULL,"
				+ "PRIMARY KEY (admin_alluvus_id),"
				+ "FOREIGN KEY (riigi_admin_yksuse_id) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT,"
				+ "FOREIGN KEY (riigi_admin_yksuse_alluva_id) REFERENCES RIIGI_ADMIN_YKSUS ON DELETE RESTRICT);");
	}
	
	private void insertTestDataAdminAlluvus() throws SQLException {
		statement.executeUpdate("INSERT INTO ADMIN_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, alates, kuni, kommentaar, riigi_admin_yksuse_id, riigi_admin_yksuse_alluva_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, TODAY, NULL, 'Viiratsi vald allub Viljandi maakonnale', 1, 2);");
		statement.executeUpdate("INSERT INTO ADMIN_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, alates, kuni, kommentaar, riigi_admin_yksuse_id, riigi_admin_yksuse_alluva_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, TODAY, NULL, 'Verilaske küla allub Viiratsi vallale', 2, 3);");
		statement.executeUpdate("INSERT INTO ADMIN_ALLUVUS("
				+ "avaja, avatud, muutja, muudetud, sulgeja, suletud, alates, kuni, kommentaar, riigi_admin_yksuse_id, riigi_admin_yksuse_alluva_id) VALUES("
				+ "'Mart Potter', TODAY, 'Mart Potter', TODAY, NULL, NULL, TODAY, NULL, 'Ärma talu allub Verilaske külale', 3, 4);");	
	}
}