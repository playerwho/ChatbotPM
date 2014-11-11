package chatbot.view;

import javax.swing.JOptionPane;

import chatbot.controller.ChatbotAppController;

public class ChatbotView
{
	private ChatbotAppController baseController;
	
	public ChatbotView(ChatbotAppController baseController)
	{
		this.baseController = baseController;
	}
/**
 * Shows a string from the chatbot with the availability of user input.	
 * @param currentInput the supplied String
 * @return the users typed response
 */
	public String showChatbotDialog(String currentInput)
	{
		String result = "";
		
		result = JOptionPane.showInputDialog(null, baseController.getMySillyChatbot().getName() + " says: " +currentInput);
		 
		return result;	
	}
	/**
	 * shows a String from the chatbot as a popup window.
	 * @param currentInput the string from the chatbot
	 */
	public void showChatbotMessage(String currentInput)
	{
		JOptionPane.showMessageDialog(null, baseController.getMySillyChatbot().getName() + " says: " + currentInput);
	}
}
