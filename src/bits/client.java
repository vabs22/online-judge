/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.lang.*;
import java.util.Scanner;

/**
 *
 * @author vaibhav
 */
public class client {
    public static final int PORT = 3339;
    public static final int BUFFER_SIZE = 1024;
    public static Socket socketobject = null;

    public static void main(String[] args ){
        try{
            String address="localhost";
            String team="vaibhav",question="check1",filename=null;
            String path="/home/vaibhav/Documents/java/online_judge/compiler/client/";
            Socket socketobject = new Socket(address,PORT);
            String request = "";
            int i=4,bytesRead=0;
            try{
               
                FileOutputStream fos = null;
                
                
                System.out.println("Enter the name of the file :");
                Scanner scanner = new Scanner(System.in);
                filename = scanner.nextLine();
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
                }
            }finally{
                socketobject.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
}
