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
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Mathias Seguy
 * @goals This class aims to show how to browse the external storage to detect files
 * <ul>
 * <li></li>
 * </ul>
 */
public class ExternalStorageFileReader extends Activity {
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage_file_reader_main);
        //
        boolean isExternalStorageAvailable = false;
        boolean isExternalStorageWriteable = false;
        String externalStorageState = Environment.getExternalStorageState();
        Toast.makeText(this, "externalStorageState " + externalStorageState, Toast.LENGTH_LONG).show();
        if (Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
            // We can read and write the media
            isExternalStorageAvailable = isExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState)) {
            // We can only read the media
            isExternalStorageAvailable = true;
            isExternalStorageWriteable = false;
        } else if (Environment.MEDIA_UNMOUNTED.equals(externalStorageState)) {
            // the media is present but not mounted
            isExternalStorageAvailable = false;
            isExternalStorageWriteable = false;
        } else if (Environment.MEDIA_SHARED.equals(externalStorageState)) {
            // the media is present but not mounted, and shared via USB mass storage.
            isExternalStorageAvailable = false;
            isExternalStorageWriteable = false;
        } else if (Environment.MEDIA_UNMOUNTABLE.equals(externalStorageState)) {
            // the media is present but is not mountable
            isExternalStorageAvailable = false;
            isExternalStorageWriteable = false;
        } else if (Environment.MEDIA_NOFS.equals(externalStorageState)) {
            // the media is present but is blank or is using an unsupported filesystem
            isExternalStorageAvailable = false;
            isExternalStorageWriteable = false;
        } else if (Environment.MEDIA_REMOVED.equals(externalStorageState)) {
            // the media is not present
            isExternalStorageAvailable = false;
            isExternalStorageWriteable = false;
        }
        // then do something with those external data
        if (isExternalStorageAvailable) {
            StringBuilder filesList = new StringBuilder("The directory of this \r\n");
            // Retrieve the directory associated to this context
            File rootDirectory = getApplicationContext().getExternalFilesDir(null);
            Toast.makeText(this, "1� rootDirectory " + rootDirectory, Toast.LENGTH_LONG).show();
            filesList.append(recursiveBrowsing(rootDirectory, ""));

            // Retrieve and browse all the root directories of the device
            filesList.append("\r\n\r\nThe root directory of the Android device \r\n");
            rootDirectory = Environment.getRootDirectory();
            Toast.makeText(this, "2� rootDirectory " + rootDirectory, Toast.LENGTH_LONG).show();
            filesList.append(recursiveBrowsing(rootDirectory, ""));

            writeFilesList(filesList.toString());
            TextView filesListTV = (TextView) findViewById(R.id.TextViewExternalStrorageFileReader);
            filesListTV.setText(filesList.toString());
        } else {
            Toast.makeText(this, "no external storage available", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Recursive browsing of the directory
     *
     * @param directory        the root directory of the browse
     * @param tabulationString the tabulation string to use
     * @return the list of files contained within the directory
     */
    private String recursiveBrowsing(File directory, String tabulationString) {
        // the stringBuilder
        StringBuilder strBuilder = new StringBuilder();
        // write the directory
        strBuilder.append(tabulationString);
        strBuilder.append(directory);
        strBuilder.append("\r\n");
        tabulationString = tabulationString + ">";
        // // Browse files existing within that directory
        if (directory != null) {
            File[] filesList = directory.listFiles();
            if (filesList != null) {
                // the current file found
                File currentFile;
                for (int i = 0; i < filesList.length; i++) {
                    // retrieve the current file
                    currentFile = filesList[i];
                    // if the file is not a directory, then just write its name
                    if (!currentFile.isDirectory()) {
                        strBuilder.append(tabulationString);
                        strBuilder.append(currentFile.getName());
                        strBuilder.append("\r\n");
                    } else {
                        // else make a recursive call
                        strBuilder.append(recursiveBrowsing(currentFile, tabulationString));
                    }
                }
            }
        } else {
            Toast.makeText(this, "directory : " + directory, Toast.LENGTH_LONG).show();
        }
        return strBuilder.toString();
    }


    /**
     * @param filesList
     */
    private void writeFilesList(String filesList) {
        try {
            // Append is used to open the file and write at its end
            FileOutputStream fos = openFileOutput("FilesList", Context.MODE_PRIVATE);
            // open the writer
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            // for the exemple write its location as first lines:
            // The File that is the folder that contains the files of your application
            File fileDir = getFilesDir();
            // looks like something like that : /data/data/com.android2ee.android.tuto/files
            // ie: data/data/package_name_of_the_activity/files
            String fileDirName = fileDir.getAbsolutePath();
            String pathInfo = "File Directory : " + fileDirName + "\r\n";
            outputStreamWriter.write(pathInfo);
            // write
            outputStreamWriter.write(filesList);
            // Close streams
            outputStreamWriter.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
