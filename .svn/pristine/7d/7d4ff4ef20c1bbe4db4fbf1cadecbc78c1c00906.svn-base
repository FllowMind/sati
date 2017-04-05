package com.jyu.sati.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.jyu.sati.common.error.ExceptionObject;

/**
 * 
 * <p>
 * DOCUMENTME <code>com.szm.core.common.utils.AjaxResult</code>类的说明
 * </p>
 * <p>
 * Function List:
 * <ul>
 * </ul>
 * </p>
 * <p>
 * Revision History:
 * <ul>
 * <li>2014-3-5 louis.tsang 初始版本</li>
 * </ul>
 * </p>
 *
 * @author louis.tsang
 * @version $Id$
 */

public class AjaxResult {
	private boolean success = true;
	
	private LinkedHashMap<String,String> errors = new LinkedHashMap<String,String>();
	
	private String msg;
	
	private List<String> errorInfos = new ArrayList<String>();

	private Object result;
	
	private Object otherObj;
	
	private Object resultList;
	
	private  ExceptionObject error;
	
	private String serialNo;
	
	public List<String> getErrorInfos() {
		return errorInfos;
	}

	public void setErrorInfos(List<String> errorInfos) {
		this.errorInfos = errorInfos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public LinkedHashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(LinkedHashMap<String, String> errors) {
		this.errors = errors;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

    public Object getOtherObj() {
        return otherObj;
    }

    public void setOtherObj(Object otherObj) {
        this.otherObj = otherObj;
    }

	public ExceptionObject getError() {
		return error;
	}

	public void setError(ExceptionObject error) {
		this.error = error;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Object getResultList() {
		return resultList;
	}

	public void setResultList(Object resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "AjaxResult [success=" + success + ", errors=" + errors + ", msg=" + msg + ", errorInfos=" + errorInfos
				+ ", result=" + result + ", otherObj=" + otherObj + ", resultList=" + resultList + ", error=" + error
				+ ", serialNo=" + serialNo + "]";
	}
	
	
}
