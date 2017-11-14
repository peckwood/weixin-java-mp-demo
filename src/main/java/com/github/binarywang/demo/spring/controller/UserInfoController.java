package com.github.binarywang.demo.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@RestController
@RequestMapping("/wechat/user-info")
public class UserInfoController {
	private WxMpService wxMpService;

	@GetMapping
	public Map<String, String> getUserInfo(@RequestParam(required = true) String code) throws WxErrorException {
		WxMpOAuth2AccessToken oauth2AccessToken = wxMpService.oauth2getAccessToken(code);
		WxMpUser userInfo = wxMpService.oauth2getUserInfo(oauth2AccessToken, "zh_CN");
		String openId = oauth2AccessToken.getOpenId();

		Map<String, String> map = new HashMap<>();
		map.put("openid", openId);
		map.put("nickname", userInfo.getNickname());
		map.put("country", userInfo.getCountry());
		map.put("province", userInfo.getProvince());
		map.put("city", userInfo.getCity());
		map.put("headimgurl", userInfo.getHeadImgUrl());
		return map;
	}
}
