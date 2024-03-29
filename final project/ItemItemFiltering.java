package com;


import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

//import com.google.code.morphia.Datastore;
//import com.google.code.morphia.Morphia;
//import com.google.code.morphia.query.Query;
//import com.mongodb.Mongo;
//import com.mongodb.MongoException;
//import com.yelp.entities.YelpBusiness;
//import com.yelp.entities.YelpReview;
//import com.yelp.entities.YelpSimilarity;

/*
 *   from dataset3 get a list of all businss id. (Query1)
 *   for each busines_id search dataset2 and get a list of that particular business 
 *   from that list get the user_id's name this set as "related reviews" (Query2)
 *   	bus_id					u_id
 *   l7H8VGwc08dsqmgm_k7_gw  4VIPkNvGoPACST85sc01vg 0 2 0 1 2011-03-20 review
	 l7H8VGwc08dsqmgm_k7_gw  1pYaKw-hSbi9pHP06TJzNg 0 2 1 3 2011-08-18 review
	 l7H8VGwc08dsqmgm_k7_gw  De5DK1Q4mImcA4eF_z8JYA 0 1 0 1 2011-04-15 review
	 l7H8VGwc08dsqmgm_k7_gw  u4Lb_y7OCkOJeS9QM1zPlA 0 0 0 1 2011-07-19 review
	 l7H8VGwc08dsqmgm_k7_gw  OWUalEUNYj1W2VKWjLp3DQ 1 2 1 1 2011-04-25 review
	 l7H8VGwc08dsqmgm_k7_gw  OWERbgAQoyPa5qFOA2HOsg 0 0 0 4 2011-04-15 review
	 l7H8VGwc08dsqmgm_k7_gw  ZPCt3XYINKZb2pSw1-bmmQ 0 3 0 1 2011-08-04 review
	 
	 the result of hashmap:
	 for this business, there is a list of user id that have average rating and similarity of 0.
	 l7H8VGwc08dsqmgm_k7_gw
	 [4VIPkNvGoPACST85sc01vg, 1pYaKw-hSbi9pHP06TJzNg, De5DK1Q4mImcA4eF_z8JYA, u4Lb_y7OCkOJeS9QM1zPlA, OWUalEUNYj1W2VKWjLp3DQ, OWERbgAQoyPa5qFOA2HOsg, ZPCt3XYINKZb2pSw1-bmmQ]
	 0.0
     0.0
	 
 * 
 * 
 */
public class ItemItemFiltering 
{
	private String d1 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset1.txt";	// users and reviews.
	private String d2 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset2.txt";	// business id , user id, review id and full text of the review. Review object in original code.
	private String d3 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset3.txt";	// business data
	private List<YelpDataSet1> list_alldata1;	
	private List<YelpDataSet2> list_alldata2;		
	private  List<YelpDataSet3> list_alldata3; 	
	
	public ItemItemFiltering()
	{
		list_alldata1 = dataset1(d1);		
		list_alldata2 = dataset2(d2);		
		list_alldata3 = dataset3(d3);	// query in original code 	
		
	}
	public static void main(String args[]) 
	{
		
		
		 ItemItemFiltering obj = new  ItemItemFiltering();
//		 List<YelpDataSet1> list_alldata1 = obj.dataset1(d1);		
//		 List<YelpDataSet2> list_alldata2 = obj.dataset2(d2);		
//		 List<YelpDataSet3> list_alldata3 = obj.dataset3(d3);	// query in original code 	
		 
		 List<YelpDataSet2> testreviews = new ArrayList<YelpDataSet2>();
		 
		 
		 HashMap<String,HashObject> hashEntries = new HashMap<String,HashObject>();
		 
		 for(YelpDataSet3 item : list_alldata3 )		// line 43 in original code
		 {
			 String business_id = item.getBusinessid();
			 // for each business id create a HashObject that contains, business_id, userid and their reviews
			// HashMap<String,HashObject> hashEntries = new HashMap<String,HashObject>();
			
			 
			 //System.out.println(item.getBusinessid());
			 testreviews = obj.getReviews(business_id, list_alldata2);   //  Query 1 line 49 get a list of business entries in dataset2. dataset 2 contains the reviews for business 
			 
			 // for the list of this business,  
			 
			 
			 obj.AddUserReview(hashEntries, testreviews);				// line 53-76 original code.
			 
			 HashObject compare1 = hashEntries.get(business_id);
			 
			 
			 
			 
			 
			 break;
			 
		 }
		 obj.PrintReviews(testreviews);
		 System.out.println();
		 obj.PrintMap(hashEntries);
		 
		
	}
	public void AddUserReview(HashMap<String,HashObject> hashEntries, List<YelpDataSet2> list_reviews)
	{
		for (YelpDataSet2 eachReview1 : list_reviews)
		{
			if (hashEntries.containsKey(eachReview1.getBusinessid()))
			{
				HashObject temp = hashEntries.get(eachReview1.getBusinessid());
				temp.getUsers().add(eachReview1.getUserid());
				//temp.setRating_sum(temp.getRating_sum()+eachReview1.getStars());
				temp.getUserreviews().put(eachReview1.getUserid(),eachReview1);
				hashEntries.put(eachReview1.getBusinessid(), temp);
			}
			else
			{
				HashObject temp = new HashObject(eachReview1.getBusinessid());
				temp.getUsers().add(eachReview1.getUserid());
				//temp.setRating_sum(temp.getRating_sum()+eachReview1.getStars());
				temp.getUserreviews().put(eachReview1.getUserid(),eachReview1);
				hashEntries.put(eachReview1.getBusinessid(), temp);
			}

		}
		
	}
	public void PrintMap(HashMap<String,HashObject> hashEntries)
	{
		System.out.println(hashEntries);
		for (String eachKey: hashEntries.keySet())
		{
			HashObject obj = hashEntries.get(eachKey);
			obj.toString();
		} 
		
	}
	public void PrintReviews(List<YelpDataSet2> data)
	{
		for(YelpDataSet2 item : data)
		 {
			 System.out.println(item.getBusinessid() + "  " + item.getUserid() + " " + item.getVote().getCool() + " " + item.getVote().getUseful() + " " + item.getVote().getFunny() + " " + item.getStars() + " " + item.getDate() + " " + item.getType());
			 
		 }
	}
	public List<YelpDataSet2> getListOfBusiness(String businessid)
	{
		List<YelpDataSet2> list_reviews = new ArrayList<YelpDataSet2>(); 
		for(YelpDataSet2 d : list_alldata2)
		{
			if( d.getBusinessid().equals(businessid))
			{
				list_reviews.add(d);
				
			}
		}
		return list_reviews;
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

