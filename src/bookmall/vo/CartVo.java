package bookmall.vo;

public class CartVo {
	//카트(cart)
	private Long counts;
	
	//서적(book)
	private Long bookNo;
	private String title;
	private Long price;
	
	//고객(member)
	private Long memberNo;
	private String name;
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "CartVo [counts=" + counts + ", bookNo=" + bookNo + ", title=" + title + ", price=" + price
				+ ", memberNo=" + memberNo + ", name=" + name + "]";
	}
	
}
