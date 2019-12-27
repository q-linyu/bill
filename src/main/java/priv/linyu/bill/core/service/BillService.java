package priv.linyu.bill.core.service;

import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Bill;

import java.util.List;

/**
 * @className: BillService
 * @author: q-linyu
 * @description:
 * @date: 2019/12/26 18:22
 * @version: 1.0
 **/
public interface BillService {

    /**
     * 查询全部
     * @return
     */
    List<Bill> selectAll();

    /**
     * 查询全部数据
     * @param billName
     * @param pageHepler
     * @return
     */
    PageInfo<Bill> select(String billName, PageHepler pageHepler);

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
    Bill selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param bill
     */
    void update(Bill bill);

    /**
     * 新增
     * @param bill
     */
    void add(Bill bill);
    
}
