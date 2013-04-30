package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;


public class Test
{
	public static void main(String[] args)
	{
		
		Float f = new Float("3");
		if(f >= 3.0)
		System.out.print(f);
		
		//String d1 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset1.txt";	// users and reviews.
		String d2 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset2.txt";	// business id , user id, review id and full text of the review. Review object in original code.
		String d3 = "C:\\Users\\fred\\Documents\\SJSU\\CMPE 239\\final project\\yelpdataset3.txt";	// business data
		//List<YelpDataSet1> list_alldata1;	
		List<YelpDataSet2> list_alldata2;	
		List<YelpDataSet3> list_alldata3;	
		
		HashMap<String, Integer> positive  = new HashMap<String,Integer>(); 
		HashMap<String, Integer> negative  = new HashMap<String,Integer>(); 
		HashMap<String, Integer> result  = new HashMap<String,Integer>(); 
		
		int poscount = 0;
		int negcount = 0;
		
		
		// List<YelpDataSet3> list_alldata3; 	
		Test t = new Test();
		list_alldata2 = t.dataset2(d2);
		list_alldata3 = t.dataset3(d3);
		int i = 0;
		for(YelpDataSet2 d : list_alldata2)
		{
			
			int x = Integer.parseInt(d.getStars()); 
			if( x >=   3)
			{
				//System.out.println(x);
			
				if(positive.containsKey(d.getBusinessid()))
				{
					//Integer count  = positive.get(d.getBusinessid());
					positive.put(d.getBusinessid(),positive.get(d.getBusinessid()) + 1);
					//System.out.println("value:" + count);
				}
				else
				{
					//Integer firstcount =1;
					positive.put(d.getBusinessid(),1);
				}
			}
			else
			{
				if(negative.containsKey(d.getBusinessid()))
				{
					//Integer count  = negative.get(d.getBusinessid());
					
					negative.put(d.getBusinessid(),negative.get(d.getBusinessid()) + 1);
					//System.out.println("value:" + count);
				}
				else
				{
					//Integer firstcount =1;
					negative.put(d.getBusinessid(),1);
				}
			}
		}
		//System.out.println(" number " + i);
		//t.PrintMap(positive);
		System.out.println("=======================");
		//t.PrintMap(negative);
		t.t1(positive, negative,result) ;
		//t.PrintCat(list_alldata3);
		List<String> listcat = t.GetCat(list_alldata3);
		//t.printlist(listcat);
		//t.matrix();
		List<MatrixRow> listmatrix = t.matrix(list_alldata3);
		//System.out.println(listmatrix.size());
		
		//t.test1(result,listmatrix);
		

	
	}
	public void test1(HashMap<String, Integer> result , List<MatrixRow> list)
	{
		// fred . push this item to ui and make a list of json file
		// and send it over.
		// budiness name, city , stars, reviww count, addess.
		System.out.println("col1" +  "col2" + "col3" +  "col4" + "col5" +  "col6" + "col7" +  "col8" + "col9" +  "col0" );
		
		for(MatrixRow row : list)
		{
			//System.out.println("dddd");
			if( result.containsKey(row.getBusinessid()))
			{
				//System.out.println("sakldfjalksdfj");
				int[] temp = row.getcat();
				for(int i = 0 ; i < temp.length; i++)
				{	
					if(temp[i] == 1)
					{
						printcol(i,row);
					}
				}
			}
		}
		
		
	}
	
	public void printcol(int i , MatrixRow row)
	{
		switch ( i )
		{
			case 0 :
				System.out.println(row.getBusinessid() +  "  " + " Indian Cusine " ) ;
				break;
			case 1 :
				System.out.println(row.getBusinessid() +  "  " + " Korean Cusine " ) ;
				break;
			case 2 :
				System.out.println(row.getBusinessid() +  "  " + " Greek Cusine " ) ;
				break;
			case 3 :
				System.out.println(row.getBusinessid() +  "  " + " Med Cusine " ) ;
				break;
			case 4 :
				System.out.println(row.getBusinessid() +  "  " + " Chinees Cusine " ) ;
				break;
			case 5 :
				System.out.println(row.getBusinessid() +  "  " + " Fench Cusine " ) ;
				break;
			case 6 :
				System.out.println(row.getBusinessid() +  "  " + " Tai Cusine " ) ;
				break;
			case 7 :
				System.out.println(row.getBusinessid() +  "  " + "Japanese Cusine " ) ;
				break;
			case 8 :
				System.out.println(row.getBusinessid() +  "  " + " Mexican Cusine " ) ;
				break;
			case 9 :
				System.out.println(row.getBusinessid() +  "  " + " Italian Cusine " ) ;
				break;
		}
		
		
	}
	public void jacard()
	{
		
	}
	public List<MatrixRow> matrix(List<YelpDataSet3> list)
	{
		/*
		 * Indian Korean Greek Mediterranean Chinese French Thai Japanese Mexican Italian
		 * 
		 */
		List<MatrixRow> listmatrix = new ArrayList<MatrixRow>();
		
		//List<String> listcat = GetCat(list);
		
		
		for(YelpDataSet3 d : list)
		{
			List<String> listcat = d.getCategories();
			MatrixRow row = new MatrixRow( d.getBusinessid(), listcat);
			listmatrix.add(row);
		}
		for(MatrixRow row : listmatrix)
		{
			
			int[] cats = row.getcat();
			for(int i = 0 ; i < cats.length ; i++)
			{
				System.out.print(cats[i]);
			}
			System.out.print("   " + row.getBusinessid());
			System.out.println();
		}
		
		return listmatrix;
		
	}
	
	public void printlist(List<String> list)
	{
		for(String s : list)
		{
			System.out.println(s);
		}
	}
	public List<String> GetCat(List<YelpDataSet3> list)
	{
		List<String> listcat = new ArrayList<String>();
		List<String> listcat2 = new ArrayList<String>();
		
		for(YelpDataSet3 d : list )
		{
			
			ArrayList x = d.getCategories();
			if(x.contains("Restaurants"))
			{
				for(Object b : x)
				{
					if( !b.toString().equals("Restaurants")  && ( !listcat.contains(b.toString()) ) )
					{
						listcat.add(b.toString());
					}
				}
			}
		}
		for(String str : listcat)
		{
			if( str.equals("Indian") || str.equals("Korean") || str.equals("Greek") || str.equals("Mediterranean") ||
				str.equals("Chinese") || str.equals("French") || str.equals("Thai") || str.equals("Japanese") ||
				str.equals("Mexican") || str.equals("Italian") )
			{
				listcat2.add(str);
			}
		}
		
		return listcat2;
		
	}
	
	public void PrintCat(List<YelpDataSet3> list)
	{
		for(YelpDataSet3 d : list )
		{
			
			ArrayList x = d.getCategories();
			if(x.contains("Restaurants"))
				System.out.println(x);
		}
		
		
	}
	public void t1(HashMap<String, Integer> pos, HashMap<String, Integer> neg, HashMap<String, Integer> result )
	{
		for (String eachKey: pos.keySet())
		{
			// this bsiness has a combination of + and - compute % and if above put in result.
			if( neg.containsKey(eachKey) )
			{
				float posvalue = pos.get(eachKey);
				float negvalue = neg.get(eachKey);
				float sum=posvalue+negvalue;
				
				
				float posper = ((posvalue /sum ) * 100);
				float negper = ((negvalue /sum ) * 100);
				if(posper > negper)
				{
					result.put(eachKey, pos.get(eachKey));
					//result.put(eachKey, new Integer((int) posper));
				}
			}
			// this business have ratings above "rate" ie 3. 
			else
			{
				result.put(eachKey, pos.get(eachKey));
			}
		} 
		PrintMap(result);
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

