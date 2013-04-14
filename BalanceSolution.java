

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Balance{
	private int weightLeft;
	private int weightRight;
	private int weightTotal;
	private int initWeightLeft;
	private int initWeightRight;
	private int[] leftBalance;
	private int[] rightBalance;
	
	public Balance(){
		
	}

	public int getWeightLeft() {
		return weightLeft;
	}

	public void setWeightLeft(int weightLeft) {
		this.weightLeft = weightLeft;
	}

	public int getWeightRight() {
		return weightRight;
	}

	public void setWeightRight(int weightRight) {
		this.weightRight = weightRight;
	}

	public int getWeightTotal() {
		return weightTotal;
	}

	public void setWeightTotal(int weightTotal) {
		this.weightTotal = weightTotal;
	}

	public int getInitWeightLeft() {
		return initWeightLeft;
	}

	public void setInitWeightLeft(int initWeightLeft) {
		this.initWeightLeft = initWeightLeft;
	}

	public int getInitWeightRight() {
		return initWeightRight;
	}

	public void setInitWeightRight(int initWeightRight) {
		this.initWeightRight = initWeightRight;
	}

	public int[] getLeftBalance() {
		return leftBalance;
	}

	public void setLeftBalance(int[] leftBalance) {
		this.leftBalance = leftBalance;
	}

	public int[] getRightBalance() {
		return rightBalance;
	}

	public void setRightBalance(int[] rightBalance) {
		this.rightBalance = rightBalance;
	}
	
	
}

public class BalanceSolution {
	public static int MAX;
	public static Balance[] balances;
	public static int[] checker;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		MAX = Integer.parseInt(bufferedReader.readLine());
		balances = new Balance[MAX];
		checker = new int[MAX];
		for (int i = 0; i < MAX; ++i) {
			checker[i] = 0;
		}
		for (int i = 0; i < MAX; ++i) {
			st = new StringTokenizer(bufferedReader.readLine());
			balances[i] = new Balance();
			balances[i].setInitWeightLeft(Integer.parseInt(st.nextToken()));
			balances[i].setLeftBalance(new int[st.countTokens()]);
			int c1 = st.countTokens();
			for (int j = 0; j < c1; ++j) {
				balances[i].getLeftBalance()[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bufferedReader.readLine());
			balances[i].setInitWeightRight(Integer.parseInt(st.nextToken()));
			balances[i].setRightBalance(new int[st.countTokens()]);
			int c2 = st.countTokens();
			for (int j = 0; j < c2; ++j) {
				balances[i].getRightBalance()[j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < MAX; ++i) {
			getCorrectBallance(i);
		}
		for (int i = 0; i < MAX; ++i) {
			if (balances[i].getWeightLeft() > balances[i].getWeightRight()) {
				System.out.println(i + ": " + "0" + " "
						+ balanceDifference(balances[i].getWeightLeft(), balances[i].getWeightRight()));
			} else {
				System.out.println(i + ": " + balanceDifference(balances[i].getWeightLeft(), balances[i].getWeightRight()) + " "
						+ "0");
			}
		}

	}

	public static void getCorrectBallance(int id) {
		balances[id].setWeightTotal(0);
		balances[id].setWeightLeft(0);
		balances[id].setWeightLeft( balances[id].getInitWeightLeft());
		for (int j = 0; j < balances[id].getLeftBalance().length; ++j) {
			if (checker[balances[id].getLeftBalance()[j]] == 0) {
				getCorrectBallance(balances[id].getLeftBalance()[j]);
			}
			balances[id].setWeightLeft(balances[id].getWeightLeft()+ balances[balances[id].getLeftBalance()[j]].getWeightTotal());
		}
		balances[id].setWeightRight(0);
		balances[id].setWeightRight(balances[id].getInitWeightRight());
		for (int k = 0; k < balances[id].getRightBalance().length; ++k) {
			if (checker[balances[id].getRightBalance()[k]] == 0) {
				getCorrectBallance(balances[id].getRightBalance()[k]);
			}
			balances[id].setWeightRight(balances[id].getWeightRight() + balances[balances[id].getRightBalance()[k]].getWeightTotal());
		}
		balances[id].setWeightTotal(balances[id].getWeightLeft() + balances[id].getWeightRight() + 10
				+ balanceDifference(balances[id].getWeightLeft(), balances[id].getWeightRight()));
		checker[id] = 1;
		return;
	}

	public static int balanceDifference(int a, int b) {
		return (a > b)? (a - b): (b - a);
	}

}