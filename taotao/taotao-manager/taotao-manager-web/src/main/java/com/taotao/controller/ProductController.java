package com.taotao.controller;

import com.taotao.pojo.ContentCategoryVO;
import com.taotao.pojo.ProductMenuVO;
import com.taotao.pojo.ProductVO;
import com.taotao.service.IproductService;
import com.taotao.utils.BeanToMap;
import com.taotao.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: ProductController
 * </p>
 *
 * <p>
 * Description: 商品的控制器
 * </p>
 *
 * @author wansiliang
 * @date 2018年4月5日
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     * 私有内部类，只能再当前方法内使用
     * <p>
     * Title: TreeNode
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     * @author wansiliang
     * @date 2018年4月6日
     */
    private class TreeNode implements Serializable {

        /**
         * serialVersionUID
         */

        private static final long serialVersionUID = 3862795433008931749L;
        /**
         * 当前节点的id
         */
        Long id;
        /**
         * 节点显示的名称
         */
        String text;
        /**
         * 节点的状态，如果是closed就是一个文件夹形式，
         * 当打开时还会 做一次请求。如果是open就显示为叶子节点。
         */
        String state;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

    }

    @Autowired
    private IproductService productService;

    /**
     * 分页查询商品
     * <p>
     * Title: getProducts
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     * @param currentPage
     * @param rows
     * @return {total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
     * 前台为EASYUI其需要的json格式为
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getProducts(@RequestParam("page") Integer currentPage,
                                           @RequestParam("rows") Integer rows) {
        Map<String, Object> pageVO = calculatePageVO(currentPage, rows);
        List<ProductVO> products = productService.getProduct(pageVO);
        // 如果products 为空返回null
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }
        Map<String, Object> viewPageVo = new HashMap<String, Object>(10);
        // 取大于该小数的最小整数
        viewPageVo.put("total", products.get(0).getTotalNum());
        viewPageVo.put("rows", products);
        return viewPageVo;
    }

    private Map<String, Object> calculatePageVO(Integer currentPage, Integer rows) {
        // 声明起始 id
        Integer startId = null;
        startId = currentPage * rows;
        Map<String, Object> pageMap = new HashMap<String, Object>(2);
        pageMap.put("startId", startId);
        pageMap.put("rows", rows);
        return pageMap;
    }

    /**
     * <th data-options="field:'ck',checkbox:true"></th>
     * <th data-options="field:'id',width:60">ID</th>
     * <th data-options="field:'itemCatId',width:80">商品类目ID</th>
     * <th data-options="field:'itemCatName',width:100">商品类目</th>
     * <th data-options=
     * "field:'paramData',width:300,formatter:formatItemParamData">规格(只显示分组名称)</th>
     * <th data-options=
     * "field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
     * <th data-options=
     * "field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
     *
     * <p>
     * Title: getProductParaMenus
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     * @param currentPage
     * @param rows
     */
    @RequestMapping(value = "/param/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductParaMenus(@RequestParam("page") Integer currentPage,
                                                   @RequestParam("rows") Integer rows) {
		/*
		封装list<map>返回给前端
		List<Map<String, Object>> itemParamListViewVos = new ArrayList<Map<String,
		Object>>(30);
		// 模拟单个对象map
		Map<String, Object> itemParamListViewVo = new HashMap<String, Object>(10);
		itemParamListViewVo.put("id", "id");
		itemParamListViewVo.put("itemCatId", "itemCatId");
		itemParamListViewVo.put("itemCatName", "itemCatName");
		itemParamListViewVo.put("paramData", "paramData");
		itemParamListViewVo.put("created", "created");
		itemParamListViewVo.put("updated", "updated");
		Map<String, Object> pageVO = calculatePageVO(currentPage, rows);
		*/
        List<Map<String, Object>> productParaMenus = productService.getProductParaMenus(null);

        Map<String, Object> viewPageVo = new HashMap<String, Object>(10);
        // 取大于该小数的最小整数
        viewPageVo.put("total", productParaMenus.size());
        if ("0.0".equals((viewPageVo.get("total")))) {
            viewPageVo.put("total", "1");
        }
        int fromIndex = 0;
        int toIndex = 0;
        // 如果起始角标大于map最大索引则起始设置为最0,终止为最大size。如果小于，则取起始
        if (rows * currentPage > productParaMenus.size() - 1) {

            viewPageVo.put("rows", productParaMenus.subList(fromIndex, productParaMenus.size()));
        } else {
            fromIndex = rows * currentPage;
            // 如果终止角标大于最大所有设置为最大 size
            if (rows * (currentPage + 1) > productParaMenus.size() - 1) {
                toIndex = productParaMenus.size();
            } else {
                toIndex = rows * (currentPage + 1);
            }
            viewPageVo.put("rows", productParaMenus.subList(fromIndex, toIndex));

        }
        return viewPageVo;
    }

    @RequestMapping(value = "/cat/list", method = {RequestMethod.POST})
    @ResponseBody
    public List<TreeNode> getProductMenus(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

        TreeNode treeNode = null;
        Map<String, Object> paraMap = new HashMap<String, Object>(10);
        paraMap.put("parentId", parentId.intValue());
        List<ProductMenuVO> productMenus = productService.getProductMenuVO(paraMap);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>(30);
        for (ProductMenuVO productMenuVO : productMenus) {
            treeNode = new TreeNode();
            treeNode.setId(productMenuVO.getMenuId());
            treeNode.setState(productMenuVO.getIsParent() == 1 ? "open" : "closed");
            treeNode.setText(productMenuVO.getMenuName());
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }

    /**
     * <p>
     * Title: saveNewProduct
     * </p>
     *
     * <p>
     * Description:创建新的产品 需要同时写入两张表。在service层实现，为了统一管理事物
     * </p>
     *
     * @param productVO
     * @param desc
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveNewProduct(ProductVO productVO, String desc) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        // 商品状态，1-正常，2-下架，3-删除
        productVO.setStatus('1');
        Map<String, Object> paraMap = BeanToMap.beanToMap(productVO);
        Boolean saveProductIsOk = productService.saveProduct(paraMap);
        // 前端要求的范围格式
        if (!saveProductIsOk) {
            resultMap.put("status", 500);
            resultMap.put("msg", "false");
            resultMap.put("data", null);
            return resultMap;
        }
        resultMap.put("status", 200);
        resultMap.put("msg", "OK");
        resultMap.put("data", null);
        return resultMap;
    }

    /**
     * <p>
     * Title: imageUpload
     * </p>
     *
     * <p>
     * Description:多线程上传图片
     * </p>
     *
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartFile uploadFile) {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        try {
            new Thread(new ImageUtils(uploadFile.getInputStream(), uploadFile.getOriginalFilename())).start();
            // 前端要求的范围格式
            resultMap.put("error", 0);
            resultMap.put("url",
                    "E:\\eclipse-workspace\\taotao\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\webapp\\WEB-INF\\image\\"
                            + uploadFile.getOriginalFilename());

        } catch (IOException e) {
            /* TODO Auto-generated catch block */
            resultMap.put("error", 1);
            resultMap.put("url", "文件上传失败");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * <p>
     * Title: getDescByProductId
     * </p>
     *
     * <p>
     * Description:通过商品ID查询商品的描述
     * </p>
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getDesc/{productId}")
    @ResponseBody
    public ProductVO getDescByProductId(@PathVariable(value = "productId") String productId) {
        Map<String, Object> paraMap = new HashMap<String, Object>(2);
        paraMap.put("productId", productId);
        List<ProductVO> products = productService.selectProductDescByProductI(paraMap);
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }
        return products.get(0);
    }

    /**
     * <p>
     * Title: getProductInfoByProductId
     * </p>
     *
     * <p>
     * Description:根据商品ID查询商品基本信息
     * </p>
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/list/{productId}")
    @ResponseBody
    public ProductVO getProductInfoByProductId(@PathVariable(value = "productId") String productId) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("productId", productId);
        List<ProductVO> products = productService.getProduct(paraMap);
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }
        return products.get(0);
    }

    /**
     * <p>
     * Title: updateProductByProductId
     * </p>
     *
     * <p>
     * Description: 根据当前productId来修改产品配置信息
     * </p>
     *
     * @param productVO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateProductByProductId(ProductVO productVO) {
        Map<String, Object> paraMap = null;
        try {
            productVO.getStatus();
            paraMap = BeanToMap.beanToMap(productVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isUpdateOK = productService.updateProductByProductId(paraMap);
        if (isUpdateOK) {
            paraMap.clear();
            paraMap.put("status", 200);
        }
        return paraMap;
    }

    /**
     * <p>
     * Title: instockProductById
     * </p>
     *
     * <p>
     * Description: 下架产品
     * </p>
     *
     * @param productVO
     * @return
     */
    @RequestMapping(value = "/instock", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> instockProductById(@RequestParam(value = "ids") String ids) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("status", '2');
        paraMap.put("ids", ids.split(","));
        boolean isUpdateOK = productService.instockOrReshelfProductByIds(paraMap);
        if (isUpdateOK) {
            paraMap.clear();
            paraMap.put("status", 200);
        }
        return paraMap;
    }

    /**
     * <p>
     * Title: instockProductById
     * </p>
     *
     * <p>
     * Description: 上架产品
     * </p>
     *
     * @param productVO
     * @return
     */
    @RequestMapping(value = "/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> reshelfProductById(@RequestParam(value = "ids") String ids) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("status", '1');
        paraMap.put("ids", ids.split(","));
        boolean isUpdateOK = productService.instockOrReshelfProductByIds(paraMap);
        if (isUpdateOK) {
            paraMap.clear();
            paraMap.put("status", 200);
        }
        return paraMap;
    }

    /**
     * <p>
     * Title: insertNewProductParam
     * </p>
     *
     * <p>
     * Description: 根据menId 新增一个参数模板
     * </p>
     * var url = "/product/param/save/"+$("#itemParamAddTable [name=cid]").val();
     * $.post(url,{"paramData":JSON.stringify(params)},function(data){
     * if(data.status == 200){
     *
     * @param paramData
     * @return
     */
    @RequestMapping(value = "/param/save/{menuId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertNewProductParam(@PathVariable("menuId") String menuId,
                                                     @RequestParam("paramData") String paramGroups) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("menuId", menuId);
        paraMap.put("paramGroups", paramGroups);
        try {
            productService.insertNewProductParam(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
            return paraMap;
        }
        paraMap.put("status", 200);
        return paraMap;
    }

    /**
     * <p>
     * Title: hasParamByMenuId
     * </p>
     *
     * <p>
     * Description: // 判断选择的类目是否已经添加过规格
     * </p>
     * <p>
     * $.getJSON("/product/param/query/menuId/" + node.id,function(data){
     * if(data.status == 200 && data.data){
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "param/query/menuId/{menuId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> hasParamByMenuId(@PathVariable("menuId") String menuId) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("menuId", menuId);
        List<ProductMenuVO> productParaMenus = productService.getMenuParamGroupsByMenId(paraMap);
        paraMap.clear();
        if (!CollectionUtils.isEmpty(productParaMenus)) {
            paraMap.put("status", 200);
            paraMap.put("data", productParaMenus);
            return paraMap;
        }
        paraMap.put("status", 500);
        return paraMap;
    }

    @RequestMapping(value = "/content/category/list", method = RequestMethod.GET)
    @ResponseBody
    public List getContentCatList(@RequestParam(defaultValue = "0", value = "id") Long parentId) {
        TreeNode treeNode = null;
		Map<String, Object> paraMap = new HashMap<String, Object>(10);
		paraMap.put("parentId", parentId.intValue());
		List<ContentCategoryVO> productMenus = productService.getContentCatList(paraMap);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>(30);
		for (ContentCategoryVO productMenuVO : productMenus) {
			treeNode = new TreeNode();
			treeNode.setId(productMenuVO.getId());
			treeNode.setState(productMenuVO.getBeParent() == 1 ? "closed" : "open");
			treeNode.setText(productMenuVO.getName());
			treeNodes.add(treeNode);
		}
		return treeNodes;
    }
}
