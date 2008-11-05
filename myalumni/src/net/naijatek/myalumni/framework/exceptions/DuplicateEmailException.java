package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;


public class DuplicateEmailException extends MyGradeBookBaseException {

	/**
     * DuplicateEmailException
     *
     * @param msg String
     */
    public DuplicateEmailException(final String msg) {
      super(msg);
   }
}

