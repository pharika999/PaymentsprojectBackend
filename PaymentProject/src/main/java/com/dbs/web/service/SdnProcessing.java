package com.dbs.web.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class SdnProcessing 
{
	public static ArrayList<String[]> processSDNfile() {
		BufferedReader br;
		String temp,k;
		int i=0;
		ArrayList<String []> l=new ArrayList<>();
		try {
			FileReader fr=new FileReader("C:\\Users\\Administrator\\Downloads\\sdnlist.txt");    
			br=new BufferedReader(fr);    
			// BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			while((k=br.readLine())!=null){
				k=k.replace("\"", "");
				k=k.replace("(","");
				k=k.replace("a.k.a.", ";");
				k=k.replace(")", ";");
				l.add(k.split(",")[0].split(";"));
			}

			       for (String[] element:l) 
			       { 
			           for(String names:element)
			           {
			               if(i==8359||i==1042)                               //printing to debug particular record or line number
			            System.out.print(names+"\t");
			            }
			          i++;
			        }
		}
		catch(IOException e) {
			System.out.println("Error in reading file \n"+e.getMessage());
		}
		return l;

	}



	public static boolean checkSDNlist(String Receivername)  {
		ArrayList<String[]> sdn =processSDNfile();
		for (String[] element:sdn) 
		{ 
			for(String names:element)
			{
				if(Receivername.equalsIgnoreCase(names.trim())) 
					return true; 
			} 
		}
		return false;
	}
}
