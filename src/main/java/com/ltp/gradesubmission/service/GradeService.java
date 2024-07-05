package com.ltp.gradesubmission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Grade;

public interface GradeService {
    Grade getGrade(int studentId, int courseId);
    Grade saveGrade(Grade grade, int studentId, int courseId);
    Grade updateGrade(String score, int studentId, int courseId);
    void deleteGrade(int studentId, int courseId);
    List<Grade> getStudentGrades(int studentId);
    List<Grade> getCourseGrades(int courseId);
    List<Grade> getAllGrades();
    
}
