package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GetClient
{
    public static void main(String[] args)
    {
        String menu = "-1";
        do
        {
            try
            {
                URL url = new URL("http://localhost:8080/api/tutorial/1.0/employees");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Scanner input = new Scanner(System.in);
                String in;
                OutputStream os;

                BufferedReader br;
                String output;

                System.out.println(""
                        + "1) GET\n"
                        + "2) POST\n"
                        + "3) DELETE\n"
                        + "4) GET ID\n"
                        + "5) PATCH\n"
                        + "6) PUT\n");
                menu = input.nextLine();
                switch(menu)
                {
                    case "1":
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/json");

                        System.out.println("Output dal server .... \n");
                        br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                        while ((output = br.readLine()) != null)
                        {
                            System.out.println(output);
                        }
                        System.out.println("\n");
                        break;

                    case "2":
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json");

                        in = "{\"employeeId\":4,\"firstName\":\"Mauro\",\"lastName\":\"Lapio\",\"email\":\"lapiomauro@gmail.com\",\"phone\":\"3205644629\"}";

                        conn.setDoOutput(true);
                        os = conn.getOutputStream();
                        os.write(in.getBytes());
                        os.flush();

                        System.out.println("Aggiunto dipendente\n");
                        break;

                    case "3":
                        url = new URL("http://localhost:8080/api/tutorial/1.0/employees/4");
                        conn = (HttpURLConnection) url.openConnection();

                        System.out.println(url.toString());
                        conn.setRequestMethod("DELETE");
                        conn.setRequestProperty("Accept", "application/json");

                        System.out.println("Eliminato dipendente\n");
                        break;

                    case "4":
                        url = new URL("http://localhost:8080/api/tutorial/1.0/employees/1");
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/json");

                        System.out.println("Output dal server .... \n");
                        br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                        while ((output = br.readLine()) != null)
                        {
                            System.out.println(output);
                        }
                        System.out.println("\n");
                        break;
                    
                    case "5":
                        conn.setRequestMethod("PATCH");
                        conn.setRequestProperty("Content-Type", "application/json");
                        url = new URL("http://localhost:8080/api/tutorial/1.0/employees/4");
                        conn = (HttpURLConnection) url.openConnection();

                        in = "{\"firstName\":\"Lauro\",\"lastName\":\"Mapio\"}";

                        conn.setDoOutput(true);
                        os = conn.getOutputStream();
                        os.write(in.getBytes());
                        os.flush();

                        System.out.println("Modificato dipendente\n");
                        break;
                        
                    case "6":
                        url = new URL("http://localhost:8080/api/tutorial/1.0/employees/1");
                        conn = (HttpURLConnection) url.openConnection();
                        
                        conn.setRequestMethod("PUT");
                        conn.setRequestProperty("Content-Type", "application/json");
                        
                        in = "{\"employeeId\":1,\"firstName\":\"Lauro\",\"lastName\":\"Mapio\",\"email\":\"mapiolauro@gmail.com\",\"phone\":\"4206991169\"}";

                        conn.setDoOutput(true);
                        os = conn.getOutputStream();
                        os.write(in.getBytes());
                        os.flush();

                        System.out.println("Modificato dipendente\n");
                        break;
                    
                    default:
                        System.out.println("Errore input");
                        break;
                }

                if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201)
                {
                        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
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
        while(menu!="0");
    }
}