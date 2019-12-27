package priv.linyu.bill.core.service;

import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Logs;

import java.util.List;

/**
 * @className: LogsService
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 13:19
 * @version: 1.0
 **/
public interface LogsService {

    /**
     * 保存
     * @param userId
     * @param content
     */
    void save(Integer userId,String content);

    /**
     * 查询
     * @param pageHepler
     * @return
     */
    List<Logs> select(PageHepler pageHepler);

}
