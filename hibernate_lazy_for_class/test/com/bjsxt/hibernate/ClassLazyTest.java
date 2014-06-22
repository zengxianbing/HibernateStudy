package com.bjsxt.hibernate;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * ���б���Ԫ���Ե�������
 * 	
 * ����<class>��ǩ�ϵ�lazy=true��Ҳ����Ĭ������
 *  
 * @author Administrator
 *
 */
public class ClassLazyTest extends TestCase {

	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//���ᷢ��sql
			Group group = (Group)session.load(Group.class, 1);
			
			//���ᷢ��sql
			System.out.println("group.id=" + group.getId());
			
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

	public void testLoad2() {
		Session session = null;
		Group group = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			group = (Group)session.load(Group.class, 1);
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		//������ȷ������׳�LazyInitializationException �쳣����Ϊsession�Ѿ��ر�
		//hibernate֧��lazy����ֻ����session��״̬����Ч
		System.out.println("group.name=" + group.getName());
	}	
	
}
