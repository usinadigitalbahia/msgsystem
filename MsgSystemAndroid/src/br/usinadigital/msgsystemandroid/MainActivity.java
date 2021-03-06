package br.usinadigital.msgsystemandroid;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import br.usinadigital.msgsystemandroid.R;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.UIUtils;
import br.usinadigital.msgsystemandroid.util.Utils;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity  {

	Context context;
	ConfigurationDAO configDAO;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		UIUtils.styleActionBar(getSupportActionBar(), getResources().getString(R.color.actionBarBg)); 
		context = this;
		initializeConfiguration();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public void clickMessages(View v) {
		Intent intent = new Intent(context, MessagesActivity.class);
		startActivity(intent);
	}

	public void clickConfigurations(View v) {
		Intent intent = new Intent(context, ConfigurationsActivity.class);
		startActivity(intent);
	}
	
	public void clickHelp(View v) {
		Intent intent = new Intent(context, HelpActivity.class);
		startActivity(intent);
	}

	private void initializeConfiguration() {
		int[] frequencyValues = getResources().getIntArray(R.array.array_frequency_values);
		configDAO = new ConfigurationDAOImpl(getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE));
		if (configDAO.isFirstApplicationExecution()) {
			Log.d(Constants.TAG, "First Application Execution");
			configDAO.unsetFirstApplicationExecution();
			configDAO.setHistoryLength(getResources().getInteger(R.integer.default_history_index));
			configDAO.setUpdateFrequency(getResources().getInteger(R.integer.default_frequency_index));
			configDAO.setCategoriesLastUpdate(new Date());
			String firstTimeHelp = getResources().getString(R.string.firstTimeHelp);
			String firstTimeHelpTitle = getResources().getString(R.string.firstTimeHelpTitle);
			UIUtils.showDialog(context,firstTimeHelpTitle,firstTimeHelp);
		} else {
			Log.d(Constants.TAG, "NOT First Application Execution");
		}
		Utils.initializeMessageService(this, frequencyValues[configDAO.getUpdateFrequency()]);
	}

}
