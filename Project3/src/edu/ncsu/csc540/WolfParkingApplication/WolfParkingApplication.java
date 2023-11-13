package edu.ncsu.csc540.WolfParkingApplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class WolfParkingApplication {

    static final String       jdbcURL    = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/stithi";

    private static Connection connection = null;
    private static Statement  statement  = null;
    private static ResultSet  result     = null;

    public static void main ( final String[] args ) {

        Scanner scnr = new Scanner( System.in );
        System.out.println( "Welcome to the WolfParking Managment System!" );
        System.out.println( "1) Information processing" );
        System.out.println( "2) Maintaining permits and vehicle information for each driver" );
        System.out.println( "3) Generating and maintaining citations" );
        System.out.println( "4) Reports" );
        System.out.print( "Select the major task you want to perform: " );
        String majorTask = scnr.next();
        switch ( majorTask ) {
            case "1":
                System.out.println( "You selected 1" );
                break;
            case "2":
                System.out.println( "You selected 2" );
                break;
            case "3":
                System.out.println( "You selected 3" );
                break;
            case "4":
                System.out.println( "1) Citation Report By Time" );
                System.out.println( "2) Get Zones" );
                System.out.println( "3) Get Current Violations" );
                System.out.println( "4) Get Employee Zone" );
                System.out.println( "5) Get Permit Information" );
                System.out.println( "6) Get Available Spaces" );
                System.out.print( "Select the operation you want to perform: " );
                String operation = scnr.next();
                switch ( operation ) {
                    case "1":
                        System.out.println( "You selected 1" );
                        break;
                    case "2":
                        System.out.println( "You selected 2" );
                        break;
                    case "3":
                        getCurrentViolation();
                        break;
                    case "4":
                        System.out.println( "You selected 4" );
                        break;
                    case "5":
                        System.out.println( "You selected 5" );
                        break;
                    case "6":
                        System.out.println( "You selected 6" );
                        break;
                }
                break;
        }

        initialize();

        preloadDemoData();

        // enterDriver("jbfjd", "S", "Dylan");
        // getDriverInfo("jbfjd");
        // updateDriverInformation("jbfjd", "V", "Dylan");
        // deleteDriver("jbfjd");
        // enterLot("LotName 1", "address 1");
        // enterVehicle("LicenseNum 1", "2003", "fjbgj", "Black", "jjjjj");
        // enterPermitInfo (1, "spaceType", "2023-05-12",
        // "expDate", "jbfjd", "permitType", "expTime",
        // "licenseNum", "zoneID", "LotName 1" );

        // stithi starts : tasks and operations 3

        // enterCitation( date, 10, "Unpaid", time, 1, "Invalid Permit",
        // "LotName 1",
        // "LicenseNum 1" );
        // updateCitation( 1, "Expired Permit", 50 );
        // enterCitation( date, 10, "Unpaid", time, 2, "Invalid Permit",
        // "LotName 1",
        // "LicenseNum 1" );
        // deleteCitation( 2 );
        // NEED TO CHECK // FIX
        // checkParkingViolation( "LotName 1", "LicenseNum 1", date, time );
        // payCitation( 1 );
        // requestAppeal( 1 );
        // stithi ends : tasks and operations 3

        // enterZone ("zone 1", "LotName 1");
        // getZones("LotName 1");
        // getCurrentViolation ();

        // Task 4
        /*
         * getCitationReportByTime("2023-10-02", "2024-10-01");
         * getZones("Partners Way Deck"); getCurrentViolation();
         * getEmployeeZone(); getPermitInformation("122765234");
         * getAvailableSpaces("Poulton Lot");
         */
        scnr.close();
        close();
    }

    // INFORMATION PROCESSING:

    /**
     * Creates a new entry in the driver table with the given driver
     * information.
     *
     * @param driverID
     *            the driver's ID
     * @param status
     *            the driver's status
     * @param name
     *            the name of the driver
     */
    public static void enterDriver ( final String driverID, final String status, final String name ) {
        try {
            // Inserting data into Driver table
            statement.executeUpdate( "INSERT INTO Driver (DriverID, Name, Status) VALUES" + "('" + driverID + "','"
                    + name + "','" + status + "');" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Retrieves a driver entry with the matching ID from the parameter driverID
     * and updates the status and/or name attributes for the matching entry.
     *
     * @param driverID
     *            the ID of the driver entry to be updated
     * @param status
     *            the updated status of the driver entry to be updated
     * @param name
     *            the updated name of the driver entry to be updated
     */
    public static void updateDriverInformation ( final String driverID, final String status, final String name ) {
        if ( status.length() != 0 || status != null ) {
            try {
                // Update driver status with the matching driverID
                statement.executeUpdate(
                        "UPDATE Driver SET Status='" + status + "' " + "WHERE DriverID='" + driverID + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

        if ( name.length() != 0 || name != null ) {
            try {
                // Update driver name with the matching driverID
                statement.executeUpdate(
                        "UPDATE Driver SET Name='" + name + "' " + "WHERE DriverID='" + driverID + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

    }

    /**
     * Deletes a driver entry with the matching ID from the parameter driverID
     *
     * @param driverID
     *            the ID of the driver entry to be deleted
     */
    public static void deleteDriver ( final String driverID ) {
        try {
            // Delete driver entry with the matching driverID
            statement.executeUpdate( "DELETE FROM Driver WHERE DriverID='" + driverID + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Creates a new entry for the ParkingLot table with the given Parking Lot
     * information.
     *
     * @param lotName
     *            the name of the lot to be created
     * @param address
     *            the address of the lot to be created
     */
    public static void enterLot ( final String lotName, final String address ) {
        try {
            // Inserting data into the ParkingLot table
            statement.executeUpdate(
                    "INSERT INTO ParkingLot (LotName, Address) VALUES ('" + lotName + "','" + address + "');" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Updates an existing entry in the ParkingLot entry with the matching
     * Parking Lot name.
     *
     * @param lotName
     *            the name of the entry to be updated
     * @param address
     *            the new address of the parking lot
     */
    public static void updateLotInformation ( final String lotName, final String address ) {
        try {
            // Updating ParkingLot entry with the matching lot name.
            statement.executeUpdate(
                    "UPDATE ParkingLot SET Address='" + address + "' WHERE LotName = '" + lotName + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Deletes a ParkingLot entry with the matching lot name
     *
     * @param lotName
     *            the name of the Parking Lot to be deleted
     */
    public static void deleteLot ( final String lotName ) {
        try {
            // Deletes ParkingLot entry with the matching lot name.
            statement.executeUpdate( "DELETE FROM ParkingLot WHERE LotName= '" + lotName + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

    }

    /**
     * Creates a new Zone entry with the given zone information
     *
     * @param zoneID
     *            the ID of the zone to be created
     * @param lotName
     *            the name of the Parking Lot that the new zone will exist in
     */
    public static void enterZone ( final String zoneID, final String lotName ) {
        try {
            // Inserting into Zone table
            statement.executeUpdate( "INSERT INTO Zone(ZoneID, LotName) VALUES ('" + zoneID + "','" + lotName + "');" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

    }

    /**
     * Updates an existing zone entry with the given parking lot name
     *
     * @param zoneID
     *            the ID of the zone to be updated
     * @param newLotName
     *            the new parking lot where the existing zone will exist in
     * @param oldLotName
     *            the old parking lot where the existing zone exists in
     */
    public static void updateZone ( final String zoneID, final String newLotName, final String oldLotName ) {
        try {
            // Updating Zone entry with existing ID
            statement.executeUpdate( "UPDATE Zone SET LotName='" + newLotName + "' WHERE ZoneID = '" + zoneID
                    + "' AND LotName ='" + oldLotName + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

    }

    /**
     * Deletes an existing Zone entry with the matching zoneID
     *
     * @param zoneID
     *            the ID of the zone entry to be deleted
     * @param lotName
     *            the name of the lot the Zone exists in
     */
    public static void deleteZone ( final String zoneID, final String lotName ) {
        try {
            // Deletes Zone entry with existing ID
            statement.executeUpdate( "DELETE FROM Zone WHERE ZoneID='" + zoneID + "' AND LotName ='" + lotName + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

    }

    /**
     * Creates a new Space entry with the given space information.
     *
     * @param spaceNumber
     *            the number of the space to be created
     * @param lotName
     *            the name of the Parking Lot where the new space will exist
     * @param spaceType
     *            the type of the space to be created
     * @param availabilityStatus
     *            the status of the space to be created
     */
    public static void enterSpace ( final int spaceNumber, final String lotName, final String spaceType,
            final String availabilityStatus ) {
        try {
            // Insert entry into Space table
            statement.executeUpdate( "INSERT INTO Space (SpaceNumber, LotName, SpaceType, AvailabilityStatus) VALUES ('"
                    + spaceNumber + "','" + lotName + "','" + spaceType + "','" + availabilityStatus + "');" );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Updates an existing Space with the given spaceNumber and lotName to the
     * given parameters
     *
     * @param spaceNumber
     *            the number of Space to be updated
     * @param lotName
     *            the name of the Lot that the Space to be updated exists in
     * @param spaceType
     *            the type of the Space to be updated
     * @param availabilityStatus
     *            the availability of the Space to be updated
     */
    public static void updateSpace ( final int spaceNumber, final String lotName, final String spaceType,
            final String availabilityStatus ) {

        if ( spaceType.length() != 0 || spaceType != null ) {
            try {
                // Updates spaceType with matching spaceNumber and lotName
                statement.executeUpdate( "UPDATE Space SET SpaceType='" + spaceType + "' " + "WHERE SpaceNumber='"
                        + spaceNumber + "' AND WHERE LotName='" + lotName + "';" );

            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

        if ( availabilityStatus.length() != 0 || availabilityStatus != null ) {
            try {
                // Updates availabilityStatus with matching spaceNumber and
                // lotName
                statement.executeUpdate( "UPDATE Space SET AvailabilityStatus='" + availabilityStatus + "' "
                        + "WHERE SpaceNumber='" + spaceNumber + "' AND WHERE LotName='" + lotName + "';" );

            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

    }

    /**
     * Deletes the Space entry with the matching space number.
     *
     * @param spaceNumber
     *            the number of the Space to be deleted
     */
    public static void deleteSpace ( final int spaceNumber ) {

        try {
            // Delete entry from Space table with matching space number
            statement.executeUpdate( "DELETE FROM Space WHERE SpaceNumber ='" + spaceNumber + "';" );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Assigns an existing Zone existing within a Parking Lot to another Parking
     * Lot
     *
     *
     * @param newLotName
     *            the name of the new lot
     * @param ZoneID
     *            the ID of the Zone to be assigned
     * @param oldLotName
     *            the name of the old lot
     */
    public static void assignZoneToLot ( final String newLotName, final String ZoneID, final String oldLotName ) {
        try {
            // Assign an existing Zone from one lot to another
            statement.executeUpdate( "UPDATE Zone SET LotName='" + newLotName + "' WHERE LotName ='" + oldLotName
                    + "' AND ZoneID ='" + ZoneID + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Assigns an existing Space existing within a Parking Lot to another
     * Parking Lot
     *
     *
     * @param newLotName
     *            the name of the new lot
     * @param spaceNumber
     *            the number of the Space to be assigned
     * @param oldLotName
     *            the name of the old lot
     */
    public static void assignSpaceToLot ( final String newLotName, final int spaceNumber, final String oldLotName ) {
        try {
            // Assign an existing Space from one lot to another
            statement.executeUpdate( "UPDATE Space SET LotName='" + newLotName + "' WHERE LotName ='" + oldLotName
                    + "' AND SpaceNumber =" + spaceNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Assigns an existing Space a new Type
     *
     * @param lotName
     *            the name of the lot where the Space exists
     * @param spaceNumber
     *            the number of the Space to be updated
     * @param spaceType
     *            the Type of space to be updated to
     */
    public static void assignTypeToSpace ( final String lotName, final int spaceNumber, final String spaceType ) {
        try {
            // Assign an Type to an existing Space
            statement.executeUpdate( "UPDATE Space SET SpaceType='" + spaceType + "' WHERE LotName ='" + lotName
                    + "' AND SpaceNumber =" + spaceNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Updates the payment status of an existing citation.
     *
     * @param paymentStatus
     *            the payment status
     * @param citationNumber
     *            the number of the citation to be updated
     *
     */
    public static void updatePaymentStatus ( final String paymentStatus, final int citationNumber ) {
        try {
            // Update payment status on an exisitng Citation
            statement.executeUpdate( "UPDATE Citation SET PaymentStatus='" + paymentStatus + "' WHERE CitationNumber ='"
                    + citationNumber + "';" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    // Task and operations: Maintaining permits and vehicle information for each
    // driver

    /**
     * Returns the name and status of the driver with the specified DriverID.
     *
     * @param driverID
     *            the ID of the driver to return
     */
    public static void getDriverInfo ( final String driverID ) {
        try {
            ResultSet rs = statement
                    .executeQuery( "SELECT Name, Status FROM Driver WHERE DriverID='" + driverID + "';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "Name" ) );
                sb.append( " " );
                sb.append( rs.getString( "Status" ) );
            }
            System.out.println( sb.toString() );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Adds a permit with the specified parameters, including the permit ID, the
     * type of the space, the lot name, and the zone ID. In addition, the driver
     * and vehicle information and the start and expiration date is specified.
     *
     * @param permitID
     *            ID of the permit to add
     * @param spaceType
     *            type of the space for the permit
     * @param startDate
     *            start date of the permit
     * @param expDate
     *            expiration date of the permit
     * @param driverID
     *            ID of the driver of the permit
     * @param permitType
     *            type of the permit
     * @param expTime
     *            expiration time of the permit
     * @param licenseNum
     *            license number of the vehicle for the permit
     * @param zoneID
     *            zone ID of the permit
     * @param lotName
     *            lot name of the permit
     *
     */
    public static void enterPermitInfo ( final String permitID, final String permitType, final String zoneID,
            final String lotName, final String driverID, final String licenseNum, final String spaceType,
            final String startDate, final String expDate, final String expTime ) {

        try {
            statement.executeUpdate(
                    "INSERT INTO Permit (PermitID, SpaceType, StartDate, ExpDate, DriverID, PermitType, ExpTime, LicenseNum, ZoneID, LotName) VALUES ('"
                            + permitID + "','" + spaceType + "','" + startDate + "','" + expDate + "','" + driverID
                            + "','" + permitType + "','" + expTime + "','" + licenseNum + "','" + zoneID + "','"
                            + lotName + "');" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

    }

    // CAN YOU UPDATE PRIMARY KEYS?
    /**
     * Updates an existing permit with the permit id, space type, start date,
     * expiration date, driver id, permit type, expiration time, license number,
     * zone id, or the lot name.
     *
     * @param permitID
     *            ID of the permit
     * @param spaceType
     *            type of the space for the permit
     * @param startDate
     *            start date of the permit
     * @param expDate
     *            expiration date of the permit
     * @param driverID
     *            ID of the driver of the permit
     * @param permitType
     *            type of the permit
     * @param expTime
     *            expiration time of the permit
     * @param licenseNum
     *            license number of the vehicle for the permit
     * @param zoneID
     *            zone ID of the permit
     * @param lotName
     *            lot name of the permit
     */
    public static void updatePermit ( final String permitID, final String spaceType, final String startDate,
            final String expDate, final String driverID, final String permitType, final String expTime,
            final String licenseNum, final String zoneID, final String lotName ) {

        if ( spaceType.length() != 0 || spaceType != null ) {

            try {
                statement.executeUpdate(
                        "UPDATE Permit SET SpaceType='" + spaceType + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }
        if ( startDate.length() != 0 || startDate != null ) {

            try {
                statement.executeUpdate(
                        "UPDATE Permit SET StartDate='" + startDate + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( expDate.length() != 0 || expDate != null ) {

            try {
                statement
                        .executeUpdate( "UPDATE Permit SET ExpDate='" + expDate + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( driverID.length() != 0 || driverID != null )

        {

            try {
                statement.executeUpdate(
                        "UPDATE Permit SET DriverID='" + driverID + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( permitType.length() != 0 || permitType != null ) {

            try {
                statement.executeUpdate(
                        "UPDATE Permit SET PermitType='" + permitType + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( expTime.length() != 0 || expTime != null ) {

            try {
                statement
                        .executeUpdate( "UPDATE Permit SET ExpTime='" + expTime + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( licenseNum.length() != 0 || licenseNum != null ) {

            try {
                statement.executeUpdate(
                        "UPDATE Permit SET LicenseNum='" + licenseNum + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( zoneID.length() != 0 || zoneID != null ) {

            try {
                statement.executeUpdate( "UPDATE Permit SET ZoneID='" + zoneID + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

        if ( lotName.length() != 0 || lotName != null ) {

            try {
                statement
                        .executeUpdate( "UPDATE Permit SET LotName='" + lotName + "'WHERE PermitID=" + permitID + ";" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }

        }

    }

    /**
     * Deletes a permit with the given permit ID.
     *
     * @param permitID
     *            id of permit to delete
     */
    public static void deletePermit ( final String permitID ) {
        try {
            // Delete entry from Space table with matching space number
            statement.executeUpdate( "DELETE FROM Permit WHERE PermitId ='" + permitID + "';" );
        }
        catch ( SQLException e ) {

            System.out.println( "Error message" );
        }
    }

    /**
     * Assigns the specified permit to the given driver ID.
     *
     * @param permitID
     */
    public static void assignPermit ( final String driverID, final String permitID ) {
        try {
            // Delete entry from Space table with matching space number
            statement.executeUpdate(
                    "UPDATE PERMIT SET DriverID = '" + driverID + "'WHERE PermitID = '" + permitID + "';" );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Returns the name and status of permit the with the specified permitID.
     *
     * @param permitID
     *            the ID of the permit to return
     */
    public static void getPermitInfo ( final String permitID ) {
        // not sure how to do this
        // have to return information about the permit
    }

    /**
     * Enters a vehicle into the database with a specified license number, year,
     * model, color, and manufacturer.
     *
     * @param licenseNum
     *            license number of vehicle
     * @param year
     *            year of the vehicle
     * @param model
     *            model of the vehicle
     * @param color
     *            color of the vehicle
     * @param manf
     *            manf of the vehicle
     */
    public static void enterVehicle ( final String licenseNum, final String year, final String model,
            final String color, final String manf ) {
        try {
            statement.executeUpdate( "INSERT INTO Vehicle (LicenseNum, Year, Model, Color, Manf) VALUES ('" + licenseNum
                    + "', '" + year + "', '" + model + "', '" + color + "', '" + manf + "');" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    public static void updateVehicle ( final String licenseNum, final String year, final String model,
            final String color, final String manf ) {

        if ( year.length() != 0 || year != null ) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Year='" + year + "' WHERE LicenseNum='" + licenseNum + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

        if ( model.length() != 0 || model != null ) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Model='" + model + "' WHERE LicenseNum='" + licenseNum + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

        if ( color.length() != 0 || color != null ) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Color='" + color + "' WHERE LicenseNum='" + licenseNum + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

        if ( manf.length() != 0 || manf != null ) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Manf='" + manf + "' WHERE LicenseNum='" + licenseNum + "';" );
            }
            catch ( SQLException e ) {
                System.out.println( "Error message" );
            }
        }

    }

    /**
     * Deletes a vehicle with the given license plate number
     *
     * @param licenseNum
     *            license plate number of the vehicle to delete
     */
    public static void deleteVehicle ( final String licenseNum ) {
        try {
            statement.executeUpdate( "DELETE FROM Permit WHERE LicenseNum ='" + licenseNum + "';" );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     *
     * @param licenseNum
     */
    public static void assignVehicle ( final String licenseNum, final int permitID ) {
        try {
            statement.executeUpdate( "" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    // Task and operations 3: Generating and maintaining citations
    /**
     * Creates a new Citation entry with the given citation information.
     *
     * @param CitationDate
     *            the date when the citation is created
     * @param Fee
     *            fee ($25 for category "Invalid Permit," $30 for category
     *            "Expired Permit," $40 for category "No Permit", but handicap
     *            users will receive a 50% discount on all citation fees)
     * @param PaymentStatus
     *            the status (if the driver has paid for the citation) of the
     *            citation to be created values can be {paid, unpaid}, which can
     *            be updated by a payment procedure
     * @param CitationTime
     *            the date when the citation is created
     * @param CitationNumber
     *            the number of the Citation to be created
     * @param Category
     *            the category of the Citation to be created
     * @param LotName
     *            the name of the parking lot where the vehicle relevant to the
     *            citation exists
     * @param LicenseNum
     *            the license number of the vehicle relevant to the citation
     */
    public static void enterCitation ( final String CitationDate, final double Fee, final String PaymentStatus,
            final String CitationTime, final String CitationNumber, final String Category, final String LotName,
            final String LicenseNum ) {
        try {
            // Insert entry into Citation table
            statement.executeUpdate(
                    "INSERT INTO Citation (CitationDate, Fee, PaymentStatus, CitationTime, CitationNumber, Category, LotName, LicenseNum) VALUES ('"
                            + CitationDate + "'," + Fee + ",'" + PaymentStatus + "','" + CitationTime + "','"
                            + CitationNumber + "','" + Category + "','" + LotName + "','" + LicenseNum + "');" );

        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Updates an existing Citation entry with the given citation category and
     * fee
     *
     * @param CitationNumber
     *            the ID of the citation to be updated
     * @param Category
     *            the new category for the updated citation entry
     * @param Fee
     *            the new fee for the updated citation entry
     */
    public static void updateCitation ( final int CitationNumber, final String Category, final double Fee ) {
        try {
            // Updating Citation entry with existing CitationNumber
            statement.executeUpdate( "Update Citation SET Fee = " + Fee + ", Category = '" + Category
                    + "' WHERE CitationNumber = " + CitationNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Deletes the Citation entry with the matching CitationNumber.
     *
     * @param CitationNumber
     *            the number of the Citation to be deleted
     */
    public static void deleteCitation ( final int CitationNumber ) {
        try {
            // Delete entry from Citation table with matching CitationNumber
            statement.executeUpdate( "DELETE FROM Citation WHERE CitationNumber =" + CitationNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Checks if a vehicle has a valid permit
     *
     * @param LotName
     *            the name of the parking lot where the vehicle relevant to the
     *            citation exists
     * @param LicenseNum
     *            the license number of the vehicle relevant to the citation
     * @param ExpDate
     *            the date when the permit will expire
     * @param ExpTime
     *            the time of the day when the permit will expire
     */
    public static void checkParkingViolation ( final String LotName, final String LicenseNum, final Date ExpDate,
            final Time ExpTime ) {
        try {
            // Get permit id from Permit table with matching LicenseNum
            ResultSet rs = statement.executeQuery(
                    "SELECT PermitID FROM Permit WHERE LicenseNum = '" + LicenseNum + "' AND LotName = '" + LotName
                    // + "' AND ExpDate >= '" + ExpDate + "' AND ExpTime >= '" +
                    // ExpTime + "';" );
                            + "';" );

            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "PermitID" ) );
                // sb.append(" ");
                // sb.append(rs.getString("Status"));
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
            // return ;
        }

    }

    /**
     * Updates Fee as 0 and Payment status as "Paid" in Citation table for a
     * given CitationNumber
     *
     * @param CitationNumber
     *            the number of the Citation to be created
     */
    public static void payCitation ( final int CitationNumber ) {
        try {
            // Updating Citation entry with existing CitationNumber
            statement.executeUpdate( "Update Citation SET Fee =  0, PaymentStatus = 'Paid'" + " WHERE CitationNumber = "
                    + CitationNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Updates Fee as 0 and Payment status as "In Appeal" in Citation table for
     * a given CitationNumber
     *
     * @param CitationNumber
     *            the number of the Citation to be created
     */
    public static void requestAppeal ( final int CitationNumber ) {
        try {
            // Updating Citation entry with existing CitationNumber
            statement.executeUpdate( "Update Citation SET Fee =  0, PaymentStatus = 'In Appeal'"
                    + " WHERE CitationNumber = " + CitationNumber + ";" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    // Task and operations 4: Reports

    /**
     * Returns a table titled TotalCitations with the name of the parking lot
     * and the associated number of citation for each lot within a given time
     * frame
     *
     * @param StartDate
     *            the earliest the citation was given
     * @param EndDate
     *            the latest the citation was given
     */
    public static void getCitationReportByTime ( final String StartDate, final String EndDate ) {
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT p.LotName, COUNT(c.CitationNumber) AS TotalCitations FROM ParkingLot p LEFT JOIN Citation c ON p.LotName = c.LotName AND c.CitationDate BETWEEN '"
                            + StartDate + "' AND '" + EndDate + "' GROUP BY p.LotName" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "LotName" ) );
                sb.append( " " );
                sb.append( rs.getString( "TotalCitations" ) );
                sb.append( "\n" );
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Return a list of zones in a given Parking Lot
     *
     * @param LotName
     *            the name of the parking lot
     */
    public static void getZones ( final String LotName ) {
        try {
            ResultSet rs = statement.executeQuery( "SELECT * FROM Zone WHERE LotName = '" + LotName + "';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "ZoneID" ) );
                sb.append( " " );
                sb.append( rs.getString( "LotName" ) );
                sb.append( "\n" );
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Gets the number of people who have not paid their citation
     */
    public static void getCurrentViolation () {
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT COUNT(DISTINCT LicenseNum) AS Violations FROM Citation WHERE PaymentStatus = 'Unpaid';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "Violations" ) );
                sb.append( "\n" );
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Gets the amount of employees
     */
    public static void getEmployeeZone () {
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT COUNT(*) AS 'Employee Zones' From Permit Join Driver On Permit.DriverID = Driver.DriverID WHERE Permit.ZoneID = 'D' AND Driver.Status = 'E';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "Employee Zones" ) );
                sb.append( "\n" );
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Gets the permit associated with a particular driver
     *
     * @param DriverId
     *            the id of the driver to get their permit info
     */
    public static void getPermitInformation ( final String DriverId ) {
        try {
            ResultSet rs = statement.executeQuery( "SELECT * From Permit WHERE Permit.DriverId = '" + DriverId + "';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "PermitID" ) );
                sb.append( " " );
                sb.append( rs.getString( "DriverID" ) );
                sb.append( " " );
                sb.append( rs.getString( "LicenseNum" ) );
                sb.append( " " );
                sb.append( rs.getString( "ZoneID" ) );
                sb.append( " " );
                sb.append( rs.getString( "LotName" ) );
                sb.append( " " );
                sb.append( rs.getString( "StartDate" ) );
                sb.append( " " );
                sb.append( rs.getString( "ExpDate" ) );
                sb.append( " " );
                sb.append( rs.getString( "ExpTime" ) );
                sb.append( " " );
                sb.append( rs.getString( "SpaceType" ) );
                sb.append( " " );
                sb.append( rs.getString( "PermitType" ) );
                sb.append( "\n" );

            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    /**
     * Gets the number of available spaces within a particular Parking Lot
     *
     * @param LotName
     *            the name of the lot to find the number of available spaces for
     */
    public static void getAvailableSpaces ( final String LotName ) {
        try {
            ResultSet rs = statement
                    .executeQuery( "SELECT SpaceNumber From Space WHERE spaceType = 'regular' AND lotName = '" + LotName
                            + "' AND AvailabilityStatus = 'Available';" );
            String s = "";
            StringBuilder sb = new StringBuilder( s );

            while ( rs.next() ) {
                sb.append( rs.getString( "SpaceNumber" ) );
                sb.append( "\n" );
            }
            System.out.println( sb.toString() );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    private static void initialize () {
        try {
            connectToDatabase();

            // // Creating Security table
            // statement.executeUpdate(
            // "CREATE TABLE Security (" + " SecurityID INTEGER," + " PRIMARY
            // KEY
            // (SecurityID)" + ");" );

            // // Creating Driver table
            // statement.executeUpdate(
            // "CREATE TABLE Driver (" + " DriverID VARCHAR(20) NOT NULL," + "
            // Name
            // VARCHAR(36) NOT NULL,"
            // + " Status VARCHAR(1) NOT NULL," + " PRIMARY KEY (DriverID)" +
            // ");" );

            // // Creating ParkingLot table
            // statement.executeUpdate( "CREATE TABLE ParkingLot (" + " LotName
            // VARCHAR(128)
            // NOT NULL,"
            // + " Address VARCHAR(128) NOT NULL," + " PRIMARY KEY (LotName)" +
            // ");" );

            // // Creating Vehicle table
            // statement.executeUpdate( "CREATE TABLE Vehicle (" + " LicenseNum
            // VARCHAR(128)
            // NOT NULL,"
            // + " Year VARCHAR(4)," + " Model VARCHAR(20)," + " Color
            // VARCHAR(20),"
            // + " Manf VARCHAR(20)," + " PRIMARY KEY (LicenseNum)" + ");" );

            // // Creating Zone table
            // statement.executeUpdate( "CREATE TABLE Zone (" + " ZoneID
            // VARCHAR(2) NOT
            // NULL,"
            // + " LotName VARCHAR(128) NOT NULL," + " PRIMARY KEY (ZoneID,
            // LotName),"
            // + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON
            // UPDATE CASCADE" +
            // ");" );

            // // Creating Space table
            // statement.executeUpdate( "CREATE TABLE Space (" + " SpaceNumber
            // INTEGER NOT
            // NULL,"
            // + " LotName VARCHAR(128) NOT NULL," + " SpaceType VARCHAR(20) NOT
            // NULL,"
            // + " AvailabilityStatus VARCHAR(20) NOT NULL," + " PRIMARY KEY
            // (SpaceNumber,
            // LotName),"
            // + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON
            // UPDATE CASCADE" +
            // ");" );

            // // Creating Permit table
            // statement.executeUpdate( "CREATE TABLE Permit (" + " PermitID
            // INTEGER NOT
            // NULL,"
            // + " DriverID VARCHAR(20) NOT NULL, LicenseNum VARCHAR(20) NOT
            // NULL, ZoneID
            // VARCHAR(2) NOT NULL, LotName VARCHAR(20),"
            // + " StartDate DATE NOT NULL," + " ExpDate DATE NOT NULL," + "
            // ExpTime TIME
            // NOT NULL,"
            // + " SpaceType VARCHAR(20) NOT NULL," + " PermitType VARCHAR(20)
            // NOT NULL,"
            // + " PRIMARY KEY (PermitID),"
            // + " FOREIGN KEY(DriverID) REFERENCES Driver (DriverID) ON UPDATE
            // CASCADE ON
            // DELETE CASCADE,"
            // + " FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON
            // UPDATE CASCADE
            // ON DELETE CASCADE,"
            // + " FOREIGN KEY(ZoneID) REFERENCES Zone (ZoneID) ON UPDATE
            // CASCADE ON DELETE
            // CASCADE,"
            // + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON
            // UPDATE CASCADE ON
            // DELETE CASCADE"
            // + ");" );

            // // Creating Citation table
            // statement.executeUpdate( "CREATE TABLE Citation ("
            // + "CitationNumber INTEGER NOT NULL, LotName VARCHAR(128) NOT
            // NULL, LicenseNum
            // VARCHAR(128) NOT NULL,"
            // + "CitationDate DATE NOT NULL, Fee DOUBLE,"
            // + "PaymentStatus VARCHAR(32) NOT NULL, CitationTime TIME NOT
            // NULL,"
            // + "Category VARCHAR(128) NOT NULL, PRIMARY KEY(CitationNumber),"
            // + "FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE
            // CASCADE,"
            // + "FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON
            // UPDATE CASCADE"
            // + ");" );

            // // Creating Maintain table
            // statement.executeUpdate( "CREATE TABLE Maintain (" + "
            // CitationNumber INTEGER
            // NOT NULL,"
            // + " SecurityID INTEGER NOT NULL," + " PRIMARY KEY
            // (CitationNumber,
            // SecurityID),"
            // + " FOREIGN KEY(CitationNumber) REFERENCES Citation
            // (CitationNumber) ON
            // UPDATE CASCADE,"
            // + " FOREIGN KEY(SecurityID) REFERENCES Security (SecurityID) ON
            // UPDATE
            // CASCADE" + ");" );

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

        String user = "stithi";
        String password = "200475434";

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

    private static void preloadDemoData () {
        try {
            statement.executeUpdate( "drop database stithi;" );
            statement.executeUpdate( "create database stithi;" );
            statement.executeUpdate( "use stithi;" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }

        createAllTables();
        populateTables();
    }

    private static void createAllTables () {
        // Creating Security table
        try {
            statement.executeUpdate(
                    "CREATE TABLE Security (" + " SecurityID INTEGER," + " PRIMARY KEY (SecurityID)" + ");" );

            // Creating Driver table
            statement.executeUpdate(
                    "CREATE TABLE Driver (" + " DriverID VARCHAR(20) NOT NULL," + "Name VARCHAR(36) NOT NULL,"
                            + " Status VARCHAR(1) NOT NULL," + " PRIMARY KEY (DriverID)" + ");" );

            // Creating ParkingLot table
            statement.executeUpdate( "CREATE TABLE ParkingLot (" + " LotName VARCHAR(128) NOT NULL,"
                    + " Address VARCHAR(128) NOT NULL," + " PRIMARY KEY (LotName)" + ");" );

            // Creating Vehicle table
            statement.executeUpdate( "CREATE TABLE Vehicle (" + " LicenseNum VARCHAR(128) NOT NULL,"
                    + " Year VARCHAR(4)," + " Model VARCHAR(20)," + " Color VARCHAR(20)," + " Manf VARCHAR(20),"
                    + " PRIMARY KEY (LicenseNum)" + ");" );

            // Creating Zone table
            statement.executeUpdate( "CREATE TABLE Zone (" + " ZoneID VARCHAR(2) NOT NULL,"
                    + " LotName VARCHAR(128) NOT NULL," + " PRIMARY KEY (ZoneID, LotName),"
                    + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" + ");" );

            // Creating Space table
            statement.executeUpdate( "CREATE TABLE Space (" + " SpaceNumber INTEGER NOT NULL,"
                    + " LotName VARCHAR(128) NOT NULL," + " SpaceType VARCHAR(20) NOT NULL,"
                    + " AvailabilityStatus VARCHAR(20) NOT NULL," + " PRIMARY KEY (SpaceNumber, LotName),"
                    + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" + ");" );

            // Creating Permit table
            statement.executeUpdate( "CREATE TABLE Permit (" + " PermitID VARCHAR(20) NOT NULL,"
                    + " DriverID VARCHAR(20) NOT NULL, LicenseNum VARCHAR(20) NOT NULL, ZoneID VARCHAR(2) NOT NULL, LotName VARCHAR(20),"
                    + " StartDate DATE NOT NULL," + " ExpDate DATE NOT NULL," + " ExpTime TIME NOT NULL,"
                    + " SpaceType VARCHAR(20) NOT NULL," + " PermitType VARCHAR(20) NOT NULL,"
                    + " PRIMARY KEY (PermitID),"
                    + " FOREIGN KEY(DriverID) REFERENCES Driver (DriverID) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + " FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + " FOREIGN KEY(ZoneID) REFERENCES Zone (ZoneID) ON UPDATE CASCADE ON DELETE CASCADE"
                    // + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName)
                    // ON UPDATE CASCADE ON
                    // DELETE CASCADE" // CHECK
                    + ");" );

            // Creating Citation table
            statement.executeUpdate( "CREATE TABLE Citation ("
                    + "CitationNumber VARCHAR(32) NOT NULL, LotName VARCHAR(128) NOT NULL, LicenseNum VARCHAR(128) NOT NULL,"
                    + "CitationDate DATE NOT NULL, Fee DOUBLE,"
                    + "PaymentStatus VARCHAR(32) NOT NULL, CitationTime TIME NOT NULL,"
                    + "Category VARCHAR(128) NOT NULL, PRIMARY KEY(CitationNumber),"
                    + "FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE,"
                    + "FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE" + ");" );

            // // Creating Maintain table
            // statement.executeUpdate( "CREATE TABLE Maintain (" + "
            // CitationNumber INTEGER
            // NOT NULL,"
            // + " SecurityID INTEGER NOT NULL," + " PRIMARY KEY
            // (CitationNumber,
            // SecurityID),"
            // + " FOREIGN KEY(CitationNumber) REFERENCES Citation
            // (CitationNumber) ON
            // UPDATE CASCADE,"
            // + " FOREIGN KEY(SecurityID) REFERENCES Security (SecurityID) ON
            // UPDATE
            // CASCADE" + ");" );
        }
        catch ( SQLException e ) {
            System.out.println( "Error message" );
        }
    }

    private static void populateTables () {
        enterDriver( "7729119111", "V", "Sam BankmanFried" );
        enterDriver( "266399121", "E", "John Clay" );
        enterDriver( "366399121", "E", "Julia Hicks" );
        enterDriver( "466399121", "E", "Ivan Garcia" );
        enterDriver( "122765234", "S", "Sachin Tendulkar" );
        enterDriver( "9194789124", "V", "Charles Xavier" );

        enterLot( "Poulton Deck", "1021 Main Campus Dr, Raleigh, NC, 27606" );
        enterLot( "Poulton Lot", "1021 Main Campus Dr, Raleigh, NC, 27606" );
        enterLot( "Partners Way Deck", "851 Partners Way, Raleigh, NC, 27606" );
        enterLot( "Dan Allen Parking Deck", "110 Dan Allen Dr, Raleigh, NC, 27607" );

        enterVehicle( "SBF", "2024", "GT-R-Nismo", "Pearl White TriCoat", "Nissan" );
        enterVehicle( "Clay1", "2023", "Model S", "Ultra Red", "Tesla" );
        enterVehicle( "Hicks1", "2024", "M2 Coupe", "Zandvoort Blue", "BMW" );
        enterVehicle( "Garcia1", "2024", "Continental GT Speed", "Blue Fusion", "Bentley" );
        enterVehicle( "CRICKET", "2024", "Civic SI", "Sonic Gray Pearl", "Honda" );
        enterVehicle( "PROFX", "2024", "Taycan Sport Turismo", "Frozenblue Metallic", "Porsche" );
        // dummy extra data
        enterVehicle( "VAN-9910", null, "Macan GTS", "Papaya Metallic", null );

        // zones
        enterZone( "V", "Poulton Deck" );
        enterZone( "A", "Partners Way Deck" );
        enterZone( "AS", "Dan Allen Parking Deck" );

        // LOT NAME MIGHT NEED TO GO IN HERE
        enterPermitInfo( "VSBF1C", "Commuter", "V", "Poulton Deck", "7729119111", "SBF", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00" );
        enterPermitInfo( "EJC1R", "Residential", "A", "Partners Way Deck", "266399121", "Clay1", "Electric",
                "2010-01-01", "2030-01-01", "06:00:00" );
        enterPermitInfo( "EJH2C", "Commuter", "A", "Partners Way Deck", "366399121", "Hicks1", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00" );
        enterPermitInfo( "EIG3C", "Commuter", "A", "Partners Way Deck", "466399121", "Garcia1", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00" );
        enterPermitInfo( "SST1R", "Residential", "AS", "Dan Allen Parking Deck", "122765234", "CRICKET", "Compact Car",
                "2022-01-01", "2023-09-30", "06:00:00" );
        enterPermitInfo( "VCX1SE", "Special event", "V", "Poulton Deck", "9194789124", "PROFX", "Handicap",
                "2023-01-01", "2023-11-15", "06:00:00" );

        // CITATION
        enterCitation( "2024-01-01", 40, "PAID", "08:00:00", "NP1", "No Permit", "Dan Allen Parking Deck", "VAN-9910" );
        enterCitation( "2023-10-01", 30, "DUE", "08:00:00", "EP1", "Expired Permit", "Poulton Lot", "CRICKET" ); // "Poulton
                                                                                                                 // Lot"
                                                                                                                 // or
                                                                                                                 // "Poulton
                                                                                                                 // Deck"
    }

}
