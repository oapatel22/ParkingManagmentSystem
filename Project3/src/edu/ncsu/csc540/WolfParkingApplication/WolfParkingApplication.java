package edu.ncsu.csc540.WolfParkingApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WolfParkingApplication {

    static final String       jdbcURL    = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/oapatel2";

    private static Connection connection = null;
    private static Statement  statement  = null;
    private static ResultSet  result     = null;

    public static void main ( final String[] args ) {

        System.out.println( "Welcome to the WolfParking Managment System!" );

        initialize();
        close();
    }

    // Example for how to create functions for SQL Queries
    public static void insertDriver ( final int driverID, final String status, final String name ) {
        try {
            // Inserting data into Driver table
            statement.executeUpdate( "INSERT INTO Driver (DriverID, Name, status) VALUES" + "(" + driverID + "," + name
                    + "," + status + ")" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    private static void initialize () {
        try {
            connectToDatabase();

            // Creating Security table
            statement.executeUpdate(
                    "CREATE TABLE Security (" + "    SecurityID INTEGER," + "    PRIMARY KEY (SecurityID)" + ");" );

            // Creating Driver table
            statement.executeUpdate(
                    "CREATE TABLE Driver (" + "    DriverID VARCHAR(20) NOT NULL," + "    Name VARCHAR(36) NOT NULL,"
                            + "    Status VARCHAR(1) NOT NULL," + "    PRIMARY KEY (DriverID)" + ");" );

            // Creating ParkingLot table
            statement.executeUpdate( "CREATE TABLE ParkingLot (" + "    LotName VARCHAR(128) NOT NULL,"
                    + "    Address VARCHAR(128) NOT NULL," + "    PRIMARY KEY (LotName)" + ");" );

            // Creating Vehicle table
            statement.executeUpdate( "CREATE TABLE Vehicle (" + "    LicenseNum VARCHAR(128) NOT NULL,"
                    + "    Year VARCHAR(4)," + "    Model VARCHAR(20)," + "    Color VARCHAR(20),"
                    + "    Manf VARCHAR(20)," + "    PRIMARY KEY (LicenseNum)" + ");" );

            // Creating Zone table
            statement.executeUpdate( "CREATE TABLE Zone (" + "    ZoneID VARCHAR(2) NOT NULL,"
                    + "    LotName VARCHAR(128) NOT NULL," + "    PRIMARY KEY (ZoneID, LotName),"
                    + "    FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" + ");" );

            // Creating Space table
            statement.executeUpdate( "CREATE TABLE Space (" + "    SpaceNumber INTEGER NOT NULL,"
                    + "    LotName VARCHAR(128) NOT NULL," + "    SpaceType VARCHAR(20) NOT NULL,"
                    + "    AvailabilityStatus VARCHAR(20) NOT NULL," + "    PRIMARY KEY (SpaceNumber, LotName),"
                    + "    FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" + ");" );

            // Creating Permit table
            statement.executeUpdate( "CREATE TABLE Permit (" + "    PermitID INTEGER NOT NULL,"
                    + "    DriverID VARCHAR(20) NOT NULL,    LicenseNum VARCHAR(20) NOT NULL,  ZoneID VARCHAR(2) NOT NULL, LotName VARCHAR(20),"
                    + "    StartDate DATE NOT NULL," + "    ExpDate DATE NOT NULL," + "    ExpTime TIME NOT NULL,"
                    + "    SpaceType VARCHAR(20) NOT NULL," + "    PermitType VARCHAR(20) NOT NULL,"
                    + "    PRIMARY KEY (PermitID),"
                    + "    FOREIGN KEY(DriverID) REFERENCES Driver (DriverID) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "    FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "    FOREIGN KEY(ZoneID) REFERENCES Zone (ZoneID) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "    FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ");" );

            // Creating Citation table
            statement.executeUpdate( "CREATE TABLE Citation ("
                    + "CitationNumber INTEGER NOT NULL, LotName VARCHAR(128) NOT NULL, LicenseNum VARCHAR(128) NOT NULL,"
                    + "CitationDate DATE NOT NULL, Fee DOUBLE,"
                    + "PaymentStatus VARCHAR(32) NOT NULL, CitationTime TIME NOT NULL,"
                    + "Category VARCHAR(128) NOT NULL, PRIMARY KEY(CitationNumber),"
                    + "FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE,"
                    + "FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE" + ");" );

            // Creating Maintain table
            statement.executeUpdate( "CREATE TABLE Maintain (" + "    CitationNumber INTEGER NOT NULL,"
                    + "    SecurityID INTEGER NOT NULL," + "    PRIMARY KEY (CitationNumber, SecurityID),"
                    + "    FOREIGN KEY(CitationNumber) REFERENCES Citation (CitationNumber) ON UPDATE CASCADE,"
                    + "    FOREIGN KEY(SecurityID) REFERENCES Security (SecurityID) ON UPDATE CASCADE" + ");" );

        }
        catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    private static void connectToDatabase () throws ClassNotFoundException, SQLException {
        Class.forName( "org.mariadb.jdbc.Driver" );

        String user = "oapatel2";
        String password = "200404428";

        connection = DriverManager.getConnection( jdbcURL, user, password );
        statement = connection.createStatement();

    }

    private static void close () {
        if ( connection != null ) {
            try {
                connection.close();
            }
            catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        if ( statement != null ) {
            try {
                statement.close();
            }
            catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        if ( result != null ) {
            try {
                result.close();
            }
            catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

}
