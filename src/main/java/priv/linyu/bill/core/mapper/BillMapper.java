package priv.linyu.bill.core.mapper;


import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Bill;

import java.util.List;

/**
 * @className: BillMapper
 * @author: q-linyu
 * @description:
 * @date: 2019/12/26 18:11
 * @version: 1.0
 **/
public interface BillMapper {

    /**
     * 查询全部
     * @return
     */
    List<Bill> selectAll();

    /**
     * 是否付款
     * @param pay
     * @return
     */
    Integer isPay(@Param(value = "pay")Integer pay);

    /**
     * 查询全部数据
     * @param billName
     * @param pageHepler
     * @return
     */
    List<Bill> select(@Param(value = "billName")String billName,@Param(value = "pageHepler") PageHepler pageHepler);

    /**
     * 统计的人数,已激活的人数,未激活的人数
     * @param billName
     * @return
     */
    Integer count(@Param(value = "billName")String billName);

    /**
     * 根据主键id删除
     * @param bid
     * @return
     */
    Integer deleteByPrimaryKey(@Param(value = "bid")Integer bid);

    /**
     * 根据主键id查询
     * @param bid
     * @return
     */
    Bill selectByPrimaryKey(@Param(value = "bid")Integer bid);

    /**
     * 修改
     * @param bill
     * @return
     */
    Integer update(Bill bill);

    /**
     * 新增
     * @param bill
     * @return
     */
    Integer insert(Bill bill);


}
