package com.automated.learntest.data;

public class Responseinfo {

    /*成功返回状态*/
    private String SUCCESS_CODE = "0";
    /*失败返回状态*/
    private String FAIL_CODE = "1";

    private String ACCOUNT_SUCCESS="success";



    private String ACCOUNT_NO_FOUND="输入的账号不存在，请重新输入！";

    private String ACCOUNT_FOUNDED="输入的账号已存在，请重新输入！";

    private String ACCOUNT_DELERROR="删除账号失败！";

    private String ACCOUNT_ERROR="输入的账号密码不正确，请重新输入！";

    private String EDITACCOUNT_NEWOLD_NOTSAME="新密码和确认密码输入不一致，请重新输入！";

    private String EDITACCOUNT_NEWOLD_SAME="新密码与旧密码相同，请重新输入";

    private String EDITACCOUNT_ERROR="修改密码失败";

    private String SESSION_TIMEOUT="会话已失效,请重新登陆!";

    private String MISSING_PARAMETER="缺失必填参数";

    private String NOT_ADMIN="非管理员账户不允许修改账户信息！";

    public String getSUCCESS_CODE(){
        return SUCCESS_CODE;
    }

    public void setSUCCESS_CODE(String SUCCESS_CODE){
        this.SUCCESS_CODE=SUCCESS_CODE;
    }

    public String getFAIL_CODE(){
        return FAIL_CODE;
    }

    public void setFAIL_CODE(String FAIL_CODE){
        this.FAIL_CODE=FAIL_CODE;
    }

    public String getACCOUNT_SUCCESS(){
        return ACCOUNT_SUCCESS;
    }

    public void setACCOUNT_SUCCESS(String ACCOUNT_SUCCESS){
        this.ACCOUNT_SUCCESS=ACCOUNT_SUCCESS;
    }


    public String getACCOUNT_NO_FOUND(){
        return ACCOUNT_NO_FOUND;
    }

    public void setACCOUNT_NO_FOUND(String ACCOUNT_NO_FOUND){
        this.ACCOUNT_NO_FOUND=ACCOUNT_NO_FOUND;
    }

    public String getACCOUNT__FOUNDED(){
        return ACCOUNT_FOUNDED;
    }

    public void setACCOUNT_FOUNDED(String ACCOUNT_FOUNDED){
        this.ACCOUNT_FOUNDED=ACCOUNT_FOUNDED;
    }

    public String getACCOUNT_DELERROR(){
        return ACCOUNT_DELERROR;
    }

    public void setACCOUNT_DELERROR(String ACCOUNT_DELERROR){
        this.ACCOUNT_DELERROR=ACCOUNT_DELERROR;
    }

    public String getACCOUNT_ERROR(){
        return ACCOUNT_ERROR;
    }

    public void setACCOUNT_ERROR(String ACCOUNT_ERROR){
        this.ACCOUNT_ERROR=ACCOUNT_ERROR;
    }

    public String getEDITACCOUNT_NEWOLD_NOTSAME(){
        return EDITACCOUNT_NEWOLD_NOTSAME;
    }

    public void setEDITACCOUNT_NEWOLD_NOTSAME(String EDITACCOUNT_NEWOLD_NOTSAME){
        this.EDITACCOUNT_NEWOLD_NOTSAME=EDITACCOUNT_NEWOLD_NOTSAME;
    }

    public String getEDITACCOUNT_NEWOLD_SAME(){
        return EDITACCOUNT_NEWOLD_SAME;
    }

    public void setEDITACCOUNT_NEWOLD_SAME(String EDITACCOUNT_NEWOLD_SAME){
        this.EDITACCOUNT_NEWOLD_SAME=EDITACCOUNT_NEWOLD_SAME;
    }

    public String getEDITACCOUNT_ERROR(){
        return EDITACCOUNT_ERROR;
    }

    public void setEDITACCOUNT_ERROR(String EDITACCOUNT_ERROR){
        this.EDITACCOUNT_ERROR=EDITACCOUNT_ERROR;
    }

    public String getSESSION_TIMEOUT(){
        return SESSION_TIMEOUT;
    }

    public void setSESSION_TIMEOUT(String SESSION_TIMEOUT){
        this.SESSION_TIMEOUT=SESSION_TIMEOUT;
    }

    public String getMISSING_PARAMETER(){
        return MISSING_PARAMETER;
    }

    public void setMISSING_PARAMETER(String MISSING_PARAMETER){
        this.MISSING_PARAMETER=MISSING_PARAMETER;
    }

    public String getNOT_ADMIN(){
        return NOT_ADMIN;
    }

    public void setNOT_ADMIN(String NOT_ADMIN){
        this.NOT_ADMIN=NOT_ADMIN;
    }

}
