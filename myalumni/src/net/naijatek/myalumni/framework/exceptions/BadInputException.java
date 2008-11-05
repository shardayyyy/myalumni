package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;


public class BadInputException extends MyGradeBookBaseException {

	public BadInputException(final String msg) {
      super(msg);
   }
}

