package com.bjsxt.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate����������
 * @author Administrator
 *
 */
public class HibernateUtils {
	
	private static SessionFactory factory;
	
	static{
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("��ʼ��SessionFactory�����쳣������");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static Session getSession(){
		return factory.openSession();
	}
	
	public static void closeSession(Session session){
		if(session.isOpen()){
			session.close();
		}
	}
}
