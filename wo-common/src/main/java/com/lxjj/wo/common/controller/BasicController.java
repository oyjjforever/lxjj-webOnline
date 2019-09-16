package com.lxjj.wo.common.controller;


import com.lxjj.wo.common.entity.PageResult;
import com.lxjj.wo.common.entity.ResponseResult;
import com.lxjj.wo.common.entity.StatusCode;
import com.lxjj.wo.common.utils.ListUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
//@ApiSort(value = 1)
@Api(tags = "通用接口")
@RequestMapping(value = "api/v1/basic")
public class BasicController {
    @Autowired
    private SqlSession sqlSession;

    @GetMapping("queryData")
    @ApiOperation(value = "通用查询接口", notes = "同时支持过滤、分页、排序等功能", position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mapperId", value = "SQL配置的完整限定名称（namespace.id)", example = "com.xxx.xxx.selectList", required = true, dataType = "string"),
            @ApiImplicitParam(name = "orderBy", value = "排序，如[name asc,age desc]", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isList", value = "是否列表，true：列表，false：实体", defaultValue = "true", dataType = "boolean")
    })
    public ResponseResult queryData(
            @RequestParam String mapperId,
            @RequestParam(required = false, defaultValue = "true") Boolean isList,
            HttpServletRequest req) {
        Map<String, Object> params = this.paramsConversion(req);
        Object orderBy = params.get("orderBy");
        List<Object> data = sqlSession.selectList(mapperId, params);
        if (isList) {
            if (orderBy != null) {
                List<String> fieldNames = new ArrayList<>();
                List<String> orderMode = new ArrayList<>();
                for (String e : Arrays.asList((String[]) orderBy)) {
                    fieldNames.add(e.split(" ")[0]);
                    orderMode.add(e.split(" ")[1]);
                }
                ListUtil.sort(data, fieldNames, orderMode);
            }
            return ResponseResult.success(data);
        }
        if (data.size()>0) return ResponseResult.success(data.get(0));
        return  ResponseResult.success("数据为空！",null);
    }

    @GetMapping("pagingData")
    @ApiOperation(value = "通用分页查询接口", notes = "同时支持过滤、分页、排序等功能", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mapperId", value = "SQL配置的完整限定名称（namespace.id)", example = "com.xxx.xxx.selectList", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageSize", value = "页容量",defaultValue = "10",dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码",defaultValue = "1",dataType = "int"),
            @ApiImplicitParam(name = "orderBy", value = "排序，如[name asc,age desc]", dataType = "string", paramType = "query")
    })
    public ResponseResult<PageResult> pagingData(
            @RequestParam String mapperId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum,
            HttpServletRequest req) {
        Map<String, Object> params = this.paramsConversion(req);
        Object orderBy = params.get("orderBy");
        Page<Map<String, Object>> page = PageHelper.startPage(
                pageNum,
                pageSize,
                orderBy == null ? "" : StringUtils.join((String[]) orderBy)
        );
        List<Object> data= sqlSession.selectList(mapperId, params);
        return ResponseResult.success(new PageResult<>(page.getTotal(), data, page.getPages(), page.getPageSize(), page.getPageNum()));
    }

    @RequestMapping(value = "execute", method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    @ApiOperation(value = "通用增删改接口（POST：新增，DELETE：删除，PUT：修改）", position = 3)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mappingId", value = "SQL配置的完整限定名称（namespace.id），如：com.xxx.xxx.update", required = true, dataType = "string")
    })
    public ResponseResult execute(
            @RequestParam String mapperId,
            HttpServletRequest req) {
        String method = req.getMethod();
        Map<String, Object> params = this.paramsConversion(req);
        if (method.equalsIgnoreCase(RequestMethod.POST.toString())) {
            Integer result = sqlSession.insert(mapperId, params);
            return result > 0 ? ResponseResult.success(params) : ResponseResult.failure(StatusCode.Error);
        } else if (method.equalsIgnoreCase(RequestMethod.DELETE.toString())) {
            Integer result = sqlSession.delete(mapperId, params);
            return result > 0 ? ResponseResult.success() : ResponseResult.failure(StatusCode.Error);
        } else {
            Integer result = sqlSession.update(mapperId, params);
            return result > 0 ? ResponseResult.success() : ResponseResult.failure(StatusCode.Error);
        }
    }

    /**
     * 参数转换
     *
     * @param req
     * @return
     */
    private Map<String, Object> paramsConversion(HttpServletRequest req) {
        Map<String, Object> params = new HashMap<>();

        for (Map.Entry<String,String[]> entry:req.getParameterMap().entrySet()) {
            String k =entry.getKey();
            String[] v =entry.getValue();
            if (v.length == 1 && StringUtil.isNotEmpty(v[0])) {
                if (v[0].startsWith("[") && v[0].endsWith("]")) {
                    if (!v[0].equals("[]")) {
                        String[] arrayParams = v[0].replace("[", "").replace("]", "").replace("\"", "").split(",");
                        params.put(k,arrayParams);
                    }
                } else {
                    params.put(k, v[0]);
                }
            }
            if (v.length > 1) {
                params.put(k, v);
            }
        }
        return params;
    }
}
