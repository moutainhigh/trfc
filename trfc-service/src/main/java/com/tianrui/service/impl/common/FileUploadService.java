package com.tianrui.service.impl.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mongodb.gridfs.GridFSDBFile;
import com.tianrui.api.intf.common.IFileService;
import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.service.bean.common.UploadImage;
import com.tianrui.service.mapper.common.UploadImageMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class FileUploadService implements IFileService{
	public Logger logger =LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	@Autowired
	private UploadImageMapper uploadImageMapper;

	/**
	 * 保存图片数据到mongo
	 * TODO 图片的上传记录信息
	 */
	public Result uploadImg(FileUploadReq fileUploadReq) throws Exception {
		Result result=Result.getSuccessResult();
		if(fileUploadReq !=null){
			//验证图片格式
			if(fileUploadReq.getFileByte() != null && fileUploadReq.getFileByte().length > 0){
				try {
					String imgURI = UUIDUtil.getId();
					InputStream input = new ByteArrayInputStream(fileUploadReq.getFileByte());
					gridFsTemplate.store(input, imgURI);
					String imgURL = Constant.FILE_URL_PRE+imgURI;
					UploadImage bean = new UploadImage();
					bean.setId(UUIDUtil.getId());
					bean.setBillcode(fileUploadReq.getBillcode());
					bean.setBilltype(fileUploadReq.getBilltype());
					bean.setSource(fileUploadReq.getType());
					bean.setImgurl(imgURL);
					bean.setCreator(fileUploadReq.getuId());
					bean.setCreatetime(System.currentTimeMillis());
					if(uploadImageMapper.insertSelective(bean) == 1){
						result.setData(imgURL);
					}else{
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				} catch (Exception e) {
					logger.error("{}",e.getMessage(),e);
					result =new Result("error","上传图片服务异常" );
				}
			}else{
				result =new Result("error","上传图片格式有问题" );
			}
		}else{
			result =new Result("error","上传图片为空" );
		}
		logger.info("图片上传结束！ result:{}",JSON.toJSON(result));
		return result;
	}
	
	public InputStream download(String fileName) throws Exception {
		InputStream in=null;
		if( StringUtils.isNotBlank(fileName) ){
			Query query = new Query();
			query.addCriteria(Criteria.where("filename").is(fileName));
			 List<GridFSDBFile> list =gridFsTemplate.find(query);
			 if( CollectionUtils.isNotEmpty(list) ){
				 GridFSDBFile file= list.get(0);
				 if( file !=null ){
					 in=file.getInputStream();
				 }
			 }
		}
		return in;
	}
}
