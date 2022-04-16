package unit6_lj;

import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {
	public static void DirectoryList(File dirPath, String fileName) {
		File filelist[] = dirPath.listFiles();
		System.out.println("Files in directory \"" + dirPath + "\":");
		for (File file: filelist) {
			if(file.isFile()) {
				System.out.println(file.getName());
			}else {
				DirectoryList(file, "");
			}
		}	
	}

   public static void main(String[] args) {

      String directoryName;  // Directory name entered by the user.
      File directory;        // File object referring to the directory.
      String[] files;        // Array of file names in the directory.
      Scanner scanner;       // For reading a line of input from the user.

      scanner = new Scanner(System.in);  // scanner reads from standard input.

      System.out.print("Enter a directory name: ");
      directoryName = scanner.nextLine().trim();
      directory = new File(directoryName);
      DirectoryList(directory, "");

   } // end main()

} // end class DirectoryList
