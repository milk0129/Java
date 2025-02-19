package classes;
import java.util.ArrayList;

public class UserDB {
	String userName;
	String userBirth;
	String userCreateAt;
	String userId;
	// 유저의 데이터베이스 = 이름, 생년월일, 아이디, 가입일

	ArrayList<ArrayList<String>> exp_DB = new ArrayList<>();
	// 내역의 데이터베이스 [날짜, 내역, 카테고리, 항목, 메모]
}
