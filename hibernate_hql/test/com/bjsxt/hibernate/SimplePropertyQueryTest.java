package com.bjsxt.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * �����Բ�ѯ
 * @author Administrator
 *
 */
public class SimplePropertyQueryTest extends TestCase {
	
	/**
	 * ��һ���Բ�ѯ
	 */
	public void testQuery1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//���ؽ���������б�Ԫ�����ͺ�ʵ��������Ӧ����������һ��
			List students = session.createQuery("select name from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
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
	 * ������Բ�ѯ
	 */
	public void testQuery2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//��ѯ������ԣ��伯��Ԫ���Ƕ�������
			//����Ԫ�ص����ͺͶ�Ӧ��������ʵ�����е�����һ��
			//����ĳ���ȡ����select�����Եĸ���
			List students = session.createQuery("select id, name from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Object[] obj = (Object[])iter.next();
				System.out.println(obj[0] + "," + obj[1]);
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
	 * ����Studentʵ�����
	 */
	public void testQuery3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�����Ϊ�������鲻�����󻯣����Բ���hql��̬ʵ����Student����
			//��ʱlist��ΪStudent���󼯺�
			List students = session.createQuery("select new Student(id, name) from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Student student = (Student)iter.next();
				System.out.println(student.getId() + "," + student.getName());
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
	 * ʹ�ñ���
	 */
	public void testQuery4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//����ʹ�ñ���
			List students = session.createQuery("select s.id, s.name from Student s").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Object[] obj = (Object[])iter.next();
				System.out.println(obj[0] + "," + obj[1]);
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
	 * ʹ�ñ���
	 */
	public void testQuery5() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//����ʹ��as��������
			List students = session.createQuery("select s.id, s.name from Student as s").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Object[] obj = (Object[])iter.next();
				System.out.println(obj[0] + "," + obj[1]);
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
