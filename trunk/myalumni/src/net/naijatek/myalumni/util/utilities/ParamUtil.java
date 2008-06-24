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
package net.naijatek.myalumni.util.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.naijatek.myalumni.framework.exceptions.BadInputException;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * <p>Title: Federal government College System (FeGoCoId System)</p>
 * <p>Description: FeGoCoId System</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Naijatek Solutions</p>
* @author Folashade Adeyosoye (shardayyy@@naijatek.com)
* @version 1.0
 */
public final class ParamUtil {
    private static Log logger = LogFactory.getLog(ParamUtil.class);

    private ParamUtil() { // prevent instantiation
    }

    private static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat ("dd/MM/yyyy");


    public static String getServer(final HttpServletRequest request) {

        StringBuffer server = new StringBuffer(128);
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        server.append(scheme);
        server.append ("://");
        server.append(request.getServerName());
        if ( scheme.equals("http") && port != 80
            || scheme.equals("https") && port != 443 ) {
            server.append(':');
            server.append(port);
        }
        return server.toString();
    }

    public static String getServer2(final HttpServletRequest request) {
        StringBuffer server = new StringBuffer(128);
        server.append(request.getScheme());
        server.append ("://");
        server.append(request.getHeader("host"));
        return server.toString();
    }

    public static String getParameter(final HttpServletRequest request, final String param) {
        String ret = request.getParameter(param);
        if (ret == null){
          ret = "";
        }
        return ret.trim();
    }

    public static String getParameter(final HttpServletRequest request, final String param, final boolean checkEmpty)
        throws BadInputException {
        String ret = request.getParameter(param);
        if (ret == null) {
          ret = "";
        }
        ret = ret.trim();
        if ( checkEmpty && ret.length() == 0 ){
          throw new BadInputException("The parameter '" + param +
              "' is not allowed to be empty! Please try again.");
        }
        return ret;
    }

  /**
   *
   * @todo review this method
   * @param request HttpServletRequest
   * @param param String
   * @param checkEmpty boolean
   * @throws BadInputException
   * @return String
   */
  public static String getParameterSafe(final HttpServletRequest request, final String param, final boolean checkEmpty) throws BadInputException {
        String ret = getParameter(request, param, checkEmpty);
        if ( ret.indexOf('<') != -1 ||
             ret.indexOf('>') != -1) {
            throw new BadInputException("The parameter '" + param + "' is not allowed to contain '<' or '>'! Please try again.");
        }
        return ret;
    }

    public static int getParameterInt(final HttpServletRequest request, final String param) throws BadInputException {
        String inputStr = getParameter(request, param, true);
        int ret;
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an integer value!. Please try again.");
        }
        return ret;
    }

    public static int getParameterInt(final HttpServletRequest request, final String param, final int defaultValue)
        throws BadInputException {
        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }
        int ret;
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an integer value!. Please try again.");
        }
        return ret;
    }

    public static int getParameterInt(String value, final int defaultValue)
        throws BadInputException {

        value = StringUtil.safeString(value);
        if (value.length() == 0) {
            return defaultValue;
        }
        int ret;
        try {
            ret = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse \"" + value + "\" to an integer value!. Please try again.");
        }
        return ret;
    }

    public static long getParameterLong(final HttpServletRequest request, final String param) throws BadInputException {
        String inputStr = getParameter(request, param, true);
        long ret;
        try {
            ret = Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an long value!. Please try again.");
        }
        return ret;
    }

    public static long getParameterLong(final HttpServletRequest request, final String param, final long defaultValue)
        throws BadInputException {
        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }

        long ret;
        try {
            ret = Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an long value!. Please try again.");
        }
        return ret;
    }

  /**
   *
   * @param request param is the name of variable
   * @param param String
   * @return : true if the value of param is not empty
   */
  public static boolean getParameterBoolean(final HttpServletRequest request, final String param) {
        String inputStr = getParameter(request, param);
        if (inputStr.length() == 0) {
          return false;
        }
        return true;
    }

    public static byte getParameterByte(final HttpServletRequest request, final String param) throws BadInputException {
        String inputStr = getParameter(request, param, true);
        byte ret;
        try {
            ret = Byte.parseByte(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an Byte value!. Please try again.");
        }
        return ret;
    }

    public static double getParameterDouble(final HttpServletRequest request, final String param) throws BadInputException {
        String inputStr = getParameter(request, param, true);
        double ret;
        try {
            ret = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            throw new BadInputException("Cannot parse the parameter \"" + param + "\" to an double value!. Please try again.");
        }
        return ret;
    }

    public static String getParameterUrl(final String ret)
        throws BadInputException {

        if ( ret.length() > 0 ) {
            if ( !ret.startsWith("http://") &&
                 !ret.startsWith("https://")) {
                throw new BadInputException("The parameter '" + ret + "' must begin with http:// or https:// ! Please try again.");
            }
        }
        return ret;
    }

    public static String getParameterPassword(final HttpServletRequest request, final String param, int minLength, final int option)
        throws BadInputException {

        if (minLength < 1) {
          minLength = 1;
        }
        String ret = request.getParameter(param);
        if (ret == null){
          ret = "";
        }
        ret = ret.trim();

        if ( ret.length() < minLength ) {
            throw new BadInputException("Your password is not allowed to be lesser than " + minLength + " characters! Please try again.");
        }

        /* @todo implement this feature */
        if (option == 1) {//char and number

        } else if (option == 2) {// lower char, upper char and number

        }
        return ret;
    }

    public static String getParameterEmail(final HttpServletRequest request, final String param, final boolean checkempty) throws BadInputException {
        String email = getParameterSafe(request, param, checkempty);
        MailUtil.checkGoodEmail(email);
        return email;
    }

  /**
     * returns the enteredDate
     * 
     * @param request HttpServletRequest
     * @param param String
     * @throws BadInputException
     * @return Date
     */
  public static java.sql.Date getParameterDate(final HttpServletRequest request, final String param) throws BadInputException {
        String inputStr = getParameter(request, param, true);
        java.util.Date ret;
        try {
            ret = dateFormat.parse(inputStr);
        } catch (java.text.ParseException e) {
            throw new BadInputException("Cannot parse the parameter '" + param + "' to an Date value!. Please try again.");
        }
        return new java.sql.Date(ret.getTime());
    }

  /**
   *
* @param paramDay String
* @param paramMonth String
* @param paramYear String
* @throws BadInputException
* @return Date
   */
  public static java.sql.Date getParameterDate(final String paramDay, final String paramMonth, final String paramYear)
        throws BadInputException {

        int day = Integer.parseInt(paramDay);
        int month = Integer.parseInt(paramMonth);
        int year = Integer.parseInt(paramYear);
        StringBuffer buffer = new StringBuffer();
        buffer.append(day).append("/").append(month).append("/").append(year);
        String inputStr = buffer.toString();

        java.util.Date ret;
        try {
            ret = dateFormat.parse(inputStr);
            //log.debug("Date of birth is " + ret);
        } catch (java.text.ParseException e) {
            throw new BadInputException("Cannot parse the parameter '" + inputStr + "' to an Date value!. Please try again.");
        }

//        if ( validate){
        //* @todo : rewrite this code */
//            long nowtime = System.currentTimeMillis();
//            long oldest = nowtime - 100 * DateUtil.YEAR;
//            long youngest = nowtime - 10 * DateUtil.YEAR;
//            long age = (nowtime - ret.getTime()) / DateUtil.YEAR;

//            if (ret.getTime() > youngest ||
//                ret.getTime() < oldest) {
//                log.debug("age = " + age + " date = " + ret + " gettime = " + ret.getTime());
//            throw new BadInputException("Your age is not allowed: " + age);
//            }
//        }
        return new java.sql.Date(ret.getTime());
    }




    public static int getParameterTimeZone(final HttpServletRequest request, final String param)
        throws BadInputException {
        int timeZone = getParameterInt(request, param, 0);
        if (timeZone < -12 || timeZone > 13) {
            timeZone = 0;
        }
        return timeZone;
    }

    public static String getAttribute(final HttpSession session, final String name) {
        String ret = (String)session.getAttribute(name);
        if (ret == null){
          ret = "";
        }
        return ret.trim();
    }

    public static String getAttribute(final HttpServletRequest request, final String name) {
        String ret = (String)request.getAttribute(name);
        if (ret == null){
          ret = "";
        }
        return ret.trim();
    }

    public static void getParameterMemberName(final String memberName) throws BadInputException{
        if (memberName.equalsIgnoreCase(BaseConstants.ADMIN_USERNAME) || memberName.equalsIgnoreCase("guest")) {
            throw new BadInputException("Cannot register member with a reserved name : " + memberName);
        }
    }

    public static void confirmPassword(final String str1, final String str2) throws BadInputException{
        if (!str1.equals(str2)) {
            throw new BadInputException( "Password and confirmed password are not the same, please try again.");
        }
    }

    public static void compareEmails(final String str1, final String str2) throws BadInputException{
        if (!str1.equals(str2)) {
            throw new BadInputException( "Your Primary Email and Confirmation Email are not be the same, please try again.");
        }
    }

  /**
   *
   * @todo review this method
   * @param request HttpServletRequest
   * @param param String
   * @param checkEmpty boolean
   * @throws BadInputException
   * @return String
   */
  public static String getParameterIM(final HttpServletRequest request, final String param, final boolean checkEmpty) throws BadInputException {
        String ret = getParameter(request, param, checkEmpty);
        if ( ret.indexOf('<') != -1 ||
             ret.indexOf('>') != -1 ||
             ret.indexOf('@') != -1) {
            throw new BadInputException("The parameter '" + param + "' is not allowed to contain '<' or '>' or '@@' ! Please try again.");
        }
        return ret;
    }

    public static void checkMemberStay(final int memberArrivalYear, final int memberDepartureYear)  throws BadInputException {
        if (memberDepartureYear < memberArrivalYear) {
            throw new BadInputException( "Your departure year is less than your arrival year, please try again.");
        }
    }

    public static String getParameterGender(final HttpServletRequest request, final String param) throws BadInputException{


        String inputStr = getParameter(request, param);
        logger.debug("The Gender is " + inputStr);
        if( inputStr.equalsIgnoreCase("--")){
          throw new BadInputException(
              "The Gender field is not allowed to be empty, please try again.");
        }
        return inputStr;
    }

    public static String getParameterDropDown(final HttpServletRequest request, final String param, final boolean checkEmpty, final String desc)
    throws BadInputException{
        String ret = getParameter(request, param, checkEmpty);
        if ( ret.indexOf('<') != -1 || ret.indexOf('>') != -1){
          throw new BadInputException("The parameter '" + param +
              "' is not allowed to contain '<' or '>' or '@@' ! Please try again.");
        }
        if (checkEmpty){
            if (ret.equals("--") || ret.equals(" ")){
              throw new BadInputException("The " + desc +
                  " field is not allowed to be empty, please try again.");
            }
        }
        return ret;
    }
}
