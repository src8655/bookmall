package bookmall.vo;

public class OrdersVo {
	//주문(order)
	private Long no;
	private String ordersCode;
	private Long money;
	private String addr;
	private String ordersDate;
	private String status;		//입금대기중, 배송준비, 배송중, 배송완료
	
	//고객(member)
	private Long memberNo;
	private String name;
	private String email;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrdersCode() {
		return ordersCode;
	}
	public void setOrdersCode(String ordersCode) {
		this.ordersCode = ordersCode;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", ordersCode=" + ordersCode + ", money=" + money + ", addr=" + addr
				+ ", ordersDate=" + ordersDate + ", status=" + status + ", memberNo=" + memberNo + ", name=" + name
				+ ", email=" + email + "]";
	}
	
}
