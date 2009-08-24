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
package net.naijatek.myalumni.modules.admin.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.FrontPageVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.domain.XlatGroupVO;
import net.naijatek.myalumni.modules.common.presentation.form.FrontPageForm;
import net.naijatek.myalumni.modules.common.presentation.form.SystemGroupForm;
import net.naijatek.myalumni.modules.common.service.IFrontPageService;
import net.naijatek.myalumni.modules.common.service.IXlatGroupService;
import net.naijatek.myalumni.modules.common.service.IXlatService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class MaintainGeneralModuleAction  extends MyAlumniDispatchAction {

    private Log logger = LogFactory.getLog(this.getClass());
    
    private IXlatService xlatService;
    private IXlatGroupService xlatGroupService;
    private IFrontPageService frontPageService;
    

    /**
     * Instantiates the service classes
     * @param xlatService
     */
    public MaintainGeneralModuleAction(IXlatService xlatService, IXlatGroupService xlatGroupService, IFrontPageService frontPageService) {
        super();
        this.xlatService = xlatService ;
        this.xlatGroupService = xlatGroupService;
        this.frontPageService = frontPageService;
    }    

    // ----------------------------------
    // LOOKUP CODES 
    //-----------------------------------
    public ActionForward listLookupCodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listLookupCodes...");
        listLookupCodesHelper(request, form);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareAddLookupCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddLookupCode...");
        saveToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward addLookupCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addLookupCode...");        
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }
        SystemGroupForm detailForm = (SystemGroupForm)form;
        XlatDetailVO detailVO = new XlatDetailVO();
        BeanUtils.copyProperties(detailVO, detailForm);
        detailVO.setLastModifiedBy(getLastModifiedBy(request));
        xlatService.addXlatDetail(detailVO);
        listLookupCodesHelper(request, form);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareUpdateLookupCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateLookupCode...");
        saveToken(request);
        SystemGroupForm groupForm = (SystemGroupForm)form;
        XlatDetailVO detailVO = xlatService.getDetail(groupForm.getLookupGroupId(), groupForm.getLookupCodeId());
        BeanUtils.copyProperties(groupForm, detailVO);
        BeanUtils.copyProperties(groupForm, detailVO.getGroup());
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward updateLookupCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateLookupCode...");        
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }
        SystemGroupForm groupForm = (SystemGroupForm)form;
        xlatService.updateXlatDetail(getLastModifiedBy(request), groupForm.getLookupGroupId(), groupForm.getLookupCodeId(), 
        groupForm.getStatus(), groupForm.getLabel());
        listLookupCodesHelper(request, form);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    public void listLookupCodesHelper(HttpServletRequest request, ActionForm form) throws Exception {
        logger.debug("in listLookupCodesHelper...");
        SystemGroupForm groupForm = (SystemGroupForm)form;
        XlatGroupVO group = xlatGroupService.findById(groupForm.getLookupGroupId());
        group.setDetails(xlatService.getGroupDetails(groupForm.getLookupGroupId()));
        setRequestObject(request, BaseConstants.LIST_OF_CODES_FROM_GROUP, group);
    }    
    
    
    // ----------------------------------
    // LOOKUP GROUPS 
    //-----------------------------------
    public ActionForward listLookupGroups(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listLookupGroups...");   
        List<XlatGroupVO> groups = xlatGroupService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_LOOKUP_GROUPS,  groups);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }   
    
 
	//**********************************************************************
	//******************  FRONT PAGE       ********************************
	//********************************************************************** 	   
    public ActionForward prepareAddFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddFrontPageLinks...");  
        saveToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward listFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listFrontPageLinks...");       
        getFrontPageHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    

    public ActionForward viewFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewFrontPageLinks...");       
        FrontPageForm frontPageForm = (FrontPageForm) form;
        FrontPageVO frontPageVO = new FrontPageVO();
        frontPageVO = frontPageService.findById(frontPageForm.getLinkId());
        BeanUtils.copyProperties(frontPageForm, frontPageVO );
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
 
    public ActionForward updateFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateFrontPageLinks...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
        
        FrontPageForm frontPageForm = (FrontPageForm) form;
        FrontPageVO frontPageVO = new FrontPageVO();
        BeanUtils.copyProperties(frontPageVO, frontPageForm);
        frontPageVO.setLastModifiedBy(getLastModifiedBy(request));
        frontPageService.merge(frontPageVO);
        getFrontPageHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }


    public ActionForward addFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addFrontPageLinks...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        FrontPageForm frontPageForm = (FrontPageForm) form;
        FrontPageVO frontPageVO = new FrontPageVO();
        BeanUtils.copyProperties(frontPageVO, frontPageForm);
        frontPageVO.setLastModifiedBy(getLastModifiedBy(request));
        frontPageService.save(frontPageVO);
        getFrontPageHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward prepareUpdateFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateFrontPageLinks...");    
        saveToken(request);
        FrontPageForm frontPageForm = (FrontPageForm) form;
        FrontPageVO frontPageVO = frontPageService.findById(frontPageForm.getLinkId());
        BeanUtils.copyProperties(frontPageForm, frontPageVO);
        getFrontPageHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    


    public ActionForward deleteFrontPageLinks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in deleteFrontPageLinks...");        
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        FrontPageForm frontPageForm = (FrontPageForm) form;
        frontPageService.softDelete(frontPageForm.getLinkId(), getLastModifiedBy(request));
        getFrontPageHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }

    private void getFrontPageHelper(HttpServletRequest request){
        List<FrontPageVO> tasks =  frontPageService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_FRONT_PAGE_LINKS, tasks);        
    }
    
	    
    
}
