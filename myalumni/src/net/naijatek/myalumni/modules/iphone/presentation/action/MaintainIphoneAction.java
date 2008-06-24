package net.naijatek.myalumni.modules.iphone.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MaintainIphoneAction extends MyAlumniDispatchAction{

	private IMemberService memService;
	private static Log logger = LogFactory.getLog(MaintainIphoneAction.class);
	private static String FIRST_NAME = "firstName";
	private static String LAST_NAME = "lastName";
	private static String SORT_TYPE = "sortType";
	
	
    public MaintainIphoneAction(final IMemberService memService) {
        this.memService = memService;      
    }	
   
    
    public ActionForward filterfn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	logger.warn("***iPhone***: in Filter First Name...");
		MemberForm memberForm = (MemberForm) form;
		
		List<MemberVO> lstMemberVO = memService.getFirstNameStartingWith(memberForm.getAlpha(), BaseConstants.BOOLEAN_NO);
		if (lstMemberVO == null){
			lstMemberVO = new ArrayList<MemberVO>();
		}
			
		setRequestObject(request, BaseConstants.LIST_OF_IPHONE_MEMBERS, lstMemberVO);
		setRequestObject(request, SORT_TYPE, FIRST_NAME);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward filterln(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	logger.warn("***iPhone***: in Filter Last Name...");
		MemberForm memberForm = (MemberForm) form;	
		
		List<MemberVO> lstMemberVO = memService.getLastNameStartingWith(memberForm.getAlpha(), BaseConstants.BOOLEAN_NO);
		if (lstMemberVO == null){
			lstMemberVO = new ArrayList<MemberVO>();
		}
				
		setRequestObject(request, BaseConstants.LIST_OF_IPHONE_MEMBERS, lstMemberVO);
		setRequestObject(request, SORT_TYPE, LAST_NAME);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }        
    
    
    public ActionForward viewMember(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MemberForm memberForm = (MemberForm) form;	
		
		MemberVO memberVO = memService.getMemberProfileByMemberId(memberForm.getMemberId());
		if (memberVO == null){
			memberVO = new MemberVO();
		}
				
		setRequestObject(request, BaseConstants.IPHONE_MEMBER_PROFILE, memberVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    
    
    public ActionForward getDorms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<XlatDetailVO> lstXlatVO = memService.getAllActiveDormitory();
		if (lstXlatVO == null){
			lstXlatVO = new ArrayList<XlatDetailVO>();
		}
				
		setRequestObject(request, BaseConstants.LIST_OF_IPHONE_DORMS, lstXlatVO);
		setRequestObject(request, SORT_TYPE, FIRST_NAME);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    } 
    
    
    
    
    public ActionForward filterDorms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	logger.warn("***iPhone***: in Filter Dorms...");
		MemberForm memberForm = (MemberForm) form;	
		
		List<MemberVO> lstMemberVO = memService.getMembersInDorm(memberForm.getDormitoryId(), BaseConstants.BOOLEAN_NO);
		if (lstMemberVO == null){
			lstMemberVO = new ArrayList<MemberVO>();
		}
			
		setRequestObject(request, BaseConstants.LIST_OF_IPHONE_MEMBERS, lstMemberVO);
		setRequestObject(request, SORT_TYPE, FIRST_NAME);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    } 
    
   
    public ActionForward filterGender(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	logger.warn("***iPhone***: in Filter Gender...");
		MemberForm memberForm = (MemberForm) form;	
		
		List<MemberVO> lstMemberVO = memService.getGenderBy(memberForm.getAlpha(), BaseConstants.BOOLEAN_NO);
		if (lstMemberVO == null){
			lstMemberVO = new ArrayList<MemberVO>();
		}
			
		setRequestObject(request, BaseConstants.LIST_OF_IPHONE_MEMBERS, lstMemberVO);
		setRequestObject(request, SORT_TYPE, FIRST_NAME);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    } 
       
    
    
}
