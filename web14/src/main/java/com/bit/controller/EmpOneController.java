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

import com.bit.model.EmpDto;
import com.bit.util.BitMysql;

public class EmpOneController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int empno = Integer.parseInt(req.getParameter("empno"));
		String sql = "select * from emp where empno = " + empno;
		
		resp.setContentType("applicaion/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		try(
				Connection conn = BitMysql.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
		){
			out.println("{\"emp\" : [");
			if(rs.next()) {
				EmpDto bean = new EmpDto();
				bean.setEmpno(rs.getInt("empno"));
				bean.setEname(rs.getString("ename"));
				bean.setSal(rs.getInt("sal"));
				
				out.println("{");
				out.println("\"empno\" : " + bean.getEmpno());
				out.println(",\"ename\" : \"" + bean.getEname() + "\"");
				out.println(",\"sal\" : " + bean.getSal());
				out.println("}");
				
				
			}
			out.println("]}");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
