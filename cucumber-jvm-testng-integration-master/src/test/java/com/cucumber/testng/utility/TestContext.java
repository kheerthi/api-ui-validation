package com.cucumber.testng.utility;
/**
 * 
 * @author kmathai
 * Store results in context
 *
 */

	import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cucumber.testng.combination.Runner;
import com.thoughtworks.selenium.Selenium;


	public enum TestContext {
		
		INSTANCE;
		
		/*private static TestContext instance = null;
		
		protected TestContext()
		{
			
		}
		
		public static TestContext getInstance() {
		      if(instance == null) {
		         instance = new TestContext();
		      }
		      return instance;
		   }*/
		

		private static Map<String, Object> data = new HashMap<String, Object>();
		
		

		public static TestContext put(String key, Object value) {
			return (TestContext) data.put(key, value);
			//return this;
		}

		public static boolean containsKey(String key) {
			
			 return data.containsKey(key);
			// return this;
		}
			
		public static Set<Entry<String, Object>> entrySet()
		{
			
		return data.entrySet();
		//return this;
		}
		
		public static Object get(String key)
		{
			
			return data.get(key);
		}
		
		
		

	  

}
