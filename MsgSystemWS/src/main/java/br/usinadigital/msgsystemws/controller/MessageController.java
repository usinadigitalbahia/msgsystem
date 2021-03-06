package br.usinadigital.msgsystemws.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.usinadigital.msgsystemws.dao.MessageDAO;
import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.model.WSRequestGetMessagesFromDateByCategories;
import br.usinadigital.msgsystemws.util.Constants;
import br.usinadigital.msgsystemws.util.Utils;
 
@Controller
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageDAO messageDAO;
	
	@RequestMapping(value = Constants.GET_TEST_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody Message getTestMessage() {
		
		logger.info("Begin request test Message");
		Message cat = new Message();
		cat.setId(0);
		cat.setTitle("Thi is the title");	
		cat.setText("Thi is the text");		
		logger.info("End request test Message");
		
		return cat;
	}
	
	@RequestMapping(value = Constants.GET_ALL_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody List<Message> getAllMessages() {
		
		logger.info("Start request: " + Constants.GET_ALL_MESSAGE);
		List<Message> list = messageDAO.getAll();
		for (Message msg : list) logger.info("Messages Requested:" + msg);
		logger.info("Stop request: " + Constants.GET_ALL_MESSAGE);
		
		return list;
	}

	@RequestMapping(value = Constants.SAVE_MESSAGE, method = RequestMethod.POST)
	public @ResponseBody Message save(@RequestBody  Message message) {
		
		logger.info("Start request: " + Constants.SAVE_MESSAGE);
		logger.info("Message to save: " + message);
		int savedCategories = messageDAO.save(message);
		logger.info("Stop request: " + Constants.SAVE_MESSAGE);
		
		return null;
	}
	
	@RequestMapping(value = Constants.GET_MESSAGE_FROM_DATE_BY_CATEGORIES, method = RequestMethod.POST)
	public @ResponseBody List<Message> getMessages(@RequestBody  WSRequestGetMessagesFromDateByCategories request) {
		
		logger.info("Start request: " + Constants.GET_MESSAGE_FROM_DATE_BY_CATEGORIES);
		logger.info("Data requested (fromDate): " + Utils.fromDateToString(request.getFromDate()));
		logger.info("Data requested (categories): " + Arrays.toString(request.getCategoriesId()));
		List<Message> list = messageDAO.getMessagesFromDateByCategories(request.getFromDate(), request.getCategoriesId());
		for (Message m : list) logger.info("Messages Requested: " + m);
		logger.info("Stop request: " + Constants.GET_MESSAGE_FROM_DATE_BY_CATEGORIES);
		
		return list;
	}
	
	@RequestMapping(value = Constants.GET_LAST_INSERTED_MESSAGE, method = RequestMethod.GET)
	public @ResponseBody Message getLastInsertedMessage() {
		
		logger.info("Start request: " + Constants.GET_LAST_INSERTED_MESSAGE);
		Message message = messageDAO.getLastInsertedMessage();
		logger.info("Messages Requested: " + message);
		logger.info("Stop request: " + Constants.GET_LAST_INSERTED_MESSAGE);
		
		return message;
	}
}
