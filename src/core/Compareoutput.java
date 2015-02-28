/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vaibhav
 */
public class Compareoutput {
    public static boolean compare(String team , String name1) {
        try {
                String name2,line1,line2;
                int flag=0;
                String [] str=name1.split("\\.");
                name1="/home/vaibhav/Documents/java/online_judge/compiler/server/user/"+team+"/outputs/"+str[0]+".txt";
                name2="/home/vaibhav/Documents/java/online_judge/compiler/server/outputs/"+str[0]+".txt";

                File file1 = new File(name1);
                File file2 = new File(name2);
                System.out.println(name1);


                BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));


                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));

                while(true){
                        if((line1 = bufferedReader1.readLine())!=null)
                        {
                                if((line2 = bufferedReader2.readLine())!=null)
                                {
                                        if(!line1.equals(line2))
                                        {
                                                flag=1;
                                                break;
                                        }
                                }
                                else
                                {
                                        flag=1;
                                        break;
                                }

                        }
                        else
                        {
                                if((line2 = bufferedReader2.readLine())!=null)
                                {
                                        flag=1;
                                        break;
                                }
                                else
                                {
                                        flag=0;
                                        break;
                                }

                        }
                }
                bufferedReader1.close();
                bufferedReader2.close();
                
                if(flag==1)
                        return false;
                else
                        return true;

                

        } catch (IOException e) {
                e.printStackTrace();
        }
        return false;
    }
    
}
