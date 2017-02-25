package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.file.ICertificationService;
import com.tianrui.api.req.quality.file.CertificationReq;
import com.tianrui.api.resp.quality.file.CertificationResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.Certification;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.CertificationMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CertificationService implements ICertificationService {
	@Resource
	private CertificationMapper certificationMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;


	@Override
	@Transactional
	public Result delete(CertificationReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			Certification cert = new Certification();
			cert.setId(req.getId());
			//设置为删除状态
			cert.setState("0");
			//删除数据
			int index = certificationMapper.updateByPrimaryKeySelective(cert);
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
	public Result add(CertificationReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			//转换类型
			Certification c = new Certification();
			PropertyUtils.copyProperties(c, req);
			//设置id
			c.setId(UUIDUtil.getId());
			//设置创建者和修改者
			c.setCreator(req.getUser());
			c.setCreatetime(System.currentTimeMillis());
			c.setModifier(req.getUser());
			c.setModifytime(System.currentTimeMillis());
			c.setUtc(System.currentTimeMillis());
			//设置为正常状态
			c.setState("1");
			//保存数据
			int index = certificationMapper.insertSelective(c);
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
	public Result update(CertificationReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			Certification c = new Certification();
			PropertyUtils.copyProperties(c, req);
			//设置创建者和修改者
			c.setModifier(req.getUser());
			c.setModifytime(System.currentTimeMillis());
			c.setUtc(System.currentTimeMillis());
			//更新数据
			int index = certificationMapper.updateByPrimaryKeySelective(c);
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
	public Result page(CertificationReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			req.setState("1");
			PaginationVO<CertificationResp> page = new PaginationVO<CertificationResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置开始位置,和每页数据量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			//获取数据总数
			int total = certificationMapper.count(req);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotal(total);
			//创建一个出参集合
			List<CertificationResp> resps = new ArrayList<CertificationResp>();
			//判断是否有数据可查询
			if(total>0){
				List<Certification> list = certificationMapper.page(req);
				if(list!=null && !list.isEmpty()){
					//遍历集合,并转换类型
					for(Certification c : list){
						CertificationResp resp = new CertificationResp();
						PropertyUtils.copyProperties(resp, c);
						//通过物料id 查询物料信息 获取物料名称
						MaterielManage m = materielManageMapper.selectByPrimaryKey(c.getMaterialid());
						if(m!=null){
							resp.setMaterialname(m.getName());
						}
						//将对想放入集合
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
