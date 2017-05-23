package com.tjl.yangxixi.params;

/**
 * API
 *
 * @author zh
 *
 */
public class ConstantsUrl {
	private static final String BASE_URL = "http://139.196.22.252:8081/WS/";

	//登录
	private static final String lOGIN_URL = setUrl("LoginService.asmx/Login");
	//经理当天任务统计
	private static final String SALESTATISTICS_URL=setUrl("SaleClueServiceService.asmx/getStatistics");
	//销售经理
	private static final String GETREPORTINFOLIST=setUrl("SaleClueServiceService.asmx/GetReportInfoList");
	//今日线索总数
	private static final String GETTODAYTOTALTASK=setUrl("SaleClueServiceService.asmx/getTodayTotalTask");
	//销售经理线索跟进中列表
	private static final String GETFOLLMANCLUESALE_URL = setUrl("SaleClueServiceService.asmx/GetFollManClueSale");
	//今日任务跟进中
	private static final String GETFOLLMANCLUESALE=setUrl("SaleClueServiceService.asmx/GetFollManClueSale");
	//今日任务搜索框
	private static final String GETBLURCHURBYNAME_URL = setUrl("SaleClueServiceService.asmx/getBlurClueByName");
	//回库任务
	private static final String GETBACKTASKLIST=setUrl("SaleClueServiceService.asmx/getBackTaskList");
	//获取员工总数和线索总数
	private static final String GETTASKEMPCOUNT=setUrl("SaleClueServiceService.asmx/getTaskEmpCunt");
	//获取员工回库总数和线索总数
	private static final String GETBACKTASKEMPCOUNT=setUrl("SaleClueServiceService.asmx/getBackTaskEmpCunt");
	//平均分配
	private static final String GETAVGASSIGSTORE=setUrl("SaleClueServiceService.asmx/GetAvgAssigStore");
	//平均分配回库任务
	private static final String GETAVGBACKASSIGSTORE=setUrl("SaleClueServiceService.asmx/GetAvgBackAssigStore");
	//门店顾问列表
	private static final String GETSTOREEMPLOYEELIST = setUrl("SaleClueServiceService.asmx/GetStoreEmployeeList");
	//任务自主分配
	private static final String GETASSIGSTORE = setUrl("SaleClueServiceService.asmx/GetAssigStore");
	//外勤列表（未处理、未通过、外勤签到）
	private static final String GETPROCESSEDSTATE =setUrl("ApplicationService.asmx/getSaleApplicationListByState");
	//外勤申请通过/驳回操作
	private static final String MAKEAPPLICATION =setUrl("ApplicationService.asmx/makeApplication");
	//外勤详细信息
	private static final String GETAPPLICATIONINFO =setUrl("ApplicationService.asmx/getApplicationInfo");
	//选中线索平均分配
	private static final String GETAVGBID = setUrl("SaleClueServiceService.asmx/GetAvgBId");
	//获取今日消息数量
	private static final String GETMESSAGERNUMBER =setUrl("SaleClueServiceService.asmx/getMessager");
	//获取顾问今日消息数量
	private static final String GETMESSAGERNUMBERGW =setUrl("SaleTaskDetailService.asmx/getSaleMessager");


	//我的任务统计
	private static final String MY_GETSALESTATISTICS=setUrl("SaleTaskDetailService.asmx/getSaleStatistics");
	//销售任务列表
	private static final String GETUSERTASKLIST=setUrl("SaleTaskDetailService.asmx/GetUserTaskList");
	//销售今日任务列表
	private static final String GETTODAYTASK=setUrl("SaleTaskDetailService.asmx/getTodayTask");
	//销售顾问跟进中和未跟进任务
	private static final String GETCLUELISTBYSTATE=setUrl("SaleTaskDetailService.asmx/getClueListByState");
	//成单
	public static final String GETCLUE = setUrl("SaleTaskDetailService.asmx/GetClue");
	//拨打电话记录
	public static final String CALL_MSG_URL = setUrl("SaleTaskDetailService.asmx/CallRecording");
	//日历集合
	public static final String GETTASKBOOKDATE_URL = setUrl("SaleTaskDetailService.asmx/getTaskBookDateByUser");
	//计划列表
	public static final String GETTASKBOOKLIST_URL = setUrl("SaleTaskDetailService.asmx/GetTaskBookListByDate");
	//反馈计划
	public static final String BOOKCALLTIME=setUrl("SaleTaskDetailService.asmx/BookCallTime");
	//外勤申请
	private static final String SALEGETAPPLICATION =setUrl("ApplicationService.asmx/saleGetApplication");
	//顾问查看外勤申请
	private static final String GETAPPLICATIONBYUSER =setUrl("ApplicationService.asmx/getApplicationByUser");
	//获取可签到申请
	private static final String GETCANSIGNINLIST =setUrl("ApplicationService.asmx/saleGetApplicationList");
	//外勤签到
	private static final String WAIQINSIGNIN =setUrl("ApplicationService.asmx/ApplicationSignIn");

	//检查版本更新
	private static final String CHECKVERSIONCODE =setUrl("LoginService.asmx/getAPPVersion");


	/*//修改密码
	private static final String RESETPWD_URL = setUrl("ResetPassWordService.asmx/ResetPassword");

	//意见反馈
	private static final String FEEDBACKADD_URL = setUrl("FeedBackService.asmx/FeedBackAdd");

	//销售任务列表
	private static final String GETUSERTAAKLIST_URL = setUrl("SaleTaskDetailService.asmx/GetUserTaskList");

	//任务详情
	private static final String GETTASKBYID_URL = setUrl("SaleTaskDetailService.asmx/GetTaskById");

	//成单
	private static final String GETCLUE_URL = setUrl("SaleTaskDetailService.asmx/GetClue");

	//任务详情---反馈按钮
	private static final String BOOKCALLTIME_URL = setUrl("SaleTaskDetailService.asmx/BookCallTime");

	//加载丢单原因
	private static final String LOSECLUEDATIL_URL = setUrl("SaleTaskDetailService.asmx/LoseClueDetail");


	//任务详情---丢单按钮
	private static final String LOSECLUE_URL = setUrl("SaleTaskDetailService.asmx/LoseClue");


	//任务跟进---进行通话记录
	private static final String CALLRECORDING_URL = setUrl("SaleTaskDetailService.asmx/CallRecording");

	//销售线索列表
	private static final String GETREPORTINFoLIST_URL = setUrl("SaleClueServiceService.asmx/GetReportInfoList");

	//任务详情列表
	private static final String GETCLUEDETAILS_URL = setUrl("SaleClueServiceService.asmx/GetClueDetails");


	//門店顧問列表
	private static final String GETSTOREEMPLOYEELIST_URL = setUrl("SaleClueServiceService.asmx/GetStoreEmployeeList");

	//任务分配
	private static final String GETASSIGSTORE_URL = setUrl("SaleClueServiceService.asmx/GetAssigStore");

	//平均分配
	private static final String GETAVGASSIGSTORE_URL = setUrl("SaleClueServiceService.asmx/GetAvgAssigStore");


	//销售线索跟进中列表
	private static final String GETFOLLMANCLUESALE_URL = setUrl("SaleClueServiceService.asmx/GetFollManClueSale");

	//获取待分配任务数和销售线索总数
	private static final String GETtASKTODAY_URL = setUrl("SaleClueServiceService.asmx/GetTaskToday");

	//拨打电话记录
	private static final String CALL_MSG_URL = setUrl("SaleTaskDetailService.asmx/CallRecording");

	//计划列表
	private static final String GETTASKBOOKLIST_URL = setUrl("SaleTaskDetailService.asmx/GetTaskBookListByDate");

	//日历集合
	private static final String GETTASKBOOKDATE_URL = setUrl("SaleTaskDetailService.asmx/getTaskBookDateByUser");

	//搜索框
	private static final String GETBLURCHURBYNAME_URL = setUrl("SaleClueServiceService.asmx/getBlurClueByName");*/

	private static String setUrl(String action){

		return BASE_URL + action;
	}
	/**
	 * 登录
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public static String loging(String userName,String userPwd){
		return lOGIN_URL+"?userName="+userName+"&userPwd="+userPwd;
	}
	/**
	 * 销售经理今日任务统计
	 * @param userId
	 * @return
	 */
	public static String getSaleStatistics(String userId){
		return SALESTATISTICS_URL+"?userId="+userId;
	}
	/**
	 * 销售经理未分配线索
	 * @param userId
	 * @param indexPage
	 * @return
	 */
	public static String getReportInfoList(String userId,String indexPage){
		return GETREPORTINFOLIST+"?userId="+userId+"&indexPage="+indexPage;
	}
	/**
	 * 销售经理今日总任务
	 * @param userId
	 * @param indexPage
	 * @return
	 */
	public static String getTodayTotalTask(String userId,String indexPage){
		return GETTODAYTOTALTASK+"?userId="+userId+"&indexPage="+indexPage;
	}
	/**
	 * 我的任务统计
	 * @param userId
	 * @return
	 */
	public static String getMySaleStatistics(String userId){
		return MY_GETSALESTATISTICS+"?userId="+userId;
	}
	/**
	 * 获取销售任务列表
	 * @param userId
	 * @return
	 */
	public static String getUserTaskList(String userId,String indexPage){
		return GETUSERTASKLIST+"?UserCode="+userId+"&IndexPage="+indexPage;
	}

	/**
	 * 经理端销售线索跟进中或未跟进中所有列表
	 * @param userId
	 * @param clueState
	 * @param indexPage
	 * @return
	 */
	public static String getFollManClueSale(String userId,String clueState,String indexPage){
		return GETFOLLMANCLUESALE+"?userId="+userId+"&clueState="+clueState+"&indexPage="+indexPage;
	}
	/**
	 * 今日任务搜索
	 * @param userId
	 * @param clueState
	 * @param indexPage
	 * @return
	 */
	public static String getBlurClueByName(String userId,String cusName,String clueState,String indexPage){
		return GETBLURCHURBYNAME_URL+"?userId="+userId+"&cusName="+cusName+"&clueState="+clueState+"&indexPage="+indexPage;
	}
	/**
	 * 回库任务
	 * @param userId
	 * @return
	 */
	public static String getBackTaskList(String userId,String indexPage){
		return GETBACKTASKLIST+"?userId="+userId+"&indexPage="+indexPage;
	}
	/**
	 * 获取员工总数和线索总数
	 * @param userId
	 * @return
	 */
	public static String getTaskEmpCunt(String userId){
		return GETTASKEMPCOUNT+"?userId="+userId;
	}
	/**
	 * 获取回库员工总数和线索总数
	 * @param userId
	 * @return
	 */
	public static String getBackTaskEmpCunt(String userId){
		return GETBACKTASKEMPCOUNT+"?userId="+userId;
	}
	/**
	 * 平均分配线索
	 * @param userId
	 * @return
	 */
	public static String getAvgAssigStore(String userId){
		return GETAVGASSIGSTORE+"?userId="+userId;
	}
	/**
	 * 平均分配回库线索
	 * @param userId
	 * @return
	 */
	public static String getAvgBackAssigStore(String userId){
		return GETAVGBACKASSIGSTORE+"?userId="+userId;
	}
	/**
	 * 选中线索平均分配
	 * @param userId
	 * @param Ids
	 * @return
	 */
	public static String getAvgBId(String userId,String Ids){
		return GETAVGBID+"?userId="+userId+"&Ids="+Ids;
	}

	/**
	 * 任务自主分配
	 * @param userId
	 * @param clueId
	 * @return
	 */
	public static String getAssigStore(String userId,String clueId){
		return GETASSIGSTORE+"?userId="+userId+"&clueId="+clueId;
	}
	/**
	 * 门店顾问列表
	 * @param userId
	 * @return
	 */
	public static String getStoreEmployeeList(String userId){
		return GETSTOREEMPLOYEELIST+"?userId="+userId;
	}

	/**
	 * 销售顾问跟进中和未跟进任务
	 * @param userId
	 * @param clueState
	 * @param indexPage
	 * @return
	 */
	public static String getClueListByState(String userId,String clueState,String indexPage){
		return GETCLUELISTBYSTATE+"?userId="+userId+"&clueState="+clueState+"&indexPage="+indexPage;
	}
	/**
	 * 成单
	 * @param clueId
	 * @return
	 */
	public static String getClue(String clueId){
		return GETCLUE+"?ClueId="+clueId;
	}
	/**
	 * 通话时长
	 * @param clueId
	 * @param duration
	 * @param createTime
	 * @return
	 */
	public static String callRecording(String culeId,String duration,String createTime){
		return CALL_MSG_URL+"?CuleId="+culeId+"&Duration="+duration+"&CreateTime="+createTime;
	}
	/**
	 * 日历集合
	 * @param userId
	 * @return
	 */
	public static String getTaskBookDateByUser(String userId){
		return GETTASKBOOKDATE_URL+"?userId="+userId;
	}
	/**
	 * 根据日期获取计划安排
	 * @param userId
	 * @param time
	 * @return
	 */
	public static String getTaskBookListByDate(String userId,String time){
		return GETTASKBOOKLIST_URL+"?UserCode="+userId+"&SelectTime="+time;
	}
	/**
	 * 反馈计划
	 * @param clueId
	 * @param bookTime
	 * @param txt
	 * @return
	 */
	public static String requestBookCall(String clueId,String bookTime,String txt){
		return BOOKCALLTIME+"?ClueId="+clueId+"&BookTime="+bookTime+"&txt="+txt;
	}
	/**
	 * 外勤列表（未处理、未通过、外勤签到）
	 * @param userId
	 * @param appState 申请状态（1未处理，2通过（即是外勤签到），3未通过）
	 * @param indexPage
	 * @param selectTime
	 * @return
	 */
	public static String getProcessedState(String userId,String appState,String indexPage,String selectTime){
		return GETPROCESSEDSTATE+"?userId="+userId+"&appState="+appState+"&indexPage="+indexPage+"&selectTime="+selectTime;
	}
	/**
	 * 外勤申请通过/驳回操作
	 * @param astate 3（通过）,2（驳回）
	 * @param ids
	 * @return
	 */
	public static String makeApplication(String astate,String ids){
		return MAKEAPPLICATION+"?Astate="+astate+"&ids="+ids;
	}
	/**
	 * 外勤信息详情
	 * @param appId
	 * @return
	 */
	public static String getApplicationInfo(String appId){
		return GETAPPLICATIONINFO+"?appId="+appId;
	}
	/**
	 * 外勤申请
	 * @param userId
	 * @param selectTime
	 * @param addr
	 * @param cause
	 * @return
	 */
	public static String saleGetApplication(String userId,String selectTime,String addr,String cause){
		return SALEGETAPPLICATION+"?userId="+userId+"&selectTime="+selectTime+"&addr="+addr+"&cause="+cause;
	}
	/**
	 * 顾问查看申请
	 * @param userId
	 * @param astate
	 * @param indexPage
	 * @return
	 */
	public static String getApplicationByUser(String userId,String astate,String indexPage){
		return GETAPPLICATIONBYUSER+"?userId="+userId+"&Astate="+astate+"&indexPage="+indexPage;
	}
	/**
	 * 查看可签到申请
	 * @param userId
	 * @return
	 */
	public static String saleGetApplicationList(String userId){
		return GETCANSIGNINLIST+"?userId="+userId;
	}
	/**
	 * 外勤签到申请
	 * @param appId
	 * @param signLng
	 * @param signLat
	 * @param signName
	 * @return
	 */
	public static String applicationSignIn(String appId,String signLng,String signLat,String signName){
		return WAIQINSIGNIN+"?appId="+appId+"&signLng="+signLng+"&signLat="+signLat+"&signName="+signName;
	}
	/**
	 * 检查版本更新
	 * @param versionCode
	 * @return
	 */
	public static String saleGetApplicationList(int versionCode){
		return CHECKVERSIONCODE+"?Remark=Android&versionCode="+versionCode;
	}
	/**
	 * 得到消息个数
	 * @param userId
	 * @return
	 */
	public static String getMessagerNumber(String userId){
		return GETMESSAGERNUMBER+"?userId="+userId;
	}
	/**
	 * 得到顾问消息个数
	 * @param userId
	 * @return
	 */
	public static String getMessagerNumberGw(String userId){
		return GETMESSAGERNUMBERGW+"?userId="+userId;
	}
	/**
	 * 顾问端今日任务
	 * @param userId
	 * @param indexPage
	 * @param clueState
	 * @return
	 */
	public static String getTodayTask(String userId,String indexPage,String clueState){
		return GETTODAYTASK+"?userId="+userId+"&indexPage="+indexPage+"&clueState="+clueState;
	}
}