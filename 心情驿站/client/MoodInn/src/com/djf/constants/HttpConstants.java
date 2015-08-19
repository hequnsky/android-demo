package com.djf.constants;

public class HttpConstants {

	
	public final static String HTTP_HEAD="http://";
	
	//内网
	public final static String HTTP_IP="192.168.61.109:8888";
	
	//外网
	//public final static String HTTP_IP="10.72.130.51:80/";
	
	public final static String HTTP_CONTEXT="/NoteforSSM/";
	
	public final static String HTTP_REQUEST=HTTP_HEAD+HTTP_IP+HTTP_CONTEXT;
	
	/**
	 * 用户协议
	 */
	public static String HTTP_AGREEMENT=HTTP_REQUEST+"agreement.jsp";
	
	/**
	 * 用户注册
	 */
	public static String HTTP_REGISTER=HTTP_REQUEST+"user/userRegist.do";
	
	/**
	 * 用户登录
	 */
	public static String HTTP_LOGIN=HTTP_REQUEST+"user/userLogin.do";
	
	/**
	 * 修改用户图像
	 */
	public static String HTTP_UPDATE_USERICON=HTTP_REQUEST+"user/updateUserIcon.do";
	
	/**
	 * 提交建议
	 */
	public static String HTTP_SUMBIT_FEEDBACK=HTTP_REQUEST+"feedback/add.do";
	
	/**
	 * 写心情 支持图片
	 */
	public static String HTTP_WRITE_MOOD=HTTP_REQUEST+"diary/addDiary.do";
	
	/**
	 * 写心情不支持图片
	 */
	public static String HTTP_WRITE_MOOD_NO_IMAGE=HTTP_REQUEST+"diary/addDiaryNoImage.do";
	
	/**
	 * 获取当天所有公众心情
	 */
	public static String HTTP_ALL_DIARYS=HTTP_REQUEST+"diary/allDiarys.do";
	
	/**
	 * 根据id查看指定id相关心情
	 */
	public static String HTTP_ALL_DIARY_FORUSERID=HTTP_REQUEST+"diary/allMeDiarys.do";
	
	
	/**
	 * 对心情发表评论
	 */
	public static String HTTP_PINGLUN=HTTP_REQUEST+"comment/add.do";
	
	/**
	 * 根据ID 获取个人资料
	 */
	public static String HTTP_GET_USERINFO=HTTP_REQUEST+"user/getUserInfoId.do";
	
	/**
	 * 私信
	 */
	public static String HTTP_SEND_MESSAGE=HTTP_REQUEST+"message/add.do";
	
	/**
	 * 查询私信
	 */
	public static String HTTP_QUERY_AllMESSAGE=HTTP_REQUEST+"message/queryAllMessage.do";
	
	/**
	 * 版本更新
	 */
	public static String HTTP_VERSION_UPDATE=HTTP_REQUEST+"version/update.do";
	
	/**
	 * 拉黑
	 */
	public static String HTTP_PULL_THE_BLACK=HTTP_REQUEST+"message/addBlackList.do";
}
