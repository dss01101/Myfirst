package typing_battle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import typing_battle.model.DeveloperVO;
import typing_battle.model.WordVO;

public class TypingBattleDao {
	private static TypingBattleDao instance = new TypingBattleDao();

	private TypingBattleDao() {
	}

	public static TypingBattleDao getInstance() {
		if (instance == null)
			instance = new TypingBattleDao();

		return instance;
	}

	// 명예의 전당
	// 개발자 목록을 레벨로 정렬해서 리턴
	public ArrayList<DeveloperVO> devList(Connection conn) throws SQLException {

		StringBuffer query = new StringBuffer();

		query.append("SELECT						");
		query.append("		dev_id					");
		query.append("	  ,dev_name				");
		query.append("	  ,dev_pw					");
		query.append("	  ,dev_level				");
		query.append("	  ,dev_exp				");
		query.append("	  ,dev_money				");
		query.append("	  ,dev_upgrade_exp		");
		query.append("	  ,dev_upgrade_money	");
		query.append("FROM							");
		query.append("		developers			");
		query.append("ORDER BY						");
		query.append("		dev_level desc		");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		ResultSet rs = ps.executeQuery();

		ArrayList<DeveloperVO> result = new ArrayList<DeveloperVO>();

		while (rs.next()) {

			DeveloperVO temp = new DeveloperVO();

			temp.setDevId(rs.getString("dev_id	"));
			temp.setDevName(rs.getString("dev_name"));
			temp.setDevPw(rs.getString("dev_pw"));
			temp.setDevLevel(rs.getInt("dev_level"));
			temp.setDevExp(rs.getInt("dev_exp"));
			temp.setDevMoney(rs.getInt("dev_money"));
			temp.setDevUpgradeExp(rs.getInt("dev_upgrade_exp"));
			temp.setDevUpgradeMoney(rs.getInt("dev_upgrade_money"));

			result.add(temp);
		}
		if (ps != null)
			ps.close();
		if (rs != null)
			rs.close();

		return result;

	}
	// 회원가입

	public int registDev(Connection conn, String id, String name, String pw) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT						");
		query.append("		dev_id					");
		query.append("	  ,dev_name				");
		query.append("	  ,dev_pw					");
		query.append("	  ,dev_level				");
		query.append("	  ,dev_exp				");
		query.append("	  ,dev_money				");
		query.append("	  ,dev_upgrade_exp		");
		query.append("	  ,dev_upgrade_money	");
		query.append("FROM							");
		query.append("		developers			");
		query.append("where 1=1					");
		query.append("		and dev_id = ?		");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		int idx = 1;

		DeveloperVO temp = new DeveloperVO(id, name, pw);

		ps.setString(idx++, temp.getDevId());
		ps.setString(idx++, temp.getDevName());
		ps.setString(idx++, temp.getDevPw());
		ps.setInt(idx++, temp.getDevLevel());
		ps.setInt(idx++, temp.getDevExp());
		ps.setInt(idx++, temp.getDevUpgradeExp());
		ps.setInt(idx++, temp.getDevUpgradeMoney());

		int cnt = ps.executeUpdate();

		if (ps != null)
			ps.close();

		return cnt;

	}

	// 회원가입할때, 입력한 아이디에 대한 중복체크
	// 로그인 시 입력한 아이디에 대한 패스워드 검사
	// 로그인 성공, 입력한 아이디에 대한 developer 데이터 가져옴

	public DeveloperVO getDev(Connection conn, String id) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("select 			");
		query.append("			developers		");
		query.append("VALUES (					");
		query.append("		  ?	");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		)					");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		DeveloperVO temp = new DeveloperVO();

		while (rs.next()) {

			temp.setDevId(rs.getString("dev_id	"));
			temp.setDevName(rs.getString("dev_name"));
			temp.setDevPw(rs.getString("dev_pw"));
			temp.setDevLevel(rs.getInt("dev_level"));
			temp.setDevExp(rs.getInt("dev_exp"));
			temp.setDevMoney(rs.getInt("dev_money"));
			temp.setDevUpgradeExp(rs.getInt("dev_upgrade_exp"));
			temp.setDevUpgradeMoney(rs.getInt("dev_upgrade_money"));
		}
		if (ps != null)
			ps.close();
		if (rs != null)
			rs.close();

		return temp;

	}
	
	// 회원정보 업데이트
	
		public int saveDev(Connection conn, DeveloperVO dev) throws SQLException {
			StringBuffer query = new StringBuffer();
			query.append("UPDATE 								");
			query.append("			developers				");
			query.append("SET   								");
			query.append("		  dev_level = ?  			");
			query.append("		, dev_exp=	?				");
			query.append("		, dev_money=	?				");
			query.append("		, dev_upgrade_exp=	?		");
			query.append("		, dev_upgrade_money=	?	");
			query.append("WHERE		1=1							");
			query.append("	AND	  dev_ID = ?					");
			
			PreparedStatement ps = conn.prepareStatement(query.toString());
			
			int idx = 1;
			
			ps.setInt(idx++, dev.getDevLevel() );
			ps.setInt(idx++, dev.getDevExp() );
			ps.setInt(idx++, dev.getDevMoney() );
			ps.setInt(idx++, dev.getDevUpgradeExp() );
			ps.setInt(idx++, dev.getDevUpgradeMoney() );
			ps.setString(idx++, dev.getDevId());
			
			int cnt = ps.executeUpdate();
			
			if(ps!=null)ps.close();
			
			return cnt;
			
		}
		
	
		
	
	
		//레벨에 다른 단어 목록 리턴
	public ArrayList<WordVO> getWordList(Connection conn, int level) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT						");
		query.append("		words_level					");
		query.append("	  ,words_word			");
		query.append("FROM							");
		query.append("		words			");
		query.append("where 1=1					");
		query.append("		and words_level = ?		");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setInt(1, level);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<WordVO> result = new ArrayList<WordVO> () ;
		
		while(rs.next()) {
			WordVO temp = new WordVO();
			
			temp.setWordsLevle(rs.getInt("words_level"));
			temp.setWordsword(rs.getString("words_word"));
			result.add(temp);
			
		}
		
		if(ps!=null)ps.close();
		if(rs!=null)rs.close();
		
		return result;
	}

}
