package com.sonwalker.domain;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe 问题实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionDetail implements Serializable {
	
	private static final long serialVersionUID = 7988880194090660282L;
	@Id
	private String qbkId;
	private String qbkTitle;
	private String qbkKey;
	private String qbkAnswer;
	private String qbkDate; 
	private String qbkType;
	
}
