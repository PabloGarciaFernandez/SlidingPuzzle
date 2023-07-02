package logic.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the logger of the app.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-05-15
 */
public final class Logger {
	public static final String ERROR = " [ERROR] -> ";
	public static final String RUNNING = " [RUNNING] -> ";
	public static final String INFO = " [INFO] -> ";
	private static Logger instance;
	private String path;

	/**
	 * The constructor of class
	 */
	private Logger() {
		String folderPath = "logs/";
		String fileName = "LOG-T7-G26-" + getFormattedDateTime("file") + ".txt";
		this.path = folderPath + fileName;
		createLog(this.path);
	}

	/**
	 * The instance of the class
	 * 
	 * @return instance
	 */
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	/**
	 * Creates the log file
	 * 
	 * @param filePath The path of the file to create
	 */
	private void createLog(String filePath) {
		try {
			Files.createDirectories(Paths.get("logs"));
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.close();
			System.out.println("File '" + filePath + "' created successfully.");
		} catch (IOException e) {
			Logger.getInstance().log(Logger.ERROR, e.getMessage());
		}
	}

	/**
	 * Method that returns a datetime depending on the type
	 * 
	 * @param type type of the file
	 * @return the date
	 */
	private String getFormattedDateTime(String type) {
		SimpleDateFormat dateFormat = null;
		if (type.equals("file")) {
			dateFormat = new SimpleDateFormat("d-EEE-yyyy--HH-mm-ss");
		} else if (type.equals("log")) {
			dateFormat = new SimpleDateFormat("dd/MM/yyyy--HH:mm:ss");
		}

		if (dateFormat != null) {
			return dateFormat.format(new Date());
		}
		return null;
	}

	/**
	 * Logs the process
	 * 
	 * @param type    The type of the messages (Error, Info or Running)
	 * @param message The message to log
	 */
	public void log(String type, String message) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(this.path, true);
			fileWriter.write(getFormattedMessage(type, message) + "\n");
		} catch (IOException e) {
			Logger.getInstance().log(Logger.ERROR, e.getMessage());
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					Logger.getInstance().log(Logger.ERROR, e.getMessage());
				}
			}
		}
	}

	/**
	 * Gets the message formatted to write in the log
	 * 
	 * @param type    The type of the messages (Error, Info or Running)
	 * @param message The message to log
	 * @return message formatted
	 */
	private String getFormattedMessage(String type, String message) {
		return getFormattedDateTime("log") + type + message;
	}
}
