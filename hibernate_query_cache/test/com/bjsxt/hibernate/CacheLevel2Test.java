package com.bjsxt.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import junit.framework.TestCase;

public class CacheLevel2Test extends TestCase {

	/**
	 * ������ѯ���棬�رն�������
	 * 
	 * ����һ��session���ֱ����query.list
	 */
	public void testCache1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			List names = query.list(); 
			for (Iterator iter=names.iterator();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}
			
			System.out.println("-------------------------------------");
			query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			//û�з�����ѯsql����Ϊ�����˲�ѯ����
			names = query.list(); 
			for (Iterator iter=names.iterator();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	/**
	 * ������ѯ���棬�رն�������
	 * 
	 * ��������session���ֱ����query.list
	 */
	public void testCache2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			List names = query.list(); 
			for (Iterator iter=names.iterator();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		System.out.println("-------------------------------------");
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			//���ᷢ����ѯsql����Ϊ��ѯ������������ں�session�޹�
			List names = query.list(); 
			for (Iterator iter=names.iterator();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}		
	
	/**
	 * ������ѯ���棬�رն�������
	 * 
	 * ��������session���ֱ����query.iterate
	 */
	public void testCache3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			 
			for (Iterator iter=query.iterate();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		System.out.println("-------------------------------------");
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s.name from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			 
			//��ѯ����ֻ��query.list()�����ã�query.iterate�������ã�Ҳ����query.iterate��ʹ��
			//��ѯ����
			for (Iterator iter=query.iterate();iter.hasNext(); ) {
				String name = (String)iter.next();
				System.out.println(name);
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * �رղ�ѯ���棬�رն�������
	 * 
	 * ��������session���ֱ����query.list��ѯʵ�����
	 */
	public void testCache4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			//query.setCacheable(true);
			
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		System.out.println("-------------------------------------");
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			//query.setCacheable(true);
			//�ᷢ����ѯsql����ΪlistĬ��ÿ�ζ��ᷢ����ѯsql
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}		

	/**
	 * ������ѯ���棬�رն�������
	 * 
	 * ��������session���ֱ����query.list��ѯʵ�����
	 */
	public void testCache5() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		System.out.println("-------------------------------------");
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			//�ᷢ��n����ѯ��䣬��Ϊ�����˲�ѯ���棬�ر��˶������棬��ô��ѯ����Ỻ��ʵ������id
			//����hibernate�����ʵ������idȥ��ѯ��Ӧ��ʵ�壬��������в�������Ӧ��
			//ʵ����ô����������ʵ��id��ѯ��sql��䣬���򲻻ᷢ��sqlʹ�û����е�����
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	/**
	 * ������ѯ���棬������������
	 * 
	 * ��������session���ֱ����query.list��ѯʵ�����
	 */
	public void testCache6() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		System.out.println("-------------------------------------");
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Query query = session.createQuery("select s from Student s");
			//���ò�ѯ��ѯ����
			query.setCacheable(true);
			
			//���ᷢ����ѯsql����Ϊ�����˶�������Ͳ�ѯ���棬��ѯ���滺����ʵ������id�б�
			//hibernate�����ʵ������id�б�����������ȡ����Ӧ������
			List students = query.list(); 
			for (Iterator iter=students.iterator();iter.hasNext(); ) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
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
