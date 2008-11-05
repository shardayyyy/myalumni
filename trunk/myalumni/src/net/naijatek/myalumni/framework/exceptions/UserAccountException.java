package com.schoolsite.myGradebook.framework.exceptions;

/**
 * A runtime exception thrown by all methods of user profile management class
 * to indicate an error condition.
 *
 */
public class UserAccountException extends NotLoginException  {
    

	/**
    * Default Constructor. Takes no arguments
    *
    * @since 1.0
    */
    public UserAccountException(String message) {
        super(message);
    }
    
    /**
    * Constructor
    *
    * @param <b> message </b> The detailed exception message.
    *
    * @since 1.0
    */
    public UserAccountException(int message) {
         super(message);
    }

    /**
    * Constructor
    *
    * @param <b>thr</b> The Throwable class.
    *
    * @since 1.0
    */
    public UserAccountException(final Throwable thr) {
        super((thr == null) ? null : thr.getMessage());
    }
}
