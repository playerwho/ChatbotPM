package chatbot.model;

public class ChatbotUser
{
	//four data members
	// at least two different types(string, int, boolean, double...)
	private String userName;
	
	private int age;
	
	private boolean glasses;
	
	private int pets;
	
	public ChatbotUser()
	{
		this.userName = "";
		this.age = -999;
		this.glasses = false;
		this.pets = -999;
	}

	public String getUserName()
	{
		return userName;
	}

	public int getAge()
	{
		return age;
	}

	public boolean isGlasses()
	{
		return glasses;
	}

	public int getPets()
	{
		return pets;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setGlasses(boolean glasses)
	{
		this.glasses = glasses;
	}

	public void setPets(int pets)
	{
		this.pets = pets;
	}
}
