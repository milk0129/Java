## classes

MainView.java        // 메인 화면 (입출력 및 메뉴 선택 관리)

UserAPI.java         // 출력 디자인 (구분선 등)

UserDAO.java         // 사용자 관리 (회원가입, 로그인, 아이디 찾기 등)

UserDTO.java         // 사용자 데이터 객체 (사용자 정보 저장)

ExpenseDAO.java      // 지출 내역 관리 (추가, 수정, 삭제, 조회)

ExpenseDTO.java      // 지출 데이터 객체 (지출 정보 저장)

IncomeDAO.java       // 입금 내역 관리 (추가, 수정, 삭제, 조회)

IncomeDTO.java       // 입금 데이터 객체 (입금 정보 저장)


## MainView 

 프로그램 실행 (mainShowMenu())
	 MainView에서 메뉴 출력
	사용자 입력을 받아 회원가입 / 로그인 / 아이디 찾기 / 종료 선택

* 회원가입 (mainRegisterUser())
	이름, 생년월일, 아이디 입력
	아이디 중복 확인 & 생성
	성공 시 "회원가입 완료" 메시지 출력

* 로그인 (mainViewLogin())
	아이디 입력 후 UserDAO에서 존재 여부 확인
	성공 시 mainMoneyMenu()(가계부 메뉴) 실행
	실패 시 "존재하지 않는 아이디" 메시지 출력

* 가계부 메인 메뉴 (mainMoneyMenu())
	로그인 성공 후 가계부 메뉴가 제공
	1. 내역 기입
	2. 내역 수정
	3. 내역 확인
	4. 잔액 확인
	Q. 로그아웃

* 내역 기입 (mainInputMenu())
	1. 지출 내역 기입 선택 → mainInputMoney() 
	2. 입금 내역 기입 선택 → mainInputIncome()

* 내역 수정 (mainEditMoney())
	1. 지출 내역 수정 선택 → mainEditExpense() 
	2. 입금 내역 수정 선택 → mainEditIncome()

* 내역 확인 (mainShowMoney())
	1. 지출 내역 확인 선택 → mainShowExpense() 
	2. 입금 내역 확인 선택 → mainShowIncome()

* 잔액 확인 (mainShowBalance())
	총 입금 (incGetTotal()) - 총 지출 (expGetTotal()) 계산 후 출력


## UserDTO (데이터 저장)

* 사용자 정보를 저장하는 객체
* userId, userName, userBirth, userCreateAt 필드를 가짐
* getter/setter를 통해 데이터 접근

## UserDAO (비즈니스 로직 & 데이터 관리)

* UserDTO 객체를 리스트로 관리 (ArrayList<UserDTO>)
* 사용자 등록, 로그인, 아이디 찾기 기능 제공
* 사용자 ID 중복 체크, 패스워드 검증, 가입 처리

## ExpenseDTO (데이터 저장)
* 사용자의 지출 내역을 저장하는 객체
* expDate, expItem, expMoney, expCategory, expMemo, expId 필드를 가짐
* getter/setter로 데이터 접근 가능

## ExpenseDAO (비즈니스 로직 & 데이터 관리)
* ExpenseDTO 객체를 리스트로 관리 (Map<String, List<ExpenseDTO>>)
* 특정 사용자의 지출 추가, 삭제, 수정, 총합 계산 기능 수행

## IncomeDTO (데이터 저장)
* 사용자의 입금 내역을 저장하는 객체
* incDate, incItem, incMoney, incCategory, incMemo, incId 필드를 가짐
* getter/setter로 데이터 접근 가능

## IncomeDAO (비즈니스 로직 & 데이터 관리)
* IncomeDTO 객체를 리스트로 관리 (Map<String, List<IncomeDTO>>)
* 특정 사용자의 입금 추가, 삭제, 수정, 총합 계산 기능 수행
