/**<!--
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author mathias1
 * @goals
 * This class aims to:
 * <ul><li></li></ul>
 */
public class ExternalFileWriterTuto extends Activity {
	 /**
	 * The file name to write 
	 */
	EditText fileName;
	 /**
	 * The file content to write
	 */
	EditText fileContent;
	/**
	 * The button that launch the writing process
	 */
	Button buttonWrite;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_file_writer_main);
		//Retrieve Gui components
		fileName=(EditText)findViewById(R.id.EditorViewSeletedFile);
		fileContent=(EditText)findViewById(R.id.EditorViewFileWriter);
		buttonWrite=(Button)findViewById(R.id.ButtonWrite);
		//add the listener on button's click
		buttonWrite.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				writeFile();				
			}
		});		
	}
	
	/**
	 * Write the file
	 */
	private void writeFile(){
		String fileNameStr=fileName.getText().toString();
		String fileContentStr=fileContent.getText().toString();
		try{
			
			//To manage subdirectories use that code
//			//The root directory
//			File rootDir=getFilesDir();
//			//Use sub-folder to organize your data
//			String subFolderName="SubFolder";
//			//Create this folder
//			File subFolder=new File(rootDir,subFolderName);
//			if(!subFolder.exists()) {
//				//use mkdirs to create the folder (and its folders' path)
//				subFolder.mkdirs();
//			}
//			//Then create your file
//			File filePicture = new File(subFolder, fileNameStr);
//			FileOutputStream fos = new FileOutputStream(filePicture);
			
			//Default way
			//To open you can choose the mode MODE_PRIVATE,MODE_APPEND,MODE_WORLD_READABLE,MODE_WORLD_WRITEABLE
			//This is the creation mode (Private, World Readable et World Writable), 
			//Append is used to open the file and write at its end 
			FileOutputStream fos= openFileOutput(fileNameStr, Context.MODE_PRIVATE);
			//open the writer
			OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fos);
			//for the exemple write its location as first lines:
			//The File that is the folder that contains the files of your application
			File fileDir=getFilesDir();
			//looks like something like that : /data/data/com.android2ee.android.tuto/files
			//ie: data/data/package_name_of_the_activity/files
			String fileDirName=fileDir.getAbsolutePath();
			File fileStreamPath=getFileStreamPath(fileNameStr);
			//looks like something like that : /data/data/com.android2ee.android.tuto/files/fileNameStr
			//ie: data/data/package_name_of_the_activity/files/my_file_name
			String fileStreamPathName=fileStreamPath.getAbsolutePath();
			String pathInfo="File Directory : "+fileDirName+" and the FileStreamPath : "+fileStreamPathName+"\r\n";
			outputStreamWriter.write(pathInfo);
			//write
			outputStreamWriter.write(fileContentStr);
			//Close streams
			outputStreamWriter.close();
			fos.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
