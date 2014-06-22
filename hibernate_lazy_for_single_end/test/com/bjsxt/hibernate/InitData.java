package com.bjsxt.hibernate;

import org.hibernate.Session;

public class InitData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

}
