package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/4350lab4?useSSL=false";
        String username = "root";
        String password = "NotZane";
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();

        // Number 1
        numOne(con, st);


        // Number 2
        numTwo(con, st);


        // Number 3
        numThree(con, st);


        // Number 4
        numFour(con, st);


        // Number 5
        numFive(con, st);


        // Number 6
        numSix(con, st);


        // Number 7
        numSeven(con, st);


        // Number 8
        numEight(con, st);
        st.close();
        con.close();
    }

    public static void numOne(Connection con, Statement st){
        System.out.println("\nNumber 1");
        String inStartLocationName = "";
        String inDestinationName = "";
        String inDate = "2000-01-01";
        String query = "SELECT A.StartLocationName, A.DestinationName, B.Date, B.ScheduledStartTime, B.ScheduledArrivalTime, B.DriverName, B.BusID FROM trip A " +
                "LEFT JOIN tripoffering B " +
                "ON A.TripNumber = B.TripNumber " +
                "WHERE A.StartLocationName = \"" + inStartLocationName + "\" AND A.DestinationName = \"" + inDestinationName + "\" AND B.Date = \"" + inDate + "\"";
        try {
            ResultSet rs = st.executeQuery(query);

            System.out.println("StartLocationName DestinationName Date ScheduledStartTime ScheduledArrivalTime DriverName BusID");
            while (rs.next()) {
                String StartLocationName = rs.getString("StartLocationName");
                String DestinationName = rs.getString("DesinationName");
                String Date = rs.getDate("Date").toString();
                String ScheduledStartTime = rs.getString("ScheduledStartTime");
                String ScheduledArrivalTime = rs.getString("ScheduledArrivalTime");
                String DriverName = rs.getString("DriverName");
                int BusID = rs.getInt("BusID");

                System.out.println(StartLocationName + " " + DestinationName + " " + Date + " " + ScheduledStartTime + " " + ScheduledArrivalTime + " " + DriverName + " " + BusID);
            }
        } catch (Exception e){
            System.out.println("Problems :(");
        }
    }

    public static void numTwo(Connection con, Statement st){
        System.out.println("\nNumber 2");
        int inTripNumber = 0;
        String inDate = "";
        String inScheduledStartTime = "";
        String query = "DELETE FROM tripoffering WHERE TripNumber = \""+ inTripNumber +"\" AND Date = \""+ inDate +"\" AND ScheduledStartTime = \" "+ inScheduledStartTime + "\"";
        try{
            st.executeQuery(query);
            System.out.println("Deleted :)");
        }catch (Exception e){
            System.out.println("Can't remove that :(");
        }

        inTripNumber = 0;
        inDate = "";
        inScheduledStartTime = "";
        String inScheduledArrivalTime = "";
        String inDriverName = "";
        int inBusID = 0;
        query = "INSERT INTO tripoffering VALUE (\""+ inTripNumber +"\", \""+ inDate +"\", \""+ inScheduledStartTime +"\", \""+ inScheduledArrivalTime +"\", \""+ inDriverName +"\", \""+ inBusID +"\")";
        try{
            st.executeQuery(query);
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
            ResultSet rs = st.executeQuery(query);
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
            st.executeQuery(query);
            System.out.println("Updated :)");
        }catch (Exception e) {
            System.out.println("Can't update that :(");
        }
    }

    public static void numThree(Connection con, Statement st){
        System.out.println("\nNumber 3");
        int inTripNumber = 0;
        String query = "SELECT * FROM tripstopinfo WHERE TripNumber = "+ inTripNumber +" ORDER BY SequenceNumber ASC";
        try{
            ResultSet rs = st.executeQuery(query);
            System.out.println("TripNumber StopNumber SequenceNumber DrivingTime");
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
    }

    public static void numFour(Connection con, Statement st){
        System.out.println("\nNumber 4");
        String inDriverName = "Dave";
        String inDate = "2022-05-10";
        String query = "SELECT * FROM tripoffering WHERE DriverName = \""+ inDriverName +"\" AND WEEK(Date) = Week(\""+ inDate +"\")";

        try{
            ResultSet rs = st.executeQuery(query);
            System.out.println("TripNumber Date ScheduledStartTime ScheduledArrivalTime DriverName BusID");
            while (rs.next()){
                int TripNumber = rs.getInt("TripNumber");
                String Date = rs.getDate("Date").toString();
                String ScheduledStartTime = rs.getString("ScheduledStartTime");
                String ScheduledArrivalTime = rs.getString("ScheduledArrivalTime");
                String DriverName = rs.getString("DriverName");
                int BusID = rs.getInt("BusID");
                System.out.println(TripNumber + " " + Date + " " + ScheduledStartTime + " " + ScheduledArrivalTime + " " + DriverName + " " + BusID);

            }
        }catch (Exception e){
            System.out.println("Can't show that :(");
        }



    }

    public static void numFive(Connection con, Statement st){
        System.out.println("\nNumber 5");

        String inDriverName = "";
        String inDriverTelephoneNumber = "";
        String query = "INSERT INTO driver VALUE (\""+ inDriverName +"\", \""+ inDriverTelephoneNumber +"\")";
        try {
            st.executeQuery(query);
            System.out.println("Added :)");
        }catch (Exception e){
            System.out.println("Can't add that :(");
        }

    }

    public static void numSix(Connection con, Statement st){
        System.out.println("\nNumber 6");
        int inBusID = 0;
        String inModel = "";
        int inYear = 0;
        String query = "INSERT INTO bus VALUE ( "+ inBusID +", \""+ inModel +"\", "+ inYear +")";

        try{
            st.executeQuery(query);
            System.out.println("Added :)");
        }catch (Exception e){
            System.out.println("Can't add that :(");
        }
    }

    public static void numSeven(Connection con, Statement st){
        System.out.println("\nNumber 7");

        int inBusID = 0;
        String inModel = "";
        int inYear = 0;
        String query = "DELETE FROM bus WHERE BusID = "+ inBusID +" AND Model = \""+ inModel +"\" AND Year = "+ inYear;

        try {
            st.executeQuery(query);
            System.out.println("Deleted :)");
        }catch (Exception e){
            System.out.println("Can't remove that :(");
        }
    }

    public static void numEight(Connection con, Statement st){
        System.out.println("\nNumber 8");
    }
}
