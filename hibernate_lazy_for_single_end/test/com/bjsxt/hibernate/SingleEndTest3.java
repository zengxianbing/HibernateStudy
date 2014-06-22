package com.bjsxt.hibernate;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * <class>��ǩ�ϵ�lazy=false,����Ĭ��
 * @author Administrator
 *
 */
public class SingleEndTest3 extends TestCase {
	
	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�ᷢ��sql
			User user = (User)session.load(User.class, 1);
			
			//���ᷢ��sql
			System.out.println("user.name=" + user.getName());
			
			//���ᷢ��sql
			Group group = user.getGroup();
			
			//�ᷢ��sql
			System.out.println("group.name=" + group.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
}