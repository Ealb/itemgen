package com.elmokki;


import itemgen.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Generic {
	

	
	public static String integerToPath(int integer)
	{
		String[] paths = { "fire", "air", "water", "earth", "astral", "death", "nature", "blood", "holy", "any", "elemental", "sorcery" };
		if(integer >= 0 && integer < 12)
			return paths[integer];	
		else
			return "NONE";
	}
	
	public static int PathToInteger(String path)
	{
		String[] paths = { "fire", "air", "water", "earth", "astral", "death", "nature", "blood", "holy", "any", "elemental", "sorcery" };
		path = path.toLowerCase().trim();
		for(int i = 0; i < 12; i++)
		{
			if(paths[i].equals(path))
			{
				return i;
			}
		}
		
		return -1;	
	}
	
	public static boolean matchingPaths(int orig, int target)
	{
		return  target == orig || (target == 11 && isSorcery(orig)) || (target == 10 && isElemental(orig)) || target == 9;
	}
	
	public static boolean isElemental(int path)
	{
		if(path == 9 || path == 10 || (path > -1 && path < 4))
			return true;
		return false;
	}
	public static boolean isSorcery(int path)
	{
		if(path == 9 || path == 11 || (path > 3 && path < 8))
			return true;
		return false;
	}
	
	public static String integerToShortPath(int integer)
	{

		String[] paths = { "F", "A", "W", "E", "S", "D", "N", "B", "H", "?", "?", "?" };
		
		if(integer >= 0 && integer < 9)
			return paths[integer];	
		else
			return " NONE";
	}
	
	public static int getNextArgument(List<String> args, String arg, int defaultvalue)
	{
		int integer = defaultvalue; 
		if(args.contains(arg))
		{
			if(args.size() > args.indexOf(arg) + 1)
			{
				integer = Integer.parseInt(args.get(args.indexOf(arg) + 1));
			}
		}
		return integer;
	}
	
	public static <E extends Entity> E getRandom(List<E> list, Random r)
	{
		double max = 0;
		double rand = 0;
		for(E e : list)
			max = max + e.basechance;
		
		rand = r.nextDouble() * max;
		
		double current = 0;
		
		for(E e : list)
		{
			current += e.basechance;
			if(current >= rand)
				return e;
		}
		
		
		return null;
		
		
	}
	
	public static List<String> getListOfPathsInMask(int mask)
	{
		int[] masks = {128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
		String[] paths = { "fire", "air", "water", "earth", "astral", "death", "nature", "blood", "holy" };

		List<String> list = new ArrayList<String>();
		for(int i = masks.length - 1; i >= 0; i--)
		{
			if(mask >= masks[i])
			{
				list.add(paths[i]);
				mask = mask - masks[i];
			}
		}
		

		return list;
	}
	
	public static List<String> parseArgs(String str)
	{
		return parseArgs(str, "\"");
	}
	
	
	
	public static boolean containsTag(List<String> tags, String tag)
	{
		for(String str : tags)
		{
			List<String> args = parseArgs(str);
			if(args.get(0).equals(tag))
				return true;
		}
		
		return false;
	}
	
	public static String getTagValue(List<String> tags, String tag)
	{
		for(String str : tags)
		{
			List<String> args = parseArgs(str);

			if(args.get(0).equals(tag))
			{

				args.remove(0);
				return listToString(args);
			}
		
		}
		
		return null;
	}
	

	
	
	public static int shortPathToInteger(String path)
	{
		String[] paths = { "F", "A", "W", "E", "S", "D", "N", "B", "H" };
		path = path.toUpperCase().trim();
		for(int i = 0; i < 9; i++)
		{
			if(paths[i].equals(path))
			{
				return i;
			}
		}
		
		return -1;	
	}
	
	public static boolean isNumeric(String str)
	{
		try
		{
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static String listToString(List<String> list)
	{
		String result = "";
		for(String str : list)
		{
			if(result.length() != 0)
				result = result + " ";
			
			result = result + str;
		
		}
		
		return result;
	}

   public static boolean containsBitmask(int bitmask, int wanted)
   {
	   return((bitmask & wanted) != 0);
   }
		
   
   public static int AmountOfVariables(int bitmask)
   {
	   int vars = 0;
	   int pos = 1;
	   
	   while(bitmask > 0)
	   {
		   if(containsBitmask(bitmask, pos))
		   {
			   vars++;
			   bitmask -= pos;
		   }
		   pos *= 2;
	   }
	   
	   return vars;
	   
	   
   }
   
   
   
   public static String plural(String line)
   {
  
   	String end = "";
   	if(line.indexOf(" of ") != -1)
   	{
   		end = line.substring(line.indexOf(" of "), line.length());
   		line = line.substring(0, line.indexOf(" of "));
   	}
   	
   	if(line.toLowerCase().endsWith("s") || line.toLowerCase().endsWith("x") || line.toLowerCase().endsWith("sh"))
   		line = line + "es";
   	else if(line.toLowerCase().endsWith("y"))
   		line = line.substring(0, line.length() - 1) + "ies";
   	else if(line.toLowerCase().endsWith("man") && !line.toLowerCase().endsWith("human"))
   		line = line.substring(0, line.length() - 3) + "men";
   	else if(line.toLowerCase().endsWith("samurai") || line.toLowerCase().endsWith("vanir") || line.toLowerCase().endsWith(" sheep") || line.toLowerCase().endsWith(" fish"));
   	else if(line.toLowerCase().endsWith("van"))
   	{
   		line = line + "ir";
   	}
   	else
   		line = line + "s";
   	

   	
   	return line + end;
   }
		
	public static String capitalizeFirst(String s)
	{
		if(s.length() < 2)
			return s.toUpperCase();
		
		String string = "";
		string = (s.substring(0, 1).toUpperCase() + s.substring(1));
		return string;
	}
	
	public static String capitalize(String s)
	{
		
		if(s.length() < 2)
			return s.toUpperCase();
		
		String string = "";
		for(String str : s.split(" "))
		{
			str = str.trim();
			if(str.length() < 1)
				continue;
			
			if(str.equals("of") || str.equals("the"))
				string = string + " " + str;
			else
				string = string + " " + (str.substring(0, 1).toUpperCase() + str.substring(1));
		}
		string = string.substring(1);

		return string;
	}
	
	
	/**
	 * Parses args from string. Separator is space by default, but separator arg
	 * Overrides, ie string DERP "HERP DURP" would result in args 
	 * DERP and HERP DURP.
	 * @param str
	 * @param separator
	 * @return
	 */
	public static List<String> parseArgs(String str, String separator)
	{
		List<String> newlist = new ArrayList<String>();
		
		str = str.trim();
		
		str = str.replaceAll("\t", " ");
		
		while(str.length() > 0)
		{
			boolean done = false;
			if(str.startsWith(separator))
			{
				for(int i = 1; i < str.length(); i++)
				{
					//System.out.println(i + ": " + str.charAt(i));
					if(("" + str.charAt(i)).equals(separator))
					{
						// Found next separator
						newlist.add(str.substring(1, i));
						str = str.substring(i + 1);

						done = true;
						break;
					}	
				}
			}
			
			if(!done)
			{
				int index = str.indexOf(" ");
				if(index == -1)
					index = str.length();
				
				newlist.add(str.substring(0, index).trim());
				str = str.substring(index);
				
			}
			
			str = str.trim();
		}
		
		return newlist;
				
	}
	
    /**
     * Gets the position of the highest double in an array
     * @param array
     * @return
     */
    public static int GetHighestPosition(double[] array)
    {
        double highestvalue = array[0];
        int highest = 0;

        for (int i = 0; i < array.length; i++)
        {
            if (highestvalue < array[i])
            {
                highestvalue = array[i];
                highest = i;
            }
        }
        return highest;
    }
    
    /**
     * Gets the position of the highest int in an array
     * @param array
     * @return
     */
    public static int GetHighestPosition(int[] array)
    {
        int highestvalue = array[0];
        int highest = 0;

        for (int i = 0; i < array.length; i++)
        {
            if (highestvalue < array[i])
            {
                highestvalue = array[i];
                highest = i;
            }
        }
        return highest;
    }
	
	

}
