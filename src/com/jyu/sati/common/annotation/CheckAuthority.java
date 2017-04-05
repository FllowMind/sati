package com.jyu.sati.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 * DOCUMENTME <code>com.szm.core.common.CheckAuthority</code>类的说明
 * </p>
 * <p>
 * Function List:
 * <ul>
 * <li> 自定义权限注解</li>
 * </ul>
 * </p>
 * <p>
 * Revision History:
 * <ul>
 * <li>2014-3-3 bry 初始版本</li>
 * </ul>
 * </p>
 *
 * @author bry
 * @author louis.tsang
 * @version $Id$
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface CheckAuthority {
    /**
     * 用户类型
     * @return
     */
	public String userType()default ""; 
	 /**
     * 用户权限
     * @return
     */
	public String permission()default "";
	 /**
     * 自定义信息
     * @return
     */
	public String message()default "";
}
