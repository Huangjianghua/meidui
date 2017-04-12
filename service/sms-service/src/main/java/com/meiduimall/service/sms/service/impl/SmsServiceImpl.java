/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.service.impl;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.SmsServiceErrorInfoEnum;
import com.meiduimall.service.sms.SysConstant;
import com.meiduimall.service.sms.entity.SendSmsHistory;
import com.meiduimall.service.sms.entity.TemplateInfo;
import com.meiduimall.service.sms.mapper.SendSmsHistoryMapper;
import com.meiduimall.service.sms.model.message.CommonShortMessageModel;
import com.meiduimall.service.sms.service.SmsService;
import com.meiduimall.service.sms.util.Time;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 短信接口
 *
 * @author pc
 * @since 2017.01.05
 */
@Service
public class SmsServiceImpl implements SmsService {

  private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

  @Autowired
  private ZucpService zucpService;

  @Autowired
  private AliyunService aliyunService;

  //  @Autowired
//  private RedisService redisService;
  @Autowired
  private MessageChannelService messageChannelService;
  @Autowired
  private SendSmsHistoryMapper sendSmsHistoryMapper;


  /*
   * 根据模板ID获取短信模板
   * @param templateId
   * @param templateListJsonStr
   * @return
   */
  private TemplateInfo getTemplateByKey(String templateId, String templateListJsonStr) {
    TemplateInfo ti = new TemplateInfo();
    List<TemplateInfo> templateInfoList = JsonUtils.jsonToList(templateListJsonStr, TemplateInfo.class);
    for (TemplateInfo info : templateInfoList) {
      if (info.getTemplateKey().equals(templateId)) {

        BeanUtils.copyProperties( info,ti);

        break;
      }
    }
    return ti;
  }

  /**
   * 替换模板中的参数
   *
   * @param params
   * @param content
   * @return
   */
  private String replacesContent(String params, String content) {
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
    if (StringUtils.isNotEmpty(params)) {
      String[] replaces = params.split(",");
      int count = StringUtils.countMatches(content, "{");
      if (replaces.length < count) {
        logger.info("替换短信模板内容异常：%s", "替换内容与替换参数不匹配，replaces=" + replaces + ",count=" + count);
        throw new ServiceException(SmsServiceErrorInfoEnum.PARAM_ERROR);
//        return result;
      }
      for (int index = 0; index < replaces.length; index++) {
        //TODO 需要检查参数  add by simon
        content = content.replace("{" + index + "}", replaces[index]);
      }
      return content;
    }

    return content;
  }

  /**
   * 转换发送给阿里大于的替换模板参数格式，以json格式
   *
   * @param params
   * @return
   */
  private String aliDaYuParamsToJson(boolean isCode, String params) {
    Map<String, String> map = new HashMap<>();
    String[] paramsStr = params.split(",");
    for (int i = 0; i < paramsStr.length; i++) {
      if (!isCode && !StringUtils.isEmpty(paramsStr[i])) {
        map.put("V" + i, paramsStr[i]);
      } else {
        if (i == 0) {
          map.put("VerificationCode", paramsStr[i]);
        } else {
          map.put("V" + i, paramsStr[i]);
        }
      }
    }
    params = JsonUtils.beanToJson(map);

    return params;
  }

  private void send(String channel, String phone, String templateId, String params){

    String tempMsg = RedisUtils.get(phone + templateId + params);
    if (StringUtils.isNotEmpty(tempMsg)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.REPEATING);
    }

    //获取消息模板
    String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
    if (StringUtils.isEmpty(templateListJsonStr)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
    }

    TemplateInfo ti = getTemplateByKey(templateId, templateListJsonStr);

    if (StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
    }

    String content = ti.getTemplateContent();
    content = replacesContent(params, content);

    if (StringUtils.isNotEmpty(params)) {
      String aliParams = aliDaYuParamsToJson(false, params);
    }
    //设置发送历史记录值
//    SendSmsHistory ssh = setHistory(model);

  }
  /**
   * 发送短信
   *
   * @param model
   * @throws Exception
   */
  public void sendSmsMessage(CommonShortMessageModel model)  {
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");

//    String tempMsg = redisService.get(model.getPhones() + model.getTemplateId() + model.getParams()) == null ? "" : redisService.get(model.getPhones() + model.getTemplateId() + model.getParams()).toString();
    String tempMsg = RedisUtils.get(model.getPhones() + model.getTemplateId() + model.getParams());
    if (StringUtils.isNotEmpty(tempMsg)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.REPEATING);
//      return result;
    }

    //获取消息模板
    String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
    if (StringUtils.isEmpty(templateListJsonStr)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
//      return result;
    }

    TemplateInfo ti = getTemplateByKey(model.getTemplateId(), templateListJsonStr);

    if (StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
//      return result;
    }

    String content = ti.getTemplateContent();
    content = replacesContent(model.getParams(), content);

//    if (ResultBody.SUCCESS.equals(rb.getStatus_code()) && !StringUtil.isEmptyByString(String.valueOf(rb.getData()))) {
//      content = String.valueOf(rb.getData());
//    }

    String params = "";
    if (StringUtils.isNotEmpty(model.getParams())) {
      params = aliDaYuParamsToJson(false, model.getParams());
    }
    //设置发送历史记录值
    SendSmsHistory ssh = setHistory(model);

//      if (StringUtils.isEmpty(model.getSupplierId()) || "0".equals(model.getSupplierId())) {
        /**
         * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
         * 全部失败则返回失败信息
         */
        boolean flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
        logger.info("阿里大于发送短信结果（flag）：", flag);
        String res = "";
        if (!flag) {
          res = zucpService.send(model.getPhones(), content);
          logger.info("漫道发送短信结果（res）：", res);
          if (Long.parseLong(res) < 0) {
            throw new ServiceException(SmsServiceErrorInfoEnum.SMS_SEND_FAILUER);
//            return result;
          }
        }
        RedisUtils.setex(model.getPhones() + model.getTemplateId() + model.getParams(), Time.parseDuration(ti.getEffectiveTime()) , content);

        ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
        ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
//    try {
        sendSmsHistoryMapper.insert(ssh);

//        return result;
//      } else {
//
//        ssh.setChannelId(model.getSupplierId());
//        boolean flag = false;
//        String res = "-1";
//        if (SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(model.getSupplierId())) {
//          flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
//          Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
//          if (!flag) {
//            result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
//            return result;
//          }
//        } else if (SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(model.getSupplierId())) {
//          res = zucpService.send(model.getPhones(), content);
//          Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
//          if (Long.parseLong(res) < 0) {
//            result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
//            return result;
//          }
//        } else {
//          result = new ResultBody(ResultBody.FAILED, "短信渠道错误！");
//          return result;
//        }
//        redisService.set(model.getPhones() + model.getTemplateId() + model.getParams(), content, ti.getEffectiveTime());
//
//        ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
//        ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
//        sendSmsHistoryMapper.insert(ssh);
//
//      }

//    } catch (Exception e) {
//      logger.error("发送普通短信时服务发生异常", e);
//
//      ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
//      ssh.setResultMsg("发送普通短信时服务发生异常：" + e.toString());
//      sendSmsHistoryMapper.insert(ssh);
//    }
//    return result;

  }

//  @Override
//  public ResultBody validSmsMessageParams(CommonShortMessageModel model) {
//    //CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
//    if (null != model) {
////			if(StringUtil.isEmptyByString(model.getClientID())){
////				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
////				return result;
////			}
//      if (StringUtil.isEmptyByString(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
//        return result;
//      }
//      if (!StringUtil.isPhoneToRegex(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
//        return result;
//      }
//      if (StringUtil.isEmptyByString(model.getTemplateId())) {
//        result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
//        return result;
//      }
//      if (StringUtil.isEmptyByString(model.getParams())) {
//        result = new ResultBody(ResultBody.FAILED, "请求替换模板参数不能为空");
//        return result;
//      }
//      result.setData(model);
//    }
//    return result;
//  }
//
//  @Override
//  public ResultBody validVerificationCodeParams(CommonShortMessageModel model) {
//    //CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
//    if (null != model) {
////			if(StringUtil.isEmptyByString(model.getClientID())){
////				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
////				return result;
////			}
//      if (StringUtil.isEmptyByString(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
//        return result;
//      }
//      if (!StringUtil.isPhoneToRegex(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
//        return result;
//      }
//      if (StringUtil.isEmptyByString(model.getTemplateId())) {
//        result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
//        return result;
//      }
//      result.setData(model);
//    }
//    return result;
//  }
//
//  @Override
//  public ResultBody validCheckVerificationCodeParams(CommonShortMessageModel model) {
//    //CommonShortMessageModel model = JsonHelper.jsonToBean(biz_params, CommonShortMessageModel.class);
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
//    if (null != model) {
////			if(StringUtil.isEmptyByString(model.getClientID())){
////				result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
////				return result;
////			}
//      if (StringUtil.isEmptyByString(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码不能为空");
//        return result;
//      }
//      if (!StringUtil.isPhoneToRegex(model.getPhones())) {
//        result = new ResultBody(ResultBody.FAILED, "手机号码格式错误");
//        return result;
//      }
//      if (StringUtil.isEmptyByString(model.getTemplateId())) {
//        result = new ResultBody(ResultBody.FAILED, "短信模板编号不能为空");
//        return result;
//      }
//      if (StringUtil.isEmptyByString(model.getVerificationCode())) {
//        result = new ResultBody(ResultBody.FAILED, "短信验证码不能为空");
//        return result;
//      }
//      if (!StringUtil.isNumeric(model.getVerificationCode())) {
//        result = new ResultBody(ResultBody.FAILED, "短信验证码必须是纯数字");
//        return result;
//      }
//      result.setData(model);
//    }
//    return result;
//  }

  @Override
  public String sendSmsVerificationCode(CommonShortMessageModel model)  {
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
    String result = "";

    String tempMsg = RedisUtils.get(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());

    if (StringUtils.isNotEmpty(tempMsg)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.REPEATING);
//      return result;
    }
    //生成6位随机数
    result = String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6);
    logger.info("发送短信生成的验证码为：", result);

//    String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
//    if (StringUtil.isEmptyByString(templateListJsonStr)) {
//      result = new ResultBody(ResultBody.FAILED, "获取短信模板列表失败");
//      return result;
//    }


    //获取消息模板
    String templateListJsonStr = messageChannelService.getTemplateList(SysConstant.MESSAGE_TEMPLATE_KEY);
    if (StringUtils.isEmpty(templateListJsonStr)) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
//      return result;
    }

    //确定发送内容
    TemplateInfo ti = getTemplateByKey(model.getTemplateId(), templateListJsonStr);

    if (StringUtils.isEmpty(ti.getTemplateKey()) || StringUtils.isEmpty(ti.getTemplateContent())) {
      throw new ServiceException(SmsServiceErrorInfoEnum.NOT_FOUND_TEMPLATES);
//      return result;
    }

    //设置发送历史记录值
    SendSmsHistory ssh = setHistory(model);

    String content = ti.getTemplateContent();
    content = content.replace("{VerificationCode}", result);

    if (StringUtils.isNotEmpty(model.getParams())) {
      content = replacesContent(model.getParams(), content);
    }

    String params = "";
    if (StringUtils.isNotEmpty(model.getParams())) {
      params = aliDaYuParamsToJson(true, result + "," + model.getParams());
    }
    boolean flag = false;
    String res = "-1";

//    try {

//      if (StringUtil.isEmptyByString(model.getSupplierId())) {
        /**
         * 首先阿里云发送发送短信，如果发送失败则调用漫道发送 </br>
         * 全部失败则返回失败信息
         */
        flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
        logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));

        if (!flag) {
          res = zucpService.send(model.getPhones(), content);
          logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
          if (Long.parseLong(res) < 0) {
            throw new ServiceException(SmsServiceErrorInfoEnum.SMS_SEND_FAILUER);
//            return result;
          }
        }
        int expire = Time.parseDuration(model.getTimeout() == null ? ti.getEffectiveTime() : model.getTimeout());
        RedisUtils.setex(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId(), expire, result);


        ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
        ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
        sendSmsHistoryMapper.insert(ssh);

//        return result;
//      } else {
//
//        if (SysConstant.MESSAGE_TEMPLATE_ALI_KEY.equals(model.getSupplierId())) {
//          flag = aliyunService.Send(model.getPhones(), ti.getExternalTemplateNo(), params);
//          Logger.info("阿里大于发送短信结果（flag）：%s", String.valueOf(flag));
//          if (!flag) {
//            result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
//            return result;
//          }
//        } else if (SysConstant.MESSAGE_TEMPLATE_MANDAO_KEY.equals(model.getSupplierId())) {
//          res = zucpService.send(model.getPhones(), content);
//          Logger.info("漫道发送短信结果（res）：%s", String.valueOf(res));
//          if (Long.parseLong(res) < 0) {
//            result = new ResultBody(ResultBody.FAILED, "发送短信失败！");
//            return result;
//          }
//        } else {
//          result = new ResultBody(ResultBody.FAILED, "短信渠道错误！");
//          return result;
//        }
//
//        redisService.set(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId(), randomCode, model.getTimeout() == null ? ti.getEffectiveTime() : model.getTimeout());
//
//        ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
//        ssh.setResultMsg("ali result, flag:" + String.valueOf(flag) + ";mandao result, res:" + String.valueOf(res));
//        sendSmsHistoryMapper.insert(ssh);
//      }

//    } catch (Exception e) {
//      Logger.error("发送短信验证码时服务发生异常", e);
//
//      ssh.setRequestParams(model.getPhones() + "ali send param:" + ti.getExternalTemplateNo() + params + ";mandao send param:" + content);
//      ssh.setResultMsg("发送短信验证码时服务发生异常：" + e.toString());
//      sendSmsHistoryMapper.insert(ssh);
//    }
    return result;
  }

  @Override
  public int checkSmsVerificationCode(CommonShortMessageModel model)  {
//    ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
    String tempVerificationCode = RedisUtils.get(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());

    //设置发送历史记录值
//    SendSmsHistory ssh = setHistory(model);

//    ssh.setRequestParams("用户校验验证码发送参数：" + ReflectionToStringBuilder.toString(model));

    if (StringUtils.isEmpty(tempVerificationCode)) {
//      ssh.setResultMsg("验证返回结果：" + ReflectionToStringBuilder.toString(result));
//      sendSmsHistoryMapper.insert(ssh);
      //过期
      return -2;
    }

    if (!StringUtils.equalsIgnoreCase(model.getVerificationCode().trim(),tempVerificationCode)) {
//      ssh.setResultMsg("验证返回结果：" + ToStringBuilder.reflectionToString(result));
//      sendSmsHistoryMapper.insert(ssh);
      //不匹配
      return -1;
    }
//    ssh.setResultMsg("验证返回结果：" + ToStringBuilder.reflectionToString(result));
//    sendSmsHistoryMapper.insert(ssh);

    RedisUtils.del(model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId());

    //验证通过
    return  0;
  }

  private SendSmsHistory setHistory(CommonShortMessageModel model) {
    SendSmsHistory ssh = new SendSmsHistory();
    //ssh.setChannelId(model.getSupplierId() == null?"":model.getSupplierId());
    ssh.setId(UUID.randomUUID().toString());
//		ssh.setClientId(model.getClientID());
    ssh.setTemplateKey(model.getTemplateId());
    ssh.setCreateDate(new Date());
    ssh.setCreater(model.getPhones());
    ssh.setPhone(model.getPhones());
    ssh.setRemark(model.getParams());

    return ssh;
  }

}