hibernate��ѯ����hql

��hql�йؼ��ֲ����ִ�Сд���������Ժ��������ִ�Сд

1�������Բ�ѯ����Ҫ��
	* ��һ���Բ�ѯ�����ؽ���������б�Ԫ�����ͺ�ʵ��������Ӧ����������һ��
	* ������Բ�ѯ,���صļ���Ԫ���Ƕ�������,����Ԫ�ص����ͺͶ�Ӧ��������ʵ�����е�����һ��
	  ����ĳ���ȡ����select�����Եĸ���
	* �����Ϊ�������鲻�����󻯣����Բ���hql��̬ʵ����Student����
	�μ���SimplePropertyQueryTest.java  

2��ʵ������ѯ����Ҫ��
	* N + 1���⣬��Ĭ������£�ʹ��query.iterate��ѯ���п����ܳ���N+1����
	  ��ν��N+1���ڲ�ѯ��ʱ�򷢳���N+1��sql���
	  1: ���ȷ���һ����ѯ����id�б��sql
	  N: ����id�б������в�ѯ����������в�������֮ƥ������ݣ���ô�����id������Ӧ��sql���
	* list��iterate������
		* listÿ�ζ��ᷢ��sql��䣬list���򻺴��з������ݣ��������û����е�����
		* iterate����Ĭ�������iterate���û������ݣ�����������в����������п����ܳ���N+1����
	�μ���SimpleObjectQueryTest1.java/SimpleObjectQueryTest2.java
	
3��������ѯ����Ҫ��	
	* ���Բ���ƴ�ַ����ķ�ʽ���ݲ���
	* ���Բ��� �������ݲ�����������0��ʼ��
	* ���Բ��� :������ �����ݲ���
	* ������ݶ�����������Բ���setParamterList����
	* ��hql�п���ʹ�����ݿ�ĺ������磺date_format
	�μ���SimpleConditionQueryTest.java 	
		  
4��hibernateҲ֧��ֱ��ʹ��sql���в�ѯ
	�μ���SqlQueryTest.java

5������������ѯ
	* ��ӳ���ļ��в���<query>��ǩ������hql
	* �ڳ����в���session.getNamedQuery()�����õ�hql��ѯ��
	�μ���Student.hbm.xml��NameQueryTest.java
	
6����ѯ������	
	* ��ӳ���ļ��ж������������
	* �����ӳ����ʹ����Щ����
	* �ڳ��������ù�����
	�μ���Student.hbm.xml��FilterQueryTest.java
	
7����ҳ��ѯ����Ҫ��	
	* setFirstResult()����0��ʼ
	* setMaxResults,ÿҳ��ʾ����������
	�μ���PageQueryTest.java
			  
8�����󵼺���ѯ����hql�в��� . ���е�������Ҫ��
	�μ���ObjectNavQueryTest.java
	
9�����Ӳ�ѯ����Ҫ��
	* ����
	* �����ӣ�������/�����ӣ�	
	�μ���JoinQueryTest.java
	
10��ͳ�Ʋ�ѯ����Ҫ��
	�μ���StatQueryTest.java
	
11��DML���Ĳ������������ã���Ϊ�ͻ��治ͬ����
	�μ���DMLQueryTest.java