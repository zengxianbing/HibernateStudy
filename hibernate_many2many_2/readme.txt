hibernate��Զ����ӳ��(˫��User<---->Role)

ӳ�䷽����
		<set name="roles" table="t_user_role">
			<key column="userid"/>
			<many-to-many class="com.bjsxt.hibernate.Role" column="roleid"/>
		</set>
table����ֵ����͵�������е�table����ֵһ��
<key>��column����ֵҪ�뵥������е�<many-to-many>��ǩ�е�column����ֵһ��
��<many-to-many>�е�column����ֵҪ�뵥�������<key>��ǩ��column����ֵһ��
	