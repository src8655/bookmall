package bookmall.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;


public class OrdersDaoTest {
	public static void main(String[] args) {
		//insert Test
		List<OrdersBookVo> list = new ArrayList<OrdersBookVo>();
		OrdersBookVo obVo1 = new OrdersBookVo();
		obVo1.setBookNo(1L);
		obVo1.setCounts(10L);
		list.add(obVo1);		//"트와일라잇" 책 "10"권
		
		OrdersBookVo obVo2 = new OrdersBookVo();
		obVo2.setBookNo(4L);
		obVo2.setCounts(2L);
		list.add(obVo2);		//"태백산맥" 책 "2"권
		
		insertTest(0L,"의정부시 금오동", 1L, list);	//"윤민호"의 주문
		
		List<OrdersBookVo> list2 = new ArrayList<OrdersBookVo>();
		OrdersBookVo obVo3 = new OrdersBookVo();
		obVo3.setBookNo(2L);
		obVo3.setCounts(32L);
		list2.add(obVo3);		//"뉴문" 책 "32"권
		
		insertTest(0L,"양주시 은현면", 2L, list2);	//"장석준"의 주문
		
		
		//getList Test
		getListTest();
		
	}
	//insert Test
	public static boolean insertTest(Long money, String addr, Long memberNo, List<OrdersBookVo> list) {
		OrdersDao dao = new OrdersDao();
		
		//주문정보
		OrdersVo vo = new OrdersVo();
		vo.setMoney(money);
		vo.setAddr(addr);
		vo.setMemberNo(memberNo);
		
		//주문정보 추가 후 주문번호를 받는다
		Long ordersNo = dao.insertOrders(vo);
		
		//주문서적 리스트를 하나씩 주문번호를 추가하고 주문서적 입력
		for(OrdersBookVo obVo : list) {
			obVo.setOrdersNo(ordersNo);
			dao.insertOrderBook(obVo);
		}
		
		//주문서적의 총 가격을 받아옴
		Long money_sum = dao.getOrdersBookMoney(ordersNo);
		
		//주문정보의 금액을 갱신
		return dao.updateOrdersMoney(ordersNo, money_sum);
	}
	
	//getList Test
	public static void getListTest() {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getOrdersList();
		
		for(OrdersVo vo : list) {
			System.out.println(vo);
			List<OrdersBookVo> ordersBookList = dao.getOrdersBookList(vo.getNo());
			for(OrdersBookVo obVo : ordersBookList) {
				System.out.println("\t" + obVo);
			}
		}
	}
}
