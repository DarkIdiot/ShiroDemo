package com.demo.shiro;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * shiro hash test 
 */
public class TestMD5 {

	@Test
	public void TestMD5() {
		// ԭʼ ����
		String source = "111111";
		// ��
		String salt = "qwerty";
		// ɢ�д���
		int hashIterations = 2;
		// �ϱ�ɢ��1�Σ�f3694f162729b7d0254c6e40260bf15c
		// �ϱ�ɢ��2�Σ�36f2dfa24d0a9fa97276abbe13e596fc

		// ���췽���У�
		// ��һ�����������ģ�ԭʼ����
		// �ڶ����������Σ�ͨ��ʹ�������
		// ������������ɢ�еĴ���������ɢ�����Σ��൱ ��md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, 1);

		String password_md5 = md5Hash.toString();
		System.out.println(password_md5);
		// ��һ��������ɢ���㷨
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, 2);
		System.out.println(simpleHash.toString());
	}

}
