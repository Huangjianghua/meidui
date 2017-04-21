package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.mapper.ScannerMapper;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.Scanner;
import com.meiduimall.application.search.manage.services.ScannerService;

@Service
public class ScannerServiceImpl implements ScannerService {

	@Autowired
	private ScannerMapper scannerMapper;
	
	@Override
	public List<Scanner> queryScanners(PageView pageView) {
		return scannerMapper.queryScanners(pageView);
	}

	@Override
	public long queryScannerCount()  {
		return scannerMapper.queryScannerCount();
	}

	@Override
	public Scanner queryScannerById(Integer id)  {
		return scannerMapper.queryScannerById(id);
	}

	@Override
	public int deleteScanned(List<Integer> ids) {
		return scannerMapper.deleteScanned(ids);
	}

}
