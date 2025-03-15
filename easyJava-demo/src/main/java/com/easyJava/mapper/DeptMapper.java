package com.easyJava.mapper;

import org.apache.ibatis.annotations.Param;

/**
  * @Description:部门表
  * @Author:刘耿豪
  * @Date:2025/03/15
  */
public interface DeptMapper<T,P> extends BaseMapper{
	/**
	 * 根据Id查询
	 */
	 T selectById(@Param("id") Integer id);

	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Integer id);

	/**
	 * 根据Name查询
	 */
	 T selectByName(@Param("name") String name);

	/**
	 * 根据Name更新
	 */
	 Integer updateByName(@Param("bean") T t, @Param("name") String name);

	/**
	 * 根据Name删除
	 */
	 Integer deleteByName(@Param("name") String name);

}