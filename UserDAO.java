package classes;
// 사용자 회원가입, 로그인, 아이디찾기 수행 클래스
import java.util.ArrayList;

class  UserDAO{

	//UserDB userDB = new UserDB(); // DB 클래스 생성자로 객체 생성
	ArrayList<UserDTO> uDTO = new ArrayList<UserDTO>();
		boolean userChk =false;
		
	// 새로운  사용자 정보 저장
	void userAdd(String userName, String userId, String userBirth){
		userIsUserExists(userId);
		
			uDTO.add(new UserDTO(userName, userId, userBirth));
			System.out.println(userName + "님 "+ userId + " 아이디로 회원가입 완료되었습니다."); 

			for (UserDTO user : uDTO) {
				System.out.println("아이디 :" + user.getUserId() + ", 이름 : " + user.getUserName() + ", 생일: " + user.getUserBirth());
			}
	}

	// 사용자 아이디 찾기
	String userFindId(String userName, String userBirth){
		for (UserDTO udto : uDTO){
			if(udto.getUserName().equals(userName) && udto.getUserBirth().equals(userBirth)){
				System.out.println(udto.getUserId() + " 입니다.\n");
				return udto.getUserId();
			}
		}
		return null; 
	}

	// 사용자 로그인
	boolean userLogin(String userId) {
		for (UserDTO udto : uDTO) {
			if (udto.getUserId().equals(userId)) {
				System.out.println("로그인 성공했습니다.\n");
				return true; 
			}
		}
		System.out.println("아이디가 존재하지 않습니다. 다시 입력해주세요.\n");
		return false; 
	}

	// 사용자 중복확인
	boolean userIsUserExists(String userId) {
		userChk = false;
		for (UserDTO udto : uDTO) {
			if (udto.getUserId() != null && udto.getUserId().equals(userId)) { 
				System.out.println("중복입니다");
				userChk = true;
				return true; 
			}
		}
		System.out.println("중복이 아닙니다");
		userChk = false;
		return false;
	}
}