package net.naijatek.myalumni.modules.admin.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.domain.XlatGroupVO;
import net.naijatek.myalumni.modules.common.presentation.form.SystemGroupForm;
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
    

    /**
     * Instantiates the service classes
     * @param xlatService
     */
    public MaintainGeneralModuleAction(IXlatService xlatService, IXlatGroupService xlatGroupService) {
        super();
        this.xlatService = xlatService ;
        this.xlatGroupService = xlatGroupService;
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
    
 
    
    
}
