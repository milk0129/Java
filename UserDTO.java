package classes;
// 사용자 관리 클래스 

class UserDTO{
	// 사용자 이름, 아이디, 가입일, 생년월일
	protected String userName;
	protected String userId;
	protected String userCreateAt;
	protected String userBirth;

	// Constructor
	public UserDTO(String userName, String userId, String userBirth){
		this.userName = userName;
		this.userId = userId;
		//this.userCreateAt = userCreateAt;
		this.userBirth = userBirth;
	}

	// setter
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public void setUserCreateAt(String userCreateAt){
		this.userCreateAt = userCreateAt;
	}
	public void setUserBirth(String userBirth){
		this.userBirth = userBirth;
	}
		
	// getter
	public String getUserName(){
		return userName;
	}
	public String getUserId(){
		return userId;
	}
	public String getUserCreateAt(){
		return userCreateAt;
	}
	public String getUserBirth(){
		return userBirth;
	}
}
