package com.demo.shiro;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 *
 * @author DarkIdiot-PC
 * @date 2016��3��10�� ����12:44:32
 */
public class TestAuthorization {
	/**
	 * IniSecurityManagerFactory�Ǵ���securityManager�Ĺ���������Ҫһ��ini�����ļ�·������֧�֡�
	 * classpath:������·��������file:�����ļ�ϵͳ������url:�������磩����·����ʽ��Ĭ�����ļ�ϵͳ;
	 */
	// ��ɫ��Ȩ����Դ��Ȩ����
	@Test
	public void testAuthorization() {

		// ����SecurityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-permission.ini");

		// ����SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// ��SecurityManager���õ�ϵͳ���л�������spring��SecurityManager����spring�����У�һ�㵥������
		SecurityUtils.setSecurityManager(securityManager);

		// ����subject
		Subject subject = SecurityUtils.getSubject();

		// ����token����
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
				"123");

		// ִ����֤
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		System.out.println("��֤״̬��" + subject.isAuthenticated());
		// ��֤ͨ����ִ����Ȩ

		// ���ڽ�ɫ����Ȩ
		// hasRole�����ɫ��ʶ
		boolean ishasRole = subject.hasRole("role1");
		System.out.println("������ɫ�ж�" + ishasRole);
		// hasAllRoles�Ƿ�ӵ�ж����ɫ
		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1",
				"role2", "role3"));
		System.out.println("�����ɫ�ж�" + hasAllRoles);

		// ʹ��check����������Ȩ�������Ȩ��ͨ�����׳��쳣org.apache.shiro.authz.UnauthorizedException
		// subject.checkRole("role13");

		// ������Դ����Ȩ
		// isPermitted����Ȩ�ޱ�ʶ��
		boolean isPermitted = subject.isPermitted("user:create:1");
		System.out.println("����Ȩ���ж�" + isPermitted);

		boolean isPermittedAll = subject.isPermittedAll("user:create:1",
				"user:delete");
		System.out.println("���Ȩ���ж�" + isPermittedAll);

		// ʹ��check����������Ȩ�������Ȩ��ͨ�����׳��쳣org.apache.shiro.authz.UnauthorizedException
		subject.checkPermission("items:update:1");

	}

	// �Զ���realm������Դ��Ȩ����
	@Test
	public void testAuthorizationCustomRealm() {

		// ����SecurityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");

		// ����SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// ��SecurityManager���õ�ϵͳ���л�������spring��SecurityManager����spring�����У�һ�㵥������
		SecurityUtils.setSecurityManager(securityManager);

		// ����subject
		Subject subject = SecurityUtils.getSubject();

		// ����token����
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
				"111111");

		// ִ����֤
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		System.out.println("��֤״̬��" + subject.isAuthenticated());
		// ��֤ͨ����ִ����Ȩ

		// ������Դ����Ȩ������isPermitted���������CustomRealm�����ݿ��ѯ��ȷȨ������
		// isPermitted����Ȩ�ޱ�ʶ�����ж�user:create:1�Ƿ���CustomRealm��ѯ��Ȩ������֮��
		boolean isPermitted = subject.isPermitted("user:create:1");
		System.out.println("����Ȩ���ж�" + isPermitted);

		boolean isPermittedAll = subject.isPermittedAll("user:create:1",
				"user:create");
		System.out.println("���Ȩ���ж�" + isPermittedAll);

		// ʹ��check����������Ȩ�������Ȩ��ͨ�����׳��쳣org.apache.shiro.authz.UnauthorizedException
		subject.checkPermission("items:add:1");

	}
}
