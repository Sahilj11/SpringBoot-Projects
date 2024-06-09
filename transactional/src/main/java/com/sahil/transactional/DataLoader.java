package com.sahil.transactional;

import com.sahil.transactional.entities.Course;
import com.sahil.transactional.entities.Student;
import com.sahil.transactional.repo.CourseRepo;
import com.sahil.transactional.repo.StudentRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** DataLoader */
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Override
    public void run(String... args) throws Exception {
        var st1 = new Student();
        st1.setName("sahil");
        st1.setEmail("thisisemail");
        var st2 = new Student();
        st2.setName("Ram");
        st2.setEmail("Secondemail");
        var st3 = new Student();
        st3.setName("Shyam");
        st3.setEmail("Thirdemail");
        var c1 = new Course();
        c1.setTitle("MCA");
        c1.setCredits(55);
        var c2 = new Course();
        c2.setTitle("Btech");
        c2.setCredits(34);
        st1.setCourses(List.of(c1, c2));
        st2.setCourses(List.of(c1));
        st3.setCourses(List.of(c1, c2));
        courseRepo.saveAll(List.of(c1, c2));
        studentRepo.saveAll(List.of(st1, st2, st3));
    }
}
