package com.github.binarywang.demo.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

/**
 * <pre>
 *  注意：此contorller 实现WxMpMenuService接口，仅是为了演示如何调用所有菜单相关操作接口，
 *      实际项目中无需这样，根据自己需要添加对应接口即可
 * </pre>
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController implements WxMpMenuService {

	@Autowired
	private WxMpService wxService;

	/**
	 * <pre>
	 * 自定义菜单创建接口
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
	 * 如果要创建个性化菜单，请设置matchrule属性
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 * </pre>
	 *
	 * @param menu
	 * @return 如果是个性化菜单，则返回menuid，否则返回null
	 */
	@Override
	@PostMapping("/create")
	public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
		return this.wxService.getMenuService().menuCreate(menu);
	}

	@GetMapping("/create")
	public String menuCreateSample() throws WxErrorException {
		List<WxMenuButton> x5Meuns = new ArrayList<WxMenuButton>();

		// 大按钮1
		WxMenuButton menuButton1 = new WxMenuButton();
		menuButton1.setName("精选•攻略");
		menuButton1.setType(WxConsts.BUTTON_CLICK);

		// 大按钮下的小按钮0
		WxMenuButton subButton1_0 = new WxMenuButton();
		subButton1_0.setName("家装指南");
		subButton1_0.setType(WxConsts.BUTTON_VIEW);
		subButton1_0.setUrl(
				"http://mp.weixin.qq.com/mp/homepage?__biz=MzIyMTU3Mzg3Ng==&hid=4&sn=4119ae078c29c2f7dbe911ece3e2596c#wechat_redirectece3e2596c%23wechat_redirect");

		// 大按钮下的小按钮1
		WxMenuButton subButton1_1 = new WxMenuButton();
		subButton1_1.setName("涂装焕新");
		subButton1_1.setType(WxConsts.BUTTON_VIEW);
		subButton1_1.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxService.getWxMpConfigStorage().getAppId()
						+ "&redirect_uri=http%3a%2f%2fkkzhuang.com%2fKKZWX%2findex.html%3fpagemap%3dtushua&"
						+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		// 大按钮下的小按钮2
		WxMenuButton subButton1_2 = new WxMenuButton();
		subButton1_2.setName("厨房改造");
		subButton1_2.setType(WxConsts.BUTTON_VIEW);
		subButton1_2.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxService.getWxMpConfigStorage().getAppId()
						+ "&redirect_uri=http%3a%2f%2fkkzhuang.com%2fKKZWX%2findex.html%3fpagemap%3dchufang&"
						+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		// 大按钮下的小按钮3
		WxMenuButton subButton1_3 = new WxMenuButton();
		subButton1_3.setName("浴室改造");
		subButton1_3.setType(WxConsts.BUTTON_VIEW);
		subButton1_3.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxService.getWxMpConfigStorage().getAppId()
						+ "&redirect_uri=http%3a%2f%2fkkzhuang.com%2fKKZWX%2findex.html%3fpagemap%3dweishj&"
						+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		List<WxMenuButton> menuButton1Subbuttons = new ArrayList<WxMenuButton>();
		menuButton1Subbuttons.add(subButton1_0);
		menuButton1Subbuttons.add(subButton1_1);
		menuButton1Subbuttons.add(subButton1_2);
		menuButton1Subbuttons.add(subButton1_3);
		menuButton1.setSubButtons(menuButton1Subbuttons);

		x5Meuns.add(menuButton1);

		// 大按钮2
		WxMenuButton menuButton2 = new WxMenuButton();
		menuButton2.setName("预约");
		menuButton2.setType(WxConsts.BUTTON_VIEW);

		String serverContext = null;
		try {
			serverContext = "tg.kkzhuang.com";
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			menuButton2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ wxService.getWxMpConfigStorage().getAppId() + "&redirect_uri=http%3A%2F%2F" + serverContext
					+ "%2FKKZWX%2Findex.html%3Fpagemap%3Dappoint&"
					+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(menuButton2.getUrl());
		x5Meuns.add(menuButton2);

		// 大按钮3
		WxMenuButton menuButton4 = new WxMenuButton();
		menuButton4.setName("个人中心");
		menuButton4.setType(WxConsts.BUTTON_VIEW);
		try {
			menuButton4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ wxService.getWxMpConfigStorage().getAppId() + "&redirect_uri=http%3A%2F%2F" + serverContext
					+ "%2FKKZWX%2Findex.html%3Fpagemap%3Dcenter&"
					+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		x5Meuns.add(menuButton4);

		WxMenu menu = new WxMenu();
		menu.setButtons(x5Meuns);
		try {
			wxService.getMenuService().menuDelete();
			wxService.getMenuService().menuCreate(menu);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return this.wxService.getMenuService().menuCreate(menu);
	}

	/**
	 * <pre>
	 * 自定义菜单创建接口
	 * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
	 * 如果要创建个性化菜单，请设置matchrule属性
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 * </pre>
	 *
	 * @param json
	 * @return 如果是个性化菜单，则返回menuid，否则返回null
	 */
	@Override
	@GetMapping("/create/{json}")
	public String menuCreate(@PathVariable String json) throws WxErrorException {
		return this.wxService.getMenuService().menuCreate(json);
	}

	/**
	 * <pre>
	 * 自定义菜单删除接口
	 * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
	 * </pre>
	 */
	@Override
	@GetMapping("/delete")
	public void menuDelete() throws WxErrorException {
		this.wxService.getMenuService().menuDelete();
	}

	/**
	 * <pre>
	 * 删除个性化菜单接口
	 * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 * </pre>
	 *
	 * @param menuId
	 *            个性化菜单的menuid
	 */
	@Override
	@GetMapping("/delete/{menuId}")
	public void menuDelete(@PathVariable String menuId) throws WxErrorException {
		this.wxService.getMenuService().menuDelete(menuId);
	}

	/**
	 * <pre>
	 * 自定义菜单查询接口
	 * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
	 * </pre>
	 */
	@Override
	@GetMapping("/get")
	public WxMpMenu menuGet() throws WxErrorException {
		return this.wxService.getMenuService().menuGet();
	}

	/**
	 * <pre>
	 * 测试个性化菜单匹配结果
	 * 详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
	 * </pre>
	 *
	 * @param userid
	 *            可以是粉丝的OpenID，也可以是粉丝的微信号。
	 */
	@Override
	@GetMapping("/menuTryMatch/{userid}")
	public WxMenu menuTryMatch(@PathVariable String userid) throws WxErrorException {
		return this.wxService.getMenuService().menuTryMatch(userid);
	}

	/**
	 * <pre>
	 * 获取自定义菜单配置接口
	 * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
	 * 请注意：
	 * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
	 * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
	 * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
	 * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
	 * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
	 *  接口调用请求说明:
	 * http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
	 * </pre>
	 */
	@Override
	@GetMapping("/getSelfMenuInfo")
	public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
		return this.wxService.getMenuService().getSelfMenuInfo();
	}
}
