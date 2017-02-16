package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.file.IQualityItemService;
import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.api.resp.quality.file.QualityItemResp;
import com.tianrui.service.bean.quality.file.QualityItem;
import com.tianrui.service.mapper.quality.file.QualityItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class QualityItemService implements IQualityItemService {

	@Resource
	private QualityItemMapper qualityItemMapper;
	
	@Override
	public Result delete(QualityItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//删除数据
			int index = qualityItemMapper.deleteByPrimaryKey(req.getId());
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(QualityItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			QualityItem qi = new QualityItem();
			PropertyUtils.copyProperties(qi, req);
			//设置id
			qi.setId(UUIDUtil.getId());
			//设置创建者和修改者
			qi.setCreator(req.getUser());
			qi.setCreatetime(System.currentTimeMillis());
			qi.setModifier(req.getUser());
			qi.setModifytime(System.currentTimeMillis());
			qi.setUtc(System.currentTimeMillis());
			//保存数据
			int index = qualityItemMapper.insertSelective(qi);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
			
		}
		return rs;
	}

	@Override
	public Result update(QualityItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			QualityItem qi = new QualityItem();
			PropertyUtils.copyProperties(qi, req);
			//设置修改者
			qi.setModifier(req.getUser());
			qi.setModifytime(System.currentTimeMillis());
			qi.setUtc(System.currentTimeMillis());
			//保存数据
			int index = qualityItemMapper.updateByPrimaryKeySelective(qi);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(QualityItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<QualityItemResp> page = new PaginationVO<QualityItemResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置开始位置,和每页数据量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			//获取数据总数
			int total = qualityItemMapper.count(req);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotal(total);
			System.out.println(total);
			//创建一个出参集合
			List<QualityItemResp> resps = new ArrayList<QualityItemResp>();
			//判断是否有数据可查询
			if(total>0){
				List<QualityItem> list = qualityItemMapper.page(req);
				if(list!=null && !list.isEmpty()){
					//遍历集合,并转换类型
					for(QualityItem c : list){
						QualityItemResp resp = new QualityItemResp();
						PropertyUtils.copyProperties(resp, c);
						//将对象放入集合
						resps.add(resp);
					}
				}
			}
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}

}
