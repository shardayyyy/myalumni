package com.schoolsite.myGradebook.framework.exceptions;

import com.schoolsite.myGradebook.framework.struts.MyGradeBookBaseException;

public class NotLoginException extends MyGradeBookBaseException {
   

    public static final int NOT_LOGIN                   = 0;
    public static final int ACCOUNT_LOCKED              = 1;
    public static final int ACCOUNT_UNAPPROVED          = 2;
    public static final int ACCOUNT_DEACTIVATED         = 3;
    public static final int ACCOUNT_ACTIVE              = 4;     
    public static final int NOT_ENOUGH_RIGHTS           = 5;
    public static final int WRONG_USERNAME          		= 6;
    public static final int WRONG_PASSWORD          	= 7;  
    public static final int USER_NOT_FOUND          		= 8;     
    public static final int SAME_PASSWORD          		= 9; 
    public static final int PROMPT_CHANGE_PASSWORD		= 10;
    
    
    
    /**
     * int exceptionReason
     */
    public int exceptionReason = NOT_LOGIN;

    /**
     * NotLoginException
     * @param msg String
     */
    public NotLoginException(String msg) {
        super(msg);
    }

    /**
     * NotLoginException
     * @param reason int
     */
    public NotLoginException(int reason) {
        exceptionReason = reason;
    }

    /**
     * NotLoginException
     * @param msg String
     * @param reason int
     */
    public NotLoginException(String msg, int reason) {
        super(msg);
        exceptionReason = reason;
    }


    /**
     * getErrorCode
     * @return String
     */
    public int getErrorCode() {
        return exceptionReason;
    }

	public int getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(int exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

 

}
