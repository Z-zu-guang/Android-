package com.sise.android.mapper;

import com.sise.android.pojo.Classed;
import com.sise.android.pojo.CourseStudent;
import com.sise.android.pojo.Result;
import com.sise.android.pojo.Results;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface InformationMapper {

    /**
     * 查询课程表
     * @param sid
     * @return
     */
    @Select("select cs.day, cs.time, ci.courseName, ci.courseCode, ci.classroom, ci.instructor, ci.week\n" +
            "from course_student cs, course_information ci\n" +
            "where cs.courseId = ci.id\n" +
            "order by day,time")
    List<CourseStudent> findCourseBySid(@Param("sid")String sid);


    /**
     * 查询该id的所有成绩
     * @param
     * @return
     */
    @Select("select r.id, ci.id as cid, courseName, courseCode, score " +
            "from course_information ci " +
            "left join result r on ci.id = r.cid " +
            "where ci.id in " +
            "(select distinct courseId from course_student " +
            "where sid = " +
            "(select id from customer where no = #{no} ) )")
    List<Result> findScoreBySno(@Param("no") String sno);

    /**
     * 查询所有班级
     * @return
     */
    @Select("select * from class;")
    List<Classed> findAllClass();

    /**
     * 添加不在表里的记录
     * @param results
     * @return
     */
    @Insert("insert into result(sid, cid) values (#{sid}, #{cid} )")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addScoreBySidAndCid(Results results);

    /**
     * 修改成绩
     * @param id
     * @param score
     * @return
     */
    @Update("update result set score = #{score} where id = #{id} ")
    int updateScoreByRid(@Param("id") String id, @Param("score") String score);
}
