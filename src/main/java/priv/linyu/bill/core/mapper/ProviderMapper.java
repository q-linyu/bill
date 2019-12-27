package priv.linyu.bill.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Provider;

import java.util.List;

/**
 * @className: ProviderMapper
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 11:12
 * @version: 1.0
 **/
public interface ProviderMapper {

    /**
     * 查询全部
     * @return
     */
    List<Provider> selectAll();


    /**
     * 查询全部数据
     * @param providerName
     * @param pageHepler
     * @return
     */
    List<Provider> select(@Param(value = "providerName")String providerName,@Param(value = "pageHepler") PageHepler pageHepler);

    /**
     * 统计的人数,已激活的人数,未激活的人数
     * @param providerName
     * @return
     */
    Integer count(@Param(value = "providerName")String providerName);

    /**
     * 根据主键id删除
     * @param pid
     * @return
     */
    Integer deleteByPrimaryKey(@Param(value = "pid")Integer pid);

    /**
     * 根据主键id查询
     * @param pid
     * @return
     */
    Provider selectByPrimaryKey(@Param(value = "pid")Integer pid);

    /**
     * 修改
     * @param provider
     * @return
     */
    Integer update(Provider provider);

    /**
     * 新增
     * @param provider
     * @return
     */
    Integer insert(Provider provider);

}
