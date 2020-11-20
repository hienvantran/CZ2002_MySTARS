package DB;
import java.io.*;
import java.util.*;

public class IOforDB {
	public static ArrayList<String> read(String filename) throws FileNotFoundException{
		ArrayList mydata = new ArrayList();
		//the file to be opened for reading  
		System.out.println(filename);
		
		
		Scanner scanner = new Scanner(new FileInputStream(filename));  //file to be scanned 
		try {
			while (scanner.hasNextLine()) {
				mydata.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return mydata;
	}
	public static void write(String fileName, ArrayList mydata) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName, false));

		try {
			for (int i = 0; i < mydata.size(); i++) {
				out.println((String) mydata.get(i));
			}
		} finally {
			out.close();
		}
	}
}
