/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni Board MUST remain 
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
 * <p>Title: MyAlumni Board </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.util.encryption;

/**
 * This is a utility class to perform simple cipher on texts. The tokenizer, prefixing
 * and suffixing constant values can be changed at any point, but this can result to
 * inconsitent values if new values are applied on old cipher.
 *
 * @author Folashade Adeyosoye.
 * @version 1.0.
 */
public class SimpleCipher {
    
    static String PREFIX_SUFFIX = "uWillNotGuessRight";
    static String TOKENIZER = "-";
    
    public static String encodeString(String encStr)
    {   
        encStr = PREFIX_SUFFIX + TOKENIZER + encStr + 
                 TOKENIZER + PREFIX_SUFFIX;
        
        String code = "";
        byte[] pushData = encStr.getBytes(); 
        for(int i = 0;i < pushData.length;i++){    
             code += (char)(pushData[i]+58);
        }     
        return code;
    }

    public static String decodeString(String decStr)
    {     
        String decode = "";
        char[] pullData = decStr.toCharArray();
        for(int i = 0;i < decStr.length();i++){
            decode += (char)(byte)(pullData[i]-58);    
        }
        decode = decode.substring(decode.indexOf(TOKENIZER) + 1, 
                                  decode.lastIndexOf(TOKENIZER));
        return decode;
    } 
    
    
//    /**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// 
//		String tmp1 = "07/22/2007";
//		String tmp2 = "01/22/2008";
//		String tmp3 = "EMP;CLI;";
//		System.out.println("tmp1 = " + encodeString(tmp1));
//		System.out.println("tmp2 = " + encodeString(tmp2));
//		String c = encodeString(tmp3);
//		System.out.println("tmp3 = " + c);
//		System.out.println("tmp3 = " + decodeString(c));
//	}
	
}
