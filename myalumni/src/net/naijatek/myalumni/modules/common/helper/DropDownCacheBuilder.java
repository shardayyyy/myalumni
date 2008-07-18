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
