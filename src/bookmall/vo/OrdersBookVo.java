package bookmall.vo;

public class OrdersBookVo {
	//주문도서(order_book)
	private Long counts;
	
	//주문(orders)
	private Long ordersNo;
	private String ordersCode;
	
	//서적(book)
	private Long bookNo;
	private String title;
	
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public Long getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(Long ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getOrdersCode() {
		return ordersCode;
	}
	public void setOrdersCode(String ordersCode) {
		this.ordersCode = ordersCode;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "OrdersBookVo [counts=" + counts + ", ordersNo=" + ordersNo + ", ordersCode=" + ordersCode + ", bookNo="
				+ bookNo + ", title=" + title + "]";
	}
	
	
	
}
