package com.tianrui.service.impl.system.base;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.system.base.ISystemDataDictService;
import com.tianrui.api.req.system.base.SystemDataDictItemReq;
import com.tianrui.api.req.system.base.SystemDataDictReq;
import com.tianrui.api.resp.system.base.SystemDataDictItemResp;
import com.tianrui.api.resp.system.base.SystemDataDictResp;
import com.tianrui.service.bean.system.base.SystemDataDict;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 系统数据字典Service
 * @author Yangzhenfu
 * @createtime 2017年1月11日 下午2:56:14
 * @classname SystemDataDictService.java
 */
@Service
public class SystemDataDictService implements ISystemDataDictService{
	
	@Autowired
	private SystemDataDictMapper systemDataDictMapper;
	
	@Autowired
	private SystemDataDictItemMapper systemDataDictItemMapper;
	
	/**
	 * 查询所有数据字典类别
	 * @throws Exception 
	 */
	@Override
	public Result findSystemDataDicts(SystemDataDictReq req) throws Exception{
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(req!=null){
			List<SystemDataDictResp> page=new ArrayList<SystemDataDictResp>();
			SystemDataDict dataDict=new SystemDataDict();
			
			//拷贝SystemDataDictReq中的属性到数据字典实体bean中
			PropertyUtils.copyProperties(dataDict, req);
			
			//查询数据
			List<SystemDataDict> list=this.systemDataDictMapper.findSystemDataDicts(req);
			
			//集合转换
			page=copyBeanListRespList(list);
			
			//查询成功时保存数据
			result.setData(page);
		}
		return result;
	}
	
	
	/**
	 * 增加数据字典类别
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result addSystemDataDict(SystemDataDictReq req) throws  Exception {
		Result result = Result.getSuccessResult();
		//参数不能为空校验
		if(req != null){
			SystemDataDict dataDict=new SystemDataDict();
			
			//拷贝SystemDataDictReq中的属性到数据字典实体bean中
			PropertyUtils.copyProperties(dataDict, req);
			
			//生成Id
			dataDict.setId(UUIDUtil.getId());
			//假设一个创建者/修改者   (实际:用userId获取)
			dataDict.setCreator("YZF");
			dataDict.setCreatetime(System.currentTimeMillis());
			dataDict.setModifier("YZF");
			dataDict.setModifytime(System.currentTimeMillis());
			dataDict.setUtc(new Timestamp(System.currentTimeMillis()));
			//插入数据
			int n=this.systemDataDictMapper.insert(dataDict);
			//插入成功时保存数据，失败时抓取异常
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 修改数据字典类别
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result editSystemDataDict(SystemDataDictReq req) throws  Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(req != null){
			SystemDataDict dataDict=new SystemDataDict();
			
			//拷贝SystemDataDictReq中的属性到数据字典实体bean中
			PropertyUtils.copyProperties(dataDict, req);
			
			dataDict.setModifytime(System.currentTimeMillis());
			dataDict.setUtc(new Timestamp(System.currentTimeMillis()));
			//修改数据
			int n=this.systemDataDictMapper.updateByPrimaryKeySelective(dataDict);
			//修改成功时保存数据，失败时抓取异常
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 删除数据字典类别
	 */
	@Transactional
	@Override
	public Result deleteSystemDataDict(String id) {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(id!=null && !id.trim().isEmpty()){
			//是否能查到数据
			SystemDataDict dataDict=this.systemDataDictMapper.selectByPrimaryKey(id);
			if(dataDict!=null){
				//根据Id查询数据字典子表里的数据
				List<SystemDataDictItem> list=this.systemDataDictItemMapper.selectByDictId(id);
				//子表里有其对应的数据则不能被删除，没有则执行删除操作
				if(list.isEmpty()){
					int n=this.systemDataDictMapper.deleteByPrimaryKey(id);
					//删除成功时保存数据
					if(n>0){
						result.setData(n); 	
					}
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 集合类型转换
	 * @param List<SystemDataDict>
	 * @return List<SystemDataDictResp>
	 * @throws Exception
	 */
	private List<SystemDataDictResp> copyBeanListRespList(List<SystemDataDict> list) throws Exception {
		List<SystemDataDictResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SystemDataDictResp>();
			for(SystemDataDict dataDict : list){
				listResp.add(copyBeanResp(dataDict));
			}
		}
		return listResp;
	}


	/**
	 * 实体bean类型转换
	 * @param SystemDataDict
	 * @return SystemDataDictResp
	 * @throws Exception
	 */
	private SystemDataDictResp copyBeanResp(SystemDataDict bean) throws Exception {
		SystemDataDictResp resp = null;
		if(bean != null){
			resp = new SystemDataDictResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}


	/**
	 * 根据数据字典类别id查询所有的数据字典明细
	 * @throws Exception 
	 */
	@Override
	public Result findSystemDataDictItems(SystemDataDictItemReq req) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(req!=null){
			List<SystemDataDictItemResp> page=new ArrayList<SystemDataDictItemResp>();
			SystemDataDictItem dictItem=new SystemDataDictItem();
			
			//拷贝SystemDataDictItemReq中的属性到数据字典子表实体bean中
			PropertyUtils.copyProperties(dictItem, req);
			
			String dictId=dictItem.getDictid();
			//查询数据
			List<SystemDataDictItem> list=this.systemDataDictItemMapper.selectByDictId(dictId);
		
			//集合转换
			page=copyBeanList2RespList(list);
			
			//查询成功时保存数据
			result.setData(page);
		}
		return result;
	}

	/**
	 * 增加数据字典明细
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result addSystemDataDictItem(SystemDataDictItemReq req) throws  Exception {
		Result result = Result.getSuccessResult();
		//参数不能为空校验
		if(req != null){ 
			SystemDataDictItem dictItem=new SystemDataDictItem();
		
			//拷贝SystemDataDictItemReq中的属性到数据字典子表实体bean中
			PropertyUtils.copyProperties(dictItem, req);
			
			//生成Id
			dictItem.setId(UUIDUtil.getId());
			//假设一个创建者/修改者   (实际:用userId获取)
			dictItem.setCreator("YZF");
			dictItem.setCreatetime(System.currentTimeMillis());
			dictItem.setModifier("YZF");
			dictItem.setModifytime(System.currentTimeMillis());
			dictItem.setUtc(new Timestamp(System.currentTimeMillis()));
			//插入数据
			int n=this.systemDataDictItemMapper.insert(dictItem);
			//插入成功时保存数据，失败时抓取异常
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	/**
	 * 修改数据字典明细
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result editSystemDataDictItem(SystemDataDictItemReq req) throws  Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(req != null){
			SystemDataDictItem dictItem=new SystemDataDictItem();
		
			//拷贝SystemDataDictItemReq中的属性到数据字典子表实体bean中
			PropertyUtils.copyProperties(dictItem, req);
			
			dictItem.setModifytime(System.currentTimeMillis());
			dictItem.setUtc(new Timestamp(System.currentTimeMillis()));
			//修改数据
			int n=this.systemDataDictItemMapper.updateByPrimaryKeySelective(dictItem);
			//修改成功时保存数据，失败时抓取异常
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	/**
	 * 删除数据字典明细
	 */
	@Transactional
	@Override
	public Result deleteSystemDataDictItem(String id) {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(id!=null && !id.trim().isEmpty()){
			//此条数据是否存在
			SystemDataDictItem dictItem=this.systemDataDictItemMapper.selectByPrimaryKey(id);
			//数据存在则执行删除，失败则抓取异常
			if(dictItem!=null){
				int n=this.systemDataDictItemMapper.deleteByPrimaryKey(id);
				//删除成功时保存数据，失败时抓取异常
				if(n > 0){
					result.setData(n);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 集合类型转换
	 * @param List<SystemDataDictItem>
	 * @return List<SystemDataDictItemResp>
	 * @throws Exception
	 */
	private List<SystemDataDictItemResp> copyBeanList2RespList(List<SystemDataDictItem> list) throws Exception {
		List<SystemDataDictItemResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SystemDataDictItemResp>();
			for(SystemDataDictItem dataDict : list){
				listResp.add(copyBean2Resp(dataDict));
			}
		}
		return listResp;
	}


	/**
	 * 实体bean类型转换
	 * @param SystemDataDictItem
	 * @return SystemDataDictItemResp
	 * @throws Exception
	 */
	private SystemDataDictItemResp copyBean2Resp(SystemDataDictItem bean) throws Exception {
		SystemDataDictItemResp resp = null;
		if(bean != null){
			resp = new SystemDataDictItemResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
