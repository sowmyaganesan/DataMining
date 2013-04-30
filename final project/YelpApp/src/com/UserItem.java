package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class UserItem
{
	
	public static void main(String[] args)
	{
		String d1 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset1.txt";	// users and reviews.
		String d2 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset2.txt";	// business id , user id, review id and full text of the review. Review object in original code.
		String d3 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset3.txt";	// business data
		List<YelpDataSet1> list_alldata1;	
		List<YelpDataSet2> list_alldata2;	
		List<YelpDataSet3> list_alldata3;	
				
		HashMap<String, Integer>  map1 = new HashMap<String,Integer>();	
		HashMap<String, List<Integer>>  map2 = new HashMap<String, List<Integer>>();	
		
		
		// List<YelpDataSet3> list_alldata3; 	
		UserItem obj = new UserItem();
		
		list_alldata1 = obj.dataset1(d1);
		list_alldata2 = obj.dataset2(d2);
		list_alldata3 = obj.dataset3(d3);
		
		obj.readmap1(list_alldata3, map1);
		//obj.PrintMap(map1);
		// obj.PrintMap2(map1);
		List<UserBusiness> listusers = obj.getUseBuslist(list_alldata1, list_alldata2,map1);
		List<UserBusiness> listusers2 = obj.getUseBuslist(list_alldata1, list_alldata2,map1);
		//obj.PrintUsers(listusers);
		
		List<UserBusiness> listusersnear = obj.compareusers2(listusers, listusers2);		
		obj.PrintUsers(listusersnear);
	}
	
	public List<UserBusiness> compareusers2(List<UserBusiness> list1 , List<UserBusiness> list2)
	{
		// List<String> near = new ArrayList<String>();
		List<UserBusiness> listnear = new ArrayList<UserBusiness>(); 
		
		for(UserBusiness d1 : list1)
		{
			String userid = d1.getUerid();
			List<String> listbus = d1.getBusinessIDs();
			List<String> near = new ArrayList<String>();
			
			List<String> nearbus = new ArrayList<String>();
			
			for(UserBusiness d2 : list2)
			{
				int countoftrue=0;
				
				if( d2.getUerid().equals(userid))
					continue;
				for(String str : listbus)
				{
					if(d2.getBusinessIDs().contains(str))
					{
						//System.out.println(str + "  " + d2.getUerid());
						countoftrue++;
						if(countoftrue>(listbus.size()*0.75))
						{
							near.add(d2.getUerid());
							nearbus.addAll(d2.getBusinessIDs());
							break;
						}
					}
				}
				
			}
			UserBusiness x = new UserBusiness(userid,nearbus,near);
			listnear.add(x);
			
		}
		return listnear;
	}
	
	
	public void ui(List<UserBusiness> list )
	{
		for(UserBusiness d2 : list)
		{
			List<String> users = d2.getBusinessIDs();
			
		}
		
		
	}
	
	
	public void printlist(List<String> list)
	{
		for(String s : list)
		{
			System.out.println(s);
		}
	}
	public void PrintUsers(List<UserBusiness> list)
	{
		int i = 0 ;
		for(UserBusiness b : list)
		{
			//if ( i++ > 6000 )
			System.out.println(b.getUerid() + " " + b.getNear());
		}
	}
	public List<UserBusiness> getUseBuslist(List<YelpDataSet1> listusers , List<YelpDataSet2> list, HashMap<String, Integer> map1 )
	{
		int i = 0 ; 
		List<UserBusiness> listub = new ArrayList<UserBusiness>(); 
	
		for(YelpDataSet1 users : listusers)
		{  
			i++;
			List<String> busslist = new ArrayList<String>();
			for(YelpDataSet2 d : list)
			{
				if( users.getUserid().equals(d.getUserid()))
				{
					busslist.add(d.getBusinessid());
				}
			}
			UserBusiness x = new UserBusiness(users.getUserid(), busslist, null);
			listub.add(x);
			
			if( i > 100)
				break;
		}
		
		return listub;
		
	}
//	public void readdataset2(List<YelpDataSet2> list_alldata2,HashMap<String, Integer> map1,  HashMap<String, List<Integer>>  map2)
//	{
//		for(YelpDataSet2 d : list_alldata2)
//		{
//			if(map1.containsKey(d.getBusinessid() )  ) 
//			{
//				Integer x = map1.get(d.getBusinessid());
//				
//			}
//		}
//		
//	}
	public void readmap1(List<YelpDataSet3> list, HashMap<String, Integer> map1)
	{
		int i  =  0 ;
		for(YelpDataSet3 d : list)
		{
			map1.put(d.getBusinessid(),  i++);	
		}
	}
	public void PrintMap2(HashMap<String,Integer> hashEntries)
	{
		//System.out.println(hashEntries);
		for (String eachKey: hashEntries.keySet())
		{
			Integer obj = hashEntries.get(eachKey);
			if(obj < 10 )
			System.out.println(eachKey + " " + obj);
		} 
		
	}
	public void PrintMap(HashMap<String,Integer> hashEntries)
	{
		//System.out.println(hashEntries);
		for (String eachKey: hashEntries.keySet())
		{
			Integer obj = hashEntries.get(eachKey);
			System.out.println(eachKey + " " + obj);
		} 
		
	}
	public List<YelpDataSet1> dataset1(String filename)
	{
	List<YelpDataSet1> data = new ArrayList<YelpDataSet1>();	
	ObjectMapper mapper = new ObjectMapper();
	try 
	{
		Iterator<YelpDataSet1> it = mapper.reader(YelpDataSet1.class).readValues(new File(filename));
		while (it.hasNext())
	    {
			YelpDataSet1 d = it.next();
			data.add(d);
	    }	
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
	return data;
	}
	public List<YelpDataSet2> dataset2(String filename)
	{
	List<YelpDataSet2> data = new ArrayList<YelpDataSet2>();	
	ObjectMapper mapper = new ObjectMapper();
	try 
	{
		Iterator<YelpDataSet2> it = mapper.reader(YelpDataSet2.class).readValues(new File(filename));
		while (it.hasNext())
	    {
			YelpDataSet2 d = it.next();
			data.add(d);
	    }	
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
	return data;
	}
	public List<YelpDataSet3> dataset3(String filename)
	{
	List<YelpDataSet3> data = new ArrayList<YelpDataSet3>();	
	ObjectMapper mapper = new ObjectMapper();
	try 
	{
		Iterator<YelpDataSet3> it = mapper.reader(YelpDataSet3.class).readValues(new File(filename));
		while (it.hasNext())
	    {
			YelpDataSet3 d = it.next();
			data.add(d);
	    }	
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
	return data;
	}

}






