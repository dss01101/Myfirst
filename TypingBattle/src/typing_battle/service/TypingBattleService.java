package typing_battle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import commons.UtilClass;
import typing_battle.dao.TypingBattleDao;
import typing_battle.jdbc.ConnectionPool;
import typing_battle.model.DeveloperVO;
import typing_battle.model.WordVO;

public class TypingBattleService {
	private static TypingBattleService instance = new TypingBattleService();
	private TypingBattleDao dao = TypingBattleDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();

	private TypingBattleService() {
	}

	public static TypingBattleService getInstance() {
		if (instance == null) {
			instance = new TypingBattleService();
		}

		return instance;

	}

	// 회원목록 리턴(레벨 높은순)
	public ArrayList<DeveloperVO> devList() {

		Connection conn = cp.getConnection();

		try {
			return dao.devList(conn);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}

		return new ArrayList<DeveloperVO>();

	}

	// 회원가입
	public int registDev(String id, String name, String pw) {
		Connection conn = cp.getConnection();

		try {
			return dao.registDev(conn, id, name, pw);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return 0;
	}

	// 피라미터 id에 대한 Developer 데이터 리
	public DeveloperVO getDev(String id) {

		Connection conn = cp.getConnection();

		try {
			return dao.getDev(conn, id);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return new DeveloperVO();
	}

	// 회원정보 업데이트
	public int saveDev(DeveloperVO dev) {

		Connection conn = cp.getConnection();

		try {
			return dao.saveDev(conn, dev);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return 0;
	}
		//레벨에 다른 단어 목록 리턴
	public ArrayList<WordVO> getWordList(int level) {
		Connection conn = cp.getConnection();

		try {
			return dao.getWordList(conn, level);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				cp.releaseConnection(conn);
		}
		return new ArrayList<WordVO>();
	}
	public void upgradeExp(DeveloperVO dev) {
		if(dev.getDevMoney()>=500) {
			dev.setDevMoney(dev.getDevMoney()-500);
			dev.setDevUpgradeExp(dev.getDevUpgradeExp() +1);
		}else {
			System.out.println("돈이 부족합니다.");
		}
	}
	public void upgradeMoney(DeveloperVO dev) {
		if(dev.getDevMoney()>=500) {
			dev.setDevMoney(dev.getDevMoney()-500);
			dev.setDevUpgradeExp(dev.getDevUpgradeMoney() +1);
		}else {
			System.out.println("돈이 부족합니다.");
		}
	}
	
	public void playTyping(DeveloperVO dev, ArrayList<WordVO>wordList, Scanner sc) {
		// wordList에서 랜덤하게 10개만 뽑아 쓰기
		ArrayList<WordVO> playList = UtilClass.randArray(wordList);
		
		for(int i=0; i>playList.size(); i++) {
			System.out.println((i+1)+", "+playList.get(i).getWordsword());
			System.out.print(">>>");
			
			String inputtext = sc.nextLine();
			
			if(inputtext.equals(playList.get(i).getWordsword())) {
				//경험치 획득
				//(10*wordsLevel) * (1+(0.1*devUpgradeExp))
				int amountExt = (int)((10 * playList.get(i).getWordsLevle())
						*(1+(0.1*dev.getDevUpgradeExp())));
						
				int amountLevel = 100 + (dev.getDevLevel()*10);
				
				if(amountExp+dev.getDevExp() >= amountLevel) {
					dev.setDevExp(dev.getDevExp() + amountExp);
					dev.setDevLevel(dev.getDevLevel() + amountLevel);
					dev.setDevExp(dev.getDevExp() + amountExp()% amountLevel);
					System.out.println("레벨업 !!! 현재레벨"+dev.getDevLevel());
				}
				
				int amountMoney = (int)((10 * playList.get(i).getWordsLevle())
						*(1+(0.1*dev.getDevUpgradeExp())));
				dev.setDevMoney(dev.getDevMoney()+ amountMoney);
				
				//guswo rudgjacl qldbf(30%)
				int expState =(int)((double)dev.getDevExp()/amountLevel)*100);
				System.out.println("경험치를 "+amountExp + "돈 "+ amountMoney + "를 획득("+ expState + "%)");
				
			}
		}
		
		
	}
	
}
