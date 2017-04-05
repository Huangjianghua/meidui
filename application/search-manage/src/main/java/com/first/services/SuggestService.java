package com.first.services;

import java.util.List;

public interface SuggestService {

	/**
	 * 查询提示词
	 * @param keyword
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<String> querySuggest(String keyword, Integer limit) throws Exception;
}