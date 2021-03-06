package com.bjsxt.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import junit.framework.TestCase;

/**
 * ����������ѯ
 * @author Administrator
 *
 */
public class NameQueryTest extends TestCase {

	public void testQuery1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			List students = session.getNamedQuery("searchStudents")
								.setParameter(0, 10)
								.list();
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
