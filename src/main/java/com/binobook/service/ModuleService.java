package com.binobook.service;

import com.binobook.base.BaseService;
import com.binobook.dao.ModuleMapper;
import com.binobook.model.TreeModel;
import com.binobook.po.Module;
import com.binobook.dao.PermissionMapper;
import com.binobook.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;


    public List<TreeModel> queryAllModules(Integer roleId) {
        // Query All Modules By roleId
        List<TreeModel> treeModelList = moduleMapper.queryAllModules();
        // Query Authorized Module List of this roleId
        List<Integer> permissionIds = permissionMapper.queryRoleHasModuleIdsByRoleId(roleId);
        // Verify if this role has that Module Id
        if (permissionIds != null && permissionIds.size() > 0) {
            // Iterate all the Module Table
            // Verify if the Module id matches
            // If yes, set the checked property true
            treeModelList.forEach(treeModel -> {
                // Verify if the roleID has the current ModuleId
                if (permissionIds.contains(treeModel.getId())) {
                    //If yes, the Role is authorized to access this Module
                    treeModel.setChecked(true);
                }
            });
        }
        return treeModelList;
    }

    public Map<String,Object> queryModuleList() {
        Map<String, Object> map = new HashMap<>();
        // Query all Modules
        List<Module> moduleList = moduleMapper.queryModuleList();
        map.put("code",0);
        map.put("msg","");
        map.put("count", moduleList.size());
        map.put("data",moduleList);

        return map;
    }


    /**
     * Add Modules
     *  1. Params Check
     *       moduleName
     *          not null，not null and unique
     *      url
     *          Second Level Menu（grade=1），not null and unique at thew same grade
     *      parent menu parentId
     *          First level（Content grade=0）    -1
     *          Secondd level|Third level（Menu|Button grade=1或2）    Not Null, parent menu should exist
     *      grade
     *          not null，0|1|2
     *      optValue(Authorization Code )
     *          not null，not null and unique
     *  2. Default Params
     *      createDate
     *      updateDate
     *  3. Perform Addition Operation，Verify affected lines
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) {
        /* 1. Params Check  */
        // grade not null，0|1|2
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2),"Illegal Grade！");

        // moduleName  Not null
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "Module Name Required！");
        // moduleName  unique at thew same level
        AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName()), "Module Name Exists at the same level！");

        // if Grade 2 （grade=1)
        if (grade == 1) {
            // url   Second level Menu（grade=1），Not Null
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"URL Required！");
            // url   Second level Menu（grade=1），Not Null and unique at the same level
            AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndUrl(grade,module.getUrl()),"Unique URL Required！");
        }

        //  parentId    Root Grade （Content grade=0）    -1
        if (grade == 0) {
            module.setParentId(-1);
        }
        //  parentId    Grade 2|Grade 3（Menu|Button grade=1或2）    Not Null，Parent Menu Required
        if (grade != 0) {
            // Not Null
            AssertUtil.isTrue(null == module.getParentId(),"Parent Menu Required！");
            // Parent Menu Required (Query Modules By Parent Id)
            AssertUtil.isTrue(null == moduleMapper.selectByPrimaryKey(module.getParentId()), "Please Assign Correct Parent Menu！");
        }

        // optValue(Authorization Code )    Not Null
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"Authorization Code Required ！");
        // optValue(Authorization Code )     Should be Unique
        AssertUtil.isTrue(null != moduleMapper.queryModuleByOptValue(module.getOptValue()),"Authorization Code ALready Exists！");


        /* 2. Default Prams  */
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());

        /* 3. Perform AAddition Operation */
        AssertUtil.isTrue(moduleMapper.insertSelective(module) < 1, "Fail to add Module！");

    }

    /**
     * Update Module
     *  1. Params Verification
     *      id
     *          not null
     *      grade
     *          not null 0|1|2
     *      moduleName
     *          not null and unique at thew same grade（itself not included）
     *      url
     *          Grade 2 Menu（grade=1），not null and unique at thew same grade
     *      optValue(Authorization Code )
     *           not null and unique at thew same grade（itself not included）
     *  2. Default Params
     *      updateDate
     *  3. Perform Addition Operation，Verify affected lines     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {

        AssertUtil.isTrue(null == module.getId(), "Empty Input！");
        // Query By Id
        Module temp = moduleMapper.selectByPrimaryKey(module.getId());

        AssertUtil.isTrue(null == temp, "No Such Record!！");

        //  grade  not null 0|1|2
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "Illegal Grade！");

        // moduleName      not null，unique name under the same grade （itself not included）
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "Module Name Required！");
        // Query Module Object By Grade and Module Name
        temp = moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName());
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getId()).equals(module.getId()), "Menu Name Already Exist at the same level！");
        }

        // url  Garde 2（grade=1），not null，unique name under the same grade （itself not included）
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "Url Required！");
            // Query Module By Grade and Manual url
            temp = moduleMapper.queryModuleByGradeAndUrl(grade, module.getUrl());
            if (temp != null) {
                AssertUtil.isTrue(!(temp.getId()).equals(module.getId()), "Url ALready Exists！");
            }
        }

        // optValue(Authorization Code )     not null，unique name under the same grade （itself not included）
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "Authorization Code Required！");
        // Query Module by Authorization Code
        temp = moduleMapper.queryModuleByOptValue(module.getOptValue());
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getId()).equals(module.getId()),"Authorization Code Already Exists！");
        }

        /* 2. Default Params  */
        // Update Time
        module.setUpdateDate(new Date());

        /* 3. Perform Addition Operation，Verify affected lines  */
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1, "Update Failed！");
    }

    /**
     * Delete Module
     *  1. Verify Module
     *  2. Cannot delete if such module do not exists
     *  3. When deleting Module，the optValue(Authorization Code) will be deleted
     *  （Verify if there is link between Module and Permission table，delete if exists）
     *  4. Update and Delete and see whichh rows are affected
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModule(Integer id) {
        // Non-empty id verification
        AssertUtil.isTrue(null == id, "Empty Inputs！");
        // Query Module By Id
        Module temp = moduleMapper.selectByPrimaryKey(id);
        // Verify if Module exists
        AssertUtil.isTrue(null == temp, "No such records！");

        // If existing Module has some modules(Query submodule by existing id as parentId)
        Integer count = moduleMapper.queryModuleByParentId(id);
        // if subModule exists，we cannot delete it
        AssertUtil.isTrue(count > 0, "Sub Module Exists，Deletion Failed！");

        // Check Module id to see if data exists
        count = permissionMapper.countPermissionByModuleId(id);
        // Delete them if exists
        if (count > 0) {
            // delete permission records linked to this module id
            permissionMapper.deletePermissionByModuleId(id);
        }

        temp.setUpdateDate(new Date());

        // Perform Deleting; Verify if success
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(temp) < 1, "Deleting Failed！");
    }
}
