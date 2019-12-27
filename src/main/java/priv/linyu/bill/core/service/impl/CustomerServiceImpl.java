package priv.linyu.bill.core.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.common.utils.Constant;
import priv.linyu.bill.common.utils.RedisUtil;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.po.Customer;
import priv.linyu.bill.core.entity.vo.EcheartVO;
import priv.linyu.bill.core.mapper.CustomerMapper;
import priv.linyu.bill.core.service.CustomerService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @className: CustomerServiceImpl
 * @author: q-linyu
 * @description:
 * @date: 2019/12/18 23:22
 * @version: 1.0
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Customer> selectAll() {
        List<Customer> customers = customerMapper.selectAll();
        if (CollectionUtils.isEmpty(customers)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return customers;
    }

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public PageInfo<Customer> select(String name, PageHepler pageHepler) {
        List<Customer> customers = customerMapper.select(name,pageHepler);
        if (CollectionUtils.isEmpty(customers)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        int total = customerMapper.count(null,name);
        return new PageInfo<>(total,customers);
    }

    /**
     * 根据主键id删除
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Integer id) {
        int count = customerMapper.deleteByPrimaryKey(id);
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
    public Customer selectByPrimaryKey(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if (Objects.isNull(customer)){
            throw new BusinessRuntimeException(HttpState.NOT_FOUND);
        }
        return customer;
    }

    /**
     * 修改
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        int count = customerMapper.update(customer);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILD);
        }
    }

    /**
     * 新增
     * @param customer
     */
    @Override
    public void add(Customer customer) {
        int count = customerMapper.add(customer);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.NO_CREATED);
        }
    }

    /**
     * 校验客户名是否被注册
     * @param name
     * @return
     */
    @Override
    public Boolean checkByname(String name) {
        int count = customerMapper.checkByname(name);
        if (count > 0){
            throw new BusinessRuntimeException(HttpState.NO_CREATED);
        }
        return true;
    }

    /**
     * 统计分析
     * @return
     */
    @Override
    public EcheartVO echearts() {

        EcheartVO echeartVO = new EcheartVO();
        // 统计客户注册的次数
        Integer regCount = customerMapper.count(null,null);
        echeartVO.setRegCount(regCount);

        // 已激活的人数
        Integer activeCount = customerMapper.count(1,null);
        echeartVO.setActiveCount(activeCount);

        // 未激活的人数
        Integer noActiveCount = customerMapper.count(0,null);
        echeartVO.setNoActiveCount(noActiveCount);

        // 登录的次数
        String countStr = redisUtil.get(Constant.loginKey).toString();
        Integer count = Integer.parseInt(countStr);
        echeartVO.setLoginCount(count);

        // 用来存储月份
        List<String> monthsResult = new ArrayList<>();
        // 用来存储每月获取对应的记录数
        List<Integer> dataResult = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        // 打印12个月
        for (int i = 0; i < 12; i++) {
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 获取月
            int month = calendar.get(Calendar.MONTH) + 1 -i;

            // 记录时间
            String dataStr = year + "-" + (month >= 10 ? "":"0") + month;

            // 获得月份
            monthsResult.add(month + "月");
            echeartVO.setMonths(monthsResult);

            // 根据每月获取对应的记录数
            Integer ret = customerMapper.echearts(dataStr);
            dataResult.add(ret);
            echeartVO.setEveryCount(dataResult);
        }

        return echeartVO;
    }
}
