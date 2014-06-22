package com.bjsxt.hibernate;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

  
public class JoinTest extends TestCase {
	
	public void testInitData(){
		
		Session session = HibernateUtils.getSession();
		try {
			session.beginTransaction();
			int studentid = 1;
			//�����༶0->4
			for(int i=0; i<5; i++){
				Classes cls = new Classes();
				cls.setClassesNo("SXT"+i);
				session.save(cls);
				
				//����ѧ��: 
				for(int j=0; j<3; j++){
					Student s = new Student();
					s.setName("ѧ��"+(studentid++));
					s.setClasses(cls);
					s.setStudentNo("STUDENT"+j+"["+cls.getClassesNo()+"]");
					session.save(s);
				}
			}
			
			//�ɼ���:
			//ѧ��15û�����κογ̣�
			//ѧ��1-9�����˵�һ�ſγ�
			//ѧ��4-14,���˵ڶ��ſγ�
			//ѧ��7-14�����˵����ſγ�
			int[][] grades = {
					{60,45,80,90,96,88,67,95,95} //�γ�0,ѧ��(1-9)�ĳɼ�,��߷�95
					,{65,50,99,81,95,83,72,83,98} //�γ�1��ѧ��(4-12)�ĳɼ�����߷�98
					,{75,60,94,91,85,73,82,63,68} //�γ�2��ѧ��(6-14)�ĳɼ�,��߷�94
			};
			
			//�ֱ��Ӧ��ͬ�ɼ���ѧ����ID
			String[] studentIds = {
					"1,2,3,4,5,6,7,8,9",
					"4,5,6,7,8,9,10,11,12",
					"6,7,8,9,10,11,12,13,14"
			};
			
			//�����γ̺Ͷ�Ӧ�ĳɼ�
			for(int i=0; i<3; i++){
				Course c = new Course();
				c.setName("�γ�"+i);
				session.save(c);

				List students = session.createQuery("select s from Student s where s.id in ("+studentIds[i]+")").list();
				for (int j=0; j<students.size(); j++) {
					Student student = (Student)students.get(j);
					Grade g = new Grade();
					g.setCourse(c);
					g.setStudent(student);
					g.setGrade(grades[i][j]);
					session.save(g);
				}
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally{
			HibernateUtils.closeSession(session);
		}
	}

	//�г����пγ���,�ɼ���ߵ�ѧ��
	public void testQuery(){
		Session session = HibernateUtils.getSession();
		try {
			session.beginTransaction();
			
			List grades = session.createQuery("select c.name,s.name,g.grade,cls.classesNo from Grade g " +
					"join g.course c join g.student s join s.classes cls " +
					"where g.grade in ( select max(gg.grade) from Grade gg where gg.course.name = c.name group by gg.course.name) " +
					"order by c.name").list();

//			List grades = session.createQuery("select c.name,s.name,g.grade,cls.classesNo from Grade g, " +
//					"Course c, Student s, Classes cls " +
//					"where g.course=c.id and g.student=s.id and s.classes.id=cls.id and " + 
//					"g.grade in ( select max(gg.grade) from Grade gg where gg.course.name = c.name group by gg.course.name) " +
//					"order by c.name").list();
			
			//�γ����ơ�ѧ�����ơ������������༶����
			//List grades = session.createQuery("???����д��Ӧ��HQL���???").list();
			for (Iterator iter = grades.iterator(); iter.hasNext();) {
				Object[] objs = (Object[]) iter.next();
				System.out.println(objs[0]+","+objs[1]+","+objs[2]+","+objs[3]);
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally{
			HibernateUtils.closeSession(session);
		}
	}
}
