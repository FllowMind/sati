package com.jyu.sati.business.service.impl;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BaseServiceImpl {
	public Logger log = Logger.getLogger("common");
	public String Msg = null;

	/**
	 * 错误日志
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @param dataInfo
	 *            发生错误的数据
	 */
	public void errorLog(String errorMsg, String dataInfo) {
		this.setMsg(errorMsg);
		log.error(errorMsg + "发生错误数据【" + dataInfo + "】");
	}

	/**
	 * 错误日志
	 * 
	 * @param errorMsg
	 *            错误信息
	 */
	public void errorLog(String errorMsg) {
		this.setMsg(errorMsg);
		log.error(errorMsg);
	}

	/**
	 * 错误日志,并把调用e.printStackTrace
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @param e
	 *            发生错误的异常
	 */
	public void errorLog(String errorMsg, Exception e) {
		this.setMsg(errorMsg);
		e.printStackTrace();
		log.error(errorMsg, e);
	}

	/**
	 * 错误日志,并把调用e.printStackTrace
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @param dataInfo
	 *            发生错误的数据
	 * @param e
	 *            发生错误的异常
	 */
	public void errorLog(String errorMsg, String dataInfo, Exception e) {
		this.setMsg(errorMsg);
		e.printStackTrace();
		log.error(errorMsg + "发生错误数据【" + dataInfo + "】", e);
	}

	/**
	 * 信息日志
	 * 
	 * @param infoMsg
	 * @param dataInfo
	 */
	public void infoLog(String infoMsg) {
		this.setMsg(infoMsg);
		log.info(infoMsg);
	}

	/**
	 * 获取当前用户Id
	 * 
	 * @return
	 */
	public String getCurrentUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		return (String) currentUser.getSession().getAttribute("currentUser");
	}

	/**
	 * 获取错误信息
	 * 
	 * @return
	 */
	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

}
