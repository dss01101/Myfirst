package commons;

import java.text.DecimalFormat;
import java.util.ArrayList;

import typing_battle.model.WordVO;

public class UtilClass {
	//숫자를 천 단위마다. 붙여주는 메소드
	
	public static String strMoney(int money) {
		deciFormat = new DecimalFormat("#,###");
		return deciFormat.format(money);
		
		
		
	}
	
	//wordList에서 랜덤한 10개 추출한 리스트 리턴
	public static ArrayList<WordVO> randArray(ArrayList<WordVO> wordList){
		// 0 부터 wordList.size()-1까지 (인덱스)의 랜덤숫자를 
		//10개  중복되지 않게  리스트에 담는다.
		ArrayList<Integer> randIdxList = new ArrayList<Integer>();
		
		while(randIdxList.size()<10) {
			int randInt = (int)(Math.random()* wordList.size());
			
			if(randIdxList.indexOf(randInt)== -1) {
				randIdxList.add(randInt);
			}
		}
		// randIdxList = [5,1,0,3,9,21,17,...] 라면
		// wordList 에서 randIdxList의 요소들에 해당하는
		// WordVO들만 꺼내 담아서 리턴
		ArrayList<WordVO> result = new ArrayList<WordVO>();
		for(int i=0; i< randIdxList.size();i++) {
			result.add(wordList.get(randIdxList.get(i)));
			
		}
		return result;
		
	}
	
}
