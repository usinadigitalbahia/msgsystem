package br.usinadigital.msgsystemandroid.dao;

import java.util.Date;

public interface ConfigurationDAO {

	public Date getCategoriesLastUpdate();
	
	public void setCategoriesLastUpdate(Date date);
	
	public Date getMessagesLastUpdate();
	
	public void setMessagessLastUpdate(Date date);
	
	public int getHistoryLength(); 
	
	public void setHistoryLength(int length);
	
	public int getUpdateFrequency(); 
	
	public void setUpdateFrequency(int freq);
}