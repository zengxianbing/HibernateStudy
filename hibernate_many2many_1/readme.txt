hibernate��Զ����ӳ��(����User---->Role)

����ӳ�䷽ʽ��
	<set name="roles" table="t_user_role">
		<key column="userid"/>
		<many-to-many class="com.bjsxt.hibernate.Role" column="roleid"/>
	</set>
	