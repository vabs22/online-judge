/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author vaibhav
 */
public class Clientfupload {
    //public static final int PORT = 3337;
    public static final int BUFFER_SIZE = 100;
    public static Socket socketobject = null;

    public static boolean fileupload (String team,String filename,int port,String address,String path,String question){
        try{
       
            Socket socketobject = new Socket(address,port);
            String request = "";
            int i=4,bytesRead=0;
            try{
                OutputStream writer = socketobject.getOutputStream();
                FileOutputStream fos = null;
                
                String[] str1 = filename.split("\\.");
                String extension = str1[1];
                File file = new File(path+filename);
                if(file.exists() && !file.isDirectory()) { 
                    
                    ObjectOutputStream oos = new ObjectOutputStream(socketobject.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socketobject.getInputStream());
                    oos.writeObject(team+"-"+filename);

                    FileInputStream fis = new FileInputStream(file);
                    byte [] buffer = new byte[BUFFER_SIZE];
                    bytesRead = 0;

                    while ((bytesRead = fis.read(buffer)) > 0) {
                        oos.writeObject(bytesRead);
                        oos.writeObject(Arrays.copyOf(buffer, buffer.length));
                    }
                    
                    String message = (String)ois.readObject();
                    System.out.println("Message received from the server : " +message);
                    oos.close();
                    ois.close();
                    fis.close();
                    buffer=null;
                }
                else{
                    System.out.println("File not found !");
                    return false;
                }
            }finally{
                socketobject.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    } 
}
