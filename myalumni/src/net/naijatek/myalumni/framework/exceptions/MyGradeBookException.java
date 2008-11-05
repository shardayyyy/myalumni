package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;

public class MyGradeBookException extends MyGradeBookBaseException {

	/**
     * UnauthorizedException
     *
     * @param msg String
     */
	public MyGradeBookException(final String msg) {
		super(msg);
	}

}
