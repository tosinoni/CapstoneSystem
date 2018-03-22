package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.EmailDTO;
import com.carleton.CapstoneSystem.models.Student;
import com.carleton.CapstoneSystem.repositories.StudentRepository;
import com.carleton.CapstoneSystem.services.EmailService;
import com.carleton.CapstoneSystem.utils.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Max;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Controller
public class CoordinatorController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EmailService emailService;


    public Response sendEmails(EmailDTO email) {
        Mail mail = new Mail();
        mail.setFrom(email.getFrom());
        mail.setContent(email.getContent());
        mail.setSubject(email.getSubject());
        ArrayList<String> emails = getRecipients(email.getRecipients());
        for(String sendTo: emails){
            mail.setTo(sendTo);
            emailService.sendSimpleMessage(mail);
        }
        return Response.status(Response.Status.CREATED).build();

    }

    private ArrayList<String> getRecipients(String recipients) {
        if(recipients.equals("ALL_STUDENTS")){
            return allStduents();
        }else if(recipients.equals("STDUENTS_WITHOUT_PROJECT")){
            return noProjectStudents();
        }else{
            return projectStudents();
        }
    }

    private ArrayList<String> projectStudents() {
        ArrayList<Student> studentsWithNoProject=studentRepository.findAllByProjectIsNotNull();
        return copyEmails(studentsWithNoProject);
    }

    private ArrayList<String> noProjectStudents() {
        ArrayList<Student> studentsWithNoProject=studentRepository.findAllByProjectIsNull();
        return copyEmails(studentsWithNoProject);


    }

    private ArrayList<String> copyEmails(ArrayList<Student> studentsWithNoProject) {
        ArrayList<String> emails =new ArrayList<String>();

        for(Student student: studentsWithNoProject){
            emails.add(student.getEmail());
        }
        return emails;
    }

    private ArrayList<String> allStduents() {
        ArrayList<Student> studentsWithNoProject=studentRepository.findAll();
        return copyEmails(studentsWithNoProject);

    }
}
