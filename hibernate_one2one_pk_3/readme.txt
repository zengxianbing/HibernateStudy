hibernateһ��һ��������ӳ�䣨�������Person---->IdCard��

һ��һ��������ӳ��:������ʵ������id������ͬ���������Ա��������ֶα�����

����ӳ�䣺

	<id name="id">
		<!-- person��������ԴidCard��Ҳ���ǹ���idCard������ -->
		<generator class="foreign">
			<param name="property">idCard</param>
		</generator>
	</id>
	<property name="name"/>
	<!-- one-to-one��ǩ�ĺ��壬ָʾhibernate��ô�������Ĺ�������Ĭ�ϸ����������أ�
	constrained="true"��	������ǰ�����ϴ���һ��Լ����person��������Ϊ���������idCard	
	 -->
	<one-to-one name="idCard" constrained="true"/>
