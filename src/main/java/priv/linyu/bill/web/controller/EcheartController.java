package priv.linyu.bill.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.utils.Constant;
import priv.linyu.bill.common.utils.RedisUtil;
import priv.linyu.bill.core.entity.ResponseData;
import priv.linyu.bill.core.entity.po.Customer;
import priv.linyu.bill.core.entity.vo.EcheartVO;
import priv.linyu.bill.core.mapper.CustomerMapper;
import priv.linyu.bill.core.service.CustomerService;

/**
 * @className: EcheartController
 * @author: q-linyu
 * @description: 统计分析视图控制器
 * @date: 2019/12/19 21:56
 * @version: 1.0
 **/
@RestController
public class EcheartController {

    @Autowired
    private CustomerService customerService;

    /**
     * 统计分析
     * @return
     */
    @GetMapping(value = "/echearts")
    public ResponseData<EcheartVO> echearts(){
        return new ResponseData<>(HttpState.SUCCESS, customerService.echearts());
    }


}
