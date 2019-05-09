package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		//insert Test
		insertTest("윤민호", "010-3324-4343", "src8655@naver.com", "minho123");
		insertTest("장석준", "010-7224-2918", "seok2918@naver.com", "seok123");
		
		//getList Test
		getListTest();
	}
	//insert Test
	public static boolean insertTest(String name, String tel, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPassword(password);
		
		return new MemberDao().insert(vo);
	}
	
	//getList Test
	public static void getListTest() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
}
