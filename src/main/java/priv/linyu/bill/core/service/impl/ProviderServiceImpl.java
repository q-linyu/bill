package priv.linyu.bill.core.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Provider;
import priv.linyu.bill.core.mapper.ProviderMapper;
import priv.linyu.bill.core.service.ProviderService;

import java.util.List;
import java.util.Objects;

/**
 * @className: ProviderServiceImpl
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 11:15
 * @version: 1.0
 **/
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;


    @Override
    public List<Provider> selectAll() {
        List<Provider> providers = providerMapper.selectAll();
        if (CollectionUtils.isEmpty(providers)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return providers;
    }

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public PageInfo<Provider> select(String providerName, PageHepler pageHepler) {
        List<Provider> providers = providerMapper.select(providerName,pageHepler);
        if (CollectionUtils.isEmpty(providers)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        int total = providerMapper.count(providerName);
        return new PageInfo<>(total,providers);
    }

    /**
     * 根据主键id删除
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Integer id) {
        int count = providerMapper.deleteByPrimaryKey(id);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.DELETE_FAILD);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Provider selectByPrimaryKey(Integer id) {
        Provider provider = providerMapper.selectByPrimaryKey(id);
        if (Objects.isNull(provider)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return provider;
    }

    /**
     * 修改
     * @param provider
     */
    @Override
    public void update(Provider provider) {
        int count = providerMapper.update(provider);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILD);
        }
    }

    /**
     * 新增
     * @param provider
     */
    @Override
    public void add(Provider provider) {
        int count = providerMapper.insert(provider);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.NO_CREATED);
        }
    }
}
