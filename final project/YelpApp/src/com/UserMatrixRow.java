package com;

import java.util.List;


public class UserMatrixRow 
{
	


	int[] cats = new int[10];
	
	private String business_id;
	
	public UserMatrixRow(String busid, List<String> list)
	{
		this.business_id = busid;
		init(list);	
	}
	public int[] getcat()
	{
		return cats;
	}
	public String getBusinessid()
	{
		return business_id;
	}
	private void init(List<String>  listcat)
	{	
		if(listcat.contains("Indian"))
		{
			cats[0] = 1;
		}
		if(listcat.contains("Korean"))
		{
			cats[1] = 1;
		}
		if(listcat.contains("Greek"))
		{
			cats[2] = 1;
		}
		if(listcat.contains("Mediterranean"))
		{
			cats[3] = 1;
		}
		if(listcat.contains("Chinese"))
		{
			cats[4] = 1;
		}
		if(listcat.contains("French"))
		{
			cats[5] = 1;
		}
		if(listcat.contains("Thai"))
		{
			cats[6] = 1;
		}
		if(listcat.contains("Japanese"))
		{
			cats[7] = 1;
		}
		if(listcat.contains("Mexican"))
		{
			cats[8] = 1;
		}
		if(listcat.contains("Italian"))
		{
			cats[9] = 1;
		}
		
	}
	
}
