/**
* 
*/
package com.jyu.sati.common.exception;
/**
* @author wjq
* @version 创建时间�?2017�?1�?10�? 上午11:42:36
*/

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author wjq
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectCaptchaException() {
		super();
	}

	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}

}
