package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {
	public static void main(String[] args) {
		//insert Test
		insertTest("소설");
		insertTest("수필");
		insertTest("컴퓨터/IT");
		
		//getList Test
		getListTest();
	}
	//insert Test
	public static boolean insertTest(String categoryName) {
		CategoryVo vo = new CategoryVo();
		vo.setCategoryName(categoryName);
		return new CategoryDao().insert(vo);
	}
	
	//getList Test
	public static void getListTest() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
}
