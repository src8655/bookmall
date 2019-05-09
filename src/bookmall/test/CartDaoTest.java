package bookmall.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;


public class CartDaoTest {
	public static void main(String[] args) {
		//insert Test
		insertTest(1L, 3L, 3L);		//"윤민호" 고객이 "아리랑"을 "3"권 카트에 담았다
		insertTest(1L, 2L, 3L);		//"윤민호" 고객이 "뉴문"을 "3"권 카트에 담았다
		insertTest(2L, 1L, 11L);	//"장석준" 고객이 "트와일라잇"을 "11"권 카트에 담았다

		//getList Test(전체고객)
		System.out.println("전체고객");
		getListTest();
		System.out.println("");
		System.out.println("특정고객");
		//getList Test(특정고객)
		getListTest(1L);
	}
	//insert Test
	public static boolean insertTest(Long memberNo, Long bookNo, Long counts) {
		CartVo vo = new CartVo();
		vo.setMemberNo(memberNo);
		vo.setBookNo(bookNo);
		vo.setCounts(counts);
		
		return new CartDao().insert(vo);
	}

	//getList Test(전체고객)
	public static void getListTest() {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList();
		
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
	//getList Test(특정고객)
	public static void getListTest(Long memberNo) {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList(memberNo);
		
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
}
