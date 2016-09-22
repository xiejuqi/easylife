package org.easylife.domain.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.easylife.domain.member.Permission;
import org.easylife.domain.member.Tree;


/**
 * 
 * @Filename ResourceTreeUtil.java
 * 
 * @Description	根据会员权限构建权限树形结构
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月13日 上午10:05:10
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class ResourceTreeUtil {
	
	public static List<Tree> convertResourceListToTreeList(Set<Permission> resourceList) {
		
		Map<Integer, Tree> temp = convertResourceListToTreeMap(resourceList);
		
		// 存储顶级菜单
		List<Tree> topTreeList = new ArrayList<Tree>();
		
		// 构造子菜单及顶级菜单
		for (Permission resource : resourceList) {

			Tree tree = temp.get(resource.getPermissionId());

			if (resource.getParentId() > 0) {

				Tree parentTree = temp.get(resource.getParentId());

				parentTree = (null == parentTree) ? (new Tree()) : parentTree; // 解决空指针报错

				if (parentTree.getChildren() == null) {
					parentTree.setChildren(new ArrayList<Tree>());
				}
				parentTree.getChildren().add(tree);
				parentTree.setState("closed");// 默认关闭

			} else {
				topTreeList.add(tree);
			}
		}
		
		return topTreeList;
	}
	
	
	/**
	 * 构造TreeMap
	 * @param resourceList
	 * @return
	 */
	public static Map<Integer, Tree> convertResourceListToTreeMap(Set<Permission> resourceList) {
		
		Set<Permission> distinctResourceList = new HashSet<Permission>();
		
		Map<Integer, Tree> result = new HashMap<Integer, Tree>();
		
		for (Permission resource : resourceList) {
			
			if(!result.containsKey(resource.getPermissionId())){
				Tree tree = new Tree(resource.getPermissionId(),resource.getParentId(), resource.getName(), resource.getIcon(),
						resource.getCssClass(), resource.getPermission(), resource.getHref());
				tree.setState("open");
				
				result.put(tree.getPermissionId(), tree);
				
				distinctResourceList.add(resource);
			}
			
		}
		
		resourceList.clear();
		resourceList.addAll(distinctResourceList) ;
		
		return result;
	}
	
	/**
	 * 将List<Resource>转化为List<Tree>，并根据treeCheckedIds选中树节点
	 * @param resourceList 资源列表
	 * @param treeCheckedIds 选中的节点列表
	 * @return
	 */
	public static List<Tree> convertResourceListToTreeList(Set<Permission> resourceList, List<Long> treeCheckedIds){
		
		Map<Integer,Tree> temp = convertResourceListToTreeMap(resourceList);
		// 存储顶级菜单
		List<Tree> topTreeList = new ArrayList<Tree>();
		// 构造子菜单及顶级菜单
		for (Permission resource : resourceList) {

			Tree tree = temp.get(resource.getPermissionId());

			if (resource.getParentId() > 0) {

				Tree parentTree = temp.get(resource.getParentId());
				
				parentTree = (null==parentTree)?(new Tree()):parentTree;  //解决空指针报错  
				
				if (parentTree.getChildren() == null) {
					parentTree.setChildren(new ArrayList<Tree>());
				}
				parentTree.getChildren().add(tree);
				parentTree.setState("closed");// 默认关闭
			} else {
				topTreeList.add(tree);
			}
			
		}
		
		//设置是否选中
		for(Map.Entry<Integer, Tree> entry: temp.entrySet()){
			Tree tree = entry.getValue();
			if ((tree.getChildren() == null || tree.getChildren().size() < 1) && treeCheckedIds.contains(tree.getId())) {
				tree.setChecked(true);
			}
		}
		
		return topTreeList;
	}
}
