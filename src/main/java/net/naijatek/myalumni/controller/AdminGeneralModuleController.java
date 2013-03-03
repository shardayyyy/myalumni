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
package net.naijatek.myalumni.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.entity.FrontPageVO;
import net.naijatek.myalumni.entity.XlatDetailVO;
import net.naijatek.myalumni.entity.XlatGroupVO;
import net.naijatek.myalumni.entity.SystemGroupVO;
import net.naijatek.myalumni.service.IFrontPageService;
import net.naijatek.myalumni.service.IXlatGroupService;
import net.naijatek.myalumni.service.IXlatService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/general")
public class AdminGeneralModuleController extends MyAlumniBaseController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IXlatService xlatService;

    @Autowired
    private IXlatGroupService xlatGroupService;

    @Autowired
    private IFrontPageService frontPageService;
    

    /**
     * Instantiates the service classes
     * @param xlatService
     */
    /**
     public MaintainGeneralModuleAction(IXlatService xlatService, IXlatGroupService xlatGroupService, IFrontPageService frontPageService) {
        super();
        this.xlatService = xlatService ;
        this.xlatGroupService = xlatGroupService;
        this.frontPageService = frontPageService;
    }    */

    // ----------------------------------
    // LOOKUP CODES 
    //-----------------------------------
    public ModelAndView listLookupCodes(SystemGroupVO systemGroupVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listLookupCodes...");
        listLookupCodesHelper(request, systemGroupVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView prepareAddLookupCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddLookupCode...");
        //saveToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView addLookupCode(@RequestParam("groupVO") SystemGroupVO detailForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addLookupCode...");        
//        if ( !isTokenValid(request) ) {
//            return  new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
//        }
        XlatDetailVO detailVO = new XlatDetailVO();
        BeanUtils.copyProperties(detailVO, detailForm);
        detailVO.setLastModifiedBy(getLastModifiedBy(request));
        xlatService.addXlatDetail(detailVO);
        listLookupCodesHelper(request, form);
        //resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView prepareUpdateLookupCode(@RequestParam("groupVO") SystemGroupVO groupVO,HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateLookupCode...");
        //saveToken(request);
        XlatDetailVO detailVO = xlatService.getDetail(groupVO.getLookupGroupId(), groupVO.getLookupCodeId());
        BeanUtils.copyProperties(groupVO, detailVO);
        BeanUtils.copyProperties(groupVO, detailVO.getGroup());
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView updateLookupCode(@RequestParam("groupVO") SystemGroupVO groupVO,  HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateLookupCode...");        
//        if ( !isTokenValid(request) ) {
//            return  new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
//        }
        xlatService.updateXlatDetail(getLastModifiedBy(request), groupVO.getLookupGroupId(), groupVO.getLookupCodeId(),
        groupVO.getStatus(), groupVO.getLabel());
        listLookupCodesHelper(request, form);
        //resetToken(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
    
    public void listLookupCodesHelper(SystemGroupVO groupVO, HttpServletRequest request) throws Exception {
        logger.debug("in listLookupCodesHelper...");

        XlatGroupVO group = xlatGroupService.findById(groupVO.getLookupGroupId());
        group.setDetails(xlatService.getGroupDetails(groupVO.getLookupGroupId()));
        setRequestObject(request, BaseConstants.LIST_OF_CODES_FROM_GROUP, group);
    }    
    
    
    // ----------------------------------
    // LOOKUP GROUPS 
    //-----------------------------------
    public ModelAndView listLookupGroups(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listLookupGroups...");   
        List<XlatGroupVO> groups = xlatGroupService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_LOOKUP_GROUPS,  groups);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }   
    
 
	//**********************************************************************
	//******************  FRONT PAGE       ********************************
	//********************************************************************** 	   
    public ModelAndView prepareAddFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddFrontPageLinks...");  
        //saveToken(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ModelAndView listFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listFrontPageLinks...");       
        getFrontPageHelper(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    

    public ModelAndView viewFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewFrontPageLinks...");
        FrontPageVO frontPageVO = new FrontPageVO();
        frontPageVO = frontPageService.findById(frontPageVO.getLinkId());
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
 
    public ModelAndView updateFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateFrontPageLinks...");
//        if ( !isTokenValid(request) ) {
//            return  new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
//        }
        
        FrontPageVO frontPageVO = new FrontPageVO();
        frontPageVO.setLastModifiedBy(getLastModifiedBy(request));
        frontPageService.merge(frontPageVO);
        getFrontPageHelper(request);
        //resetToken(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    public ModelAndView addFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addFrontPageLinks...");
//        if ( !isTokenValid(request) ) {
//            return  new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
//        }
		if (!adminSecurityCheck(request)) {
			return  new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
		}
        FrontPageVO frontPageVO = new FrontPageVO();
        BeanUtils.copyProperties(frontPageVO, frontPageForm);
        frontPageVO.setLastModifiedBy(getLastModifiedBy(request));
        frontPageService.save(frontPageVO);
        getFrontPageHelper(request);
        //resetToken(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ModelAndView prepareUpdateFrontPageLinks(@RequestParam("frontPageVO") FrontPageVO frontPageVO,  HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateFrontPageLinks...");    
        //saveToken(request);

        FrontPageVO frontPageVO = frontPageService.findById(frontPageForm.getLinkId());
        BeanUtils.copyProperties(frontPageForm, frontPageVO);
        getFrontPageHelper(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    


    public ModelAndView deleteFrontPageLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in deleteFrontPageLinks...");        
		if (!adminSecurityCheck(request)) {
			return  new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        FrontPageVO frontPageForm = (FrontPageVO) form;
        frontPageService.softDelete(frontPageForm.getLinkId(), getLastModifiedBy(request));
        getFrontPageHelper(request);
        return  new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    private void getFrontPageHelper(HttpServletRequest request){
        List<FrontPageVO> tasks =  frontPageService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_FRONT_PAGE_LINKS, tasks);        
    }
    
	    
    
}
