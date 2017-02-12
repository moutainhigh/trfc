package com.tianrui.service.impl.system.base;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.system.base.SystemCodeReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.system.base.SystemCodeResp;
import com.tianrui.service.bean.system.base.SystemCode;
import com.tianrui.service.bean.system.base.SystemCodeItem;
import com.tianrui.service.mapper.system.base.SystemCodeItemMapper;
import com.tianrui.service.mapper.system.base.SystemCodeMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemCodeService implements ISystemCodeService{
	@Autowired
	private SystemCodeMapper systemCodeMapper;
	@Autowired
	private SystemCodeItemMapper systemCodeItemMapper;
	/**
	 * 新增数据
	 */
	@Transactional
	@Override
	public Result insert(SystemCodeReq req) throws Exception{
		Result result = Result.getParamErrorResult();
		//判断参数是不是null
		if(req!=null){
			SystemCode code = new SystemCode();

			//req-->code 类型转换
			PropertyUtils.copyProperties(code, req);
			//生成ID
			code.setId(UUIDUtil.getId());

			code.setCreator(req.getUserName());
			code.setCreatetime(System.currentTimeMillis());
			code.setModifier(req.getUserName());
			code.setModifytime(System.currentTimeMillis());
			//新增数据
			int index = systemCodeMapper.insertSelective(code);
			//判断小于0的情况
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}else{
				//操作成功
				result = Result.getSuccessResult();
				result.setData(index>0);
			}
		}
		return result;
	}
	/**
	 * 删除数据
	 */
	@Transactional
	@Override
	public Result delete(SystemCodeReq req) throws Exception{
		Result result = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			String id = req.getId();
			//删除数据
			int index1 = systemCodeMapper.deleteByPrimaryKey(id);
			//删除子表数据
			systemCodeItemMapper.deleteByCodeId(id);
			//判断小于0的情况
			if(index1<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}else{
				//操作成功
				result = Result.getSuccessResult();
				result.setData(index1>0);
			}
		}
		return result;
	}
	/**
	 * 更新数据
	 */
	@Transactional
	@Override
	public Result update(SystemCodeReq req) throws Exception{
		Result result = Result.getParamErrorResult();
		//参数不能为空 并且id不能为空
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			SystemCode code = new SystemCode();
			//转换类型
			PropertyUtils.copyProperties(code, req);
			code.setModifier(req.getUserName());
			code.setModifytime(System.currentTimeMillis());
			//更新数据
			int index = systemCodeMapper.updateByPrimaryKeySelective(code);
			//判断小于0的情况
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}else{
				//操作成功
				result = Result.getSuccessResult();
				result.setData(index>0);
			}
		}
		return result;
	}
	/**
	 * 查询数据
	 */
	@Override
	public Result select(SystemCodeReq req) throws Exception{
		Result result = Result.getParamErrorResult();
		if(req!=null){
			//设置排序
			req.setOrdering("createtime");
			req.setSorting("desc");
			//获取数据
			List<SystemCode> codes = systemCodeMapper.selectByReq(req);
			//集合codes不能为空
			if(codes!=null){
				//codes -->  resps   转换为 resp类型的集合
				List<SystemCodeResp> resps = new ArrayList<SystemCodeResp>();
				for(SystemCode code : codes){
					SystemCodeResp resp = new SystemCodeResp();
					//类型转换
					PropertyUtils.copyProperties(resp, code);
					resps.add(resp);
				}
				//操作成功
				result=Result.getSuccessResult();
				result.setData(resps);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	/**
	 * 获取编号
	 */
	@Transactional
	public Result getCode(GetCodeReq codeReq) throws Exception{
		Result result = Result.getParamErrorResult();
		if(codeReq!=null){
			//通过code 获取 systemcode 对象
			SystemCode sc = systemCodeMapper.selectByCode(codeReq.getCode());
			if(sc!=null){
				//转换为req类型
				SystemCodeReq  req = new SystemCodeReq();
				PropertyUtils.copyProperties(req, sc);
				req.setUserName(codeReq.getUserid());
				req.setItemType(codeReq.getCodeType());
				//赋值给TypeValue
				req.setTypeValue(req.getCodeType()+"");
				//通过id,typeValue获取item对象,判断item对象是否存在
				SystemCodeItem item = systemCodeItemMapper.selectByReq(req);
				//如果item对象为空,则进行初始化
				if(item==null){
					//初始化并重新获取数据
					item = initCodeItem(req);
				}
				if(item != null){
					//确保判断的时间 与生成code是的时间一致
					Date now = new Date(System.currentTimeMillis());

					item.setModifier(req.getUserName());
					item.setModifytime(now.getTime());
					StringBuilder code = codeFactory(req, item,now);
					//code不能为空
					if(code!=null){
						//操作成功
						result = Result.getSuccessResult();
						result.setData(code.toString());
					}else{
						result = Result.getErrorResult();
					}
				}

			}
		}
		return result;
	}
	/**
	 * 初始化CodeItem
	 * @throws Exception 
	 */
	public SystemCodeItem initCodeItem(SystemCodeReq req) throws Exception{
		SystemCodeItem sci = new SystemCodeItem();
		sci.setId(UUIDUtil.getId());
		sci.setCodeId(req.getId());
		sci.setTypeValue(req.getCodeType()+"");
		sci.setItemType(req.getItemType());

		sci.setCreator(req.getUserName());
		sci.setCreatetime(System.currentTimeMillis());
		sci.setModifier(req.getUserName());
		sci.setModifytime(System.currentTimeMillis());
		sci.setDigit("1");
		//数据库新增失败的情况
		if(systemCodeItemMapper.insertSelective(sci)<=0){
			sci = null;
		}
		return sci;
	}
	/**
	 * 刷新计数
	 * @throws Exception 
	 */
	public Result updateCodeItem(GetCodeReq codeReq) throws Exception{ 
		Result rs = Result.getParamErrorResult();
		if(codeReq!=null){
			//通过code 获取 systemcode 对象
			SystemCode sc = systemCodeMapper.selectByCode(codeReq.getCode());
			if(sc!=null){
				//转换为req类型
				SystemCodeReq  req = new SystemCodeReq();
				PropertyUtils.copyProperties(req, sc);
				req.setUserName(codeReq.getUserid());
				req.setItemType(codeReq.getCodeType());
				//赋值给TypeValue
				req.setTypeValue(req.getCodeType()+"");
				//通过id,typeValue获取item对象,判断item对象是否存在
				SystemCodeItem item = systemCodeItemMapper.selectByReq(req);
				//设置修改者和时间
				item.setModifier(req.getUserName());
				item.setModifytime(System.currentTimeMillis());
				//在原来的值上+1
				item.setDigit(Integer.parseInt(item.getDigit())+1+"");
				//更新数据
				int index = systemCodeItemMapper.updateByPrimaryKeySelective(item);
				if(index>0){
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
			return rs;
	}
	/**
	 * 获取计数
	 */
	public String gainDigit(SystemCodeItem item,Date now) throws Exception{
		Date modifyTime = new Date(item.getModifytime());
		//获得上次数据变动的时间
		Calendar old = Calendar.getInstance();old.setTime(modifyTime);
		//获取现在的时间
		Calendar present = Calendar.getInstance();present.setTime(now);
		//判断编码方式
		if(item.getTypeValue()!=null){
			//根据编码方式和时间判断计数是否重置
			switch(Integer.parseInt(item.getTypeValue())){
			// 0:无 1:yyyy 2:yyyyMM 3:yyyyMMdd
			case 3:
				//日期是否相同
				if(old.get(Calendar.DATE)!=present.get(Calendar.DATE)){
					item.setDigit("1");break;
				}
			case 2:
				//月份是否相同
				if(old.get(Calendar.MONTH)!=present.get(Calendar.MONTH)){
					//重新计数
					item.setDigit("1");break;
				}
			case 1:
				//年份是否相同
				if(old.get(Calendar.YEAR)!=present.get(Calendar.YEAR)){
					//重新计数
					item.setDigit("1");
				}
			}
		}

		String digit = item.getDigit();

		return digit;

	}
	/**
	 * 编号加工
	 */
	public StringBuilder codeFactory(SystemCodeReq req,SystemCodeItem item,Date now) throws Exception{
		StringBuilder code =new StringBuilder();
		String digit = gainDigit( item, now);
		//代号,计数,编码类型不能为空
		if(req.getItemType()!=null && digit!=null && req.getCode()!=null && req.getInnerCodeBegin()!=null){
			code.append(req.getCode());
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			String yearStr = String.valueOf(year);
			String monthStr = String.valueOf(month);
			String dayStr = String.valueOf(day);
			if(month < 10){
				monthStr = "0" + month;
			}
			if(day < 10){
				dayStr = "0" + day;
			}
			//拼接年月日
			if(req.getCodeType()!=null){
				switch(req.getCodeType()){
				case 3:
					code.append(yearStr);code.append(monthStr);code.append(dayStr);break;
				case 2:
					code.append(yearStr);code.append(monthStr);break;
				case 1:
					code.append(yearStr);
				}
			}
			//拼接分隔符
			if(req.getSplitType()!=null){
				switch(req.getSplitType()){
				case 1:
					code.append("-");break;
				case 2:
					code.append(".");break;
				}
			}


			//拼接内码/编码
			if(req.getItemType()){
				switch(req.getCodeBegin()){
				case 0 :
					code.append(joint("001",digit));break;
				case 1 :
					code.append(joint("0001",digit));break;
				case 2 :
					code.append(joint("00001",digit));break;
				case 3 :
					code.append(joint("000001",digit));break;
				case 4 :
					code.append(joint("0000001",digit));break;
				case 5 :
					code.append(joint("00000001",digit));break;
				case 6 :
					code.append(joint("000000001",digit));
				}
			}else{
				switch(req.getInnerCodeBegin()){
				case 0 :
					code.append(joint("001",digit));break;
				case 1 :
					code.append(joint("0001",digit));break;
				case 2 :
					code.append(joint("00001",digit));break;
				case 3 :
					code.append(joint("000001",digit));break;
				case 4 :
					code.append(joint("0000001",digit));break;
				case 5 :
					code.append(joint("00000001",digit));break;
				case 6 :
					code.append(joint("000000001",digit));
				}
				//判断是否溢出
				if("-1".equals(code.substring(code.length()-2))){
					code = null;
				}
			}
		}else{
			code = null;
		}
		return code;
	}
	/**
	 * 字符串拼接
	 */
	private StringBuilder joint(String str,String digit) throws Exception{
		StringBuilder sb = null;
		if(str!=null && digit!=null){
			sb = new StringBuilder(str);
			//判断str的长度是否小于计数的长度 
			if(str.length()>=digit.length()){
				//替换
				sb.replace(str.length()-digit.length(),str.length(), digit);
			}else{
				//溢出则返回-1
				sb = new StringBuilder("-1");
			}
		}
		return sb;
	}
	/**
	 * 检测单据代号是否重复
	 */
	@Override
	public Result checkCode(SystemCodeReq req) throws Exception{
		Result result = Result.getParamErrorResult();
		//参数不能为空
		if(req!=null ){
			try{
				List<SystemCode> list = systemCodeMapper.selectByReq(req);
				result = Result.getSuccessResult();
				if(list==null || list.size()<=0){
					//如果查不到数据,返回true
					result.setData(true);
				}else{
					//查到数据,返回false
					result.setData(false);
				}
			}catch(Exception e){
				e.printStackTrace();
				//查询失败
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

}
