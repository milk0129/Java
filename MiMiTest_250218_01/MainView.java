package classes;

import java.util.Scanner;
import java.util.ArrayList;
public class MainView {
	UserDAO uDAO = new UserDAO();
	ExpenseDAO eDAO = new ExpenseDAO();
	UserAPI uAPI = new UserAPI();
	Scanner sc = new Scanner(System.in);

	int inputValidate(String _userInput){
		int _errCode = 0;	

		if (_userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
			System.out.println("\n\t시스템을 종료합니다.\n");
			_errCode = 1;
		}
		else if (_userInput.isEmpty()) {	// Err01. 엔터 입력
			System.out.println("^ [Error Chk] 공백이 입력되었습니다.\n");
			_errCode = 2;
		}
		else if (_userInput.length() > 1) {	// Err02. 자릿수 오류
			System.out.println("^ [Error Chk] 1자리 숫자를 입력해주세요.\n");
			_errCode = 3;
		}
		else if ((_userInput.charAt(0) < 49) || (_userInput.charAt(0) > 51)) {	// Err03. 범위 오류
			System.out.println("^ [Error Chk] 1 ~ 3 사이의 번호를 입력해주세요. \n");
			_errCode = 4;
		}
		return _errCode;
	}

	public void initialPrint(){
		uAPI.mLine('=' ,45);
		System.out.printf("%27s\n", "##  가계부 시스템  ##");
		uAPI.mLine('=' ,45);
		System.out.printf("\n%-10s%s\n","","1. 회원가입");
		System.out.printf("\n%-10s%s\n","","2. 아이디 찾기");
		System.out.printf("\n%-10s%s\n","","3. 로그인");
		System.out.printf("\n%-10s%s\n\n","","[시스템 종료 : Q]");
		uAPI.mLine('-' ,45);
		System.out.print("☞ 메뉴를 선택해주세요 : ");
	}

	public void mainShowMenu(){		
		int errCode = 0;
		String userInput = "";		

		while(true){
			initialPrint();
			userInput = sc.nextLine();

			errCode = inputValidate(userInput);
			if (errCode == 1) {
				System.exit(0);
			}

			if (errCode == 0)	{
				switch (userInput)	{
				case "1":
					mainRegisterUser();
					break;
				case "2":
					mainViewFindUser();
					break;
				case "3":
					mainViewLogin();
					break;
				}
			}
		}

	}

	public void mainRegisterUser(){
		while(true){
			String userName;
			String userBirth;
			String userId;
			String userInput;

			System.out.println("\n");
			uAPI.mLine('=' ,45);
			System.out.printf("%22s\n", "회원가입");
			uAPI.mLine('=' ,45);
			System.out.printf("\n%-5s%s","","이름, 생년월일, 아이디를 입력하세요.");
			System.out.printf("\n\n%-2s%s","","아이디 조건 [숫자와 영문자 조합, 5자 이상]");
			System.out.println("\n\n\t[이전 메뉴로 돌아가기 : Q]\n");
			uAPI.mLine('-' ,45);
			
			System.out.print("☞ 이름을 입력하세요. : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			userName = userInput;

			System.out.print("☞ 생년월일을 입력하세요. : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			userBirth = userInput;
			
			while(true){// 중복이 아니면 탈출 
				System.out.print("☞ 아이디를 입력하세요. : ");
				userInput = sc.nextLine();
				 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
					System.out.println("\n\t메뉴로 돌아갑니다.\n");
					return;
				}
				userId = userInput;
				uDAO.userIsUserExists(userId); 

				if (!uDAO.userChk){	// 중복이 아닌 경우 회원가입
					uDAO.userAdd(userName, userId, userBirth);
					uAPI.mLine('=' ,45);
					System.out.println("\n\n       " + userName + "님 " + userId + " 아이디로 회원가입이 되었습니다.");
					uAPI.mLine('=' ,45);
					return;
				}else{					// 중복이면 재입력받음
					System.out.println("이미 존재하는 아이디입니다.");
				}
			}
		}
	}



	public void mainViewFindUser(){
		while(true){
			System.out.println("\n");
			uAPI.mLine('=' ,45);
			System.out.printf("%22s\n", "아이디 찾기");
			uAPI.mLine('=' ,45);
			System.out.printf("\n%-8s%s","","이름, 생년월일을 입력하세요.");
			System.out.println("\n\n\t[이전 메뉴로 돌아가기 : Q]\n");
			uAPI.mLine('-' ,45);
			System.out.print("☞ 이름을 입력하세요. : ");

			String userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
		}
	}
	public void mainViewLogin(){
		while(true){
			System.out.println("\n");
			uAPI.mLine('=' ,45);
			System.out.printf("%22s\n", "로그인");
			uAPI.mLine('=' ,45);
			System.out.printf("\n%-12s%s","","아이디를 입력하세요.");
			System.out.println("\n\n\t[이전 메뉴로 돌아가기 : Q]\n");
			uAPI.mLine('-' ,45);
			System.out.print("☞ 아이디를 입력하세요. : ");
			// ALgotirhtm
			String userInput = sc.nextLine();

			uDAO.userLogin(userInput);
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			mainMoneyMenu(userInput);
		}
	}

	public void mainMoneyMenu(String userId){
		while(true){
			System.out.println("\n");
			uAPI.mLine('=' ,45);
			System.out.printf("%17s 님의 가계부\n", userId);
			uAPI.mLine('=' ,45);
			System.out.printf("\n%-10s%s\n","","1. 내역 기입");
			System.out.printf("\n%-10s%s\n","","2. 내역 수정");
			System.out.printf("\n%-10s%s\n","","3. 내역 확인");
			System.out.printf("\n%-30s%s\n\n","","Q. 메인화면");
			uAPI.mLine('-' ,45);
			System.out.print("☞ 메뉴를 선택해주세요 : ");
			String userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			int errCode = 0;
			errCode = inputValidate(userInput);
			if (errCode == 1) {
				System.exit(0);
			}

			if (errCode == 0)	{
				switch (userInput)	{
				case "1":
					mainInputMoney(userInput);
					break;
				case "2":
					mainEditMoney(userInput);
					break;
				case "3":
					mainShowMoney(userInput);
					break;
				}
			}
		}
	}

	public void mainInputMoney(String userId){	// 01. 내역 기입
		System.out.println("메인인풋머니 테스트");
		while(true){
			String expDate;
			String expItem;
			int expMoney;
			String expCategory;
			String expMemo;

			System.out.println("\n");
			uAPI.mLine('=' ,45);
			System.out.printf("\n%-10s%s\n","","내역을 입력해주세요.");
			System.out.printf("\n%-10s%s\n","","날짜\t지출명\t지출\t카테고리\t메모");
			System.out.printf("\n%-5s%s\n","예시 :","yy-mm-dd\t커피\t2000\t간식\t 메가커피 아이스티");
			System.out.printf("\n%-30s%s\n\n","","Q. 메인화면");
			uAPI.mLine('=' ,45);
			System.out.print("☞ 날짜를 입력해주세요 : ");
			String userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			expDate = userInput;

			System.out.print("☞ 지출명을 입력해주세요 : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			expItem = userInput;

			System.out.print("☞ 지출을 입력해주세요 : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			expMoney = Integer.parseInt(userInput);

			System.out.print("☞ 카테고리 입력해주세요 : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			expCategory = userInput;

			System.out.print("☞ 메모를 입력해주세요 : ");
			userInput = sc.nextLine();
			 if (userInput.equalsIgnoreCase("q"))	{ // 시스템 종료
				System.out.println("\n\t메뉴로 돌아갑니다.\n");
				return;
			}
			expMemo = userInput;
			eDAO.expAdd(expDate, expItem, expMoney, expCategory, expMemo);
		}
	}
	public void mainEditMoney(String userId){	// 02. 내역 수정 (mainShowTotalMoney -> mainEditMoney(String userId)
		System.out.println("메인에딧머니 테스트");
	}
	public void mainShowMoney(String userId){	// 03. 내역 확인
		System.out.println("메인쇼머니 테스트");
		uAPI.mLine('=' ,45);
		eDAO.printAllExpenses();
	}

	public static void main(String[] args) {
		MainView obj = new MainView();
		obj.mainShowMenu();
	}
}
