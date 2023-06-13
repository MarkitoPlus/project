package com.se.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;


public final class Log {
	private static PrintStream console = System.out; // 控制台
		
	private static final String defaultLogFilePath = "./log/log.txt";
	
	private static FileWriter logFile = null;
	static {
		try {
			logFile = new FileWriter(defaultLogFilePath);
		} catch (Exception e) {
			console.println("Can not open Log File. The path is" + defaultLogFilePath);
		}
	}
	
	
	private static String dateFormat = "yyyy-MM-dd 'at' HH:mm:ss z";
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
	
	public static void setLogFile(FileWriter logFile) {
		Log.logFile = logFile;
	}

	public static void LogToConsole(boolean stmt, String log, String methodName) {
		if(!stmt) {
			console.println("Error occurs in " + methodName + "()");
			console.println("Log: " + log);
		}
	}
	
	public static void LogToFile(boolean stmt, String log, String methodName) {
		if(!stmt) {
			BufferedWriter fs = null;
			try{
				fs = new BufferedWriter(logFile);
				Date date = new Date(System.currentTimeMillis());
				
				String dateInfo = dateFormatter.format(date);  // 日期时间	
				String errorPosInfo = "Error occurs in " + methodName + "()";
				String logInfo = "Log: " + log;
				
				fs.newLine();
				fs.write(dateInfo);
				fs.newLine();
				fs.write(errorPosInfo);
				fs.newLine();
				fs.write(logInfo);
				fs.newLine();
				fs.write("<--------------------------------------------------------------------->");
			} catch (Exception e) {
				console.println("can not correctly write in log file");
			} finally {
				try {
					fs.close();
				} catch (IOException e) {
					console.println("Can not close bufferwritter");
					e.printStackTrace();
				}
			}
		}
	}

}
