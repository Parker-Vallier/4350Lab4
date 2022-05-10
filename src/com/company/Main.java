package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/4350lab4?useSSL=false", "root", "NotZane");

        Statement st = con.createStatement();

        // Testing Connection
        ResultSet rs = st.executeQuery("SELECT * FROM bus");
        while(rs.next()){
            int BusID = rs.getInt("BusID");
            String Model = rs.getString("Model");
            int Year = rs.getInt("Year");

            System.out.println(BusID + " " + Model + " " + Year);
        }

        // Number 1
        String inStartLocationName = "";
        String inDestinationName = "";
        String inDate = "";
        String query = "SELECT A.StartLocationName, A.DestinationName, B.Date, B.ScheduledStartTime, B.ScheduledArrivalTime, B.DriverName, B.BusID FROM trip A " +
                        "LEFT JOIN tripoffering B " +
                        "ON A.TripNumber = B.TripNumber " +
                        "WHERE A.StartLocationName = \"" + inStartLocationName + "\" AND A.DestinationName = \"" + inDestinationName + "\" AND B.Date = \"" + inDate + "\"";
        rs = st.executeQuery(query);
        System.out.println("Number 1");
        while(rs.next()){
            String StartLocationName = rs.getString("StartLocationName");
            String DestinationName = rs.getString("DesinationName");
            String Date = rs.getString("Date");
            String ScheduleStartTime = rs.getString("ScheduleStartTime");
            String ScheduleArrivalTime = rs.getString("ScheduleArrivalTime");
            String DriverName = rs.getString("DriverName");
            int BusID = rs.getInt("BusID");

            System.out.println(StartLocationName + " " + DestinationName + " " + Date + " " + ScheduleStartTime + " " + ScheduleArrivalTime + " " + DriverName + " " + BusID);
        }


        // Number 2
        System.out.println("Number 2");
        int inTripNumber = 0;
        inDate = "";
        String inScheduledStartTime = "";
        query = "DELETE FROM tripoffering WHERE TripNumber = \""+ inTripNumber +"\" AND Date = \""+ inDate +"\" AND ScheduledStartTime = \" "+ inScheduledStartTime + "\"";
        try{
            rs = st.executeQuery(query);
            System.out.println("Deleted :)");
        }catch (Exception e){
            System.out.println("Not in the table :(");
        }




        st.close();
        con.close();
    }
}
