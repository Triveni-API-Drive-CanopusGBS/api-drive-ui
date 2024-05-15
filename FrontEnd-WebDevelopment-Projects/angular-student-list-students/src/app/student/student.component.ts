import { Component, OnInit} from '@angular/core';
import { Student } from './student';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent implements OnInit {
  student: Student = new Student();

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
   let students:Student[]= [];
        // Logic to handle form submission
    console.log('Submitted:', this.student);
    students.push(this.student);
    console.log(students);

    // You can perform further actions like sending data to a server or storing it locally
  }
}
