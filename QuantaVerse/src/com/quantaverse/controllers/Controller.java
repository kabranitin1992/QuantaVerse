package com.quantaverse.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	HashMap<Character, ArrayList<String>> indexList = new HashMap<Character, ArrayList<String>>();
	//Initialize method to Index the Names using just first Character  
	public void init(){
		String line = null;
		String[] names = null;
		
		FileReader fileReader;
		try {
			//Reading file and splitting the names
			fileReader = new FileReader("/home/nitin/full_stack_test/data/names.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);while ((line = bufferedReader.readLine()) != null) {
				names = line.split(Pattern.quote("|"));
			}
			for (String singleName : names) {
				String[] allNames = singleName.split(" ");
				//Putting data in HashMap while indexing 
				for (String str : allNames) {
					if (str.length() > 0) {
						if (indexList.containsKey(str.toLowerCase().charAt(0))) {
							indexList.get(str.toLowerCase().charAt(0)).add(singleName);
						} else {
							ArrayList<String> newList = new ArrayList<String>();
							newList.add(singleName);
							indexList.put(str.toLowerCase().charAt(0), newList);
						}
					}
				}
			}
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Getting the Search Query
		String name = request.getParameter("query");
		ArrayList<String> resultSet = indexList.get(name.toLowerCase().charAt(0));
		JSONArray matchedData = new JSONArray();
		//Searching in HashMap using index
		for (int i = 0; i < resultSet.size(); i++) {
				String singleName = resultSet.get(i);
			if(singleName.toLowerCase().contains(name.toLowerCase())){
				//Data to JSON object
				JSONObject singleData = new JSONObject();
				singleData.put("name", singleName);
				//Calculating the Matching Score
				//Getting percentile of Matched String with Original Name
				singleData.put("score", (name.length()*100/singleName.length()));
				try {
					//Sleeping the Thread to calculate the Score
					Thread.sleep((long) 0.1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Adding in JSON Array
				matchedData.put(singleData);
			}
		}
		//Responding in JSON format
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(matchedData.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
