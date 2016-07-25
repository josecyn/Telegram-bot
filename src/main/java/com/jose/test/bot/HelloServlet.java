package com.jose.test.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

public class HelloServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletInputStream inputStream = req.getInputStream();
		
		String result = new BufferedReader(new InputStreamReader(inputStream))
				  .lines().collect(Collectors.joining("\n"));

		resp.setStatus(HttpStatus.OK_200);
		resp.getWriter().println(result);
	}
}
