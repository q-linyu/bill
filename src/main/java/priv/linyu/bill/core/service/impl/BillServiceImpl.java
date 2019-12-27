package priv.linyu.bill.core.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Bill;
import priv.linyu.bill.core.entity.po.Customer;
import priv.linyu.bill.core.mapper.BillMapper;
import priv.linyu.bill.core.service.BillService;

import java.util.List;
import java.util.Objects;

/**
 * @className: BillServiceImpl
 * @author: q-linyu
 * @description:
 * @date: 2019/12/26 18:22
 * @version: 1.0
 **/
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> selectAll() {
        List<Bill> bills = billMapper.selectAll();
        if (CollectionUtils.isEmpty(bills)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return bills;
    }

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public PageInfo<Bill> select(String billName, PageHepler pageHepler) {
        List<Bill> bills = billMapper.select(billName,pageHepler);
        if (CollectionUtils.isEmpty(bills)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        int total = billMapper.count(billName);
        return new PageInfo<>(total,bills);
    }

    /**
     * 根据主键id删除
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Integer id) {
        int count = billMapper.deleteByPrimaryKey(id);
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
    public Bill selectByPrimaryKey(Integer id) {
        Bill bill = billMapper.selectByPrimaryKey(id);
        if (Objects.isNull(bill)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return bill;
    }

    /**
     * 修改
     * @param bill
     */
    @Override
    public void update(Bill bill) {
        int count = billMapper.update(bill);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILD);
        }
    }

    /**
     * 新增
     * @param bill
     */
    @Override
    public void add(Bill bill) {
        int count = billMapper.insert(bill);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.NO_CREATED);
        }
    }


}
