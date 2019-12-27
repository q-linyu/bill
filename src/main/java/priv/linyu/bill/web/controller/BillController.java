package priv.linyu.bill.web.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.utils.ShiroUtils;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.ResponseData;
import priv.linyu.bill.core.entity.po.Bill;
import priv.linyu.bill.core.service.BillService;
import priv.linyu.bill.core.service.LogsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: BillController
 * @author: q-linyu
 * @description:
 * @date: 2019/12/26 18:28
 * @version: 1.0
 **/
@RestController
public class BillController {
    
    @Autowired
    private BillService billService;

    @Autowired
    private LogsService logsService;

    /**
     * 分页查询
     * @param billName
     * @param pageHepler
     * @return
     */
    @GetMapping(value = "/bill/page")
    public ResponseData<PageInfo<Bill>> page(@RequestParam(value = "billName",required = false)String billName, PageHepler pageHepler){
        return new ResponseData<>(HttpState.SUCCESS, billService.select(billName,pageHepler));
    }

    /**
     * 删除
     * @param id 主键id
     * @return
     */
    @DeleteMapping(value = "/bill/{bid}")
    public ResponseData<Void> delete(@PathVariable(value = "bid",required = false)Integer id){
        billService.deleteByPrimaryKey(id);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"删除账单成功");
        return new ResponseData<>(HttpState.PARTIAL_CONTENT);
    }

    /**
     * 编辑
     * @param id 主键id
     * @return
     */
    @GetMapping(value = "/bill")
    public ResponseData<Bill> edit(@RequestParam(value = "bid")Integer id){
        return new ResponseData<>(HttpState.SUCCESS,billService.selectByPrimaryKey(id));
    }

    /**
     * 修改
     * @param bill 对象
     * @return
     */
    @PutMapping(value = "/bill/update")
    public ResponseData<Void> update(Bill bill){
        billService.update(bill);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"修改账单成功");
        return new ResponseData<>(HttpState.NO_CONTENT);
    }


    /**
     * 新增
     * @param bill 对象
     * @return
     */
    @PostMapping(value = "/bill/add")
    public ResponseData<Void> add(Bill bill){
        billService.add(bill);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"新增账单成功");
        return new ResponseData<>(HttpState.CREATED);
    }

    /**
     * 导出
     * @param response
     * @return
     */
    @GetMapping(value = "/bill/export")
    public void export(HttpServletResponse response){

        OutputStream os = null;

        try {

            // 创建一个工作簿
            Workbook workbook = new HSSFWorkbook();

            // 创建sheet
            Sheet sheet = workbook.createSheet("客户信息");

            // 创建第一行
            Row row = sheet.createRow(0);

            // 设置单元格头部
            List<String> titles = new ArrayList<>();
            titles.add("序号");
            titles.add("账单编码");
            titles.add("账单名称");
            titles.add("续费");
            titles.add("账单数量");
            titles.add("金额");
            titles.add("是否付款");
            titles.add("创建时间");
            titles.add("修改时间");

            // 遍历表头
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles.get(i));
            }

            // 遍历数据
            List<Bill> bills = billService.selectAll();

            // 遍历数据
            for (Bill bill : bills) {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
                dataRow.createCell(0).setCellValue(bill.getBid());
                dataRow.createCell(1).setCellValue(bill.getBillCode());
                dataRow.createCell(2).setCellValue(bill.getBillName());
                dataRow.createCell(3).setCellValue(bill.getBillCom());
                dataRow.createCell(4).setCellValue(bill.getBillNum());
                dataRow.createCell(6).setCellValue(bill.getMoney());
                dataRow.createCell(7).setCellValue(bill.getPay() == 1 ? "已付款":"未付款");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataRow.createCell(8).setCellValue(sdf.format(bill.getCreateTime()));
                SimpleDateFormat updateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (bill.getUpdateTime() != null){
                    dataRow.createCell(9).setCellValue(updateTimeSdf.format(bill.getUpdateTime()));
                }else {
                    dataRow.createCell(9).setCellValue("-");
                }

            }

            SimpleDateFormat downloadSdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // 设置文件的名称
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+downloadSdf.format(new Date())+".xls");

            os = response.getOutputStream();

            workbook.write(os);

            // 记录日志
            logsService.save(ShiroUtils.getUserInfo().getUserId(),"导出账单成功");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (os != null){
                try {
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    
}
