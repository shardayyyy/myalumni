package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;


public class InvalidLoginException extends MyGradeBookBaseException{


	public InvalidLoginException(final String msg) {
		super(msg);
	}

}

