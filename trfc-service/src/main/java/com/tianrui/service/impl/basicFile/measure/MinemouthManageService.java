package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IMinemouthManageService;
import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.api.resp.basicFile.measure.MinemouthManageResp;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;
import com.tianrui.service.mapper.basicFile.measure.MinemouthManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 矿口管理Service
 * 
 * @author YangZhenFu
 * @createtime 2017年2月7日 上午10:51:52
 * @classname MinemouthManageService.java
 */
@Service
public class MinemouthManageService implements IMinemouthManageService{

	@Autowired
	private MinemouthManageMapper minemouthManageMapper;
	
	/**
	 * 分页查询数据
	 */
	@Override
	public Result page(MinemouthManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(query != null){
			PaginationVO<MinemouthManageResp> page = new PaginationVO<MinemouthManageResp>();
			//设置状态为1，查询数据总数
			query.setState("1");
			long count = minemouthManageMapper.findMinemouthPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				//数据总数大于0时执行分页查询
				List<MinemouthManage> list = minemouthManageMapper.findMinemouthPage(query);
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
	 * 新增矿口管理信息
	 */
	@Transactional
	@Override
	public Result addMinemouth(MinemouthManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		//参数不能为空校验
		if(save != null){
			MinemouthManage minemouth = new MinemouthManage();
			minemouth.setCode(save.getCode());
			minemouth.setName(save.getName());
			minemouth.setState("1");
			//查询数据库里是否有这条数据
			List<MinemouthManage> list = minemouthManageMapper.selectSelective(minemouth);
			//有的话提示错误信息，没有则执行新增
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(minemouth, save);
				minemouth.setId(UUIDUtil.getId());
				minemouth.setCreator("YZF");
				minemouth.setCreatetime(System.currentTimeMillis());
				minemouth.setModifier("YZF");
				minemouth.setModifytime(System.currentTimeMillis());
				if(minemouthManageMapper.insertSelective(minemouth) > 0){
					//插入成功时保存数据
					result.setData(minemouth);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	/**
	 * 修改矿口管理信息
	 */
	@Transactional
	@Override
	public Result updateMinemouth(MinemouthManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		//参数不能为空校验
		if(save != null){
			MinemouthManage minemouth = new MinemouthManage();
			PropertyUtils.copyProperties(minemouth, save);
			save.setModifier("YZF");
			save.setModifytime(System.currentTimeMillis());
			//执行修改方法，成功时提示信息
			if(minemouthManageMapper.updateByPrimaryKeySelective(minemouth) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 删除矿口管理信息
	 */
	@Transactional
	@Override
	public Result deleteMinemouth(MinemouthManageQuery query) {
		Result result=Result.getParamErrorResult();
		//参数不能为空校验
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			MinemouthManage minemouth=new MinemouthManage();
			minemouth.setId(query.getId());
			//修改状态为0，执行假删除
			minemouth.setState("0");
			//修改成功时提示信息
			if(minemouthManageMapper.updateByPrimaryKeySelective(minemouth)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public List<MinemouthManageResp> autoCompleteSearch(String likeName) throws Exception {
		return copyBeanList2RespList(minemouthManageMapper.autoCompleteSearch(likeName));
	}
	
	/**
	 * 集合转换
	 * @param List<MinemouthManage> list
	 * @return List<MinemouthManageResp> 
	 * @throws Exception
	 */
	private List<MinemouthManageResp> copyBeanList2RespList(List<MinemouthManage> list) throws Exception {
		List<MinemouthManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<MinemouthManageResp>();
			for(MinemouthManage minemouth : list){
				listResp.add(copyBean2Resp(minemouth));
			}
		}
		return listResp;
	}
	
	/**
	 * 实体bean类型转换
	 * @param MinemouthManage bean
	 * @return MinemouthManageResp
	 * @throws Exception
	 */
	private MinemouthManageResp copyBean2Resp(MinemouthManage bean) throws Exception {
		MinemouthManageResp resp = null;
		if(bean != null){
			resp = new MinemouthManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
