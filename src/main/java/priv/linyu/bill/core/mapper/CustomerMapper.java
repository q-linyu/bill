package priv.linyu.bill.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Customer;

import java.util.List;

/**
 * @className: CustomerMapper
 * @author: q-linyu
 * @description: 客户Mapper
 * @date: 2019/12/18 23:18
 * @version: 1.0
 **/
public interface CustomerMapper {

    /**
     * 查询全部
     * @return
     */
    List<Customer> selectAll();

    /**
     * 统计的人数,已激活的人数,未激活的人数
     * @param state
     * @param name
     * @return
     */
    Integer count(@Param(value = "state")Integer state,@Param(value = "name")String name);

    /**
     * 查询全部数据
     * @param name
     * @param pageHepler
     * @return
     */
    List<Customer> select(@Param(value = "name")String name,@Param(value = "pageHepler")PageHepler pageHepler);

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(@Param(value = "id")Integer id);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Customer selectByPrimaryKey(@Param(value = "id")Integer id);

    /**
     * 修改
     * @param customer
     * @return
     */
    Integer update(Customer customer);

    /**
     * 新增
     * @param customer
     * @return
     */
    Integer add(Customer customer);

    /**
     * 校验客户名是否被注册
     * @param name
     * @return
     */
    Integer checkByname(@Param(value = "name")String name);


    /**
     * 根据每月获取对应的记录数
     * @param dataStr
     * @return
     */
    Integer echearts(@Param(value = "dataStr")String dataStr);


}