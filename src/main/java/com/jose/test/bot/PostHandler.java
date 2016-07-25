package com.jose.test.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.json.JSONArray;
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
      
      Long chatid = null;
      try {
	      JSONObject obj = new JSONObject(result);
	      JSONArray resultArray = obj.getJSONArray("result");
	      JSONObject update = resultArray.getJSONObject(0);
	      JSONObject message = update.getJSONObject("message");
	      JSONObject chat = message.getJSONObject("chat");
	      chatid = chat.getLong("id");
      }
      catch (Exception ex) {
    	  ex.printStackTrace();
      }
      
      System.out.println("chat id:"+chatid);
      
      response.setStatus(HttpStatus.OK_200);
      
      try {
		String resultGet = doGet(chatid, "hi!");
		System.out.println(resultGet);
      } catch (Exception e) {
		e.printStackTrace();
      }

      baseRequest.setHandled(true);
    }
    
    public static String doGet(Long chatid, String text) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://api.telegram.org/bot259250951:AAHOf3pgP63TTt3ju0Mg7wmXbgPWuN3QWcA/sendMessage?chat_id="+chatid+"&text="+text);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
        return result.toString();
     }

    
  }

