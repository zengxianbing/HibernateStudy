hibernate��������

��������Ҳ�ƽ��̼��Ļ����SessionFactory���Ļ��棬����������Ա����е�session����
����������������ں�SessionFactory����������һ�£�SessionFactory���Թ����������

������������ú�ʹ�ã�
	* ��echcache.xml�ļ�������src��
	* �����������棬�޸�hibernate.cfg.xml�ļ�
		<property name="hibernate.cache.use_second_level_cache">true</property>
	* ָ�������Ʒ�ṩ�̣��޸�hibernate.cfg.xml�ļ�
		<property name="hibernate.cache.provider_class">org.hibernate.cache.EhCacheProvider</property>
	* ָ����Щʵ����ʹ�ö������棨���ַ�����
		* ��ӳ���ļ��в���<cache>��ǩ
		* ��hibernate.cfg.xml�ļ��У�����<class-cache>��ǩ
		
���������ǻ���ʵ������

�˽�һ������Ͷ�������Ľ���		
		
				 
					