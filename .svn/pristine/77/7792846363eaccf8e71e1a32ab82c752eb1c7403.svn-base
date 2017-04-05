/**
* 
*/
package com.jyu.sati.common.token;
/**
* @author wjq
* @version ÂàõÂª∫Êó∂Èó¥Ôº?2017Âπ?1Êú?10Êó? ‰∏äÂçà11:24:56
*/

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author wjq
 *
 */
public class KaptchaUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String kaptcha = null;

	public KaptchaUsernamePasswordToken(String username, String  password,
			boolean rememberMe, String host, String kaptcha) {
		super(username, password, rememberMe, host);
		this.kaptcha = kaptcha;
	}
	
	public String getKaptcha() {
		return kaptcha;
	}

	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}
}
