package az.transactional.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
     int id;
     String name;
     String rollNo;
     String semester;

     public Student(String name, String rollNo, String semester){
          this.name=name;
          this.rollNo=rollNo;
          this.semester=semester;
     }
}


