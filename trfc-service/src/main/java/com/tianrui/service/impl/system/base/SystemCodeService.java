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

	@Transactional
	@Override
	public Result insert(SystemCodeReq req) {
		//参数为空的情况
		Result result = Result.getParamErrorResult();
		//参数不为空的情况
		if(req!=null){
			SystemCode code = new SystemCode();
			try {
				PropertyUtils.copyProperties(code, req);
			} catch (Exception e) {
				e.printStackTrace();
				//抓取异常
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			//生成ID
			code.setId(UUIDUtil.getId());
			//假设一个创建者/修改者   (实际:用userId获取)
			code.setCreator("LXY");
			code.setCreatetime(System.currentTimeMillis());
			code.setModifier("LXY");
			code.setModifytime(System.currentTimeMillis());
			//新增数据
			int index = systemCodeMapper.insertSelective(code);
			//判断小于0的情况
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}
	@Transactional
	@Override
	public Result delete(SystemCodeReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			String id = req.getId();
			//删除数据
			int index = systemCodeMapper.deleteByPrimaryKey(id);
			//判断小于0的情况
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}
	@Transactional
	@Override
	public Result update(SystemCodeReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null){
			SystemCode code = new SystemCode();
			try {
				PropertyUtils.copyProperties(code, req);
			} catch (Exception e) {
				e.printStackTrace();
				//抓取异常
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			int index = systemCodeMapper.updateByPrimaryKeySelective(code);
			//判断小于0的情况
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}

	@Override
	public Result select(SystemCodeReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null){
			List<SystemCode> codes = systemCodeMapper.selectByReq(req);
			//codes -->  resps   转换为 resp类型的集合
			List<SystemCodeResp> resps = new ArrayList<SystemCodeResp>();
			try{
				for(SystemCode code : codes){
					SystemCodeResp resp = new SystemCodeResp();
					PropertyUtils.copyProperties(resp, code);
					resps.add(resp);
				}
			}catch(Exception e){
				e.printStackTrace();
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result=Result.getSuccessResult();
			result.setData(resps);
		}
		return result;
	}
	@Transactional
	public Result getCode(SystemCodeReq req){
		Result result = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			req.setTypeValue(req.getCodeType()+"");
			//通过id,typeValue获取item对象,判断item对象是否存在
			SystemCodeItem item = systemCodeItemMapper.selectByReq(req);
			if(item==null){
				//初始化
				int index = initCodeItem(req);
				if(index<=0){
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
					return result;
				}
				item = systemCodeItemMapper.selectByReq(req);
			}
			//假设一个修改者(可通过req.userId获取)
			item.setModifier("LXY");
			item.setModifytime(System.currentTimeMillis());
			String code = "";
			try {
				//确保判断的时间 与生成code是的时间一致
				Date now = new Date(System.currentTimeMillis());
				code = codeFactory(req, item,now);
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(code);

		}
		return result;
	}
	/**
	 * 初始化CodeItem
	 */
	public int initCodeItem(SystemCodeReq req){
		SystemCodeItem sci = new SystemCodeItem();
		sci.setId(UUIDUtil.getId());
		sci.setCodeId(req.getId());
		sci.setTypeValue(req.getCodeType()+"");
		sci.setItemType(req.getItemType());
		//假设一个创建者和创建者
		sci.setCreator("LXY");
		sci.setCreatetime(System.currentTimeMillis());
		sci.setModifier("LXY");
		sci.setModifytime(System.currentTimeMillis());
		sci.setDigit("1");
		int index = systemCodeItemMapper.insertSelective(sci);
		return index;
	}
	/**
	 * 刷新计数
	 */
	public void updateCodeItem(SystemCodeItem item){ 
		item.setDigit(Integer.parseInt(item.getDigit())+1+"");
		systemCodeItemMapper.updateByPrimaryKeySelective(item);
	}
	/**
	 * 获取计数
	 */
	public String gainDigit(SystemCodeReq req,SystemCodeItem item,Date now){
		Date modifyTime = new Date(item.getModifytime());
		Calendar old = Calendar.getInstance();old.setTime(modifyTime);
		Calendar present = Calendar.getInstance();present.setTime(now);
		//判断编码方式
		if(req.getCodeType()!=null){
			switch(req.getCodeType()){
			case 3:
				if(old.get(Calendar.DATE)!=present.get(Calendar.DATE)){
					//重新计数
					item.setDigit("1");
					updateCodeItem(item);
					return "1";
				}
			case 2:
				if(old.get(Calendar.MONTH)!=present.get(Calendar.MONTH)){
					//重新计数
					item.setDigit("1");
					updateCodeItem(item);
					return "1";
				}
			case 1:
				if(old.get(Calendar.YEAR)!=present.get(Calendar.YEAR)){
					//重新计数
					item.setDigit("1");
					updateCodeItem(item);
					return "1";
				}
			}

		}

		String digit = item.getDigit();
		updateCodeItem(item);
		return digit;

	}
	/**
	 * 编号加工
	 */
	public String codeFactory(SystemCodeReq req,SystemCodeItem item,Date now) throws Exception{
		StringBuffer code =new StringBuffer();
		code.append(req.getCode());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String year = c.get(Calendar.YEAR)+"";
		String month = c.get(Calendar.MONTH)+1+"";
		String day = c.get(Calendar.DAY_OF_MONTH)+"";
		//拼接年月日
		if(req.getCodeType()!=null){
			switch(req.getCodeType()){
			case 3:
				code.append(year);code.append(month);code.append(day);break;
			case 2:
				code.append(year);code.append(month);break;
			case 1:
				code.append(year);
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
		String digit = gainDigit(req, item, now);
		//拼接内码/编码
		if(req.getItemType()!=null){
			if(req.getItemType()){
				switch(req.getCodeBegin()){
				case 0 :
					code.append(joint("01",digit));break;
				case 1 :
					code.append(joint("001",digit));break;
				case 2 :
					code.append(joint("0001",digit));break;
				case 3 :
					code.append(joint("00001",digit));break;
				case 4 :
					code.append(joint("000001",digit));break;
				case 5 :
					code.append(joint("0000001",digit));break;
				case 6 :
					code.append(joint("00000001",digit));
				}
			}else{
				switch(req.getInnerCodeBegin()){
				case 0 :
					code.append(joint("01",digit));break;
				case 1 :
					code.append(joint("001",digit));break;
				case 2 :
					code.append(joint("0001",digit));break;
				case 3 :
					code.append(joint("00001",digit));break;
				case 4 :
					code.append(joint("000001",digit));break;
				case 5 :
					code.append(joint("0000001",digit));break;
				case 6 :
					code.append(joint("00000001",digit));
				}
			}
		}
		return code.toString();
	}
	/**
	 * 字符串拼接
	 */
	private String joint(String str,String digit) throws Exception{
		if(str.length()<digit.length()){
			throw new Exception("越界");
		}
		StringBuilder sb = new StringBuilder(str);
		sb.replace(str.length()-digit.length(),str.length(), digit);
		return sb.toString();
	}
	/**
	 * 检测单据代号是否重复
	 */
	@Override
	public Result checkCode(SystemCodeReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getCode())){
			List<SystemCode> list = systemCodeMapper.selectByReq(req);
			result = Result.getSuccessResult();
			if(list==null || list.size()<=0){
				result.setData(true);
			}else{
				result.setData(false);
			}
		}
		return result;
	}

}
