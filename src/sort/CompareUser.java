package sort;

import java.util.Comparator;

/**
 * @author ZYK
 *ͨ��ʵ��Comparator �ӿڣ�������ȽϺ���
 */
public class CompareUser implements Comparator<User>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * �����������С��������������Ҫ����Ļ��������� int res = o2.age - o1.age;
	 * ��������ͬʱ��������������ĸ˳������
	 */
	@Override
	public int compare(User o1, User o2) {		
	
		int res = o1.age - o2.age;
		if(res == 0){
			// ����compareTo��������������Ǵ��ڷ���������С�ڷ��ظ��������ڷ�����
			return o1.name.compareTo(o2.name);
		}else {
			return res;
		}
		
	}

	
}
