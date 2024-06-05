package com.sise.android.service.impl;

import com.sise.android.mapper.InformationMapper;
import com.sise.android.pojo.Classed;
import com.sise.android.pojo.CourseStudent;
import com.sise.android.pojo.Result;
import com.sise.android.pojo.Results;
import com.sise.android.service.InformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author draven
 */
@Service
@Transactional(rollbackForClassName = "Exception")
public class InformationServiceImpl implements InformationService {

    @Resource
    InformationMapper informationMapper;
    @Override
    public List<CourseStudent> findCourseBySid(String sid) {
        return informationMapper.findCourseBySid(sid);
    }

    @Override
    public List<Result> findScoreBySno(String sno) {
        return informationMapper.findScoreBySno(sno);
    }

    @Override
    public List<Classed> findAllClass() {
        return informationMapper.findAllClass();
    }

    @Override
    public Results addScoreBySidAndCid(Results results) {
        informationMapper.addScoreBySidAndCid(results);
        return results;
    }

    @Override
    public int updateScoreByRid(String rid, String score) {
        return informationMapper.updateScoreByRid(rid, score);
    }
}
