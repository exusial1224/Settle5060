package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

import bean.Membership;

public class MembershipDAO extends RootDAO {




//	メールアドレス重複チェック 戻り値メールアドレス
	public String searchSameMail(String mbr_mail) throws Exception {

			String search="";
			Connection con=getConnection();

			PreparedStatement stdup=con.prepareStatement(
					"SELECT * FROM MEMBERSHIP WHERE MBR_MAIL=?");

				stdup.setString(1, mbr_mail);
				ResultSet rsdup=stdup.executeQuery();
				while (rsdup.next()) {
					search=rsdup.getString("MBR_MAIL");
				}

			stdup.close();
			con.close();
			return search;
		}


//	新規会員登録、登録されると1を返す(パスワードはハッシュ済を入れる)
	public int AddNewMember(String mbr_name, String mbr_password, String mbr_mail, String mbr_tel,String mbr_address, LocalDate mbr_birth) throws Exception{

		Connection con=getConnection();
		int line=0;

		PreparedStatement st=con.prepareStatement(
				"INSERT INTO MEMBERSHIP VALUES(NULL,?,?,?,?,?,?,?,NULL)");

		st.setString(1, mbr_name);
		st.setString(2, mbr_password);
		st.setString(3, mbr_mail);
		st.setString(4, mbr_tel);
		st.setString(5, mbr_address);
		st.setDate(6, Date.valueOf(mbr_birth));

		// 現在時刻のTimestampを取得
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		st.setTimestamp(7, currentTimestamp);


		line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}



//	MBR_IDで情報取得
	public Membership getMemberInfo(int mbr_id) throws Exception {

		    Membership membership = null;

			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement(
				"SELECT * FROM MEMBERSHIP WHERE MBR_ID = ?");
			st.setInt(1, mbr_id);
			ResultSet rs=st.executeQuery();

			while (rs.next()) {
				membership = new Membership();
				membership.setMbr_id(rs.getInt("MBR_ID"));
				membership.setMbr_name(rs.getString("MBR_NAME"));
				membership.setMbr_password(rs.getString("MBR_PASSWORD"));
				membership.setMbr_mail(rs.getString("MBR_MAIL"));
				membership.setMbr_tel(rs.getString("MBR_TEL"));
				membership.setMbr_address(rs.getString("MBR_ADDRESS"));
				membership.setMbr_birth(rs.getDate("MBR_BIRTH").toLocalDate());
				membership.setMbr_reg(rs.getTimestamp("MBR_REG").toLocalDateTime());
				membership.setMbr_mod(rs.getTimestamp("MBR_MOD").toLocalDateTime());
			}

			st.close();
			con.close();
			return membership;
		}


	//会員情報の変更
	public int changeMemberInfo(int mbr_id,String mbr_name,String mbr_tel,String mbr_address, LocalDate mbr_birth) throws Exception{

		Connection con = getConnection();

		PreparedStatement st=con.prepareStatement("UPDATE MEMBERSHIP SET MBR_NAME = ?,MBR_TEL = ?,MBR_ADDRESS = ?,MBR_BIRTH = ?,MBR_MOD = ? WHERE MBR_ID = ?");

		st.setString(1, mbr_name);
		st.setString(2, mbr_tel);
		st.setString(3, mbr_address);
		st.setDate(4, Date.valueOf(mbr_birth));

		// 現在時刻のTimestampを取得
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		st.setTimestamp(5, currentTimestamp);
		st.setInt(6, mbr_id);



		int check = st.executeUpdate();

		st.close();
		con.close();

		return check;

	}


	//ログイン、メールとパスワード一致で会員情報を取得
		public Membership loginMember(String mbr_mail, String mbr_password) throws Exception {

			Membership membership = null;

			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement(
				"SELECT * FROM MEMBERSHIP WHERE MBR_PASSWORD = ? AND MBR_MAIL = ?");

			st.setString(1, mbr_password);
			st.setString(2, mbr_mail);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				membership = new Membership();
				membership.setMbr_id(rs.getInt("MBR_ID"));
				membership.setMbr_name(rs.getString("MBR_NAME"));
				membership.setMbr_password(rs.getString("MBR_PASSWORD"));
				membership.setMbr_mail(rs.getString("MBR_MAIL"));
				membership.setMbr_tel(rs.getString("MBR_TEL"));
				membership.setMbr_address(rs.getString("MBR_ADDRESS"));
				membership.setMbr_birth(rs.getDate("MBR_BIRTH").toLocalDate());
				membership.setMbr_reg(rs.getTimestamp("MBR_REG").toLocalDateTime());
				membership.setMbr_mod(rs.getTimestamp("MBR_MOD").toLocalDateTime());
			}

			st.close();
			con.close();

			return membership;
		}


		//パスワードの再設定
		public int reconfigurePassword(String mbr_mail, String mbr_password) throws Exception{

			int check = 0;

			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement("UPDATE MEMBERSHIP SET MBR_PASSWORD = ? WHERE MBR_MAIL = ?");

			st.setString(1, mbr_password);
			st.setString(2, mbr_mail);

			check = st.executeUpdate();

			st.close();
			con.close();

			return check;

		}


		//パスワードの変更
		public int updatePassword(int mbr_id, String mbr_password) throws Exception{

			int check = 0;

			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement("UPDATE MEMBERSHIP SET MBR_PASSWORD = ? WHERE MBR_ID = ?");

			st.setString(1, mbr_password);
			st.setInt(2, mbr_id);

			check = st.executeUpdate();

			st.close();
			con.close();

			return check;

		}


		//メールアドレスの変更
		public int updateMail(int mbr_id, String mbr_mail) throws Exception{

			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement("UPDATE MEMBERSHIP SET MBR_MAIL = ? WHERE MBR_ID = ?");
			st.setString(1, mbr_mail);
			st.setInt(2, mbr_id);

			int check = st.executeUpdate();

			st.close();
			con.close();

			return check;

		}


		//入力されたパスワードが正しいのかチェック
		public boolean checkPassword(int mbr_id, String mbr_password) throws Exception {

			boolean check = false;

			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement("SELECT MBR_PASSWORD FROM MEMBERSHIP WHERE MBR_ID = ?");
			st.setInt(1, mbr_id);

			ResultSet rs = st.executeQuery();

			rs.next();
			String pass = rs.getString("MBR_PASSWORD");

			if (pass.equals(mbr_password)) {
				check = true;
			}

			st.close();
			con.close();

			return check;
		}




}
