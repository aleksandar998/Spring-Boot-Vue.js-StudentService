<template>
  <div class="submit-form">
    <h1>Add registration exam</h1>
    <div v-if="!submitted">

      <div class="form-group">
        <label style="margin:15px">Student</label>
        <select v-model="examRegistration.student">
          <option v-for="student in students" v-bind:key="student.studentId " :value="student">
            {{student.firstname }}  {{student.lastname }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label style="margin:15px">Exam</label>
        <select v-model="examRegistration.exam">
          <option v-for="exam in exams" v-bind:key="exam.examId" :value="exam">
            {{ exam.subject.name }} - {{ exam.professor.firstname}} {{ exam.professor.lastname}}
          </option>
        </select>
      </div>

      <button v-on:click="saveExamRegistration" class="btn btn-success" style="margin-top:12px">Submit</button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newRegistration">Register another exam</button>
      <button @click="goToRegistratedExams()" class="btn btn-primary" style="margin-top:12px">List of registrated exams</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "AddNewRegistration",
  data() {
    return {
      students: [],
      exams: [],
      examRegistration: {
        id: 0,
        student: "",
        exam: "",
      },
      submitted: false,
    };
  },
  created() {
    this.studentList();
    this.examList();
  },
  methods: {
    studentList() {
      axios.get("http://localhost:8080/api/students")
          .then((response) => {
            this.students = response.data;
          })
          .catch((err) => console.log("Error while displaying students" + err));
    },
    examList() {
      axios.get("http://localhost:8080/api/exams")
          .then((response) => {
            this.exams = response.data;
          })
          .catch((err) => console.log("Error while displaying exams" + err));
    },
    saveExamRegistration() {
      var data = {
        id: this.examRegistration.id,
        student: this.examRegistration.student,
        exam: this.examRegistration.exam
      };
      axios.post("http://localhost:8080/api/registration", data)
          .then((response) => {
            this.examRegistration.id = response.data.id;
            this.examRegistration.student = response.data.student;
            this.examRegistration.exam = response.data.exam;
            console.log(response.data.id);
          })
          .catch((e) => {
            console.log(e);
          });
      this.submitted = true;
    },
    goToRegistratedExams() {
      this.$router.push("/registration");
    },
    newRegistration() {
      this.submitted = false;
      this.examRegistration = {};
    },
  },
}
</script>

<style scoped>

</style>