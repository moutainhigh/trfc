package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.service.bean.system.auth.SystemUser;
/**
 * 系统用户
 * @author lixp 2017年1月14日 15:47:53
 *
 */
public interface SystemUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
    /**
     * 根据对象查询
     * @param queryReq
     * @return
     */
    List<SystemUser> selectByCondition(SystemUserQueryReq queryReq );
    /**
     * 查询总数
     * @param queryReq
     * @return
     */
    long countByCondition(SystemUserQueryReq queryReq );
    /**
     * 自动搜索下拉框
     * @param queryReq
     * @return List<SystemUser>
     */
    List<SystemUser> autoCompleteSearch(SystemUserQueryReq queryReq );
    /**
     * @Description 根据组织查询所有用户
     * @author zhanggaohao
     * @version 2017年4月28日 上午10:53:36
     * @param orgid
     * @return
     */
	List<SystemUser> queryAllUserByOrgId(String orgid);
	/**
	 * @Description 批量添加用户
	 * @author zhanggaohao
	 * @version 2017年5月2日 下午4:22:29
	 * @param list
	 * @return
	 */
	int insertBatch(List<SystemUser> list);
	/**
	 * @Description 根据NCid和身份类型查询用户
	 * @author zhanggaohao
	 * @version 2017年5月10日 下午2:48:08
	 * @param ncid
	 * @param identityTypes
	 * @return
	 */
	SystemUser selectByNcIdAndIdentityTypes(String ncid, String identityTypes);
}