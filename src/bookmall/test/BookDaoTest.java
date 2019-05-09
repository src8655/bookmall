package bookmall.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;


public class BookDaoTest {
	public static void main(String[] args) {
		//insert Test
		insertTest("트와일라잇", 60000L, 1L);
		insertTest("뉴문", 75000L, 1L);
		insertTest("아리랑", 32000L, 2L);
		insertTest("태백산맥", 50000L, 3L);
		
		//getList Test
		getListTest();
	}
	//insert Test
	public static boolean insertTest(String title, Long price, Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		return new BookDao().insert(vo);
	}
	
	//getList Test
	public static void getListTest() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
