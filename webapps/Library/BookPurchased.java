package Objects;

public class BookPurchased  {
	int bookId;
	int bookCount;
	String bookName;
	public void setId(int bookId){
		this.bookId=bookId;
	}
	public void setCount(int bookCount){
		this.bookCount=bookCount;
	}
	public void setName(String bookName){
		this.bookName=bookName;
	}
	public int getId() {
		return bookId;
	}
	public int getCount() {
		return bookCount;
	}
	public String getName() {
		return bookName;
	}
}