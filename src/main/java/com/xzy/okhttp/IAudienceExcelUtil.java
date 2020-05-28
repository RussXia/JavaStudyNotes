package com.xzy.okhttp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
//import com.souche.optimus.common.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author xiazhengyue
 * @since 2019-04-01
 */
@Slf4j
public class IAudienceExcelUtil {

    private static String URL = "https://api.data.jiguang.cn/v5/users";

    private static OkHttpClient client = new OkHttpClient.Builder().authenticator(new Authenticator() {
        @Nullable
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            String credential = Credentials.basic("bcd385c6e1d66e4921014df4", "1bc305466ac2d7b7e97ce1d3");
            return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
        }
    }).build();

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/dasouche/Downloads/极光样本数据.csv");
        List<String> phoneNumList = parsePhoneNumMD5(file);

        List<List<String>> iAudienceResult = Lists.newArrayList();
        for (String phoneNum : phoneNumList) {
            String url = buildURL(phoneNum);
            String result = doGet(url);
            List<String> elements = parseExportElements(phoneNum, result);
            iAudienceResult.add(elements);
            log.info("the size of this line is : {}", elements.size());
            log.info("the size of this iAudienceResult is : {}", iAudienceResult.size());
        }
        List<String> title = getTitleRow();
        log.info("=====================================================================");
        log.info("=====================================================================");
        log.info("=====================================================================");
        log.info("iAudienceResult size : {}", iAudienceResult.size());
        log.info("title size : {}", title.size());
        File excel = new File("/Users/dasouche/Downloads/极光样本数据.xlsx");
        FileOutputStream out = new FileOutputStream(excel);

//        ExcelUtils.createExcelStream(out, "样本数据", title, iAudienceResult);
        log.info("excel donwload over");
    }

    public static List<String> getTitleRow() {
        return Lists.newArrayList("手机号md5", "极光设备唯一标识", "行为性别", "国籍", "年龄段", "职业类型", "是否在校大学生",
                "学历水平", "婚姻状态", "是否有子女", "子女阶段", "有无车标识", "收入能力水平", "消费能力水平", "消费偏好", "消费品级", "驾考意愿强度", "买车意愿强度",
                "租车意愿强度", "车贷意愿强度", "旅游意愿强度", "健身意愿强度", "孕期知识学习意愿强度", "孕育期知识学习意愿强度", "婴幼儿早教意愿强度",
                "小学教育意愿强度", "中小学教育意愿强度", "初中教育意愿强度", "初高中教育意愿强度", "高中教育意愿强度", "大学教育意愿强度", "考研培训意愿强度",
                "美食烹饪培训意愿强度", "语言培训意愿强度", "职场培训意愿强度", "出国留学意愿强度", "国考培训意愿强度", "艺术培训意愿强度", "成人自考培训意愿强度",
                "租房意愿强度", "买房意愿强度", "手机型号", "品牌类型", "硬件特性", "运营商", "分辨率", "屏幕尺寸", "操作系统", "上市时间", "设备类型", "设备价格",
                "应用偏好（数量）", "购买方式", "上网目的", "兴趣爱好(数量)", "阅读偏好", "公交类应用偏好", "票务类应用偏好", "火车高铁类应用偏好", "飞机类应用偏好",
                "打车类应用偏好", "专车类应用偏好", "大巴类应用偏好", "代驾类应用偏好", "租车类应用偏好", "青年旅馆类应用偏好", "民宿类应用偏好", "快捷酒店类应用偏好",
                "银联支付类应用偏好", "其他三方支付类应用偏好", "网络银行类应用偏好", "外资银行类应用偏好", "股份制银行类应用偏好", "信用卡类应用偏好",
                "城市银行类应用偏好", "国有银行类应用偏好", "期货类应用偏好", "虚拟货币类应用偏好", "外汇类应用偏好", "贵金属类应用偏好", "基金类应用偏好",
                "股票类应用偏好", "综合理财类应用偏好", "车贷类应用偏好", "分期贷类应用偏好", "信用卡贷类应用偏好", "现金贷类应用偏好", "房贷类应用偏好",
                "P2P类应用偏好", "综合贷款平台类应用偏好", "体彩类应用偏好", "福利彩票类应用偏好", "双色球类应用偏好", "综合彩票平台类应用偏好", "足彩类应用偏好",
                "综合直播类应用偏好", "短视频DIY类应用偏好", "社交直播类应用偏好", "综合视频类应用偏好", "体育视频类应用偏好", "游戏直播类应用偏好",
                "自拍美颜类应用偏好", "电视直播类应用偏好", "文化直播类应用偏好", "秀场直播类应用偏好", "体育直播类应用偏好", "有声小说类应用偏好",
                "综合新闻类应用偏好", "女性健康知识类应用偏好", "政治军事类应用偏好", "漫画绘本类应用偏好", "体育新闻类应用偏好", "文学历史类应用偏好",
                "财经新闻类应用偏好", "娱乐资讯类应用偏好", "医学教育类应用偏好", "仙侠小说类应用偏好", "科技新闻类应用偏好", "寻医问药类应用偏好",
                "励志鸡汤类应用偏好", "汽车资讯类应用偏好", "幽默笑话类应用偏好", "麻将棋牌类应用偏好", "动作竞速类应用偏好", "角色扮演类应用偏好",
                "网络游戏类应用偏好", "休闲益智类应用偏好", "武侠金庸类应用偏好", "游戏视频类应用偏好", "神话修真类应用偏好", "宝石消除类应用偏好",
                "经营策略类应用偏好", "户外运动类应用偏好", "电影类应用偏好", "动漫动画类应用偏好", "美容美妆类应用偏好", "健身减肥类应用偏好",
                "体育运动类应用偏好", "生鲜配送类应用偏好", "蹭网达人类应用偏好", "汽车养护类应用偏好", "生活缴费类应用偏好", "宠物周边类应用偏好",
                "外卖类应用偏好", "美食类应用偏好", "掌上超市类应用偏好", "女性健康类应用偏好", "便签记事类应用偏好", "孕育指导类应用偏好",
                "共享出行类应用偏好", "食谱菜谱类应用偏好", "买房租房类应用偏好", "中医养生类应用偏好", "工作招聘类应用偏好", "家政服务类应用偏好",
                "K歌类应用偏好", "快递邮政类应用偏好", "人脉社交类应用偏好", "妈妈社区类应用偏好", "熟人社交类应用偏好", "婚恋交友类应用偏好",
                "校园社交类应用偏好", "情侣社交类应用偏好", "二次元类应用偏好", "陌生人社交类应用偏好", "匿名社交类应用偏好", "同城交友类应用偏好",
                "追星一族类应用偏好", "金融教育类应用偏好", "中学教育类应用偏好", "IT教育类应用偏好", "小学教育类应用偏好", "胎儿教育类应用偏好",
                "在线教育类应用偏好", "外语学习类应用偏好", "驾考学习类应用偏好", "公务员考试类应用偏好", "儿童教育类应用偏好", "大学教育类应用偏好",
                "汽车专卖类应用偏好", "二手闲置类应用偏好", "综合购物类应用偏好", "优惠类应用偏好", "品牌折扣类应用偏好", "母婴专卖类应用偏好",
                "女性购物类应用偏好", "返利类应用偏好", "团购类应用偏好", "海淘类应用偏好", "导购资讯类应用偏好", "情趣专卖类应用偏好", "移动办公类应用偏好",
                "近3个月访问驾考类APP的次数（评分）", "近3个月最近一次访问驾考类APP的日期（评分）", "近3个月新安装驾考类APP个数（评分）",
                "近3个月访问买车类APP的次数（评分）", "近3个月最近一次访问买车类APP的日期（评分）", "近3个月新安装买车类APP个数（评分）",
                "近3个月访问租车类APP的次数（评分）", "近3个月最近一次访问租车类APP的日期（评分）", "近3个月新安装租车类APP个数（评分）",
                "近3个月访问用车类APP的次数（评分）", "近3个月最近一次访问用车类APP的日期（评分）", "近3个月新安装用车类APP个数（评分）",
                "近3个月访问汽车服务类APP的次数（评分）", "近3个月最近一次访问汽车服务类APP的日期（评分）", "近3个月新安装汽车服务类APP个数（评分）",
                "近3个月访问车贷类APP的次数（评分）", "近3个月最近一次访问车贷类APP的日期（评分）", "近3个月新安装车贷类APP个数（评分）",
                "近3个月访问违章类APP的次数（评分）", "近3个月最近一次访问违章类APP的日期（评分）", "近3个月新安装违章类APP个数（评分）",
                "近3个月最近一次去汽车美容店的日期（评分）", "近3个月出现在汽车美容店的次数（评分）", "近3个月最近一次去汽车检测场的日期（评分）",
                "近3个月出现在汽车检测场的次数（评分）", "近3个月最近一次去汽车配件店的日期（评分）", "近3个月出现在汽车配件店的次数（评分）",
                "近3个月最近一次去租车的日期（评分）", "近3个月租车的次数（评分）", "近3个月最近一次去汽车维修店的日期（评分）", "近3个月出现在汽车维修店的次数（评分）",
                "近3个月最近一次去汽车销售点的日期（评分）", "近3个月出现在汽车销售点的次数（评分）", "近3个月最近一次去汽车服务点的日期（评分）",
                "近3个月出现在汽车服务点的次数（评分）", "近3个月统计家庭工作地址距离（评分）", "近3个月最近一次周末出行的日期（评分）",
                "近3个月周末出行的次数（评分）", "近3个月最近一次去驾校的日期（评分）", "近3个月出现在驾校的次数（评分）", "近3个月访问孕期类APP的次数（评分）",
                "近3个月最近一次访问孕期类APP的日期（评分）", "近3个月新安装孕期类APP个数（评分）", "近3个月访问孕育期类APP的次数（评分）",
                "近3个月最近一次访问孕育期类 APP的日期（评分）", "近3个月新安装孕育期类APP个数（评分）", "近3个月访问婴幼儿类APP的次数（评分）",
                "近3个月最近一次访问婴幼儿类APP的日期（评分）", "近3个月新安装婴幼儿类APP个数（评分）", "近3个月访问小学类APP的次数（评分）",
                "近3个月最近一次访问小学类APP的日期（评分）", "近3个月新安装小学类APP个数（评分）", "近3个月访问中小学类APP的次数（评分）",
                "近3个月最近一次访问中小学类APP的日期（评分）", "近3个月新安装中小学类APP个数（评分）", "近3个月访问初中类APP的次数（评分）",
                "近3个月最近一次访问初中类APP的日期（评分）", "近3个月新安装初中类APP个数（评分）", "近3个月访问初高中类APP的次数（评分）",
                "近3个月最近一次访问初高中类APP的日期（评分）", "近3个月新安装初高中类APP数（评分）", "近3个月访问高中类APP的次数（评分）",
                "近3个月最近一次访问高中类APP的日期（评分）", "近3个月新安装高中类APP个数（评分）", "近3个月访问大学类APP的次数（评分）",
                "近3个月最近一次访问大学类APP的日期（评分）", "近3个月新安装大学类APP个（评分）", "近3个月访问考研类APP的次数（评分）",
                "近3个月最近一次访问考研类APP的日期（评分）", "近3个月新安装考研类APP个数（评分）", "近3个月访问在线教育类APP的次数（评分）",
                "近3个月最近一次访问在线教育类APP的日期（评分）", "近3个月新安装在线教育类APP个数（评分）", "近3个月访问考培训类APP的次数（评分）",
                "近3个月最近一次访问国考培训类APP的日期（评分）", "近3个月新安装国考培训类APP个数（评分）", "近3个月访问外语学习类APP的次数（评分）",
                "近3个月最近一次访问外语学习类APP的日期（评分）", "近3个月新安装外语学习类APP个数（评分）", "近3个月访问烹饪类APP的次数（评分）",
                "近3个月最近一次访问烹饪类APP的日期（评分）", "近3个月新安装烹饪类APP个数（评分）", "近3个月访问健身类APP的次数（评分）",
                "近3个月最近一次访问健身类APP的日期（评分）", "近3个月新安装健身类APP个数（评分）", "近3个月访问艺术培训类APP的次数（评分）",
                "近3个月最近一次访问艺术培训类APP的日期（评分）", "近3个月新安装艺术培训类APP个数（评分）", "近3个月访问成人教育类APP的次数（评分）",
                "近3个月最近一次访问成人教育类APP的日期（评分）", "近3个月新安装成人教育类APP个数（评分）", "近3个月访问出国留学类APP的次数（评分）",
                "近3个月最近一次访问出国留学类APP的日期（评分）", "近3个月新安装出国留学类APP个数（评分）", "近3个月最近一次去早教", "近3个月出现在早教",
                "近3个月最近一次去幼儿园", "近3个月出现在幼儿园", "近3个月最近一次去小学的日期（评分）", "近3个月出现在小学的次数（评分）",
                "近3个月最近一次去初中的日期（评分）", "近3个月出现在初中的次数（评分）", "近3个月最近一次去中学的日期（评分）", "近3个月出现在中学的次数（评分）",
                "近3个月最近一次去高中的日期（评分）", "近3个月出现在高中的次数（评分）", "近3个月最近一次去大学日期（评分）", "近3个月出现在大学的次数",
                "近3个月最近一次去出国留学机构日期（评分）", "近3个月出现在留学机构的次数（评分）", "近3个月最近一次去成人教育机构日期（评分）",
                "近3个月出现在成人教育机构的次数（ 评分）", "近3个月最近一次去艺术培训机构日期（评分）", "近3个月出现在艺术培训机构的次数（ 评分）",
                "近3个月最近一次去健身房日期（评分）", "近3个月出现在健身房的次数（评分）", "近3个月最近一次去美食", "近3个月出现在美食",
                "近3个月最近一次去外语培训机构的日期（评分）", "近3个月出现在外语培训机构的次数（评分）", "近3个月最近一次去国考培训的日期（评分）",
                "近3个月出现在国考培训的次数（评分）", "近3个月最近一次去考研机构的日期（评分）", "近3个月出现在考研机构的次数（评分）",
                "近3个月访问租房类APP的次数（评分）", "近3个月最近一次访问租房类APP的日期（评分）", "近3个月新安装租房类APP个数（评分）",
                "近3个月访问房产综合类APP的次数（评分）", "近3个月最近一次访问房产综合类APP的日期（评分）", "近3个月新安装房产综合类APP个数（评分）",
                "近3个月最近一次去售楼处的日期（评分）", "近3个月出现在售楼处的次数（评分）", "近3个月访问民宿预订APP的次数（评分）",
                "近3个月最近一次访问民宿预订 APP的日期（评分）", "近3个月新安装民宿预订APP个数（评分）", "近3个月访问旅游攻略类APP的次数（评分）",
                "近3个月最近一次访问旅游攻略类APP的日期（评分）", "近3个月新安装旅游攻略类APP个数（评分）", "近3个月访问经济型酒店预订APP的次数（评分）",
                "近3个月最近一次访问经济型酒店预订APP的日期（评分）", "近3个月新安装经济型酒店预订APP个数（评分）", "近3个月访问综合酒店预订类APP的次数（评分）",
                "近3个月最近一次访问综合酒店预订类APP的日期（评分）", "近3个月新安装综合酒店预订类APP个数（评分）", "近3个月最近一次去旅行社的日期（评分）",
                "近3个月出现在旅行社的次数（评分）", "近3个月最近一次去旅游的日期（评分）", "近3个月去旅游的次数（评分）");
    }

    private static List<String> parsePhoneNumMD5(File file) {
        List<String> phoneNumList = Lists.newArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                phoneNumList.add(line);
            }
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    log.error("some error occured when closing BufferedReader,error is : {}", e);
                }
            }
        }

        return phoneNumList;
    }

    private static String buildURL(String phoneNum) {
        return URL + "?phone_md5=" + phoneNum + "&tag=all";
    }

    private static String doGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        Preconditions.checkNotNull(response, "response is null");
        Preconditions.checkNotNull(response.body(), "response body is null");
        return response.body().string();
    }

    private static List<String> parseExportElements(String phoneNum, String result) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if (code != 2000 && code != 2001) {
            log.error("查询信息失败,phoneNum:{},result:{}", phoneNum, result);
        }
        if (code == 2001) {
            return Lists.newArrayList(phoneNum, "no data");
        }
        JSONObject data = jsonObject.getJSONObject("data");
        List<String> parseResult = Lists.newArrayList();
        try {
            parseResult = parseElement(phoneNum, data);
        } catch (Exception e) {
            log.error("parse Element error,phone num : {}; error msg: {}", phoneNum, e);
        }
        return parseResult;
    }

    private static List<String> parseElement(String phoneNum, JSONObject data) {
        String cid_jid = getValue("CID_JID", data);
        String cpl_indm_gend_s = getValue("CPL_INDM_GEND_S", data);
        String cpl_indm_nati = getValue("CPL_INDM_NATI", data);
        String cpl_indm_age_c5 = getValue("CPL_INDM_AGE_C5", data);
        String som_ocm_career = getValue("SOM_OCM_CAREER", data);
        String cpl_indm_underg = getValue("CPL_INDM_UNDERG", data);
        String cpl_indm_edu_level = getValue("CPL_INDM_EDU_LEVEL", data);
        String cpl_indm_marrc2 = getValue("CPL_INDM_MARRC2", data);
        String cpl_hhm_child_hc = getValue("CPL_HHM_CHILD_HC", data);
        String cpl_hhm_child_chli = getValue("CPL_HHM_CHILD_CHLI", data);
        String cpl_indm_veic_veid = getValue("CPL_INDM_VEIC_VEID", data);
        String fim_fism_incl = getValue("FIM_FISM_INCL", data);
        String fim_fism_conl_cir = getValue("FIM_FISM_CONL_CIR", data);
        String gbm_bhm_purb_conp = getValue("GBM_BHM_PURB_CONP", data);
        String gbm_bhm_purb_pref = getJsonArray(data, "GBM_BHM_PURB_PREF");
        String desire_car_drivingTest = getValue("DESIRE_Car_DrivingTest", data);
        String desire_car_purchase = getValue("DESIRE_Car_Purchase", data);
        String desire_car_rent = getValue("DESIRE_Car_Rent", data);
        String desire_car_loan = getValue("DESIRE_Car_Loan", data);
        String desire_travel = getValue("DESIRE_Travel", data);
        String desire_edu_bodybuilding = getValue("DESIRE_EDU_Bodybuilding", data);
        String desire_edu_pregnancy = getValue("DESIRE_EDU_Pregnancy", data);
        String desire_edu_gestation = getValue("DESIRE_EDU_Gestation", data);
        String desire_edu_child = getValue("DESIRE_EDU_Child", data);
        String desire_edu_primary = getValue("DESIRE_EDU_Primary", data);
        String desire_edu_primaryMid = getValue("DESIRE_EDU_PrimaryMid", data);
        String desire_edu_junior = getValue("DESIRE_EDU_Junior", data);
        String desire_edu_middle = getValue("DESIRE_EDU_Middle", data);
        String desire_edu_senior = getValue("DESIRE_EDU_Senior", data);
        String desire_edu_college = getValue("DESIRE_EDU_College", data);
        String desire_edu_postgraduate = getValue("DESIRE_EDU_Postgraduate", data);
        String desire_edu_gourmand = getValue("DESIRE_EDU_Gourmand", data);
        String desire_edu_language = getValue("DESIRE_EDU_Language", data);
        String desire_edu_job = getValue("DESIRE_EDU_JOB", data);
        String desire_edu_abroad = getValue("DESIRE_EDU_Abroad", data);
        String desire_edu_officeholder = getValue("DESIRE_EDU_Officeholder", data);
        String desire_edu_art = getValue("DESIRE_EDU_Art", data);
        String desire_edu_selfTaught = getValue("DESIRE_EDU_SelfTaught", data);
        String desire_house_rent = getValue("DESIRE_House_Rent", data);
        String desire_house_buyNewHouse = getValue("DESIRE_House_BuyNewHouse", data);
        String cid_model = getValue("CID_MODEL", data);
        String cpl_dvm_brad = getValue("CPL_DVM_BRAD", data);
        String cpl_dvm_hf = getJsonArray(data, "CPL_DVM_HF");
        String cpl_dvm_isp = getValue("CPL_DVM_ISP", data);
        String cpl_dvm_reso = getValue("CPL_DVM_RESO", data);
        String cpl_dvm_scsize = getValue("CPL_DVM_SCSIZE", data);
        String cpl_dvm_os = getValue("CPL_DVM_OS", data);
        String cpl_dvm_time = getValue("CPL_DVM_TIME", data);
        String cpl_dvm_type = getValue("CPL_DVM_TYPE", data);
        String cpl_dvm_pupr = getValue("CPL_DVM_PUPR", data);
        String gbm_bhm_appp_appr_s = getJsonArray(data, "GBM_BHM_APPP_APPR_S");
        String gbm_bhm_purb_purw = getJsonArray(data, "GBM_BHM_PURB_PURW");
        String gbm_bhm_purb_supr = getJsonArray(data, "GBM_BHM_PURB_SUPR");
        String gbm_hbm_s = getJsonArray(data, "GBM_HBM_S");
        String gbm_bhm_reab_reap = getJsonArray(data, "GBM_BHM_REAB_REAP");
        String app_hoby_bus = getJsonObject(data, "APP_HOBY_BUS");
        String app_hoby_ticket = getJsonObject(data, "APP_HOBY_TICKET");
        String app_hoby_train = getJsonObject(data, "APP_HOBY_TRAIN");
        String app_hoby_flight = getJsonObject(data, "APP_HOBY_FLIGHT");
        String app_hoby_taxi = getJsonObject(data, "APP_HOBY_TAXI");
        String app_hoby_special_drive = getJsonObject(data, "APP_HOBY_SPECIAL_DRIVE");
        String app_hoby_high_bus = getJsonObject(data, "APP_HOBY_HIGH_BUS");
        String app_hoby_other_drive = getJsonObject(data, "APP_HOBY_OTHER_DRIVE");
        String app_hoby_rent_car = getJsonObject(data, "APP_HOBY_RENT_CAR");
        String app_hoby_young_hotel = getJsonObject(data, "APP_HOBY_YOUNG_HOTEL");
        String app_hoby_home_hotel = getJsonObject(data, "APP_HOBY_HOME_HOTEL");
        String app_hoby_convert_hotel = getJsonObject(data, "APP_HOBY_CONVERT_HOTEL");
        String app_hoby_bank_unin = getJsonObject(data, "APP_HOBY_BANK_UNIN");
        String app_hoby_third_pay = getJsonObject(data, "APP_HOBY_THIRD_PAY");
        String app_hoby_internet_bank = getJsonObject(data, "APP_HOBY_INTERNET_BANK");
        String app_hoby_foreign_bank = getJsonObject(data, "APP_HOBY_FOREIGN_BANK");
        String app_hoby_middle_bank = getJsonObject(data, "APP_HOBY_MIDDLE_BANK");
        String app_hoby_credit_card = getJsonObject(data, "APP_HOBY_CREDIT_CARD");
        String app_hoby_city_bank = getJsonObject(data, "APP_HOBY_CITY_BANK");
        String app_hoby_state_bank = getJsonObject(data, "APP_HOBY_STATE_BANK");
        String app_hoby_futures = getJsonObject(data, "APP_HOBY_FUTURES");
        String app_hoby_virtual_currency = getJsonObject(data, "APP_HOBY_VIRTUAL_CURRENCY");
        String app_hoby_forex = getJsonObject(data, "APP_HOBY_FOREX");
        String app_hoby_noble_metal = getJsonObject(data, "APP_HOBY_NOBLE_METAL");
        String app_hoby_fund = getJsonObject(data, "APP_HOBY_FUND");
        String app_hoby_stock = getJsonObject(data, "APP_HOBY_STOCK");
        String app_hoby_zonghelicai = getJsonObject(data, "APP_HOBY_ZONGHELICAI");
        String app_hoby_car_loan = getJsonObject(data, "APP_HOBY_CAR_LOAN");
        String app_hoby_divide_loan = getJsonObject(data, "APP_HOBY_DIVIDE_LOAN");
        String app_hoby_credit_card_loan = getJsonObject(data, "APP_HOBY_CREDIT_CARD_LOAN");
        String app_hoby_cash_loan = getJsonObject(data, "APP_HOBY_CASH_LOAN");
        String app_hoby_house_loan = getJsonObject(data, "APP_HOBY_HOUSE_LOAN");
        String app_hoby_p2P = getJsonObject(data, "APP_HOBY_P2P");
        String app_hoby_loan_platform = getJsonObject(data, "APP_HOBY_LOAN_PLATFORM");
        String app_hoby_sport_lottery = getJsonObject(data, "APP_HOBY_SPORT_LOTTERY");
        String app_hoby_welfare_lottery = getJsonObject(data, "APP_HOBY_WELFARE_LOTTERY");
        String app_hoby_double_ball = getJsonObject(data, "APP_HOBY_DOUBLE_BALL");
        String app_hoby_lottery = getJsonObject(data, "APP_HOBY_LOTTERY");
        String app_hoby_football_lottery = getJsonObject(data, "APP_HOBY_FOOTBALL_LOTTERY");
        String app_hoby_summary_live = getJsonObject(data, "APP_HOBY_SUMMARY_LIVE");
        String app_hoby_short_video = getJsonObject(data, "APP_HOBY_SHORT_VIDEO");
        String app_hoby_social_live = getJsonObject(data, "APP_HOBY_SOCIAL_LIVE");
        String app_hoby_summary_video = getJsonObject(data, "APP_HOBY_SUMMARY_VIDEO");
        String app_hoby_sports_video = getJsonObject(data, "APP_HOBY_SPORTS_VIDEO");
        String app_hoby_game_live = getJsonObject(data, "APP_HOBY_GAME_LIVE");
        String app_hoby_self_photo = getJsonObject(data, "APP_HOBY_SELF_PHOTO");
        String app_hoby_tv_live = getJsonObject(data, "APP_HOBY_TV_LIVE");
        String app_hoby_culture_live = getJsonObject(data, "APP_HOBY_CULTURE_LIVE");
        String app_hoby_show_live = getJsonObject(data, "APP_HOBY_SHOW_LIVE");
        String app_hoby_sports_live = getJsonObject(data, "APP_HOBY_SPORTS_LIVE");
        String app_hoby_read_listen = getJsonObject(data, "APP_HOBY_READ_LISTEN");
        String app_hoby_sunmmary_news = getJsonObject(data, "APP_HOBY_SUNMMARY_NEWS");
        String app_hoby_women_hel_book = getJsonObject(data, "APP_HOBY_WOMEN_HEL_BOOK");
        String app_hoby_army_news = getJsonObject(data, "APP_HOBY_ARMY_NEWS");
        String app_hoby_carton_book = getJsonObject(data, "APP_HOBY_CARTON_BOOK");
        String app_hoby_phy_news = getJsonObject(data, "APP_HOBY_PHY_NEWS");
        String app_hoby_famouse_book = getJsonObject(data, "APP_HOBY_FAMOUSE_BOOK");
        String app_hoby_fincal_news = getJsonObject(data, "APP_HOBY_FINCAL_NEWS");
        String app_hoby_fun_news = getJsonObject(data, "APP_HOBY_FUN_NEWS");
        String app_hoby_edu_med = getJsonObject(data, "APP_HOBY_EDU_MED");
        String app_hoby_kongfu = getJsonObject(data, "APP_HOBY_KONGFU");
        String app_hoby_tech_news = getJsonObject(data, "APP_HOBY_TECH_NEWS");
        String app_hoby_look_for_med = getJsonObject(data, "APP_HOBY_LOOK_FOR_MED");
        String app_hoby_encourage_book = getJsonObject(data, "APP_HOBY_ENCOURAGE_BOOK");
        String app_hoby_car_info_news = getJsonObject(data, "APP_HOBY_CAR_INFO_NEWS");
        String app_hoby_humerious = getJsonObject(data, "APP_HOBY_HUMERIOUS");
        String app_hoby_cards_game = getJsonObject(data, "APP_HOBY_CARDS_GAME");
        String app_hoby_speed_game = getJsonObject(data, "APP_HOBY_SPEED_GAME");
        String app_hoby_role_game = getJsonObject(data, "APP_HOBY_ROLE_GAME");
        String app_hoby_net_game = getJsonObject(data, "APP_HOBY_NET_GAME");
        String app_hoby_relax_game = getJsonObject(data, "APP_HOBY_RELAX_GAME");
        String app_hoby_kongfu_game = getJsonObject(data, "APP_HOBY_KONGFU_GAME");
        String app_hoby_game_video = getJsonObject(data, "APP_HOBY_GAME_VIDEO");
        String app_hoby_tale_game = getJsonObject(data, "APP_HOBY_TALE_GAME");
        String app_hoby_diamonds_game = getJsonObject(data, "APP_HOBY_DIAMONDS_GAME");
        String app_hoby_tragedy_game = getJsonObject(data, "APP_HOBY_TRAGEDY_GAME");
        String app_hoby_outdoor = getJsonObject(data, "APP_HOBY_OUTDOOR");
        String app_hoby_movie = getJsonObject(data, "APP_HOBY_MOVIE");
        String app_hoby_carton = getJsonObject(data, "APP_HOBY_CARTON");
        String app_hoby_beautiful = getJsonObject(data, "APP_HOBY_BEAUTIFUL");
        String app_hoby_lose_weight = getJsonObject(data, "APP_HOBY_LOSE_WEIGHT");
        String app_hoby_phy_book = getJsonObject(data, "APP_HOBY_PHY_BOOK");
        String app_hoby_fresh_shopping = getJsonObject(data, "APP_HOBY_FRESH_SHOPPING");
        String app_hoby_wifi = getJsonObject(data, "APP_HOBY_WIFI");
        String app_hoby_car_pro = getJsonObject(data, "APP_HOBY_CAR_PRO");
        String app_hoby_life_pay = getJsonObject(data, "APP_HOBY_LIFE_PAY");
        String app_hoby_pet_market = getJsonObject(data, "APP_HOBY_PET_MARKET");
        String app_hoby_out_food = getJsonObject(data, "APP_HOBY_OUT_FOOD");
        String app_hoby_food = getJsonObject(data, "APP_HOBY_FOOD");
        String app_hoby_palm_market = getJsonObject(data, "APP_HOBY_PALM_MARKET");
        String app_hoby_women_heal = getJsonObject(data, "APP_HOBY_WOMEN_HEAL");
        String app_hoby_record = getJsonObject(data, "APP_HOBY_RECORD");
        String app_hoby_conceive = getJsonObject(data, "APP_HOBY_CONCEIVE");
        String app_hoby_share = getJsonObject(data, "APP_HOBY_SHARE");
        String app_hoby_cook_book = getJsonObject(data, "APP_HOBY_COOK_BOOK");
        String app_hoby_buy_rent_house = getJsonObject(data, "APP_HOBY_BUY_RENT_HOUSE");
        String app_hoby_chinese_medicine = getJsonObject(data, "APP_HOBY_CHINESE_MEDICINE");
        String app_hoby_job = getJsonObject(data, "APP_HOBY_JOB");
        String app_hoby_home_service = getJsonObject(data, "APP_HOBY_HOME_SERVICE");
        String app_hoby_krayok = getJsonObject(data, "APP_HOBY_KRAYOK");
        String app_hoby_fast_send = getJsonObject(data, "APP_HOBY_FAST_SEND");
        String app_hoby_people_resouse = getJsonObject(data, "APP_HOBY_PEOPLE_RESOUSE");
        String app_hoby_mama_social = getJsonObject(data, "APP_HOBY_MAMA_SOCIAL");
        String app_hoby_hot_social = getJsonObject(data, "APP_HOBY_HOT_SOCIAL");
        String app_hoby_marry_social = getJsonObject(data, "APP_HOBY_MARRY_SOCIAL");
        String app_hoby_campus_social = getJsonObject(data, "APP_HOBY_CAMPUS_SOCIAL");
        String app_hoby_lovers_social = getJsonObject(data, "APP_HOBY_LOVERS_SOCIAL");
        String app_hoby_ecy = getJsonObject(data, "APP_HOBY_ECY");
        String app_hoby_stranger_social = getJsonObject(data, "APP_HOBY_STRANGER_SOCIAL");
        String app_hoby_anonymous_social = getJsonObject(data, "APP_HOBY_ANONYMOUS_SOCIAL");
        String app_hoby_city_social = getJsonObject(data, "APP_HOBY_CITY_SOCIAL");
        String app_hoby_fans = getJsonObject(data, "APP_HOBY_FANS");
        String app_hoby_fin = getJsonObject(data, "APP_HOBY_FIN");
        String app_hoby_middle = getJsonObject(data, "APP_HOBY_MIDDLE");
        String app_hoby_it = getJsonObject(data, "APP_HOBY_IT");
        String app_hoby_primary = getJsonObject(data, "APP_HOBY_PRIMARY");
        String app_hoby_baby = getJsonObject(data, "APP_HOBY_BABY");
        String app_hoby_online_study = getJsonObject(data, "APP_HOBY_ONLINE_STUDY");
        String app_hoby_foreign = getJsonObject(data, "APP_HOBY_FOREIGN");
        String app_hoby_drive = getJsonObject(data, "APP_HOBY_DRIVE");
        String app_hoby_servants = getJsonObject(data, "APP_HOBY_SERVANTS");
        String app_hoby_child_edu = getJsonObject(data, "APP_HOBY_CHILD_EDU");
        String app_hoby_university = getJsonObject(data, "APP_HOBY_UNIVERSITY");
        String app_hoby_car_shopping = getJsonObject(data, "APP_HOBY_CAR_SHOPPING");
        String app_hoby_secondhand_shopping = getJsonObject(data, "APP_HOBY_SECONDHAND_SHOPPING");
        String app_hoby_zonghe_shopping = getJsonObject(data, "APP_HOBY_ZONGHE_SHOPPING");
        String app_hoby_payback = getJsonObject(data, "APP_HOBY_PAYBACK");
        String app_hoby_discount_market = getJsonObject(data, "APP_HOBY_DISCOUNT_MARKET");
        String app_hoby_baby_shopping = getJsonObject(data, "APP_HOBY_BABY_SHOPPING");
        String app_hoby_women_shopping = getJsonObject(data, "APP_HOBY_WOMEN_SHOPPING");
        String app_hoby_rebate_shopping = getJsonObject(data, "APP_HOBY_REBATE_SHOPPING");
        String app_hoby_group_buy = getJsonObject(data, "APP_HOBY_GROUP_BUY");
        String app_hoby_global_shopping = getJsonObject(data, "APP_HOBY_GLOBAL_SHOPPING");
        String app_hoby_shopping_guide = getJsonObject(data, "APP_HOBY_SHOPPING_GUIDE");
        String app_hoby_sex_shopping = getJsonObject(data, "APP_HOBY_SEX_SHOPPING");
        String app_hoby_smote_office = getJsonObject(data, "APP_HOBY_SMOTE_OFFICE");
        String car_driving_viewScore = getValue("CAR_Driving_ViewScore", data);
        String car_driving_viewDateScore = getValue("CAR_Driving_ViewDateScore", data);
        String car_driving_installScore = getValue("CAR_Driving_InstallScore", data);
        String car_purchase_viewScore = getValue("CAR_Purchase_ViewScore", data);
        String car_purchase_viewDateScore = getValue("CAR_Purchase_ViewDateScore", data);
        String car_purchase_installScore = getValue("CAR_Purchase_InstallScore", data);
        String car_rent_viewScore = getValue("CAR_Rent_ViewScore", data);
        String car_rent_viewDateScore = getValue("CAR_Rent_ViewDateScore", data);
        String car_rent_installScore = getValue("CAR_Rent_InstallScore", data);
        String car_brand_viewScore = getValue("CAR_Brand_ViewScore", data);
        String car_brand_viewDateScore = getValue("CAR_Brand_ViewDateScore", data);
        String car_brand_installScore = getValue("CAR_Brand_InstallScore", data);
        String car_serve_viewScore = getValue("CAR_Serve_ViewScore", data);
        String car_serve_viewDateScore = getValue("CAR_Serve_ViewDateScore", data);
        String car_serve_installScore = getValue("CAR_Serve_InstallScore", data);
        String car_loan_viewScore = getValue("CAR_Loan_ViewScore", data);
        String car_loan_viewDateScore = getValue("CAR_Loan_ViewDateScore", data);
        String car_loan_installScore = getValue("CAR_Loan_InstallScore", data);
        String car_illegal_viewScore = getValue("CAR_Illegal_ViewScore", data);
        String car_illegal_viewDateScore = getValue("CAR_Illegal_ViewDateScore", data);
        String car_illegal_installScore = getValue("CAR_Illegal_InstallScore", data);
        String car_beauty_locViewDateScore = getValue("CAR_Beauty_LocViewDateScore", data);
        String car_beauty_locViewScore = getValue("CAR_Beauty_LocViewScore", data);
        String car_check_locViewDateScore = getValue("CAR_Check_LocViewDateScore", data);
        String car_check_locViewScore = getValue("CAR_Check_LocViewScore", data);
        String car_part_locViewDateScore = getValue("CAR_Part_LocViewDateScore", data);
        String car_part_locViewScore = getValue("CAR_Part_LocViewScore", data);
        String car_rental_locViewDateScore = getValue("CAR_Rental_LocViewDateScore", data);
        String car_rental_locViewScore = getValue("CAR_Rental_LocViewScore", data);
        String car_repair_locViewDateScore = getValue("CAR_Repair_LocViewDateScore", data);
        String car_repair_locViewScore = getValue("CAR_Repair_LocViewScore", data);
        String car_sale_locViewDateScore = getValue("CAR_Sale_LocViewDateScore", data);
        String car_sale_locViewScore = getValue("CAR_Sale_LocViewScore", data);
        String car_service_locViewDateScore = getValue("CAR_Service_LocViewDateScore", data);
        String car_service_locviewscore = getValue("CAR_Service_LocViewScore", data);
        String all_home_work_locdistancescore = getValue("ALL_Home_Work_LocDistanceScore", data);
        String all_trip_locviewdatescore = getValue("ALL_Trip_LocViewDateScore", data);
        String all_trip_locviewscore = getValue("ALL_Trip_LocViewScore", data);
        String edu_driving_locviewdatescore = getValue("EDU_Driving_LocViewDateScore", data);
        String edu_driving_locviewscore = getValue("EDU_Driving_LocViewScore", data);
        String edu_pregnancy_viewscore = getValue("EDU_Pregnancy_ViewScore", data);
        String edu_pregnancy_viewdatescore = getValue("EDU_Pregnancy_ViewDateScore", data);
        String edu_pregnancy_installScore = getValue("EDU_Pregnancy_InstallScore", data);
        String edu_pre_child_viewScore = getValue("EDU_Pre_child_ViewScore", data);
        String edu_pre_child_viewDateScore = getValue("EDU_Pre_child_ViewDateScore", data);
        String edu_pre_child_installscore = getValue("EDU_Pre_child_InstallScore", data);
        String edu_child_viewscore = getValue("EDU_Child_ViewScore", data);
        String edu_child_viewdatescore = getValue("EDU_Child_ViewDateScore", data);
        String edu_child_installscore = getValue("EDU_Child_InstallScore", data);
        String edu_primary_viewscore = getValue("EDU_Primary_ViewScore", data);
        String edu_primary_viewdatescore = getValue("EDU_Primary_ViewDateScore", data);
        String edu_primary_installscore = getValue("EDU_Primary_InstallScore", data);
        String edu_primary_middle_viewscore = getValue("EDU_Primary_middle_ViewScore", data);
        String edu_primary_middle_viewdatescore = getValue("EDU_Primary_middle_ViewDateScore", data);
        String edu_primary_middle_installscore = getValue("EDU_Primary_middle_InstallScore", data);
        String edu_junior_viewscore = getValue("EDU_Junior_ViewScore", data);
        String edu_junior_viewdatescore = getValue("EDU_Junior_ViewDateScore", data);
        String edu_junior_installScore = getValue("EDU_Junior_InstallScore", data);
        String edu_middle_viewScore = getValue("EDU_Middle_ViewScore", data);
        String edu_middle_viewDateScore = getValue("EDU_Middle_ViewDateScore", data);
        String edu_middle_installScore = getValue("EDU_Middle_InstallScore", data);
        String edu_senior_viewScore = getValue("EDU_Senior_ViewScore", data);
        String edu_senior_viewDateScore = getValue("EDU_Senior_ViewDateScore", data);
        String edu_senior_installScore = getValue("EDU_Senior_InstallScore", data);
        String edu_college_viewScore = getValue("EDU_College_ViewScore", data);
        String edu_college_viewDateScore = getValue("EDU_College_ViewDateScore", data);
        String edu_college_installScore = getValue("EDU_College_InstallScore", data);
        String edu_postgraduate_viewScore = getValue("EDU_Postgraduate_ViewScore", data);
        String edu_postgraduate_viewDateScore = getValue("EDU_Postgraduate_ViewDateScore", data);
        String edu_postgraduate_installScore = getValue("EDU_Postgraduate_InstallScore", data);
        String edu_online_viewScore = getValue("EDU_Online_ViewScore", data);
        String edu_online_viewDateScore = getValue("EDU_Online_ViewDateScore", data);
        String edu_online_installScore = getValue("EDU_Online_InstallScore", data);
        String edu_officeholder_viewScore = getValue("EDU_Officeholder_ViewScore", data);
        String edu_officeholder_viewDateScore = getValue("EDU_Officeholder_ViewDateScore", data);
        String edu_officeholder_installScore = getValue("EDU_Officeholder_InstallScore", data);
        String edu_language_viewScore = getValue("EDU_Language_ViewScore", data);
        String edu_language_viewDateScore = getValue("EDU_Language_ViewDateScore", data);
        String edu_language_installScore = getValue("EDU_Language_InstallScore", data);
        String edu_gourmand_viewScore = getValue("EDU_Gourmand_ViewScore", data);
        String edu_gourmand_viewDateScore = getValue("EDU_Gourmand_ViewDateScore", data);
        String edu_gourmand_installScore = getValue("EDU_Gourmand_InstallScore", data);
        String edu_body_building_viewScore = getValue("EDU_Body_building_ViewScore", data);
        String edu_body_building_viewDateScore = getValue("EDU_Body_building_ViewDateScore", data);
        String edu_body_building_installScore = getValue("EDU_Body_building_InstallScore", data);
        String edu_art_viewScore = getValue("EDU_Art_ViewScore", data);
        String edu_art_viewDateScore = getValue("EDU_Art_ViewDateScore", data);
        String edu_art_installScore = getValue("EDU_Art_InstallScore", data);
        String edu_adult_viewScore = getValue("EDU_Adult_ViewScore", data);
        String edu_adult_viewDateScore = getValue("EDU_Adult_ViewDateScore", data);
        String edu_adult_installScore = getValue("EDU_Adult_InstallScore", data);
        String edu_abroad_viewScore = getValue("EDU_Abroad_ViewScore", data);
        String edu_abroad_viewDateScore = getValue("EDU_Abroad_ViewDateScore", data);
        String edu_abroad_installScore = getValue("EDU_Abroad_InstallScore", data);
        String edu_child_locViewDateScore = getValue("EDU_Child_LocViewDateScore", data);
        String edu_child_locViewScore = getValue("EDU_Child_LocViewScore", data);
        String edu_kindergarden_locViewDateScore = getValue("EDU_Kindergarden_LocViewDateScore", data);
        String edu_kindergarden_locViewScore = getValue("EDU_Kindergarden_LocViewScore", data);
        String edu_primary_locViewDateScore = getValue("EDU_Primary_LocViewDateScore", data);
        String edu_primary_locViewScore = getValue("EDU_Primary_LocViewScore", data);
        String edu_junior_locViewDateScore = getValue("EDU_Junior_LocViewDateScore", data);
        String edu_junior_locViewScore = getValue("EDU_Junior_LocViewScore", data);
        String edu_middle_locViewDateScore = getValue("EDU_Middle_LocViewDateScore", data);
        String edu_middle_locViewScore = getValue("EDU_Middle_LocViewScore", data);
        String edu_senior_locViewDateScore = getValue("EDU_Senior_LocViewDateScore", data);
        String edu_senior_locViewScore = getValue("EDU_Senior_LocViewScore", data);
        String edu_colleage_locViewDateScore = getValue("EDU_Colleage_LocViewDateScore", data);
        String edu_colleage_locViewScore = getValue("EDU_Colleage_LocViewScore", data);
        String edu_abroad_locViewDateScore = getValue("EDU_Abroad_LocViewDateScore", data);
        String edu_abroad_locViewScore = getValue("EDU_Abroad_LocViewScore", data);
        String edu_adult_locViewDateScore = getValue("EDU_Adult_LocViewDateScore", data);
        String edu_adult_locViewScore = getValue("EDU_Adult_LocViewScore", data);
        String edu_art_locViewDateScore = getValue("EDU_Art_LocViewDateScore", data);
        String edu_art_locViewScore = getValue("EDU_Art_LocViewScore", data);
        String edu_body_building_locViewDateScore = getValue("EDU_Body_Building_LocViewDateScore", data);
        String edu_body_building_locViewScore = getValue("EDU_Body_Building_LocViewScore", data);
        String edu_gourmand_locViewDateScore = getValue("EDU_Gourmand_LocViewDateScore", data);
        String edu_gourmand_locViewScore = getValue("EDU_Gourmand_LocViewScore", data);
        String edu_language_locViewDateScore = getValue("EDU_Language_LocViewDateScore", data);
        String edu_language_locViewScore = getValue("EDU_Language_LocViewScore", data);
        String edu_officeholder_locViewDateScore = getValue("EDU_Officeholder_LocViewDateScore", data);
        String edu_officeholder_locViewScore = getValue("EDU_Officeholder_LocViewScore", data);
        String edu_postgraduate_locViewDateScore = getValue("EDU_Postgraduate_LocViewDateScore", data);
        String edu_postgraduate_locViewScore = getValue("EDU_Postgraduate_LocViewScore", data);
        String house_rent_viewScore = getValue("HOUSE_Rent_ViewScore", data);
        String house_rent_viewDateScore = getValue("HOUSE_Rent_ViewDateScore", data);
        String house_rent_installScore = getValue("HOUSE_Rent_InstallScore", data);
        String house_combine_viewScore = getValue("HOUSE_Combine_ViewScore", data);
        String house_combine_viewDateScore = getValue("HOUSE_Combine_ViewDateScore", data);
        String house_combine_installScore = getValue("HOUSE_Combine_InstallScore", data);
        String house_newHouse_locViewDateScore = getValue("House_NewHouse_LocViewDateScore", data);
        String house_newhouse_locviewscore = getValue("House_NewHouse_LocViewScore", data);
        String travel_homestay_viewscore = getValue("TRAVEL_Homestay_ViewScore", data);
        String travel_homestay_viewdatescore = getValue("TRAVEL_Homestay_ViewDateScore", data);
        String travel_homestay_installscore = getValue("TRAVEL_Homestay_InstallScore", data);
        String travel_comprehensive_guide_viewscore = getValue("TRAVEL_Comprehensive_guide_ViewScore", data);
        String travel_comprehensive_guide_viewdatescore = getValue("TRAVEL_Comprehensive_guide_ViewDateScore", data);
        String travel_comprehensive_guide_installscore = getValue("TRAVEL_Comprehensive_guide_InstallScore", data);
        String travel_budget_hotel_viewscore = getValue("TRAVEL_Budget_hotel_ViewScore", data);
        String travel_budget_hotel_viewdatescore = getValue("TRAVEL_Budget_hotel_ViewDateScore", data);
        String travel_budget_hotel_installscore = getValue("TRAVEL_Budget_hotel_InstallScore", data);
        String travel_booking_hotel_viewscore = getValue("TRAVEL_Booking_hotel_ViewScore", data);
        String travel_booking_hotel_viewdatescore = getValue("TRAVEL_Booking_hotel_ViewDateScore", data);
        String travel_booking_hotel_installscore = getValue("TRAVEL_Booking_hotel_InstallScore", data);
        String travel_travel_agency_locviewdatescore = getValue("TRAVEL_Travel_agency_LocViewDateScore", data);
        String travel_travel_agency_locviewscore = getValue("TRAVEL_Travel_agency_LocViewScore", data);
        String travel_travel_locviewdatescore = getValue("TRAVEL_Travel_LocViewDateScore", data);
        String travel_travel_locviewscore = getValue("TRAVEL_Travel_LocViewScore", data);

        List<String> list = Lists.newArrayList(phoneNum, cid_jid, cpl_indm_gend_s, cpl_indm_nati, cpl_indm_age_c5, som_ocm_career, cpl_indm_underg,
                cpl_indm_edu_level, cpl_indm_marrc2, cpl_hhm_child_hc, cpl_hhm_child_chli, cpl_indm_veic_veid, fim_fism_incl, fim_fism_conl_cir,
                gbm_bhm_purb_conp, gbm_bhm_purb_pref, desire_car_drivingTest, desire_car_purchase, desire_car_rent, desire_car_loan, desire_travel,
                desire_edu_bodybuilding, desire_edu_pregnancy, desire_edu_gestation, desire_edu_child, desire_edu_primary, desire_edu_primaryMid,
                desire_edu_junior, desire_edu_middle, desire_edu_senior, desire_edu_college, desire_edu_postgraduate, desire_edu_gourmand, desire_edu_language,
                desire_edu_job, desire_edu_abroad, desire_edu_officeholder, desire_edu_art, desire_edu_selfTaught, desire_house_rent, desire_house_buyNewHouse,
                cid_model, cpl_dvm_brad, cpl_dvm_hf, cpl_dvm_isp, cpl_dvm_reso, cpl_dvm_scsize, cpl_dvm_os, cpl_dvm_time, cpl_dvm_type, cpl_dvm_pupr, gbm_bhm_appp_appr_s,
                gbm_bhm_purb_purw, gbm_bhm_purb_supr, gbm_hbm_s, gbm_bhm_reab_reap, app_hoby_bus, app_hoby_ticket, app_hoby_train, app_hoby_flight, app_hoby_taxi,
                app_hoby_special_drive, app_hoby_high_bus, app_hoby_other_drive, app_hoby_rent_car, app_hoby_young_hotel, app_hoby_home_hotel,
                app_hoby_convert_hotel, app_hoby_bank_unin, app_hoby_third_pay, app_hoby_internet_bank, app_hoby_foreign_bank, app_hoby_middle_bank,
                app_hoby_credit_card, app_hoby_city_bank, app_hoby_state_bank, app_hoby_futures, app_hoby_virtual_currency, app_hoby_forex,
                app_hoby_noble_metal, app_hoby_fund, app_hoby_stock, app_hoby_zonghelicai, app_hoby_car_loan, app_hoby_divide_loan,
                app_hoby_credit_card_loan, app_hoby_cash_loan, app_hoby_house_loan, app_hoby_p2P, app_hoby_loan_platform, app_hoby_sport_lottery,
                app_hoby_welfare_lottery, app_hoby_double_ball, app_hoby_lottery, app_hoby_football_lottery, app_hoby_summary_live,
                app_hoby_short_video, app_hoby_social_live, app_hoby_summary_video, app_hoby_sports_video, app_hoby_game_live,
                app_hoby_self_photo, app_hoby_tv_live, app_hoby_culture_live, app_hoby_show_live, app_hoby_sports_live, app_hoby_read_listen,
                app_hoby_sunmmary_news, app_hoby_women_hel_book, app_hoby_army_news, app_hoby_carton_book, app_hoby_phy_news,
                app_hoby_famouse_book, app_hoby_fincal_news, app_hoby_fun_news, app_hoby_edu_med, app_hoby_kongfu, app_hoby_tech_news,
                app_hoby_look_for_med, app_hoby_encourage_book, app_hoby_car_info_news, app_hoby_humerious, app_hoby_cards_game,
                app_hoby_speed_game, app_hoby_role_game, app_hoby_net_game, app_hoby_relax_game, app_hoby_kongfu_game, app_hoby_game_video,
                app_hoby_tale_game, app_hoby_diamonds_game, app_hoby_tragedy_game, app_hoby_outdoor, app_hoby_movie, app_hoby_carton,
                app_hoby_beautiful, app_hoby_lose_weight, app_hoby_phy_book, app_hoby_fresh_shopping, app_hoby_wifi, app_hoby_car_pro,
                app_hoby_life_pay, app_hoby_pet_market, app_hoby_out_food, app_hoby_food, app_hoby_palm_market, app_hoby_women_heal,
                app_hoby_record, app_hoby_conceive, app_hoby_share, app_hoby_cook_book, app_hoby_buy_rent_house, app_hoby_chinese_medicine,
                app_hoby_job, app_hoby_home_service, app_hoby_krayok, app_hoby_fast_send, app_hoby_people_resouse, app_hoby_mama_social,
                app_hoby_hot_social, app_hoby_marry_social, app_hoby_campus_social, app_hoby_lovers_social, app_hoby_ecy, app_hoby_stranger_social,
                app_hoby_anonymous_social, app_hoby_city_social, app_hoby_fans, app_hoby_fin, app_hoby_middle, app_hoby_it, app_hoby_primary, app_hoby_baby, app_hoby_online_study,
                app_hoby_foreign, app_hoby_drive, app_hoby_servants, app_hoby_child_edu, app_hoby_university, app_hoby_car_shopping,
                app_hoby_secondhand_shopping, app_hoby_zonghe_shopping, app_hoby_payback, app_hoby_discount_market, app_hoby_baby_shopping, app_hoby_women_shopping,
                app_hoby_rebate_shopping, app_hoby_group_buy, app_hoby_global_shopping, app_hoby_shopping_guide, app_hoby_sex_shopping,
                app_hoby_smote_office, car_driving_viewScore, car_driving_viewDateScore, car_driving_installScore, car_purchase_viewScore,
                car_purchase_viewDateScore, car_purchase_installScore, car_rent_viewScore, car_rent_viewDateScore, car_rent_installScore,
                car_brand_viewScore, car_brand_viewDateScore, car_brand_installScore, car_serve_viewScore, car_serve_viewDateScore,
                car_serve_installScore, car_loan_viewScore, car_loan_viewDateScore, car_loan_installScore, car_illegal_viewScore,
                car_illegal_viewDateScore, car_illegal_installScore, car_beauty_locViewDateScore, car_beauty_locViewScore, car_check_locViewDateScore,
                car_check_locViewScore, car_part_locViewDateScore, car_part_locViewScore, car_rental_locViewDateScore, car_rental_locViewScore,
                car_repair_locViewDateScore, car_repair_locViewScore, car_sale_locViewDateScore, car_sale_locViewScore, car_service_locViewDateScore,
                car_service_locviewscore, all_home_work_locdistancescore, all_trip_locviewdatescore, all_trip_locviewscore, edu_driving_locviewdatescore,
                edu_driving_locviewscore, edu_pregnancy_viewscore, edu_pregnancy_viewdatescore, edu_pregnancy_installScore,
                edu_pre_child_viewScore, edu_pre_child_viewDateScore, edu_pre_child_installscore, edu_child_viewscore, edu_child_viewdatescore,
                edu_child_installscore, edu_primary_viewscore, edu_primary_viewdatescore, edu_primary_installscore, edu_primary_middle_viewscore,
                edu_primary_middle_viewdatescore, edu_primary_middle_installscore, edu_junior_viewscore, edu_junior_viewdatescore, edu_junior_installScore,
                edu_middle_viewScore, edu_middle_viewDateScore, edu_middle_installScore, edu_senior_viewScore, edu_senior_viewDateScore,
                edu_senior_installScore, edu_college_viewScore, edu_college_viewDateScore, edu_college_installScore, edu_postgraduate_viewScore,
                edu_postgraduate_viewDateScore, edu_postgraduate_installScore, edu_online_viewScore, edu_online_viewDateScore, edu_online_installScore,
                edu_officeholder_viewScore, edu_officeholder_viewDateScore, edu_officeholder_installScore, edu_language_viewScore,
                edu_language_viewDateScore, edu_language_installScore, edu_gourmand_viewScore, edu_gourmand_viewDateScore, edu_gourmand_installScore,
                edu_body_building_viewScore, edu_body_building_viewDateScore, edu_body_building_installScore, edu_art_viewScore,
                edu_art_viewDateScore, edu_art_installScore, edu_adult_viewScore, edu_adult_viewDateScore, edu_adult_installScore,
                edu_abroad_viewScore, edu_abroad_viewDateScore, edu_abroad_installScore, edu_child_locViewDateScore, edu_child_locViewScore,
                edu_kindergarden_locViewDateScore, edu_kindergarden_locViewScore, edu_primary_locViewDateScore, edu_primary_locViewScore,
                edu_junior_locViewDateScore, edu_junior_locViewScore, edu_middle_locViewDateScore, edu_middle_locViewScore,
                edu_senior_locViewDateScore, edu_senior_locViewScore, edu_colleage_locViewDateScore, edu_colleage_locViewScore,
                edu_abroad_locViewDateScore, edu_abroad_locViewScore, edu_adult_locViewDateScore, edu_adult_locViewScore,
                edu_art_locViewDateScore, edu_art_locViewScore, edu_body_building_locViewDateScore, edu_body_building_locViewScore,
                edu_gourmand_locViewDateScore, edu_gourmand_locViewScore, edu_language_locViewDateScore, edu_language_locViewScore,
                edu_officeholder_locViewDateScore, edu_officeholder_locViewScore, edu_postgraduate_locViewDateScore, edu_postgraduate_locViewScore,
                house_rent_viewScore, house_rent_viewDateScore, house_rent_installScore, house_combine_viewScore, house_combine_viewDateScore,
                house_combine_installScore, house_newHouse_locViewDateScore, house_newhouse_locviewscore, travel_homestay_viewscore,
                travel_homestay_viewdatescore, travel_homestay_installscore, travel_comprehensive_guide_viewscore, travel_comprehensive_guide_viewdatescore,
                travel_comprehensive_guide_installscore, travel_budget_hotel_viewscore, travel_budget_hotel_viewdatescore,
                travel_budget_hotel_installscore, travel_booking_hotel_viewscore, travel_booking_hotel_viewdatescore, travel_booking_hotel_installscore,
                travel_travel_agency_locviewdatescore, travel_travel_agency_locviewscore, travel_travel_locviewdatescore, travel_travel_locviewscore);
        return list;
    }

    private static String getValue(String key, JSONObject data) {
        String result = data.getString(key);
        if (StringUtils.isBlank(result)) {
            return StringUtils.EMPTY;
        }
        return result;
    }

    private static String getJsonArray(JSONObject data, String key) {
        JSONArray jsonArray = data.getJSONArray(key);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (jsonArray != null && !jsonArray.isEmpty()) {
            for (int i = 0; i < jsonArray.size(); i++) {
                Object value = jsonArray.get(i);
                if (value instanceof JSONObject) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject != null) {
                        sb.append(getJsonObject(jsonObject));
                    }
                } else {
                    sb.append(jsonArray.getString(i));
                }
                sb.append(",");
            }
        }
        if (!"[".equals(sb.toString())) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private static String getJsonObject(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((key, value) -> {sb.append(value).append("-");});
        if (StringUtils.isNotBlank(sb.toString())) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String getJsonObject(JSONObject data, String key) {
        JSONObject jsonObject = data.getJSONObject(key);
        if (jsonObject == null || jsonObject.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((k, v) -> {sb.append(v).append("-");});
        if (StringUtils.isNotBlank(sb.toString())) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
