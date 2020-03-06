package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pojo.Books;
import com.pojo.DateRecord;
import com.service.IBookService;
import com.service.IDateRecordService;
@SessionAttributes({"page","pageNum"})
@Controller
@RequestMapping("/date")
public class DateRecordController {
	@Autowired
	private IDateRecordService dateRecordService;
	@Autowired
	private IBookService bookService;
	/**
	 * 跳转到借还日志界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/dateRecord")
	public String dateRecord(Model model) {
		List<DateRecord> dateRecord=dateRecordService.dateRecord();
		model.addAttribute("dateRecord",dateRecord);
		return "dateRecord";
	}
	/**
	 * 根据bookId删除还书读者
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/deleteReturnReader")
	public String deleteReturnReader(Integer bookId) {
		dateRecordService.delete(bookId);
		return "redirect:/date/dateRecord";
	}
	@RequestMapping("/reader_dateRecord")
	public String reader_dateRecord(Integer readerId,Model model) {
		List<DateRecord> dateRecord=dateRecordService.selectByReaderId(readerId);
		model.addAttribute("dateRecord",dateRecord);
		return "reader_dateRecord";
	}

	@RequestMapping("/borrowReturn")
	public String borrowReturn(Model model,Integer page) {
		List<Books> books=bookService.selectBooks(page);
		Integer pageNum=(int) Math.ceil(bookService.pageCount()/5.0);
		model.addAttribute("books",books);
		model.addAttribute("page",page);
		model.addAttribute("pageNum",pageNum);
		return "borrowReturn";
	}
}
