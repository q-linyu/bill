package priv.linyu.bill.web.controller;
import	java.nio.file.Path;


import java.io.IOException;
import	java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.utils.ShiroUtils;
import priv.linyu.bill.core.entity.PageHepler;
import priv.linyu.bill.core.entity.PageInfo;
import priv.linyu.bill.core.entity.ResponseData;
import priv.linyu.bill.core.entity.po.Customer;
import priv.linyu.bill.core.service.CustomerService;
import priv.linyu.bill.core.service.LogsService;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: CustomerController
 * @author: q-linyu
 * @description:
 * @date: 2019/12/18 23:23
 * @version: 1.0
 **/
@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LogsService logsService;

    /**
     * 分页查询
     * @param name
     * @param pageHepler
     * @return
     */
    @GetMapping(value = "/customer/page")
    public ResponseData<PageInfo<Customer>> page(@RequestParam(value = "name",required = false)String name,PageHepler pageHepler){
        return new ResponseData<>(HttpState.SUCCESS, customerService.select(name,pageHepler));
    }

    /**
     * 删除
     * @param id 主键id
     * @return
     */
    @DeleteMapping(value = "/customer/{id}")
    public ResponseData<Void> delete(@PathVariable(value = "id",required = false)Integer id){
        customerService.deleteByPrimaryKey(id);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"删除客户成功");
        return new ResponseData<>(HttpState.PARTIAL_CONTENT);
    }

    /**
     * 编辑
     * @param id 主键id
     * @return
     */
    @GetMapping(value = "/customer")
    public ResponseData<Customer> edit(@RequestParam(value = "id")Integer id){
        return new ResponseData<>(HttpState.SUCCESS,customerService.selectByPrimaryKey(id));
    }

    /**
     * 修改
     * @param customer 对象
     * @return
     */
    @PutMapping(value = "/customer/update")
    public ResponseData<Void> update(Customer customer){
        customerService.update(customer);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"修改客户成功");
        return new ResponseData<>(HttpState.NO_CONTENT);
    }

    /**
     * 校验客户名是否被注册
     * @param name
     * @return
     */
    @GetMapping(value = "/customer/checkByname")
    public ResponseData<Boolean> checkByname(@RequestParam(value = "name")String name){
        return new ResponseData<>(HttpState.SUCCESS,customerService.checkByname(name));
    }


    /**
     * 新增
     * @param customer 对象
     * @return
     */
    @PostMapping(value = "/customer/add")
    public ResponseData<Void> add(Customer customer){
        customerService.add(customer);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"新增客户成功");
        return new ResponseData<>(HttpState.CREATED);
    }

    /**
     * 导出
     * @param response
     * @return
     */
    @GetMapping(value = "/customer/export")
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
            titles.add("姓名");
            titles.add("年龄");
            titles.add("性别");
            titles.add("邮箱");
            titles.add("生日");
            titles.add("地址");
            titles.add("状态");
            titles.add("创建时间");
            titles.add("修改时间");

            // 遍历表头
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles.get(i));
            }

            // 遍历数据
            List<Customer> customers = customerService.selectAll();

            // 遍历数据
            for (Customer customer : customers) {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
                dataRow.createCell(0).setCellValue(customer.getId());
                dataRow.createCell(1).setCellValue(customer.getName());
                dataRow.createCell(2).setCellValue(customer.getAge());
                dataRow.createCell(3).setCellValue(customer.getGender() == 1 ? "男":"女");
                dataRow.createCell(4).setCellValue(customer.getEmail());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataRow.createCell(5).setCellValue(sdf.format(customer.getBirthday()));
                dataRow.createCell(6).setCellValue(customer.getAddress());
                dataRow.createCell(7).setCellValue(customer.getState() == 1 ? "已激活":"未激活");
                dataRow.createCell(8).setCellValue(sdf.format(customer.getCreateTime()));
                SimpleDateFormat updateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (customer.getUpdateTime() != null){
                    dataRow.createCell(9).setCellValue(updateTimeSdf.format(customer.getUpdateTime()));
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
            logsService.save(ShiroUtils.getUserInfo().getUserId(),"导出客户信息成功");

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
