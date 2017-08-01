package com.sonwalker.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sonwalker.domain.QuestionDetail;
import com.sonwalker.util.DateUtil;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe Dao实现类
 */

@Repository(value = "questionDetailRepository")
@Transactional
public class QuestionDetailRepositoryImpl implements QuestionDetailRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<QuestionDetail> findAll() {
		String sql = " select t.QBK_ID, t.QBK_TITLE, t.QBK_KEY, t.QBK_ANSWER, t.QBK_DATE, t.QBK_TYPE from QBK_QUESTION t order by t.QBK_DATE desc ";
		final List<QuestionDetail> questionDetails = new ArrayList<QuestionDetail>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				QuestionDetail questionDetail = QuestionDetail.builder()
						.qbkId(rs.getString("QBK_ID"))
						.qbkTitle(rs.getString("QBK_TITLE"))
						.qbkKey(rs.getString("QBK_KEY"))
						.qbkAnswer(rs.getString("QBK_ANSWER"))
						.qbkDate(DateUtil.parseDateToString(rs.getTimestamp("QBK_DATE")))
						.qbkType(rs.getString("QBK_TYPE"))
						.build();
				questionDetails.add(questionDetail);
			}
		});
		return questionDetails;
	}

	public QuestionDetail getDetailById(String id) {
		String sql = " select rowid, t.QBK_ID, t.QBK_TITLE, t.QBK_KEY, t.QBK_ANSWER, t.QBK_DATE, t.QBK_TYPE from QBK_QUESTION t where t.qbk_id=? ";
		final QuestionDetail questionDetail = new QuestionDetail();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				questionDetail.setQbkId(rs.getString("QBK_ID"));
				questionDetail.setQbkTitle(rs.getString("QBK_TITLE"));
				questionDetail.setQbkAnswer(rs.getString("QBK_ANSWER"));
				questionDetail.setQbkType(rs.getString("QBK_TYPE"));
				questionDetail.setQbkDate(DateUtil.parseDateToString(rs.getTimestamp("QBK_DATE")));
				questionDetail.setQbkKey(rs.getString("QBK_KEY"));
			}
		});
		return questionDetail;
	}

	public List<QuestionDetail> queryDetailsByLikeKey(String keyword) {
		String sql = " select t.QBK_ID, t.QBK_TITLE, t.QBK_KEY, t.QBK_ANSWER, t.QBK_DATE, t.QBK_TYPE from QBK_QUESTION t where t.qbk_key like '%" + keyword + "%' order by t.QBK_DATE desc ";
		final List<QuestionDetail> questionDetails = new ArrayList<QuestionDetail>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				QuestionDetail questionDetail = QuestionDetail.builder()
						.qbkId(rs.getString("QBK_ID"))
						.qbkTitle(rs.getString("QBK_TITLE"))
						.qbkKey(rs.getString("QBK_KEY"))
						.qbkAnswer(rs.getString("QBK_ANSWER"))
						.qbkDate(DateUtil.parseDateToString(rs.getTimestamp("QBK_DATE")))
						.qbkType(rs.getString("QBK_TYPE"))
						.build();
				questionDetails.add(questionDetail);
			}
		});
		return questionDetails;
	}

	public List<QuestionDetail> queryDetailsByType(String questionType) {
		String sql = " select t.QBK_ID, t.QBK_TITLE, t.QBK_KEY, t.QBK_ANSWER, t.QBK_DATE, t.QBK_TYPE from QBK_QUESTION t where t.QBK_TYPE= " + questionType + " order by t.QBK_DATE desc ";
		final List<QuestionDetail> questionDetails = new ArrayList<QuestionDetail>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				QuestionDetail questionDetail = QuestionDetail.builder()
						.qbkId(rs.getString("QBK_ID"))
						.qbkTitle(rs.getString("QBK_TITLE"))
						.qbkKey(rs.getString("QBK_KEY"))
						.qbkAnswer(rs.getString("QBK_ANSWER"))
						.qbkDate(DateUtil.parseDateToString(rs.getTimestamp("QBK_DATE")))
						.qbkType(rs.getString("QBK_TYPE"))
						.build();
				questionDetails.add(questionDetail);
			}
		});
		return questionDetails;
	}

	public int insertOneQuestion(final QuestionDetail questionDetail) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into QBK_QUESTION(QBK_ID, QBK_TITLE, QBK_KEY, QBK_ANSWER, QBK_DATE, QBK_TYPE) ");
		sql.append(" VALUES(?,?,?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, questionDetail.getQbkId());
				ps.setString(2, questionDetail.getQbkTitle());
				ps.setString(3, questionDetail.getQbkKey());
				ps.setString(4, questionDetail.getQbkAnswer());
				ps.setTimestamp(5, new Timestamp(DateUtil.parseStringToDate(questionDetail.getQbkDate()).getTime()));
				ps.setString(6, questionDetail.getQbkType());
			}
		});
	}

	public boolean updateOneQuestion(final QuestionDetail questionDetail, String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update QBK_QUESTION t set t.QBK_TITLE=?, t.QBK_KEY=?, t.QBK_ANSWER=?, t.QBK_TYPE=? where t.QBK_ID=?  ");
		int resultCount = jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, questionDetail.getQbkTitle());
				ps.setString(2, questionDetail.getQbkKey());
				ps.setString(3, questionDetail.getQbkAnswer());
				ps.setString(4, questionDetail.getQbkType());
				ps.setString(5, questionDetail.getQbkId());
			}
		});
		if (resultCount > 0) {
			return true;
		}
		return false;
	}

}
