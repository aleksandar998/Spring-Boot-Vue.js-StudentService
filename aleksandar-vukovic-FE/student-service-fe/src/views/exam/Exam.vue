<template>
  <div class="container">
    <h1>All Exams</h1>
    <table class="table">
      <thead>
      <tr>
        <th>#</th>
        <th>Subject</th>
        <th>Professor</th>
        <th>Period</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(exam, index) in exams" v-bind:key="exam.examId">
        <th scope="row">{{(currentPage * pageSize) + index + 1}}</th>
        <td>{{exam.subject.name}}</td>
        <td>{{exam.professor.firstname}} {{exam.professor.lastname}}</td>
        <td>{{exam.period}}</td>
      </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="gotoToPage(0)">First</a></li>
        <li class="page-item"><a class="page-link" @click="previousPage()">Previous</a></li>
        <li class="page-item" :class="{'active': pageNum===currentPage}" v-for="pageNum of pageLinks" :key="pageNum">
          <a class="page-link"  @click="gotoToPage(pageNum)" >{{pageNum}}</a></li>
        <li class="page-item"><a class="page-link" @click="nextPage()">Next</a></li>
        <li class="page-item"><a class="page-link" @click="gotoToPage(totalPage-1)">Last</a></li>
      </ul>
    </nav>
    <div class="buttons">
      <button class="btn btn-primary" style="margin-right: 1000px;" v-on:click="addExamPage"><b-icon-plus/> Add new</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Exam",
  data(){
    return{
      exams: [],
      subjects: [],
      professors: [],
      currentPage: 0,
      totalPage: 0,
      pageSize: 3,
      pageLinks: []
    }
  },
  created() {
    this.examList();
    this.subjectList();
    this.professorList();
    this.loadPage();
  },
  methods: {
    examList(){
      axios.get("http://localhost:8080/api/exams")
          .then((response) => {
            this.exams = response.data
          }).catch((err) => console.log("Error for display exams"+ err))
    },
    subjectList(){
      axios.get("http://localhost:8080/api/subjects")
          .then((response) => {
            this.subjects = response.data
          }).catch((err) => console.log("Error for display subjects"+ err))
    },
    professorList(){
      axios.get("http://localhost:8080/api/professors")
          .then((response) => {
            this.professors = response.data
          }).catch((err) => console.log("Error for display professors"+ err))
    },
    addExamPage(){
      this.$router.push('/new-exam');
    },
    loadPage(){
      axios.get(`http://localhost:8080/api/exams/page?page=${this.currentPage}&size=${this.pageSize}`)
          .then((response) => {
            console.log(response)
            this.totalPages = response.data.totalPages;
            this.pageSize = response.data.size;
            this.createPageLinks();
            this.professorList = response.data.content
          });
    },
    previousPage(){
      this.currentPage = this.currentPage > 0 ? this.currentPage - 1 : 0;
      console.log(this.currentPage)
    },
    nextPage() {
      this.currentPage = this.currentPage < this.totalPages ? this.currentPage + 1 : 0;
      console.log(this.currentPage)
      this.loadPage();
    },
    gotoToPage(page) {
      console.log('page',page);
      this.currentPage = page;
      this.loadPage();
    },
    createPageLinks() {
      const firsPage = this.currentPage ===0 ? this.currentPage : this.currentPage ===this.totalPages - 1?this.currentPage -2 : this.currentPage -1 ;

      this.pageLinks = [...Array(3).keys()].map(x => firsPage + x);
    }
  }
}
</script>

<style scoped>
.buttons{
  display: flex;
}
</style>