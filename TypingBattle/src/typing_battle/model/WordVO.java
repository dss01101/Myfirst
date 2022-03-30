package typing_battle.model;

public class WordVO {
	private int wordsLevle;				//단어 수준
	private String wordsword;				//단어
	
	
	
	public WordVO(int wordsLevle, String wordsword) {
		super();
		this.wordsLevle = wordsLevle;
		this.wordsword = wordsword;
	}
	
	public WordVO() {}
	
	@Override
	public String toString() {
		return "WordVO [wordsLevle=" + wordsLevle + ", wordsword=" + wordsword + "]";
	}
	
	
	
	public int getWordsLevle() {
		return wordsLevle;
	}
	public void setWordsLevle(int wordsLevle) {
		this.wordsLevle = wordsLevle;
	}
	public String getWordsword() {
		return wordsword;
	}
	public void setWordsword(String wordsword) {
		this.wordsword = wordsword;
	}
	
	
	
}
