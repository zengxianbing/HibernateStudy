package com.bjsxt.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class SessionTest extends TestCase {

	public void testHello1() {
		System.out.println("-----------SessionTest.Hello1()------------");
		//throw new java.lang.RuntimeException();
	}
	
	public void testHello2() {
		System.out.println("-----------SessionTest.testHello2()------------");
		
		//this.assertEquals("hello", "hello111");
	}
	
	public void testSave1() {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			
			//Transient״̬
			user = new User();
			user.setName("����");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());
			
			//persistent״̬,�����Է����ı��ʱ��hibernate���Զ������ݿ�ͬ��
			session.save(user);
			
			user.setName("����");
			//session.update(user);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		//detached״̬
		user.setName("����");
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
	 		//persistent״̬
			session.update(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}		
	}
	
	public void testReadByGetMethod1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//���Ϸ�����ѯsql������User����
			User user = (User)session.get(User.class, "402880d01b9bf210011b9bf2a2ff0001");
			System.out.println("user.name=" + user.getName());
			
			//persistent״̬,�����Է����ı��ʱ��hibernate���Զ������ݿ�ͬ��
			user.setName("����");
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	public void testReadByGetMethod2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//����get�������ݣ�������ݿ��в�������Ӧ�����ݣ�����null
			User user = (User)session.get(User.class, "asdfsafsdfdsf");

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	public void testReadByLoadMethod1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//���ᷢ����ѯsql����Ϊload����ʵ����lazy�������ػ��ӳټ��أ�
			//�ӳټ��أ�ֻ������ʹ����������ʱ�򣬲ż��أ�����sql��䣩
			//hibernate�ӳټ���ʵ��ԭ���Ǵ���ʽ
			User user = (User)session.load(User.class, "402880d01b9bf210011b9bf2a2ff0001");
			System.out.println("user.name=" + user.getName());
			
			//persistent״̬,�����Է����ı��ʱ��hibernate���Զ������ݿ�ͬ��
			user.setName("����");
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	public void testReadByLoadMethod2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//����load�������ݣ�������ݿ���û����Ӧ������
			//��ô�׳�ObjectNotFoundException
			User user = (User)session.load(User.class, "55555555");
			
			System.out.println(user.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new java.lang.RuntimeException();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	public void testUpdate1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�ֶ������detached״̬�Ķ���
			User user = new User();
			user.setId("402880d01b9be8dc011b9be9b23d0001");
			user.setName("�»�");
			
			session.update(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	

	public void testDelete1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
//			//�ֶ������detached״̬�Ķ���
//			User user = new User();
//			user.setId("402880d01b9be8dc011b9be9b23d0001");
//			user.setName("�»�");
//			session.delete(user);
			
			User user = (User)session.load(User.class, "402880d01b9be8dc011b9be9b23d0001");
			session.delete(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		//transient״̬
	}	
	
}
