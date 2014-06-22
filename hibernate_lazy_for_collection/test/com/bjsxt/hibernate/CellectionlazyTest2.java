package com.bjsxt.hibernate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.Session;

/**
 * ����<class>��ǩ�ϵ�lazy=false
 * @author Administrator
 *
 */
public class CellectionlazyTest2 extends TestCase {


	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//�ᷢ��sql
			Classes classes = (Classes)session.load(Classes.class, 1);
			
			//���ᷢ��sql
			System.out.println("classes.name=" + classes.getName());
			
			//���ᷢ��sql
			Set students = classes.getStudents();
			
			//�ᷢ��sql
			for (Iterator iter=students.iterator(); iter.hasNext();) {
				Student student = (Student)iter.next();
				System.out.println("student.name=" + student.getName());
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
