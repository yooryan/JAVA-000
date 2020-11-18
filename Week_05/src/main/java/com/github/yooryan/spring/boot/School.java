/*
package com.github.yooryan.spring.boot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Data
@Configuration
public class School implements ISchool {
    
    @Autowired(required = true)
    Klass class1;
    
    @Resource(name = "student")
    Student student;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
*/
