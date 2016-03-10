package com.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * shiro authentication test
 */
public class TestAuthentication {
	/**
	 * IniSecurityManagerFactory�Ǵ���securityManager�Ĺ���������Ҫһ��ini�����ļ�·������֧�֡�
	 * classpath:������·��������file:�����ļ�ϵͳ������url:�������磩����·����ʽ��Ĭ�����ļ�ϵͳ;
	 */
	// �û���½���˳�
	@Test
	public void testLoginAndLogout() {

		// ����securityManager������ͨ��ini�����ļ�����securityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-first.ini");

		// ����SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// ��securityManager���õ�ǰ�����л�����
		SecurityUtils.setSecurityManager(securityManager);

		// ��SecurityUtils��ߴ���һ��subject
		Subject subject = SecurityUtils.getSubject();

		// ����֤�ύǰ׼��token�����ƣ�
		// ������˺ź����� ���������û������ȥ
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
				"111111");

		try {
			// ִ����֤�ύ
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// �Ƿ���֤ͨ��
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("�Ƿ���֤ͨ����" + isAuthenticated);

		// �˳�����
		subject.logout();

		// �Ƿ���֤ͨ��
		isAuthenticated = subject.isAuthenticated();

		System.out.println("�Ƿ���֤ͨ����" + isAuthenticated);

	}

	// �Զ���realm
	@Test
	public void testCustomRealm() {

		// ����securityManager������ͨ��ini�����ļ�����securityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");

		// ����SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// ��securityManager���õ�ǰ�����л�����
		SecurityUtils.setSecurityManager(securityManager);

		// ��SecurityUtils��ߴ���һ��subject
		Subject subject = SecurityUtils.getSubject();

		// ����֤�ύǰ׼��token�����ƣ�
		// ������˺ź����� ���������û������ȥ
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
				"111111");

		try {
			// ִ����֤�ύ
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// �Ƿ���֤ͨ��
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("�Ƿ���֤ͨ����" + isAuthenticated);

	}

	// �Զ���realmʵ��ɢ��ֵƥ��
	@Test
	public void testCustomRealmMd5() {

		// ����securityManager������ͨ��ini�����ļ�����securityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm-md5.ini");

		// ����SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// ��securityManager���õ�ǰ�����л�����
		SecurityUtils.setSecurityManager(securityManager);

		// ��SecurityUtils��ߴ���һ��subject
		Subject subject = SecurityUtils.getSubject();

		// ����֤�ύǰ׼��token�����ƣ�
		// ������˺ź����� ���������û������ȥ
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
				"222222");

		try {
			// ִ����֤�ύ
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// �Ƿ���֤ͨ��
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("�Ƿ���֤ͨ����" + isAuthenticated);

	}

}
