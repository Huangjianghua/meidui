package com.meiduimall.service.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.search.entity.Scanner;
import com.meiduimall.service.search.mapper.ScannerMapper;
import com.meiduimall.service.search.page.PageView;
import com.meiduimall.service.search.service.ScannerService;

@Service
public class ScannerServiceImpl implements ScannerService {

	@Autowired
	private ScannerMapper scannerMapper;

	@Override
	public List<Scanner> queryScanners(PageView pageView) throws Exception {
		return scannerMapper.queryScanners(pageView);
	}

	@Override
	public long queryScannerCount() throws Exception {
		return scannerMapper.queryScannerCount();
	}

	@Override
	public Scanner queryScannerById(Integer id) throws Exception {
		return scannerMapper.queryScannerById(id);
	}

	@Override
	public int deleteScanned(List<Integer> ids) {
		return scannerMapper.deleteScanned(ids);
	}

}
