package chatbot.model;

import java.util.ArrayList;

/**
 * The chatbot model class. used for checking and manipulating strings.
 * 
 * @author Austin Widmeier
 * @version 1.9 11/7/2014
 */
public class Chatbot
{
	private ChatbotUser myUser;
	
	private ArrayList<String> userInputList;
	
	/**
	 * creates memelist list
	 */
	private ArrayList<String> memeList;

	/**
	 * name of chatbot
	 */
	private String name;

	/**
	 * chat counter
	 */
	private int chatCount;

	/**
	 * creates a Chatbot object with the supplied name and initializes the current number of chats to zero
	 * @param name - the supplied name for the Chatbot.         
	 */
	public Chatbot(String name)
	{
		userInputList = new ArrayList<String>();
		memeList = new ArrayList<String>();
		this.name = name;
		chatCount = 0;
		myUser = new ChatbotUser();
		
		
		fillTheMemeList();
	}

	/**
	 * gets the name of Chatbot
	 * @return the current name of the Chatbot
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Retreves chat count
	 * @return chatCount
	 */
	public int getChatCount()
	{
		return chatCount;
	}

	/**
	 * defines name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * fills the meme list with text
	 */
	public void fillTheMemeList()
	{
		memeList.add("kitties");
		memeList.add("one does not simply");
		memeList.add("doge");
		memeList.add("mhhhh, doughnuts");
		memeList.add("challenge accepted");
		memeList.add("nyan");
	}

	/**
	 * Processes input from the user againts the checker methods. Returns the next output for the view
	 * @param currentInput - the supplied text       
	 * @return the processed text based on checker or other methods
	 */
	public String processText(String currentInput)
	{
		String result = "";
		
		if(getChatCount() < 5)
		{
			result = introduceUser(currentInput);	
		}
		else if (currentInput != null && currentInput.length() > 0)
		{
			result = randomChatConversation(currentInput);
		}
		else
		{
			result = "use Words!!";
			chatCount--;
		}
		updateChatCount();
		return result;
	}
	
	private String introduceUser(String input)
	{
		String userQuestion = "";
		//ask questions about all data members
		//you will need ifs or a switch
		//assign via myUser.set
		if(getChatCount() == 0)
		{
			 myUser.setUserName(input);
			 userQuestion = "good name " + myUser.getUserName() + ", what is your age?";
		}
		else if(getChatCount() == 1)
		{
			int userAge = Integer.parseInt(input);
			myUser.setAge(userAge);
			userQuestion = myUser.getAge() + " is nice! I'm currently 5061, do you need the bifocals?";
		}
		else if(getChatCount() == 2)
		{
			boolean userGlasses = Boolean.parseBoolean(input);
			myUser.setGlasses(userGlasses);
			userQuestion = "Bifocals are a sign of weakness. How many deadly animals do you have?";
		}
		else if(getChatCount() == 3)
		{
			int userPets = Integer.parseInt(input);
			myUser.setPets(userPets);
			userQuestion = myUser.getPets() +" is a good number. I have 3 that are able to rip a demonic being in half! anything else you would like to talk about?";
		}
		
		return userQuestion;
	}
	
	private String randomChatConversation(String input)
	{
		String conversation = "";
		
		int randomPosition = (int) (Math.random() * 6);
		if (randomPosition == 0)
		{
			if (stringChecker(input))
			{
				conversation = "very too long";
			}
			else
			{
			conversation = "hiiii";
			}
		}
		else if (randomPosition == 1)
		{
			if (contentChecker(input))
			{
				conversation = "CARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTSCARPARTS";
			} 
			else
			{
				conversation = "oh";
			}
		}
		else if( randomPosition == 2)
		{
			if (memeChecker(input))
			{
				conversation = "Wow " + input + " is a meme. yay!";
			}
			else
			{
				conversation = "not a meme, try again";
			}
		}
		else if(randomPosition == 3)
		{
			//talk about user
		}
		else if(randomPosition == 4)
		{
			// add to list
			userInputList.add(input);
			conversation = "Thank you for the comment";
		}
		else
		{
			if(userInputChecker(input))
			{
				conversation = "That was nice - you removed it from the list";		
			}
			else
			{
				conversation = "that wasnt in the conversation before";
			}
		}
		
		return conversation;
	}
	
	private String userTopic(String userInput)
	{
		String userBasedResponse = "";
		
		int randomUserTopic = (int) (Math.random() * 6);
		
		switch(randomUserTopic)
		{
			case 1:
				userBasedResponse = myUser.isGlasses() + "is the response for the bifocals!";
				break;
			case 0:
				userBasedResponse = myUser.getUserName() + "is the name of a leader!";
				break;
			default:
				userBasedResponse = myUser.getAge() + "is an age of time";
				break;
		}
		
		return userBasedResponse;
	}
	
	private boolean userInputChecker(String userInput)
	{
		boolean matchesInput = false;
		for(int loopCount = 0; loopCount < userInputList.size(); loopCount++)
		{
			if(userInput.equalsIgnoreCase(userInputList.get(loopCount)))
			{
				matchesInput =true;
				userInputList.remove(loopCount);
				loopCount--;
			}
		}
		return matchesInput;
	}

	/**
	 * updates chatcount + one
	 */
	private void updateChatCount()
	{
		chatCount++;
	}

	/**
	 * checks user input for string length, if its over 30 then boolean is true
	 * @param input - user input         
	 * @return boolean tooLong
	 */
	private boolean stringChecker(String input)
	{
		boolean tooLong = false;

		String Str1 = new String(input);

		System.out.print("String Length :");

		System.out.println(Str1.length());

		if (Str1.length() >= 30)
		{
			tooLong = true;
		}

		return tooLong;
	}

	/**
	 * checks each input from the user for a specified meme
	 * @param input - is user input        
	 * @return a meme
	 */
	private boolean memeChecker(String input)
	{
		boolean isAMeme = false;

		for (String currentMeme : memeList)
		{
			if (input.equalsIgnoreCase(currentMeme))
			{
				isAMeme = true;
			}
		}

		for (int loopCounter = 0; loopCounter < memeList.size(); loopCounter++)
		{
			if (input.equalsIgnoreCase(memeList.get(loopCounter)))
			{
				isAMeme = true;
			}
		}

		return isAMeme;
	}

	/**
	 * checks user input for special topic
	 * @param input - user input            
	 * @return the string
	 */
	private boolean contentChecker(String input)
	{
		boolean containsCars = false;

		if (input.contains("car"))
		{
			containsCars = true;
		}

		return containsCars;
	}
	
	/**
	 * Checks a user response and based on that response, the program either keeps running or quits
	 * @param Checks - the user reponse         
	 * @return if user input is "bye", then application is ok to quit
	 */
	public boolean quitChecker(String input)
	{
		boolean okToQuit = false;

		if (input != null && input.equalsIgnoreCase("Bye"))
		{
			okToQuit = true;
		}

		return okToQuit;
	}
}
