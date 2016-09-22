package org.easylife.service.login;

import java.util.List;

import org.easylife.common.enumeration.ResultSetEnum;
import org.easylife.common.resultset.ResultSet;
import org.easylife.common.service.BaseServiceImpl;
import org.easylife.dao.member.MemberMapper;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.MemberQueryForm;
import org.easylife.facade.login.LoginService;
import org.easylife.jms.listener.SyncReceiveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @Filename LoginServiceImpl.java
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
 * @Date: 2016年4月8日 下午12:29:15
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SyncReceiveMessage syncReceiveMessage;

	@SuppressWarnings("unchecked")
	public ResultSet login(MemberQueryForm memberQueryForum) {
		
		/*syncReceiveMessage.receive();*/
		
		/**验证该用户是否存在*/
		memberQueryForum.setTotalCount(memberMapper.pageQueryEntityCount(memberQueryForum));
		logger.info("[查询入参memberQueryForum={}]",memberQueryForum);
		
		List<Member> memberList = memberMapper.pageQueryEntity(memberQueryForum);
		
		if(memberList == null || memberList.size() == 0){
			return ResultSet.failResult(ResultSetEnum.NULL_RESULT,memberList);
		}else if(memberList.size() > 1){
			return ResultSet.failResult(ResultSetEnum.MULTIPLE_RESULTS,memberList);
		}
		
		return ResultSet.successResult(ResultSetEnum.QUERY_SUCCESS,memberList);
	}

}
