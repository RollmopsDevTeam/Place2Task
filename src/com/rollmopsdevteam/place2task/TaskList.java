package com.rollmopsdevteam.place2task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class TaskList extends ArrayList<Task> {
	

	private static final long serialVersionUID = 4105832240816454926L;
	private static TaskList _instance=null;
	private static File _filesDir = null;
	
	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private TaskList() {
		if(_filesDir == null ) {
			Log.e(Constants.LOG, "FilesDir is not set. Use TaskList.setFilesDir() before calling TaskList.getInstance()!");
		} else {
			Log.v(Constants.LOG, "Creating " + TaskList.class.getName());
			Log.v(Constants.LOG, "Trying to load XML from " + _filesDir.toString());
			try {
				__init();
			} catch (Exception e) {
				Log.e(Constants.LOG, e.getMessage() );
			}
		}
	}
	
	private static void __init() throws IOException, XmlPullParserException {
		File inFile = new File(_filesDir, Constants.TASK_LIST_FILENAME);
		if( ! inFile.exists() ) {
			Log.v(Constants.LOG, inFile.toString() + " does not yet exists. Creating new one");
			
			//TODO create new file
		}
		FileInputStream inStream = new FileInputStream( inFile );
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(inStream, null);
		} finally {
			inStream.close();
		}
	}
	
	public static void setFilesDir( File filesDir ) {
		_filesDir = filesDir;
	}
	
	public static TaskList getInstance() {
		if( _instance == null ) {
			_instance = new TaskList();
		} 
		return _instance;
	}
	
	
	

	

}
