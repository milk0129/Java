package classes;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private List<ExpenseDTO> expenseList;
    private int expenseIdCounter;

    public ExpenseDAO() {
        this.expenseList = new ArrayList<>();
        this.expenseIdCounter = 1; // ID 자동 증가
    }

    // 사용자가 입력한 지출 내역을 저장
    public void expAdd(String expDate, String expItem, int expMoney, String expCategory, String expMemo) {
        ExpenseDTO newExpense = new ExpenseDTO(expMoney, expDate, expCategory, expMemo, expItem, expenseIdCounter++);
        expenseList.add(newExpense);
        System.out.println("지출 내역이 추가되었습니다.");
    }

    // 특정 지출 내역을 삭제
    public void expDelete(int expId) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getExpId() == expId) {
                expenseList.remove(i);
                System.out.println("지출 내역이 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 지출 내역을 찾을 수 없습니다.");
    }

    // 특정 지출 내역을 수정
    public void expEdit(int expId, String expNewItem, int expNewMoney, String expNewCategory, String expNewMemo) {
        for (ExpenseDTO expense : expenseList) {
            if (expense.getExpId() == expId) {
                expense.setExpItem(expNewItem);
                expense.setExpMoney(expNewMoney);
                expense.setExpCategory(expNewCategory);
                expense.setExpMemo(expNewMemo);
                System.out.println("지출 내역이 수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 지출 내역을 찾을 수 없습니다.");
    }

    // 특정 사용자의 총 지출 금액 반환
    public int expGetTotal(String userId) {
        int total = 0;
        for (ExpenseDTO expense : expenseList) {
            total += expense.getExpMoney();
        }
        return total;
    }

    // 저장된 모든 지출 내역 출력 (디버깅용)
    public void printAllExpenses() {
        for (ExpenseDTO expense : expenseList) {
            System.out.println(expense);
        }
    }
}
