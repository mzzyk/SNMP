package sort;

import java.util.ArrayList;
import java.util.Collections;

public class test {
	
	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("zhao", 10));
		users.add(new User("yan", 11));
		users.add(new User("hank", 11));
		users.add(new User("kang", 13));
		//�� User ������Ϊ��׼��������
		Collections.sort(users,new CompareUser());
		
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			System.out.println(user.name + " " + user.age);
			
		}

	}

}
