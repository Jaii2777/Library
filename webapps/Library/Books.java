package Objects;
public class Books {
	public String name;
	public int id;
	public int bookCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setBookCount(int bookCount)
	{
		this.bookCount=bookCount;
	}
	public int getBookCount()
	{
		return bookCount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int year;
	public String author;
}
