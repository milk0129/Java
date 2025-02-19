package classes;

import java.util.Scanner;

public class MainView {

    private UserAPI uAPI = new UserAPI();
    private UserDAO uDAO = new UserDAO();
    private ExpenseDAO eDAO = new ExpenseDAO();
    private Scanner sc = new Scanner(System.in);

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

    public void mainShowMenu() {
        while (true) {
            initialPrint();
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    mainRegisterUser();
                    break;
                case "2":
                    mainViewFindUser();
                    break;
                case "3":
                    mainViewLogin();
                    break;
                case "q":
                case "Q":
                    System.out.println("\n\t시스템을 종료합니다.\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private void mainRegisterUser() {
        System.out.print("이름을 입력하세요: ");
        String userName = sc.nextLine();

        System.out.print("생년월일을 입력하세요: ");
        String userBirth = sc.nextLine();

        while (true) {
            System.out.print("아이디를 입력하세요: ");
            String userId = sc.nextLine();
            if (!uDAO.userIsUserExists(userId)) {
                uDAO.userAdd(userName, userId, userBirth);
                System.out.println("회원가입이 완료되었습니다.");
                return;
            } else {
                System.out.println("이미 존재하는 아이디입니다. 다시 입력하세요.");
            }
        }
    }

    private void mainViewFindUser() {
        System.out.print("이름을 입력하세요: ");
        String userName = sc.nextLine();
        System.out.print("생년월일을 입력하세요: ");
        String userBirth = sc.nextLine();

        String userId = uDAO.userFindId(userName, userBirth);
        if (userId != null) {
            System.out.println("아이디: " + userId);
        } else {
            System.out.println("일치하는 사용자가 없습니다.");
        }
    }

    private void mainViewLogin() {
        System.out.print("아이디를 입력하세요: ");
        String userId = sc.nextLine();

        if (uDAO.userIsUserExists(userId)) {
            System.out.println("로그인 성공!");
            mainMoneyMenu(userId);
        } else {
            System.out.println("존재하지 않는 아이디입니다.");
        }
    }

    private void mainMoneyMenu(String userId) {
        while (true) {
            System.out.println("\n1. 지출 내역 추가");
            System.out.println("2. 지출 내역 수정");
            System.out.println("3. 지출 내역 확인");
            System.out.println("Q. 로그아웃");
            System.out.print("메뉴를 선택하세요: ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    mainInputMoney(userId);
                    break;
                case "2":
                    mainEditMoney(userId);
                    break;
                case "3":
                    mainShowMoney(userId);
                    break;
                case "q":
                case "Q":
                    System.out.println("로그아웃 되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    private void mainInputMoney(String userId) {
        System.out.print("날짜를 입력하세요 (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("지출 항목을 입력하세요: ");
        String item = sc.nextLine();
        System.out.print("금액을 입력하세요: ");
        int amount = Integer.parseInt(sc.nextLine());
        System.out.print("카테고리를 입력하세요: ");
        String category = sc.nextLine();
        System.out.print("메모를 입력하세요: ");
        String memo = sc.nextLine();

        eDAO.expAdd(userId, date, item, amount, category, memo);
        System.out.println("지출 내역이 추가되었습니다.");
    }

	public void mainEditMoney(String userId) {
		Scanner sc = new Scanner(System.in);

		System.out.println("===== 지출 내역 수정 =====");
		eDAO.printAllExpenses(userId); // 현재 지출 내역 출력

		System.out.print("수정할 지출 내역의 번호를 입력하세요: ");
		int expId = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기

		System.out.print("새로운 날짜 입력하세요: ");
		String newDate = sc.nextLine();

		System.out.print("새로운 지출 항목을 입력하세요: ");
		String newItem = sc.nextLine();

		System.out.print("새로운 지출 금액을 입력하세요: ");
		int newMoney = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기

		System.out.print("새로운 카테고리를 입력하세요: ");
		String newCategory = sc.nextLine();

		System.out.print("새로운 메모를 입력하세요: ");
		String newMemo = sc.nextLine();

		// ExpenseDAO의 expEdit 호출하여 수정
		eDAO.expEdit(userId, expId, newDate, newItem, newMoney, newCategory, newMemo);
	}


    private void mainShowMoney(String userId) {
        System.out.println("지출 내역:");
        eDAO.printExpensesByUser(userId);
    }

    private void mainShowTotalMoney(String userId) {
        int total = eDAO.expGetTotal(userId);
        System.out.println("총 지출 금액: " + total + "원");
    }

    public static void main(String[] args) {
        MainView obj = new MainView();
        obj.mainShowMenu();
    }
}
