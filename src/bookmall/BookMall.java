package bookmall;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		// 데이터 생성 방법 : 테스트 클래스 5개 아래 순서로 한번씩 돌려보면 생성
		// 1) CategoryDaoTest
		// 2) BookDaoTest
		// 3) MemberDaoTest
		// 4) CartDaoTest
		// 5) OrdersDaoTest
		
		
		// 1. 회원 리스트
		System.out.println("1. 회원 리스트 -------------------------");
		MemberDao memberDao = new MemberDao();
		List<MemberVo> memberList = memberDao.getList();
		
		for(MemberVo memberVo : memberList) {
			System.out.println("\t번호 : " + memberVo.getNo()
									+ "\t이름 : " + memberVo.getName()
									+ "\t전화번호 : " + memberVo.getTel()
									+ "\t이메일 : " + memberVo.getEmail()
									+ "\t비밀번호 : " + memberVo.getPassword());
		}
		
		// 공백
		System.out.println(" ");
		
		
		
		
		
		
		// 2. 카테고리 리스트
		System.out.println("2. 카테고리 리스트 -------------------------");
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryVo> categoryList = categoryDao.getList();
		
		for(CategoryVo categoryVo : categoryList) {
			System.out.println("\t번호 : " + categoryVo.getNo()
									+ "\t카테고리이름 : " + categoryVo.getCategoryName());
		}
		
		// 공백
		System.out.println(" ");
		
		
		
		
		
		
		// 3. 상품리스트
		System.out.println("3. 상품리스트 -------------------------");
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = bookDao.getList();
		
		for(BookVo bookVo : bookList) {
			System.out.println("\t번호 : " + bookVo.getNo()
									+ "\t제목 : " + bookVo.getTitle()
									+ "\t가격 : " + bookVo.getPrice()
									+ "\t카테고리번호 : " + bookVo.getCategoryNo()
									+ "\t카테고리 : " + bookVo.getCategoryName());
		}
		
		// 공백
		System.out.println(" ");
		
		
		
		
		
		
		// 4. 카트 리스트(고객별 출력)
		System.out.println("4. 카트 리스트(고객별 출력) -------------------------");
		for(MemberVo memberVo : memberList) {
			CartDao cartDao = new CartDao();
			List<CartVo> cartList = cartDao.getList(memberVo.getNo());
			
			System.out.println("\t고객번호 : " + memberVo.getNo()
									+ "\t고객이름 : " + memberVo.getName());
			
			for(CartVo cartVo : cartList) {
				System.out.println("\t\t - 서적번호 : " + cartVo.getBookNo()
				+ "\t서적제목 : " + cartVo.getTitle()
				+ "\t서적가격 : " + cartVo.getPrice()
				+ "\t개수 : " + cartVo.getCounts());
			}
		}
		
		// 공백
		System.out.println(" ");
		
		
		
		
		
		
		// 5. 주문 리스트(고객별 출력)
		System.out.println("5. 주문 리스트(고객별 출력) -------------------------");
		for(MemberVo memberVo : memberList) {
			System.out.println("\t고객번호 : " + memberVo.getNo()
									+ "\t고객이름 : " + memberVo.getName());
			
			OrdersDao ordersDao = new OrdersDao();
			List<OrdersVo> ordersList = ordersDao.getOrdersList(memberVo.getNo());
			
			for(OrdersVo ordersVo : ordersList) {
				System.out.println("\t\t - 주문번호 : " + ordersVo.getNo()
									+ "\t주문코드 : " + ordersVo.getOrdersCode()
									+ "\t결제금액 : " + ordersVo.getMoney()
									+ "\t배송지 : " + ordersVo.getAddr()
									+ "\t주문일 : " + ordersVo.getOrdersDate());
			}
		}
		
		// 공백
		System.out.println(" ");
		
		
		
		
		
		
		// 6. 주문 도서 리스트(고객별 + 주문별 출력)
		System.out.println("6. 주문 도서 리스트(고객별 + 주문별 출력) -------------------------");
		for(MemberVo memberVo : memberList) {
			System.out.println("\t고객번호 : " + memberVo.getNo()
									+ "\t고객이름 : " + memberVo.getName());
			
			OrdersDao ordersDao = new OrdersDao();
			List<OrdersVo> ordersList = ordersDao.getOrdersList(memberVo.getNo());
			
			for(OrdersVo ordersVo : ordersList) {
				System.out.println("\t\t - 주문번호 : " + ordersVo.getNo()
									+ "\t주문코드 : " + ordersVo.getOrdersCode()
									+ "\t결제금액 : " + ordersVo.getMoney()
									+ "\t배송지 : " + ordersVo.getAddr()
									+ "\t주문일 : " + ordersVo.getOrdersDate());
				// 주문도서 리스트
				List<OrdersBookVo> ordersBookList = ordersDao.getOrdersBookList(ordersVo.getNo());
				for(OrdersBookVo obVo : ordersBookList) {
					System.out.println("\t\t\t -- 서적번호 : " + obVo.getBookNo()
					+ "\t서적제목 : " + obVo.getTitle()
					+ "\t수량 : " + obVo.getCounts());
				}
			}
		}
	}

}
