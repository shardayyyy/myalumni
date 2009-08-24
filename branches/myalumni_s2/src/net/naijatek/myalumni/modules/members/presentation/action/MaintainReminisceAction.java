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
package net.naijatek.myalumni.modules.members.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.ReminisceVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.presentation.form.ReminisceForm;
import net.naijatek.myalumni.modules.common.service.IReminisceService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MaintainReminisceAction extends MyAlumniDispatchAction{

    private IReminisceService reminisceService;
    private static Log logger = LogFactory.getLog(MaintainReminisceAction.class);
    
    
    public MaintainReminisceAction(final IReminisceService reminisceService) {
        this.reminisceService = reminisceService;
    }
    
    public ActionForward listReminisce(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			Exception {
    	logger.debug("in listReminisce");
        
        List<ReminisceVO> list = reminisceService.findAllByStatus(BaseConstants.ACTIVE);       
    	setRequestObject(request, BaseConstants.LIST_OF_REMINISCE, list);
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareAddReminisce(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			Exception {
    	logger.debug("in prepareAddReminisce");
    	
    	MemberVO token = getCurrentLoggedInUser(request);
    	
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
          return mapping.findForward(BaseConstants.FWD_LOGIN);
        }
        
        ReminisceForm cnForm =  (ReminisceForm)form;
    	
    	cnForm.setFromYear(String.valueOf(token.getYearIn()));
    	cnForm.setToYear(String.valueOf(token.getYearOut()));
    	
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward addReminisce(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

    	logger.debug("in prepareAddReminisce");
    	
      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }
      
      ReminisceForm cnForm =  (ReminisceForm)form;
      ReminisceVO cnVO = new ReminisceVO();   

      BeanUtils.copyProperties(cnVO, cnForm);
      cnVO.setLastModifiedBy(getLastModifiedBy(request));
      cnVO.setAuthorId(getCurrentUserId(request));
      reminisceService.save(cnVO);
      ActionMessages errors = new ActionMessages();
      errors.add(BaseConstants.INFO_KEY, new ActionMessage("message.reminiscesubmitted"));
      saveMessages(request, errors);      
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
        
    
}
