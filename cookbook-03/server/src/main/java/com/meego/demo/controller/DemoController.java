package com.meego.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meego.demo.entity.Demo;
import com.meego.demo.service.DemoService;
import com.meego.demo.service.LoginService;
import com.meego.demo.utils.JsonUtils;
import com.meego.demo.vo.CreateByUuidVO;
import com.meego.demo.vo.ResponseVO;
import com.meego.demo.vo.WebhookCreateVO;
import com.meego.demo.vo.WebhookInterceptResponse;
import com.meego.demo.vo.WebhookInterceptVO;
import com.meego.demo.vo.WebhookListeningCreateVO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/demo")
public class DemoController {
	
	@Autowired
	private DemoService demoService;

	/**
	 * 模拟获取第三方业务数据接口
	 * @return
	 * @throws Exception
	 */
	@GetMapping("getDemoList")
	public Object getDemoList() throws Exception {
		log.info("getDemoList start");
		List<Demo> demos = new ArrayList<>();
		demos.add(new Demo().setId(1).setName("张三").setAge(23).setCity("北京"));
		demos.add(new Demo().setId(2).setName("李四").setAge(24).setCity("上海"));
		demos.add(new Demo().setId(3).setName("王五").setAge(25).setCity("广州"));
		demos.add(new Demo().setId(4).setName("赵六").setAge(26).setCity("深圳"));
		return demos;
	}
	
	@Data
	static class UpdateDescriptionRequest {
		/**
		 * code
		 */
		@NotBlank
		private String projectKey;
		private String workItemTypeKey; 
		private Long workItemID; 
		private String value;
	}
	
	/**
	 * 更新描述字段接口
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateDescription")
	public Object updateDescription(@RequestBody @Validated UpdateDescriptionRequest res) throws Exception {
		log.info("updateDescription start");
		demoService.updateDescriptionField(res.getProjectKey(), res.getWorkItemTypeKey(), res.getWorkItemID(), res.getValue());
		return "OK";
	}
	

	@Autowired
	private LoginService loginService;

	@Data
	static class LoginRequest {
		/**
		 * code
		 */
		@NotBlank
		private String code;
	}
	
	@Data
	@Accessors(chain = true)
	public static class LoginResponse {
		/**
		 * token 用于 header: authorization
		 */
		private String token;
		/**
		 * 过期时间(秒)
		 */
		private Integer expireTime;
	}
	
	/**
	 * 登录
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping("login")
	public ResponseVO<LoginResponse> login(@RequestBody @Validated LoginRequest res) throws Exception {
		LoginResponse loginResponse = loginService.login(res.getCode());
		return ResponseVO.generateOK(loginResponse);
	}
	
	/**
	 * 用于测试
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/webhook/test", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO<String> getConfig(@RequestBody String data) throws Exception {
		log.info(data);
		return ResponseVO.generateOK("test");
	}
	
	
	
	/**
	 * 根据UUID创建
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/create/uuid", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO<String> createByUuid(@RequestBody CreateByUuidVO data) throws Exception {
		log.info("uuid:{}, value:{}", data.getUuid(), data.getValue());
		demoService.createByUuid(data);
		return ResponseVO.generateOK();
	}
	
	
	/**
	 * 新建回调
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/webhook/create", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO<String> webhookCreate(@RequestBody WebhookCreateVO payload) throws Exception {
		log.info("create payload:{}", JsonUtils.writeValueAsString(payload));
		demoService.webhookCreate(payload);
		return ResponseVO.generateOK();
	}
	
	/**
	 * 根据workItemId读取控件值
	 * @param workItemId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/getValueByWorkitemid", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO<String> getValueByWorkItemId(Long workItemId) throws Exception {
		log.info("workItemId:{}", workItemId);
		String value = demoService.getValueByWorkItemId(workItemId);
		return ResponseVO.generateOK(value);
	}
	
	/**
	 * 拦截回调
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/webhook/intercept", produces =MediaType.APPLICATION_JSON_VALUE)
	public WebhookInterceptResponse webhookIntercept(@RequestBody WebhookInterceptVO payload) throws Exception {
		log.info("intercept payload:{}", JsonUtils.writeValueAsString(payload));
		return demoService.webhookIntercept(payload);
	}
	
	/**
	 * 监听回调
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/webhook/listening", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO<String> webhookListening(@RequestBody WebhookListeningCreateVO payload) throws Exception {
		log.info("listening payload:{}", JsonUtils.writeValueAsString(payload));
		demoService.webhookListeningCreate(payload);
		return ResponseVO.generateOK();
	}
	
}
