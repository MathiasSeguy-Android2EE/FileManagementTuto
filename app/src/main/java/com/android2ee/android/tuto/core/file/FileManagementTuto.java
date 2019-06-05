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

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * @author mathias1
 * @goals This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class FileManagementTuto extends TabActivity {
	/** * The tabHost */
	TabHost tabHost;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Instanciate the activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Retrieve the ressource to access to the icon
		Resources resources = getResources(); // Resource object to get Drawables
		// Set the tabHost (set it has final to use it in the OnTabChangeListener)
		final TabHost tabHost = getTabHost();

		// Define the tabSpec that can be thought has the content of the TabPanel
		TabHost.TabSpec tabSpec;
		// Define the intent that is used to populate the tabSpec
		Intent intent;
		/******************************************************************************************/
		/** Internal File Reader Tuto *************************************************************/
		/******************************************************************************************/
		// Create an Intent to launch an Activity for the tab
		intent = new Intent().setClass(this, InternalFileReaderTuto.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		// Get a new TabHost.TabSpec associated with this tab host.
		tabSpec = tabHost.newTabSpec("Internal File Reader");
		// Define its indicator the label displayed (it should be
		// ressources.getString(R.string.stringId)
		tabSpec.setIndicator("Internal File Reader", resources.getDrawable(R.drawable.ic_tab));
		// Define the content of the Spec (the TabPanel)
		tabSpec.setContent(intent);
		// Add it to the tab container
		tabHost.addTab(tabSpec);
		/******************************************************************************************/
		/** External File Reader Tuto *************************************************************/
		/******************************************************************************************/
		// Create an Intent to launch an Activity for the tab
		intent = new Intent().setClass(this, ExternalFileReaderTuto.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		// Get a new TabHost.TabSpec associated with this tab host.
		tabSpec = tabHost.newTabSpec("External File Reader");
		// Define its indicator the label displayed (it should be
		// ressources.getString(R.string.stringId)
		tabSpec.setIndicator("External File Reader", resources.getDrawable(R.drawable.ic_tab));
		// Define the content of the Spec (the TabPanel)
		tabSpec.setContent(intent);
		// Add it to the tab container
		tabHost.addTab(tabSpec);
		/******************************************************************************************/
		/** External File Writer Tuto *************************************************************/
		/******************************************************************************************/
		// Create an Intent to launch an Activity for the tab
		intent = new Intent().setClass(this, ExternalFileWriterTuto.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		// Get a new TabHost.TabSpec associated with this tab host.
		tabSpec = tabHost.newTabSpec("External File Writer");
		// Define its indicator the label displayed (it should be
		// ressources.getString(R.string.stringId)
		tabSpec.setIndicator("External File Writer", resources.getDrawable(R.drawable.ic_tab));
		// Define the content of the Spec (the TabPanel)
		tabSpec.setContent(intent);
		// Add it to the tab container
		tabHost.addTab(tabSpec);
		/******************************************************************************************/
		/** External Storage File Reader Tuto *************************************************************/
		/******************************************************************************************/
		// Create an Intent to launch an Activity for the tab
		intent = new Intent().setClass(this, ExternalStorageFileReader.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		// Get a new TabHost.TabSpec associated with this tab host.
		tabSpec = tabHost.newTabSpec("External Storage File Reader");
		// Define its indicator the label displayed (it should be
		// ressources.getString(R.string.stringId)
		tabSpec.setIndicator("External Strorage File Reader", resources.getDrawable(R.drawable.ic_tab));
		// Define the content of the Spec (the TabPanel)
		tabSpec.setContent(intent);
		// Add it to the tab container
		tabHost.addTab(tabSpec);
		
		//Define the current tab displayed (here the clock)
		tabHost.setCurrentTab(3);
	}
}