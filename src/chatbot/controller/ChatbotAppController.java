package chatbot.controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import chatbot.model.Chatbot;
import chatbot.view.ChatbotFrame;
import chatbot.view.ChatbotPanel;
import chatbot.view.ChatbotView;

/**
 * Runs the chatbot project. Owns the model and associated views
 * 
 * @author Austin Widmeier
 * @version 1.5 11/7/2014
 */
public class ChatbotAppController
{
	private ChatbotView applicationView;
	private Chatbot mySillyChatbot;
	private String startMessage;
	private String quitMessage;
	/**
	 * Reference to GUI JFrame object
	 */
	private ChatbotFrame appFrame;
	
	/**
	 * contains specific messages for the Chatbot and sets up the Chatbot interface.
	 */
	public ChatbotAppController()
	{
		applicationView = new ChatbotView(this);
		appFrame = new ChatbotFrame(this);
		mySillyChatbot = new Chatbot("Doom");
		startMessage = "welcome to the " + mySillyChatbot.getName() + " chatbot. What beith your alias!?";
		quitMessage = "goodbye cruel user :(";
	}

	/**
	 * gets the Chatbots specified name
	 * @return Chatbot name
	 */
	public Chatbot getMySillyChatbot()
	{
		return mySillyChatbot;
	}

	/**
	 * starts the JFrame with the specified startMessage text
	 */
	public void start()
	{
		try
		{
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/awid5247/Downloads/doomsounds.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    }
		catch(Exception ex)
		{
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		((ChatbotPanel) appFrame.getContentPane()).showTextMessage(startMessage);
		// ChatbotPanel testPanel = (ChatbotPanel) appFrame.getContentPane();
		// testPanel.showTextMessage(startMessage);

	}

	/**
	 * runs user specified text from the JFrame through specified checkers
	 * @param input - user input
	 * @return - Chatbot's response 
	 */
	public String getChatbotDialog(String input)
	{
		String result = "";
		
		if(mySillyChatbot.quitChecker(input))
		{
			quit();
		}

		result = mySillyChatbot.processText(input);

		return result;
	}

	/**
	 * quits Chatbot
	 */
	private void quit()
	{
		applicationView.showChatbotMessage(quitMessage);
		System.exit(0);
	}
}
