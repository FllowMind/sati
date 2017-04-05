/**
* 
*/
package com.jyu.sati.common.exception;
/**
* @author wjq
* @version 创建时间�?2017�?1�?11�? 上午11:24:13
*/

import org.apache.log4j.Logger;

/**
 * 说明：这是业务异常类
 * @author wjq
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 普�?�日志（异常日志和操作数据日志）
	protected Logger commonLog = Logger.getLogger("common");

	/**
	 * 
	 */
	public BusinessException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		commonLog.error(message, cause);
	}

	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
		commonLog.info(message);
	}

	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
		commonLog.error(cause);
	}

}
