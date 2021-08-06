<template>
  <h1>Add Exam</h1>
  <div class="submit-form">
    <div v-if="!submitted">

      <div class="form-group">
        <label style="margin:15px">Subject</label>
        <select v-model="exam.subject">
          <option v-for="subject in subjects" v-bind:key="subject.subjectId" :value="subject">
            {{ subject.name }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label style="margin:15px">Professor</label>
        <select v-model="exam.professor">
          <option v-for="professor in professors" v-bind:key="professor.professorId" :value="professor">
            {{ professor.firstname }} {{ professor.lastname }}
            <i class="arrow down"/>
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="period">Period</label>
        <input type="date" class="form-control" id="period" required v-model="exam.period" name="period">
      </div>

      <button v-on:click="onSubmit" class="btn btn-success" style="margin-top: 7px; margin-right: 5px;">Submit</button>
      <button v-on:click="cancel" class="btn btn-primary" style="margin-top: 7px">Cancel</button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newExam" style="margin-right: 7px">Add another exam</button>
      <button v-on:click="backToExams" class="btn btn-primary" style="margin-top:12px">Back to exams</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AddExam",
  data() {
    return {
      subjects: [],
      professors: [],
      exam: {
        examId: 0,
        subject: "",
        professor: "",
        period: ""
      }
    }
  },
  created() {
    this.subjectList();
    this.professorList();
  },
  methods: {
    subjectList() {
      axios.get("http://localhost:8080/api/subjects")
          .then((response) => {
            this.subjects = response.data
          }).catch((err) => console.log("Error for display subjects" + err))
    },
    professorList() {
      axios.get("http://localhost:8080/api/professors")
          .then((response) => {
            this.professors = response.data
          }).catch((err) => console.log("Error for display professors" + err))
    },
    onSubmit() {
      var data = {
        examId: this.exam.examId,
        subject: this.exam.subject,
        professor: this.exam.professor,
        period: this.exam.period,
      };

      axios.post("http://localhost:8080/api/exams", data)
          .then(response => {
            this.exam.examId = response.data.examId;
            this.exam.subject = response.data.subject;
            this.exam.professor = response.data.professor;
            console.log(response.data.examId);
          })
          .catch(e => {
            console.log(e);
          });
      this.submitted = true;
    },
    cancel() {
      this.$router.push('/exams');
    },
    newExam() {
      this.submitted = false;
      this.exam = {};
    },
    backToExams() {
      this.$router.push('/exams');
    }
  }
}
</script>

<style scoped>

</style>
