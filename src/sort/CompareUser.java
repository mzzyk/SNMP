package sort;

import java.util.Comparator;

/**
 * @author ZYK
 *通过实现Comparator 接口，来构造比较函数
 */
public class CompareUser implements Comparator<User>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * 依据年龄的由小到大进行排序。如果要降序的话，可以用 int res = o2.age - o1.age;
	 * 当年龄相同时，按照姓名的字母顺序排列
	 */
	@Override
	public int compare(User o1, User o2) {		
	
		int res = o1.age - o2.age;
		if(res == 0){
			// 对于compareTo（）函数，如果是大于返回正数，小于返回负数，等于返回零
			return o1.name.compareTo(o2.name);
		}else {
			return res;
		}
		
	}

	
}
