/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni MUST remain 
 * intact in the scripts and in the outputted HTML.
 * The "powered by" text/logo with a link back to
 * http://www.naijatek.com in 
 * the footer of the pages MUST remain visible when the pages
 * are viewed on the internet or intranet.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Support can be obtained from support forums at:
 * http://www.naijatek.com/myalumni/forum
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at naijatek com
 *
 * <p>Title: MyAlumni </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.util.utilities;

import java.util.ArrayList;
import java.util.Collection;

import net.naijatek.myalumni.controller.exceptions.BadInputException;



/**
 * @todo: add options for SHORT_STRING_LENGTH
 */
public final class StringUtil {
  /**
   * Constructor
   */
  private StringUtil() {//prevent instantiation
  }

    private static final int SHORT_STRING_LENGTH = 100;

  /**
   * This method trim the input variable, so if it contains only spaces, then
   * it will be empty string, then we have 0 token :-) The returned value is
   * never null. If the input String is null, an empty String array will be
   * returned All tokens are trimed before returning
   *
* @param inputValue String
* @param delim String
* @return String[]
   */
  public static String[] getStringArray(String inputValue, final String delim) {
        if (inputValue == null) {
			inputValue = "";
		}
        inputValue = inputValue.trim();// very important
        java.util.StringTokenizer t = new java.util.StringTokenizer(inputValue, delim);
        String[] ret = new String[t.countTokens()];
        int index = 0;
        while(t.hasMoreTokens()) {
            String token = t.nextToken().trim();
            // check for valid value here if needed
            ret[index] = token;
            index++;
        }
        return ret;
    }

  /**
   *
* @todo: use StringBuffer accept name with char, number or '_' or '.'
* @param str String
* @throws BadInputException
   */
  public static void checkGoodName(final String str) throws BadInputException {
        byte[] s = str.getBytes();
        int length = s.length;
        byte b = 0;

        for (int i = 0; i < length; i++) {
            b = s[i];
            if (b >= 'a' && b <= 'z') {
                // lower char
            } else if (b >= 'A' && b <= 'Z') {
                // upper char
            } else if (b >= '0' && b <= '9' && i != 0) {
                // numeric char
            } else if (( b=='_' || b=='.' ) && i != 0) {
                // _ char
            } else {
                // not good char, throw an BadInputException
                throw new BadInputException("The string '" + str + "' is not a good name. Reason: character '" + (char)b + "' is not allowed.");
            }
        }// for
    }

  /**
   *
* @param str String
* @param maxLength int
* @return java.lang.String
* @throws BadInputException
   */
  public static String getShorterString(final String str, final int maxLength) throws BadInputException {
        if (maxLength < 0) {
			throw new BadInputException("The maxLength < 0 is not allowed.");
		}
        if (str == null) {
			return "";
		}
        if (str.length() <= maxLength) {
			return str;
		}
        return str.substring(0, maxLength) + "...";
    }

  /**
   *
* @param str String
* @return java.lang.String
* @throws BadInputException
   */
  public static String getShorterString(final String str) throws BadInputException {
        return getShorterString(str, SHORT_STRING_LENGTH);
    }

  /**
   *
* @param input String
* @param from char
* @param to String
* @return java.lang.String
   */
  public static String replace(final String input, final char from, final String to) {
        if (input == null) {
            return null;
        }

        char[] s = input.toCharArray();
        int length = s.length;
        StringBuffer ret = new StringBuffer(length * 2);

        for (int i = 0; i < length; i++) {
            if (s[i] == from) {
                ret.append(to);
            } else {
                ret.append(s[i]);
            }
        }// for
        return ret.toString();
    }

    /*
    public static String replace(String input, String from, String to) {
        if (input == null) {
            return null;
        }
        return null;
    }
    */

  /**
   * This method can be replaced by getStringArray
   *
* @param strContent String
* @param pattern String
* @return Collection
   */
  public static Collection getSeparateString(final String strContent, final String pattern) {
        int beginIndex = 0;
        Collection coResult = new ArrayList();
        String result;
        int position = strContent.indexOf(pattern, beginIndex); // Get the first position
        while (position != -1) {
            result = strContent.substring(beginIndex, position);
            if (!result.trim().equals("")) {
                coResult.add(result);
            }
            beginIndex = position + pattern.length(); //Cong 1 la chieu dai cua ky tu ;
            position = strContent.indexOf(pattern, beginIndex);
        }

        return coResult;
    }

    /* for test only
    public static void main(String[] args) {
        //String[] s = getStringArray("  fasg;,   zdgsag, ,,", ",");
        String[] s = getStringArray("  fasg  ", ",");
        System.out.println("length = " + s.length);
        for (int i = 0; i < s.length; i++) {
            System.out.println("" + i + " : " + s[i]);
        }
    }


   public static String capitalize(String str){
       if ( str == null  || str.length() == 0 )
           return "";
       return Character.toUpperCase(str.charAt(0)) + str.substring(1);
   } */
 //--------------------------------------------
  /**
   * Capitalize the first letter of a word.
   *
* @param s String
* @return s
   */
  public static String capitalize(String s) {

	 if (s == null)
		 return "";
	 
     if ( s.length() > 0 ){
         s = s.toLowerCase();
         char chars[] = s.toCharArray();
         chars[0] = Character.toUpperCase(chars[0]);
         return new String(chars);
     }
     else{
         return s ;
     }
  }


   /**
    * Convert a numeric month into an alphabet month.
    *
* @param importIntMonth Numeric month (e.g., 1)
* @return String
    */
   public static String convertToAlphaMonth(final int importIntMonth) {
     String importStrMonth = null;
     switch (importIntMonth) {
       case 1:
         importStrMonth = "January";
         break;
       case 2:
         importStrMonth = "February";
         break;
       case 3:
         importStrMonth = "March";
         break;
       case 4:
         importStrMonth = "April";
         break;
       case 5:
         importStrMonth = "May";
         break;
       case 6:
         importStrMonth = "June";
         break;
       case 7:
         importStrMonth = "July";
         break;
       case 8:
         importStrMonth = "August";
         break;
       case 9:
         importStrMonth = "September";
         break;
       case 10:
         importStrMonth = "October";
         break;
       case 11:
         importStrMonth = "November";
         break;
       case 12:
         importStrMonth = "December";
         break;
       default:
         importStrMonth = "";
     }
     return importStrMonth;
   }

   /**
    * Convert an alphabet month into a numeric month.
    *
* @param importStrMonth Alphabet month (e.g., January)
* @return int
    */
   public static int convertToNumericMonth(String importStrMonth) {

     importStrMonth = safeString(importStrMonth);

     if (importStrMonth.equals("January")) {
		return 1;
	} else if (importStrMonth.equals("February")) {
		return 2;
	} else if (importStrMonth.equals("March")) {
		return 3;
	} else if (importStrMonth.equals("April")) {
		return 4;
	} else if (importStrMonth.equals("May")) {
		return 5;
	} else if (importStrMonth.equals("June")) {
		return 6;
	} else if (importStrMonth.equals("July")) {
		return 7;
	} else if (importStrMonth.equals("August")) {
		return 8;
	} else if (importStrMonth.equals("September")) {
		return 9;
	} else if (importStrMonth.equals("October")) {
		return 10;
	} else if (importStrMonth.equals("November")) {
		return 11;
	} else if (importStrMonth.equals("December")) {
		return 12;
	} else {
		return 0;
	}
   }

   /**
    * This method bullet proofs a string for SQL. It trims off leading and trailing
    * spaces, replace ' with '', and return empty string if the string is a null.
    *
* @param inStr the unsafe string
* @return String the safe string
    */
   public static String safeString(final String inStr) {
     //String safeStr = null;

     if (inStr == null) {
		return "";
	}
     //safeStr = inStr.trim().toUpperCase();
     return inStr.trim();
   }

  /**
   * Returns the boolean equivalent
   *
* @param str String
* @return boolean
   */
  public static final boolean stringToBoolean(String str)
    {
      if (str == null) {
		return false;
	}

      if (str.equals("0")) {
        return false;
      }
      str = str.toLowerCase();
      if (str.equals("false")) {
        return false;
      }
      if (str.equals("no")) {
        return false;
      }
      if (str.equals("")) {
        return false;
      }

      //if it's non false, it's true by definition
      return true;
    }


    public static final String booleanToString_1(String str)
      {
        if (str == null) {
			return "0";
		}

        if (str.equals("0")) {
          return "0";
        }
        str = str.toLowerCase();
        if (str.equals("false")) {
          return "0";
        }
        if (str.equals("no")) {
          return "0";
        }
        if (str.equals("")) {
          return "0";
        }

        //if it's non false, it's true by definition
        return "1";
    }
    

    public static String dobPostfix(final int day){

      if (day < 10 || day > 20){
          if (day == 1 || day == 21 || day == 31){
        	  return day + "<sup>st</sup>";
          }
          else if (day == 2 || day == 22){
        	  return day + "<sup>nd</sup>";
          }
          else if (day == 3 || day == 23){
        	  return day + "<sup>rd</sup>";
          }
      }
      return day + "<sup>th</sup>";
    
    }

    /**
     * Prefix the input string with zeros so it has the specified length.
     *
* @param inStr The input String
* @param outStrLen The length of the output string
* @return String
     */
    public static String prefixZeros(final String inStr, final int outStrLen) {
      int inStrLen = inStr.length();
      int diff = outStrLen - inStrLen;

      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < diff; i++) {
        sb.append("0");
      }
      sb.append(inStr);
      return sb.toString();

  }
    
    
    /**
     * Removes the preceding /
     */
    public static synchronized String trimRootContext(String context){
    	String str = "";
    	if (context != null){
    		str = context.substring(1, context.length());
    	}
    	
    	return str;
    }
}

