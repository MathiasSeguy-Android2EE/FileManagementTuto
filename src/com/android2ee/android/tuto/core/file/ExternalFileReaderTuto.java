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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author mathias1
 * @goals This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class ExternalFileReaderTuto extends Activity {
    /**
     * The spinner that is use to select the file to read
     */
    Spinner spinner;
    /**
     * The text view that displays the content of the file
     */
    TextView textViewFile;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_file_reader_main);
        // First implement the textView
        textViewFile = (TextView) findViewById(R.id.TextViewExternalFileReader);
        // Then fill the spinner with existing files
        spinner = (Spinner) findViewById(R.id.SpinnerFiles);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fileList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Add a listener for spinner's selection changes
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // read file
                readFile();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // just do nothing
            }
        });
        //and read the first file found (the one selected by default by the spinner)
        readFile();
    }

    /**
     * Read the file associated to the file selected in the spinner
     */
    private void readFileBestWay() {
        // Instanciate the first element
        String fileName = (String) spinner.getSelectedItem();
        //if there is a selected file, then read it
        if (fileName != null) {
            try {
                //To manage subdirectories use that code
                //The root directory
                File rootDir = getFilesDir();
//                getExternalFilesDir(Environment.DIRECTORY_DCIM)
//                getExternalFilesDir(Environment.DIRECTORY_ALARMS)
//                getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//                getExternalFilesDir(Environment.DIRECTORY_MOVIES)
//                getExternalFilesDir(Environment.DIRECTORY_MUSIC)
//                getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS)
//                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                getExternalFilesDir(Environment.DIRECTORY_PODCASTS)
//                getExternalFilesDir(Environment.DIRECTORY_RINGTONES)
//                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                fileList()
                //Use sub-folder to organize your data
                String subFolderName = "SubFolder";
                //Create this folder
                File subFolder = new File(rootDir, subFolderName);
                if (!subFolder.exists()) {
                    //use mkdirs to create the folder (and its folders' path)
                    subFolder.mkdirs();
                }
                //Then create your file
                File filePicture = new File(subFolder, fileName);
                if(filePicture.exists()) {
                    FileInputStream fis = new FileInputStream(filePicture);
                    if (fis != null) {
                        //open a reader on the inputStream
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        //String to use to store read lines
                        String str;
                        StringBuilder buf = new StringBuilder();
                        //reda the file
                        while ((str = reader.readLine()) != null) {
                            buf.append(str + "\r\n");
                        }
                        //close the reader
                        reader.close();
                        //close the inputStream
                        fis.close();
                        //Affect the text to the textView
                        textViewFile.setText(buf.toString());
                    }
                }
            } catch (java.io.FileNotFoundException e) {
                Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
            } catch (IOException e) {
                Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
            }
        }
    }

    /**
     * Read the file associated to the file selected in the spinner
     */
    private void readFile() {
        // Instanciate the first element
        String fileName = (String) spinner.getSelectedItem();
        //if there is a selected file, then read it
        if (fileName != null) {
            try {
                //open the file and retrieve the inputStream
                InputStream inputStream = openFileInput(fileName);
                if (inputStream != null) {
                    //open a reader on the inputStream
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    //String to use to store read lines
                    String str;
                    StringBuilder buf = new StringBuilder();
                    //reda the file
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\r\n");
                    }
                    //close the reader
                    reader.close();
                    //close the inputStream
                    inputStream.close();
                    //Affect the text to the textView
                    textViewFile.setText(buf.toString());
                }
            } catch (java.io.FileNotFoundException e) {
                Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
            } catch (IOException e) {
                Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
            }
        }
    }
}
