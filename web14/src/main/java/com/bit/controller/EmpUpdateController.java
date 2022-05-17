package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.util.BitMysql;

public class EmpUpdateController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String[] params = {
				req.getParameter("empno"),
				req.getParameter("ename"),
				req.getParameter("sal")
		};
		
		try(
				Connection conn = BitMysql.getConnection();
				Statement stmt = conn.createStatement();
			){
				int empno = Integer.parseInt(params[0].trim());
				String ename = params[1].trim();
				int sal = Integer.parseInt(params[2].trim());
				//UPDATE emp SET ENAME = 'park', `SAL` = '3800' WHERE (`EMPNO` = '12');
				String sql = "UPDATE emp SET ename = '" + ename + "', sal = " + sal
								+ " WHERE empno = '" + empno + "'"; 
			
				stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}catch (NumberFormatException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
