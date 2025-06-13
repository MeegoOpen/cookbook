package com.meego.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lark.project.service.field.model.FieldValuePair;
import com.meego.demo.utils.JsonUtils;
import com.meego.demo.vo.CreateByUuidVO;
import com.meego.demo.vo.FieldInfo;
import com.meego.demo.vo.ResponseVO;
import com.meego.demo.vo.WebhookCreateVO;
import com.meego.demo.vo.WebhookInterceptResponse;
import com.meego.demo.vo.WebhookInterceptResponse.InterceptOptions;
import com.meego.demo.vo.WebhookInterceptResponse.Msg;
import com.meego.demo.vo.WebhookInterceptVO;
import com.meego.demo.vo.WebhookListeningCreateVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoService {

	@Autowired
	private LarkApiService larkApiService;

	// 本地缓存
	private LoadingCache<String, CreateByUuidVO> cacheByUuid = CacheBuilder.newBuilder()
			// 设置写入后的过期时间为2小时-3分钟
			.expireAfterWrite(7200 - 180, TimeUnit.SECONDS)
			// 设置并发级别为10，即同时可以有10个线程并发写入缓存
			.concurrencyLevel(10)
			// 设置缓存容量最大为100个键值对
			.maximumSize(100).removalListener(l -> log.info("value removal:{}", l))
			// 构建缓存
			.build(new CacheLoader<String, CreateByUuidVO>() {
				// 定义如何加载数据到缓存
				@Override
				public CreateByUuidVO load(String key) throws Exception {
					return new CreateByUuidVO(); // 
				}
			});
	// 本地缓存
	private LoadingCache<Long, CreateByUuidVO> cacheByWorkItemId = CacheBuilder.newBuilder()
			// 设置写入后的过期时间为2小时-3分钟
			.expireAfterWrite(7200 - 180, TimeUnit.SECONDS)
			// 设置并发级别为10，即同时可以有10个线程并发写入缓存
			.concurrencyLevel(10)
			// 设置缓存容量最大为100个键值对
			.maximumSize(100).removalListener(l -> log.info("value removal:{}", l))
			// 构建缓存
			.build(new CacheLoader<Long, CreateByUuidVO>() {
				// 定义如何加载数据到缓存
				@Override
				public CreateByUuidVO load(Long key) throws Exception {
					return  new CreateByUuidVO();// 
				}
			});

	// 更新描述字段
	public void updateDescriptionField(String projectKey, String workItemType, Long workItemID, String value)
			throws Exception {
		List<FieldValuePair> updateFields = new ArrayList<>();
		FieldValuePair fieldValuePair = new FieldValuePair();
		fieldValuePair.setFieldKey("description");
		fieldValuePair.setFieldValue("描述通过OpenAPI设置值:" + value + "; 时间: + " + new Date());
		updateFields.add(fieldValuePair);
		// 调用OpenAPI接口
		larkApiService.updateWorkItemField(projectKey, workItemType, workItemID, updateFields);
	}

	// 根据UUID创建
	public void createByUuid(CreateByUuidVO data) throws Exception {
		cacheByUuid.put(data.getUuid(), data);
	}

	// 新建回调
	public ResponseVO<String> webhookCreate(WebhookCreateVO payload) throws Exception {
		Long workItemId = payload.getData().getWorkItemInfo().get(0).getWorkItemId();
		List<FieldInfo> fieldInfos = payload.getData().getFieldInfo();
		if (fieldInfos.size() > 0) {
			String afterFieldValue = fieldInfos.get(0).getAfterFieldValue();
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = JsonUtils.readValueAsString(afterFieldValue, HashMap.class);
			String uuid = map.get("__system_control_uuid");
			CreateByUuidVO createByUuidVO = cacheByUuid.get(uuid);
			createByUuidVO.setWorkItemId(workItemId);
			cacheByWorkItemId.put(workItemId, createByUuidVO);
		}
		return ResponseVO.generateOK();
	}

	// 根据WorkItemId得到值
	public String getValueByWorkItemId(Long workItemId) throws Exception {
		CreateByUuidVO createByUuidVO = cacheByWorkItemId.get(workItemId);
		return createByUuidVO.getValue();
	}
	
	// 拦截回调
	public WebhookInterceptResponse webhookIntercept(@RequestBody WebhookInterceptVO payload) throws Exception {
		WebhookInterceptResponse response = new WebhookInterceptResponse();
		if(payload.getData().getWorkItemInfo().getFirst().getWorkItemName().equals("abc")) {
			response.setNeedIntercept(true);
			Msg msg = new Msg();
			msg.setZh("名称不能为abc");
			response.setMsg(msg);
			response.setCustomData("这里是自定义数据, 可以传json");
			InterceptOptions interceptOptions = new InterceptOptions();
			interceptOptions.setDisableShowPlugin(true); // 是否展示插件拦截弹窗，true为不展示拦截弹窗。
			response.setInterceptOptions(interceptOptions);
		}
		for (FieldInfo fieldInfo : payload.getData().getFieldInfo()) {
			if (fieldInfo.getFieldKey().contains("control_ggeinf")) {
				String afterFieldValue = fieldInfo.getAfterFieldValue();
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = JsonUtils.readValueAsString(afterFieldValue, HashMap.class);
				String uuid = map.get("__system_control_uuid");
				CreateByUuidVO createByUuidVO = cacheByUuid.get(uuid);
				if (createByUuidVO.getUuid() == null) { // 判断是否有uuid
					response.setNeedIntercept(true);
					Msg msg = new Msg();
					msg.setZh("控件值必填");
					response.setMsg(msg);
					response.setCustomData("这里是自定义数据, 可以传json");
					InterceptOptions interceptOptions = new InterceptOptions();
					interceptOptions.setDisableShowPlugin(true); // 是否展示插件拦截弹窗，true为不展示拦截弹窗。
					response.setInterceptOptions(interceptOptions);
				}
			}
		}
		
		return response;
	}

	// 监听回调
	public void webhookListeningCreate(WebhookListeningCreateVO payload) throws Exception {
		String projectKey = payload.getData().getProjectKey();
		String workItemTypeKey = payload.getData().getWorkItemTypeKey();
		Long workItemID = payload.getData().getWorkItemInfo().getFirst().getWorkItemId();
		String name = payload.getData().getWorkItemInfo().getFirst().getWorkItemName();
		CreateByUuidVO createByUuidVO = cacheByWorkItemId.get(workItemID);
		updateDescriptionField(projectKey, workItemTypeKey, workItemID, "名称的值为" + name + "控件值为" + createByUuidVO.getValue());
	}
}
