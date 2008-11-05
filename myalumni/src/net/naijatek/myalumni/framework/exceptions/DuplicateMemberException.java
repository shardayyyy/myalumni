package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;



public class DuplicateMemberException extends MyGradeBookBaseException {

	/**
     * DuplicateMemberException
     *
     * @param msg String
     */
    public DuplicateMemberException(final String msg) {
      super(msg);
   }
}
