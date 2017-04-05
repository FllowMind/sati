/**
* 
*/
package com.jyu.sati.business.realm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Constants;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.token.KaptchaUsernamePasswordToken;
import com.jyu.sati.common.util.DateUtil;
import com.jyu.sati.entity.Permission;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.User;

/**
 * 登录授权
 * @author wjq
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private PermissionService permissionService;

	private static Logger logger = Logger.getLogger("common");

	/*
	 * 授权回调函数,登录后时调用
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登录的用户名
		String account = (String) super.getAvailablePrincipal(principal);
		List<Role> roles = new ArrayList<>();
		List<Permission> permissions = new ArrayList<>();
		List<String> roleNames = new ArrayList<>();
		List<String> permissionNames = new ArrayList<>();
		User user = null;
		try {
			// 获取用户
			user = permissionService.getUserByUserName(account);
		} catch (BusinessException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("登陆授权异常", e);
		}

		if (user != null) {
			roles = permissionService.getRolesByUserId(user.getUserId());
			if (roles.size() > 0) {
				for (Role role : roles) {
					roleNames.add(role.getRoleName());
					permissions = permissionService.getPermissionsByRoleId(role.getRoleId());
					if (permissions.size() > 0) {
						for (Permission perm : permissions) {
							// 判断父权限是否存在或可用
							if (perm.getFatherPermissionId() == null) {
								permissionNames.add(perm.getPermissionName());
							} else {
								if (permissionService.isFatherPermissionNormal(perm.getFatherPermissionId())) {
									permissionNames.add(perm.getPermissionName());
								}
							}
						}
					} else {
						logger.info("");
						throw new AuthorizationException("无法找到权限！角色ID" + "【" + role.getRoleId() + "】");
					}
				}
			} else {
				throw new AuthorizationException("无法找到角色！用户ID" + "【" + user.getUserId() + "】");
			}
		} else {
			throw new AuthorizationException("无法给当前用户设置角色权限！");
		}
		// 给当前用户设置角色
		info.addRoles(roleNames);
		// 给当前用户设置权限
		info.addStringPermissions(permissionNames);
		return info;
	}

	/*
	 * 
	 * 认证回调函数,登录时调用
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authencToken)
			throws AuthenticationException {
		// TODO Auto-generated method stub
 
		String userName = null;// 用户账号
		String password = null;// 用户密码
		if (authencToken instanceof KaptchaUsernamePasswordToken) {
			String kaptcha = null;// 验证码
			KaptchaUsernamePasswordToken token = (KaptchaUsernamePasswordToken) authencToken;
			kaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			// if(kaptcha==null||
			// !kaptcha.equalsIgnoreCase(token.getKaptcha())){
			// throw new IncorrectCaptchaException("验证码错误！");
			// }
			userName = token.getUsername();
			password = new String(token.getPassword());
		} else {
			UsernamePasswordToken token = (UsernamePasswordToken) authencToken;
			userName = token.getUsername();
			password = new String(token.getPassword());
		}

		User user = permissionService.getUserByUserNameAndPassword(userName, password);

		// 如果用户存在，则更新登录时间，shiro认证成功，返还令牌
		if (user != null) {
			if (user.getStatus()==User.USER_STATUS_LIMIT) {
				throw new BusinessException("当前用户已被禁用！");
			} else {

				SecurityUtils.getSubject().getSession().removeAttribute("loginUser");
				SecurityUtils.getSubject().getSession().setAttribute("loginUser", user);

				Date date = user.getCurrentloginTime();
				if (date != null) {
					user.setPreloginTime(date);// 如果当前登录时间不为空，设置为当前登录时时间
				}
				// 设置当前登录时间
				user.setCurrentloginTime(new Date());
				try {
					permissionService.saveUser(user);

					logger.info("【" + user.getUserId() + "】在" + DateUtil.datetimeToString(new Date()) + "登录系统\n");
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("更新用户登陆时间失败", e);
					e.printStackTrace();
				}
				return new SimpleAuthenticationInfo(user.getUserId(), user.getPassword(), getName());
			}
		}
		return null;

	}

}
