package org.easylife.service.member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.easylife.common.enumeration.ResultSetEnum;
import org.easylife.common.resultset.ResultSet;
import org.easylife.common.service.BaseServiceImpl;
import org.easylife.common.utils.ListUtil;
import org.easylife.common.utils.StringUtils;
import org.easylife.dao.member.MemberMapper;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.MemberQueryForm;
import org.easylife.domain.member.Permission;
import org.easylife.domain.member.Role;
import org.easylife.domain.member.Tree;
import org.easylife.domain.utils.ResourceTreeUtil;
import org.easylife.facade.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * @Filename MemberServiceImpl.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月18日 下午3:20:43
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public ResultSet updateMember(Member member) {
		
		logger.info("[查询入参member={}]",member);
		
		if(member == null){
			return ResultSet.failResult(ResultSetEnum.NULL_RESULT);
		}
		
		String account = member.getAccount();
		
		if(StringUtils.isBlank(account)){
			return ResultSet.failResult(ResultSetEnum.NULL_RESULT);
		}
		
		MemberQueryForm memberQueryForum = new MemberQueryForm();
		memberQueryForum.setAccount(account);
		
		int n  = memberMapper.pageQueryEntityCount(memberQueryForum);
		
		if(n == 1){
			
			int updateLine = memberMapper.updateEntity(member);
			
			if(updateLine == 1){
				return ResultSet.successResult(ResultSetEnum.UPDATE_SUCCESS);
			}
			
			logger.info("[根据账户更新member结果不唯一,member={}]",member);
			
			return ResultSet.failResult(ResultSetEnum.UPDATE_FAIL);
		}
		
		logger.info("[根据入参查询结果不唯一,member={}]",member);
		
		return ResultSet.successResult(ResultSetEnum.MULTIPLE_RESULTS);
	}

	@SuppressWarnings("unchecked")
	public ResultSet getMemberPermissions(Member member) {
		
		if(member == null || StringUtils.isBlank(member.getAccount())){
			return ResultSet.failResult(ResultSetEnum.NULL_RESULT);
		}
		
		MemberQueryForm memberQueryForum = new MemberQueryForm();
		memberQueryForum.setAccount(member.getAccount());
		
		List<Member> memberList = memberMapper.pageQueryEntity(memberQueryForum);
		
		if(ListUtil.isEmpty(memberList)){
			return ResultSet.failResult(ResultSetEnum.NULL_RESULT);
		}
		
		if(memberList.size() > 1){
			return ResultSet.failResult(ResultSetEnum.MULTIPLE_RESULTS);
		}
		
		/**登录用户的角色集合*/
		Set<Role> userRoles = memberList.get(0).getRoles();
		
		Set<Permission> permissions = new HashSet<Permission>();
		
		if(userRoles != null && userRoles.size() > 0){
			
			for(Role role : userRoles){
				
		    	Set<Permission> userPermissions = role.getPermissions();
		    	
		    	if(userPermissions != null && userPermissions.size() > 0){
		    		for(Permission permission : userPermissions){
		    			permissions.add(permission);
			    	}
		    	}
		    }
		}
		
		List<Tree> treeList = ResourceTreeUtil.convertResourceListToTreeList(permissions);
		
		return ResultSet.successResult(ResultSetEnum.QUERY_SUCCESS, treeList);
	}
	
	

}
