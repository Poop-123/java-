package com.easyJava.mapper;

import org.apache.ibatis.annotations.Param;

/**
  * @Description:员工表
  * @Author:刘耿豪
  * @Date:2025/03/15
  */
public interface EmpMapper<T,P> extends BaseMapper{
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
	 * 根据Username查询
	 */
	 T selectByUsername(@Param("username") String username);

	/**
	 * 根据Username更新
	 */
	 Integer updateByUsername(@Param("bean") T t, @Param("username") String username);

	/**
	 * 根据Username删除
	 */
	 Integer deleteByUsername(@Param("username") String username);

}