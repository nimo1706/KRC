package dto;

public class DB_TWEET {

	private int id;
	private String text;
	private int user_Id;

	public DB_TWEET(int id, String text, int user_Id) {
		this.id = id;
		this.text = text;
		this.user_Id = user_Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

}
