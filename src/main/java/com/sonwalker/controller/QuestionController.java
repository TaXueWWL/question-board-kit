package com.sonwalker.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonwalker.domain.QuestionDetail;
import com.sonwalker.repository.QuestionDetailRepository;
import com.sonwalker.util.DateUtil;
import com.sonwalker.util.IDGenerator;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe 问题页面处理控制器
 */
@Controller
@RequestMapping("question")
public class QuestionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionDetailRepository questionDetailRepository;
	
	/**
	 * 获得所有的问题列表
	 * @return
	 */
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody List<QuestionDetail> getAllQuestions() {
		return questionDetailRepository.findAll();
	}
	
	/**
	 * 通过关键词进行模糊查询
	 * @param keyword
	 * @return
	 */
	@GetMapping("search/keyword/{keyword}")
	public @ResponseBody List<QuestionDetail> getDetailsByLikeKey(@PathVariable(value = "keyword") String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}
		LOGGER.info("search/keyword/{keyword}:" + keyword);
		return questionDetailRepository.queryDetailsByLikeKey(keyword);
	}
	
	@GetMapping("search/type/{type}")
	public @ResponseBody List<QuestionDetail> getDetailsByType(@PathVariable(value = "type") String type) {
		if (StringUtils.isEmpty(type)) {
			return null;
		}
		LOGGER.info("search/type/{type}:" + type);
		return questionDetailRepository.queryDetailsByType(type);
	}
	
	/**
	 * 根据id查询一个问题
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody QuestionDetail getQuestionDetailById(@PathVariable("id") String id) {
		LOGGER.info("{id}:" + id);
		return questionDetailRepository.getDetailById(id);
	}
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public @ResponseBody String insertQuestionDetail(HttpServletRequest request) {
		String qbkId = IDGenerator.generateId();
		String qbkTitle = request.getParameter("qbkTitle");
		String qbkKey = request.getParameter("qbkKey");
		String qbkAnswer = request.getParameter("qbkAnswer");
		String qbkDate = DateUtil.parseDateToString(new Timestamp(System.currentTimeMillis()));
		String qbkType = request.getParameter("qbkType");
		if (!StringUtils.isEmpty(qbkTitle)) {
			QuestionDetail questionDetail = QuestionDetail.builder()
					.qbkId(qbkId)
					.qbkTitle(qbkTitle)
					.qbkKey(qbkKey)
					.qbkAnswer(qbkAnswer)
					.qbkDate(qbkDate)
					.qbkType(qbkType).build();
			questionDetailRepository.insertOneQuestion(questionDetail);
			LOGGER.info("qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey + "qbkDate:" + qbkDate);
			return "success";
		}
		LOGGER.info("qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey + "qbkDate:" + qbkDate);
		return "failed";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody String updateQuestionDetail(HttpServletRequest request) {
		String qbkId = request.getParameter("qbkId");
		String qbkTitle = request.getParameter("qbkTitle");
		String qbkKey = request.getParameter("qbkKey");
		String qbkAnswer = request.getParameter("qbkAnswer");
		String qbkType = request.getParameter("qbkType");
		if (!StringUtils.isEmpty(qbkTitle)) {
			QuestionDetail questionDetail = QuestionDetail.builder()
					.qbkId(qbkId)
					.qbkTitle(qbkTitle)
					.qbkKey(qbkKey)
					.qbkAnswer(qbkAnswer)
					.qbkType(qbkType).build();
			questionDetailRepository.updateOneQuestion(questionDetail, qbkId);
			LOGGER.info("qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey);
			return "success";
		}
		LOGGER.info("qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey);
		return "failed";
	}
}
