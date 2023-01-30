package MemberFrame;

public class RandomQuestion {
	
	private String questionStr;
	private char [] questionChar;
	private int [] randomIndex;
	private String randomStr;
	
	public RandomQuestion(String questionStr) {
		this.questionStr = questionStr;
		randomQuestion();
	}
	
	public void randomQuestion() {
		// 1) 중복없이 랜덤으로 questionStr 인덱스 뽑아내기
		randomIndex = new int[questionStr.length()];
		int tmpInt = questionStr.length();
		for(int i=0; i<questionStr.length(); i++) {
			randomIndex[i] = (int) (Math.random()*tmpInt);
			for(int j=0; j<i; j++) {
				if(randomIndex[i] == randomIndex[j]) {
					i--;
				} 
			}
		}
		// 2) 뽑아낸 인덱스 char에다 차곡차곡 넣기
		questionChar = new char[questionStr.length()];
		int charIndex = 0;
		while(charIndex<questionStr.length()) {
			questionChar[charIndex] = questionStr.charAt(randomIndex[charIndex]);
			charIndex++;
		}
		// 3) char > String
		randomStr = new String(questionChar);
	}
	
	public String getRandomStr() {
		return randomStr;
	}
	
	public String getQuestionStr() {
		return questionStr;
	}

	public void setQuestionStr(String questionStr) {
		this.questionStr = questionStr;
	}
	
}
