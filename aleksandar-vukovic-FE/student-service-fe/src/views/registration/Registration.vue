<template>
  <div class="container">
    <h3>All registrated exams</h3>
    <table class="table">
      <thead>
      <tr>
        <th>Student</th>
        <th>Registrated exam</th>
        <th>Professor on duty</th>
        <th>Registrated on date</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="registratedExam in registratedExams" v-bind:key="registratedExam.examRegistrationId">
        <td>{{ registratedExam.student.firstname }} {{ registratedExam.student.lastname }}</td>
        <td>{{ registratedExam.exam.subject.name }}</td>
        <td>{{ registratedExam.exam.professor.firstname }} {{ registratedExam.exam.professor.lastname }}</td>
        <td>{{ registratedExam.exam.period }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Registration",
  data() {
    return {
      registratedExams: [],
    };
  },
  created() {
    this.registrationList();
  },
  methods: {
    registrationList() {
      axios.get("http://localhost:8080/api/registration")
          .then((response) => {
            this.registratedExams = response.data;
          })
          .catch((err) =>
              console.log("Error while displaying registratedExams " + err)
          );
    },
  },
}
</script>

<style scoped>

</style>
