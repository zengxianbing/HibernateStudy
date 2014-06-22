package com.bjsxt.hibernate;

import java.io.Serializable;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import junit.framework.TestCase;

public class CacheLevel2Test extends TestCase {

	/**
	 * ��������session���ֱ����load
	 */
	public void testCache1() {
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
			
			//���ᷢ��sql����Ϊ�����˶������棬session�ǹ�����������
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
	 * ��������session���ֱ����get
	 */
	public void testCache2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Student student = (Student)session.get(Student.class, 1);
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
			
			//���ᷢ��sql����Ϊ�����˶������棬session�ǹ�����������
			Student student = (Student)session.get(Student.class, 1);
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
	 * ��������session���ֱ����load,��ʹ��SessionFactory�����������
	 */
	public void testCache3() {
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
		
		//�����������
		SessionFactory factory = HibernateUtils.getSessionFactory();
		//factory.evict(Student.class);
		factory.evict(Student.class, 1);
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�ᷢ����ѯsql����Ϊ���������е����ݱ������
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
	 * һ������Ͷ�������Ľ���
	 */
	public void testCache4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//���������������ݣ��������������д����
			session.setCacheMode(CacheMode.GET);
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
			
			//����sql��䣬��Ϊsession������CacheModeΪGET�����Զ���������û������
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
			
			//ֻ���������д���ݣ������Ӷ������������
			session.setCacheMode(CacheMode.PUT);
			
			//�ᷢ����ѯsql����Ϊsession��CacheMode���ó���PUT
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
}
