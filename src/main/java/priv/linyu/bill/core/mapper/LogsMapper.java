package priv.linyu.bill.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Logs;

import java.util.List;

/**
 * @className: LogsMapper
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 13:09
 * @version: 1.0
 **/
public interface LogsMapper {

    /**
     * 新增
     * @param userId
     * @param content
     * @return
     */
    Integer insert(@Param(value = "userId")Integer userId,@Param(value = "content")String content);

    /**
     * 查询
     * @param pageHepler
     * @return
     */
    List<Logs> select(@Param(value = "pageHepler") PageHepler pageHepler);

}
