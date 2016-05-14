package com.demo.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.ActiveUser;
import com.demo.model.SysPermission;
import com.demo.util.ResourcesUtil;


public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// 用户访问地址：
		String url = request.getRequestURI();

		// 校验用户访问是否是公开资源地址(无需认证即可访问)
		List<String> open_urls = ResourcesUtil.gekeyList("anonymousURL");
		// 用户访问的url
		for (String open_url : open_urls) {
			if (url.indexOf(open_url) >= 0) {
				// 如果访问的是公开 地址则放行
				return true;
			}
		}
		//从 session获取用户公共访问地址（认证通过无需分配权限即可访问）
		List<String> common_urls = ResourcesUtil.gekeyList("commonURL");
		// 用户访问的url
		for (String common_url : common_urls) {
			if (url.indexOf(common_url) >= 0) {
				// 如果访问的是公共地址则放行
				return true;
			}
		}
		// 从session获取用户权限信息

		HttpSession session = request.getSession();

		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");

		// 取出session中权限url
		// 获取用户操作权限
		List<SysPermission> permission_list = activeUser.getPermissions();
		// 校验用户访问地址是否在用户权限范围内
		for (SysPermission sysPermission : permission_list) {
			String permission_url = sysPermission.getUrl();
			if (url.contains(permission_url)) {
				return true;
			}
		}

		// 跳转到页面
		request.getRequestDispatcher("/refuse.jsp").forward(
				request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

}
