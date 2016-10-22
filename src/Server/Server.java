package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

class ServerThread extends Thread {
	private static final int PORT = 2014;
	private Socket client;
	private DataInputStream dis;
	private FileOutputStream fos;

	public ServerThread(Socket accept) {
		client = accept;
	}

	public void run() {
		try {
			dis = new DataInputStream(client.getInputStream());
			String action = dis.readUTF();
			if (action.equals("upload")) {
				upload();
			}
			if (action.equals("download")) {
				download();
			}
			if (action.equals("getFileList")) {
				getFileList();
			}
		} catch (Exception e) {
		}

	}

	private void getFileList() {
		try{
			DataOutputStream dos =new DataOutputStream(client.getOutputStream());
	        
	        //文件名和长度
	        dos.writeUTF("getFileList");
	        dos.flush();
	        
	        Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://59.69.101.2:3306/javadb",
							"java", "whut_java");
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery("select * from myfile");
			while (rs.next()){
				dos.writeUTF(rs.getString("filename"));
		        dos.flush();
		        dos.writeUTF(rs.getString("path"));
		        dos.flush();
			}
	        
		}catch(Exception e){}
         

	}

	private void download() {
		// TODO Auto-generated method stub

	}

	private void upload() {
		try {
			// 文件名和长度
			String fileName = dis.readUTF();
			long fileLength = dis.readLong();
			fos = new FileOutputStream(new File("d:/" + fileName));

			byte[] sendBytes = new byte[1024];
			int transLen = 0;
			System.out.println("----开始接收文件<" + fileName + ">,文件大小为<"
					+ fileLength + ">----");
			while (true) {
				int read = 0;
				read = dis.read(sendBytes);
				if (read == -1)
					break;
				transLen += read;
				System.out.println("接收文件进度" + 100 * transLen / fileLength
						+ "%...");
				fos.write(sendBytes, 0, read);
				fos.flush();
			}
			System.out.println("----接收文件<" + fileName + ">成功-------");
			client.close();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://59.69.101.2:3306/javadb",
							"java", "whut_java");
			Statement sta = con.createStatement();
			sta.executeUpdate("insert into myfile (filename, path) values ('"
					+ fileName + "','" + "d:/" + fileName + "')");
		} catch (Exception e) {
		}

	}
}

public class Server extends ServerSocket {
	private ServerSocket server;
	private static final int PORT = 2014;

	private Socket client;

	public Server() throws Exception {
		try {
			try {
				server = new ServerSocket(PORT);
				boolean listening = true;
				while (listening)
					new ServerThread(server.accept()).start();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new Server();
	}
}

