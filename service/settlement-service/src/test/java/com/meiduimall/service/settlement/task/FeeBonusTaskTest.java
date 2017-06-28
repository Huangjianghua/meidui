package com.meiduimall.service.settlement.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.BaseTest;

public class FeeBonusTaskTest extends BaseTest {
	
	@Autowired
	private FeeBonusTask feeBonusTask;

	@Test
	public void testCalFeeBonusMorrow() throws Exception {
		feeBonusTask.calFeeBonusMorrow();
	}

	@Test
	public void testIssueFeeBonusMorrow() throws Exception {
		feeBonusTask.issueFeeBonusMorrow();
	}

}
