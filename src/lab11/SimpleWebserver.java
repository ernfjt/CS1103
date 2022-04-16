package lab11;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author anonymous
 *
 */
public class SimpleWebserver {
	/**
	 * The server listens on this port. Note that the port number must be greater
	 * than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;

	/**
	 * Main program opens a server socket and listens for connection requests. The
	 * program runs in an infinite loop, unless an error occurs.
	 * 
	 * @param args ignored
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		} catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
				// handleConnection(connection);
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}
		serverSocket.close();
	}

	/**
	 * This method gets an already connected socket as a parameter. It can use that
	 * socket to get an InputStream and an OutputStream for communicating over the
	 * connection. It can read the request from the input stream and send a response
	 * to the output stream. Finally, it can close the connection.
	 * 
	 * @param connection
	 */
	private static void handleConnection(Socket connection) {
		try {
			Scanner in = new Scanner(connection.getInputStream());
			OutputStream os = connection.getOutputStream();
			String rootDirectory = "./src/www";
			int linenum = 0;
			String line = "";
			// get the first line from InputStream
			while (true) {
				if (!in.hasNextLine())
					break;
				linenum++;
				line = in.nextLine();
				if (line.trim().length() == 0)
					break;
				if (linenum == 1)
					break;
				// System.out.println(" " + line);
			}

			String[] data = line.split(" ");
			String requestType = data[0];
			String pathToFile = data[1];
			String htmlVersion = data[2];
			// System.out.println(rootDirectory + pathToFile);
			File file = new File(rootDirectory + pathToFile);

			// If the file doesn't exist this method calls sendError(). On the other hand,
			// when the file exists the status indicates a good response.
			if (!requestType.equals("GET")) {
				sendError(501, os);
				in.close();
				connection.close();
				return;
			} else if (!htmlVersion.equalsIgnoreCase("HTTP/1.1") && !htmlVersion.equalsIgnoreCase("HTTP/1.0")) {
				sendError(400, os);
				in.close();
				connection.close();
				return;
			} else if (file.exists()) {
				if (!file.isDirectory() && file.length() != 0) {
					if (file.canRead()) {
						PrintWriter output = new PrintWriter(os);
						output.print(htmlVersion + " 200 OK\r\n");
						output.print("Connection:close\r\n");
						output.print("Content-Type:" + getMimeType(pathToFile) + "\r\n");
						output.print("Content-Length:" + file.length() + "\r\n");
						output.print("\r\n");
						output.flush();
						sendFile(file, os);
						output.close();
						// return;
					} else {
						sendError(403, os);
						in.close();
						connection.close();
						return;
					}
				}
			} else {
				sendError(404, os);
				in.close();
				connection.close();
				return;
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
		} finally { // make SURE connection is closed before returning!
			try {
				connection.close();
			} catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}

	/**
	 * Displays an error message depending on the response passed error code.
	 * 
	 * @param errorCode
	 * @param os
	 */
	static void sendError(int errorCode, OutputStream os) {

		String errorStatus = "";
		String errorMsg = "";
		switch (errorCode) {
		case 400:
			errorStatus = "400 Bad Request";
			errorMsg = "The server cannot process the request due to an issue on the client side.";
			break;
		case 403:
			errorStatus = "403 Forbidden";
			errorMsg = "A web server forbids your access.";
			break;
		case 404:
			errorStatus = "404 Not Found";
			errorMsg = "The resource that you requested does not exist on this server.";
			break;
		case 500:
			errorStatus = "500 Internal Server Error";
			errorMsg = "Some kind of error occurred inside the server.";
			break;
		case 501:
			errorStatus = "501 Not Implemented";
			errorMsg = "The server has not implemented the functionality necessary to fulfill the request.";
			break;
		}

		try {
			PrintWriter output = new PrintWriter(os);
			output.print("HTTP/1.1" + errorStatus + "\r\n");
			output.print("Connection: close\r\n");
			output.print("Content-Type: text/html\r\n");
			output.print("\r\n");
			output.print("<html><head><title>Error</title></head><body>\r\n");
			output.print("<h2>Error:" + errorStatus + "</h2>\r\n");
			output.print(errorMsg + "\r\n");
			output.print("</body></html>\r\n");
			output.flush();
			output.close();
		} catch (Exception e) {
			//
		}
	}

	/**
	 * A method that will return the proper content type for many kinds of files
	 * 
	 * @param fileName
	 * @return content type
	 */
	private static String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos + 1).toLowerCase();
		if (ext.equals("txt"))
			return "text/plain";
		else if (ext.equals("html"))
			return "text/html";
		else if (ext.equals("htm"))
			return "text/html";
		else if (ext.equals("css"))
			return "text/css";
		else if (ext.equals("js"))
			return "text/javascript";
		else if (ext.equals("java"))
			return "text/x-java";
		else if (ext.equals("jpeg"))
			return "image/jpeg";
		else if (ext.equals("jpg"))
			return "image/jpeg";
		else if (ext.equals("png"))
			return "image/png";
		else if (ext.equals("gif"))
			return "image/gif";
		else if (ext.equals("ico"))
			return "image/x-icon";
		else if (ext.equals("class"))
			return "application/java-vm";
		else if (ext.equals("jar"))
			return "application/java-archive";
		else if (ext.equals("zip"))
			return "application/zip";
		else if (ext.equals("xml"))
			return "application/xml";
		else if (ext.equals("xhtml"))
			return "application/xhtml+xml";
		else
			return "x-application/x-unknown";
		// Note: x-application/x-unknown is something made up;
		// it will probably make the browser offer to save the file.
	}

	/**
	 * This method can be used to copy the content of the file to the socket's output stream
	 * 
	 * @param file
	 * @param socketOut
	 * @throws IOException
	 */
	private static void sendFile(File file, OutputStream socketOut) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x); // write the byte to the socket
		}
		out.flush();
		in.close();
	}

	/**
	 * The method makes your server into a multi-threaded server.
	 * @author anonymous
	 *
	 */
	private static class ConnectionThread extends Thread {
		Socket connection;

		ConnectionThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			handleConnection(connection);
		}
	}
}
