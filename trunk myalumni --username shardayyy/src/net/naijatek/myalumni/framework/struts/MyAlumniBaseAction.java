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
package net.naijatek.myalumni.framework.struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * This is the base class from_email which all Action classes that use
 * Struts can be derived from_email. It takes into consideration some
 * general architecture that would most likely be needed in a real
 * application. For the purpose of this article, those methods that
 * are not directly related to_email the struts framework will be black-boxed
 * and commented so that you can use this as a skeleton and fill in those
 * methods as you see fit while you are doing development.
 * All action classes need to_email be derived from_email org.apache.struts.action.Action
 * @see org.apache.struts.action.Action
 * @author Folashade Adeyosoye Naijatek Solutions www.naijatek.com
 */
abstract public class MyAlumniBaseAction extends Action {


 //================================================== EXTENSION POINT

  abstract public ActionForward executeAction(ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws
      Exception;

//================================================== ANCESTOR METHODS

  /**
   *
   * @param mapping ActionMapping
   * @param form ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return org.apache.struts.action.ActionForward
   * @throws Exception
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

//    MemberVO onlineUser = new MemberVO();
//    onlineUser = getSessionMember(request);

//    if (onlineUser != null){
//      onlineUser.setLastRequestTime(new Date());
//      setSessionObject(request, BaseConstants.USER_KEY, onlineUser );
//    }

    return executeAction(mapping, form, request, response);

  }

//--------------------------------------------------------------------------------------


}
