package com.meidui.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meidui.sms.entity.TemplateInfo;

@Mapper
public interface TemplateInfoMapper {

    int insert(TemplateInfo record);

    int insertSelective(TemplateInfo record);
    
    List<TemplateInfo> getTemplateInfoList();

}