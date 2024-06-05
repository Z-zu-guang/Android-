package com.sise.android.service;

import com.sise.android.pojo.Classed;
import com.sise.android.pojo.CourseStudent;
import com.sise.android.pojo.Result;
import com.sise.android.pojo.Results;

import java.util.List;

/**
 * @author draven
 */
public interface InformationService {
    /**
     * 查课程表
     * @return
     */
    List<CourseStudent> findCourseBySid(String sid);

    /**
     * 查询成绩
     * @param sno
     * @return
     */
    List<Result> findScoreBySno(String sno);

    /**
     * 查询所有班级
     * @return
     */
    List<Classed> findAllClass();

    /**
     * 把还没有插入成绩表的数据插入成绩表
     * @return
     */
    Results addScoreBySidAndCid(Results results);

    /**
     * 修改成绩
     * @param rid
     * @param score
     * @return
     */
    int updateScoreByRid(String rid, String score);
}
