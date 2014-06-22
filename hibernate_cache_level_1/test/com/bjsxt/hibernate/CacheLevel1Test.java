package com.bjsxt.hibernate;

import java.io.Serializable;

import org.hibernate.Session;

import junit.framework.TestCase;

public class CacheLevel1Test extends TestCase {

	/**
	 * ��ͬһ��session�з�������load��ѯ
	 */
	public void testCache1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student student = (Student)session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			
			//���ᷢ��sql����Ϊloadʹ�û���
			student = (Student)session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}		

	/**
	 * ��ͬһ��session�з�������get��ѯ
	 */
	public void testCache2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student student = (Student)session.get(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			
			//���ᷢ��sql����Ϊgetʹ�û���
			student = (Student)session.get(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	/**
	 * ��ͬһ��session�з�������iterate��ѯʵ�����
	 */
	public void testCache3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student student = (Student)session.createQuery("from Student s where s.id=1").iterate().next();
			System.out.println("student.name=" + student.getName());
			
			//�ᷢ����ѯid��sql�����ᷢ����ѯʵ������sql����Ϊiterateʹ�û���
			student = (Student)session.createQuery("from Student s where s.id=1").iterate().next();
			System.out.println("student.name=" + student.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}			
	
	/**
	 * ��ͬһ��session�з�������iterate��ѯʵ�����
	 */
	public void testCache4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			String name = (String)session.createQuery("select s.name from Student s where s.id=1").iterate().next();
			System.out.println("student.name=" + name);
			
			//iterate��ѯ��ͨ���ԣ�һ�����治�Ỻ�棬���Է���sql
			//һ�������ǻ���ʵ������
			name = (String)session.createQuery("select s.name from Student s where s.id=1").iterate().next();
			System.out.println("student.name=" + name);
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}			
	
	/**
	 * ��������session�з���load��ѯ
	 */
	public void testCache5() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student student = (Student)session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�ᷢ����ѯ��䣬session�䲻�ܹ���һ�����������
			//��Ϊ�������session���������ڴ��ں�����
			Student student = (Student)session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
	}		
	
	/**
	 * ��ͬһ��session����save���ڷ���load��ѯsave��������
	 */
	public void testCache6() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student stu = new Student();
			stu.setName("����");
			
			Serializable id = session.save(stu);
			
			//���ᷢ��sql����Ϊsave��ʹ�û����
			Student student = (Student)session.load(Student.class, id);
			System.out.println("student.name=" + student.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	

	/**
	 * �����ݿ�����������1000������
	 */
	public void testCache7() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			for (int i=0; i<1000; i++) {
				Student student = new Student();
				student.setName("s_" + i);
				session.save(student);
				//ÿ20�����ݾ�ǿ��session�����ݳ־û�
				//ͬʱ������棬���������������ڴ����
				if ( i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
}
