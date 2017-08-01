package com.sonwalker.constant;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe 问题类型枚举
 */

public enum QuestionTypeEnum {
	
	THIRD_PART_PLATFORM("第三方平台"),
	L9E_ESALES_PLATFORM("19e前端接入"),
	GAOYANG_ESALES_PLATFORM("高阳前端接入");
	
	private String type;
	
	public String toString() {
		return this.type;
	}

	private QuestionTypeEnum(String type) {
		this.type = type;
	}
	
}
