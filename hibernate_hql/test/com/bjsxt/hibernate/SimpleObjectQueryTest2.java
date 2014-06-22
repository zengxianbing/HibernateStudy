package com.bjsxt.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * ʵ������ѯ
 * @author Administrator
 *
 */
public class SimpleObjectQueryTest2 extends TestCase {

	public void testQuery1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			/**
			 * ����list��ѯ����һ����ѯ��䣬ȡ��Student�������ݡ�
			 * 
			 * Hibernate: select student0_.id as id1_, student0_.name as name1_, 
			 * student0_.createTime as createTime1_, student0_.classesid as classesid1_ 
			 * from t_student student0_
			 * 
			 */
			List students = session.createQuery("from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
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
	
	public void testQuery2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			/**
			 * ����N+1����
			 * 
			 * 1��������ѯid�б��sql
			 *   Hibernate: select student0_.id as col_0_0_ from t_student student0_
			 * 
			 * N�������η�������id��ѯStudent�����sql
			 * Hibernate: select student0_.id as id1_0_, student0_.name as name1_0_, 
			 * student0_.createTime as createTime1_0_, student0_.classesid as classesid1_0_ 
			 * from t_student student0_ where student0_.id=?
			 *  
			 */
			Iterator iter = session.createQuery("from Student").iterate();
			while(iter.hasNext()) {
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
	
	public void testQuery3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			List students = session.createQuery("from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			System.out.println("---------------------------------------------");
			
			/**
			 * �������N+1����
			 * 
			 * ��Ϊlist�����Ѿ���Student����ŵ���һ�������У������ٴ�ʹ��iterate������ʱ��
			 * �����ȷ���һ����ѯid�б��sql���ڸ���id��������ȥ���ݣ�ֻ���ڻ������Ҳ�����Ӧ��
			 * ����ʱ���Żᷢ��sql�����ݿ��в�ѯ
			 * 
			 */
			Iterator iter = session.createQuery("from Student").iterate();
			while(iter.hasNext()) {
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
	
	public void testQuery4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			List students = session.createQuery("from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Student student = (Student)iter.next();
				System.out.println(student.getName());
			}
			System.out.println("---------------------------------------------");
			
			/**
			 * �ٴη�����ѯsql
			 * 
			 * ��Ĭ�������listÿ�ζ��������ݿⷢ����ѯ�����sql���������ò�ѯ���棬���������list����
			 * ��Ȼ��һ���������Ѿ����˶������ݣ���listĬ������²������û��棬���ٴη���sql
			 * 
			 * Ĭ������£�list���򻺴��з������ݣ���������������
			 * 
			 */
			students = session.createQuery("from Student").list();
			for (Iterator iter=students.iterator(); iter.hasNext();) {
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
