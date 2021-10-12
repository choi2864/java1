package view;

import model.UserDao;
import model.UserVo;

public class TestMain {

	public static void main(String[] args) {

		UserDao uDao = new UserDao();
		UserVo  user  = new UserVo("sky2", "1111","스카이", "신", "M", "자기 소개");
		uDao.insertUser(user);
	}

}
