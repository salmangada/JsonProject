import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class ReadJson {

    //Get hours from checkin and checkout time
    public static int getHour(String inTime, String outTime){

        long seconds = Duration.between(LocalTime.parse(inTime),LocalTime.parse(outTime)).getSeconds();

        return (int)seconds/3600;
    }

    public  static void main(String[] args) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the employee name");
        String emp = sc.nextLine();
        emp=emp.trim();

        int flag=0;

        try{
            JSONArray a = (JSONArray) jsonParser.parse(new FileReader(".\\JsonFIle\\Employee.json"));
            for(Object o : a){
                JSONObject employee = (JSONObject) o;

                String name  = (String) employee.get("employeName");

                if(name.compareToIgnoreCase(emp)==0){

                    String inTime = (String) employee.get("checkinTime");
                    String outTime = (String) employee.get("checkouttime");
                    String dept = (String) employee.get("dept");
                    String date = (String) employee.get("date");
                    int hours=getHour(inTime,outTime);

                    System.out.println("Employee name : "+name+" || Date : "+date+" || CheckinTime : "+inTime+" || CheckoutTime : "+outTime+" || Working hour : "+hours+ " || department : "+dept);
                    flag=1;
                }

            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }

        if(flag==0){
            System.out.println("Employee not found with the given name");
        }
    }

}
