/**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong> with the smart contribution of <strong>Julien PAPUT</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br>
 * </br>
 * For any information (Advice, Expertise, J2EE or Android Training, Rates, Business):</br>
 * <em>mathias.seguy.it@gmail.com</em></br>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Séguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br>
 * </br>
 * Pour tous renseignements (Conseil, Expertise, Formations J2EE ou Android, Prestations, Forfaits):</br>
 * <em>mathias.seguy.it@gmail.com</em></br>
 * *****************************************************************************************************************</br>
 * Merci à vous d'avoir confiance en Android2EE les Ebooks de programmation Android.
 * N'hésitez pas à nous suivre sur twitter: http://fr.twitter.com/#!/android2ee
 * N'hésitez pas à suivre le blog Android2ee sur Developpez.com : http://blog.developpez.com/android2ee-mathias-seguy/
 * *****************************************************************************************************************</br>
 * com.android2ee.android.tuto</br>
 * 25 mars 2011</br>
 */
package com.android2ee.android.tuto.core.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

/**
 * @author mathias1
 * @goals This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class InternalFileReaderTuto extends Activity {
	/**
	 * The internal file's name to read
	 */
	final String fileName = "internal_file";
	/**
	 * The internal xml file's name to read
	 */
	final String xmlFileName = "internal_xml_file";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_file_reader_main);
		/******************************************************************************************/
		/** Simple File reader *********************************************************************/
		/******************************************************************************************/
		// First implement the textView
		TextView textViewSelected = (TextView) findViewById(R.id.TextViewSeletedFile);
		textViewSelected.setText(fileName);
		// First implement the textView
		TextView textViewFile = (TextView) findViewById(R.id.TextViewFileReader);
		// Read the file :open a InputStream on it
		InputStream inputStream = getResources().openRawResource(R.raw.internal_file);
		try {
			if (inputStream != null) {
				// open a reader on the inputStream
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				// String used to store the lines
				String str;
				StringBuilder buf = new StringBuilder();
				// read the file
				while ((str = reader.readLine()) != null) {
					buf.append(str + "\r\n");
				}
				// close streams
				reader.close();
				inputStream.close();
				textViewFile.setText(buf.toString());
				// Log.e("InternalFile result", buf.toString());
			}
		} catch (java.io.FileNotFoundException e) {
			Log.e("InternalFile result", "FileNotFoundException", e);
		} catch (IOException e) {
			Log.e("InternalFile result", "IOException", e);
		}
		/******************************************************************************************/
		/** Xml File reader *********************************************************************/
		/******************************************************************************************/

		// First implement the textView
		TextView textViewXmlSelected = (TextView) findViewById(R.id.TextViewSeletedXmlFile);
		textViewXmlSelected.setText(xmlFileName);
		// First implement the textView
		TextView textViewXmlFile = (TextView) findViewById(R.id.TextViewXmlFileReader);
		// Read the file
		inputStream = getResources().openRawResource(R.raw.internal_xml_file);
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(inputStream, null);
			NodeList nodes = doc.getElementsByTagName("string");
			StringBuilder buf = new StringBuilder();
			Element element;
			for (int i = 0; i < nodes.getLength(); i++) {
				element = (Element) nodes.item(i);
				buf.append("Node name:" + element.getNodeName());
				buf.append("\r\n\t Node Attribute name :" + element.getAttribute("name"));
				buf.append("\r\n\t Node Text Content:" + element.getTextContent());
				buf.append("\r\n\t node type:" + element.getNodeType());
				buf.append("\r\n");
			}
			inputStream.close();
			textViewXmlFile.setText(buf.toString());
			// Log.e("\r\n\r\nInternalFile_XML result", buf.toString());
		} catch (java.io.FileNotFoundException e) {
			Log.e("nInternalFile_XML result", "FileNotFoundException", e);
		} catch (IOException e) {
			Log.e("nInternalFile_XML result", "IOException", e);
		} catch (ParserConfigurationException e) {
			Log.e("nInternalFile_XML result", "ParserConfigurationException", e);
		} catch (SAXException e) {
			Log.e("nInternalFile_XML result", "SAXException", e);
		}

		/******************************************************************************************/
		/** Xml File reader using XmlResourceParser *********************************************************************/
		/******************************************************************************************/
		Log.e("InternalFile result", "Beginning XmlPasring");
		// Read the file
		StringBuffer stringBuffer = new StringBuffer();
		String type;
		XmlResourceParser xmlResParser = getResources().getXml(R.xml.internal_xml_file);

		try {
			int eventType = xmlResParser.next();
			while (eventType != XmlResourceParser.END_DOCUMENT)
			{
				if (eventType == XmlResourceParser.START_DOCUMENT)
				{
					stringBuffer.append("--- Start XML ---");
				}
				else if (eventType == XmlResourceParser.START_TAG)
				{
					stringBuffer.append("\nNode name: " + xmlResParser.getName());
					for (int i = 0; i < xmlResParser.getAttributeCount(); i++) {
						stringBuffer.append("\n\t attribute name: " + xmlResParser.getAttributeName(i));
						stringBuffer.append("\n\t attribute type: " + xmlResParser.getAttributeType(i));
						stringBuffer.append("\n\t attribute value: " + xmlResParser.getAttributeValue(i));
					}
				}
				// else if (eventType == XmlResourceParser.END_TAG)
				// {
				// stringBuffer.append("\nEND_TAG: " + xmlResParser.getName());
				// }
				else if (eventType == XmlResourceParser.TEXT)
				{
					stringBuffer.append("\n\t content value: " + xmlResParser.getText());
				}

				eventType = xmlResParser.next();

			}
		} catch (XmlPullParserException e) {
			Log.e("nXmlResourceParser result", "XmlPullParserException", e);
		} catch (IOException e) {
			Log.e("nXmlResourceParser result", "IOException", e);
		}
		stringBuffer.append("\n--- End XML ---");
		Log.e("\r\n\r\nXmlResourceParser result", stringBuffer.toString());
	}

	void shutdownAndAwaitTermination(ExecutorService executor) {
		executor.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
					System.err.println("Pool did not terminate: Memory Leaks you have");
				}
			}

		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			executor.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	private void getSharedPreferences() {
		// Retrieve the default SharedPreferences of this activity
		SharedPreferences activitySharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
		// Another way to retrieve the default SharedPreferences of this activity
		activitySharedPreference = getPreferences(MODE_PRIVATE);

	}

}
