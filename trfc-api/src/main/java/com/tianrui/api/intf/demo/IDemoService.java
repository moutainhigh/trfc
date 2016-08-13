package com.tianrui.api.intf.demo;

import com.tianrui.api.req.demo.DemoReq;
import com.tianrui.api.resp.demo.DemoResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 
  * @ClassName: IDemoService 
  * @Description: TODO
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年8月13日 下午3:53:22 
  *
 */
public interface IDemoService  {
	
	public PaginationVO<DemoResp> page(DemoReq req)throws Exception;
	
	
	public Result saveDemo(DemoReq req)throws Exception;
	
	public Result delDemo(DemoReq req)throws Exception;
}
