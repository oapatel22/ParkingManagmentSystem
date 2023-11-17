package edu.ncsu.csc540.WolfParkingApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

/**
 * The `WolfParkingApplication` class represents the main entry point for the
 * WolfParking Management System application.
 * It contains the `main` method that initializes the application, interacts
 * with users, and manages the various tasks
 * related to parking management.
 */

public class WolfParkingApplication {

    // Create a Scanner object to read user input
    static Scanner scanner;

    // Define the JDBC (Java Database Connectivity) URL for connecting to a MariaDB
    // database
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/oapatel2";

    // Initialize database connection objects
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    /**
     * Main method of the application.
     * It initializes the database connection, provides a user interface to perform
     * different tasks related to parking management,
     * and processes user inputs to execute specific actions.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(final String[] args) {

        // Initialize the database connection and load demo data
        initialize();
        preloadDemoData();

        // Initialize a Scanner object to read user input
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the WolfParking Managment System!");

        while (true) {
            // Display major task options to the user
            System.out.println("1) Information processing");
            System.out.println("2) Maintaining permits and vehicle information for each driver");
            System.out.println("3) Generating and maintaining citations");
            System.out.println("4) Reports");
            System.out.print("Select the major task you want to perform (or enter 'q' to exit): ");
            String majorTask = scanner.next();

            // Check if the user wants to exit the program
            if (majorTask.equals("q")) {
                break;
            }
            switch (majorTask) {
                case "1": // 1) Information processing
                    System.out.println("1) Enter Driver Information");
                    System.out.println("2) Update Driver Information");
                    System.out.println("3) Delete Driver Information");
                    System.out.println("4) Enter Lot Information");
                    System.out.println("5) Update Lot Information");
                    System.out.println("6) Delete Parking Lot Information");
                    System.out.println("7) Enter Zone Information");
                    System.out.println("8) Update Zone Information");
                    System.out.println("9) Delete Zone Information");
                    System.out.println("10) Enter Space Information");
                    System.out.println("11) Update Space Information");
                    System.out.println("12) Delete Space Information");
                    System.out.println("13) Assign Zone To Lot");
                    System.out.println("14) Assign Space To Lot");
                    System.out.println("15) Assign Type To Space");
                    System.out.println("16) Update Payment Status");
                    System.out.print("Select the operation you want to perform: ");
                    String operation1 = scanner.next();
                    switch (operation1) {
                        case "1":
                            scanner.nextLine();
                            enterDriverByInput(scanner);
                            break;
                        case "2":
                            scanner.nextLine();
                            updateDriverInformationByInput(scanner);
                            break;
                        case "3":
                            scanner.nextLine();
                            deleteDriverByInput(scanner);
                            break;
                        case "4":
                            scanner.nextLine();
                            enterLotByInput(scanner);
                            break;
                        case "5":
                            scanner.nextLine();
                            updateLotInformationByInput(scanner);
                            break;
                        case "6":
                            scanner.nextLine();
                            deleteLotByInput(scanner);
                            break;
                        case "7":
                            scanner.nextLine();
                            enterZoneByInput(scanner);
                            break;
                        case "8":
                            scanner.nextLine();
                            updateZoneByInput(scanner);
                            break;
                        case "9":
                            scanner.nextLine();
                            deleteZoneByInput(scanner);
                            break;
                        case "10":
                            scanner.nextLine();
                            enterSpaceByInput(scanner);
                            break;
                        case "11":
                            scanner.nextLine();
                            updateSpaceByInput(scanner);
                            break;
                        case "12":
                            scanner.nextLine();
                            deleteSpaceByInput(scanner);
                            break;
                        case "13":
                            scanner.nextLine();
                            assignZoneToLotByInput(scanner);
                            break;
                        case "14":
                            scanner.nextLine();
                            assignSpaceToLotByInput(scanner);
                            break;
                        case "15":
                            scanner.nextLine();
                            assignTypeToSpaceByInput(scanner);
                            break;
                        case "16":
                            scanner.nextLine();
                            updatePaymentStatusByInput(scanner);
                            break;
                    }
                    break;
                case "2":
                    // 2) Maintaining permits and vehicle information for each driver
                    System.out.println("1) Get Driver Info");
                    System.out.println("2) Enter Permit Info");
                    System.out.println("3) Update Permit Info");
                    System.out.println("4) Delete Permit Info");
                    System.out.println("5) Assign Permit Info");
                    System.out.println("6) Enter Vehicle Information");
                    System.out.println("7) Update Vehicle Information");
                    System.out.println("8) Delete Vehicle Information");
                    System.out.print("Select the operation you want to perform: ");
                    String operation2 = scanner.next();
                    switch (operation2) {
                        case "1":
                            scanner.nextLine();
                            getDriverInfoByInput(scanner);
                            break;
                        case "2":
                            scanner.nextLine();
                            enterPermitInfoByInput(scanner);
                            break;
                        case "3":
                            scanner.nextLine();
                            updatePermitByInfo(scanner);
                            break;
                        case "4":
                            scanner.nextLine();
                            deletePermitByInfo(scanner);
                            break;
                        case "5":
                            scanner.nextLine();
                            assignPermitByInfo(scanner);
                            break;
                        case "6":
                            scanner.nextLine();
                            enterVehicleByInfo(scanner);
                            break;
                        case "7":
                            scanner.nextLine();
                            updateVehicleByInfo(scanner);
                            break;
                        case "8":
                            scanner.nextLine();
                            deleteVehicleByInfo(scanner);
                            break;
                    }
                    break;
                case "3": // 3) Generating and maintaining citations
                    System.out.println("1) Enter Citation Information");
                    System.out.println("2) Check Parking Violation");
                    System.out.println("3) Pay Citation");
                    System.out.println("4) Request Appeal");
                    System.out.print("Select the operation you want to perform: ");
                    String operation3 = scanner.next();
                    switch (operation3) {
                        case "1":
                            scanner.nextLine();
                            enterCitationByInput(scanner);
                            break;
                        case "2":
                            scanner.nextLine();
                            checkParkingViolationByInput(scanner);
                            break;
                        case "3":
                            scanner.nextLine();
                            payCitationByInput(scanner);
                            break;
                        case "4":
                            scanner.nextLine();
                            requestAppealByInput(scanner);
                            break;
                    }
                    break;
                case "4": // 4) Reports
                    System.out.println("1) Citation Report By Time");
                    System.out.println("2) Get Zones");
                    System.out.println("3) Get Current Violations");
                    System.out.println("4) Get Employee Zone");
                    System.out.println("5) Get Permit Information");
                    System.out.println("6) Get Available Spaces");
                    System.out.print("Select the operation you want to perform: ");
                    String operation4 = scanner.next();
                    switch (operation4) {
                        case "1":
                            scanner.nextLine();
                            getCitationReportByTime(scanner);
                            break;
                        case "2":
                            scanner.nextLine();
                            getZones(scanner);
                            break;
                        case "3":
                            getCurrentViolations();
                            break;
                        case "4":
                            scanner.nextLine();
                            getEmployeeZone(scanner);
                            break;
                        case "5":
                            scanner.nextLine();
                            getPermitInformation(scanner);
                            break;
                        case "6":
                            scanner.nextLine();
                            getAvailableSpaces(scanner);
                            break;
                    }
                    break;
            }

        }
        // Close the Scanner and database connection when the program exits
        scanner.close();
        close();
    }

    // INFORMATION PROCESSING:

    /**
     * Inserts a new entry into the Driver table with the specified driver
     * information.
     *
     * @param driverID The unique identifier for the driver.
     * @param status   The status of the driver (e.g., active, suspended).
     * @param name     The name of the driver.
     */
    public static void enterDriver(final String driverID, final String status, final String name) {
        try {
            // Execute an SQL statement to insert driver information into the Driver table
            statement.executeUpdate("INSERT INTO Driver (DriverID, Name, Status) VALUES" + "('" + driverID + "','"
                    + name + "','" + status + "');");

        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the insertion process
            System.out.println("Error when entering driver");
        }
    }

    /**
     * Prompts the user to enter driver information and creates a new entry in the
     * driver table.
     * The user is asked to input the driver's ID, status, and name in one line,
     * separated by commas.
     * This information is then used to create a new entry in the driver table in
     * the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void enterDriverByInput(final Scanner scnr) {

        System.out.println("Enter driver information separated by commas (e.g., 'oapatel2', 'S', 'Om Patel'):");

        // Read the user's input as a single line
        String input = scnr.nextLine();
        System.out.println(input);
        // Split the input line into an array of strings using commas as separators
        String[] line = input.split(",");

        // Check if the input contains all required fields (ID, status, and name)
        if (line.length < 3) {
            System.out.println("Invalid input format. Please enter ID, status, and name separated by commas.");
            return;
        }

        // Extract and trim individual components (driverID, status, and name) from the
        // input
        String driverID = line[0].trim();
        String status = line[1].trim();
        String name = line[2].trim();

        // Calling method to insert data into Driver table
        enterDriver(driverID, status, name);

    }

    /**
     * Retrieves a driver entry with the matching ID from the parameter driverID
     * and updates the status and/or name attributes for the matching entry.
     *
     * @param driverID    The ID of the driver entry to be updated.
     * @param newDriverID The updated driver ID (optional). Pass an empty string or
     *                    null if not updating.
     * @param status      The updated status of the driver entry (optional). Pass an
     *                    empty string or null if not updating.
     * @param name        The updated name of the driver entry (optional). Pass an
     *                    empty string or null if not updating.
     */
    public static void updateDriverInformation(final String driverID, final String newDriverID, final String status,
            final String name) {

        // Construct and execute SQL statements to update driver information based on
        // provided parameters

        if (status.length() != 0 && status != null) {
            try {
                // Update driver status with the matching driverID
                statement.executeUpdate(
                        "UPDATE Driver SET Status='" + status + "' " + "WHERE DriverID='" + driverID + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating driver status");
            }
        }

        if (name.length() != 0 && name != null) {
            try {
                // Update driver name with the matching driverID
                statement.executeUpdate(
                        "UPDATE Driver SET Name='" + name + "' " + "WHERE DriverID='" + driverID + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating driver name");
            }
        }

        if (newDriverID.length() != 0 && newDriverID != null) {
            try {
                // Update driver ID with the matching driverID
                statement.executeUpdate(
                        "UPDATE Driver SET DriverID='" + newDriverID + "' " + "WHERE DriverID='" + driverID + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating driver ID");
            }
        }

    }

    /**
     * Updates the information of a driver in the database based on user input.
     * The user is prompted to enter the driver ID, new driver ID, new status, and
     * new name in
     * one line, separated by commas. The status and name can be left blank if
     * no changes are to be made.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void updateDriverInformationByInput(final Scanner scnr) {

        System.out.println(
                "Enter the driver ID, new driver ID, new status, and new name separated by commas (e.g., 'tatran5', 'oapatel2', 'S', 'Om Patel') (leave status or name blank if no change):");
        // Read the user's input as a single line
        String input = scnr.nextLine();
        // Split the input line into an array of strings using commas as separators
        String[] parts = input.split(",");

        // Check if the input contains all required fields (driver ID, new driver ID,
        // status, and name)
        if (parts.length < 4) {
            System.out.println(
                    "Invalid input format. Please enter the driver ID, status, and name separated by commas.");
            return;
        }

        // Extract and trim individual components from the input
        String driverID = parts[0].trim();
        String newDriverID = parts[1].trim();
        String status = parts[2].trim();
        String name = parts[3].trim();

        // Call the 'updateDriverInformation' method to update the driver's information
        // in the database
        updateDriverInformation(driverID, newDriverID, status, name);
    }

    /**
     * Deletes a driver entry with the matching ID from the database.
     *
     * @param driverID The ID of the driver entry to be deleted.
     */
    public static void deleteDriver(final String driverID) {
        try {
            // Execute an SQL statement to delete the driver entry with the matching
            // driverID
            statement.executeUpdate("DELETE FROM Driver WHERE DriverID='" + driverID + "';");
        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the deletion process
            System.out.println("Error message when deleting driver");
        }
    }

    /**
     * Prompts the user to enter the ID of a driver and deletes the driver entry
     * with the matching ID from the database. This method uses a Scanner to
     * read the driver ID from the console and then performs a deletion
     * operation in the driver table in the database based on this ID.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void deleteDriverByInput(final Scanner scnr) {
        System.out.println("Enter the ID of the driver to be deleted (e.g., 'oapatel2'):");
        // Read the user's input for the driver ID and trim any leading/trailing spaces
        String driverID = scnr.nextLine().trim();
        // Call the 'deleteDriver' method to delete the driver entry from the database
        deleteDriver(driverID);
    }

    /**
     * Creates a new entry for the ParkingLot table with the given Parking Lot
     * information.
     * Additionally, this method initializes parking spaces within the newly created
     * lot, including regular,
     * handicap, compact car, and electric spaces.
     *
     * @param lotName The name of the parking lot to be created.
     * @param address The address of the parking lot to be created.
     */
    public static void enterLot(final String lotName, final String address) {
        try {
            // Inserting data into the ParkingLot table
            statement.executeUpdate(
                    "INSERT INTO ParkingLot (LotName, Address) VALUES ('" + lotName + "','" + address + "');");
        } catch (SQLException e) {
            System.out.println("Error message when inserting lot");
        }

        // Enters regular spaces to this lot
        for (int i = 0; i < 5; i++) {
            enterSpace(i, lotName, "regular", "available");
        }

        // Enters handicap spaces to this lot
        for (int i = 0; i < 5; i++) {
            enterSpace(i + 5, lotName, "handicap", "available");
        }

        // Enters compact car spaces to this lot
        for (int i = 0; i < 5; i++) {
            enterSpace(i + 10, lotName, "compact car", "available");
        }

        // Enters electric spaces to this lot
        for (int i = 0; i < 5; i++) {
            enterSpace(i + 15, lotName, "electric", "available");
        }

    }

    /**
     * Prompts the user to enter the name and address of a parking lot in one line,
     * separated by commas, and creates a new entry in the ParkingLot table. The
     * method
     * uses a Scanner to read this information from the console and then inserts it
     * into
     * the ParkingLot table in the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void enterLotByInput(final Scanner scnr) {

        System.out.println(
                "Enter the name and address of the parking lot separated by a comma (e.g., 'MainLot', '123 Wolf Drive'):");
        // Read the user's input as a single line
        String input = scnr.nextLine();
        // Split the input line into an array of strings using a comma as the separator
        String[] parts = input.split(",");

        // Check if the input contains both the lot name and address
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the lot name and address separated by a comma.");
            return;
        }

        // Extract and trim the lot name and address from the input
        String lotName = parts[0].trim();
        String address = parts[1].trim();

        // Call the 'enterLot' method to create a new parking lot entry in the database
        enterLot(lotName, address);

    }

    /**
     * Updates an existing entry in the ParkingLot table with the matching Parking
     * Lot name.
     * This method allows for updating the address and/or the name of the parking
     * lot.
     *
     * @param oldLotName The current name of the parking lot entry to be updated.
     * @param newLotName The new name for the parking lot entry (optional). Pass an
     *                   empty string or null if not updating.
     * @param address    The new address for the parking lot entry (optional). Pass
     *                   an empty string or null if not updating.
     */

    public static void updateLotInformation(final String oldLotName, final String newLotName, final String address) {
        // Construct and execute SQL statements to update parking lot information based
        // on provided parameters
        if (address.length() != 0 && address != null) {
            try {
                // Updating ParkingLot entry address with the matching lot name.
                statement.executeUpdate(
                        "UPDATE ParkingLot SET Address='" + address + "' WHERE LotName = '" + oldLotName + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating lot address");
            }
        }

        if (newLotName.length() != 0 && newLotName != null) {
            try {
                // Updating ParkingLot entry name with the matching lot name.
                statement.executeUpdate(
                        "UPDATE ParkingLot SET LotName='" + newLotName + "' WHERE LotName = '" + oldLotName + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating lot name");
            }
        }
    }

    /**
     * Prompts the user to enter the name of a parking lot and its new address in
     * one line,
     * separated by a comma, and updates the corresponding entry in the ParkingLot
     * table.
     * This method uses a Scanner to read this information from the console and then
     * updates
     * the parking lot's address in the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void updateLotInformationByInput(final Scanner scnr) {

        System.out.println(
                "Enter the name of the parking lot and the new address separated by a comma (e.g., 'OldLot', 'NewLot', '123 Wolf Drive'):");

        // Read the user's input as a single line
        String input = scnr.nextLine();

        // Split the input line into an array of strings using commas as separators
        String[] parts = input.split(",");

        // Check if the input contains both the old lot name, new lot name, and address
        if (parts.length < 3) {
            System.out
                    .println("Invalid input format. Please enter the lot name and new address separated by a comma.");
            return;
        }
        // Extract and trim the old lot name, new lot name, and address from the input
        String oldLotName = parts[0].trim();
        String newLotName = parts[1].trim();
        String address = parts[2].trim();

        // Call the 'updateLotInformation' method to update the parking lot's address in
        // the database
        updateLotInformation(oldLotName, newLotName, address);

    }

    /**
     * Deletes a ParkingLot entry with the matching lot name from the database.
     *
     * @param lotName The name of the Parking Lot entry to be deleted.
     */
    public static void deleteLot(final String lotName) {
        try {
            // Deletes ParkingLot entry with the matching lot name.
            statement.execute("DELETE FROM ParkingLot WHERE LotName='" + lotName + "';");
        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the deletion process
            System.out.println("Error message when deleting lot");
        }

    }

    /**
     * Prompts the user to enter the name of a parking lot to be deleted. This
     * method uses a Scanner
     * to read the parking lot name from the console and then deletes the
     * corresponding entry in the
     * ParkingLot table in the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void deleteLotByInput(final Scanner scnr) {

        System.out.println("Enter the name of the parking lot to be deleted: (e.g., 'MainLot')");
        // Read the user's input for the parking lot name and trim any leading/trailing
        // spaces
        String lotName = scnr.nextLine().trim();

        // Call the 'deleteLot' method to delete the Parking Lot entry from the database
        deleteLot(lotName);

    }

    /**
     * Creates a new Zone entry with the given zone information and associates it
     * with a specific parking lot.
     *
     * @param zoneID  The ID of the zone to be created.
     * @param lotName The name of the Parking Lot in which the new zone will exist.
     */
    public static void enterZone(final String zoneID, final String lotName) {
        try {
            // Inserting into Zone table
            statement.executeUpdate("INSERT INTO Zone(ZoneID, LotName) VALUES ('" + zoneID + "','" + lotName + "');");
        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the insertion process
            System.out.println("Error message when entering zone");
        }

    }

    /**
     * Prompts the user to enter the ID of a zone and the name of the parking lot it
     * belongs to in one line,
     * separated by a comma, and creates a new entry in the Zone table. This method
     * uses a Scanner to read this
     * information from the console and then inserts it into the Zone table in the
     * database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void enterZoneByInput(final Scanner scnr) {

        System.out.println(
                "Enter the zone ID and the name of the parking lot separated by a comma: (e.g., 'A', 'MainLot')");
        // Read the user's input for the zone ID and lot name as a single line
        String input = scnr.nextLine();
        // Split the input line into an array of strings using a comma as the separator
        String[] parts = input.split(",");

        // Check if the input contains both the zone ID and lot name
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the zone ID and lot name separated by a comma.");
            return;
        }

        // Extract and trim the zone ID and lot name from the input
        String zoneID = parts[0].trim();
        String lotName = parts[1].trim();

        // Call the 'enterZone' method to create a new zone entry in the database
        enterZone(zoneID, lotName);

    }

    /**
     * Updates an existing zone entry with the given parking lot name by modifying
     * its Zone ID.
     *
     * @param zoneID    The ID of the zone to be updated.
     * @param newZoneID The new Zone ID for the existing zone entry (optional). Pass
     *                  an empty string or null if not updating.
     * @param lotName   The parking lot where the existing zone currently exists.
     */
    public static void updateZone(final String zoneID, final String newZoneID, final String lotName) {

        try {
            // Updating Zone entry with existing ID
            statement.executeUpdate("UPDATE Zone SET ZoneID='" + newZoneID + "' WHERE ZoneID = '" + zoneID
                    + "' AND LotName ='" + lotName + "';");
        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the update process
            System.out.println("Error message when updating zoneID");
        }

    }

    /**
     * Prompts the user to enter the ID of a zone, its new parking lot name, and its
     * old parking lot name
     * in one line, separated by commas, and updates the corresponding entry in the
     * Zone table. This method
     * uses a Scanner to read this information from the console and then updates the
     * zone's parking lot name
     * in the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void updateZoneByInput(final Scanner scnr) {

        System.out.println(
                "Enter the zone ID, new zone ID, and parking lot name separated by commas (e.g., 'B', 'A', 'MainLot'):");
        // Read the user's input for the zone ID, new zone ID, and parking lot name as a
        // single line
        String input = scnr.nextLine();

        // Split the input line into an array of strings using commas as the separator
        String[] parts = input.split(",");

        // Check if the input contains the required zone ID, new zone ID, and parking
        // lot name
        if (parts.length < 3) {
            System.out.println(
                    "Invalid input format. Please enter the zone ID, new lot name, and old lot name separated by commas.");
            return;
        }

        // Extract and trim the old zone ID, new zone ID, and parking lot name from the
        // input
        String oldZoneID = parts[0].trim();
        String newZoneID = parts[1].trim();
        String lotName = parts[2].trim();

        // Call the 'updateZone' method to update the zone's parking lot name in the
        // database
        updateZone(oldZoneID, newZoneID, lotName);

    }

    /**
     * Deletes an existing Zone entry with the matching zoneID and associated
     * parking lot name from the database.
     *
     * @param zoneID  The ID of the zone entry to be deleted.
     * @param lotName The name of the parking lot where the Zone entry currently
     *                exists.
     */
    public static void deleteZone(final String zoneID, final String lotName) {
        try {
            // Deletes Zone entry with existing ID
            statement.executeUpdate("DELETE FROM Zone WHERE ZoneID='" + zoneID + "' AND LotName ='" + lotName + "';");
        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the deletion process
            System.out.println("Error message when deleting zone");
        }

    }

    /**
     * Prompts the user to enter the ID of a zone and the name of the parking lot it
     * exists in, in one line,
     * separated by a comma, and deletes the corresponding entry in the Zone table.
     * This method uses a Scanner
     * to read this information from the console and then performs the deletion in
     * the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void deleteZoneByInput(final Scanner scnr) {

        System.out.println(
                "Enter the zone ID and the name of the parking lot separated by a comma (e.g., 'A', 'MainLot'):");
        // Read the user's input for the zone ID and parking lot name as a single line
        String input = scnr.nextLine();
        // Split the input line into an array of strings using a comma as the separator
        String[] parts = input.split(",");

        // Check if the input contains both the zone ID and parking lot name
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the zone ID and lot name separated by a comma.");
            return;
        }
        // Extract and trim the zone ID and parking lot name from the input
        String zoneID = parts[0].trim();
        String lotName = parts[1].trim();

        // Call the 'deleteZone' method to delete the corresponding Zone entry in the
        // database
        deleteZone(zoneID, lotName);
    }

    /**
     * Creates a new Space entry with the given space information.
     *
     * @param spaceNumber        The number of the space to be created.
     * @param lotName            The name of the Parking Lot where the new space
     *                           will exist.
     * @param spaceType          The type of the space to be created.
     * @param availabilityStatus The availability status of the space to be created.
     */
    public static void enterSpace(final int spaceNumber, final String lotName, final String spaceType,
            final String availabilityStatus) {
        try {
            // Insert entry into Space table
            statement.executeUpdate("INSERT INTO Space (SpaceNumber, LotName, SpaceType, AvailabilityStatus) VALUES ('"
                    + spaceNumber + "','" + lotName + "','" + spaceType + "','" + availabilityStatus + "');");

        } catch (SQLException e) {
            // Handle any SQL-related errors that may occur during the insertion process
            System.out.println("Error message when entering space");
        }
    }

    /**
     * Prompts the user to enter the number, lot name, type, and availability status
     * of a parking space
     * in one line, separated by commas, and creates a new entry in the Space table.
     * This method uses a
     * Scanner to read this information from the console and then inserts it into
     * the Space table in the database.
     *
     * @param scnr A Scanner object used for reading user input.
     */
    public static void enterSpaceByInput(final Scanner scnr) {

        System.out.println(
                "Enter the space number, lot name, space type, and availability status separated by commas (e.g., 101, 'MainLot', 'Regular', 'Available'):");
        // Read the user's input for space information as a single line
        String input = scnr.nextLine();
        // Split the input line into an array of strings using a comma as the separator

        String[] parts = input.split(",");

        // Check if the input contains all the required information (space number, lot
        // name, space type, and availability status)
        if (parts.length < 4) {
            System.out.println(
                    "Invalid input format. Please enter the space number, lot name, space type, and availability status separated by commas.");
            return;
        }

        // Extract and parse the space number, lot name, space type, and availability
        // status from the input
        int spaceNumber = Integer.parseInt(parts[0].trim());
        String lotName = parts[1].trim();
        String spaceType = parts[2].trim();
        String availabilityStatus = parts[3].trim();

        // Insert entry into Space table
        enterSpace(spaceNumber, lotName, spaceType, availabilityStatus);

    }

    /**
     * Updates an existing parking space entry with the given spaceNumber and
     * lotName to the
     * specified parameters (space type, availability status, and space number).
     *
     * @param spaceNumber        The current space number to be updated.
     * @param newSpaceNumber     The new space number (optional) to replace the
     *                           current space number.
     * @param lotName            The name of the parking lot where the space exists.
     * @param spaceType          The new space type (optional) to update the
     *                           existing space type.
     * @param availabilityStatus The new availability status (optional) to update
     *                           the existing status.
     */
    public static void updateSpace(final String spaceNumber, final String newSpaceNumber, final String lotName,
            final String spaceType, final String availabilityStatus) {

        // Parse the current space number into an integer
        int spaceNumberVal = Integer.parseInt(spaceNumber);

        // Update spaceType if provided
        if (spaceType.length() != 0 && spaceType != null) {
            try {
                // Updates spaceType with matching spaceNumber and lotName
                statement.executeUpdate("UPDATE Space SET SpaceType='" + spaceType + "' " + "WHERE SpaceNumber='"
                        + spaceNumberVal + "' AND LotName='" + lotName + "';");

            } catch (SQLException e) {
                System.out.println("Error message when updating spacetype");
            }
        }
        // Update availabilityStatus if provided
        if (availabilityStatus.length() != 0 && availabilityStatus != null) {
            try {
                // Updates availabilityStatus with matching spaceNumber and
                // lotName
                statement.executeUpdate("UPDATE Space SET AvailabilityStatus='" + availabilityStatus + "' "
                        + "WHERE SpaceNumber='" + spaceNumberVal + "' AND LotName='" + lotName + "';");

            } catch (SQLException e) {
                System.out.println("Error message when updating space availability");
            }
        }

        // Update space number if a new space number is provided
        if (newSpaceNumber.length() != 0 && newSpaceNumber != null) {
            try {
                // Parse the new space number into an integer
                int tempSpaceNumber = Integer.parseInt(newSpaceNumber);
                // Updates spaceType with matching spaceNumber and lotName
                statement.executeUpdate("UPDATE Space SET SpaceNumber='" + tempSpaceNumber + "' "
                        + "WHERE SpaceNumber='" + spaceNumberVal + "' AND LotName='" + lotName + "';");

            } catch (SQLException e) {
                System.out.println("Error message when updating space number");
            }
        }

    }

    /**
     * Prompts the user to enter the details of a parking space to be updated,
     * including the current
     * space number, new space number (optional), lot name, new space type
     * (optional), and new availability
     * status (optional). The user input should be provided in one line, separated
     * by commas. The method uses
     * a Scanner to read this information from the console and then updates the
     * corresponding entry in the
     * Space table in the database.
     *
     * @param scnr A Scanner object used to read user input from the console.
     */
    public static void updateSpaceByInput(final Scanner scnr) {
        try {
            System.out.println(
                    "Enter the space number, new space number, lot name, new space type, and new availability status separated by commas (e.g., 101, 102, 'MainLot', Compact, Occupied'):");
            String input = scnr.nextLine();
            String[] parts = input.split(",");

            if (parts.length < 5) {
                System.out.println(
                        "Invalid input format. Please enter the space number, lot name, space type, and availability status separated by commas.");
                return;
            }

            String spaceNumber = parts[0].trim();
            String newSpaceNumber = parts[1].trim();
            String lotName = parts[2].trim();
            String spaceType = parts[3].trim();
            String availabilityStatus = parts[4].trim();

            updateSpace(spaceNumber, newSpaceNumber, lotName, spaceType, availabilityStatus);

        } catch (NumberFormatException e) {
            System.out.println("Invalid space number format. Please enter a valid number.");
        }

    }

    /**
     * Deletes a parking space entry from the Space table based on the provided
     * space number and lot name.
     * This method removes the parking space with the specified space number and lot
     * name combination from
     * the database. It uses these parameters to identify the space to be deleted.
     *
     * @param spaceNumber The number of the parking space to be deleted.
     * @param lotName     The name of the parking lot where the parking space
     *                    exists.
     */
    public static void deleteSpace(final int spaceNumber, String lotName) {

        try {
            // Delete entry from Space table with matching space number and lot name
            statement.executeUpdate(
                    "DELETE FROM Space WHERE SpaceNumber ='" + spaceNumber + "' AND LotName ='" + lotName + "';");

        } catch (SQLException e) {
            System.out.println("Error message when deleting space");
        }
    }

    /**
     * Prompts the user to enter the number of a parking space and the name of the
     * corresponding parking lot.
     * It then deletes the entry for the specified parking space from the Space
     * table in the database. This method
     * uses a Scanner to read the input from the user and perform the deletion
     * operation based on the provided space number
     * and lot name.
     *
     * @param scnr A Scanner object used to read user input.
     */
    public static void deleteSpaceByInput(final Scanner scnr) {

        System.out.println(
                "Enter the space number and the lot name of the space to delete (e.g., 101, Centennial Deck) :");
        String input = scnr.nextLine();
        String[] parts = input.split(",");
        if (parts.length < 2) {
            System.out.println(
                    "Invalid input format. Please enter the space number and lot name separated by commas.");
            return;
        }

        String spaceNumber = parts[0].trim();
        String lotName = parts[1].trim();

        // Delete entry from Space table with matching space number
        deleteSpace(Integer.parseInt(spaceNumber), lotName);

    }

    /**
     * Assigns an existing Zone within one Parking Lot to another Parking Lot.
     * This method updates the lot name of a Zone with the specified Zone ID,
     * transferring it
     * from the old lot to the new lot. The assignment is performed in the database.
     *
     * @param newLotName The name of the new Parking Lot to which the Zone will be
     *                   assigned.
     * @param ZoneID     The ID of the Zone to be reassigned to the new Parking Lot.
     * @param oldLotName The name of the old Parking Lot from which the Zone is
     *                   being transferred.
     */
    public static void assignZoneToLot(final String newLotName, final String ZoneID, final String oldLotName) {
        try {
            // Assign an existing Zone from one lot to another
            statement.executeUpdate("UPDATE Zone SET LotName='" + newLotName + "' WHERE LotName ='" + oldLotName
                    + "' AND ZoneID ='" + ZoneID + "';");
        } catch (SQLException e) {
            System.out.println("Error message when assigning zone to Lot");
        }
    }

    /**
     * Prompts the user to enter the new lot name, zone ID, and old lot name in
     * one line, separated by commas, and assigns the specified zone from the old
     * parking lot to the new parking lot. The method uses a Scanner to read this
     * information from the console and then updates the Zone table in the database
     * accordingly.
     *
     * @param scnr A Scanner object used to read user input from the console.
     */
    public static void assignZoneToLotByInput(final Scanner scnr) {
        System.out.println(
                "Enter the new lot name, zone ID, and old lot name separated by commas (e.g., 'Centennial', 'A', 'Main'):");
        String input = scnr.nextLine();
        String[] parts = input.split(",");

        if (parts.length < 3) {
            System.out.println(
                    "Invalid input format. Please enter the new lot name, zone ID, and old lot name separated by commas.");
            return;
        }

        String newLotName = parts[0].trim();
        String zoneID = parts[1].trim();
        String oldLotName = parts[2].trim();

        // Assign an existing Zone from one lot to another
        assignZoneToLot(newLotName, zoneID, oldLotName);
    }

    /**
     * Assigns an existing Space existing within a Parking Lot to another Parking
     * Lot.
     *
     * @param newLotName  The name of the new Parking Lot where the Space will be
     *                    assigned.
     * @param spaceNumber The number of the Space to be assigned.
     * @param oldLotName  The name of the old Parking Lot from which the Space is
     *                    moved.
     */
    public static void assignSpaceToLot(final String newLotName, final int spaceNumber, final String oldLotName) {
        try {
            // Assign an existing Space from one lot to another
            statement.executeUpdate("UPDATE Space SET LotName='" + newLotName + "' WHERE LotName ='" + oldLotName
                    + "' AND SpaceNumber =" + spaceNumber + ";");
        } catch (SQLException e) {
            System.out.println("Error message when assigning space to Lot");
        }
    }

    /**
     * Prompts the user to enter the new lot name, space number, and old lot name
     * in one line, separated by commas, and assigns the specified space from the
     * old parking lot to the new parking lot. The method uses a Scanner to read
     * this information from the console and then updates the Space table in the
     * database accordingly.
     *
     * @param scnr The Scanner object used for user input.
     */
    public static void assignSpaceToLotByInput(final Scanner scnr) {
        try {
            System.out.println(
                    "Enter the new lot name, space number, and old lot name separated by commas (e.g., 'Centennial', 101, 'Main'):");
            String input = scnr.nextLine();
            String[] parts = input.split(",");

            if (parts.length < 3) {
                System.out.println(
                        "Invalid input format. Please enter the new lot name, space number, and old lot name separated by commas.");
                return;
            }

            String newLotName = parts[0].trim();
            int spaceNumber = Integer.parseInt(parts[1].trim());
            String oldLotName = parts[2].trim();

            // Assign an existing Space from one lot to another
            assignSpaceToLot(newLotName, spaceNumber, oldLotName);
        } catch (NumberFormatException e) {
            System.out.println("Invalid space number format. Please enter a valid number.");
        }
    }

    /**
     * Assigns a new space type to an existing parking space within a specified
     * parking lot.
     *
     * @param lotName     The name of the parking lot where the space exists.
     * @param spaceNumber The number of the parking space to be updated.
     * @param spaceType   The new type to assign to the parking space.
     */
    public static void assignTypeToSpace(final String lotName, final int spaceNumber, final String spaceType) {
        try {
            // Assign an Type to an existing Space
            statement.executeUpdate("UPDATE Space SET SpaceType='" + spaceType + "' WHERE LotName ='" + lotName
                    + "' AND SpaceNumber =" + spaceNumber + ";");
        } catch (SQLException e) {
            System.out.println("Error message when assigning type to space");
        }
    }

    /**
     * Prompts the user to enter the parking lot name, space number, and the new
     * space type in one line,
     * separated by commas, and assigns the new space type to the specified parking
     * space. The method uses
     * a Scanner to read this information from the console and then updates the
     * Space table in the database
     * accordingly.
     *
     * @param scnr The Scanner object used to read user input from the console.
     */
    public static void assignTypeToSpaceByInput(final Scanner scnr) {
        try {
            System.out.println(
                    "Enter the lot name, space number, and new space type separated by commas (e.g., 'MainLot', 101, 'Compact'):");
            String input = scnr.nextLine();
            String[] parts = input.split(",");

            if (parts.length < 3) {
                System.out.println(
                        "Invalid input format. Please enter the lot name, space number, and new space type separated by commas.");
                return;
            }

            String lotName = parts[0].trim();
            int spaceNumber = Integer.parseInt(parts[1].trim());
            String spaceType = parts[2].trim();

            // Assign a new Type to an existing Space
            assignTypeToSpace(lotName, spaceNumber, spaceType);

        } catch (NumberFormatException e) {
            System.out.println("Invalid space number format. Please enter a valid number.");
        }
    }

    /**
     * Updates the payment status of an existing citation in the database.
     *
     * @param paymentStatus  The new payment status to be assigned to the citation
     *                       (e.g., 'Paid', 'Unpaid').
     * @param citationNumber The unique number of the citation to be updated.
     *
     */
    public static void updatePaymentStatus(final String paymentStatus, final String citationNumber) {
        try {
            // Update payment status on an exisitng Citation
            statement.executeUpdate("UPDATE Citation SET PaymentStatus='" + paymentStatus + "' WHERE CitationNumber ='"
                    + citationNumber + "';");
        } catch (SQLException e) {
            System.out.println("Error message when updating payment status");
        }
    }

    /**
     * Prompts the user to enter the payment status and citation number in one line,
     * separated by a comma,
     * and updates the payment status of the specified citation in the database.
     *
     * @param scnr A Scanner object used to read user input from the console.
     */
    public static void updatePaymentStatusByInput(final Scanner scnr) {

        System.out.println("Enter the payment status and citation number separated by a comma (e.g., 'Paid', 12): ");
        String input = scnr.nextLine();
        String[] parts = input.split(",");

        if (parts.length < 2) {
            System.out.println(
                    "Invalid input format. Please enter the payment status and citation number separated by a comma.");
            return;
        }

        String paymentStatus = parts[0].trim();
        String citationNumber = parts[1].trim();

        // Update payment status on an existing Citation
        updatePaymentStatus(paymentStatus, citationNumber);

    }

    // Task and operations: Maintaining permits and vehicle information for each
    // driver

    /**
     * Returns the name and status of the driver with the specified DriverID.
     *
     * @param driverID
     *                 the ID of the driver to return
     */
    public static void getDriverInfo(final String driverID) {
        try {
            ResultSet rs = statement
                    .executeQuery("SELECT Name, Status FROM Driver WHERE DriverID='" + driverID + "';");
            String s = "";
            StringBuilder sb = new StringBuilder(s);

            while (rs.next()) {
                sb.append(rs.getString("Name"));
                sb.append(" ");
                sb.append(rs.getString("Status"));
            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            System.out.println("Error message when getting driver info");
        }
    }

    /**
     * Initiates the retrieval and display of driver information based on user
     * input.
     *
     * @param scnr The Scanner object used for user input.
     */
    public static void getDriverInfoByInput(final Scanner scnr) {
        System.out.println("Enter the driver id to get driver info: ");
        // Read user input for the driver ID
        String driverID = scnr.next();

        // Invoke the getDriverInfo method with the provided driver ID
        getDriverInfo(driverID);
    }

    /**
     * Retrieves and returns the available space number for a specified space type
     * and parking lot.
     *
     *
     * @param spaceType The type of the space to search for
     * @param lotName   The name of the parking lot in which to search for an
     *                  available space.
     * @return The available space number if found; otherwise, -1.
     */
    public static int returnAvailableSpaceNumber(final String spaceType, final String lotName) {

        try {
            // Execute SQL query to find an available space number for the specified space
            // type and parking lot
            ResultSet rs = statement.executeQuery("SELECT * From Space WHERE spaceType = '" + spaceType
                    + "' AND lotName = '" + lotName + "' AND AvailabilityStatus = 'Available';");
            StringBuilder sb = new StringBuilder();

            // Check if an available space is found and append the space number to the
            // StringBuilder
            if (rs.next()) {
                sb.append(rs.getString("SpaceNumber"));
            }

            // Convert the StringBuilder content to a String
            String s = sb.toString();
            // Return -1 if no available space is found; otherwise, parse and return the
            // space number
            if (s.length() == 0) {
                return -1;
            } else {
                Integer temp = Integer.parseInt(s);
                return temp;
            }

        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message finding available space number");
            return -1;
        }
    }

    /**
     * Retrieves and returns the occupied space number for a specified space type
     * and parking lot.
     *
     * @param spaceType The type of the space to search for
     * @param lotName   The name of the parking lot in which to search for an
     *                  occupied space.
     * @return The occupied space number if found; otherwise, -1.
     */
    public static int returnOccupiedSpaceNumber(final String spaceType, final String lotName) {

        try {
            // Execute SQL query to find an occupied space number for the specified space
            // type and parking lot
            ResultSet rs = statement.executeQuery("SELECT SpaceNumber From Space WHERE spaceType = '" + spaceType
                    + "' AND lotName = '" + lotName + "' AND AvailabilityStatus = 'Occupied';");
            StringBuilder sb = new StringBuilder();

            // Check if an occupied space is found and append the space number to the
            // StringBuilder
            if (rs.next()) {
                sb.append(rs.getString("SpaceNumber"));
            }
            // Convert the StringBuilder content to a String
            String s = sb.toString();
            // Return -1 if no occupied space is found; otherwise, parse and return the
            // space number
            if (s.length() == 0) {
                return -1;
            } else {
                Integer temp = Integer.parseInt(s);
                return temp;
            }

        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Checks the validity of a new parking permit based on driver information and
     * permit restrictions.
     *
     *
     * @param permitID   The ID of the new permit.
     * @param permitType The type of the new permit
     * @param zoneID     The ID of the selected parking zone.
     * @param lotName    The name of the parking lot.
     * @param driverID   The ID of the driver for whom the permit is issued.
     * @param licenseNum The license number associated with the driver.
     * @param spaceType  The type of parking space requested
     * @param startDate  The start date of the permit.
     * @param expDate    The expiration date of the permit.
     * @param expTime    The expiration time of the permit.
     * @return True if the new permit is valid; false otherwise.
     */
    public static boolean checkValidPermit(final String permitID, final String permitType, final String zoneID,
            final String lotName, final String driverID, final String licenseNum, final String spaceType,
            final String startDate, final String expDate, final String expTime) {
        ResultSet rs;
        String currStatus = "";
        // Retrieve the current status of the driver
        try {
            rs = statement.executeQuery("SELECT Status FROM Driver WHERE DriverID ='" + driverID + "';");
            if (rs.next()) {
                currStatus = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting status from driver");
        }

        ResultSet rs2;
        int permitCount = 0;
        // Count the number of existing permits for the driver
        try {
            rs2 = statement.executeQuery("SELECT COUNT(*) FROM Permit WHERE DriverID = '" + driverID + "';");
            if (rs2.next()) {
                permitCount = rs2.getInt(1);
            }
            rs2.close();

        } catch (SQLException e) {
            System.out.print("Error getting permit count from driver");
        }

        boolean canExecute = true;

        // Check permit validity based on driver status
        if (currStatus.equalsIgnoreCase("V")) {

            // Visitor can only park in zone V
            if (!zoneID.equalsIgnoreCase("V")) {
                canExecute = false;
                System.out.println("Visitor can only park in zone V");
            }

            // Visitor can have only one permit
            if (permitCount >= 1) {
                canExecute = false;
                System.out.println("Visitor already has one permit");
            }

        } else if (currStatus.equalsIgnoreCase("S")) {

            // Student can only park in zones AS, BS, CS, DS
            if (!(zoneID.equalsIgnoreCase("AS") || zoneID.equalsIgnoreCase("BS") || zoneID.equalsIgnoreCase("CS")
                    || zoneID.equalsIgnoreCase("DS"))) {
                canExecute = false;
                System.out.println("Student can only park in zones AS, BS, CS, DS.");
            }

            // Student can have at most two permits
            if (permitCount >= 2) {
                canExecute = false;
                System.out.println("Student already has two permits");
            } else if (permitCount == 1) {
                ResultSet rs3;
                String tempPermitType = "";
                // Get the type of the existing permit
                try {
                    rs3 = statement
                            .executeQuery("SELECT PermitType FROM Permit WHERE DriverID = '" + driverID + "';");
                    if (rs3.next()) {
                        tempPermitType = rs3.getString(1);
                    }
                    rs3.close();
                    // Check if the new permit type is valid for the existing permit type
                    if ((tempPermitType.equalsIgnoreCase("special event"))
                            || (tempPermitType.equalsIgnoreCase("Park & Ride"))) {
                        if ((permitType.equalsIgnoreCase("special event"))
                                || (permitType.equalsIgnoreCase("Park & Ride"))) {
                            canExecute = false;
                            System.out.println("Student already has special permit");
                        }
                    } else {
                        if ((permitType.equalsIgnoreCase("special event"))
                                || (permitType.equalsIgnoreCase("Park & Ride"))) {
                            // do nothing
                        } else {
                            canExecute = false;
                            System.out.println("Student already has regular permit, only special permit allowed");
                        }
                    }
                } catch (SQLException e) {
                    System.out.print("Error getting permit type from driver's permit");
                }
            }
        } else if (currStatus.equalsIgnoreCase("E")) {
            // Employee can only park in zones A, B, C, D
            if (!(zoneID.equalsIgnoreCase("A") || zoneID.equalsIgnoreCase("B") || zoneID.equalsIgnoreCase("C")
                    || zoneID.equals("D"))) {
                canExecute = false;
                System.out.println("Employee can only park in zones A, B, C, D.");
            }
            // Employee can have at most three permits
            if (permitCount >= 3) {
                canExecute = false;
                System.out.println("Employee already has three permits");
            } else if (permitCount == 2) {
                String tempPermitType = "";
                String tempPermitType2 = "";
                // Get both types of existing permits
                try {
                    ResultSet rs4;
                    rs4 = statement
                            .executeQuery("SELECT PermitType FROM Permit WHERE DriverID = '" + driverID + "';");
                    // get both permit types
                    int rowCount = 0;
                    // Iterate over the result set to retrieve permit types if there are at most two
                    // permits
                    while (rs4.next() && rowCount < 2) {
                        // Get the permit type for the first and second permits
                        if (rowCount == 0) {
                            tempPermitType = rs4.getString("PermitType");
                        } else if (rowCount == 1) {
                            tempPermitType2 = rs4.getString("PermitType");
                        }
                        rowCount++;
                    }
                    rs4.close();
                    // Check if both permits are special event or Park & Ride permits
                    if ((tempPermitType.equalsIgnoreCase("special event"))
                            || (tempPermitType.equalsIgnoreCase("Park & Ride"))
                            || tempPermitType2.equalsIgnoreCase("special event")
                            || (tempPermitType2.equalsIgnoreCase("Park & Ride"))) {
                        // If the new permit is also special event or Park & Ride, disallow issuing the
                        // permit
                        if ((permitType.equalsIgnoreCase("special event"))
                                || (permitType.equalsIgnoreCase("Park & Ride"))) {
                            canExecute = false;
                            System.out.println("Employee already has special permit");
                        }
                    } else {
                        // If the new permit is special event or Park & Ride, allow issuing the permit;
                        // otherwise, disallow
                        if ((permitType.equalsIgnoreCase("special event"))
                                || (permitType.equalsIgnoreCase("Park & Ride"))) {
                            // do nothing

                        } else {
                            canExecute = false;
                            System.out.println("Employee already has special permit");
                        }
                    }

                } catch (SQLException e) {
                    System.out.print("Error getting permit type from driver's permit");
                }

            } else if (permitCount == 1) {
                String tempPermitType = "";
                try {
                    ResultSet rs5;
                    // Retrieve the permit type for the single existing permit
                    rs5 = statement
                            .executeQuery("SELECT PermitType FROM Permit WHERE DriverID = '" + driverID + "';");
                    if (rs5.next()) {
                        tempPermitType = rs5.getString(1);
                    }
                    rs5.close();

                    // Check if the existing permit is a special event or Park & Ride permit
                    if ((tempPermitType.equalsIgnoreCase("special event"))
                            || (tempPermitType.equalsIgnoreCase("Park & Ride"))) {
                        // If the new permit is also special event or Park & Ride, disallow issuing the
                        // permit
                        if ((permitType.equalsIgnoreCase("special event"))
                                || (permitType.equalsIgnoreCase("Park & Ride"))) {
                            canExecute = false;
                            System.out.println("Employee already has special permit");
                        }
                    }
                } catch (SQLException e) {
                    System.out.print("Error getting permit type from driver's permit");
                }
            }

        }

        return canExecute;

    }

    /**
     * Enters information for a new parking permit into the database.
     *
     * @param permitID   The ID of the new permit.
     * @param permitType The type of the new permit
     * @param zoneID     The ID of the selected parking zone.
     * @param lotName    The name of the parking lot.
     * @param driverID   The ID of the driver for whom the permit is issued.
     * @param licenseNum The license number associated with the driver.
     * @param spaceType  The type of parking space requested
     * @param startDate  The start date of the permit.
     * @param expDate    The expiration date of the permit.
     * @param expTime    The expiration time of the permit.
     */
    public static void enterPermitInfo(final String permitID, final String permitType, final String zoneID,
            final String lotName, final String driverID, final String licenseNum, final String spaceType,
            final String startDate, final String expDate, final String expTime) {

        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Check to see if the permit can be created based on permit
            // restrictions
            boolean validPermit = checkValidPermit(permitID, permitType, zoneID, lotName,
                    driverID, licenseNum,
                    spaceType, startDate, expDate, expTime);

            int spaceNumber = returnAvailableSpaceNumber(spaceType, lotName);
            if (validPermit && spaceNumber != -1) {
                // Insert Permit into the database
                statement.executeUpdate(
                        "INSERT INTO Permit (PermitID, SpaceType, StartDate, ExpDate, DriverID, PermitType, ExpTime, LicenseNum, ZoneID, LotName) VALUES ('"
                                + permitID + "','" + spaceType + "','" + startDate + "','" + expDate + "','"
                                + driverID
                                + "','" + permitType + "','" + expTime + "','" + licenseNum + "','" + zoneID
                                + "','"
                                + lotName + "');");

                // Update space to occupied
                updateSpace(String.valueOf(spaceNumber), "", lotName, spaceType, "occupied");

                // Commit the transaction
                connection.commit();
            } else {
                // Rollback the transaction if the permit is invalid or no space
                // is available
                connection.rollback();
                System.out.println("Transaction rolled back due to invalid permit or no available space.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
            try {
                // Roll back the transaction in case of an error
                connection.rollback();
                System.out.println("Transaction rolled back due to SQLException.");
            } catch (SQLException ex) {
                System.out.println("Error occurred during rollback: " + ex.getMessage());
            }
        } finally {
            try {
                // Reset auto-commit to its default state
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error occurred while resetting auto-commit: " +
                        ex.getMessage());
            }
        }
    }

    /**
     * Accepts user input to enter information for a new parking permit into the
     * database.
     *
     * @param scnr Scanner object for user input.
     */
    public static void enterPermitInfoByInput(final Scanner scnr) {
        System.out.println(
                "Enter the permitID, permit type, zone ID, lot name, driver id, license number, space type, start date, expiration date, and expiration time (e.g., 1, 'commuter', 'AS', 'Centennial', 'albhadri', 'XYZ334', 'handicap', '2023-10-04', '2023-10-21', '08:00:00'): ");
        String input = scnr.nextLine();

        // Split the input into parts using commas
        String[] parts = input.split(",");
        if (parts.length < 10) {
            // Print an error message if the input format is invalid
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        // Extract information from the input
        String permitID = parts[0].trim();
        String permitType = parts[1].trim();
        String zoneID = parts[2].trim();
        String lotName = parts[3].trim();
        String driverID = parts[4].trim();
        String licenseNum = parts[5].trim();
        String spaceType = parts[6].trim();
        String startDate = parts[7].trim();
        String expDate = parts[8].trim();
        String expTime = parts[9].trim();

        // Call the method to enter permit information with the extracted values
        enterPermitInfo(permitID, permitType, zoneID, lotName, driverID, licenseNum, spaceType, startDate, expDate,
                expTime);

    }

    /**
     * Updates an existing permit with the permit id, space type, start date,
     * expiration date, driver id, permit type, expiration time, license number,
     * zone id, or the lot name.
     *
     * @param permitID
     *                   ID of the permit
     * @param spaceType
     *                   type of the space for the permit
     * @param startDate
     *                   start date of the permit
     * @param expDate
     *                   expiration date of the permit
     * @param driverID
     *                   ID of the driver of the permit
     * @param permitType
     *                   type of the permit
     * @param expTime
     *                   expiration time of the permit
     * @param licenseNum
     *                   license number of the vehicle for the permit
     * @param zoneID
     *                   zone ID of the permit
     * @param lotName
     *                   lot name of the permit
     */
    public static void updatePermit(final String permitID, final String newPermitID, final String permitType,
            final String zoneID, final String lotName, final String driverID, final String licenseNum,
            final String spaceType, final String startDate, final String expDate, final String expTime) {

        // call the check valid permit and use the old values for permit
        // wherever a
        // parameter is null
        ResultSet rs;

        // Variables to store existing permit information
        String tempSpaceType = "";
        String tempStartDate = "";
        String tempExpDate = "";
        String tempDriverID = "";
        String tempPermitType = "";
        String tempExpTime = "";
        String tempLicenseNum = "";
        String tempZoneID = "";
        String tempLotName = "";

        try {

            rs = statement.executeQuery("SELECT * FROM Permit WHERE PermitID = '" + permitID + "';");

            // Fetch existing values
            if (rs.next()) {
                tempSpaceType = rs.getString("SpaceType");
                tempStartDate = rs.getString("StartDate");
                tempExpDate = rs.getString("ExpDate");
                tempDriverID = rs.getString("DriverID");
                tempPermitType = rs.getString("PermitType");
                tempExpTime = rs.getString("ExpTime");
                tempLicenseNum = rs.getString("LicenseNum");
                tempZoneID = rs.getString("ZoneID");
                tempLotName = rs.getString("LotName");
            }
        } catch (SQLException e) {
            System.out.println("Error trying to retrieve permit information");
        }

        // Use old values if new ones are null
        String tempPermitID = newPermitID != null ? newPermitID : permitID;
        String newSpaceType = spaceType != null ? spaceType : tempSpaceType;
        String newStartDate = startDate != null ? startDate : tempStartDate;
        String newExpDate = expDate != null ? expDate : tempExpDate;
        String newDriverID = driverID != null ? driverID : tempDriverID;
        String newPermitType = permitType != null ? permitType : tempPermitType;
        String newExpTime = expTime != null ? expTime : tempExpTime;
        String newLicenseNum = licenseNum != null ? licenseNum : tempLicenseNum;
        String newZoneID = zoneID != null ? zoneID : tempZoneID;
        String newLotName = lotName != null ? lotName : tempLotName;

        System.out.println(tempPermitID + newPermitType + newZoneID + newLotName + newDriverID + newLicenseNum
                + newSpaceType + newStartDate + newExpDate + newExpTime);

        // Check the validity of the updated permit
        boolean validPermit = checkValidPermit(tempPermitID, newPermitType, newZoneID, newLotName, newDriverID,
                newLicenseNum, newSpaceType, newStartDate, newExpDate, newExpTime);

        System.out.println(validPermit);

        // Check the validity of the updated permit
        if (validPermit) {
            // Perform updates based on non-null and non-empty parameters
            if (spaceType.length() != 0 && spaceType != null) {
                // Update SpaceType
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET SpaceType='" + spaceType + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit spacetype");
                }

            }
            if (startDate.length() != 0 && startDate != null) {
                // Update StartDate
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET StartDate='" + startDate + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit start date");
                }

            }

            if (expDate.length() != 0 && expDate != null) {
                // Update ExpDate
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET ExpDate='" + expDate + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit exp date");
                }

            }

            if (driverID.length() != 0 && driverID != null) {
                // Update DriverId
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET DriverId='" + driverID + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating driver id");
                }
            }

            if (permitType.length() != 0 && permitType != null) {
                // Update PermitType
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET PermitType='" + permitType + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permittype");
                }

            }

            if (expTime.length() != 0 && expTime != null) {
                // Update ExpTime
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET ExpTime='" + expTime + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit expTime");
                }

            }

            if (licenseNum.length() != 0 && licenseNum != null) {
                // Update LicenseNum
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET LicenseNum='" + licenseNum + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit licenseNum");
                }

            }

            // Update ZoneId and LotName
            if (zoneID.length() != 0 && zoneID != null) {
                if (lotName.length() != 0 && lotName != null) {
                    try {
                        statement.executeUpdate(
                                "UPDATE Permit SET LotName='" + lotName + "', ZoneID='" + zoneID + "' WHERE PermitID='"
                                        + permitID + "';");
                    } catch (SQLException e) {
                        System.out.println("Error message when updating permit zone & lot");
                    }

                }
            }

            if (newPermitID.length() != 0 && newPermitID != null) {
                // Update PermitId
                try {
                    statement.executeUpdate(
                            "UPDATE Permit SET PermitID='" + newPermitID + "' WHERE PermitID='" + permitID + "';");
                } catch (SQLException e) {
                    System.out.println("Error message when updating permit ID");
                }

            }
        } else {
            System.out.println("No available space or invalid permit ");
        }

    }

    /**
     * Takes user input to update an existing parking permit in the database.
     *
     * @param scnr The Scanner object to read user input.
     */
    public static void updatePermitByInfo(final Scanner scnr) {
        System.out.println(
                "Enter the old permit id, new permit id, permit type, zone ID, lot name, driver id, license number, space type, start date, expiration date, and expiration time (e.g., 1, 'commuter', 'AS', 'Centennial', 'albhadri', 'XYZ334', 'handicap', '2023-10-04', '2023-10-21', '08:00:00'): ");
        // Read user input
        String input = scnr.nextLine();

        // Parse input and perform update if valid
        String[] parts = input.split(",");
        if (parts.length < 11) {
            // Display error message for invalid input format
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }
        // Extract information from input
        String oldPermitID = parts[0].trim();
        String newPermitID = parts[1].trim();
        String permitType = parts[2].trim();
        String zoneID = parts[3].trim();
        String lotName = parts[4].trim();
        String driverID = parts[5].trim();
        String licenseNum = parts[6].trim();
        String spaceType = parts[7].trim();
        String startDate = parts[8].trim();
        String expDate = parts[9].trim();
        String expTime = parts[10].trim();

        // Call the updatePermit method with the extracted information
        updatePermit(oldPermitID, newPermitID, permitType, zoneID, lotName, driverID, licenseNum, spaceType, startDate,
                expDate, expTime);
    }

    /**
     * Deletes a permit from the database based on the provided permit ID.
     *
     * This method retrieves information about the permit, including space type and
     * lot name, before
     * deleting the corresponding permit record from the 'Permit' table. It also
     * updates the space's
     * availability status in the 'Space' table.
     *
     * @param permitID The unique identifier of the permit to be deleted.
     */
    public static void deletePermit(final String permitID) {
        ResultSet rs;

        String tempSpaceType = "";
        String tempLotName = "";

        try {
            rs = statement.executeQuery("SELECT * FROM Permit WHERE PermitID = '" + permitID + "';");

            // Fetch existing values
            if (rs.next()) {
                tempSpaceType = rs.getString("SpaceType");
                tempLotName = rs.getString("LotName");
            }
        } catch (SQLException e) {
            System.out.println("Error trying to retrieve permit information");
        }

        int spaceNumber = returnOccupiedSpaceNumber(tempSpaceType, tempLotName);
        updateSpace(String.valueOf(spaceNumber), "", tempLotName, tempSpaceType, "available");

        try {

            // Delete entry from Space table with matching space number
            statement.executeUpdate("DELETE FROM Permit WHERE PermitId ='" + permitID + "';");
        } catch (SQLException e) {
            System.out.println("Error message when deleting permit");
        }
    }

    /**
     * Deletes a permit from the database based on the user-provided permit ID.
     *
     * @param scnr The Scanner object to read user input.
     */
    public static void deletePermitByInfo(final Scanner scnr) {
        System.out.println("Enter the permit id to delete (e.g. 12): ");
        String input = scnr.next().trim();
        // Validate user input
        if (input.equals("")) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String permitID = input;

        // Call the deletePermit method to delete the specified permit
        deletePermit(permitID);
    }

    /**
     * Assigns a permit to a driver based on the provided information.
     *
     * This method retrieves information about the specified permit from the
     * database and checks if it is valid
     * for the given driver. If the permit is valid, it updates the 'DriverID' field
     * in the 'Permit' table to link
     * the permit with the driver.
     *
     * @param driverID The ID of the driver to whom the permit is being assigned.
     * @param permitID The ID of the permit being assigned.
     */
    public static void assignPermit(final String driverID, final String permitID) {
        ResultSet rs;

        String tempSpaceType = "";
        String tempStartDate = "";
        String tempExpDate = "";
        String tempPermitType = "";
        String tempExpTime = "";
        String tempLicenseNum = "";
        String tempZoneID = "";
        String tempLotName = "";

        try {
            rs = statement.executeQuery("SELECT * FROM Permit WHERE PermitID = '" + permitID + "';");

            // Fetch existing values
            if (rs.next()) {
                tempSpaceType = rs.getString("SpaceType");
                tempStartDate = rs.getString("StartDate");
                tempExpDate = rs.getString("ExpDate");
                tempPermitType = rs.getString("PermitType");
                tempExpTime = rs.getString("ExpTime");
                tempLicenseNum = rs.getString("LicenseNum");
                tempZoneID = rs.getString("ZoneID");
                tempLotName = rs.getString("LotName");
            }
        } catch (SQLException e) {
            System.out.println("Error trying to retrieve permit information");
        }
        // Check if the permit is valid for the given driver
        boolean validPermit = checkValidPermit(permitID, tempPermitType, tempZoneID, tempLotName, driverID,
                tempLicenseNum, tempSpaceType, tempStartDate, tempExpDate, tempExpTime);

        if (validPermit) {
            try {
                // Update the 'DriverID' field in the 'Permit' table to link the permit with the
                // driver
                statement.executeUpdate(
                        "UPDATE Permit SET DriverID = '" + driverID + "' WHERE PermitID = '" + permitID + "';");

            } catch (SQLException e) {
                System.out.println("Error message when assigning permit" + e.getMessage());
            }
        } else {
            System.out.println("Permit cannot be assigned");
        }
    }

    /**
     * Assigns a permit to a driver based on the provided information.
     *
     * This method prompts the user to enter the driver ID and permit ID. It then
     * calls the {assignPermit} method
     * to link the specified permit with the given driver in the database.
     *
     * @param scnr The Scanner object for user input.
     */
    public static void assignPermitByInfo(final Scanner scnr) {

        System.out.println("Enter the driver ID and permit ID (e.g., 'oapatek', 'XY342'): ");
        String input = scnr.nextLine();

        String[] parts = input.split(",");

        // Check for valid input format
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String driverID = parts[0].trim();
        String permitID = parts[1].trim();

        // Assign the permit to the driver based on the provided information
        assignPermit(driverID, permitID);
    }

    /**
     * Adds a new vehicle entry to the 'Vehicle' table in the database.
     *
     * This method takes the provided information for the license number, year,
     * model, color, and manufacturer
     * and inserts a new record into the 'Vehicle' table in the database.
     *
     * @param licenseNum The license number of the new vehicle.
     * @param year       The year of the new vehicle.
     * @param model      The model of the new vehicle.
     * @param color      The color of the new vehicle.
     * @param manf       The manufacturer of the new vehicle.
     */
    public static void enterVehicle(final String licenseNum, final String year, final String model,
            final String color, final String manf) {
        try {
            statement.executeUpdate("INSERT INTO Vehicle (LicenseNum, Year, Model, Color, Manf) VALUES ('" + licenseNum
                    + "', '" + year + "', '" + model + "', '" + color + "', '" + manf + "');");
        } catch (SQLException e) {
            System.out.println("Error message when entering vehicle");
        }
    }

    /**
     * Adds a new vehicle entry to the database based on the provided information.
     *
     * This method prompts the user to enter the license number, year, model, color,
     * and manufacturer
     * for a new vehicle. It then calls the {enterVehicle} method to insert
     * the information into the database.
     *
     * @param scnr The Scanner object for user input.
     */
    public static void enterVehicleByInfo(final Scanner scnr) {

        System.out.println(
                "Enter the license number, year, model, color, and manufacturer (e.g., 'XYNCL', '2010', 'Civic', 'Blue', 'Honda'): ");
        String input = scnr.nextLine();

        String[] parts = input.split(",");

        if (parts.length < 5) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String licenseNum = parts[0].trim();
        String year = parts[1].trim();
        String model = parts[2].trim();
        String color = parts[3].trim();
        String manf = parts[4].trim();

        // Add the new vehicle based on the provided information
        enterVehicle(licenseNum, year, model, color, manf);
    }

    /**
     * Updates information for a vehicle entry in the database based on the provided
     * details.
     *
     * This method allows updating the year, model, color, manufacturer, and/or
     * license number
     * for a vehicle entry in the 'Vehicle' table in the database. It performs
     * individual updates
     * for each non-null and non-empty input parameter.
     *
     * @param licenseNum    The existing license number of the vehicle to be
     *                      updated.
     * @param newLicenseNum The new license number for the vehicle (can be empty or
     *                      null if not updating).
     * @param year          The new year for the vehicle (can be empty or null if
     *                      not updating).
     * @param model         The new model for the vehicle (can be empty or null if
     *                      not updating).
     * @param color         The new color for the vehicle (can be empty or null if
     *                      not updating).
     * @param manf          The new manufacturer for the vehicle (can be empty or
     *                      null if not updating).
     */
    public static void updateVehicle(final String licenseNum, final String newLicenseNum, final String year,
            final String model, final String color, final String manf) {
        // Update the year if provided
        if (year.length() != 0 && year != null) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Year='" + year + "' WHERE LicenseNum='" + licenseNum + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating vehicle year");
            }
        }
        // Update the model if provided
        if (model.length() != 0 && model != null) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Model='" + model + "' WHERE LicenseNum='" + licenseNum + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating vehicle model");
            }
        }
        // Update the color if provided
        if (color.length() != 0 && color != null) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Color='" + color + "' WHERE LicenseNum='" + licenseNum + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating vehicle color");
            }
        }
        // Update the manufacturer if provided
        if (manf.length() != 0 && manf != null) {

            try {

                statement.executeUpdate(
                        "UPDATE Vehicle SET Manf='" + manf + "' WHERE LicenseNum='" + licenseNum + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating vehicle manf");
            }
        }
        // Update the license number if provided
        if (newLicenseNum.length() != 0 && newLicenseNum != null) {

            try {

                statement.executeUpdate("UPDATE Vehicle SET LicenseNum='" + newLicenseNum + "' WHERE LicenseNum='"
                        + licenseNum + "';");
            } catch (SQLException e) {
                System.out.println("Error message when updating vehicle license num");
            }
        }

    }

    /**
     * Updates a vehicle entry in the database based on the provided information.
     *
     * This method prompts the user to enter the license number, new license number,
     * year, model, color, and manufacturer
     * for the vehicle to be updated. It then calls the {@code updateVehicle} method
     * to perform the update.
     * If an invalid input format is provided, it prints an error message.
     *
     * @param scnr The Scanner object for user input.
     */
    public static void updateVehicleByInfo(final Scanner scnr) {

        System.out.println(
                "Enter the license number, new license number, year, model, color, and manufacturer (e.g., 'XYNCL', 'XYNCZ', '2010', 'Civic', 'Blue', 'Honda'): ");
        String input = scnr.nextLine();

        String[] parts = input.split(",");

        // Check for valid input format
        if (parts.length < 6) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String licenseNum = parts[0].trim();
        String newLicenseNum = parts[1].trim();
        String year = parts[2].trim();
        String model = parts[3].trim();
        String color = parts[4].trim();
        String manf = parts[5].trim();

        updateVehicle(licenseNum, newLicenseNum, year, model, color, manf);
    }

    /**
     * Deletes a vehicle entry from the database based on the provided license
     * number.
     *
     * This method deletes the vehicle entry from the 'Vehicle' table in the
     * database
     * using the given license number.
     *
     * @param licenseNum The license number of the vehicle to be deleted.
     */
    public static void deleteVehicle(final String licenseNum) {

        try {
            statement.executeUpdate("DELETE FROM Vehicle WHERE LicenseNum ='" + licenseNum + "';");

        } catch (SQLException e) {
            System.out.println("Error message when deleting vehicle");
        }
    }

    /**
     * Deletes a vehicle entry from the database based on the provided license
     * number.
     *
     * This method prompts the user to enter the license number of the vehicle to be
     * deleted.
     * If a valid license number is provided, it calls the {@code deleteVehicle}
     * method to perform the deletion.
     * If an empty string is provided, it prints an error message indicating invalid
     * input format.
     *
     * @param scnr The Scanner object for user input.
     */

    public static void deleteVehicleByInfo(final Scanner scnr) {
        System.out.println("Enter the license number to delete (e.g. 'XYCNS'): ");
        String input = scnr.next();

        // Check for empty input
        if (input.equals("")) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String licenseNum = input;

        // Delete the vehicle based on the provided license number
        deleteVehicle(licenseNum);
    }

    /**
     *
     * @param licenseNum
     */
    public static void assignVehicle(final String licenseNum, final int permitID) {
        try {
            statement.executeUpdate(
                    "UPDATE PERMIT SET PermitID = '" + permitID + "'WHERE LicenseNum = '" + licenseNum + "';");
        } catch (SQLException e) {
            System.out.println("Error message when assigning vehicle");
        }
    }

    public static void assignVehicleByInfo(final Scanner scnr) {
        System.out.println("Enter the license number and permit ID (e.g., 'XYNCS', 10): ");
        String input = scnr.nextLine();

        String[] parts = input.split(",");

        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the information in order separated by a comma.");
            return;
        }

        String licenseNum = parts[0].trim();
        String permitID = parts[1].trim();

        assignPermit(licenseNum, permitID);
    }

    // Task and operations 3: Generating and maintaining citations
    /**
     * Allows the user to manually enter citation information through the console
     * input.
     *
     * This method prompts the user to input information such as start date, lot
     * name, zone ID, space type,
     * category, license number, citation number, and citation time. It then
     * validates the input and calls
     * the {enterCitation} method to enter the citation with the provided
     * details.
     *
     * @param scanner The Scanner object to read user input from the console.
     */
    public static void enterCitationByInput(final Scanner scanner) {
        System.out.print(
                "Enter start date of Citation (YYYY-MM-DD), lot name, zoneID, spaceType, category, license number, citation number, citation time (separated by commas):\n");
        // Read user input
        String line = scanner.nextLine();

        // Extract input components
        String[] line_parts = line.split(",");
        String citationDate = line_parts[0].trim();
        String lotName = line_parts[1].trim();
        String zoneID = line_parts[2].trim();
        String spaceType = line_parts[3].trim();
        String category = line_parts[4].trim();
        String licenseNum = line_parts[5].trim();
        String citationNumber = line_parts[6].trim();
        String time = line_parts[7].trim();

        // Validate input length
        if (line_parts.length < 7) {
            System.out.println(
                    "Invalid input format. Please enter start date of Citation (YYYY-MM-DD), fee, lot name, category, license number, citation number, citation time (separated by commas).");
            return;
        }

        // Call the enterCitation method with the extracted details
        enterCitation(citationDate, "due", time, citationNumber, category, lotName, zoneID, spaceType, licenseNum);
    }

    public static double feeCalculation(final String category, final String licenseNum) {
        double fee = 0.0;

        boolean isHandicap = false;

        try {
            // Find all permits with the same license number
            ResultSet rs = statement
                    .executeQuery("SELECT SpaceType FROM Permit WHERE LicenseNum ='" + licenseNum + "';");

            int rowCount = 0;
            String tempSpaceType = "";
            String tempSpaceType2 = "";
            String tempSpaceType3 = "";
            while (rs.next() && rowCount < 3) {
                if (rowCount == 0) {
                    tempSpaceType = rs.getString("SpaceType");
                } else if (rowCount == 1) {
                    tempSpaceType2 = rs.getString("SpaceType");
                } else if (rowCount == 2) {
                    tempSpaceType3 = rs.getString("SpaceType");
                }
                rowCount++;
            }

            if (tempSpaceType.equalsIgnoreCase("handicap") || tempSpaceType2.equalsIgnoreCase("handicap")
                    || tempSpaceType3.equalsIgnoreCase("handicap")) {
                isHandicap = true;
            }

        } catch (SQLException e) {
            System.out.println("Error message during fee calcuation");
        }

        // Assigns the fee based off the category of the permit
        if (category.equalsIgnoreCase("No Permit")) {
            fee = 40.0;
        } else if (category.equalsIgnoreCase("Expired Permit")) {
            fee = 30.0;
        } else if (category.equalsIgnoreCase("Invalid Permit")) {
            fee = 25.0;
        }

        // 50% off discount for handicap users
        if (isHandicap) {
            fee = fee / 2;
        }

        return fee;
    }

    /**
     * Enters a parking citation into the system based on the provided information.
     *
     * This method checks for parking violations using the
     * {checkParkingViolation} method
     * and calculates the citation fee using the {feeCalculation} method. If
     * a parking violation
     * is detected, it inserts the citation information into the "Citation" table in
     * the database.
     *
     * @param citationDate   The start date of the citation in the format
     *                       YYYY-MM-DD.
     * @param paymentStatus  The payment status of the citation (e.g., "due" or
     *                       "paid").
     * @param citationTime   The time when the citation was issued.
     * @param citationNumber The unique identifier for the citation.
     * @param category       The category of the citation.
     * @param lotName        The name of the parking lot.
     * @param zoneID         The ID of the parking zone.
     * @param spaceType      The type of parking space.
     * @param licenseNum     The license number of the vehicle.
     */
    public static void enterCitation(final String CitationDate, final String PaymentStatus, final String CitationTime,
            final String CitationNumber, final String Category, final String LotName, String ZoneID,
            final String SpaceType,
            final String LicenseNum) {

        // Check for parking violation
        boolean parkingViolation = checkParkingViolation(CitationDate, CitationTime, LotName, SpaceType, ZoneID,
                LicenseNum);

        if (parkingViolation) {
            // Calculate citation fee
            double fee = feeCalculation(Category, LicenseNum);
            try {
                // Insert entry into Citation table
                statement.executeUpdate(
                        "INSERT INTO Citation (CitationDate, Fee, PaymentStatus, CitationTime, CitationNumber, Category, LotName, SpaceType, ZoneID, LicenseNum) VALUES ('"
                                + CitationDate + "'," + fee + ",'" + PaymentStatus + "','" + CitationTime + "','"
                                + CitationNumber + "','" + Category + "','" + LotName + "','" + SpaceType + "','"
                                + ZoneID
                                + "','" + LicenseNum
                                + "');");

            } catch (SQLException e) {
                System.out.println("Error message when entering citation");
            }

        } else {
            System.out.println("Citation cannot be generated because there is no violation");
        }
    }

    /**
     * Checks for parking violations for a vehicle based on the provided input.
     *
     * This method prompts the user to input information such as lot name, license
     * number, zone ID, space type,
     * current date, and current time. It then calls the
     * {lcheckParkingViolation} method to determine
     * if the vehicle has a parking violation.
     *
     * @param scanner The Scanner object to read user input from the console.
     */
    public static void checkParkingViolationByInput(final Scanner scanner) {
        System.out.println(
                "Enter lot name, license number, zone ID, space type, current date, current time (separated by commas): ");
        String line = scanner.nextLine();
        String[] line_parts = line.split(",");
        String lotName = line_parts[0].trim();
        String licenseNum = line_parts[1].trim();
        String zoneID = line_parts[2].trim();
        String spaceType = line_parts[3].trim();
        String citationDate = line_parts[4].trim();
        String citationTime = line_parts[5].trim();

        // Check for parking violation
        boolean violation = checkParkingViolation(lotName, licenseNum, zoneID, spaceType, citationDate, citationTime);
        System.out.println();

        if (violation) {
            System.out.println("This vehicle has a parking violation");

        } else {
            System.out.println("This vehicle does not have a parking violation");
        }

    }

    /**
     * Checks for parking violations for a vehicle based on the provided
     * information.
     *
     * This method queries the database to determine if the vehicle has a valid
     * parking permit for the specified
     * lot, zone, and space type, and if it is not expired at the given citation
     * date. If no permit is found,
     * or if the permit is not valid for the specified lot, zone, space type, or if
     * it is expired, a parking
     * violation is considered.
     *
     * @param lotName      The name of the parking lot.
     * @param licenseNum   The license number of the vehicle.
     * @param zoneID       The ID of the parking zone.
     * @param spaceType    The type of parking space.
     * @param citationDate The date when the citation is issued.
     * @param citationTime The time when the citation is issued.
     * @return True if there is a parking violation, false otherwise.
     */

    public static boolean checkParkingViolation(final String LotName, final String LicenseNum, final String ZoneID,
            final String SpaceType, final String CitationDate, final String CitationTime) {
        ResultSet rs;
        int count = 0;
        try {
            // Check if the car has any permit
            rs = statement.executeQuery("SELECT COUNT(*) FROM Permit WHERE LicenseNum ='" + LicenseNum + "';");
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                // Check if the car has a valid permit for the given lot, zone, and space type
                // and is not expired
                rs = statement
                        .executeQuery("SELECT COUNT(*) FROM Permit WHERE LotName = '" + LotName + "' AND ZoneID = '"
                                + ZoneID + "' AND SpaceType = '" + SpaceType + "' AND LicenseNum ='" + LicenseNum
                                + "' AND ExpDate >= '" + CitationDate + "';");

                if (rs.next()) {
                    count = rs.getInt(1);
                    if (count > 0) {
                        // No violation: permit is valid and not expired
                        return false;
                    }
                }
                // Violation: permit is not valid for the lot, zone, space type, or it is
                // expired
                return true;
            } else {
                // Violation: no permit found
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("SQL error: " + exception.getMessage());
            // Consider handling the exception more gracefully or rethrowing it
            return false;
        }
    }

    /**
     * Allows the user to pay for a citation based on the provided citation number.
     *
     * This method prompts the user to input the citation number they want to pay
     * for. It then calls
     * the {payCitation} method to process the payment for the specified
     * citation.
     *
     * @param scanner The Scanner object to read user input from the console.
     */

    public static void payCitationByInput(final Scanner scanner) {
        System.out.println("Enter the citation number to be paid for:");
        String citationNumber = scanner.nextLine();

        if (citationNumber == "") {
            System.out.println("Invalid input format. Please enter the citation number to be paid for.");
            return;
        }

        // Process payment for the specified citation
        payCitation(citationNumber);
    }

    /**
     * Processes the payment for a given citation number.
     *
     * This method checks the current payment status of the citation and updates to
     * "Paid"
     * if the payment is successful. It simulates a payment transaction, and in case
     * of a
     * successful payment, commits the transaction; otherwise, it rolls back the
     * transaction.
     *
     * @param citationNumber The unique identifier for the citation to be paid.
     */
    public static void payCitation(final String CitationNumber) {
        Random rand = new Random();
        boolean paid = false;

        // Checks to see if the citation is already paid for, or has already been
        // appealed
        try {
            ResultSet rs;
            rs = statement.executeQuery("SELECT PaymentStatus FROM Citation WHERE CitationNumber = '"
                    + CitationNumber + "';");

            String s = "";
            StringBuilder sb = new StringBuilder(s);

            if (rs.next()) {
                sb.append(rs.getString("PaymentStatus"));
            }

            if (sb.toString().equalsIgnoreCase("Paid") || sb.toString().equalsIgnoreCase("Appealed")) {
                paid = true;
            }

        } catch (SQLException e) {
            System.out.println("Error occured while getting current payment status");
        }

        // If the citation can be paid for, begins the transaction
        if (!paid) {
            try {
                // Start a transaction
                connection.setAutoCommit(false);

                // Driver attempts to make a payment for their citation
                statement.executeUpdate("Update Citation SET PaymentStatus = 'Paid'" + " WHERE CitationNumber = '"
                        + CitationNumber + "' AND PaymentStatus = 'Due'" + ";");

                // Simulate a check to see if the driver's payment goes through
                boolean successfulPayment = rand.nextBoolean();

                if (successfulPayment) {
                    // If payment is successful, commit the transaction to update
                    // payment status
                    connection.commit();
                    System.out.println("\nPayment Successful\n");
                } else {
                    // Rollback the transaction if the payment is unsuccessful
                    connection.rollback();
                    System.out.println("\nPayment Unsuccessful\n");
                }

            } catch (SQLException e) {
                System.out.println("Invalid Payment");
                try {
                    // Roll back the transaction in case of an error
                    connection.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException ex) {
                    System.out.println("Error occurred during rollback: ");
                }
            } finally {
                try {
                    // Reset auto-commit to its default state
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Error occurred while resetting auto-commit: ");
                }
            }

        } else {
            System.out.println(
                    "\nCitation cannot be paid for, payment already made for this citation or citation is appealed.\n");
        }
    }

    public static void requestAppealByInput(final Scanner scanner) {
        System.out.println("Enter the citation number you want to appeal for:");
        String citationNumber = scanner.nextLine();

        if (citationNumber == "") {
            System.out.println("Invalid input format. Please enter the citation number you want to appeal for.");
            return;
        }

        requestAppeal(citationNumber);
    }

    /**
     * Initiates an appeal request for a given citation number.
     *
     * This method checks the current payment status of the citation and initiates
     * an appeal
     * if the citation is not already appealed or paid. It simulates the appeal
     * process and
     * updates the payment status to "Appealed" if the appeal is successful.
     *
     * @param citationNumber The unique identifier for the citation to be appealed.
     */

    public static void requestAppeal(final String CitationNumber) {
        Random rand = new Random();
        boolean cannotBeAppealed = false;
        try {
            ResultSet rs;
            rs = statement.executeQuery("SELECT PaymentStatus FROM Citation WHERE CitationNumber = '"
                    + CitationNumber + "';");

            String s = "";
            StringBuilder sb = new StringBuilder(s);

            if (rs.next()) {
                sb.append(rs.getString("PaymentStatus"));
            }

            if (sb.toString().equalsIgnoreCase("Paid") || sb.toString().equalsIgnoreCase("Appealed")) {
                cannotBeAppealed = true;
            }

        } catch (SQLException e) {
            System.out.println("Error occured while getting current payment status");
        }

        if (!cannotBeAppealed) {
            // Simulate a check to see if the driver's payment goes through
            boolean successfulAppeal = rand.nextBoolean();

            if (successfulAppeal) {
                try {
                    // Citation gets appealed
                    statement.executeUpdate(
                            "Update Citation SET PaymentStatus = 'Appealed'" + " WHERE CitationNumber = '"
                                    + CitationNumber + "';");
                    System.out.println("\nCitation successfully appealed\n");
                } catch (SQLException e) {
                    System.out.println("Appeal Denied");
                }
            } else {
                System.out.println("Appeal Denied");
            }
        } else {
            System.out.println("\nCitation cannot be appealed, already appealed or paid\n");
        }
    }

    // REPORTS

    /**
     * Prompts the user to enter a start date and an end date, then retrieves
     * and displays a report of the total number of citations for each parking
     * lot within the specified time frame. The method uses a Scanner to read
     * the dates from the console and then queries the database to generate the
     * report.
     * 
     * @param scnr The Scanner object used for user input.
     * 
     */
    public static void getCitationReportByTime(final Scanner scnr) {
        System.out.println();
        try {
            // Prompt the user to enter the start and end dates for the report
            System.out.println("Enter the start and end dates for the report (StartDate, EndDate):");
            String input = scnr.nextLine();
            String[] dates = input.split(",");

            // Validate input format
            if (dates.length < 2) {
                System.out.println("Invalid input format. Please enter StartDate and EndDate separated by a comma.");
                return;
            }

            // Extract start and end dates from user input
            final String startDate = dates[0].trim();
            final String endDate = dates[1].trim();

            // Execute SQL query to retrieve citation report
            ResultSet rs = statement.executeQuery(
                    "SELECT p.LotName, COUNT(c.CitationNumber) AS TotalCitations FROM ParkingLot p LEFT JOIN Citation c ON p.LotName = c.LotName AND c.CitationDate BETWEEN '"
                            + startDate + "' AND '" + endDate + "' GROUP BY p.LotName");

            // Build a StringBuilder to format and store the citation report
            StringBuilder sb = new StringBuilder();

            // Iterate through the ResultSet and append results to the StringBuilder
            while (rs.next()) {
                sb.append(rs.getString("LotName")).append(": ");
                sb.append(rs.getString("TotalCitations")).append(" citations\n");
            }

            // Display the citation report
            System.out.println(sb.toString());
        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message: " + e.getMessage());
        }

    }

    /**
     * Prompts the user to enter the name of a parking lot and then retrieves
     * and displays a list of zones within that parking lot. The method uses a
     * Scanner to read the parking lot name from the console and then queries
     * the Zone table in the database to find the zones in the specified lot.
     * 
     * @param scnr The Scanner object used for user input.
     * 
     */
    public static void getZones(final Scanner scnr) {
        System.out.println();
        try {
            // Prompt the user to enter the name of the parking lot
            System.out.println("Enter the name of the parking lot to find its zones:");
            String lotName = scnr.nextLine().trim();

            // Execute SQL query to retrieve zones for the specified parking lot
            ResultSet rs = statement.executeQuery("SELECT * FROM Zone WHERE LotName = '" + lotName + "';");

            // Build a StringBuilder to format and store the zone information
            StringBuilder sb = new StringBuilder();

            // Iterate through the ResultSet and append results to the StringBuilder
            while (rs.next()) {
                sb.append("(" + rs.getString("LotName")).append(", ");
                sb.append(rs.getString("ZoneID") + ")").append("\n");

            }

            // Display the zones associated with the specified parking lot
            System.out.println(sb.toString());
        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message: " + e.getMessage());
        }

    }

    /**
     * 
     * Retrieves and displays the count of distinct license numbers with current
     * violations.
     *
     */
    public static void getCurrentViolations() {
        System.out.println();
        // Execute SQL query to count distinct license numbers with current violations;
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT COUNT(DISTINCT LicenseNum) AS Violations FROM Citation WHERE PaymentStatus = 'DUE';");

            // Initialize an empty string and StringBuilder for result storage and
            // formatting
            String s = "";
            StringBuilder sb = new StringBuilder(s);

            // Iterate through the ResultSet and append the count of current violations to
            // the StringBuilder
            while (rs.next()) {
                sb.append("Current Violations: " + rs.getString("Violations"));
                sb.append("\n");
            }
            // Display the count of distinct license numbers with current violations
            System.out.println(sb.toString());
        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message when getting current violation");
        }
    }

    /**
     * Retrieves and displays the count of employee permits for a specified zone and
     * parking lot.
     * 
     * @param scanner The Scanner object used for user input
     * 
     */
    public static void getEmployeeZone(Scanner scanner) {

        System.out.println("Enter the ZoneID and LotName of where to count employee permits:");

        // Read user input for ZoneID and LotName separated by commas
        String input = scanner.nextLine();
        String[] parts = input.split(",");

        // Validate input format
        if (parts.length < 2) {
            System.out
                    .println("Invalid input format. Please enter the zone id, and lot name separated by commas.");
            return;
        }

        // Extract ZoneID and LotName from user input
        String zoneID = parts[0].trim();
        String lotName = parts[1].trim();
        System.out.println();

        // Check if the provided ZoneID is valid
        if (zoneID.equalsIgnoreCase("A") || zoneID.equalsIgnoreCase("B") || zoneID.equalsIgnoreCase("C")
                || zoneID.equalsIgnoreCase("D")) {

            try {
                // Execute SQL query to count employee permits in the specified zone and parking
                // lot
                ResultSet rs = statement.executeQuery(
                        "SELECT COUNT(*) AS 'Employee Zones' FROM Permit JOIN Driver ON Permit.DriverID = Driver.DriverID "
                                +
                                "WHERE Permit.ZoneID = '" + zoneID + "' AND Permit.LotName = '" + lotName
                                + "' AND Driver.Status = 'E';");

                // Initialize an empty string and StringBuilder for result storage and
                // formatting
                String s = "";
                StringBuilder sb = new StringBuilder(s);

                // Iterate through the ResultSet and append the count of employee permits to the
                // StringBuilder
                while (rs.next()) {
                    sb.append("Number of Employee Permits: " + rs.getString("Employee Zones"));
                    sb.append("\n");
                }
                // Display the count of employee permits for the specified zone and parking lot
                System.out.println(sb.toString());
            } catch (SQLException e) {
                // Handle any SQL exception and display an error message
                System.out.println("Error message when getting employee zone");
            }
        } else {
            // Display the count of employee permits as 0 if the provided ZoneID is not
            // valid
            System.out.println("Number of Employee Permits: 0");
        }

    }

    /**
     * Prompts the user to enter the ID of a driver and then retrieves and
     * displays the permit information associated with that driver. The method
     * uses a Scanner to read the driver ID from the console and then queries
     * the Permit table in the database to find the relevant permit details.
     * 
     * @param scnr The Scanner object used for user input
     * 
     */
    public static void getPermitInformation(final Scanner scnr) {
        try {
            // Prompt the user to enter the driver ID to retrieve permit information
            System.out.println("Enter the driver ID to get their permit information:");
            String driverId = scnr.nextLine().trim();

            // Execute SQL query to retrieve permit information for the specified driver ID
            ResultSet rs = statement.executeQuery("SELECT * FROM Permit WHERE Permit.DriverId = '" + driverId + "';");
            StringBuilder sb = new StringBuilder();

            // Flag to track whether any data was retrieved
            boolean hasData = false;

            // Print a newline character to separate the input prompt and the result
            System.out.println("\n");
            // Iterate through the ResultSet and append permit information to the
            // StringBuilder
            while (rs.next()) {
                hasData = true;
                sb.append("Permit ID: ").append(rs.getString("PermitID")).append("\n");
                sb.append("Driver ID: ").append(rs.getString("DriverID")).append("\n");
                sb.append("License Number: ").append(rs.getString("LicenseNum")).append("\n");
                sb.append("Zone ID: ").append(rs.getString("ZoneID")).append("\n");
                sb.append("Lot Name: ").append(rs.getString("LotName")).append("\n");
                sb.append("Start Date: ").append(rs.getString("StartDate")).append("\n");
                sb.append("Expiration Date: ").append(rs.getString("ExpDate")).append("\n");
                sb.append("Expiration Time: ").append(rs.getString("ExpTime")).append("\n");
                sb.append("Space Type: ").append(rs.getString("SpaceType")).append("\n");
                sb.append("Permit Type: ").append(rs.getString("PermitType")).append("\n\n");
            }

            // Display permit information if data was retrieved; otherwise, show a message
            // indicating no information found
            if (hasData) {
                System.out.println(sb.toString());
            } else {
                System.out.println("No permit information found for Driver ID: " + driverId);
            }
        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to enter the name of a parking lot and then retrieves
     * and displays the number of available spaces within that parking lot. The
     * method uses a Scanner to read the parking lot name from the console and
     * then queries the Space table in the database to find available spaces.
     * 
     * @param scnr The Scanner object used for user input
     * 
     */
    public static void getAvailableSpaces(final Scanner scnr) {
        System.out.println(
                "Enter the name of the parking lot and the space type to find available spaces (e.g. 'Centennial', 'regular'): ");

        // Read user input for parking lot name and space type separated by commas
        String input = scnr.nextLine();
        String[] parts = input.split(",");

        // Validate input format
        if (parts.length < 2) {
            System.out.println("Invalid input format. Please enter the lot name, and space type separated by commas.");
            return;
        }

        // Extract parking lot name and space type from user input
        String lotName = parts[0].trim();
        String spaceType = parts[1].trim();

        System.out.println();
        try {
            // Execute SQL query to retrieve available space information for the specified
            // lot and space type
            ResultSet rs = statement.executeQuery("SELECT SpaceNumber From Space WHERE spaceType = '" + spaceType
                    + "' AND lotName = '" + lotName + "' AND AvailabilityStatus = 'available';");
            StringBuilder sb = new StringBuilder();

            // Check if there is available space and append the result to the StringBuilder
            if (rs.next()) {
                sb.append("Available Space Number: " + rs.getString("SpaceNumber"));
                sb.append("\n");
            }
            // Display available space information
            System.out.println(sb.toString());

        } catch (SQLException e) {
            // Handle any SQL exception and display an error message
            System.out.println("Error message: " + e.getMessage());
        }
    }

    /**
     * Initializes the database connection and resources.
     *
     * This method invokes the {@code connectToDatabase} method to establish a
     * connection to the database.
     */
    private static void initialize() {
        try {
            connectToDatabase();
            // createAllTables();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to the database using the MariaDB JDBC driver.
     *
     * This method loads the MariaDB JDBC driver, establishes a connection to the
     * database using the provided
     * JDBC URL, username, and password. It also initializes the statement for
     * executing SQL queries.
     *
     * @throws ClassNotFoundException if the MariaDB JDBC driver class is not found.
     * @throws SQLException           if a database access error occurs or the URL
     *                                is null.
     */
    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        String user = "oapatel2";
        String password = "200404428";

        connection = DriverManager.getConnection(jdbcURL, user, password);
        statement = connection.createStatement();

    }

    /**
     * Closes the database connection, statement, and result set if they are not
     * null.
     * This method is responsible for closing the database connection, statement,
     * and result set
     * to ensure proper resource management.
     * Any SQLException that occurs during the closing process is printed to the
     * standard error stream.
     *
     */

    private static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Preloads the database with demo data for testing purposes.
     *
     * This method drops the existing schema if it exists, creates a new schema
     * and sets it as the current schema. It then creates all necessary tables for
     * the parking system
     * using the `createAllTables` method and populates them with dummy data using
     * the `populateTables` method.
     *
     */

    private static void preloadDemoData() {
        try {
            statement.executeUpdate("drop schema oapatel2;");
            statement.executeUpdate("create schema oapatel2;");
            statement.executeUpdate("use oapatel2;");
        } catch (SQLException e) {
            System.out.println("Error when loading demo data");
        }

        createAllTables();
        populateTables();
    }

    /**
     * Creates all necessary tables for the parking system in the database.
     *
     * This method executes SQL statements to create tables for drivers, parking
     * lots, vehicles, zones, spaces,
     * permits, and citations in the parking system. It sets up the structure of the
     * database to store relevant information.
     * The tables are created with primary keys, foreign keys, and appropriate
     * constraints.
     */
    private static void createAllTables() {

        try {
            // Creating Driver table
            statement.executeUpdate("CREATE TABLE Driver (" + " DriverID VARCHAR(20) NOT NULL,"
                    + "Name VARCHAR(36) NOT NULL," + " Status VARCHAR(1) NOT NULL CHECK (Status IN ('S', 'E', 'V')),"
                    + " PRIMARY KEY (DriverID)" + ");");

            // Creating ParkingLot table
            statement.executeUpdate("CREATE TABLE ParkingLot (" + " LotName VARCHAR(128) NOT NULL,"
                    + " Address VARCHAR(128) NOT NULL," + " PRIMARY KEY (LotName)" + ");");

            // Creating Vehicle table
            statement.executeUpdate("CREATE TABLE Vehicle (" + " LicenseNum VARCHAR(128) NOT NULL,"
                    + " Year VARCHAR(4)," + " Model VARCHAR(20)," + " Color VARCHAR(20)," + " Manf VARCHAR(20),"
                    + " PRIMARY KEY (LicenseNum)" + ");");

            // Creating Zone table
            statement.executeUpdate("CREATE TABLE Zone ("
                    + " ZoneID VARCHAR(2) NOT NULL CHECK (ZoneID IN ('A', 'B', 'C', 'D', 'AS', 'BS', 'CS', 'DS', 'V')),"
                    + " LotName VARCHAR(128) NOT NULL," + " PRIMARY KEY (ZoneID, LotName),"
                    + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ");");

            // Creating Space table
            statement.executeUpdate("CREATE TABLE Space (" + " SpaceNumber INTEGER NOT NULL,"
                    + " LotName VARCHAR(128) NOT NULL,"
                    + " SpaceType VARCHAR(20) NOT NULL CHECK (SpaceType IN ('electric', 'handicap', 'compact car', 'regular')),"
                    + " AvailabilityStatus VARCHAR(20) NOT NULL CHECK (AvailabilityStatus IN ('available', 'occupied')),"
                    + " PRIMARY KEY (SpaceNumber, LotName),"
                    + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ");");

            // Creating Permit table
            statement.executeUpdate("CREATE TABLE Permit (" + " PermitID VARCHAR(20) NOT NULL,"
                    + " DriverID VARCHAR(20) NOT NULL, LicenseNum VARCHAR(20) NOT NULL, ZoneID VARCHAR(2) NOT NULL, LotName VARCHAR(128),"
                    + " StartDate DATE NOT NULL," + " ExpDate DATE NOT NULL," + " ExpTime TIME NOT NULL,"
                    + " SpaceType VARCHAR(20) NOT NULL CHECK (SpaceType IN ('electric', 'handicap', 'compact car', 'regular')),"
                    + " PermitType VARCHAR(20) NOT NULL CHECK (PermitType IN ('residential', 'commuter', 'peak hours', 'special event', 'Park & Ride')),"
                    + " PRIMARY KEY (PermitID),"
                    + " FOREIGN KEY(DriverID) REFERENCES Driver (DriverID) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + " FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (LotName, ZoneID) REFERENCES Zone(LotName, ZoneID) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ");");

            // Creating Citation table
            statement.executeUpdate("CREATE TABLE Citation (" + "CitationNumber VARCHAR(32) NOT NULL, "
                    + "LotName VARCHAR(128) NOT NULL, " + "ZoneID VARCHAR(2) NOT NULL, "
                    + "SpaceType VARCHAR(20) NOT NULL CHECK (SpaceType IN ('electric', 'handicap', 'compact car', 'regular')),"
                    + "LicenseNum VARCHAR(128) NOT NULL, " + "CitationDate DATE NOT NULL, " + "Fee DOUBLE, "
                    + "PaymentStatus VARCHAR(32) NOT NULL CHECK (PaymentStatus IN ('paid', 'due', 'appealed')), "
                    + "CitationTime TIME NOT NULL, " + "Category VARCHAR(128) NOT NULL, "
                    + "CHECK ((Category = 'Invalid Permit' AND Fee = 25) OR "
                    + "(Category = 'Expired Permit' AND Fee = 30) OR " + "(Category = 'No Permit' AND Fee = 40) OR "
                    + "(Category = 'Invalid Permit' AND Fee = 12.5) OR "
                    + "(Category = 'Expired Permit' AND Fee = 15) OR " + "(Category = 'No Permit' AND Fee = 20)), "
                    + "PRIMARY KEY (CitationNumber), "
                    + "FOREIGN KEY (LotName, ZoneID) REFERENCES Zone (LotName, ZoneID) ON UPDATE CASCADE ON DELETE CASCADE, "
                    + "FOREIGN KEY (LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ");");

        } catch (SQLException e) {
            System.out.println("Error creating tables");
        }
    }

    /**
     * Populates the parking system tables with dummy data for testing purposes.
     *
     * This method initializes data for drivers, lots, vehicles, zones, permits, and
     * citations in
     * the parking system. It is intended for testing and demonstration purposes.
     */
    private static void populateTables() {

        // DRIVERS
        enterDriver("7729119111", "V", "Sam BankmanFried");
        enterDriver("266399121", "E", "John Clay");
        enterDriver("366399121", "E", "Julia Hicks");
        enterDriver("466399121", "E", "Ivan Garcia");
        enterDriver("122765234", "S", "Sachin Tendulkar");
        enterDriver("9194789124", "V", "Charles Xavier");

        // LOTS
        enterLot("Poulton Deck", "1021 Main Campus Dr, Raleigh, NC, 27606");
        enterLot("Partners Way Deck", "851 Partners Way, Raleigh, NC, 27606");
        enterLot("Dan Allen Parking Deck", "110 Dan Allen Dr, Raleigh, NC, 27607");

        // VEHICLES
        enterVehicle("SBF", "2024", "GT-R-Nismo", "Pearl White TriCoat", "Nissan");
        enterVehicle("Clay1", "2023", "Model S", "Ultra Red", "Tesla");
        enterVehicle("Hicks1", "2024", "M2 Coupe", "Zandvoort Blue", "BMW");
        enterVehicle("Garcia1", "2024", "Continental GT Speed", "Blue Fusion", "Bentley");
        enterVehicle("CRICKET", "2024", "Civic SI", "Sonic Gray Pearl", "Honda");
        enterVehicle("PROFX", "2024", "Taycan Sport Turismo", "Frozenblue Metallic", "Porsche");
        // dummy extra data
        enterVehicle("VAN-9910", null, "Macan GTS", "Papaya Metallic", null);

        // Zones
        enterZone("V", "Poulton Deck");
        enterZone("A", "Partners Way Deck");
        enterZone("AS", "Dan Allen Parking Deck");

        // PERMITS
        enterPermitInfo("VSBF1C", "Commuter", "V", "Poulton Deck", "7729119111", "SBF", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00");
        enterPermitInfo("EJC1R", "Residential", "A", "Partners Way Deck", "266399121", "Clay1", "Electric",
                "2010-01-01", "2030-01-01", "06:00:00");
        enterPermitInfo("EJH2C", "Commuter", "A", "Partners Way Deck", "366399121", "Hicks1", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00");
        enterPermitInfo("EIG3C", "Commuter", "A", "Partners Way Deck", "466399121", "Garcia1", "Regular", "2023-01-01",
                "2024-01-01", "06:00:00");
        enterPermitInfo("SST1R", "Residential", "AS", "Dan Allen Parking Deck", "122765234", "CRICKET", "Compact Car",
                "2022-01-01", "2023-09-30", "06:00:00");
        enterPermitInfo("VCX1SE", "Special event", "V", "Poulton Deck", "9194789124", "PROFX", "Handicap",
                "2023-01-01", "2023-11-15", "06:00:00");

        // CITATION
        enterCitation("2024-01-01", "PAID", "08:00:00", "NP1", "No Permit", "Dan Allen Parking Deck", "AS", "Regular",
                "VAN-9910");
        enterCitation("2023-10-01", "DUE", "08:00:00", "EP1", "Expired Permit", "Poulton Deck", "V", "Compact Car",
                "CRICKET");

    }

}
