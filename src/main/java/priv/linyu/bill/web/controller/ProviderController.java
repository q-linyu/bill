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
import priv.linyu.bill.core.entity.po.Provider;
import priv.linyu.bill.core.service.LogsService;
import priv.linyu.bill.core.service.ProviderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: ProviderController
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 11:23
 * @version: 1.0
 **/
@RestController
public class ProviderController {
    
    @Autowired
    private ProviderService providerService;

    @Autowired
    private LogsService logsService;

    /**
     * 分页查询
     * @param providerName
     * @param pageHepler
     * @return
     */
    @GetMapping(value = "/provider/page")
    public ResponseData<PageInfo<Provider>> page(@RequestParam(value = "providerName",required = false)String providerName, PageHepler pageHepler){
        return new ResponseData<>(HttpState.SUCCESS, providerService.select(providerName,pageHepler));
    }

    /**
     * 删除
     * @param pid 主键id
     * @return
     */
    @DeleteMapping(value = "/provider/{pid}")
    public ResponseData<Void> delete(@PathVariable(value = "pid")Integer pid){
        providerService.deleteByPrimaryKey(pid);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"删除供应商成功");
        return new ResponseData<>(HttpState.PARTIAL_CONTENT);
    }

    /**
     * 编辑
     * @param pid 主键id
     * @return
     */
    @GetMapping(value = "/provider")
    public ResponseData<Provider> edit(@RequestParam(value = "pid")Integer pid){
        return new ResponseData<>(HttpState.SUCCESS,providerService.selectByPrimaryKey(pid));
    }

    /**
     * 修改
     * @param provider 对象
     * @return
     */
    @PutMapping(value = "/provider/update")
    public ResponseData<Void> update(Provider provider){
        providerService.update(provider);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"修改供应商成功");
        return new ResponseData<>(HttpState.NO_CONTENT);
    }


    /**
     * 新增
     * @param provider 对象
     * @return
     */
    @PostMapping(value = "/provider/add")
    public ResponseData<Void> add(Provider provider){
        providerService.add(provider);
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"新增供应商成功");
        return new ResponseData<>(HttpState.CREATED);
    }

    /**
     * 导出
     * @param response
     * @return
     */
    @GetMapping(value = "/provider/export")
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
            titles.add("供应商编码");
            titles.add("供应商名称");
            titles.add("供应商");
            titles.add("电话号码");
            titles.add("地址");
            titles.add("传真");
            titles.add("描述");
            titles.add("创建时间");
            titles.add("修改时间");

            // 遍历表头
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles.get(i));
            }

            // 遍历数据
            List<Provider> providers = providerService.selectAll();

            // 遍历数据
            for (Provider provider : providers) {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
                dataRow.createCell(0).setCellValue(provider.getProviderCode());
                dataRow.createCell(1).setCellValue(provider.getProviderName());
                dataRow.createCell(2).setCellValue(provider.getPeople());
                dataRow.createCell(3).setCellValue(provider.getPhone());
                dataRow.createCell(4).setCellValue(provider.getAddress());
                dataRow.createCell(5).setCellValue(provider.getFax());
                dataRow.createCell(6).setCellValue(provider.getDescribe());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataRow.createCell(7).setCellValue(sdf.format(provider.getCreateTime()));
                SimpleDateFormat updateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (provider.getUpdateTime() != null){
                    dataRow.createCell(8).setCellValue(updateTimeSdf.format(provider.getUpdateTime()));
                }else {
                    dataRow.createCell(8).setCellValue("-");
                }

            }

            SimpleDateFormat downloadSdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // 设置文件的名称
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+downloadSdf.format(new Date())+".xls");

            os = response.getOutputStream();

            workbook.write(os);

            // 记录日志
            logsService.save(ShiroUtils.getUserInfo().getUserId(),"导出供应商信息成功");

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
