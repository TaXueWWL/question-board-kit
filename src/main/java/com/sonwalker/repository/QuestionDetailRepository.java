package com.sonwalker.repository;

import java.util.List;

import com.sonwalker.domain.QuestionDetail;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe Dao接口
 */

public interface QuestionDetailRepository  {
	// 查询所有
	public List<QuestionDetail> findAll();
	// 根据id查一个
	public QuestionDetail getDetailById(String id);
	// 根据关键词查一组
	public List<QuestionDetail> queryDetailsByLikeKey(String keyword);
	// 根据问题类别查一组
	public List<QuestionDetail> queryDetailsByType(String questionType);
	// 新增一个问题
	public int insertOneQuestion(QuestionDetail questionDetail);
	// 修改一个问题
	public boolean updateOneQuestion(QuestionDetail questionDetail, String id);
	// 删除一个问题
	public boolean deleteById(String id);
}
