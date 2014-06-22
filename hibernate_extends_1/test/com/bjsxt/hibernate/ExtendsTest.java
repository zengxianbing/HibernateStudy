package com.bjsxt.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import junit.framework.TestCase;

public class ExtendsTest extends TestCase {

	public void testSave1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Pig pig = new Pig();
			pig.setName("����");
			pig.setSex(true);
			pig.setWeight(100);
			session.save(pig);
			
			Bird bird = new Bird();
			bird.setName("����");
			bird.setSex(false);
			bird.setHeight(50);
			session.save(bird);
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}	
	
	/**
	 * ����load��ͨ��Pig��ѯ
	 */
	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Pig pig = (Pig)session.load(Pig.class, 1);
			System.out.println(pig.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * ����load��ͨ��Animal��ѯ
	 */
	public void testLoad2() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Animal animal = (Animal)session.load(Animal.class, 1);
			System.out.println(animal.getName());
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}			
	
	/**
	 * ����load��ͨ��Animal��ѯ
	 */
	public void testLoad3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Animal animal = (Animal)session.load(Animal.class, 1);
			
			//��ΪloadĬ��ֻ��lazy����Ϊ���ǿ�������Animal�Ĵ������
			//����ͨ��instanceof�Ƿ�Ӧ��������Ķ������͵�
			//���load��Ĭ��������ǲ�֧�ֶ�̬��ѯ��
			if (animal instanceof Pig) {
				System.out.println(animal.getName());
			}else {
				System.out.println("������");
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
	 * ����load��ͨ��Animal��ѯ,��<class>��ǩ�ϵ�lazy=false
	 */
	public void testLoad4() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Animal animal = (Animal)session.load(Animal.class, 1);
			//������ȷ���жϳ�Pig�����ͣ���Ϊlazy=false�����ص��Ǿ����Pig����
			//��ʱload֧�ֶ�̬��ѯ
			if (animal instanceof Pig) {
				System.out.println(animal.getName());
			}else {
				System.out.println("������");
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
	 * ����get��ͨ��Animal��ѯ
	 */
	public void testLoad5() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			//������ȷ���жϳ�Pig�����ͣ���Ϊ���ص��Ǿ����Pig����
			//get֧�ֶ�̬��ѯ
			Animal animal = (Animal)session.get(Animal.class, 1);

			if (animal instanceof Pig) {
				System.out.println(animal.getName());
			}else {
				System.out.println("������");
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
	 * ����get��ͨ��Animal��ѯ
	 */
	public void testLoad6() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
//			List animalList = session.createQuery("from Animal").list();
//			for (Iterator iter = animalList.iterator(); iter.hasNext();) {
//				Animal a = (Animal)iter.next();
//				//�ܹ���ȷ�ļ������������ͣ�hql��֧�ֶ�̬��ѯ��
//				if (a instanceof Pig) {
//					System.out.println("��Pig");
//				}else if (a instanceof Bird) {
//					System.out.println("��bird");
//				} 
//			}
			
			List list = session.createQuery("from java.lang.Object").list();
			for (Iterator iter=list.iterator(); iter.hasNext();) {
				Object o = iter.next();
				if (o instanceof Pig) {
					System.out.println("��Pig");
				}else if (o instanceof Bird) {
					System.out.println("��bird");
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
