package edu.ncsu.csc540.WolfParkingApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;

public class WolfParkingApplication {

	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/stithi";

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;

	public static void main(final String[] args) {

		System.out.println("Welcome to the WolfParking Managment System!");

		initialize();
		close();
	}

	// INFORMATION PROCESSING:

	/**
	 * Creates a new entry in the driver table with the given driver information.
	 *
	 * @param driverID the driver's ID
	 * @param status   the driver's status
	 * @param name     the name of the driver
	 */
	public static void enterDriver(final int driverID, final String status, final String name) {
		try {
			// Inserting data into Driver table
			statement.executeUpdate("INSERT INTO Driver (DriverID, Name, Status) VALUES" + "(" + driverID + "," + name
					+ "," + status + ");");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Retrieves a driver entry with the matching ID from the parameter driverID and
	 * updates the status and/or name attributes for the matching entry.
	 *
	 * @param driverID the ID of the driver entry to be updated
	 * @param status   the updated status of the driver entry to be updated
	 * @param name     the updated name of the driver entry to be updated
	 */
	public static void updateDriverInformation(final int driverID, final String status, final String name) {
		try {
			// Update driver entry with the matching driverID
			statement.executeUpdate("UPDATE Driver SET Name=" + name + "," + " SET Status=" + status + ","
					+ "WHERE Driver.DriverID=" + driverID + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Deletes a driver entry with the matching ID from the parameter driverID
	 *
	 * @param driverID the ID of the driver entry to be deleted
	 */
	public static void deleteDriver(final int driverID) {
		try {
			// Delete driver entry with the matching driverID
			statement.executeUpdate("DELETE FROM Driver WHERE DriverID=" + driverID + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Creates a new entry for the ParkingLot table with the given Parking Lot
	 * information.
	 *
	 * @param lotName the name of the lot to be created
	 * @param address the address of the lot to be created
	 */
	public static void enterLot(final String lotName, final String address) {
		try {
			// Inserting data into the ParkingLot table
			statement.executeUpdate(
					"INSERT INTO ParkingLot (LotName, Address) VALUES (" + lotName + "," + address + ");");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	// TODO

	// CAN YOU UPDATE PRIMARY KEYS?

	/**
	 * Updates an existing entry in the ParkingLot entry with the matching Parking
	 * Lot name.
	 *
	 * @param lotName the name of the entry to be updated
	 * @param address the new address of the parking lot
	 */
	public static void updateLotInformation(final String lotName, final String address) {
		try {
			// Updating ParkingLot entry with the matching lot name.
			statement.executeUpdate("UPDATE ParkingLot SET Address=" + address + " WHERE LotName = " + lotName + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Deletes a ParkingLot entry with the matching lot name
	 *
	 * @param lotName the name of the Parking Lot to be deleted
	 */
	public static void deleteLot(final String lotName) {
		try {
			// Deletes ParkingLot entry with the matching lot name.
			statement.executeUpdate("DELETE FROM ParkingLot WHERE LotName= " + lotName + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	/**
	 * Creates a new Zone entry with the given zone information
	 *
	 * @param zoneID  the ID of the zone to be created
	 * @param lotName the name of the Parking Lot that the new zone will exist in
	 */
	public static void enterZone(final String zoneID, final String lotName) {
		try {
			// Inserting into Zone table
			statement.executeUpdate("INSERT INTO Zone (ZoneID, LotName) VALUES (" + zoneID + "," + lotName + ");");
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	// TODO
	// CAN YOU UPDATE PRIMARY KEYS?
	// FOR NOW IM GOING TO SAY WE CAN UPDATE THE PARKING LOT
	/**
	 * Updates an existing zone entry with the given parking lot name
	 *
	 * @param zoneID  the ID of the zone to be updated
	 * @param lotName the new parking lot where the existing zone will exist ins
	 */
	public static void updateZone(final String zoneID, final String lotName) {
		try {
			// Updating Zone entry with existing ID
			statement.executeUpdate("UPDATE Zone SET LotName=" + lotName + "WHERE ZoneID =" + zoneID + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	/**
	 * Deletes an existing Zone entry with the matching zoneID
	 *
	 * @param zoneID the ID of the zone entry to be deleted
	 */
	public static void deleteZone(final String zoneID) {
		try {
			// Deletes Zone entry with existing ID
			statement.executeUpdate("DELETE FROM Zone WHERE ZoneID=" + zoneID + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	/**
	 * Creates a new Space entry with the given space information.
	 *
	 * @param spaceNumber        the number of the space to be created
	 * @param lotName            the name of the Parking Lot where the new space
	 *                           will exist
	 * @param spaceType          the type of the space to be created
	 * @param availabilityStatus the status of the space to be created
	 */
	public static void enterSpace(final int spaceNumber, final String lotName, final String spaceType,
			final String availabilityStatus) {
		try {
			// Insert entry into Space table
			statement.executeUpdate("INSERT INTO Space (SpaceNumber, LotName, SpaceType, AvailabilityStatus) VALUES ("
					+ spaceNumber + "," + lotName + "," + spaceType + "," + availabilityStatus + ");");

		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	// TODO

	/**
	 *
	 * @param spaceNumber        the number of Space to be updated
	 * @param lotName            the
	 * @param spaceType
	 * @param availabilityStatus
	 */
	public static void updateSpace(final int spaceNumber, final String lotName, final String spaceType,
			final String availabilityStatus) {
		// FINISH
	}

	/**
	 * Deletes the Space entry with the matching space number.
	 *
	 * @param spaceNumber the number of the Space to be deleted
	 */
	public static void deleteSpace(final int spaceNumber) {

		try {
			// Delete entry from Space table with matching space number
			statement.executeUpdate("DELETE FROM Space WHERE SpaceNumber =" + spaceNumber + ";");

		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	// Task and operations: Maintaining permits and vehicle information for each
	// driver

	/**
	 * Returns the name and status of the driver with the specified DriverID.
	 * 
	 * @param driverID the ID of the driver to return
	 */
	public static void getDriverInfo(final String driverID) {
		// not sure how to do this
		// have to return information about the driver
	}

	/**
	 * Adds a permit with the specified parameters, including the permit ID, the
	 * type of the space, the lot name, and the zone ID. In addition, the driver and
	 * vehicle information and the start and expiration date is specified.
	 * 
	 * @param permitID   ID of the permit to add
	 * @param spaceType  type of the space for the permit
	 * @param startDate  start date of the permit
	 * @param expDate    expiration date of the permit
	 * @param driverID   ID of the driver of the permit
	 * @param permitType type of the permit
	 * @param expTime    expiration time of the permit
	 * @param licenseNum license number of the vehicle for the permit
	 * @param zoneID     zone ID of the permit
	 * @param lotName    lot name of the permit
	 * 
	 */
	public static void enterPermitInfo(final String permitID, final String spaceType, final String startDate,
			final String expDate, final String driverID, final String permitType, final String expTime,
			final String licenseNum, final String zoneID, final String lotName) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date startDateFormatted;
		java.util.Date startParser;

		java.util.Date expParser;
		java.sql.Date expDateFormatted;

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		java.util.Date timeParser;
		java.sql.Time sqlTime;

		try {
			startParser = dateFormat.parse(startDate);
			startDateFormatted = new java.sql.Date(startParser.getTime());
			expParser = dateFormat.parse(expDate);
			expDateFormatted = new java.sql.Date(expParser.getTime());

			timeParser = timeFormat.parse(expTime);
			sqlTime = new java.sql.Time(timeParser.getTime());

			// Enter the permit info into the permits table
			statement.executeUpdate(
					"INSERT INTO Permit (PermitID, SpaceType, StartDate, ExpDate, DriverID, PermitType, ExpTime, LicenseNum, ZoneID, LotName) VALUES ("
							+ permitID + ", " + spaceType + ", " + startDateFormatted + ", " + expDateFormatted + ", "
							+ driverID + ", " + permitType + ", " + sqlTime + ", " + licenseNum + ", " + zoneID + ", "
							+ lotName + ");");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	// CAN YOU UPDATE PRIMARY KEYS?
	/**
	 * Updates an existing permit with the permit id, space type, start date,
	 * expiration date, driver id, permit type, expiration time, license number,
	 * zone id, or the lot name.
	 *
	 * @param permitID   ID of the permit
	 * @param spaceType  type of the space for the permit
	 * @param startDate  start date of the permit
	 * @param expDate    expiration date of the permit
	 * @param driverID   ID of the driver of the permit
	 * @param permitType type of the permit
	 * @param expTime    expiration time of the permit
	 * @param licenseNum license number of the vehicle for the permit
	 * @param zoneID     zone ID of the permit
	 * @param lotName    lot name of the permit
	 */
	public static void updatePermit(final String permitID, final String spaceType, final String startDate,
			final String expDate, final String driverID, final String permitType, final String expTime,
			final String licenseNum, final String zoneID, final String lotName) {

		// can you update the permit id
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date startDateFormatted;
		java.util.Date startParser;

		java.util.Date expParser;
		java.sql.Date expDateFormatted;

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		java.util.Date timeParser;
		java.sql.Time sqlTime;

		try {
			startParser = dateFormat.parse(startDate);
			startDateFormatted = new java.sql.Date(startParser.getTime());
			expParser = dateFormat.parse(expDate);
			expDateFormatted = new java.sql.Date(expParser.getTime());

			timeParser = timeFormat.parse(expTime);
			sqlTime = new java.sql.Time(timeParser.getTime());

			// Update the permit with the specified permit ID
			// can you update the permit ID

			statement.executeUpdate("UPDATE Permit SET SpaceType=" + spaceType + "," + " SET SpaceType=" + spaceType
					+ "," + " SET StartDate=" + startDateFormatted + "," + " SET ExpDate=" + expDateFormatted + ","
					+ " SET DriverID=" + driverID + "," + " SET PermitType=" + permitType + "," + " SET ExpTime="
					+ sqlTime + "," + " SET ExpTime=" + expTime + "," + " SET LicenseNum=" + licenseNum + ","
					+ " SET ZoneID=" + zoneID + "," + " SET LotName=" + lotName + "WHERE Permit.PermitID=" + permitID
					+ ";");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	/**
	 * Deletes a permit with the given permit ID.
	 * 
	 * @param permitID id of permit to delete
	 */
	public static void deletePermit(final String permitID) {

		try {
			// Delete entry from Space table with matching space number
			statement.executeUpdate("DELETE FROM Permit WHERE PermitId =" + permitID + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Assigns the specified permit to the given driver ID.
	 * 
	 * @param permitID
	 */
	public static void assignPermit(final String driverID, final String permitID) {
		try {
			// Delete entry from Space table with matching space number
			statement.executeUpdate("UPDATE PERMIT SET DriverID = " + driverID + "WHERE PermitID = " + permitID + ";");

		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Returns the name and status of permit the with the specified permitID.
	 * 
	 * @param permitID the ID of the permit to return
	 */
	public static void getPermitInfo(final String permitID) {
		// not sure how to do this
		// have to return information about the permit
	}

	/**
	 * Enters a vehicle into the database with a specified license number, year,
	 * model, color, and manufacturer.
	 * 
	 * @param licenseNum license number of vehicle
	 * @param year       year of the vehicle
	 * @param model      model of the vehicle
	 * @param color      color of the vehicle
	 * @param manf       manf of the vehicle
	 */
	public static void enterVehicle(final String licenseNum, final String year, final String model, final String color,
			final String manf) {

		try {
			statement.executeUpdate("INSERT INTO Vehicle (LicenseNum, Year, Model, Color, Manf) VALUES (" + licenseNum
					+ ", " + year + ", " + model + ", " + color + ", " + manf + ");");

		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	public static void updateVehicle(final String licenseNum, final String year, final String model, final String color,
			final String manf) {
		try {
			statement.executeUpdate("INSERT INTO Vehicle (LicenseNum, Year, Model, Color, Manf) VALUES (" + licenseNum
					+ ", " + year + ", " + model + ", " + color + ", " + manf + ");");

			statement
					.executeUpdate("UPDATE Vehicle SET Year=" + year + "," + " SET Model=" + model + "," + " SET Color="
							+ color + "," + " SET Manf=" + manf + "WHERE Vehicle.LicenseNum=" + licenseNum + ";");

		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	/**
	 * Deletes a vehicle with the given license plate number
	 * 
	 * @param licenseNum license plate number of the vehicle to delete
	 */
	public static void deleteVehicle(final String licenseNum) {
		try {
			statement.executeUpdate("DELETE FROM Permit WHERE LicenseNum =" + licenseNum + ";");

		} catch (SQLException e) {
			System.out.println("Error message");
		}

	}

	// TODO
	/**
	 *
	 * public static void assignZoneToLot()
	 *
	 * public static void assignSpaceToLot()
	 *
	 * public static void assignTypeToSpace()
	 *
	 * public static void updatePaymentStatus()
	 *
	 **/

	// Task and operations 3: Generating and maintaining citations
	/**
	 * Creates a new Citation entry with the given citation information.
	 * 
	 * @param CitationDate   the date when the citation is created
	 * @param Fee            fee ($25 for category “Invalid Permit,” $30 for
	 *                       category “Expired Permit,” $40 for category “No
	 *                       Permit”, but handicap users will receive a 50% discount
	 *                       on all citation fees)
	 * @param PaymentStatus  the status (if the driver has paid for the citation) of
	 *                       the citation to be created values can be {paid,
	 *                       unpaid}, which can be updated by a payment procedure
	 * @param CitationTime   the date when the citation is created
	 * @param CitationNumber the number of the Citation to be created
	 * @param Category       the category of the Citation to be created
	 * @param LotName        the name of the parking lot where the vehicle relevant
	 *                       to the citation exists
	 * @param LicenseNum     the license number of the vehicle relevant to the
	 *                       citation
	 */
	public static void enterCitation(final Date CitationDate, final double Fee, final String PaymentStatus,
			final Time CitationTime, final int CitationNumber, final String Category, final String LotName,
			final String LicenseNum) {
		try {
			// Insert entry into Citation table
			statement.executeUpdate(
					"INSERT INTO Citation (CitationDate, Fee, PaymentStatus, CitationTime, CitationNumber, Category, LotName, LicenseNum) VALUES ("
							+ CitationDate + "," + Fee + ",\"" + PaymentStatus + "\"," + CitationTime + ","
							+ CitationNumber + ",\"" + Category + "\",\"" + LotName + "\",\"" + LicenseNum + "\");");

		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Updates an existing Citation entry with the given citation category and fee
	 *
	 * @param CitationNumber the ID of the citation to be updated
	 * @param Category       the new category for the updated citation entry
	 * @param Fee            the new fee for the updated citation entry
	 */
	public static void updateCitation(final int CitationNumber, final String Category, final double Fee) {
		try {
			// Updating Citation entry with existing CitationNumber
			statement.executeUpdate("Update Citation SET Fee = " + Fee + ", Category = \"" + Category
					+ "\" WHERE CitationNumber = " + CitationNumber + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Deletes the Citation entry with the matching CitationNumber.
	 *
	 * @param CitationNumber the number of the Citation to be deleted
	 */
	public static void deleteCitation(final int CitationNumber) {
		try {
			// Delete entry from Citation table with matching CitationNumber
			statement.executeUpdate("DELETE FROM Citation WHERE CitationNumber =" + CitationNumber + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Checks if a vehicle has a valid permit
	 * 
	 * @param LotName    the name of the parking lot where the vehicle relevant to
	 *                   the citation exists
	 * @param LicenseNum the license number of the vehicle relevant to the citation
	 * @param ExpDate    the date when the permit will expire
	 * @param ExpTime    the time of the day when the permit will expire
	 */
	public static void checkParkingViolation(final String LotName, final String LicenseNum, final Date ExpDate,
			final Time ExpTime) {
		try {
			// Get permit id from Permit table with matching LicenseNum
			statement.executeUpdate(
					"SELECT PermitID FROM Permit WHERE LicenseNum = \"" + LicenseNum + "\" AND LotName = \"" + LotName
							+ "\" AND ExpDate >= " + ExpDate + "  AND ExpTime >= " + ExpTime + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Updates Fee as 0 and Payment status as "Paid" in Citation table for a given
	 * CitationNumber
	 * 
	 * @param CitationNumber the number of the Citation to be created
	 */
	public static void payCitation(final int CitationNumber) {
		try {
			// Updating Citation entry with existing CitationNumber
			statement.executeUpdate("Update Citation SET Fee =  0 AND PaymentStatus = \"Paid\""
					+ " WHERE CitationNumber = " + CitationNumber + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	/**
	 * Updates Fee as 0 and Payment status as "In Appeal" in Citation table for a
	 * given CitationNumber
	 * 
	 * @param CitationNumber the number of the Citation to be created
	 */
	public static void requestAppeal(final int CitationNumber) {
		try {
			// Updating Citation entry with existing CitationNumber
			statement.executeUpdate("Update Citation SET Fee =  0 AND PaymentStatus = \"In Appeal\""
					+ " WHERE CitationNumber = " + CitationNumber + ";");
		} catch (SQLException e) {
			System.out.println("Error message");
		}
	}

	private static void initialize() {
		try {
			connectToDatabase();

			// // Creating Security table
			// statement.executeUpdate(
			// "CREATE TABLE Security (" + " SecurityID INTEGER," + " PRIMARY KEY
			// (SecurityID)" + ");" );

			// // Creating Driver table
			// statement.executeUpdate(
			// "CREATE TABLE Driver (" + " DriverID VARCHAR(20) NOT NULL," + " Name
			// VARCHAR(36) NOT NULL,"
			// + " Status VARCHAR(1) NOT NULL," + " PRIMARY KEY (DriverID)" + ");" );

			// // Creating ParkingLot table
			// statement.executeUpdate( "CREATE TABLE ParkingLot (" + " LotName VARCHAR(128)
			// NOT NULL,"
			// + " Address VARCHAR(128) NOT NULL," + " PRIMARY KEY (LotName)" + ");" );

			// // Creating Vehicle table
			// statement.executeUpdate( "CREATE TABLE Vehicle (" + " LicenseNum VARCHAR(128)
			// NOT NULL,"
			// + " Year VARCHAR(4)," + " Model VARCHAR(20)," + " Color VARCHAR(20),"
			// + " Manf VARCHAR(20)," + " PRIMARY KEY (LicenseNum)" + ");" );

			// // Creating Zone table
			// statement.executeUpdate( "CREATE TABLE Zone (" + " ZoneID VARCHAR(2) NOT
			// NULL,"
			// + " LotName VARCHAR(128) NOT NULL," + " PRIMARY KEY (ZoneID, LotName),"
			// + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" +
			// ");" );

			// // Creating Space table
			// statement.executeUpdate( "CREATE TABLE Space (" + " SpaceNumber INTEGER NOT
			// NULL,"
			// + " LotName VARCHAR(128) NOT NULL," + " SpaceType VARCHAR(20) NOT NULL,"
			// + " AvailabilityStatus VARCHAR(20) NOT NULL," + " PRIMARY KEY (SpaceNumber,
			// LotName),"
			// + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE" +
			// ");" );

			// // Creating Permit table
			// statement.executeUpdate( "CREATE TABLE Permit (" + " PermitID INTEGER NOT
			// NULL,"
			// + " DriverID VARCHAR(20) NOT NULL, LicenseNum VARCHAR(20) NOT NULL, ZoneID
			// VARCHAR(2) NOT NULL, LotName VARCHAR(20),"
			// + " StartDate DATE NOT NULL," + " ExpDate DATE NOT NULL," + " ExpTime TIME
			// NOT NULL,"
			// + " SpaceType VARCHAR(20) NOT NULL," + " PermitType VARCHAR(20) NOT NULL,"
			// + " PRIMARY KEY (PermitID),"
			// + " FOREIGN KEY(DriverID) REFERENCES Driver (DriverID) ON UPDATE CASCADE ON
			// DELETE CASCADE,"
			// + " FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE
			// ON DELETE CASCADE,"
			// + " FOREIGN KEY(ZoneID) REFERENCES Zone (ZoneID) ON UPDATE CASCADE ON DELETE
			// CASCADE,"
			// + " FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE ON
			// DELETE CASCADE"
			// + ");" );

			// // Creating Citation table
			// statement.executeUpdate( "CREATE TABLE Citation ("
			// + "CitationNumber INTEGER NOT NULL, LotName VARCHAR(128) NOT NULL, LicenseNum
			// VARCHAR(128) NOT NULL,"
			// + "CitationDate DATE NOT NULL, Fee DOUBLE,"
			// + "PaymentStatus VARCHAR(32) NOT NULL, CitationTime TIME NOT NULL,"
			// + "Category VARCHAR(128) NOT NULL, PRIMARY KEY(CitationNumber),"
			// + "FOREIGN KEY(LotName) REFERENCES ParkingLot (LotName) ON UPDATE CASCADE,"
			// + "FOREIGN KEY(LicenseNum) REFERENCES Vehicle (LicenseNum) ON UPDATE CASCADE"
			// + ");" );

			// // Creating Maintain table
			// statement.executeUpdate( "CREATE TABLE Maintain (" + " CitationNumber INTEGER
			// NOT NULL,"
			// + " SecurityID INTEGER NOT NULL," + " PRIMARY KEY (CitationNumber,
			// SecurityID),"
			// + " FOREIGN KEY(CitationNumber) REFERENCES Citation (CitationNumber) ON
			// UPDATE CASCADE,"
			// + " FOREIGN KEY(SecurityID) REFERENCES Security (SecurityID) ON UPDATE
			// CASCADE" + ");" );

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");

		String user = "stithi";
		String password = "200475434";

		connection = DriverManager.getConnection(jdbcURL, user, password);
		statement = connection.createStatement();

	}

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

}
