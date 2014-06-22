package com.bjsxt.hibernate;

import org.hibernate.Session;

import junit.framework.TestCase;

public class Many2OneTest extends TestCase {

	public void testSave1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("��ѧ��");
			
			User user1 = new User();
			user1.setName("��10");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("�����");
			user2.setGroup(group);
			
			//���ܳɹ����棬�׳�TransientObjectException�쳣
			//��ΪGroupΪTran	sient״̬��oidû�з���ֵ
			//persistent״̬�Ķ����ǲ�������transient״̬�Ķ����
			session.save(user1);
			session.save(user2);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	public void testSave2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("��ѧ��");
			
			session.save(group);
			
			User user1 = new User();
			user1.setName("��10");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("�����");
			user2.setGroup(group);
			
			//������ȷ�洢
			session.save(user1);
			session.save(user2);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	public void testSave3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("��ѧ��");
			
			User user1 = new User();
			user1.setName("��10");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("�����");
			user2.setGroup(group);
			
			//�����׳��쳣����Ϊ������cascade���ԣ����������ȱ���Group
			//����cascade�����ǽ��TransientObjectException�쳣��һ���ֶ�			
			session.save(user1);
			session.save(user2);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			User user = (User)session.load(User.class, 3);
			System.out.println("user.name=" + user.getName());
			System.out.println("user.group.name=" + user.getGroup().getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
}
