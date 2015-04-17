package chatbot.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
	
	private JButton submitButton; 
	
	private JButton loadButton;
	
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
	private JButton saveButton;
	
	
	
	/**
	 * connects chatbot panel to base controller
	 * @param baseController on the panel is the same as baseController on the appController
	 */
	public ChatbotPanel(ChatbotAppController baseController)
	{
		this.baseController = baseController;
		
		firstButton = new JButton("Send");
		submitButton = new JButton("Submit");
		loadButton = new JButton("Load previous chat");
		saveButton = new JButton("Save Chat");
		
		firstTextField = new JTextField(20);
		
		baseLayout = new SpringLayout();

		chatArea = new JTextArea(15,35);
		
		chatPane = new JScrollPane(chatArea);
		
		
		
		
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
		doomlbl = new JLabel(new ImageIcon(ChatbotPanel.class.getResource("/chatbot/addOns/doomlogo.jpg")));
		doomlbl.setBackground(new Color(240, 240, 240));
		
		this.setLayout(baseLayout);
		this.add(doomlbl);
		this.add(chatPane);
		this.setSize(700, 400);
		this.add(firstButton);
		this.add(submitButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(firstTextField);
		setComponentZOrder(doomlbl, this.getComponentCount()-1);	
	}
	
	/**
	 * sets up the layout view
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 150, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatPane, -100, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 270, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 41, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstButton, -500, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstButton, -61, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 98, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 0, SpringLayout.WEST, firstButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstTextField, 168, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, doomlbl, 0, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, doomlbl, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, doomlbl, 400, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, doomlbl, 0, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, submitButton, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 200, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, submitButton, 500, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, submitButton, 600, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 350, SpringLayout.WEST, this);
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
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String chat = chatArea.getText();
				baseController.saveText(chat, true);
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String savedChat = baseController.readTextFromFile();
				
				if(savedChat.length()<1)
				{
					chatArea.setText("No Words Found!");
				}
				else
				{
					chatArea.setText(savedChat);
				}
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
