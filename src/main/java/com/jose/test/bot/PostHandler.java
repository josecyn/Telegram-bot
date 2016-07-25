package com.jose.test.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.json.JSONObject;

public class PostHandler extends ContextHandler {
	    
	public PostHandler() {
      setContextPath("/app");
      setAllowNullPathInfo(true);
    }

    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request,
        HttpServletResponse response) throws IOException, ServletException {
    	
      System.out.println(request.getMethod());
      
      ServletInputStream inputStream = request.getInputStream();
		
      String result = new BufferedReader(new InputStreamReader(inputStream))
				  .lines().collect(Collectors.joining("\n"));
      
      JSONObject obj = new JSONObject(result);
      String username = obj.getString("username");
      
      System.out.println("Username: "+username);

      response.setStatus(HttpStatus.OK_200);
      response.getWriter().println(username);

      baseRequest.setHandled(true);
    }
  }

