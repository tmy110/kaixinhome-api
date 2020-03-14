package com.kaixin8848.home.web.base.service.impl;


import com.kaixin8848.home.core.AbstractService;
import com.kaixin8848.home.web.base.dao.UploadFileMapper;
import com.kaixin8848.home.web.base.model.UploadFile;
import com.kaixin8848.home.web.base.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by 马路遥 on 2019/07/30.
 */
@Service
@Transactional
public class UploadFileServiceImpl extends AbstractService<UploadFile> implements UploadFileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;

}
