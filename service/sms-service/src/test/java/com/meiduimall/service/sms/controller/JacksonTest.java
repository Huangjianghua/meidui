package com.meiduimall.service.sms.controller;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.meiduimall.core.util.JsonUtils;

public class JacksonTest {

	@Test
	public void test1() throws Exception {

		ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
		rootNode.set("touser", new TextNode("OPENID"));
		rootNode.set("template_id", new TextNode("template_id"));
		rootNode.set("url", new TextNode("url"));

		ObjectNode miniprogram = JsonUtils.getInstance().createObjectNode();
		miniprogram.set("appid", new TextNode("appid"));
		miniprogram.set("pagepath", new TextNode("index?foo=bar"));
		rootNode.set("miniprogram", miniprogram);

		ObjectNode data = JsonUtils.getInstance().createObjectNode();

		ObjectNode first = JsonUtils.getInstance().createObjectNode();
		first.set("value", new TextNode("恭喜你购买成功！"));
		first.set("color", new TextNode("#173177"));
		data.set("first.DATA", first);

		ObjectNode keyword1 = JsonUtils.getInstance().createObjectNode();
		keyword1.set("value", new TextNode("巧克力"));
		keyword1.set("color", new TextNode("#173177"));
		data.set("keyword1.DATA", keyword1);

		ObjectNode keyword2 = JsonUtils.getInstance().createObjectNode();
		keyword2.set("value", new TextNode("巧克力"));
		keyword2.set("color", new TextNode("#173177"));
		data.set("keyword2.DATA", keyword2);

		ObjectNode keyword3 = JsonUtils.getInstance().createObjectNode();
		keyword3.set("value", new TextNode("巧克力"));
		keyword3.set("color", new TextNode("#173177"));
		data.set("keyword3.DATA", keyword3);

		ObjectNode keyword4 = JsonUtils.getInstance().createObjectNode();
		keyword4.set("value", new TextNode("巧克力"));
		keyword4.set("color", new TextNode("#173177"));
		data.set("keyword4.DATA", keyword4);

		ObjectNode keyword5 = JsonUtils.getInstance().createObjectNode();
		keyword5.set("value", new TextNode("巧克力"));
		keyword5.set("color", new TextNode("#173177"));
		data.set("keyword5.DATA", keyword5);

		ObjectNode remark = JsonUtils.getInstance().createObjectNode();
		remark.set("value", new TextNode("巧克力"));
		remark.set("color", new TextNode("#173177"));
		data.set("remark.DATA", remark);

		rootNode.set("data", data);

		System.out.println(rootNode.toString());
	}
}
