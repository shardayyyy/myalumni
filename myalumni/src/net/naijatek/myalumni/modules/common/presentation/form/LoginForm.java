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
package net.naijatek.myalumni.modules.common.presentation.form;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseForm;

public class LoginForm extends MyAlumniBaseForm {

	private String memberPassword;

	private String passwordConfirm;

	private String memberUserName;

	private String autoLoginExpire;

	private String memberTempPassword;

	private String memberPasswordConfirm;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberPasswordConfirm() {
		return memberPasswordConfirm;
	}

	public void setMemberPasswordConfirm(String memberPasswordConfirm) {
		this.memberPasswordConfirm = memberPasswordConfirm;
	}

	public String getMemberTempPassword() {
		return memberTempPassword;
	}

	public void setMemberTempPassword(String memberTempPassword) {
		this.memberTempPassword = memberTempPassword;
	}

	public void setMemberPassword(String password) {
		this.memberPassword = password;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setMemberUserName(String userName) {
		this.memberUserName = userName;
	}

	public String getMemberUserName() {
		return memberUserName;
	}

	public void setAutoLoginExpire(String autoLoginExpire) {
		this.autoLoginExpire = autoLoginExpire;
	}

	public String getAutoLoginExpire() {
		return autoLoginExpire;
	}
}
