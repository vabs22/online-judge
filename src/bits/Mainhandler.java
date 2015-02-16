/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;

import static bits.Compareoutput.compare;
import static bits.Submission.checknrun;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author vaibhav
 */
public class Mainhandler {
    public static String Control(String filename){
        //String str[]=filename.split("-");
        String logpath="/home/vaibhav/Documents/java/online_judge/compiler/server/Monitor/";
        boolean value ;
        String team=null,file=null,result;
        try{
        HashMap check = new HashMap();
        String str[]=filename.split("-");
        file=str[1];
        team=str[0];
        System.out.println(file);
        check = checknrun(team,file);
        value=(boolean) check.get("state");
        if(value)
        {
            if(compare(team,file))
            {
                result="Accepted";
                
                
            }
            else
            {
                result="Wrong answer";
                
            }
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logpath+"accepted_log.txt", true)))) {
                out.println(filename+" : "+result+"\n");
            }catch (IOException e) {
                System.out.println("Loggin error : ");
                e.printStackTrace();
            }
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logpath+"overall_log.txt", true)))) {
                out.println(filename+" : "+result+"\n");
            }catch (IOException e) {
                System.out.println("Loggin error : ");
                e.printStackTrace();
            }
            return result;
        }
        else
        {
            result=(String) check.get("result");
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logpath+"overall_log.txt", true)))) {
                out.println(filename+" : "+result+"\n");
            }catch (IOException e) {
                System.out.println("Loggin error : ");
                e.printStackTrace();
            }

            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logpath+"error_log.txt", true)))) {
                out.println(filename+" : "+result+"\n");
            }catch (IOException e) {
                System.out.println("Loggin error : ");
                e.printStackTrace();
            }
            return result;
        }
    }catch(Exception e)
    {
        e.printStackTrace();
    }
        return "Some error occured \n Please try again";
        
        
    }
    
}
