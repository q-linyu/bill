package priv.linyu.bill.core.service;

import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Customer;
import priv.linyu.bill.core.entity.vo.EcheartVO;

import java.util.List;

/**
 * @className: CustomerService
 * @author: q-linyu
 * @description:
 * @date: 2019/12/18 23:22
 * @version: 1.0
 **/
public interface CustomerService {

    /**
     * 查询全部
     * @return
     */
    List<Customer> selectAll();

    /**
     * 查询全部数据
     * @param name
     * @param pageHepler
     * @return
     */
    PageInfo<Customer> select(String name, PageHepler pageHepler);

    /**
     * 根据主键id删除
     * @param id
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param customer
     */
    void update(Customer customer);

    /**
     * 新增
     * @param customer
     */
    void add(Customer customer);

    /**
     * 校验客户名是否被注册
     * @param name
     * @return
     */
    Boolean checkByname(String name);

    /**
     * 统计分析
     * @return
     */
    EcheartVO echearts();
}
