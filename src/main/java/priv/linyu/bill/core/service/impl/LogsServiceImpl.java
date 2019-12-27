package priv.linyu.bill.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.po.Logs;
import priv.linyu.bill.core.mapper.LogsMapper;
import priv.linyu.bill.core.service.LogsService;

import java.util.List;

/**
 * @className: LogsServiceImpl
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 13:20
 * @version: 1.0
 **/
@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsMapper logsMapper;

    @Override
    public void save(Integer userId, String content) {
        logsMapper.insert(userId,content);
    }

    /**
     * 分页查询日志记录
     * @param pageHepler
     * @return
     */
    @Override
    public List<Logs> select(PageHepler pageHepler) {
        return logsMapper.select(pageHepler);
    }
}
