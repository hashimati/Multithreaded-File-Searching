package com.ahmed.main;

public class TestMultithreadedSearching {

	
	public static void main(String[] args) throws Exception {
		SearchController search = new SearchController("D:\\", "android", 20);
		search.startSearch();
		
		//After finishing,Printing Result
		for (String s : search.searchResult)
			System.out.println(s);
	}
}
