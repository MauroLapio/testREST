package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GetClient
{
    public static void main(String[] args)
    {
        try
        {
            URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            Scanner input = new Scanner(System.in);
            int in;
            
            System.out.println("Quale metodo vuoi chiamare?\n"
                    + "1) GET\n"
                    + "2) POST\n"
                    + "3) DELETE\n");
            in = input.nextInt();
            switch(in)
            {
                case 1:
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    
                case 2:
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    
                case 3:
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                default:
                    System.out.println("errore");
            }

            if (conn.getResponseCode() != 200)
            {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null)
            {
                System.out.println(output);
            }

            conn.disconnect();
        }
        catch (MalformedURLException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
    }
}