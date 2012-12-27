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
package net.naijatek.myalumni.modules.common.presentation.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.ErrorLogVO;
import net.naijatek.myalumni.modules.common.service.IErrorLogService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


public class ErrorAction extends MyAlumniDispatchAction {
    
	private IErrorLogService logService;
	//private static Log logger = LogFactory.getLog(ErrorAction.class);
    
    public ErrorAction(IErrorLogService logService) {        
        this.logService = logService;
    }
   
    
    public ActionForward errorTracker (ActionMapping mapping, 
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws Exception {
        
        String forwardKey = "error";
        ActionMessages errors = new ActionMessages();
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
        Throwable thr = (Throwable)request.getAttribute("javax.servlet.error.exception");
		StackTraceElement[] stack = thr.getStackTrace();
		StringBuffer strBuffer = new StringBuffer();
		for(int n = 0; n < stack.length; n++) {
			strBuffer.append(stack[n].toString());
			strBuffer.append("\n");
		}
		
        String messg = (String)request.getAttribute("javax.servlet.error.message");
        String cause = (String)request.getAttribute("javax.servlet.error.request_uri");        
        messg = (messg != null && messg.length() >= 4000) ? messg.substring(0, 3999) : messg;
        
		String trace = strBuffer.toString();
		trace = (trace != null && trace.length() >= 4000) ? trace.substring(0,3999) : trace;
		
        String userName = new String();
         
        try{
        	userName = getLastModifiedBy(request);
        }
        catch(Exception npe){
            //do nothing
        	userName = "";
        }
        
        ErrorLogVO errorLog = new ErrorLogVO();
        
        errorLog.setErrorDate(Calendar.getInstance().getTime());
        errorLog.setLoggedBy(userName);
        errorLog.setErrorMessage(messg);
        errorLog.setCause(cause);
        errorLog.setTrace(trace);
        errorLog.setLastModifiedBy(userName);
   
        logService.addErrorLog(errorLog);
        
        
        
        // 700: Insufficient Priviledges
        // 701: Session Timed Out
        
        if (statusCode != null) {
            int sc = ((Integer) statusCode).intValue();            
            
            switch (sc) {
            
            case 700:
                forwardKey = BaseConstants.SC_INSURFICIENT_PRIV_700;
                errors.add(BaseConstants.FATAL_KEY, new ActionMessage("core.errorcode.00707"));
                break;
            
            case 701:
                forwardKey = BaseConstants.SC_SESSION_EXPIRED_701;
                errors.add(BaseConstants.FATAL_KEY, new ActionMessage("core.errorcode.00708"));
                break;
            }            
        }
        
		
		saveMessages(request, errors);
        
        return mapping.findForward(forwardKey);
    }
    
    
}
