package com.tianrui.test.main;

import com.tianrui.smartfactory.common.utils.Md5Utils;

public class TestMd5 {
	public static void main(String[] args) {
		String a="{\"body\":[{\"code\":\"DTW\",\"createTime\":1512462960591,\"id\":\"0001PP1000000000BSIU\",\"innerCode\":\"\",\"isPush\":0,\"name\":\"直运仓\",\"orgId\":\"0001PP1000000000BSIU\",\"remark\":\"\",\"shortName\":\"\",\"status\":0,\"ts\":1481332758000}],\"head\":{\"callSource\":\"datacenter\",\"callTime\":\"2017-12-06 17:46:35\",\"callType\":\"dc\",\"key\":\"b26e2161b31453d899361d9b45c50198\",\"userId\":\"dc\"},\"sign\":\"trapi20170107\"}";
		System.out.println(Md5Utils.MD5(a));
		System.out.println("7d7e8fd27842817aa8de29a3805c5e51");
	}
}
