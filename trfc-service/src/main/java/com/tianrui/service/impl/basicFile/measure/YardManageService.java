package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.basicFile.measure.YardManageSave;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.YardManageResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 堆场管理Service
 * 
 * @author YangZhenFu
 * @createtime 2017年3月4日 上午9:11:52
 * @classname YardManageService.java
 */
@Service
public class YardManageService implements IYardManageService{
	
	@Autowired
	private YardManageMapper yardManageMapper;
    @Autowired
    private ISystemCodeService systemCodeService;
	
	/**
	 * 分页查询数据
	 */
	@Override
	public Result page(YardManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null){
			PaginationVO<YardManageResp> page = new PaginationVO<YardManageResp>();
			query.setState("1");
			long count=yardManageMapper.findYardPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<YardManage> list=yardManageMapper.findYardPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				//成功时保存数据
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
		}
		return result;
	}

	
	/**
	 * 新增堆场管理
	 */
	@Override
	public Result addYard(YardManageSave save) throws Exception {
		Result result=Result.getParamErrorResult();
		if(save!=null){
			YardManage bean=new YardManage();
			bean.setName(save.getName());
			bean.setState(Constant.ONE_STRING);
			List<YardManage> list=yardManageMapper.selectSelective(bean);
			//有的话提示错误信息，没有则执行新增
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(bean, save);
				bean.setId(UUIDUtil.getId());
				bean.setCode(getCode(save.getCurrId()));
				bean.setCreator(save.getCurrId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(save.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				if(yardManageMapper.insertSelective(bean) == 1
				        && updateCode(save.getCurrId())){
					//插入成功时保存数据
					result.setData(bean);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
			
		}
		return result;
	}
    
    private boolean updateCode(String userId) throws Exception {
        boolean flag = false;
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("DC");
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
            flag = true; 
        }
        return flag;
    }
    
    private String getCode(String userId) throws Exception {
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("DC");
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        return systemCodeService.getCode(codeReq).getData().toString();
    }
    
	/**
	 * 修改堆场管理信息
	 */
	@Override
	public Result updateYard(YardManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		//参数不能为空校验
		if(save != null){
			YardManage yard = new YardManage();
			PropertyUtils.copyProperties(yard, save);
			yard.setModifier(save.getCurrId());
			yard.setModifytime(System.currentTimeMillis());
			//执行修改方法，成功时提示信息
			if(yardManageMapper.updateByPrimaryKeySelective(yard) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 删除堆场管理信息(假删除)
	 */
	@Override
	public Result deleteYard(YardManageQuery query) {
		Result result=Result.getParamErrorResult();
		//参数不能为空校验
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			YardManage yard=new YardManage();
			yard.setId(query.getId());
			//修改状态为0，执行假删除
			yard.setState("0");
			yard.setModifier(query.getUser());
			yard.setModifytime(System.currentTimeMillis());
			//修改成功时提示信息
			if(yardManageMapper.updateByPrimaryKeySelective(yard)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public List<YardManageResp> autoCompleteSearch(String likeName) throws Exception {
		return copyBeanList2RespList(yardManageMapper.autoCompleteSearch(likeName));
	}
	
	/**
	 * 集合转换
	 * @param List<YardManage> list
	 * @return List<YardManageResp> 
	 * @throws Exception
	 */
	private List<YardManageResp> copyBeanList2RespList(List<YardManage> list) throws Exception {
		List<YardManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<YardManageResp>();
			for(YardManage yard : list){
				listResp.add(copyBeanList2RespList(yard));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param YardManage bean
	 * @return YardManageResp
	 * @throws Exception
	 */
	private YardManageResp copyBeanList2RespList(YardManage bean) throws Exception {
		YardManageResp resp = null;
		if(bean != null){
			resp = new YardManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}


	@Override
	public List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req) {
		List<HandSetReturnResp> list = yardManageMapper.handSetQueryAll(req);
		return list;
	}
}
