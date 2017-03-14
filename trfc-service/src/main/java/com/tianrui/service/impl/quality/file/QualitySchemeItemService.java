package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.quality.file.IQualitySchemeItemService;
import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.api.resp.quality.file.QualityItemResp;
import com.tianrui.api.resp.quality.file.QualitySchemeItemResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.QualityItem;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.file.QualitySchemeItem;
import com.tianrui.service.bean.quality.sales.AssayReportItem;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.MaterialSchemeMapper;
import com.tianrui.service.mapper.quality.file.QualityItemMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeItemMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class QualitySchemeItemService implements IQualitySchemeItemService {

	@Resource
	private QualitySchemeItemMapper qualitySchemeItemMapper;
	@Resource
	private QualityItemMapper qualityItemMapper;
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	@Resource
	private MaterialSchemeMapper materialSchemeMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private AssayReportItemMapper assayReportItemMapper;


	@Override
	@Transactional
	public Result delete(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			QualitySchemeItem item = new QualitySchemeItem();
			item.setId(req.getId());
			//设置为删除状态
			item.setState("0");
			//更新到数据库
			int index = qualitySchemeItemMapper.updateByPrimaryKeySelective(item);
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
	@Transactional
	public Result deleteBatch(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getSchemeid())){
			//通过schemeid获取数据
			List<QualitySchemeItem> list = qualitySchemeItemMapper.findBySchemeId(req);
			rs = Result.getSuccessResult();
			for(QualitySchemeItem item : list){
				//设置为删除状态
				item.setState("0");
				int index = qualitySchemeItemMapper.updateByPrimaryKeySelective(item);
				//判断是否成功
				if(index<=0){
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	@Transactional
	public Result add(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			QualitySchemeItem qi = new QualitySchemeItem();
			PropertyUtils.copyProperties(qi, req);
			//设置id
			qi.setId(UUIDUtil.getId());
			//设置创建者和修改者
			qi.setStatus("0");
			qi.setCreator(req.getUser());
			qi.setCreatetime(System.currentTimeMillis());
			qi.setModifier(req.getUser());
			qi.setModifytime(System.currentTimeMillis());
			qi.setUtc(System.currentTimeMillis());
			//设置为正常状态
			qi.setState("1");
			//保存数据
			int index = qualitySchemeItemMapper.insertSelective(qi);
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
	@Transactional
	public Result addBatch(QualitySchemeItemReq qsiReq) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(qsiReq!=null){
			List<QualitySchemeItemReq> list = getListReq(qsiReq);
			List<QualitySchemeItem> objs = new ArrayList<QualitySchemeItem>();
			//集合类型转换
			for(QualitySchemeItemReq req : list){
				QualitySchemeItem obj = new QualitySchemeItem();
				//类型转换
				PropertyUtils.copyProperties(obj, req);
				//获取ID
				obj.setId(UUIDUtil.getId());
				//将公共信息赋值给对象
				obj.setInvalid(qsiReq.getInvalid());
				obj.setCreator(qsiReq.getUser());
				obj.setCreatetime(System.currentTimeMillis());
				obj.setModifier(qsiReq.getUser());
				obj.setModifytime(System.currentTimeMillis());
				obj.setUtc(System.currentTimeMillis());
				//设置状态为 未初始化
				obj.setStatus("0");
				//设置为正常状态
				obj.setState("1");
				objs.add(obj);
			}
			//调用批量添加
			int index = qualitySchemeItemMapper.insertBatch(objs);
			if(index==objs.size()){
				//操作成功
				rs = Result.getSuccessResult();
			}else{
				//操作失败
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	/**
	 * 通过req获取List集合
	 */
	public List<QualitySchemeItemReq> getListReq(QualitySchemeItemReq req){
		List<QualitySchemeItemReq> list = new ArrayList<QualitySchemeItemReq>();
		//将字符串转换为json数组
		JSONArray json = JSONArray.parseArray(req.getArrStr());
		//通过循环把数组中的数据添加到list集合中
		if(json!=null && json.size()>0){
			for(int i=0;i<json.size();i++){
				//json对象转换为java对象
				QualitySchemeItemReq s = JSONArray.toJavaObject(json.getJSONObject(i), QualitySchemeItemReq.class);
				s.setSchemeid(req.getSchemeid());
				s.setInvalid(req.getInvalid());
				s.setUser(req.getUser());
				list.add(s);
			}
		}
		return list;
	}


	@Override
	@Transactional
	public Result update(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			QualitySchemeItem batchnum  = new QualitySchemeItem();
			//类型转换
			PropertyUtils.copyProperties(batchnum, req);

			//获取修改者和时间
			batchnum.setModifier(req.getUser());
			batchnum.setModifytime(System.currentTimeMillis());
			batchnum.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = qualitySchemeItemMapper.updateByPrimaryKeySelective(batchnum);
			if(index>0){
				//操作成功
				rs = Result.getSuccessResult();
			}else{
				//操作失败
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result findBySchemeId(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getSchemeid())){
			req.setState("1");
			//通过schemeid获取数据
			List<QualitySchemeItem> list = qualitySchemeItemMapper.findBySchemeId(req);
			//转换为resp集合
			List<QualitySchemeItemResp> resps = new ArrayList<QualitySchemeItemResp>();
			if(list!=null && !list.isEmpty()){
				for(QualitySchemeItem q : list){
					QualitySchemeItemResp resp = new QualitySchemeItemResp();
					PropertyUtils.copyProperties(resp,q);
					//获取物料名称
					QualityScheme qs = qualitySchemeMapper.selectOne(q.getSchemeid());
					//在该对象不会空的情况下,通过id获取物料的名称
					if(qs!=null){
						MaterielManage manage = materielManageMapper.selectByPrimaryKey(qs.getMaterialid());
						if(manage!=null){
							resp.setMaterialname(manage.getName());
						}
					}
					//获取item对象
					QualityItem qi = qualityItemMapper.selectOne(resp.getItemid());
					if(qi!=null){
						resp.setItemcode(qi.getCode());
						resp.setItemname(qi.getName());
						resp.setUnits(qi.getUnits());
						resp.setLine(qi.getLine());
					}


					resps.add(resp);
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resps);
		}
		return rs;
	}


	@Override
	public Result itemData() throws Exception {
		Result rs = Result.getErrorResult();
		QualityItemReq req = new QualityItemReq();
		//查询正常状态的数据
		req.setState("1");
		List<QualityItem> list = qualityItemMapper.page(req);
		List<QualityItemResp> resps = new ArrayList<QualityItemResp>();
		if(list!=null){
			for(QualityItem item : list){
				QualityItemResp resp = new QualityItemResp();
				resp.setId(item.getId());
				resp.setCode(item.getCode());
				resp.setName(item.getName());
				resps.add(resp);
			}
		}
		rs = Result.getSuccessResult();
		rs.setData(resps);
		return rs;
	}

	@Override
	@Transactional
	public Result updateBatch(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getErrorResult();
		if(req!=null){
			List<QualitySchemeItemReq> list = getListReq(req);
			//集合类型转换
			rs = Result.getSuccessResult();
			for(QualitySchemeItemReq item : list){
				QualitySchemeItem obj = new QualitySchemeItem();
				//类型转换
				PropertyUtils.copyProperties(obj, item);
				//设置为已初始化状态
				obj.setStatus("1");
				obj.setModifier(req.getUser());
				obj.setModifytime(System.currentTimeMillis());
				obj.setUtc(System.currentTimeMillis());
				//更新数据到数据库
				int index = qualitySchemeItemMapper.updateByPrimaryKeySelective(obj);
				if(index<=0){
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	public Result findDetailandVal(QualitySchemeItemReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getSchemeid())){
			req.setState("1");
			//通过schemeid获取数据
			List<QualitySchemeItem> list = qualitySchemeItemMapper.findBySchemeId(req);
			//转换为resp集合
			List<QualitySchemeItemResp> resps = new ArrayList<QualitySchemeItemResp>();
			if(list!=null && !list.isEmpty()){
				for(QualitySchemeItem q : list){
					QualitySchemeItemResp resp = new QualitySchemeItemResp();
					PropertyUtils.copyProperties(resp,q);
					//获取物料名称
					QualityScheme qs = qualitySchemeMapper.selectOne(q.getSchemeid());
					//在该对象不会空的情况下,通过id获取物料的名称
					if(qs!=null){
						MaterielManage manage = materielManageMapper.selectByPrimaryKey(qs.getMaterialid());
						if(manage!=null){
							resp.setMaterialname(manage.getName());
						}
					}
					//获取item对象
					QualityItem qi = qualityItemMapper.selectOne(resp.getItemid());
					if(qi!=null){
						resp.setItemcode(qi.getCode());
						resp.setItemname(qi.getName());
						resp.setUnits(qi.getUnits());
						//获取检测值
						if(StringUtils.isNotBlank(req.getAssayid())){
							AssayReportItem itemReq = new AssayReportItem();
							itemReq.setAssayid(req.getAssayid());
							itemReq.setItemid(resp.getItemid());
							AssayReportItem reportItem = assayReportItemMapper.findOne(itemReq);
							if(reportItem!=null){
								resp.setTestval(reportItem.getTestval());
							}
						}
					}
					resps.add(resp);
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resps);
		}
		return rs;
	}

	@Override
	public Result getOldItem(String schemeid) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(schemeid)){
			List<String> list = qualitySchemeItemMapper.findBySchemeid(schemeid);
			if(list!=null){
				rs = Result.getSuccessResult();
				rs.setData(list);
			}
		}
		return rs;
	}

}
