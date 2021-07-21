package com.mindbowser.springsecurity.business.util;

import java.util.Collection;
import java.util.Map;

public class Validator
{
	private Validator()
	{

	}

	/**
	 * This method returns true if the collection is null or is empty.
	 * 
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		Boolean result = false;
		if (collection == null || collection.isEmpty())
		{
			result = true;
		}
		return result;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * 
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty(Map<?, ?> map)
	{
		Boolean result = false;
		if (map == null || map.isEmpty())
		{
			result = true;
		}
		return result;
	}

	/**
	 * This method returns true if the object is null.
	 * 
	 * @param object
	 * @return true | false
	 */
	public static boolean isEmpty(Object object)
	{
		Boolean result = false;
		if (object == null)
		{
			result = true;
		}
		return result;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 * 
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array)
	{
		Boolean result = false;
		if (array == null || array.length == 0)
		{
			result = true;
		}
		return result;
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 * 
	 * @param string
	 * @return true | false
	 */
	public static boolean isEmpty(String string)
	{
		Boolean result = false;
		if (string == null || string.trim().length() == 0)
		{
			result = true;
		}
		return result;
	}

	public static boolean isEmpty(Float f)
	{
		Boolean result = false;
		if (f == 0.0f || Float.SIZE == 0 || f == null)
		{
			result = true;
		}
		return result;
	}

	public static boolean isNumber(String numbericString)
	{
		return numbericString.matches("^[+-]?[0-9]\\d*(\\.\\d+)?$");
	}

}
