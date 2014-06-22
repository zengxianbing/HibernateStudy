package com.bjsxt.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class SessionFlushTest extends TestCase {
	
	/**
	 * ����uuid�������ɲ���
	 */
	public void testSave1() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User1 user = new User1();
			user.setName("����");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//��Ϊuser���������ɲ�·���õ���uuid�����Ե������save��ֻ�ǽ�user���뵽��session�Ĺ���
			//���ᷢ��insert��䣬����id�Ѿ����ɣ�session��existsInDatebase״̬Ϊfalse
			session.save(user);
			
			//����flush��hibernate�������棬ִ��sql
			//������ݿ�ĸ��뼶������ΪΪ�ύ������ô���ǿ��Կ���flush��������
			//����session��existsInDatebase״̬Ϊtrue
			session.flush();
			
			//�ύ����
			//Ĭ�������commit��������ִ��flush�����棬���Բ�����ʾ�ĵ���flush
			//commit���������޷��ع���
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * ����native�������ɲ���
	 */
	public void testSave2() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User2 user = new User2();
			user.setName("����1");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//��Ϊuser���������ɲ���Ϊnative,���Ե���session.save�󣬽�ִ��insert��䣬���������ݿ����ɵ�id
			//������session�Ĺ����޸���session��existsInDatebase״̬Ϊtrue
			//������ݿ�ĸ��뼶������ΪΪ�ύ������ô���ǿ��Կ���save��������
			session.save(user);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	
	/**
	 * ����uuid�������ɲ���
	 */
	public void testSave3() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User1 user = new User1();
			user.setName("����");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//��Ϊuser���������ɲ�·���õ���uuid�����Ե������save��ֻ�ǽ�user���뵽��session�Ĺ���
			//���ᷢ��insert��䣬����id�Ѿ����ɣ�session��existsInDatebase״̬Ϊfalse
			session.save(user);
			
			//��user�����session���������session��EntityEntries���������
			session.evict(user);
			
			//�޷��ɹ��ύ����Ϊhibernate��������ʱ����session��insertions������ȡ��user�������insert������
			//��Ҫ����entityEntries�����е�existsInDatabaseΪtrue�������ǲ���evict�Ѿ���user��session��entityEntries
			//������ˣ������Ҳ���������ݣ��޷����£��׳��쳣
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * ����uuid�������ɲ���
	 */
	public void testSave4() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User1 user = new User1();
			user.setName("����");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//��Ϊuser���������ɲ�·���õ���uuid�����Ե������save��ֻ�ǽ�user���뵽��session�Ĺ���
			//���ᷢ��insert��䣬����id�Ѿ����ɣ�session��existsInDatebase״̬Ϊfalse
			session.save(user);
			
			//flush��hibernate�������棬�Ὣuser���󱣴浽���ݿ��У���session�е�insertions�е�user����
			//�������������session��existsInDatebase��״̬Ϊtrue
			session.flush();
			
			//��user�����session���������session��EntityEntries���������
			session.evict(user);
			
			//���Գɹ��ύ����Ϊhibernate��������ʱ����session��insertions�������޷��ҵ�user����
			//���ԾͲ��ᷢ��insert��䣬Ҳ�������session�е�existsInDatabase��״̬
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * ����native�������ɲ���
	 */
	public void testSave5() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User2 user = new User2();
			user.setName("����11");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//��Ϊuser���������ɲ���Ϊnative,���Ե���session.save�󣬽�ִ��insert��䣬���������ݿ����ɵ�id
			//������session�Ĺ����޸���session��existsInDatebase״̬Ϊtrue
			//������ݿ�ĸ��뼶������ΪΪ�ύ������ô���ǿ��Կ���save��������
			session.save(user);
			
			//��user�����session���������session��EntityEntries���������
			session.evict(user);
			
			//���Գɹ��ύ����Ϊhibernate��������ʱ����session��insertions�������޷��ҵ�user����
			//���ԾͲ��ᷢ��insert��䣬Ҳ�������session�е�existsInDatabase��״̬
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * ����assigned�������ɲ���
	 * 
	 */
	public void testSave6() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User3 user = new User3();
			user.setId("001");
			user.setName("����");
			
			session.save(user);
			
			user.setName("����");
			session.update(user);
			
			User3 user3 = new User3();
			user3.setId("002");
			user3.setName("����");
			session.save(user3);
			
			//Hibernate: insert into t_user3 (name, password, create_time, expire_time, user_id) values (?, ?, ?, ?, ?)
			//Hibernate: insert into t_user3 (name, password, create_time, expire_time, user_id) values (?, ?, ?, ?, ?)
			//Hibernate: update t_user3 set name=?, password=?, create_time=?, expire_time=? where user_id=?
			//hibernate����save(insert),update��delete˳���ύ��ز���
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	/**
	 * ����assigned�������ɲ���
	 * 
	 */
	public void testSave7() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			User3 user = new User3();
			user.setId("003");
			user.setName("����");
			
			session.save(user);
			
			user.setName("����");
			session.update(user);
			
			session.flush();
			
			User3 user3 = new User3();
			user3.setId("004");
			user3.setName("����");
			session.save(user3);
			
			//Hibernate: insert into t_user3 (name, password, create_time, expire_time, user_id) values (?, ?, ?, ?, ?)
			//Hibernate: update t_user3 set name=?, password=?, create_time=?, expire_time=? where user_id=?
			//Hibernate: insert into t_user3 (name, password, create_time, expire_time, user_id) values (?, ?, ?, ?, ?)
			//��Ϊ������session.udpate(user)��ִ����flush��������������ʱִ��flushǰ��sql��������
			//sql�ᰴ�����ǵ���Ըִ��
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}		
}
