package net.naijatek.myalumni.modules.common.helper;

import java.util.ArrayList;
import java.util.List;

import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.AppProp;

import org.apache.struts.util.LabelValueBean;
import org.springframework.web.context.ContextLoaderServlet;

public class DropDownCacheBuilder  extends ContextLoaderServlet{
	
	public DropDownCacheBuilder(){
	
	}
	
	public List<LabelValueBean> buildSearchOptions(String hasDormitory, Boolean adminMenu){
		AppProp appProp = AppProp.getInstance();
		
	     // Admin Search Category
	     List<LabelValueBean> searchCategory = new ArrayList<LabelValueBean>();
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.fullsearch"), BaseConstants.FULL_SEARCH));
	     
	     if (adminMenu){
	    	 searchCategory.add(new LabelValueBean(appProp.getValue("label.username"), BaseConstants.USERNAME));
	    	 searchCategory.add(new LabelValueBean(appProp.getValue("label.email"), BaseConstants.EMAIL));
	     }
	     
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.firstname"), BaseConstants.FIRST_NAME));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.lastname"), BaseConstants.LAST_NAME));
	     if (hasDormitory != null && hasDormitory.equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
	    	 searchCategory.add(new LabelValueBean(appProp.getValue("label.dormitory"), BaseConstants.DORMITORY));
	     }     
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.yearin"), BaseConstants.YEAR_IN));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.yearout"), BaseConstants.YEAR_OUT));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.nickname"), BaseConstants.NICK_NAME));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.marriedname"), BaseConstants.MARRIED_NAME));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.namebeforemarriage"), BaseConstants.MAIDEN_NAME));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.gender"), BaseConstants.GENDER));
	     searchCategory.add(new LabelValueBean(appProp.getValue("label.avatar"), BaseConstants.AVATAR));
	     
	     return searchCategory;
	}
	
	
	
}
