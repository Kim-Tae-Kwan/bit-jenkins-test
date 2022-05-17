package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.util.BitMysql;

public class EmpInsertController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String[] params = {
				req.getParameter("empno"),
				req.getParameter("ename"),
				req.getParameter("sal")
		};
		
		int empno = Integer.parseInt(params[0].trim());
		String ename = params[1].trim();
		int sal = Integer.parseInt(params[2].trim());
		
		String sql = "insert into emp (empno, ename, sal, hiredate) values (" 
						+ empno + ",'" + ename + "'," + sal +",now())";
		
		int result = 0;
		String errMsg = "";
		try(
				Connection conn = BitMysql.getConnection();
				Statement stmt = conn.createStatement();
			){
				result = stmt.executeUpdate(sql);
		}catch (Exception e) {
			e.printStackTrace();
			errMsg = e.getMessage();
		}
		resp.setContentType("applicaion/json");
		
		out.print("{\"insert\" : [{\"result\" : ");
		out.print(result > 0 ? true : false);
		out.print(", \"err\" : " + "\"" + errMsg + "\"");
		out.println("}]}");
	}
}
