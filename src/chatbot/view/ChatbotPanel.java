package chatbot.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import chatbot.controller.ChatbotAppController;
import chatbot.view.ChatbotFrame;

public class ChatbotPanel extends JPanel
{
	
	/**
	 * connects app controller to base controller
	 */
	private ChatbotAppController baseController;
	
	/**
	 * adds a button to the panel
	 */
	private JButton firstButton;
	
	/**
	 * adds a text field to the panel
	 */
	private JTextField firstTextField;
	
	/**
	 * changes layout of the panel
	 */
	private SpringLayout baseLayout;
	
	/**
	 * adds scroll bars to the chatPane
	 */
	private JScrollPane chatPane;
	
	/**
	 * declares the chatArea
	 */
	private JTextArea chatArea;
	
	/**
	 * adds a doomlbl, aka the background image when ran
	 */
	private JLabel doomlbl;
	
	/**
	 * connects chatbot panel to base controller
	 * @param baseController on the panel is the same as baseController on the appController
	 */
	public ChatbotPanel(ChatbotAppController baseController)
	{
		this.baseController = baseController;
		firstButton = new JButton("Send");
		firstTextField = new JTextField(20);
		baseLayout = new SpringLayout();
		chatArea = new JTextArea(15,35);
		chatPane = new JScrollPane(chatArea);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 150, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatPane, -100, SpringLayout.SOUTH, this);
		
		setupPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * sets up window pane
	 */
	private void setupPane()
	{
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		chatArea.setBackground(Color.ORANGE);
		firstTextField.setBackground(Color.YELLOW);
		
		
		
	}
	
	/**
	 * sets up the panel view
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		try
		{
		doomlbl = new JLabel((new ImageIcon(ImageIO.read(new File("C:/Users/awid5247/Downloads/doomlogo.jpg")))));
		baseLayout.putConstraint(SpringLayout.NORTH, doomlbl, 0, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, doomlbl, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, doomlbl, 400, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, doomlbl, 0, SpringLayout.EAST, this);
		doomlbl.setBackground(new Color(240, 240, 240));
		}
		catch(IOException e)
		{
			System.out.println("Image does not exist");
		}
		
		add(doomlbl);
		this.add(chatPane);
		this.setSize(700, 400);
		this.add(firstButton);
		this.add(firstTextField);
	}
	
	/**
	 * sets up the layout view
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 270, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 41, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstButton, -500, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstButton, -61, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 98, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 0, SpringLayout.WEST, firstButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstTextField, 168, SpringLayout.NORTH, this);
	}
	
	/**
	 * listens for a button click
	 */
	private void setupListeners()
	{
		firstButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent click)
			{
				String currentInput = firstTextField.getText();
				String result = baseController.getChatbotDialog(currentInput);
				showTextMessage(currentInput);
				showTextMessage(result);
				
				firstTextField.setText("");
				firstTextField.setCursor(getCursor());
				firstTextField.requestFocus();
			}
		});
	}
	
	/**
	 * sends user text to the chatArea for processing and response
	 * @param userInput - user specified text
	 * @return - the user specified text
	 */
	public String showTextMessage(String userInput)
	{
		chatArea.append("\n" + userInput);
		return firstTextField.getText();
	}
}
