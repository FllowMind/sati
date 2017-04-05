package com.jyu.sati.business.action.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.action.BaseController;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.common.error.ExceptionObject;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.exception.IncorrectCaptchaException;
import com.jyu.sati.common.token.KaptchaUsernamePasswordToken;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.common.util.IPUtils;
import com.jyu.sati.common.util.PasswordUtil;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.entity.Info;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.User;
import com.jyu.sati.vo.RegisterVo;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	@Autowired
	private PermissionService permService;
	private User user = null;

	/**
	 * 获取当前用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCurrentUser", method = { RequestMethod.GET })
	public AjaxResult getCurrentUser() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		Subject currentUser = SecurityUtils.getSubject();
		String userId = (String) currentUser.getSession().getAttribute("currentUser");
		try {
			User user = permService.getUserByUserName(userId);
			user.setPassword("***");
			result.setSuccess(true);
			result.setResult(user);
			result.setMsg("获取当前用户成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("无法获取当前用户！");
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/loginAction", method = { RequestMethod.POST })
	public AjaxResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		AjaxResult result = getAjaxResult();
		String expMsg = "";
		String hostIP = request.getLocalAddr();
		String returnUrl = request.getParameter("returnUrl");
		// 保存用户登陆ip
		session.put("loginIP", IPUtils.getRealIPAddress(request));
		String userName = user.getUserId();
		String password = user.getPassword();
		String kaptchaCode = "";
		boolean rememberMe = true;// 是否记住密码

		if (!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(password)) {
			// 创建令牌
			Subject subject = SecurityUtils.getSubject();
			// 对密码进行加密
			password = PasswordUtil.getMD5Password(userName, password);

			KaptchaUsernamePasswordToken token = new KaptchaUsernamePasswordToken(userName, password, rememberMe,
					hostIP, kaptchaCode);

			try {
				// 使用令牌登录
				subject.login(token);
				subject.getSession().setAttribute("currentUser", userName);// 保存当前用户到session
				result.setSuccess(true);
				result.setMsg("登录成功！");
			} catch (Exception e) {
				result.setSuccess(false);
				if (e instanceof AuthenticationException) {
					e.printStackTrace();
					AuthenticationException authExp = (AuthenticationException) e;
					if (authExp != null) {
						Throwable throwable = authExp.getCause();
						if (throwable == null) {
							if (authExp instanceof IncorrectCredentialsException
									|| authExp instanceof UnknownAccountException) {
								expMsg = "用户账号或密码错误！";
							} else if (authExp instanceof IncorrectCaptchaException) {
								expMsg = "验证码错误";
							} else {
								expMsg = "登录失败！";
							}
						} else {
							if (throwable instanceof BusinessException) {
								BusinessException businessException = (BusinessException) throwable;
								expMsg = businessException.getMessage();
							} else {
								expMsg = "登录失败";
							}
						}
					}
				} else if (e instanceof BusinessException) {
					expMsg = e.getMessage();
				} else {
					logger.info(e.getMessage(), e);
					expMsg = "登录失败";
				}

				result.setMsg(expMsg);
			}
		} else {
			result.setSuccess(false);
			result.setMsg("账号或密码为空！");
		}
		return result;

	}

	/**
	 * 打开登录页面
	 * 
	 * @return
	 * 
	 */
	@RequestMapping("/login")
	public String loginpage() {
		return LOGIN;
	}

	/**
	 * 功能描述：注销
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout")
	public AjaxResult logout() {
		AjaxResult result = getAjaxResult();
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			result.setSuccess(true);
			result.setMsg("退出成功");
			logger.info("用户退出");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMsg("退出失败！");
		}
		return result;
	}

	/**
	 * 功能描述：注册 需要传入参数： 注册用户 userId;// 账号 password;// 密码 userType;// 用户类型
	 * email;// QQ邮箱 phoneNumber;//手机号码
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public AjaxResult addUser(@RequestBody RegisterVo info) {
		AjaxResult result = getAjaxResult();
		if (info.getUserType() < 1 && info.getUserType() > 7) {
			return getFailedRusult(result, "用户类型异常！");
		}
		// 限制不能从这个接口创建管理员
		if (info.getUserType() == User.USER_TYPE_PLATFORM && info.getUserType() == User.USER_TYPE_SUPER) {
			return getFailedRusult(result, "用户类型异常！");
		}
		try {
			permService.insertUser(info);
			result.setSuccess(true);
			result.setMsg("注册成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 功能描述：添加管理员 需要传入参数： 注册用户 userId;// 账号 password;// 密码 userType;// 用户类型
	 * email;// QQ邮箱 phoneNumber;//手机号码
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addAdministrator", method = { RequestMethod.POST })
	public AjaxResult addAdministrator(@RequestBody RegisterVo info) {
		AjaxResult result = getAjaxResult();
		if (info.getUserType() < 1 && info.getUserType() > 7) {
			return getFailedRusult(result, "用户类型异常！");
		}
		try {
			permService.insertUser(info);
			result = getSuccessRusult(result, "添加管理员成果");
		} catch (Exception e) {
			result = getFailedRusult(result, "添加管理员失败！");
		}
		return result;
	}

	/**
	 * 功能描述：删除用户
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	public AjaxResult removeUser(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		if (StringUtil.isNotEmpty(user.getUserId())) {
			if (permService.getUserByUserName(user.getUserId()) != null) {
				try {
					int res = permService.removeUser(user.getUserId());
					if (res > 0) {
						result = getSuccessRusult(result, "删除成功！");
					} else {
						result = getFailedRusult(result, "删除失败！");
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.setSuccess(false);
					result.setMsg("删除失败！");
					ExceptionObject excep = new ExceptionObject();
					excep.setMsg(e.getMessage());
					result.setError(excep);
				}
			} else {
				result.setSuccess(false);
				result.setMsg("该用户不存在！");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("用户账号为空！");
		}
		return result;
	}

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

}
