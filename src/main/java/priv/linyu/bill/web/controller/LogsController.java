package priv.linyu.bill.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.ResponseData;
import priv.linyu.bill.core.entity.po.Logs;
import priv.linyu.bill.core.entity.vo.LogsVO;
import priv.linyu.bill.core.service.LogsService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: LogsController
 * @author: q-linyu
 * @description: 日志视图控制器
 * @date: 2019/12/27 13:21
 * @version: 1.0
 **/
@RestController
public class LogsController {

    @Autowired
    private LogsService logsService;

    /**
     * 分页查询
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping(value = "/logs/page")
    public ResponseData<List<LogsVO>> page(@RequestParam(value = "offset",defaultValue = "0")Integer offset,
                                           @RequestParam(value = "limit",defaultValue = "15")Integer limit){
        List<Logs> select = logsService.select(new PageHepler(offset, limit));
        List<LogsVO> logsVOS = new ArrayList<>();
        for (Logs logs : select) {
            LogsVO logsVO = new LogsVO();
            logsVO.setId(logs.getId());
            // 设置当前系统的ip地址
            try {
                String ip = InetAddress.getLocalHost().getHostAddress();
                logsVO.setIp(ip);
            }catch (UnknownHostException e){
                e.printStackTrace();
            }
            logsVO.setContent(logs.getContent());
            logsVO.setCreateTime(logs.getCreateTime());
            logsVO.setUsername(logs.getUserInfo().getUsername());

            logsVOS.add(logsVO);
        }
        return new ResponseData<>(HttpState.SUCCESS,logsVOS);
    }

}
