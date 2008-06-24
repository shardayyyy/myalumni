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
package net.naijatek.myalumni.modules.members.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.ClassNewsVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.presentation.form.ClassNewsForm;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MaintainClassNewsAction extends MyAlumniDispatchAction{

    private IClassNewsService classNewsService;
    private static Log logger = LogFactory.getLog(MaintainClassNewsAction.class);
    
    
    public MaintainClassNewsAction(final IClassNewsService classNewsService) {
        this.classNewsService = classNewsService;
    }
    
    public ActionForward listClassNews(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			Exception {
    	logger.debug("in listClassNews");
    	
    	MemberVO token = getCurrentLoggedInUser(request);
    	
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
          return mapping.findForward(BaseConstants.FWD_LOGIN);
        }
        
        List<ClassNewsVO> list = classNewsService.findAllByYearOut(token.getYearOut());       
    	setRequestObject(request, BaseConstants.LIST_OF_CLASSNEWS, list);
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareAddClassNews(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			Exception {
    	logger.debug("in prepareAddClassNews");
    	
    	MemberVO token = getCurrentLoggedInUser(request);
    	
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
          return mapping.findForward(BaseConstants.FWD_LOGIN);
        }
        
        ClassNewsForm cnForm =  (ClassNewsForm)form;
    	
    	cnForm.setFromClassYear(String.valueOf(token.getYearIn()));
    	cnForm.setToClassYear(String.valueOf(token.getYearOut()));
    	
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward addClassNews(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

    	logger.debug("in prepareAddClassNews");
    	
      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }
      
      ClassNewsForm cnForm =  (ClassNewsForm)form;
      ClassNewsVO cnVO = new ClassNewsVO();   

      BeanUtils.copyProperties(cnVO, cnForm);
      cnVO.setLastModifiedBy(getLastModifiedBy(request));
      cnVO.setAuthorId(getCurrentUserId(request));
      classNewsService.save(cnVO);
      ActionMessages errors = new ActionMessages();
      errors.add(BaseConstants.INFO_KEY, new ActionMessage("message.classnewssubmitted"));
      saveMessages(request, errors);      
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    

    
    public ActionForward viewClassNews(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      logger.debug("in viewClassNews");
      ClassNewsForm cnForm =  (ClassNewsForm)form;
      ClassNewsVO cnVO = new ClassNewsVO();   
      
      cnVO = classNewsService.findById(cnForm.getClassNewsId());
      BeanUtils.copyProperties(cnForm, cnVO);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }       
    
}
