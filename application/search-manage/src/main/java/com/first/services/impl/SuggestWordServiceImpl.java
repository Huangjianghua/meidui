package com.first.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.IDao.SuggestWordMapper;
import com.first.page.PageView;
import com.first.pojo.SuggestWord;
import com.first.services.SuggestWordService;
import com.first.utility.Pinyin4jUtil;

@Service
public class SuggestWordServiceImpl implements SuggestWordService {
	
	@Autowired
	private SuggestWordMapper suggestWordMapper;

	@Override
	public List<SuggestWord> querySuggestWords(PageView pageView) throws Exception {
		return suggestWordMapper.querySuggestWords(pageView);
	}

	@Override
	public long querySuggestWordCount(String kw) throws Exception {
		return suggestWordMapper.querySuggestWordCount(kw);
	}

	@Override
	public SuggestWord querySuggestWordById(Integer suggestId) throws Exception {
		return suggestWordMapper.querySuggestWordById(suggestId);
	}

	@Override
	public SuggestWord querySuggestWordByKey(String key) throws Exception {
		List<SuggestWord> words = suggestWordMapper.querySuggestWordByKey(key);
		if (words == null || words.size() == 0) {
			return null;
		}
		return words.get(0);
	}

	@Override
	public List<SuggestWord> fuzzyQuerySuggestWord(String key) throws Exception {
		return suggestWordMapper.fuzzyQuerySuggestWord(key);
	}

	@Override
	public int addSuggestWord(SuggestWord suggestWord) throws Exception {
		return suggestWordMapper.addSuggestWord(getSuggestWord(suggestWord));
	}

	@Override
	public int updateSuggestWord(SuggestWord suggestWord) throws Exception {
		return suggestWordMapper.updateSuggestWord(getSuggestWord(suggestWord));
	}

	@Override
	public int deleteSuggestWordById(Integer suggestId) throws Exception {
		return suggestWordMapper.deleteSuggestWordById(suggestId);
	}

	private SuggestWord getSuggestWord(SuggestWord suggestWord) {
		String key = suggestWord.getKw();
		String pinyin = Pinyin4jUtil.getPinyin(key);
		String abbre = Pinyin4jUtil.getPinyinShort(key);
		suggestWord.setAbbre(abbre);
		suggestWord.setPinyin(pinyin);
		long updateTime = System.currentTimeMillis()/1000;
		suggestWord.setUpdateTime((int)updateTime);
		return suggestWord;
	}
}
