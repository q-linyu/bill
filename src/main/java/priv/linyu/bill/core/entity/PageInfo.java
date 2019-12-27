package priv.linyu.bill.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: PageInfo
 * @author: q-linyu
 * @description:
 * @date: 2019/12/22 14:07
 * @version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {

    /**
     * 分页辅助类
     */
    private Integer total;

    /**
     * 分页数据类
     */
    private List<T> list;

}
