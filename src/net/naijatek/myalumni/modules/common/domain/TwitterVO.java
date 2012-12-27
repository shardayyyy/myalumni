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
package net.naijatek.myalumni.modules.common.domain;

import java.util.List;



public class TwitterVO extends MyAlumniBaseVO{

	private String twitterId;
	private String twitteruser;
	private String twitterpswd;
	private List<String> mytweets;

	
	

    public TwitterVO() {
    }
	
    public TwitterVO(String id) {
    	this.twitterId = id;
	}


	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getTwitterpswd() {
		return twitterpswd;
	}

	public void setTwitterpswd(String twitterpswd) {
		this.twitterpswd = twitterpswd;
	}

	public String getTwitteruser() {
		return twitteruser;
	}

	public void setTwitteruser(String twitteruser) {
		this.twitteruser = twitteruser;
	}

	public List<String> getMytweets() {
		return mytweets;
	}

	public void setMytweets(List<String> mytweets) {
		this.mytweets = mytweets;
	}

    

	
}
