package priv.linyu.bill.core.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @className: EcheartVO
 * @author: q-linyu
 * @description: 统计分析视图对象
 * @date: 2019/12/19 21:58
 * @version: 1.0
 **/
@Data
public class EcheartVO {

    /**
     * 客户注册的次数
     */
    private Integer regCount;

    /**
     * 已激活的人数
     */
    private Integer activeCount;

    /**
     * 未激活的人数
     */
    private Integer noActiveCount;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 1-12个月,格式yyyy-MM
     */
    private List<String> months;

    /**
     * 1-12个月中每月的注册人数
     */
    private List<Integer> everyCount;

}
