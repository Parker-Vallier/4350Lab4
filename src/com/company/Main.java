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
        try {
            rs = st.executeQuery(query);
            System.out.println("Number 1");
            while (rs.next()) {
                String StartLocationName = rs.getString("StartLocationName");
                String DestinationName = rs.getString("DesinationName");
                String Date = rs.getString("Date");
                String ScheduleStartTime = rs.getString("ScheduleStartTime");
                String ScheduleArrivalTime = rs.getString("ScheduleArrivalTime");
                String DriverName = rs.getString("DriverName");
                int BusID = rs.getInt("BusID");

                System.out.println(StartLocationName + " " + DestinationName + " " + Date + " " + ScheduleStartTime + " " + ScheduleArrivalTime + " " + DriverName + " " + BusID);
            }
        } catch (Exception e){
            System.out.println("Problems :(");
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

        inTripNumber = 0;
        inDate = "";
        inScheduledStartTime = "";
        String inScheduledArrivalTime = "";
        String inDriverName = "";
        int inBusID = 0;
        query = "INSERT INTO tripoffering VALUE (\""+ inTripNumber +"\", \""+ inDate +"\", \""+ inScheduledStartTime +"\", \""+ inScheduledArrivalTime +"\", \""+ inDriverName +"\", \""+ inBusID +"\")";
        try{
            rs = st.executeQuery(query);
            System.out.println("Added :)");
        }catch (Exception e){
            System.out.println("Can't add that :(");
        }

        inTripNumber = 0;
        inDate = "";
        inScheduledStartTime = "";
        inDriverName = "";
        query = "UPDATE tripoffering SET DriverName = \""+ inDriverName +"\" WHERE TripNumber = \""+ inTripNumber +"\" AND Date = \""+ inDate +"\" AND ScheduledStartTime = \""+ inScheduledStartTime +"\"";
        try{
            rs = st.executeQuery(query);
            System.out.println("Updated :)");
        }catch (Exception e){
            System.out.println("Can't update that :(");
        }

        inTripNumber = 0;
        inDate = "";
        inScheduledStartTime = "";
        inBusID = 0;
        query = "UPDATE tripoffering SET BusID = \""+ inBusID +"\" WHERE TripNumber = \""+ inTripNumber +"\" AND Date = \""+ inDate +"\" AND ScheduledStartTime = \""+ inScheduledStartTime +"\"";
        try{
            rs = st.executeQuery(query);
            System.out.println("Updated :)");
        }catch (Exception e) {
            System.out.println("Can't update that :(");
        }


        // Number 3
        System.out.println("Number 3");
        inTripNumber = 0;
        query = "SELECT * FROM tripstopinfo WHERE TripNumber = "+ inTripNumber +" ORDER BY SequenceNumber ASC";
        try{
            rs = st.executeQuery(query);
            while (rs.next()){
                int TripNumber = rs.getInt("TripNumber");
                int StopNumber = rs.getInt("StopNumber");
                int SequenceNumber = rs.getInt("SequenceNumber");
                String DrivingTime = rs.getString("DrivingTime");
                System.out.println(TripNumber + " " + StopNumber + " " + SequenceNumber + " " + DrivingTime);
            }
        }catch (Exception e){
            System.out.println("Can't show that :(");
        }


        // Number 4
        System.out.println("Number 4");
        st.close();
        con.close();
    }
}
