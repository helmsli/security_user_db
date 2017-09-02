package com.company.security.Const;

public class SecurityUserConst {
	
	//范围1000 到2000
	//成功
    public static final int RESULT_SUCCESS = 0;
    //role不存在
    public static final int RESULT_Error_startCode = 1000;
    public static final int RESULT_Error_roleNotExist = RESULT_Error_startCode+1;
    
    
    public static final int RESULT_Error_PhoneExist = RESULT_Error_startCode+2;
    public static final int RESULT_Error_PhoneError = RESULT_Error_startCode+3;
    
    public static final int RESULT_Error_DbError = RESULT_Error_startCode+5;
    
    public static final int RESULT_Error_PhoneNotVerified = RESULT_Error_startCode+6;
    
}
