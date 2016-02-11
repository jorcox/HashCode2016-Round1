package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

	private String filePath;
	private File file;
	private FileInputStream in;
	private BufferedReader br;
	
	public FileManager(String filePath){
		this.filePath = filePath;
		file = new File(filePath);
		try {
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in));
		} catch (FileNotFoundException e) {
			in = null;
			br = null;
			e.printStackTrace();
		}
	}
	
	public String readNextLine(){
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public byte[] readAllBytes(){
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
	
	public static List<String> split(String in){
		List<String> res = new ArrayList<String>();
		
		Scanner s = new Scanner(in);
		s.useDelimiter(" ");
		while(s.hasNext()){
			res.add(s.next());
		}
		
		return res;
	}
}
