package classes;
// 사용자 회원가입, 로그인, 아이디찾기 수행 클래스
import java.util.Scanner;
import java.util.ArrayList;

class  UserDAO{

	UserDB userDB = new UserDB(); // DB 클래스 생성자로 객체 생성

		boolean userChk =false;
		int userIdx = -1;					
		String userInput;
		Scanner sc;
		ArrayList<String> userInfo;
		
	// 새로운  사용자 정보 저장
	void userAdd(String userId, String userName, String userBirth){
		 UserDTO newUser = new UserDTO(userId, userName, userBirth);
		 userInfo = new ArrayList<>();
         userInfo.add(newUser.getUserId());
         userInfo.add(newUser.getUserName());
         userInfo.add(newUser.getUserBirth());
        
         userDB.exp_DB.add(userInfo);
		 System.out.println(userDB.exp_DB);
	}

	// 사용자 아이디 찾기
	void userFindId(String userName, String userBirth){
		for(String user : userInfo){
			if(user.equals(userName) && user.equals(userBirth)){
				System.out.println(userDB.exp_DB);
					userChk = true;		//아이디찾기 성공 
					System.out.println("로그인에 성공했습니당");
			}
		}
	}

	// 사용자 로그인
	void userLogin(String userId){
		for(String user : userInfo){
				if(user.equals(userId)){
					System.out.println(userDB.exp_DB);
					userChk = true;		// 로그인 성공
					System.out.println("로그인에 성공했습니당");
					return;
				}
			}
			System.out.println("로그인 실패!");
			userChk = false;				// 로그인 실패
		}

	// 사용자 중복확인
	boolean userIsUserExists(String userId){
		if(userInfo == null){
			userChk = false;
		}else{
			for(String user : userInfo){
				if(user.equals(userId)){
					//System.out.println("중복입니당");
					System.out.println(userDB.exp_DB);
					userChk = true;
					return true;
				}
			}
			userChk = false;
			//System.out.println("중복이 아입니당");
			//return;
		}
			return false;
	}
}
