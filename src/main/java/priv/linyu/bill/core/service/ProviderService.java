package priv.linyu.bill.core.service;

import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Provider;

import java.util.List;

/**
 * @className: ProviderService
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 11:15
 * @version: 1.0
 **/
public interface ProviderService {

    /**
     * 查询全部
     * @return
     */
    List<Provider> selectAll();

    /**
     * 查询全部数据
     * @param billName
     * @param pageHepler
     * @return
     */
    PageInfo<Provider> select(String billName, PageHepler pageHepler);

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
    Provider selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param provider
     */
    void update(Provider provider);

    /**
     * 新增
     * @param provider
     */
    void add(Provider provider);

}
