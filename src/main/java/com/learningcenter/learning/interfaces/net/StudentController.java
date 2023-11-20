package com.learningcenter.learning.interfaces.net;

import com.learningcenter.learning.domain.model.queries.GetStudentByAcmeStudentRecordIQuery;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.services.StudentCommandService;
import com.learningcenter.learning.domain.services.StudentQueryService;
import com.learningcenter.learning.interfaces.net.resources.CreateStudentResource;
import com.learningcenter.learning.interfaces.net.resources.StudentResource;
import com.learningcenter.learning.interfaces.net.transform.CreateStudentCommandFromResourceAssembler;
import com.learningcenter.learning.interfaces.net.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Student management endpoints")
public class StudentController {

    private  final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@RequestBody CreateStudentResource resource){
        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource);
        var studentId = studentCommandService.handle(createStudentCommand);

        if(studentId.studentRecordId().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var getStudentByAcmeStudentRecordIdQuery = new GetStudentByAcmeStudentRecordIQuery(studentId);
        var student = studentQueryService.handle(getStudentByAcmeStudentRecordIdQuery);

        if(student.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return  new ResponseEntity<>(studentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{studentRecordId}")
    public ResponseEntity<StudentResource> getStudentByAcmeStudentRecordId(@PathVariable String studentRecordId){
        var acmeStudentRecordId = new AcmeStudentRecordId(studentRecordId);
        var getStudentByAcmeStudentRecordIdQuery = new GetStudentByAcmeStudentRecordIQuery(acmeStudentRecordId);
        var student = studentQueryService.handle(getStudentByAcmeStudentRecordIdQuery);

        if(student.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }
}
