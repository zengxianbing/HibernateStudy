package com.bjsxt.drp.business.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import org.hibernate.Session;

import com.bjsxt.drp.business.itemmgr.model.ItemCategory;
import com.bjsxt.drp.business.itemmgr.model.ItemUnit;

/**
 * DRP������
 * ע�⿪�������ⷽ�������Ǿ�̬��
 * @author Administrator
 *
 */
public class Functions {

	/**
	 * �����������Ľ��
	 * @return ItemCategory����ļ���
	 */
	public static List getItemCategoryList() {
		Session session = null;
		List itemCategoryList = null; 
		try {
//			session = HibernateUtils.getSession();
			session = HibernateFilter.getSession();
			session.beginTransaction();
			itemCategoryList = session.createQuery("from ItemCategory a order by a.id").list();
			session.getTransaction().commit();
		}catch(Exception e) {
			//��¼��־,log4j��......
			e.printStackTrace();
			session.getTransaction().rollback();
//		}finally {
//			HibernateUtils.closeSession(session);
		}
		return itemCategoryList;
	}

	/**
	 * �����������Ľ��
	 * @return ItemCategory����ļ���
	 */
	public static List getItemUnitList() {
		Session session = null;
		List itemUnitList = null; 
		try {
//			session = HibernateUtils.getSession();
			session = HibernateFilter.getSession();
			session.beginTransaction();
			itemUnitList = session.createQuery("from ItemUnit a order by a.id").list();
			session.getTransaction().commit();
		}catch(Exception e) {
			//��¼��־,log4j��......
			e.printStackTrace();
			session.getTransaction().rollback();
//		}finally {
//			HibernateUtils.closeSession(session);
		}
		return itemUnitList;
	}
}
