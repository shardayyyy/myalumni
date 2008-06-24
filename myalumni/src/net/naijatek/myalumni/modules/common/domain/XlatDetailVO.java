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
package net.naijatek.myalumni.modules.common.domain;


public class XlatDetailVO  extends MyAlumniBaseVO{

	private String lookupCodeId;

	private String label;
	private String status;
	
	private String lookupGroupId;   // for group object
	private XlatGroupVO group;
	
	
	public XlatDetailVO() {
		super();
    }
	
    public XlatDetailVO(String id) {
    	this.lookupCodeId = id;
	}
    
    public String getSystemLookupId() {
		return lookupCodeId;
	}

	public void setSystemLookupId(String lookupId) {
		this.lookupCodeId = lookupId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLookupCodeId() {
		return lookupCodeId;
	}

	public void setLookupCodeId(String lookupId) {
		this.lookupCodeId = lookupId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLookupGroupId() {
		return lookupGroupId;
	}

	public void setLookupGroupId(String xlatGroupId) {
		this.lookupGroupId = xlatGroupId;
	}

	public XlatGroupVO getGroup() {
		return group;
	}

	public void setGroup(XlatGroupVO group) {
		this.group = group;
	}



}
