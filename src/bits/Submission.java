/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bits;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vaibhav
 */
public class Submission {
    private static boolean compileCFile(String fileName,String user,String question)
    {
        String compileFileCommand = "timeout 3s gcc /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/uploads/" + fileName + " -o /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/bin/" + question;
        int flag=0;

        String resultString = "";
        try
        {
            System.out.println("Compiling C File");

            Process processCompile = Runtime.getRuntime().exec(compileFileCommand);

            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));

            String errorCompile ;
            while((errorCompile = brCompileError.readLine())!=null)
            {
                flag=1;
                resultString+=errorCompile+"\n";
            }
            if(flag==1)
            {
                finresult=resultString + "\n";
                return false;
            }


            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
             String outputCompile ;
            while((outputCompile = brCompileRun.readLine())!=null)
            {
                flag=1;
                resultString+=outputCompile+"\n";
            }

            if(flag==1)
            {
                finresult=resultString + "\n";
            }
            return true;

        } catch (Exception e)
        {
            // TODO: handle exception
            //System.out.println("Exception ");
            System.out.println(e.getMessage());
            finresult="Exception: "+ e.getMessage();
            return false;
        }
    }

    private static boolean runCFile(String fileName,String user,String question)
    {
        String runFileCommand = "timeout 5s /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/bin/./" + question;
        String line; 
        int flag=0;
        //char[] inp = str.toCharArray();
        OutputStream stdin = null;
        InputStream stderr = null;
        InputStream stdout = null;

        try
        {

            System.out.println("Running C File");

            //Process processRun = Runtime.getRuntime().exec(runFileCommand);
            Process processRun = new ProcessBuilder(runFileCommand,"str","3").start();
            stdin = processRun.getOutputStream ();
            stderr = processRun.getErrorStream ();
            stdout = processRun.getInputStream ();

            BufferedReader br = new BufferedReader(new FileReader("/home/vaibhav/Documents/java/online_judge/compiler/server/inputs/"+question+".txt"));
            //String line;
            while ((line = br.readLine()) != null) {
                    line=line+"\n";
                    stdin.write(line.getBytes() );
            }
            br.close();
            stdin.flush();
            stdin.close();
            line="";

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun;
            while((errorRun = brRun.readLine())!=null)
            {
                line+= errorRun+"\n";
                flag=1;
            }
            brRun.close();
            if(flag==1)
            {
                line+= "\n";
                finresult=line;
                return false;
            }
            //if (errorRun != null)
            PrintWriter writer = new PrintWriter("/home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/outputs/" + question+".txt", "UTF-8");
            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
            String outputRun;
            while( (outputRun = brResult.readLine())!=null)
            {
                System.out.println(outputRun);
                writer.println(outputRun);
            }
            writer.close();
            brResult.close();
            return true;

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
            finresult="Exception " + e.getMessage();
            return false;
        }

    }

    private static boolean compileCPPFile(String fileName,String user,String question)
    {
        String compileFileCommand = "timeout 3s g++ /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/uploads/" + fileName + " -o /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/bin/" + question;
        String resultString = "";
        int flag=0;

        try
        {
            System.out.println("Compiling CPP File");

            Process processCompile = Runtime.getRuntime().exec(compileFileCommand);

            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String errorCompile ;
            while((errorCompile = brCompileError.readLine())!=null)
            {
                flag=1;
                resultString+=errorCompile+"\n";
            }
            if(flag==1)
            {
                finresult=resultString + "\n";
                return false;
            }


            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String outputCompile ;
            while((outputCompile = brCompileRun.readLine())!=null)
            {
                flag=1;
                resultString+=outputCompile+"\n";
            }

            if(flag==1)
            {
                finresult=resultString + "\n";
            }
            return true;
            //resultString += outputCompile +"\n";

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
            finresult="Exception : "+e.getMessage();
            return false;
        }
    }

    private static boolean runCPPFile(String fileName,String user,String question)
    {
        String runFileCommand = "timeout 5s /home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/bin/./" + question;
        String line,fin;
        int flag=0;
        OutputStream stdin = null;
        InputStream stderr = null;
        InputStream stdout = null;

        try
        {
            System.out.println("Running CPP File");

            Process processRun = Runtime.getRuntime().exec(runFileCommand);

            stdin = processRun.getOutputStream ();
            stderr = processRun.getErrorStream ();
            stdout = processRun.getInputStream ();
            BufferedReader br = new BufferedReader(new FileReader("/home/vaibhav/Documents/java/online_judge/compiler/server/inputs/"+question+".txt"));
            
            while ((line = br.readLine()) != null) {
                    line=line+"\n";
                    stdin.write(line.getBytes() );
            }
            br.close();
            stdin.flush();
            stdin.close();
            line="";

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun;
            while((errorRun = brRun.readLine())!=null)
            {
                line+= errorRun+"\n";
                flag=1;
            }
            brRun.close();
            if(flag==1)
            {
                line+= "\n";
                finresult=line;
                return false;
            }
           
            PrintWriter writer = new PrintWriter("/home/vaibhav/Documents/java/online_judge/compiler/server/user/"+user+"/outputs/" + question+".txt", "UTF-8");
            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
            String outputRun;
            while( (outputRun = brResult.readLine())!=null)
            {
                System.out.println(outputRun);
                writer.println(outputRun);
            }
            writer.close();
            brResult.close();
            return true;
            //while(outputRun != null)

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
            finresult="Exception " + e.getMessage();
            return false;
        }
    }
    
    public static String finresult = "";

    public static HashMap checknrun(String user,String file)
    {
        String text=file;
        String question;
        HashMap response;
        response = new HashMap();
        String[] str1 = text.split("\\.");
        String extension = str1[1];
        question = str1[0];
        extension=extension.trim();
        if(extension.equals("cpp"))
        {
            if(compileCPPFile(text,user,question))
            {
                if(runCPPFile(text,user,question))
                {
                    System.out.println("Program ran successfully !");
                    response.put("result",finresult);
                    response.put("state",true);
                    return response;
                }
                else
                {
                    System.out.println("RunTime error : "+ finresult);
                    System.out.println("Program ran successfully !");
                    response.put("result",finresult);
                    response.put("state",false);
                    return response;

                }
            }
            else
            {
                System.out.println("Compile time error");
                response.put("result",finresult);
                response.put("state",false);
                return response;
                
            }
        }
        else if(extension.equals("c"))
        {
            if(compileCFile(text,user,question))
            {
                if(runCFile(text,user,question))
                {
                    System.out.println("Program ran successfully !");
                    response.put("result",finresult);
                    response.put("state",true);
                    return response;
                }
                else
                {
                    System.out.println("RunTime error : "+ finresult);
                    response.put("result",finresult);
                    response.put("state",false);
                    return response;

                }
            }
            else
            {
                System.out.println("Compile Time error : "+ finresult);
                response.put("result",finresult);
                response.put("state",false);
                return response;

            }
        }
        else
        {
            finresult="Extension couldn't be readed or recognized\n";
            response.put("result",finresult);
            response.put("state",false);
            return response;
            
        }
    }
    
}
