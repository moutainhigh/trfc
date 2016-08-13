package com.tianrui.api.intf.common;

import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 
 * @类描述：附件业务接口类
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:13:17
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:13:17
 * @修改备注：
 *
 */
public interface IFileService  {
	
	Result uploadImg(FileUploadReq fileUploadReq)throws Exception;
}
