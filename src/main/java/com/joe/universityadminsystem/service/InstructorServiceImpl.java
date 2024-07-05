package com.joe.universityadminsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joe.universityadminsystem.entity.Course;
import com.joe.universityadminsystem.entity.Instructor;
import com.joe.universityadminsystem.entity.Student;
import com.joe.universityadminsystem.exception.InstructorNotFoundException;
import com.joe.universityadminsystem.repository.InstructorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    InstructorRepository instructorRepository;

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructor(int id) {
        return unwrapInstructor(id);
    }

    @Override
    public List<Instructor> getInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor updateInstructor(Instructor instructor, int id) {
        //Get the instructor from database
        Instructor unwrappedInstructor = unwrapInstructor(id);

        //Update the instructor
        unwrappedInstructor.setName(instructor.getName());
        unwrappedInstructor.setEmail(instructor.getEmail());

        //Save the instructor
        return instructorRepository.save(unwrappedInstructor);

    }

    @Override
    public void deleteInstructor(int id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public void deleteInstructors() {
        instructorRepository.deleteAll();
    }

    @Override
    public Set<Course> getAssignedCourses(int id) {
        Instructor unwrappedInstructor = unwrapInstructor(id);
        return unwrappedInstructor.getCourses();
    }

    @Override
    public Instructor unwrapInstructor(int instructorId) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);
        if(instructor.isPresent()){
            return instructor.get();
        }
        throw new InstructorNotFoundException(instructorId);
    }

    
    

}
