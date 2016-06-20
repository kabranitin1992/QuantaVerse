package com.QV.algo_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Algo_1 {

	static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		String line = null;
		String[] names = null;
		HashMap<Character, ArrayList<String>> indexList = new HashMap<Character, ArrayList<String>>();
		FileReader fileReader = new FileReader("/home/nitin/full_stack_test/data/names.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		try {
			while ((line = bufferedReader.readLine()) != null) {
				names = line.split(Pattern.quote("|"));
			}
			int count=0;
			for(String str:names){
				if(str.toLowerCase().contains("nitin")){
					System.out.println(str);
					count++;
				}
			}
			System.out.println(count);
			fileReader.close();
			bufferedReader.close();
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		for (Character ch : indexList.keySet()) {
			System.out.println(ch.toString() + "   ");
			System.out.println(indexList.get(ch).toString());
		}
		System.out.println("Took " + (endTime - startTime) + " ns");
	}
}
