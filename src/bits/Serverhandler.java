/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;
import static bits.Mainhandler.Control;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Serverhandler {
	private static ServerSocket server = null;
	public static final int PORT = 3339;
	public static final int BUFFER_SIZE = 100;

	private static ExecutorService threadPool;

	public static void main (String[] args) {
            String address="localhost";

		try{
			threadPool = Executors.newCachedThreadPool();
			threadPool.submit(new Monitor());
			server = new ServerSocket (PORT);
			System.out.println("Server started !");
			System.out.println("Hit enter to stop the server");

			while(true){
				Socket socketObject = server.accept();
				System.out.println("New thread created! ");
				threadPool.submit(new EchoThread(socketObject));

			}
		}catch(SocketException e){
			e.printStackTrace();
			System.out.println("Server is down");
		}  catch(IOException ex){
			ex.printStackTrace();
		}
	}

	private static void shutdownServer() {
		try{
			server.close();
		} catch( IOException ex){

		}
		System.exit(0);
	}

	private static class  Monitor implements Runnable {
		@Override
		public void run(){
			try{
				while(System.in.read()!='\n'){

				}
			}catch(IOException ex){
			}
				shutdownServer();
		
		}
		
	}
	private static class EchoThread implements Runnable{
		private Socket socketobject = null;

		public EchoThread(Socket socketObject){
			this.socketobject = socketObject;
		}
		@Override
		public void run(){
			try{
                        String team="vaibhav";
			InputStream reader= socketobject.getInputStream();
		        ObjectInputStream ois = new ObjectInputStream(socketobject.getInputStream());
		        ObjectOutputStream oos = new ObjectOutputStream(socketobject.getOutputStream());
		        FileOutputStream fos = null;
                        String path="/home/vaibhav/Documents/java/online_judge/compiler/server/user/"+team+"/uploads/",filename=null;
		        try{
                        byte [] buffer = new byte[BUFFER_SIZE];

			// 1. Read file name.
                        Object o = ois.readObject();
                 
                        if (o instanceof String) {
                            filename = o.toString();
                            String str[]=filename.split("-");
                            path += str[1];
                            fos = new FileOutputStream(path);
                        } else {
                            throw new Exception("Something is wrong");
                        }
                        
                        // 2. Read file to the end.
                        int bytesRead = 0;
                 
                        do {
                            o = ois.readObject();
                 
                            if (!(o instanceof Integer)) {
                                throwException("Something is wrong");
                            }
                 
                            bytesRead = (Integer)o;
                 
                            o = ois.readObject();
                 
                            if (!(o instanceof byte[])) {
                                throwException("Something is wrong");
                            }
                 
                            buffer = (byte[])o;
                 
                            // 3. Write data to output file.
                            fos.write(buffer, 0, bytesRead);
                           
                        } while (bytesRead == BUFFER_SIZE);
                         
                        System.out.println("File transfer success");
                        
                        String message = Control(filename);
                        oos.writeObject(message);

                        oos.close();
                        fos.close();
                 
                        ois.close();
				}finally{
					socketobject.close();
				}
			}catch(Exception e)
			{
                            e.printStackTrace();
			}
		}

        private void throwException(String something_is_wrong) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
	}
}