package chatbot.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

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
	        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ChatbotAppController.class.getResource("/chatbot/addOns/doomsounds.wav")));
	        Clip clip = AudioSystem.getClip();
	        
	        clip.open(audio);
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
	
	public void saveText(String conversation, boolean appendToEnd)
	{
		String fileName = "SavedText.txt";
		
		PrintWriter outputWriter;
		
		if(appendToEnd)
		{
			try
			{
				outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, appendToEnd)));
				outputWriter.println(conversation);
				outputWriter.close();
			}
			catch(FileNotFoundException noExistingFile)
			{
				JOptionPane.showMessageDialog(appFrame, "There is no file here");
				JOptionPane.showMessageDialog(appFrame, noExistingFile.getMessage());
			}
			catch(IOException inputOutputError)
			{
				JOptionPane.showMessageDialog(appFrame, "There is no file here");
				JOptionPane.showMessageDialog(appFrame, inputOutputError.getMessage());
			}
		}
		else
		{
			try
			{
				outputWriter = new PrintWriter(fileName);
				outputWriter.println(conversation);
				outputWriter.close();
			}
			catch(FileNotFoundException noFileIsHere)
			{
				JOptionPane.showMessageDialog(appFrame, "There is no file here");
				
			}
		}
	}

	public String readTextFromFile()
	{
		String fileText = "";
		String filePath = "";
		String fileName = filePath + "SavedText.txt";
		File inputFile = new File(fileName);
		
		try
		{
			Scanner fileScanner = new Scanner(inputFile);
			
			while(fileScanner.hasNext())
			{
				fileText += fileScanner.nextLine() + "\n";		
			}
			
			fileScanner.close();
		}
		catch(FileNotFoundException fileException)
		{
			JOptionPane.showMessageDialog(appFrame, "The file is not here");
		}
		
		return fileText;
	}
}
