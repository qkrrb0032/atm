package atm;

import java.io.*;

public class FileManager {
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private FileManager() {}
	
	private static FileManager instance = new FileManager();
	
	public static FileManager getInstance() {
		return instance;
	}
	
}
