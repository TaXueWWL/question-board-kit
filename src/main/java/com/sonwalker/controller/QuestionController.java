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
import org.springframework.web.bind.annotation.RequestParam;
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
public class QuestionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionDetailRepository questionDetailRepository;

	private List<QuestionDetail> list;
	
	/**
	 * 获得所有的问题列表
	 * @return
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String getAllQuestions(HttpServletRequest request) {
		List<QuestionDetail> questionDetails = questionDetailRepository.findAll();
		if (questionDetails != null && questionDetails.size() > 0) {
			request.setAttribute("questionDetails", questionDetails);
		}
		return "index";
	}
	
	/**
	 * 通过关键词进行模糊查询
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "search", method = {RequestMethod.GET, RequestMethod.POST})
	public String getDetailsByLikeKey(@RequestParam(value = "keyword") String keyword, HttpServletRequest request) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}
		if ("0,1,2,3".indexOf(keyword) >= 0) {
			LOGGER.info("关键词为类别:" + keyword);
			list = questionDetailRepository.queryDetailsByType(keyword);
		} else {
			LOGGER.info("关键词为词语:" + keyword);
			list = questionDetailRepository.queryDetailsByLikeKey(keyword);
		}
		LOGGER.info("question/search/keyword/{keyword}:" + keyword);
		request.setAttribute("list", list);
		return "search";
	}
	
	/**
	 * 根据id查询一个问题
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "question_detail", method = RequestMethod.GET)
	public String getQuestionDetailById(@RequestParam("qbkId") String qbkId, HttpServletRequest request) {
		LOGGER.info("{id}:" + qbkId);
		QuestionDetail detail = null;
		if (qbkId != null) {
			detail = questionDetailRepository.getDetailById(qbkId);
		}
		request.setAttribute("questionDetail", detail);
		return "details";
	}
	
	
	@RequestMapping(value = "insert_question", method = {RequestMethod.POST,RequestMethod.GET})
	public String insertQuestionDetail(HttpServletRequest request) {
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
		}
		LOGGER.info("qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey + "qbkDate:" + qbkDate);
		return "forward:/";
	}
	
	@RequestMapping(value = "question_update", method = RequestMethod.GET)
	public String updateQuestionDetailSearch(@RequestParam("qbkId") String qbkId, HttpServletRequest request) {
		LOGGER.info("{id}:" + qbkId);
		QuestionDetail detail = null;
		if (qbkId != null) {
			detail = questionDetailRepository.getDetailById(qbkId);
		}
		request.setAttribute("questionDetail", detail);
		return "update";
	}
	
	
	@RequestMapping(value = "question_doupdate", method = RequestMethod.POST)
	public String updateQuestionDetail(HttpServletRequest request) {
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
			LOGGER.info("修改成功--qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey);
			return "forward:/";
		}
		LOGGER.info("修改失败--qbkId:" + qbkId + " qbkTitle:" + qbkTitle + " qbkKey:" + qbkKey);
		return "forward:/";
	}
	
	@RequestMapping(value = "question_delete", method = RequestMethod.GET)
	public String deleteQuestionDetailById(@RequestParam("qbkId") String qbkId) {
		if (!StringUtils.isEmpty(qbkId)) {
			LOGGER.info("待删除的id为：" + qbkId);
			this.questionDetailRepository.deleteById(qbkId);
			return "forward:/";
		}
		return "forward:/";
	}
}
