package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdCustomerService;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.service.bean.basicFile.other.OtherBdCustomer;
import com.tianrui.service.mapper.basicFile.other.OtherBdCustomerMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class OtherBdCustomerService implements IOtherBdCustomerService {
	@Resource
	private OtherBdCustomerMapper otherBdCustomerMapper;
	/**
	 * 获取列表数据
	 */
	@Override
	public PaginationVO<OtherBdCustomerResp> findCustomerByPage(OtherBdCustomerReq req) throws Exception {
		PaginationVO<OtherBdCustomerResp> pageVO = new PaginationVO<OtherBdCustomerResp>();
		//判断参数为空时
		if(req==null){
			throw new RuntimeException("参数异常");
		}
		int pageOn = req.getPageNo();
		int pageSize = req.getPageSize();
		req.setStart((pageOn-1)*pageSize);
		req.setLimit(pageSize);
		int count = otherBdCustomerMapper.count(req);
		pageVO.setTotal(count);
		//判断数据总数为<0时
		if(count<0){
			throw new RuntimeException("获取数据异常");  
		}
		List<OtherBdCustomer> list = new ArrayList<OtherBdCustomer>();
		//判断数据总数为大于0时,则通过page()获取数据,等于0是不获取 
		if(count>0){
			list = otherBdCustomerMapper.page(req);
		}
		pageVO.setList(copyBeanList2RespList(list));
		pageVO.setPageNo(req.getPageNo());
		pageVO.setPageSize(req.getPageSize());
		return pageVO;
	}
	/**
	 * 集合转换
	 * @param List<OtherBdCustomer>
	 * @return List<OtherBdCustomerResp>
	 * @throws Exception
	 */
	private List<OtherBdCustomerResp> copyBeanList2RespList(List<OtherBdCustomer> list) throws Exception{
		List<OtherBdCustomerResp> resp = new ArrayList<OtherBdCustomerResp>();
		if(list==null){
			throw new RuntimeException("需转换的集合不能为空");
		}
		for(OtherBdCustomer o:list){
			OtherBdCustomerResp r = copyBean(o);
			resp.add(r);
		}
		return resp;
	}
	/**
	 * 类型转换
	 * @param OtherBdCustomer
	 * @return OtherBdCustomerResp
	 */
	private OtherBdCustomerResp copyBean(OtherBdCustomer o){

		OtherBdCustomerResp resp = new OtherBdCustomerResp();
		resp.setId(o.getId());
		resp.setCode(o.getCode());
		resp.setCreatetime(o.getCreatetime());
		resp.setCreator(o.getCreator());
		resp.setInfo(o.getInfo());
		resp.setInnercode(o.getInnercode());
		resp.setIsvalid(o.getIsvalid());
		resp.setModifier(o.getModifier());
		resp.setModifytime(o.getModifytime());
		resp.setName(o.getName());
		resp.setOrgid(o.getOrgid());
		resp.setOrgname(o.getOrgname());
		resp.setRemark(o.getRemark());
		return resp;
	}
	@Transactional
	@Override
	public boolean insertCustomer(OtherBdCustomerReq req) throws Exception {
		if(req==null){
			throw new RuntimeException("增加数据时,参数不能为空");
		}
		//添加创建时间
		req.setCreatetime(System.currentTimeMillis());
		req.setId(getCustomerId());
		//假设一个creator
		req.setCreator("LXY");
		int index = otherBdCustomerMapper.insertSelective(req);
		return index>0;
	}
	@Transactional
	@Override
	public boolean updateCustomer(OtherBdCustomerReq req) throws Exception {
		if(req==null){
			throw new RuntimeException("更新数据时,参数不能为空");
		}
		//添加修改时间
		req.setModifytime(System.currentTimeMillis());
		int index = otherBdCustomerMapper.updateByPrimaryKeySelective(req);
		return index>0;
	}
	@Transactional
	//删除一条数据
	@Override
	public boolean deleteCustomer(String id) throws Exception {
		if(id==null||id==""){
			throw new RuntimeException("id不能为空");

		}
		OtherBdCustomer o = otherBdCustomerMapper.selectByPrimaryKey(id);
		if(o==null){
			throw new RuntimeException("id无效");
		}
		int index = otherBdCustomerMapper.deleteByPrimaryKey(id);

		return index>0;
	}
	//获得id
	public String getCustomerId(){
		return UUIDUtil.getId();
	}
	//获得code
	public String getCustomerCode(){
		return "CD"+(int)(Math.random()*10000);
	}
	
	//获得innercode
	public String getCustomerInnercode(){
		return "ICD"+(int)(Math.random()*10000);
	}
	//通过主键(id)查找数据
	public OtherBdCustomerReq findByPrimaryKey(String id) throws Exception{
		OtherBdCustomerReq req = new OtherBdCustomerReq();
		if(id==null||id==""){
			throw new RuntimeException("查询数据时,参数不能为空");
		}
		OtherBdCustomer o = otherBdCustomerMapper.selectByPrimaryKey(id);
		if(o==null){
			throw new RuntimeException("查询数据的ID无效");
		}
		//类型 转换
		PropertyUtils.copyProperties(req, o);

		return req;
	}
	//检测名字
	@Override
	public Boolean checkName(String name) throws Exception {
		if(name==null||name==""){
			throw new RuntimeException("检测name不可为空");
		}
		int count = otherBdCustomerMapper.findCustomerByName(name);
		if(count==0){
			return true;
		}
		return false;
	}
}
