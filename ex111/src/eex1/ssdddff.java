package eex1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ssdddff {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add("아나");
		ar.add("아나2");
		
		for (int i = 0; i < ar.size(); i++) {
			String aaa = ar.get(i);
			System.out.println(aaa);
		}
		
		String data1 = "c:\\iotest\\dataex.txt";
		File f = new File(data1);
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		
		bw.close();
		fw.close();

	}

}
